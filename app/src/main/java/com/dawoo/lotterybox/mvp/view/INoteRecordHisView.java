package com.dawoo.lotterybox.mvp.view;


/**
 * Created by benson on 17-12-21.
 */

public interface INoteRecordHisView extends IBaseView {

    void onRefreshResult(Object o);

    void onLoadMoreResult(Object o);

    void onLotteryDataResult(Object o);

    void onAssetsResult(Object o);

    void onRecentProfit(Object o);
}

