package com.dawoo.lotterybox.bean;


import java.util.List;

/**
 * Created by archar on 18-3-12.
 * 本地数据承接
 */

public class BBetParamForm {
    private String code;
    private String quantity;
    private String totalMoney;
    private String playModel="tradition";
    private String expect;
    public String getPlayModel() {
        return playModel;
    }

    public void setPlayModel(String playModel) {
        this.playModel = playModel;
    }

    private List<BetOrdersListBean> betOrders;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<BetOrdersListBean> getBetOrders() {
        return betOrders;
    }

    public void setBetOrders(List<BetOrdersListBean> betOrders) {
        this.betOrders = betOrders;
    }

    @Override
    public String toString() {
        return "BBetParamForm{" +
                ", code='" + code + '\'' +
                ", quantity='" + quantity + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", betOrders=" + betOrders +
                '}';
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }
}
