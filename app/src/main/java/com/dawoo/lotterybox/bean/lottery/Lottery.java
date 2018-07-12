package com.dawoo.lotterybox.bean.lottery;

import com.google.gson.annotations.SerializedName;

/**
 * 彩种
 * Created by benson on 18-2-8.
 */

public class Lottery extends BaseLottery {

    /**
     * id : 941792
     * origin : null
     * date : null
     * orderNum : null
     */

    private int id;
    private Object origin;
    private Object date;
    private Object orderNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Object orderNum) {
        this.orderNum = orderNum;
    }

}
