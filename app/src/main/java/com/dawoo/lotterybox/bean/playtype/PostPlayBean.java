package com.dawoo.lotterybox.bean.playtype;


import java.io.Serializable;

/**
 * Created by b on 18-2-19.
 * 官方玩法
 */

public class PostPlayBean implements Serializable {

    private String type;//代表的玩法
    private String kind;//代表所属的 位数
    private String odd;//赔率
    private String money;//每一注投的钱


    //服务器交互参数
    private String code;//彩种
    private String playCode;//玩法
    private String betCode;// 位数，二级选项
    private String postNum;//号码


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

    public String getPostNum() {
        return postNum;
    }

    public void setPostNum(String postNum) {
        this.postNum = postNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
