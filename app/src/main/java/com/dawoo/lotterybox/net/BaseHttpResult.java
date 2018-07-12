package com.dawoo.lotterybox.net;

/**
 * Created by archar on 18-4-27.
 */

public class BaseHttpResult {
    protected int error;
    protected String code;
    protected String message;

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
}
