package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {
    private String uuid;
    private int balance = 0;
    private int wonBetsCount;
    private int totalBetsCount;

    private int lostMoney;
    private int wonMoney;

    private List<String> illegalActions;

    boolean illegitimate = false;

    Logger logger = Logger.getLogger(getClass().getName());

    public boolean isIllegitimate() {
        return illegitimate;
    }

    public void setIllegitimate(boolean illegitimate) {
        this.illegitimate = illegitimate;
    }

    public int getLostMoney() {
        return lostMoney;
    }

    public void setLostMoney(int lostMoney) {
        this.lostMoney = lostMoney;
    }

    public int getWonMoney() {
        return wonMoney;
    }

    public void setWonMoney(int wonMoney) {
        this.wonMoney = wonMoney;
    }

    public void increaseWonMoney(int amount) {
        this.wonMoney += amount;
    }

    public void increaseLostMoney(int amount) {
        this.lostMoney += amount;
    }


    public Player() {
        this.illegalActions = new ArrayList<>();
    }

    public int getTotalProfit() {
        return wonMoney - lostMoney;
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

    public int getTotalBetsCount() {
        return totalBetsCount;
    }

    public void incrementTotalBetsCount() {
        this.totalBetsCount++;
    }

    public double calculateWinRate() {
        return (double) this.wonBetsCount / totalBetsCount;
    }


    public List<String> getIllegalActions() {
        return illegalActions;
    }

    public void handleIllegalAction(String action) {
        illegalActions.add(action);
    }


    public String getPlayerSummary() {
        if (!illegitimate) {
            return uuid + "," + balance + "," + String.format("%.2f", calculateWinRate());
        }
        else {
            return "";
        }
    }


}
