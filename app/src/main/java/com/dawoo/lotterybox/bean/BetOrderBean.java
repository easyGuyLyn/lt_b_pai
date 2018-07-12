package com.dawoo.lotterybox.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by b on 18-3-4.
 */

public class BetOrderBean implements Parcelable {
    /**
     * playCode : ssc_yixing
     * betCode : ssc_yixing_dwd
     * betCount : 1
     * betAmount : 2.000
     * betNum : 6||||
     * odd : 19.6
     * multiple : 1
     * bonusModel : 1
     * rebate : 0.000
     */
    private String playCode;
    private String betCode;
    private int betCount;
    private String betAmount;
    private String betNum;
    private String odd;
    private String multiple;
    private String bonusModel;
    private double rebate;

    public BetOrderBean(){}

    public void setParam(BetParamBean betParamBean){
        playCode = betParamBean.getPlayCode();
        betCode = betParamBean.getBetCode();
        betCount = betParamBean.getBetCount();
        betAmount = betParamBean.getBetAmount();
        betNum = betParamBean.getBetNum();
        odd = betParamBean.getLotteryOddBeans().get(betParamBean.getMaxOddPosition()).getOdd() + "";
        multiple = betParamBean.getMultiple();
        bonusModel = betParamBean.getBonusModel();
        rebate = betParamBean.getNowRebate() == -1 ? betParamBean.getLotteryOddBeans().get(betParamBean.getMaxOddPosition()).getRebate() : betParamBean.getNowRebate();
    }

    protected BetOrderBean(Parcel in) {
        playCode = in.readString();
        betCode = in.readString();
        betCount = in.readInt();
        betAmount = in.readString();
        betNum = in.readString();
        odd = in.readString();
        multiple = in.readString();
        bonusModel = in.readString();
        rebate = in.readDouble();
    }

    public static final Creator<BetOrderBean> CREATOR = new Creator<BetOrderBean>() {
        @Override
        public BetOrderBean createFromParcel(Parcel in) {
            return new BetOrderBean(in);
        }

        @Override
        public BetOrderBean[] newArray(int size) {
            return new BetOrderBean[size];
        }
    };

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

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    public String getBetNum() {
        return betNum;
    }

    public void setBetNum(String betNum) {
        this.betNum = betNum;
    }

    public String getOdd() {
        return odd;
    }

    public void setOdd(String odd) {
        this.odd = odd;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getBonusModel() {
        return bonusModel;
    }

    public void setBonusModel(String bonusModel) {
        this.bonusModel = bonusModel;
    }

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    @Override
    public String toString() {
        return "{" +
                "playCode=" + playCode  +
                ", betCode=" + betCode  +
                ", betCount=" + betCount  +
                ", betAmount=" + betAmount  +
                ", betNum=" + betNum  +
                ", odd=" + odd  +
                ", multiple=" + multiple  +
                ", bonusModel=" + bonusModel  +
                ", rebate=" + rebate +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playCode);
        dest.writeString(betCode);
        dest.writeInt(betCount);
        dest.writeString(betAmount);
        dest.writeString(betNum);
        dest.writeString(odd);
        dest.writeString(multiple);
        dest.writeString(bonusModel);
        dest.writeDouble(rebate);
    }
}
