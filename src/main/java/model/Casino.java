package model;

public class Casino {
    private int casinoBalance = 0;

    public int getCasinoBalance() {
        return casinoBalance;
    }

    public void setCasinoBalance(int casinoBalance) {
        this.casinoBalance = casinoBalance;
    }

    public void increaseCasinoBalance(int amount) {
        this.casinoBalance += amount;
    }

    public void decreaseCasinoBalance(int amount) {
        this.casinoBalance -= amount;
    }
}
