package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.dawoo.lotterybox.bean.BBetParamForm;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;
import com.dawoo.lotterybox.util.GsonUtil;
import com.google.gson.Gson;

import rx.Subscription;

/**
 * Created by b on 18-2-22.
 */

public class LotteryBPresenter<T extends IBaseView> extends BaseLotteryPresenter {

    private final Context mContext;
    private T mView;

    public LotteryBPresenter(Context context, T mView, String code) {
        super(context, mView);
        mContext = context;
        this.mView = mView;
        mCode = code;
    }

    /**
     * 下单
     */
    public void saveBetOrderB(String token,BBetParamForm bBetParamForm) {
        Log.e("saveBetOrderB_List", new Gson().toJson(bBetParamForm));


        Subscription subscription = mModel.saveBetOrder(new ProgressSubscriber<>(o ->
                        ((LotteryBView) mView).onSaveBetOrder((SaveOrderResult) o), mContext),
                token,
                GsonUtil.GsonString(bBetParamForm));
        subList.add(subscription);
    }


    @Override
    public void onDestory() {
        super.onDestory();
    }
}
