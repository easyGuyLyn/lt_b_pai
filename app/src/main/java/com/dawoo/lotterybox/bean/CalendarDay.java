package com.dawoo.lotterybox.bean;

/**
 * Created by archar on 18-2-14.
 */

public class CalendarDay {

    private boolean selected;
    private String dayNum;
    private String data1;//备注
    private long time;
    private boolean isArea;//在近31天内

    public boolean isArea() {
        return isArea;
    }

    public void setArea(boolean area) {
        isArea = area;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }
}
