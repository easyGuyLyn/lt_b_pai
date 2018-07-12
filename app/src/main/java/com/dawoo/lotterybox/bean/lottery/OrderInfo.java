package com.dawoo.lotterybox.bean.lottery;

import java.util.List;

/**
 * Created by b on 18-4-18.
 */

public class OrderInfo {

    /**
     * type : keno
     * orderList : [{"betCode":"keno_selection","expect":"874273","betAmount":5,"playCode":"keno_selection_one","code":"bjkl8","betNum":"62","betTime":1520338432443,"multiple":1,"id":871,"billNo":"4003060002370026","status":"pending"}]
     */

    private String type;
    private List<OrderListBean> orderList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * betCode : keno_selection
         * expect : 874273
         * betAmount : 5
         * playCode : keno_selection_one
         * code : bjkl8
         * betNum : 62
         * betTime : 1520338432443
         * multiple : 1
         * id : 871
         * billNo : 4003060002370026
         * status : pending
         */

        private String betCode;
        private String expect;
        private double betAmount;
        private String playCode;
        private String code;
        private String betNum;
        private long betTime;
        private int multiple;
        private String id;
        private String billNo;
        private String status;

        public String getBetCode() {
            return betCode;
        }

        public void setBetCode(String betCode) {
            this.betCode = betCode;
        }

        public String getExpect() {
            return expect;
        }

        public void setExpect(String expect) {
            this.expect = expect;
        }

        public double getBetAmount() {
            return betAmount;
        }

        public void setBetAmount(double betAmount) {
            this.betAmount = betAmount;
        }

        public String getPlayCode() {
            return playCode;
        }

        public void setPlayCode(String playCode) {
            this.playCode = playCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getBetNum() {
            return betNum;
        }

        public void setBetNum(String betNum) {
            this.betNum = betNum;
        }

        public long getBetTime() {
            return betTime;
        }

        public void setBetTime(long betTime) {
            this.betTime = betTime;
        }

        public int getMultiple() {
            return multiple;
        }

        public void setMultiple(int multiple) {
            this.multiple = multiple;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
