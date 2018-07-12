package com.dawoo.lotterybox.mvp.model.user;


import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.LoginBean;
import com.dawoo.lotterybox.bean.LoginOutBean;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.OALinkBean;
import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.bean.InfoMation;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.record.MutType;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.IRecordService;
import com.dawoo.lotterybox.mvp.service.ISettingService;
import com.dawoo.lotterybox.mvp.service.IUserService;
import com.dawoo.lotterybox.mvp.service.IWithDrawsService;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;


/**
 * Created by benson on 17-12-21.
 */

public class UserModel extends BaseModel implements IUserModel {


    @Override
    public Subscription login(Subscriber subscriber, String name, String pwd, String appKey, String appSecret, String serialNo) {
        Observable<LoginBean> observable = RetrofitHelper
                .getService(IUserService.class)
                .login(name, pwd, appKey, appSecret, serialNo)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription register(Subscriber subscriber, String name, String pwd, String confirmPwd, String createChannel, String playerType, String mode) {
        Observable<Boolean> observable = RetrofitHelper
                .getService(IUserService.class)
                .register(name, pwd, confirmPwd, createChannel, playerType, mode)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription createAccount(Subscriber subscriber, String name, String pwd, String confirmPwd, String createChannel, String playerType, String parentId) {
        Observable<Boolean> observable = RetrofitHelper
                .getService(IUserService.class)
                .createAccount(name, pwd, confirmPwd, createChannel, playerType, parentId)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getUserInfo(Subscriber subscriber) {
        Observable<User> observable = RetrofitHelper.getService(IUserService.class).getUserInfo().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 用戶登出
     *
     * @param subscriber
     * @return
     */
    @Override
    public Subscription signOut(Subscriber subscriber) {
        Observable<Boolean> observable = RetrofitHelper.getService(IUserService.class).signOut().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 修改玩家密码
     */
    @Override
    public Subscription upDatePasswold(Subscriber subscriber, String oldPwd, String newPwd) {
        Observable<Boolean> observable = RetrofitHelper
                .getService(IUserService.class)
                .upDatePasswold(oldPwd, newPwd)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 修改玩家昵称
     *
     * @param subscriber
     * @param
     * @return
     */
    @Override
    public Subscription upDateNickName(Subscriber subscriber, String nickName) {
        Observable<InfoMation> observable = RetrofitHelper
                .getService(IUserService.class)
                .upDateNickName(nickName)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription upDateRealName(Subscriber subscriber, String realName) {
        Observable<InfoMation> observable = RetrofitHelper
                .getService(IUserService.class)
                .upDateRealName(realName)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 修改安全密码
     *
     * @param subscriber
     * @param upDatePermissionPassword
     * @return
     */
    @Override
    public Subscription upDatePermissionPassword(Subscriber subscriber, String upDatePermissionPassword) {
        Observable<InfoMation> observable = RetrofitHelper
                .getService(IUserService.class)
                .upDatePermissionPassword(upDatePermissionPassword)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }

    /**
     * 校验安全密码
     *
     * @param subscriber
     * @param qureyPermissionPassword
     * @return
     */
    @Override
    public Subscription quirePermissionPassword(Subscriber subscriber, String qureyPermissionPassword) {
        Observable<InfoMation> observable = RetrofitHelper
                .getService(IUserService.class)
                .queryPermissionPassword(qureyPermissionPassword)
                .map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }



    @Override
    public Subscription getUserInfoWithSystemSetting(Subscriber subscriber) {
        Observable<User> userObservable = RetrofitHelper.getService(IUserService.class).getUserInfo().map(new HttpResultFunc<>());
        Observable<SettingBean> settingBeanObservable = RetrofitHelper.getService(ISettingService.class).getSystemSetting().map(new HttpResultFunc<>());
        Observable<List<MyBandCard>> bankListObservable = RetrofitHelper.getService(IWithDrawsService.class).bankcardList().map(new HttpResultFunc<>());
        Observable<Object> observable = Observable.merge(userObservable, settingBeanObservable,bankListObservable);
        return toSubscribe(observable, subscriber);
    }

    @Override
    public Subscription getCreateAccocuntLink(Subscriber subscriber) {
        Observable<OALinkBean> observable = RetrofitHelper.getService(IUserService.class).getCreateAccocuntLink().map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }


}
