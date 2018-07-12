package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-5-14.
 */

public class WithDrawsDetailBean {


    /**
     * id : 100165
     * playerId : 100192
     * billNo : 1105140061110192
     * orderTime : 1526290287714
     * depositAmount : 500.0
     * favorableAmount : 5.0
     * totalCode : 0.0
     * depositCode : 1000.0
     * favorableCode : 5.0
     * availableDepositCode : 0.0
     * availableFavorableCode : 0.0
     * cleared : false
     * auditTime : 1526290648225
     * adminCost : 15.0
     * deductFavorable : 5.0
     */

    private int id;
    private int playerId;
    private String billNo;
    private long orderTime;
    private double depositAmount;
    private double favorableAmount;
    private double totalCode;
    private double depositCode;
    private double favorableCode;
    private double availableDepositCode;
    private double availableFavorableCode;
    private boolean cleared;
    private long auditTime;
    private double adminCost;
    private double deductFavorable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getFavorableAmount() {
        return favorableAmount;
    }

    public void setFavorableAmount(double favorableAmount) {
        this.favorableAmount = favorableAmount;
    }

    public double getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(double totalCode) {
        this.totalCode = totalCode;
    }

    public double getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(double depositCode) {
        this.depositCode = depositCode;
    }

    public double getFavorableCode() {
        return favorableCode;
    }

    public void setFavorableCode(double favorableCode) {
        this.favorableCode = favorableCode;
    }

    public double getAvailableDepositCode() {
        return availableDepositCode;
    }

    public void setAvailableDepositCode(double availableDepositCode) {
        this.availableDepositCode = availableDepositCode;
    }

    public double getAvailableFavorableCode() {
        return availableFavorableCode;
    }

    public void setAvailableFavorableCode(double availableFavorableCode) {
        this.availableFavorableCode = availableFavorableCode;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(long auditTime) {
        this.auditTime = auditTime;
    }

    public double getAdminCost() {
        return adminCost;
    }

    public void setAdminCost(double adminCost) {
        this.adminCost = adminCost;
    }

    public double getDeductFavorable() {
        return deductFavorable;
    }

    public void setDeductFavorable(double deductFavorable) {
        this.deductFavorable = deductFavorable;
    }
}
