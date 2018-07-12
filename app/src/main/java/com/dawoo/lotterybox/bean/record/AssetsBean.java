package com.dawoo.lotterybox.bean.record;

/**
 * Created by archar on 18-2-18.
 * <p>
 * 下注总金额，派彩总金额（注单合计）
 */

public class AssetsBean {

    private double revoke;//撤单总额
    private double repeal;//撤销总额
    private double unOpen;//未开奖总额
    private double profit;//阴亏
    private double rebate;//返点总额
    private double payout;//派彩总额（中奖）
    private double effective;//有效投注总额（有效交易量）
    private double betAmount;//下注总额
    private double balance;//玩家账户余额
    private double beforeProfit;//前一天金额
    private double afterProfit;//后一天金额

    public double getRevoke() {
        return revoke;
    }

    public void setRevoke(double revoke) {
        this.revoke = revoke;
    }

    public double getRepeal() {
        return repeal;
    }

    public void setRepeal(double repeal) {
        this.repeal = repeal;
    }

    public double getUnOpen() {
        return unOpen;
    }

    public void setUpOpen(double unOpen) {
        this.unOpen = unOpen;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public double getEffective() {
        return effective;
    }

    public void setEffective(double effective) {
        this.effective = effective;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBeforeProfit() {
        return beforeProfit;
    }

    public void setBeforeProfit(double beforeProfit) {
        this.beforeProfit = beforeProfit;
    }

    public double getAfterProfit() {
        return afterProfit;
    }

    public void setAfterProfit(double afterProfit) {
        this.afterProfit = afterProfit;
    }
}
