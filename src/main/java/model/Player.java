package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {
    private String uuid;
    private int balance = 0;
    private int wonBetsCount;
    private int lostBetsCount;
    private List<String> illegalActions;

    Logger logger = Logger.getLogger(getClass().getName());



    public Player() {
        this.illegalActions = new ArrayList<>();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void increaseBalance(int amount) {
        this.balance += amount;
    }

    public void decreaseBalance(int amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        } else {
            logger.info("Error: Invalid amount or insufficient balance.");
        }
    }

    public int getWonBetsCount() {
        return wonBetsCount;
    }

    public void incrementWonBetsCount() {
        this.wonBetsCount++;
    }

    public int getLostBetsCount() {
        return lostBetsCount;
    }

    public void incrementLostBetsCount() {
        this.lostBetsCount++;
    }

    public double calculateWinRate() {
        double totalBets = wonBetsCount + lostBetsCount;
        if (totalBets == 0) {
            return 0.0;
        }
        return  wonBetsCount / totalBets;
    }

    public List<String> getIllegalActions() {
        return illegalActions;
    }

    public void addIllegalAction(String action) {
        illegalActions.add(action);
    }

    public void handleIllegalBet(String operation) {
        addIllegalAction(operation);
    }

    public String getPlayerSummary() {
        return uuid + "," + balance + "," + calculateWinRate();
    }


}
