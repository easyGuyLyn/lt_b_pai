package com.dawoo.lotterybox.mvp.view;


import com.dawoo.lotterybox.bean.LoginBean;

/**
 * Created by benson on 17-12-21.
 */

public interface ILoginView extends IBaseView {

    void onLoginResult(LoginBean o);
    void doLogin();
    void doPwdToggle();
}

