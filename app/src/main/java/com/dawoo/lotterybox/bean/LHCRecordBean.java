package com.dawoo.lotterybox.bean;

/**
 * Created by rain on 18-3-18.
 */

public class LHCRecordBean {
    private String code;
    private int leaveOut=1;
    private boolean isAnimall;

    public boolean isAnimall() {
        return isAnimall;
    }

    public void setAnimall(boolean animall) {
        isAnimall = animall;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLeaveOut() {
        return leaveOut;
    }

    public void setLeaveOut(int leaveOut) {
        this.leaveOut = leaveOut;
    }
}
