package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.model.setting.ISettingModel;
import com.dawoo.lotterybox.mvp.model.setting.SettingModel;
import com.dawoo.lotterybox.mvp.model.user.IUserModel;
import com.dawoo.lotterybox.mvp.model.user.UserModel;
import com.dawoo.lotterybox.mvp.service.ISettingService;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.IFeedBackView;
import com.dawoo.lotterybox.mvp.view.IMCenterFragmentView;
import com.dawoo.lotterybox.mvp.view.ISystemSettingView;
import com.dawoo.lotterybox.mvp.view.IUpDatePasswoldView;
import com.dawoo.lotterybox.mvp.view.IUpdateSettingView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.Date;
import java.util.Map;

import rx.Subscription;

/**
 * Created by alex on 18-4-3.
 *
 * @author alex
 */

public class SettingPresenter<T extends IBaseView> extends BasePresenter {
    private Context mContext;
    private T mView;
    private final ISettingModel mModel;

    public SettingPresenter(Context mContext, T view) {
        super(mContext, view);
        this.mContext = mContext;
        this.mView = view;
        mModel = new SettingModel();
    }

    public void getSystemSetting() {
        Subscription subscription = mModel.getSystemSetting(new ProgressSubscriber(o -> ((ISystemSettingView) mView).onSettingResult((SettingBean) o), mContext));
        subList.add(subscription);
    }

    public void upDateSystemSetting(Map<String, String> params) {
        Subscription subscription = mModel.updateSystemSetting(new ProgressSubscriber(o -> ((IUpdateSettingView) mView).onUpdate(o), mContext), params);
        subList.add(subscription);
    }

    public void getFeedback(String type, String module, String faultTime,
                            String content, String contact, String Platform) {
        Subscription subscription = mModel.getFeedback(new ProgressSubscriber(o -> ((IFeedBackView) mView).feedBackResult(o), mContext), type, module, faultTime,
                content, contact, Platform);
        subList.add(subscription);
    }
}
