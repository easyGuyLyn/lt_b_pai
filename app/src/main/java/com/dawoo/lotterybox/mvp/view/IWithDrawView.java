package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.bean.WithDrawsFeeBean;

/**
 * Created by alex on 18-4-5.
 */

public interface IWithDrawView extends IBaseView {
    void onFeeResult(WithDrawsFeeBean o);

    void onInitResult(WithDrawBean withDrawBean);

    void onApplyResult(Object o);
}
