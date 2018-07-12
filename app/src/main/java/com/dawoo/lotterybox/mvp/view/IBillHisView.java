package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.BillAmountBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.BillCommonBean;
import com.dawoo.lotterybox.bean.record.BillItemBean;

import java.util.List;

/**
 * Created by rain on 18-4-19.
 */

public interface  IBillHisView extends IBaseView {
    void getBillsData(List<BillItemBean> mDatas);
    void getBillCount(BillCommonBean o);
    void getBillAmount(BillAmountBean o);
}
