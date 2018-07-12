package com.dawoo.lotterybox.bean.lottery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by b on 18-2-28.
 */

public class LotteryOddBean implements Parcelable{
    /**
     * id : 1207539
     * code : cqssc
     * model : tradition
     * playCode : dragon_tiger_tie
     * betCode : ten_thousand_one
     * betNum : 龙
     * odd : 1.97
     * oddLimit : 1.999
     * rebate : null
     * baseNum : null
     */

    private int id;
    private String code;
    private String model;
    private String playCode;
    private String betCode;
    private String betNum;
    private double odd;
    private double oddLimit;
    private double rebate;
    private double baseNum;

    public LotteryOddBean(){}

    protected LotteryOddBean(Parcel in) {
        id = in.readInt();
        code = in.readString();
        model = in.readString();
        playCode = in.readString();
        betCode = in.readString();
        betNum = in.readString();
        odd = in.readDouble();
        oddLimit = in.readDouble();
        rebate = in.readDouble();
        baseNum = in.readDouble();
    }

    public static final Creator<LotteryOddBean> CREATOR = new Creator<LotteryOddBean>() {
        @Override
        public LotteryOddBean createFromParcel(Parcel in) {
            return new LotteryOddBean(in);
        }

        @Override
        public LotteryOddBean[] newArray(int size) {
            return new LotteryOddBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getBetNum() {
        return betNum;
    }

    public void setBetNum(String betNum) {
        this.betNum = betNum;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public double getOddLimit() {
        return oddLimit;
    }

    public void setOddLimit(double oddLimit) {
        this.oddLimit = oddLimit;
    }

    public double getRebate() {
        return rebate;
    }

    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    public double getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(double baseNum) {
        this.baseNum = baseNum;
    }

    @Override
    public String toString() {
        return "LotteryOddBean{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", model='" + model + '\'' +
                ", playCode='" + playCode + '\'' +
                ", betCode='" + betCode + '\'' +
                ", betNum='" + betNum + '\'' +
                ", odd=" + odd +
                ", oddLimit=" + oddLimit +
                ", rebate=" + rebate +
                ", baseNum=" + baseNum +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeString(model);
        dest.writeString(playCode);
        dest.writeString(betCode);
        dest.writeString(betNum);
        dest.writeDouble(odd);
        dest.writeDouble(oddLimit);
        dest.writeDouble(rebate);
        dest.writeDouble(baseNum);
    }
}
