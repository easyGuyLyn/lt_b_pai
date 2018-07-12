package com.dawoo.lotterybox.bean;

/**
 * Created by jack on 18-2-11.
 */

public class InfoMation {

    /**
     * error : 0
     * code : null
     * message : null
     * data : null
     * extend : null
     */

    private int error;
    private Object code;
    private Object message;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
