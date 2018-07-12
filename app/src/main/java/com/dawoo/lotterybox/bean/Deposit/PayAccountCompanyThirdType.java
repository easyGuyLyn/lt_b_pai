package com.dawoo.lotterybox.bean.Deposit;


import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.ICodeEnum;

/**
 * Created by tom on 15-9-6.
 */
public enum PayAccountCompanyThirdType implements IPayEnum {
    //公司入款第三方支付类型
    WECHAT("wechatpay", "微信支付", R.mipmap.icon_wechat),
    ALIPAY("alipay", "支付宝", R.mipmap.icon_ali),
    ONE_CODE_PAY("onecodepay", "一码付", R.mipmap.icon_onecode),
    UNIOP_PAY("unionpay", "qq钱包", R.mipmap.icon_qq),
    BD_WWALLET("bdwallet", "百度钱包", R.mipmap.icon_baidu),
    JD_WALLET("jdwallet", "京东钱包", R.mipmap.icon_jd);
    PayAccountCompanyThirdType(String code, String trans, int drawable) {
        this.code = code;
        this.trans = trans;
        this.drawable = drawable;
    }

    private String code;
    private String trans;
    private int drawable;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTrans() {
        return trans;
    }

    @Override
    public int getDrawable() {
        return drawable;
    }
}
