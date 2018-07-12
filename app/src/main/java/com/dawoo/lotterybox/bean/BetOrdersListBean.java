package com.dawoo.lotterybox.bean;

import java.io.Serializable;

/**
 * Created by archar on 18-3-12.
 */

public class BetOrdersListBean implements Serializable {

    /**
     * expect : 671351
     * code : bjpk10
     * betCode : runner_up
     * playCode : pk10_digital
     * betNum : 01
     * odd : 9.85
     * betAmount : 123123
     * memo :
     */

    private String expect;
    private String code;
    private String betCode;
    private String playCode;
    private String betNum;
    private String odd;
    private String betAmount;
    private String memo;

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBetCode() {
        return betCode;
    }

    public void setBetCode(String betCode) {
        this.betCode = betCode;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getBetNum() {
        return betNum;
    }

    public void setBetNum(String betNum) {
        this.betNum = betNum;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
