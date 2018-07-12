package com.dawoo.lotterybox.bean.Deposit;

import java.io.Serializable;

/**
 * Created by rain on 18-4-30.
 */

public class ParentDepositBean implements Serializable{

   /* Count	Integer	支付账号个数
    Accounttype	String	1-网银支付,2-第三方支付
    Type	String	1-公司入款,2-线上支付
    Bank	String	Bankpay-网银支付,wechatpay-微信支付,alipay-支付宝支付
    */

   /* 支付类型(3-微信支付4-支付宝,5-qq钱包10-网银支付)*/

    String picUrl;
    String payTran;
    String item;
    String type;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
