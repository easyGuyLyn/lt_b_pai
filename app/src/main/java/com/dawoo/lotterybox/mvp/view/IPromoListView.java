package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.PromoListBean;

/**
 * Created by b on 18-3-16.
 */

public interface IPromoListView extends IBaseView {
     void onGetPromoList(PromoListBean promoListBean);
}
