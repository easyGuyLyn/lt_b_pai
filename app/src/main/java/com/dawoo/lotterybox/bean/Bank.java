package com.dawoo.lotterybox.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author jack
 * @date 18-2-19
 */

public class Bank implements Parcelable {


    /**
     * bankCode : icbc
     * bankShortName : 中国工商银行
     * type : 1
     */

    private String bankCode;
    private String bankShortName;
    private String type;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bankCode);
        dest.writeString(this.bankShortName);
        dest.writeString(this.type);
    }

    public Bank() {
    }

    protected Bank(Parcel in) {
        this.bankCode = in.readString();
        this.bankShortName = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Bank> CREATOR = new Parcelable.Creator<Bank>() {
        @Override
        public Bank createFromParcel(Parcel source) {
            return new Bank(source);
        }

        @Override
        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };
}
