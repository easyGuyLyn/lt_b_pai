package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-26.
 */

public class TeamReportBean {

    /**
     * wages : 0.0
     * rebate : 0.0
     * bonus : 0.0
     * payout : 0.0
     * id : 10232
     * playername : alexm1
     * withdrawtotal : 0.0
     * favorable : 0.0
     * deposittotal : 0.0
     * effectivevolume : 0.0
     */

    private double wages;
    private double rebate;
    private double bonus;
    private double payout;
    private int id;
    private String playername;
    private double withdrawtotal;
    private double favorable;
    private double deposittotal;
    private double effectivevolume;

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public double getWithdrawtotal() {
        return withdrawtotal;
    }

    public void setWithdrawtotal(double withdrawtotal) {
        this.withdrawtotal = withdrawtotal;
    }

    public double getFavorable() {
        return favorable;
    }

    public void setFavorable(double favorable) {
        this.favorable = favorable;
    }

    public double getDeposittotal() {
        return deposittotal;
    }

    public void setDeposittotal(double deposittotal) {
        this.deposittotal = deposittotal;
    }

    public double getEffectivevolume() {
        return effectivevolume;
    }

    public void setEffectivevolume(double effectivevolume) {
        this.effectivevolume = effectivevolume;
    }
}
