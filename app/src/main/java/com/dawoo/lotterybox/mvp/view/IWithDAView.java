package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.WithDrawsAduitBean;

import java.util.List;

/**
 * Created by alex on 18-5-2.
 */

public interface IWithDAView extends IBaseView{
    void IWithDrawsAduitResult(List<WithDrawsAduitBean> o);
}
