package com.dawoo.lotterybox.mvp.view;

/**
 * Created by benson on 18-2-7.
 */

public interface IRegisterView extends IBaseView {
    void onRigsterResult(boolean isSuccess);
    void doRigster();
    void doPwdToggle();
    void doConPwdToggle();
}
