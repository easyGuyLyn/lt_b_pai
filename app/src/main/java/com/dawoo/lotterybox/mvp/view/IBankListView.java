package com.dawoo.lotterybox.mvp.view;


import com.dawoo.lotterybox.bean.Bank;

import java.util.List;

/**
 * @author jack
 * @date 18-2-19
 */

public interface IBankListView extends IBaseView {
    void onResultDate(List<Bank> banks);
}
