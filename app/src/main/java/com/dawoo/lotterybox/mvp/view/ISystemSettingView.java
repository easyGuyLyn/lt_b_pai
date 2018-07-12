package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.SettingBean;

/**
 * Created by alex on 18-4-3.
 */

public interface ISystemSettingView extends IBaseView {
    void onSettingResult(SettingBean settingBean);
}
