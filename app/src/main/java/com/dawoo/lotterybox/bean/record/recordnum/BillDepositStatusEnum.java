package com.dawoo.lotterybox.bean.record.recordnum;


import com.dawoo.lotterybox.bean.lottery.lotteryenum.ICodeEnum;

/**
 * 存款状态枚举
 *
 * @author marz
 * @time 2018-01-21 09:29:14
 */
public enum BillDepositStatusEnum implements ICodeEnum {
    PENDING("pending", "待处理"),
    SUCCESS("success", "成功"),
    FAIL("fail", "失败"),
    PENDING_PAY("pending_pay", "待支付"),
    OVER_TIME("over_time", "超时"),
    REJECT("reject", "拒绝");
    BillDepositStatusEnum(String code, String trans) {
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
