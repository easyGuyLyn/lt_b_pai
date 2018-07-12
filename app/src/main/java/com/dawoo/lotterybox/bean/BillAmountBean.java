package com.dawoo.lotterybox.bean;

import com.dawoo.coretool.util.math.BigDemicalUtil;

/**
 * Created by rain on 18-4-20.
 */

public class BillAmountBean {
    double onlinemoney = 0.00;//在线
    double companymoney = 0.00;//公司入账
    double money = 0.00;//当前选中日期金额
    double beforemoney = 0.00;//前一天
    double aftermoney = 0.00;//后一天
    double playermoney = 0.00;//提现总额

    public double getOnlinemoney() {
        return BigDemicalUtil.round(onlinemoney, 2);

    }

    public void setOnlinemoney(double onlinemoney) {
        this.onlinemoney = onlinemoney;
    }

    public double getCompanymoney() {
        return BigDemicalUtil.round(companymoney, 2);

    }

    public void setCompanymoney(double companymoney) {
        this.companymoney = companymoney;
    }

    public double getMoney() {
        return BigDemicalUtil.round(money, 2);

    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBeforemoney() {
        return BigDemicalUtil.round(beforemoney, 2);
    }

    public void setBeforemoney(double beforemoney) {
        this.beforemoney = beforemoney;
    }

    public double getAftermoney() {
        return BigDemicalUtil.round(aftermoney, 2);
    }

    public void setAftermoney(double aftermoney) {
        this.aftermoney = aftermoney;
    }

    public double getPlayermoney() {
        return BigDemicalUtil.round(playermoney, 2);
    }

    public void setPlayermoney(double playermoney) {
        this.playermoney = playermoney;
    }
}
