package service;

import model.Casino;
import model.Match;
import model.Player;
import type.OperationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private GameService() {}
    private static final String DRAW = "DRAW";

    public static void handlePlayerAction(List<String> playerData, Map<String, Player> playerMap, Map<String, Match> matchMap, Casino casino) {
        for (String str : playerData) {
            String[] parts = str.split(",");
            String playerId = parts[0];
            OperationType operationType = OperationType.valueOf(parts[1]);

            Player player = playerMap.getOrDefault(playerId, new Player());
            player.setUuid(playerId);

            handleBasedOnAction(matchMap, casino, str, operationType, parts, player);
            playerMap.put(playerId, player);
        }
    }

    private static void handleBasedOnAction(Map<String, Match> matchMap, Casino casino, String str, OperationType operationType, String[] parts, Player player) {
        switch (operationType) {
            case DEPOSIT:
                int depositAmount = Integer.parseInt(parts[3]);
                player.increaseBalance(depositAmount);
                break;
            case WITHDRAW:
                handleWithdraw(parts, player,casino);
                break;
            case BET:
                String matchId = parts[2];
                String betSide = parts[4];
                Match matchResult = matchMap.get(matchId);
                processBet(casino, str, parts, player, matchResult, betSide);
                break;
        }
    }

    private static void handleWithdraw(String[] parts, Player player, Casino casino) {
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) {
                parts[i] = "NULL";
            }
        }
        int withdrawAmount = Integer.parseInt(parts[3]);
        if (withdrawAmount > player.getBalance()) {
            player.handleIllegalAction(String.join(",", parts));
            casino.increaseCasinoBalance(player.getTotalProfit());
            player.setIllegitimate(true);
        } else {
            player.decreaseBalance(withdrawAmount);
        }
    }

    private static void processBet(Casino casino, String str, String[] parts, Player player, Match matchResult, String betSide) {
        if (matchResult != null) {
            int betAmount = Integer.parseInt(parts[3]);
            double rate = matchResult.getRate(betSide);

            if (betAmount > player.getBalance()) {
                player.handleIllegalAction(str);
            } else if (!matchResult.getResult().equals(DRAW)) {
                updateBalance(casino, betSide, matchResult, player, betAmount, rate);
            }
            else {
                player.incrementTotalBetsCount();
            }
        }
    }

    private static void updateBalance(Casino casino, String betSide, Match matchResult, Player player, int betAmount, double rate) {
        if (betSide.equals(matchResult.getResult())) {
            player.increaseBalance((int) (betAmount * rate));
            player.increaseWonMoney((int) (betAmount * rate));
            casino.decreaseCasinoBalance((int) (betAmount * rate));
            player.incrementWonBetsCount();
            player.incrementTotalBetsCount();
        } else {
            player.decreaseBalance(betAmount);
            player.increaseLostMoney(betAmount);
            casino.increaseCasinoBalance(betAmount);
            player.incrementTotalBetsCount();
        }
    }

            public static void mapMatchFileData(List<String> matchData, Map<String, Match> matchMap) {
        matchData.stream()
                .map(data -> data.split(","))
                .forEach(matchParts -> {
                    String matchId = matchParts[0];
                    double rateSideA = Double.parseDouble(matchParts[1]);
                    double rateSideB = Double.parseDouble(matchParts[2]);
                    String result = matchParts[3];

                    matchMap.put(matchId, new Match(matchId, rateSideA, rateSideB, result));
                });
    }

    public static List<String> getOutputContent(Map<String, Player> playerMap, Casino casino) {
        List<String> outputContent = new ArrayList<>();
        List<String> allIllegalActions = new ArrayList<>();

        for (Player player : playerMap.values()) {
            outputContent.add(player.getPlayerSummary());
            allIllegalActions.addAll(player.getIllegalActions());
        }
        outputContent.addAll(allIllegalActions);
        outputContent.add(" ");
        outputContent.add("Casino balance:" + casino.getCasinoBalance());
        return outputContent;
    }
}
