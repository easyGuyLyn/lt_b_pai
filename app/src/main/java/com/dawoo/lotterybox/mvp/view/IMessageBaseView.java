package com.dawoo.lotterybox.mvp.view;


/**
 * Created by benson on 17-12-21.
 */

public interface IMessageBaseView extends IBaseView {

    void onRefreshResult(Object o);

    void onLoadMoreResult(Object o);

}

