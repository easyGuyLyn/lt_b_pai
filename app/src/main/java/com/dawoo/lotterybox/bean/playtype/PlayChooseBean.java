package com.dawoo.lotterybox.bean.playtype;

/**
 * Created by archar on 18-2-27.
 */

public class PlayChooseBean {
    private String betName;
    private String berCode;
    private boolean selected;

    public String getBetName() {
        return betName;
    }

    public void setBetName(String betName) {
        this.betName = betName;
    }

    public String getBerCode() {
        return berCode;
    }

    public void setBerCode(String berCode) {
        this.berCode = berCode;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
