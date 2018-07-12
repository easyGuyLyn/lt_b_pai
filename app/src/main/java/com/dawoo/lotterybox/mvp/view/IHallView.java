package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;

import java.util.List;

/**
 * Created by b on 18-2-16.
 */

public interface IHallView extends IBaseView{

    void onBanner(List<BannerBean> bannerBeans);

    void onBulletin(List<Bulletin> bulletins);

    void onTypeAndLottery(List<TypeAndLotteryBean> lotteryTypes);
}
