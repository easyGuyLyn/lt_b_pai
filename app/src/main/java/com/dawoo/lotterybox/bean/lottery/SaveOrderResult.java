package com.dawoo.lotterybox.bean.lottery;

/**
 * Created by benson on 18-3-13.
 */

public class SaveOrderResult {
    /**
     * error : 0
     * code : null
     * message : null
     * data : {"balance":5,"token":"93b471fc-4f99-4bea-99a7-946b7957d49b","billNo":"4004180015490067"}
     * extend : null
     */

    private int error;
    private String code;
    private String message;
    private DataBean data;
    private String extend;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public static class DataBean {
        /**
         * balance : 5
         * token : 93b471fc-4f99-4bea-99a7-946b7957d49b
         */

        private String billNo;
        private String balance;
        private String token;
        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
