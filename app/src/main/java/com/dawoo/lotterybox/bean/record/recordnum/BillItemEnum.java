package com.dawoo.lotterybox.bean.record.recordnum;

/**
 * 账单类型枚举
 *
 * @author marz
 * @time 2018-01-21 09:29:14
 */
public enum BillItemEnum implements IChildEnum {

    // 存款
    //// 公司入款
    ONLINE_BANK_DEPOSIT(BillTypeEnum.COMPANY_INCOME,"online_bank_deposit", "网银存款"),
    WECHATPAY_FAST(BillTypeEnum.COMPANY_INCOME,"wechatpay_fast", "微信支付"),
    ALIPAY_FAST(BillTypeEnum.COMPANY_INCOME,"alipay_fast", "支付宝"),
    QQWALLET_FAST(BillTypeEnum.COMPANY_INCOME,"qqwallet_fast", "QQ钱包"),
    BDWALLET_FAST(BillTypeEnum.COMPANY_INCOME,"bdwallet_fast", "百度钱包"),
    JDWALLET_FAST(BillTypeEnum.COMPANY_INCOME,"jdwallet_fast", "京东钱包"),
    ONECODEPAY_FAST(BillTypeEnum.COMPANY_INCOME,"onecodepay_fast", "一码付"),
    OTHER_FAST(BillTypeEnum.COMPANY_INCOME,"other_fast", "其他支付"),

    //// 线上支付
    ONLINE_BANK_PAY(BillTypeEnum.ONLINE_PAY,"online_bank_pay", "网银支付"),
    WECHATPAY_SCAN(BillTypeEnum.ONLINE_PAY,"wechatpay_scan", "微信扫码"),
    ALIPAY_SCAN(BillTypeEnum.ONLINE_PAY,"alipay_scan", "支付宝扫码"),
    QQWALLET_SCAN(BillTypeEnum.ONLINE_PAY,"qqwallet_scan", "QQ钱包扫码"),
    BDWALLET_SCAN(BillTypeEnum.ONLINE_PAY,"bdwallet_scan", "百度钱包"),
    JDWALLET_SCAN(BillTypeEnum.ONLINE_PAY,"jdwallet_scan", "京东钱包"),
    UNIONPAY_SCAN(BillTypeEnum.ONLINE_PAY,"unionpay_scan", "银联扫码"),

    //// 人工存款
    SYSTEM_SAVE(BillTypeEnum.SYSTEM_DEPOSIT,"system_save", "系统存入"),
    REPLENISH_FAVORABLE(BillTypeEnum.SYSTEM_DEPOSIT,"replenish_favorable", "补发优惠"),
    REPLENISH_SALARY(BillTypeEnum.SYSTEM_DEPOSIT,"replenish_salary", "补发工资"),
    REPLENISH_DIVIDEN(BillTypeEnum.SYSTEM_DEPOSIT,"replenish_dividen", "补发分红"),
    REPLENISH_RAKEBACK(BillTypeEnum.SYSTEM_DEPOSIT,"replenish_rakeback", "补发返水"),
    OTHER_SAVE(BillTypeEnum.SYSTEM_DEPOSIT,"other_save", "其他存入"),

    //优惠
    //// 优惠活动
    REGIST_SEND(BillTypeEnum.FAVORABLE_ACTIVITY,"regist_send", "注册送"),
    FIRST_DEPOSIT(BillTypeEnum.FAVORABLE_ACTIVITY,"first_deposit", "首存送"),
    DEPOSIT_SEND(BillTypeEnum.FAVORABLE_ACTIVITY,"deposit_send", "存就送"),
    SIGNED(BillTypeEnum.FAVORABLE_ACTIVITY,"signed", "签到"),

    //// 系统优惠
    SYSTEM_FAVORABLE(BillTypeEnum.SYSTEM_FAVORABLE,"system_favorable", "系统优惠"),

    //// 返水
    RAKEBACK_SETTLE(BillTypeEnum.RAKEBACK,"rakeback_settle", "返水结算"),

    //// 日工资
    SALARY_SETTLE(BillTypeEnum.DAY_SALARY,"manual_favorable", "工资结算"),

    //// 分红
    DIVIDEN_SETTLE(BillTypeEnum.DIVIDEN,"dividen_settle", "分红结算"),

    // 取款
    //// 人工取款
    SYSTEM_DRAW(BillTypeEnum.SYSTEM_WITHDRAW,"system_draw", "系统取出"),
    FAVORABLE_DEDUCT(BillTypeEnum.SYSTEM_WITHDRAW,"favorable_deduct", "优惠扣除"),
    OTHER_DEDUCT(BillTypeEnum.SYSTEM_WITHDRAW,"other_deduct", "其他扣除"),
    SYSTEM_REFUND(BillTypeEnum.SYSTEM_WITHDRAW,"system_refund", "系统退款"),

    //// 玩家取款
    FIRST_DRAW_WITHHOLD(BillTypeEnum.PLAYER_WITHDRAW,"first_draw_withhold", "首取扣款"),
    DRAW_WITHHOLD(BillTypeEnum.PLAYER_WITHDRAW,"draw_withhold", "取款扣款"),
    DRAW_REFUND(BillTypeEnum.PLAYER_WITHDRAW,"draw_refund", "取款退款"),

    //游戏
    //// 游戏支出
    GAME_BET(BillTypeEnum.GAME_EXPEND,"game_bet", "游戏投注"),
    REVOCATION_WITHHOLD(BillTypeEnum.GAME_EXPEND,"revocation_withhold", "撤销扣款"),
    RECALCULATE_WITHHOLD(BillTypeEnum.GAME_EXPEND,"recalculate_withhold", "重结扣款"),

    //// 游戏收入
    BET_REBATE(BillTypeEnum.GAME_INCOME,"bet_rebate", "投注返点"),
    GAME_PAYOUT(BillTypeEnum.GAME_INCOME,"game_payout", "游戏派彩"),
    REVOKE_REFUND(BillTypeEnum.GAME_INCOME,"revoke_refund", "撤单退款"),
    REVOCATION_REFUND(BillTypeEnum.GAME_INCOME,"revocation_refund", "撤销退款"),
    RECALCULATE_PAYOUT(BillTypeEnum.GAME_INCOME,"recalculate_payout", "重结派彩")
    ;

    BillItemEnum(BillTypeEnum parent, String code, String trans) {
        this.code = code;
        this.trans = trans;
        this.parent = parent;
    }

    private String code;
    private String trans;
    private BillTypeEnum parent;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return trans;
    }

    @Override
    public BillTypeEnum getParent() {
        return parent;
    }

}
