package com.dawoo.lotterybox.bean.record.recordnum;

/**
 * 账单方式枚举
 *
 * @author marz
 * @time 2018-01-21 09:29:14
 */
public enum BillWayEnum implements IParentEnum {
    DEPOSIT("deposit", "存款"),
    WITHDRAW("withdraw", "取款"),
    GAME("game", "游戏"),
    FAVORABLE("favorable", "优惠");

    BillWayEnum(String code, String trans) {
        this.code = code;
        this.trans = trans;
    }

    private String code;
    private String trans;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return trans;
    }
}
