package com.dawoo.lotterybox.bean;

/**
 * Created by fei on 2017/8/21.
 */
public enum SchemeEnum {
    INTENT("intent://", "通用协议"),
    QQ("mqqapi://", "QQ"),
    WECHAT("weixin://", "微信"),
    ALIPAY("alipays://", "支付宝");

    private String code;
    private String name;

    SchemeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

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

}
