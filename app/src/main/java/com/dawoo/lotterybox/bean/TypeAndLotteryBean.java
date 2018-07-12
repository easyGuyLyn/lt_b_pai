package com.dawoo.lotterybox.bean;

import java.util.List;

/**
 * Created by b on 18-2-25.
 */

public class TypeAndLotteryBean {

    /**
     * typeCode : ssc
     * typeName : 时时彩
     * frequency : high
     * unfold : false
     * lotteries : [{"code":"cqssc","name":"重庆时时彩","model":"all"},{"code":"xjssc","name":"新疆时时彩","model":"all"},{"code":"ffssc","name":"分分时时彩","model":"all"}]
     */
    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局
    private int type;// 显示类型
    private boolean isExpand;// 是否展开
    private int icon;
    private String typeCode;
    private String typeName;
    private String frequency;
    private boolean unfold;
    private List<LotteriesBean> lotteries;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isUnfold() {
        return unfold;
    }

    public void setUnfold(boolean unfold) {
        this.unfold = unfold;
    }

    public List<LotteriesBean> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<LotteriesBean> lotteries) {
        this.lotteries = lotteries;
    }

    public static class LotteriesBean {
        /**
         * code : cqssc
         * name : 重庆时时彩
         * model : all
         */

        private String code;
        private String name;
        private String model;

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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}
