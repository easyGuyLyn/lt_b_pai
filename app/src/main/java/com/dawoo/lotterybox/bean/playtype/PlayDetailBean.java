package com.dawoo.lotterybox.bean.playtype;


import java.io.Serializable;

/**
 * Created by b on 18-2-19.
 * 官方玩法
 */

public class PlayDetailBean implements Serializable {

    private String type;//代表的玩法
    private String childType;//2级玩法
    private String kind;//代表所属的 位数
    private String num;//球或数字
    private String odd = "";//赔率
    private String lr;//冷热
    private String yl;//遗漏
    private boolean selected;
    private boolean askview;

    //服务器交互参数
    private String code;//彩种
    private String playCode;//玩法
    private String betCode;// 位数，二级选项

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getBetCode() {
        return betCode;
    }

    public void setBetCode(String betCode) {
        this.betCode = betCode;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getLr() {
        return lr;
    }

    public void setLr(String lr) {
        this.lr = lr;
    }

    public boolean isAskview() {
        return askview;
    }

    public void setAskview(boolean askview) {
        this.askview = askview;
    }
}
