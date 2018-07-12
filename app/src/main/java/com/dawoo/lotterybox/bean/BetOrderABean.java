package com.dawoo.lotterybox.bean;

import java.util.List;

/**
 * Created by b on 18-3-13.
 */

public class BetOrderABean {

    /**
     * code : cqssc
     * expect : 20180223057
     * totalMoney : 2.000
     * quantity : 1
     * playModel : official
     * betOrders : [{"playCode":"ssc_yixing","betCode":"ssc_yixing_dwd","betCount":"1","betAmount":"2.000","betNum":"6||||","odd":"19.6","multiple":"1","bonusModel":"1","rebate":"0.000"}]
     */

    private String code;
    private String expect;
    private String totalMoney;
    private String quantity;
    private String playModel;
    private List<BetOrderBean> betOrders;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPlayModel() {
        return playModel;
    }

    public void setPlayModel(String playModel) {
        this.playModel = playModel;
    }

    public List<BetOrderBean> getBetOrders() {
        return betOrders;
    }

    public void setBetOrders(List<BetOrderBean> betOrders) {
        this.betOrders = betOrders;
    }

}
