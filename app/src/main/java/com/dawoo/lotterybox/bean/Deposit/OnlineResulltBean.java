package com.dawoo.lotterybox.bean.Deposit;

/**
 * Created by rain on 18-5-4.
 */

public class OnlineResulltBean {
    String billNo;
    boolean state = false;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNO(String billNo) {
        this.billNo = billNo;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
