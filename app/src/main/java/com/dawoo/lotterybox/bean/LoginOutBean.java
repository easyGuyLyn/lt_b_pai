package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-2.
 */

public class LoginOutBean {

    /**
     * error : 0
     * code : null
     * message : null
     * data : true
     * extend : null
     */

    private int error;
    private Object code;
    private Object message;
    private boolean data;
    private Object extend;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
