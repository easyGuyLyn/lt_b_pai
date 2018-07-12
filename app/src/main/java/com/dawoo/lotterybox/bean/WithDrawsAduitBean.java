package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-5-2.
 * @author alex
 */

public class WithDrawsAduitBean {

    /**
     * id : 100382
     * playerId : 10082
     * billNo : 1205020057580082
     * orderTime : 1525252023097
     * depositAmount : 99.0
     * totalCode : 7.0
     * depositCode : 10.0
     * favorableCode : 0.0
     * availableDepositCode : 0.0
     * availableFavorableCode : 0.0
     * cleared : false
     * auditTime : 1525264569874
     * adminCost : 2.97
     * deductFavorable : 0.0
     */

    private int id;
    private int playerId;
    private String billNo;
    private long orderTime;
    private double depositAmount;
    private double totalCode;
    private double depositCode;
    private double favorableCode;
    private double availableDepositCode;
    private double availableFavorableCode;
    private boolean cleared;
    private long auditTime;
    private double adminCost;
    private double deductFavorable;
    private double favorableAmount;

    public double getFavorableAmount() {
        return favorableAmount;
    }

    public void setFavorableAmount(double favorableAmount) {
        this.favorableAmount = favorableAmount;
    }

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
