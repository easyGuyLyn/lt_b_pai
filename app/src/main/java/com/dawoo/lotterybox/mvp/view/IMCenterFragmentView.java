package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.User;

/**
 * Created by benson on 18-2-8.
 */

public interface IMCenterFragmentView extends IBaseView {
    void onInfoResult(Object user);
    void onCSLinkResult(String link);
}
