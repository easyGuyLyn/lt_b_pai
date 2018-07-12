package com.dawoo.lotterybox.bean.playtype;

/**
 * Created by rain on 18-3-7.
 */

public class ParentPlayTypeBean {
    String name;
    String code;
    boolean isChoose;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
