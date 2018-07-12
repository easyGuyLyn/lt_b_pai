package com.dawoo.lotterybox.bean.lottery.lotteryenum;


public enum LotteryEnum implements ICodeEnum {

    HBK3("k3","hbk3","湖北快3",40),
    GXK3("k3","gxk3","广西快3",41),
    JSK3("k3","jsk3", "江苏快3",42),
    AHK3("k3","ahk3","安徽快3",43),

    HKLHC("lhc","hklhc", "香港六合彩",30),

    CQSSC("ssc","cqssc", "重庆时时彩",10),
    XJSSC("ssc","xjssc", "新疆时时彩",11),
    FFSSC("ssc","ffssc", "分分时时彩",12),
    TJSSC("ssc","tjssc", "天津时时彩",13),
    EFSSC("ssc","efssc", "二分时时彩",14),
    SFSSC("ssc","sfssc", "三分时时彩",15),
    WFSSC("ssc","wfssc", "五分时时彩",16),

    BJPK10("pk10","bjpk10", "北京PK10",20),
    XYFT("pk10","xyft", "幸运飞艇",21),
    JSPK10("pk10","jspk10", "极速PK10",22),

    CQXYNC("sfc","cqxync", "重庆幸运农场",50),
    GDKL10("sfc","gdkl10", "广东快乐十分",51),

    BJKL8("keno","bjkl8", "北京快乐8",60),
    XY28("xy28","xy28", "幸运28",80),

    FC3D("pl3","fc3d", "福彩3D",70),
    TCPL3("pl3","tcpl3", "体彩排列3",71);


    private String type;
    private String code;
    private String trans;
    private int rcdFlag;

    LotteryEnum(String type,String code, String trans,int rcdFlag) {
        this.type = type;
        this.code = code;
        this.trans = trans;
        this.rcdFlag = rcdFlag;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRcdFlag() {
        return rcdFlag;
    }

    public void setRcdFlag(int rcdFlag) {
        this.rcdFlag = rcdFlag;
    }
}
