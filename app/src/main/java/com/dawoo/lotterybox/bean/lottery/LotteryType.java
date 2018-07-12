package com.dawoo.lotterybox.bean.lottery;

import java.util.List;

/**
 * 彩种类型
 * Created by benson on 18-2-8.
 */

public class LotteryType {
    /**
     * typeStatus : 1       彩种类型状态（1:启用；2:停用）
     * typeName : 六合彩     彩种类型名称
     * frequency : 2        彩种类型频率（1:高频彩；2:低频彩）
     * typeCode : lhc      彩种类型代号
     */

    private int typeStatus;
    private String typeName;
    private String frequency;
    private String typeCode;
    private List<BaseLottery> lotteries;

    public int getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(int typeStatus) {
        this.typeStatus = typeStatus;
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
