package com.dawoo.lotterybox.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-2-28.
 */

public class BetParamBean implements Parcelable {
//    lb.token	是		用于防着重复下注；值为防重复下注接口数据（客户端也需处理防重复点击）
//    code	是	String	彩种代号
//    quantity	是		投注单数
//    playModel	是		official-官方；tradition-传统
//    expect	是		期号
//    betOrders.playCode	是		彩种玩法代号
//    betOrders.betCode	是		投注玩法代号
//    betOrders.betNum	是		下注号码(具体格式请参考龙头彩票)
//    betOrders.odd	是		赔率|奖金
//    betOrders.betAmount	是		下注金额
//    betOrders.multiple	是		官方-倍数
//    betOrders.bonusModel	是		官方-元角分模式（1-元；10-角;100-分）
//    betOrders.rebate	是		官方-返点
//    betOrders.betCount	是		官方-注数

    private String playTypeName;
    private String token;
    private String code;
    private String quantity = "1";
    private String playModel;
    private String expect;
    private String playCode;
    private String betCode;
    private String betNum;
    private String betAmount;
    private String multiple = "1";
    private String bonusModel = "1";
    private double nowRebate = 0;   //目前用户调整的返点比例
    private int betCount;
    private List<LotteryOddBean> mLotteryOddBeans; //当前玩法赔率集合
    private int maxOddPosition;  //当前玩法赔率集合里最大Odd所在角标
    private int model = 2;  //模式
    private boolean betHint = false; //下单提示

    public BetParamBean(){}

    public BetParamBean(Parcel in) {
        playTypeName = in.readString();
        token = in.readString();
        code = in.readString();
        quantity = in.readString();
        playModel = in.readString();
        expect = in.readString();
        playCode = in.readString();
        betCode = in.readString();
        betNum = in.readString();
        betAmount = in.readString();
        multiple = in.readString();
        bonusModel = in.readString();
        nowRebate = in.readDouble();
        betCount = in.readInt();
        maxOddPosition = in.readInt();
        model = in.readInt();
        betHint = in.readByte() != 0;
        this.mLotteryOddBeans = new ArrayList<LotteryOddBean>();
        in.readList(this.mLotteryOddBeans, LotteryOddBean.class.getClassLoader());
    }

    public static final Creator<BetParamBean> CREATOR = new Creator<BetParamBean>() {
        @Override
        public BetParamBean createFromParcel(Parcel in) {
            return new BetParamBean(in);
        }

        @Override
        public BetParamBean[] newArray(int size) {
            return new BetParamBean[size];
        }
    };

    public int getMaxOddPosition() {
        return maxOddPosition;
    }

    public void setMaxOddPosition(int maxOddPosition) {
        this.maxOddPosition = maxOddPosition;
    }


    public List<LotteryOddBean> getLotteryOddBeans() {
        return mLotteryOddBeans;
    }

    public void setLotteryOddBeans(List<LotteryOddBean> lotteryOddBeans) {
        mLotteryOddBeans = lotteryOddBeans;
    }

    public double getNowRebate() {
        return nowRebate;
    }

    public void setNowRebate(double nowRebate) {
        this.nowRebate = nowRebate;
    }

    public boolean isBetHint() {
        return betHint;
    }

    public void setBetHint(boolean betHint) {
        this.betHint = betHint;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPlayModel() {
        return playModel;
    }

    public void setPlayModel(String playModel) {
        this.playModel = playModel;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
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

    public String getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
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

    public String getPlayTypeName() {
        return playTypeName;
    }

    public void setPlayTypeName(String playTypeName) {
        this.playTypeName = playTypeName;
    }

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playTypeName);
        dest.writeString(token);
        dest.writeString(code);
        dest.writeString(quantity);
        dest.writeString(playModel);
        dest.writeString(expect);
        dest.writeString(playCode);
        dest.writeString(betCode);
        dest.writeString(betNum);
        dest.writeString(betAmount);
        dest.writeString(multiple);
        dest.writeString(bonusModel);
        dest.writeDouble(nowRebate);
        dest.writeInt(betCount);
        dest.writeInt(maxOddPosition);
        dest.writeInt(model);
        dest.writeByte((byte) (betHint ? 1 : 0));
        dest.writeList(mLotteryOddBeans);
    }

    public void setBetParamBean(BetParamBean betParamBean){
        playTypeName = betParamBean.getPlayTypeName();
        token = betParamBean.getToken();
        code = betParamBean.getCode();
        quantity = betParamBean.getQuantity();
        playModel = betParamBean.getPlayModel();
        expect = betParamBean.getExpect();
        playCode = betParamBean.getPlayCode();
        betCode = betParamBean.getBetCode();
        betNum = betParamBean.getBetNum();
        betAmount = betParamBean.getBetAmount();
        multiple = betParamBean.getMultiple();
        bonusModel = betParamBean.getBonusModel();
        nowRebate = betParamBean.nowRebate;
        betCount = betParamBean.getBetCount();
        maxOddPosition = betParamBean.maxOddPosition;
        model = betParamBean.getModel();
        betHint = betParamBean.betHint;
        mLotteryOddBeans = betParamBean.getLotteryOddBeans();
    }
}
