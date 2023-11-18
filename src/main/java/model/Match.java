package model;

public class Match {
    private String matchId;
    private double rateSideA;
    private double rateSideB;
    private String result;

    private double rate;


    public Match(String matchId, double rateSideA, double rateSideB, String result) {
        this.matchId = matchId;
        this.rateSideA = rateSideA;
        this.rateSideB = rateSideB;
        this.result = result;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public double getRateSideA() {
        return rateSideA;
    }

    public void setRateSideA(double rateSideA) {
        this.rateSideA = rateSideA;
    }

    public double getRateSideB() {
        return rateSideB;
    }

    public void setRateSideB(double rateSideB) {
        this.rateSideB = rateSideB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getRate(String side) {
        return (side.equals("A")) ? rateSideA : rateSideB;
    }
}
