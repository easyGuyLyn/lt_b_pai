package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.lottery.OrderInfo;

/**
 * Created by b on 18-4-18.
 */

public interface IOrderView extends IBaseView {
    void onOrderResult(OrderInfo o);
}
