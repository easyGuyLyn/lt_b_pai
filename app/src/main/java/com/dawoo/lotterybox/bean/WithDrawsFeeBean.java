package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-30.
 * @author alex
 */

public class WithDrawsFeeBean {

    /**
     * actualWithdraw : 300.00
     * deductFavorable : 0.00
     * fee : 0.00
     * adminCost : 0.00
     * moneyTooSmall : false
     */

    private String actualWithdraw;
    private String deductFavorable;
    private String fee;
    private String adminCost;
    private boolean moneyTooSmall;

    public String getActualWithdraw() {
        return actualWithdraw;
    }

    public void setActualWithdraw(String actualWithdraw) {
        this.actualWithdraw = actualWithdraw;
    }

    public String getDeductFavorable() {
        return deductFavorable;
    }

    public void setDeductFavorable(String deductFavorable) {
        this.deductFavorable = deductFavorable;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAdminCost() {
        return adminCost;
    }

    public void setAdminCost(String adminCost) {
        this.adminCost = adminCost;
    }

    public boolean isMoneyTooSmall() {
        return moneyTooSmall;
    }

    public void setMoneyTooSmall(boolean moneyTooSmall) {
        this.moneyTooSmall = moneyTooSmall;
    }
}
