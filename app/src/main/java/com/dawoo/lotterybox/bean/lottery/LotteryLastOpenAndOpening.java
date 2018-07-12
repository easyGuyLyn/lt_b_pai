package com.dawoo.lotterybox.bean.lottery;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 上期开奖和正在开奖
 * Created by benson on 18-2-8.
 */

public class LotteryLastOpenAndOpening implements MultiItemEntity {


    /**
     * openingExpect : 20180213001
     * leftTime : 2058
     * leftOpenTime : -84821
     * type : ssc
     * lastCode : xjssc
     * lastExpect : 20171218068
     * lastOpenTime : 1513603220000
     * lastOpenCode : 8,3,4,2,6
     */

    private String openingExpect;
    private int leftTime;
    private int leftOpenTime;
    private String type;
    private String lastCode;
    private String lastExpect;
    private long lastOpenTime;
    private String lastOpenCode;
    private int itemType;

    public String getOpeningExpect() {
        return openingExpect;
    }

    public void setOpeningExpect(String openingExpect) {
        this.openingExpect = openingExpect;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public int getLeftOpenTime() {
        return leftOpenTime;
    }

    public void setLeftOpenTime(int leftOpenTime) {
        this.leftOpenTime = leftOpenTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

    public String getLastExpect() {
        return lastExpect;
    }

    public void setLastExpect(String lastExpect) {
        this.lastExpect = lastExpect;
    }

    public long getLastOpenTime() {
        return lastOpenTime;
    }

    public void setLastOpenTime(long lastOpenTime) {
        this.lastOpenTime = lastOpenTime;
    }

    public String getLastOpenCode() {
        return lastOpenCode;
    }

    public void setLastOpenCode(String lastOpenCode) {
        this.lastOpenCode = lastOpenCode;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
