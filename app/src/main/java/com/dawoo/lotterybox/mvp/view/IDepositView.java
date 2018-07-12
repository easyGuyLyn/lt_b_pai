package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.net.BaseHttpResult;

import java.util.List;

/**
 * Created by rain on 18-4-30.
 */

public interface IDepositView extends IBaseView {
    void getDepositWay(List<ParentDepositBean> beans);

    void getBankDepositDetail(List<PayDetailBean> bean);

    void getFee(Object o);

    void postResult(OnlineResulltBean o);

    void callBackOrder(CallBackUrlBean o);

    void getSales(List<SaleBean> saleBeans);

    void getSaleMoney(Object o);

    void postCompany(BaseHttpResult o);
}
