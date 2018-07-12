package com.dawoo.lotterybox.bean.Deposit;


import com.dawoo.lotterybox.R;

/**
 * Created by tom on 15-9-6.
 */
public enum PayAccountAccountType implements IPayEnum {
    //公司入款类型
    BANKACCOUNT("1", "银行账户", R.mipmap.icon_company),
    THIRTY("2", "第三方账户", R.mipmap.icon_other),
    //线上支付类型渠道
    WECHAT("3", "微信支付", R.mipmap.icon_wechat),
    ALIPAY("4", "支付宝", R.mipmap.icon_ali),
    QQWALLET("5","QQ钱包", R.mipmap.icon_qq),
    DIGICCY("6","数字货币", R.mipmap.icon_bit),
    JD_PAY("7","京东钱包", R.mipmap.icon_jd),
    BAIFU_PAY("8","百度钱包",R.mipmap.icon_baidu),
    UNION_PAY("9","银联扫码",R.mipmap.icon_union),
    ONLINE_BANK_PAY("10","网银支付",R.mipmap.icon_online);

    PayAccountAccountType(String code, String trans,int drawable) {
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
    public int getDrawable(){return drawable;}
}
