package com.dawoo.lotterybox.bean;

/**
 * Created by b on 18-2-26.
 */

public class WayPaperButtonBean {

    private String btName;
    private int ballNumber;  //第几球   -1为全部
    private int status;  //大小单双   0-大小   1-单双  2-龙虎
    private boolean select = false; //是否选中

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getBtName() {
        return btName;
    }

    public void setBtName(String btName) {
        this.btName = btName;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
