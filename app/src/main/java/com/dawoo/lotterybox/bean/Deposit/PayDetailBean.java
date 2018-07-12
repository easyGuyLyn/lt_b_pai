package com.dawoo.lotterybox.bean.Deposit;

import java.io.Serializable;

/**
 * Created by rain on 18-5-1.
 */

public class PayDetailBean implements Serializable{


    /* bankCode	String	银行卡代号
     singleDepositMin	Integer	单笔最少存款金额
     singleDepositMax	Integer	单笔最多存款金额
     qrCodeUrl	String	二维码图片地址
     Id	Integer	Id
     Type	String	1-公司入款2-线上支付
     Account	String	账号*/
    String bankCode;
    double singleDepositMin;
    double singleDepositMax;
    String qrCodeUrl;
    int id;
    String account;
    String type;
    String customBankName;//第3方独有
    String openAcountName;
    String fullName;

    boolean randomAmount;//线上支付独有
    String accountType;
    String item;
    String picUrl;
    String payName;
    String payTran;

    public String getPayTran() {
        return payTran;
    }

    public void setPayTran(String payTran) {
        this.payTran = payTran;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public double getSingleDepositMin() {
        return singleDepositMin;
    }

    public void setSingleDepositMin(double singleDepositMin) {
        this.singleDepositMin = singleDepositMin;
    }

    public double getSingleDepositMax() {
        return singleDepositMax;
    }

    public void setSingleDepositMax(double singleDepositMax) {
        this.singleDepositMax = singleDepositMax;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomBankName() {
        return customBankName;
    }

    public void setCustomBankName(String customBankName) {
        this.customBankName = customBankName;
    }

    public String getOpenAcountName() {
        return openAcountName;
    }

    public void setOpenAcountName(String openAcountName) {
        this.openAcountName = openAcountName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isRandomAmount() {
        return randomAmount;
    }

    public void setRandomAmount(boolean randomAmount) {
        this.randomAmount = randomAmount;
    }
}
