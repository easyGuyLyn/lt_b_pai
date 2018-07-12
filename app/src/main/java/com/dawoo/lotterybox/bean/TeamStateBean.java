package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-24.
 */

public class TeamStateBean {

    /**
     * effectiveVolume : 0.000
     * withdrawTotal : 0.000
     * totalPeople : 1
     * totalAgent : 0
     * betTotal : 0.000
     * depositTotal : 0.000
     */

    private String effectiveVolume;
    private String withdrawTotal;
    private int totalPeople;
    private int totalAgent;
    private String betTotal;
    private String depositTotal;

    public String getEffectiveVolume() {
        return effectiveVolume;
    }

    public void setEffectiveVolume(String effectiveVolume) {
        this.effectiveVolume = effectiveVolume;
    }

    public String getWithdrawTotal() {
        return withdrawTotal;
    }

    public void setWithdrawTotal(String withdrawTotal) {
        this.withdrawTotal = withdrawTotal;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public int getTotalAgent() {
        return totalAgent;
    }

    public void setTotalAgent(int totalAgent) {
        this.totalAgent = totalAgent;
    }

    public String getBetTotal() {
        return betTotal;
    }

    public void setBetTotal(String betTotal) {
        this.betTotal = betTotal;
    }

    public String getDepositTotal() {
        return depositTotal;
    }

    public void setDepositTotal(String depositTotal) {
        this.depositTotal = depositTotal;
    }
}
