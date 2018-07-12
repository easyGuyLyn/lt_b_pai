package com.dawoo.lotterybox.bean.record.recordnum;

/**
 * 账单类型枚举
 *
 * @author marz
 * @time 2018-01-21 09:29:14
 */
public enum BillTypeEnum implements IParentEnum,IChildEnum {

    //存款
    COMPANY_INCOME(BillWayEnum.DEPOSIT,"company_income","11", "公司入款"),
    ONLINE_PAY(BillWayEnum.DEPOSIT,"online_pay","12", "线上支付"),
    SYSTEM_DEPOSIT(BillWayEnum.DEPOSIT,"system_deposit","13", "系统存款"),

    //优惠
    FAVORABLE_ACTIVITY(BillWayEnum.FAVORABLE,"favorable_activity","20", "优惠活动"),
    SYSTEM_FAVORABLE(BillWayEnum.FAVORABLE,"system_favorable","21" , "系统优惠"),
    RAKEBACK(BillWayEnum.FAVORABLE,"rakeback","22" , "返水"),
    DAY_SALARY(BillWayEnum.FAVORABLE,"day_salary","23", "日工资"),
    DIVIDEN(BillWayEnum.FAVORABLE,"dividen","24", "分红"),

    //取款
    SYSTEM_WITHDRAW(BillWayEnum.WITHDRAW,"system_withdraw","30", "系统取款"),
    PLAYER_WITHDRAW(BillWayEnum.WITHDRAW,"player_withdraw","31", "玩家取款"),

    //游戏
    GAME_EXPEND(BillWayEnum.GAME,"game_expend","40", "游戏支出"),
    GAME_INCOME(BillWayEnum.GAME,"game_income","41", "游戏收入")
    ;

    BillTypeEnum(BillWayEnum parent,String code,String no, String trans) {
        this.code = code;
        this.no = no;
        this.trans = trans;
        this.parent = parent;
    }

    private String code;
    private String no;
    private String trans;
    private BillWayEnum parent;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return trans;
    }

    public String getNo() {
        return no;
    }

    @Override
    public BillWayEnum getParent() {
        return parent;
    }

}
