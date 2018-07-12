package com.dawoo.lotterybox.mvp.model.user;


import rx.Subscriber;
import rx.Subscription;

/**
 * Created by benson on 17-12-21.
 */

public interface IUserModel {
    Subscription login(Subscriber subscriber, String name, String pwd, String appKey, String appSecret, String serialNo);

    Subscription register(Subscriber subscriber, String name, String pwd, String confirmPwd, String createChannel, String playerType, String mode);
    Subscription createAccount(Subscriber subscriber, String name, String pwd, String confirmPwd, String createChannel, String playerType, String parentId);
    Subscription getUserInfo(Subscriber subscriber);

    Subscription signOut(Subscriber subscriber);

    Subscription upDatePasswold(Subscriber subscriber, String oldPwd, String newPwd);

    Subscription upDateNickName(Subscriber subscriber, String nickName);

    Subscription upDateRealName(Subscriber subscriber, String realName);

    Subscription upDatePermissionPassword(Subscriber subscriber, String upDatePermissionPassword);

    Subscription quirePermissionPassword(Subscriber subscriber, String upDatePermissionPassword);



    Subscription getUserInfoWithSystemSetting(Subscriber subscriber);

    Subscription getCreateAccocuntLink(Subscriber subscriber);

}
