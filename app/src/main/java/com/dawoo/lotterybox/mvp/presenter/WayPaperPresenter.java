package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.WayPaperButtonBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.mvp.model.Lottery.ILotteryModel;
import com.dawoo.lotterybox.mvp.model.Lottery.LotteryModel;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IWayPaperView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * 路纸图
 * Created by b on 18-3-18.
 */

public class WayPaperPresenter<T extends IBaseView> extends BasePresenter {
    protected static final int PAGE_NUMBER = ConstantValue.LOTTERY_LIST_PAGE_NUMBER;
    protected static final int PAGE_SIZE = ConstantValue.LOTTERY_LIST_PAGE_SIZE;

    protected Context mContext;
    protected T mView;
    protected final ILotteryModel mModel;
    protected String mCode;

    public WayPaperPresenter(Context context, T mView, String code) {
        super(context, mView);
        mContext = context;
        this.mView = mView;
        mCode = code;
        mModel = new LotteryModel();
    }

    /**
     * 获取近120期开奖数据
     */

    public void getResultByCode() {
        Subscription subscription = mModel.getResultByCode(new ProgressSubscriber(o ->
                        ((IWayPaperView) mView).onResultByCode((List<Handicap>) o), mContext),
                mCode,
                String.valueOf(PAGE_SIZE),
                String.valueOf(PAGE_NUMBER));
        subList.add(subscription);
    }

    /**
     * 获取盘口数据
     */
    public void getLotteryExpect() {
        Subscription subscription = mModel.getLotteryExpect(new ProgressSubscriber<>(o ->
                        ((IWayPaperView) mView).onLotteryExpect((BaseHandicap) o), mContext),
                mCode);
        subList.add(subscription);
    }


    public int dragon;
    public int tiger;
    public int he;
    public int big;
    public int small;
    public int single;
    public int double_;

    /**
     * 数据整理     *
     */
    public List<List<String>> classification(List<Handicap> mHandicaps, WayPaperButtonBean buttonBean) {
        dragon = 0;
        tiger = 0;
        he = 0;
        big = 0;
        small = 0;
        single = 0;
        double_ = 0;
        List<List<String>> status = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        String lastStr = "";
        for (int i = 0; i < mHandicaps.size(); i++) {
            String nowStr = "";
            String betNum = mHandicaps.get(i).getOpenCode();
            String[] strs = betNum.split(",");
            if (buttonBean.getBallNumber() == -1) {
                if (buttonBean.getStatus() == 2) {   //龙虎和
                    if (Integer.valueOf(strs[0]) > Integer.valueOf(strs[4])) {
                        nowStr = mContext.getString(R.string.dragon);
                        dragon += 1;
                    } else if (Integer.valueOf(strs[0]) < Integer.valueOf(strs[4])) {
                        nowStr = mContext.getString(R.string.tiger);
                        tiger += 1;
                    } else {
                        nowStr = mContext.getString(R.string.he);
                        he += 1;
                    }
                } else {
                    int allNum = Integer.valueOf(strs[0]) + Integer.valueOf(strs[1]) + Integer.valueOf(strs[2]) + Integer.valueOf(strs[3]) + Integer.valueOf(strs[4]);
                    nowStr = getStatus(allNum, buttonBean);
                }
            } else
                nowStr = getStatus(Integer.valueOf(strs[buttonBean.getBallNumber()]), buttonBean);
            if (i == 0) {
                strList.add(nowStr);
            } else {
                if (lastStr.equals(nowStr)) {
                    strList.add(nowStr);
                } else {
                    status.add(strList);
                    strList = new ArrayList<>();
                    strList.add(nowStr);
                    if (i == (mHandicaps.size() - 1)) {
                        status.add(strList);
                    }
                }
            }
            lastStr = nowStr;
        }
        return status;
    }

    private String getStatus(int num, WayPaperButtonBean buttonBean) {
        String str = "";

        if (buttonBean.getStatus() == 0) { //大小
            if (buttonBean.getBallNumber() == -1) {
                if (num < 23) {
                    str = mContext.getString(R.string.small);
                    small += 1;
                } else {
                    str = mContext.getString(R.string.big);
                    big += 1;
                }
            } else {
                if (num < 5) {
                    str = mContext.getString(R.string.small);
                    small += 1;
                } else {
                    str = mContext.getString(R.string.big);
                    big += 1;
                }
            }

        } else if (buttonBean.getStatus() == 1) { //单双
            if ((num % 2) == 0) {
                str = mContext.getString(R.string.double_);
                double_ += 1;
            } else {
                str = mContext.getString(R.string.single);
                single += 1;
            }
        }
        return str;
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }
}
