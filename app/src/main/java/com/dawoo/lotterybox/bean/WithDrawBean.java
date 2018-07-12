package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-4.
 * @author alex
 */

public class WithDrawBean {


    /**
     * cardNumber : **** ****2327
     * bankCode : abc
     * balance : 0.000
     * reminder : 10
     * withdrawMaxNum : 10000
     * bankShortName : 中国农业银行
     * username : alex963
     * withdrawMinNum : 10
     */

    private String cardNumber;
    private String bankCode;
    private String balance;
    private int reminder;
    private double withdrawMaxNum;
    private String bankShortName;
    private String username;
    private double withdrawMinNum;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public double getWithdrawMaxNum() {
        return withdrawMaxNum;
    }

    public void setWithdrawMaxNum(int withdrawMaxNum) {
        this.withdrawMaxNum = withdrawMaxNum;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getWithdrawMinNum() {
        return withdrawMinNum;
    }

    public void setWithdrawMinNum(int withdrawMinNum) {
        this.withdrawMinNum = withdrawMinNum;
    }
}
