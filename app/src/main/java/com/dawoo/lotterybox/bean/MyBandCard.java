package com.dawoo.lotterybox.bean;

/**
 * Created by jack on 18-2-21.
 */

public class MyBandCard {

    /**
     * bankCode : icbc
     * bankName : 中国工商银行
     * id : 19
     * type :
     * userId : 55
     * cardNumber :
     * masterName :
     */

    private String bankCode;
    private String bankName;
    private int id;
    private String type;
    private int userId;
    private String cardNumber;
    private String masterName;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
