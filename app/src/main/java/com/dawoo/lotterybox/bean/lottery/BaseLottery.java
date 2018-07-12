package com.dawoo.lotterybox.bean.lottery;

/**
 * 彩种
 * Created by benson on 18-2-8.
 */

public class BaseLottery extends HandicapWithOpening {
    /**
     * code : ahk3    彩种代码
     * name : 安徽快3  彩种名称
     * status : 1     彩种状态（1:启用；2:停用）
     */

    protected String code;
    protected String name;
    protected String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
