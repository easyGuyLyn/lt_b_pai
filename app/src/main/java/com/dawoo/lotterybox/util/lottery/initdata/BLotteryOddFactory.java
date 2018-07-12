package com.dawoo.lotterybox.util.lottery.initdata;

import android.util.Log;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by archar on 18-3-14.
 * B盘 赔率获得  工厂类
 */

public class BLotteryOddFactory {

    private Map<String, LotteryOddBean> mStringLotteryOddBeanMap = new HashMap<>();

    private List<Handicap> mHistorys = new ArrayList<>();
    private static BLotteryOddFactory mBLotteryOddFactory;

    public static synchronized BLotteryOddFactory getInstance() {
        if (mBLotteryOddFactory == null) {
            mBLotteryOddFactory = new BLotteryOddFactory();
        }
        return mBLotteryOddFactory;
    }


    public Map<String, LotteryOddBean> getStringLotteryOddBeanMap() {
        return mStringLotteryOddBeanMap;
    }

    public void setStringLotteryOddBeanMap(Map<String, LotteryOddBean> stringLotteryOddBeanMap) {
        Log.e("BLotteryOddFactory json", GsonUtil.GsonString(stringLotteryOddBeanMap));
        mStringLotteryOddBeanMap = stringLotteryOddBeanMap;
    }

    //清楚内存中旧的赔率数据
    public void clearOddMap(){
        mStringLotteryOddBeanMap.clear();
    }

    public List<Handicap> getmHistorys() {
        return mHistorys;
    }

    public void setmHistorys(List<Handicap> mHistorys) {
        this.mHistorys = mHistorys;
    }
}
