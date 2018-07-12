package com.dawoo.lotterybox.bean.Deposit;


import com.dawoo.lotterybox.R;

/**
 * Created by rain on 18-5-1.
 */

public enum BankType implements IPayEnum {

    FJNX("fjnx", "福建农商银行", R.mipmap.icon_company),
    SZFZ("sbd", "深圳发展银行", R.mipmap.icon_company),
    HF("hsbc", "汇丰银行", R.mipmap.icon_company),
    HS("hangseng", "恒生银行", R.mipmap.icon_company),
    ZGNY("abc", "中国农业银行", R.mipmap.icon_company),
    ZGJS("ccb", "中国建设银行", R.mipmap.icon_company),
    ZG("boc", "中国银行", R.mipmap.icon_company),
    ZGMS("cmbc", "中国民生银行", R.mipmap.icon_company),
    ZGGD("ceb", "中国广大银行", R.mipmap.icon_company),
    JT("comm", "交通银行", R.mipmap.icon_company),
    SHPD("spdb", "上海浦东发展银行", R.mipmap.icon_company),
    PA("spabank", "平安银行", R.mipmap.icon_company),
    SY("cib", "兴业银行", R.mipmap.icon_company),
    OTHER("other_bank", "其他银行", R.mipmap.icon_company),
    BJ("bjbank", "北京银行", R.mipmap.icon_company),
    ZX("citic", "中信银行", R.mipmap.icon_company),
    GDFZ("gbd", "广东发展银行", R.mipmap.icon_company),
    ZMGS("icbc", "中国工商银行", R.mipmap.icon_company),
    TZ("tzb", "台州银行", R.mipmap.icon_company),
    ZGYZ("psbc", "中国邮政银行", R.mipmap.icon_company),
    JS("jsbank", "江苏银行", R.mipmap.icon_company),
    HX("hxbank", "华夏银行", R.mipmap.icon_company),
    SH("shbank", "上海银行", R.mipmap.icon_company),
    BH("bohaib", "渤海银行", R.mipmap.icon_company),
    DY("hkbea", "东亚银行", R.mipmap.icon_company),
    NB("nbbank", "宁波银行", R.mipmap.icon_company),
    ZS("czbank", "浙商银行", R.mipmap.icon_company),
    HZ("hzcb", "杭州银行", R.mipmap.icon_company),
    GZ("gcb", "广州银行", R.mipmap.icon_company),
    ZSBANK("cmb", "招商银行", R.mipmap.icon_company);


    BankType(String code, String trans, int drawable) {
        this.code = code;
        this.trans = trans;
        this.drawable = drawable;
    }

    String code;
    String trans;
    int drawable;

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
