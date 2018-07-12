package com.dawoo.lotterybox.bean.record;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by archar on 18-2-9.
 * 玩家追号报表
 */

public class ChaseNumRecordHis implements Parcelable {

    /**
     * betCode : thousand
     * betCount : 1
     * code : cqssc
     * rebate : 0
     * multiple : 1
     * payout : 19.7
     * odd : 1.97
     * expect : 20171228060
     * payoutTime : 1514448656864
     * betAmount : 10
     * playCode : one_single_double
     * betNum : 单
     * effectiveTradeAmount : 10
     * betTime : 1514447867874
     * id : 570
     * status : 2
     */

    private String betCode;//投注玩法代号
    private int betCount;//投注数
    private String code;//彩票代号
    private int rebate;//返点比例
    private int multiple;//倍数
    private double payout;//派彩金额（中奖金额）
    private double odd;//赔率或奖金
    private String expect;//期号
    private long payoutTime;//派彩时间
    private int betAmount;//下注金额（投注金额）
    private String playCode;//彩种玩法代号
    private String betNum;//投注号码（多个号码时用逗号隔开）
    private int effectiveTradeAmount;//有效投注额
    private long betTime;//下注时间
    private int id;
    private String status;//注单状态（pending：未开奖,wining：已中奖,nowin：未中奖,revoke_sys：系统撤单,revoke_self：主动撤单,revocation：系统撤销）
    private double profit = 0.00;//盈亏


    private double rebateAmount;//返点金额
    private String bonusModel;//模式
    private double odd2;//奖金
    private double odd3;//奖金
    private int terminal;//终端标识：0-全部；1-pc；2-移动
    private String openCode;//开奖号码

    public double getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(double rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getBonusModel() {
        return bonusModel;
    }

    public void setBonusModel(String bonusModel) {
        this.bonusModel = bonusModel;
    }

    public double getOdd2() {
        return odd2;
    }

    public void setOdd2(double odd2) {
        this.odd2 = odd2;
    }

    public double getOdd3() {
        return odd3;
    }

    public void setOdd3(double odd3) {
        this.odd3 = odd3;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }


    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public long getPayoutTime() {
        return payoutTime;
    }

    public void setPayoutTime(long payoutTime) {
        this.payoutTime = payoutTime;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getBetNum() {
        return betNum;
    }

    public void setBetNum(String betNum) {
        this.betNum = betNum;
    }

    public int getEffectiveTradeAmount() {
        return effectiveTradeAmount;
    }

    public void setEffectiveTradeAmount(int effectiveTradeAmount) {
        this.effectiveTradeAmount = effectiveTradeAmount;
    }

    public long getBetTime() {
        return betTime;
    }

    public void setBetTime(long betTime) {
        this.betTime = betTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.betCode);
        dest.writeInt(this.betCount);
        dest.writeString(this.code);
        dest.writeInt(this.rebate);
        dest.writeInt(this.multiple);
        dest.writeDouble(this.payout);
        dest.writeDouble(this.odd);
        dest.writeString(this.expect);
        dest.writeLong(this.payoutTime);
        dest.writeInt(this.betAmount);
        dest.writeString(this.playCode);
        dest.writeString(this.betNum);
        dest.writeInt(this.effectiveTradeAmount);
        dest.writeLong(this.betTime);
        dest.writeInt(this.id);
        dest.writeString(this.status);
        dest.writeDouble(this.profit);
        dest.writeDouble(this.rebateAmount);
        dest.writeString(this.bonusModel);
        dest.writeDouble(this.odd2);
        dest.writeDouble(this.odd3);
        dest.writeInt(this.terminal);
        dest.writeString(this.openCode);
    }

    public ChaseNumRecordHis() {
    }

    protected ChaseNumRecordHis(Parcel in) {
        this.betCode = in.readString();
        this.betCount = in.readInt();
        this.code = in.readString();
        this.rebate = in.readInt();
        this.multiple = in.readInt();
        this.payout = in.readDouble();
        this.odd = in.readDouble();
        this.expect = in.readString();
        this.payoutTime = in.readLong();
        this.betAmount = in.readInt();
        this.playCode = in.readString();
        this.betNum = in.readString();
        this.effectiveTradeAmount = in.readInt();
        this.betTime = in.readLong();
        this.id = in.readInt();
        this.status = in.readString();
        this.profit = in.readDouble();
        this.rebateAmount = in.readDouble();
        this.bonusModel = in.readString();
        this.odd2 = in.readDouble();
        this.odd3 = in.readDouble();
        this.terminal = in.readInt();
        this.openCode = in.readString();
    }

    public static final Creator<ChaseNumRecordHis> CREATOR = new Creator<ChaseNumRecordHis>() {
        @Override
        public ChaseNumRecordHis createFromParcel(Parcel source) {
            return new ChaseNumRecordHis(source);
        }

        @Override
        public ChaseNumRecordHis[] newArray(int size) {
            return new ChaseNumRecordHis[size];
        }
    };
}
