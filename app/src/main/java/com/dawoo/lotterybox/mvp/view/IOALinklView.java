package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.OALinkBean;

/**
 * Created by alex on 18-5-1.
 *
 */

public interface IOALinklView extends IBaseView {
    void onLinkResult(OALinkBean oaLinkBean);
}
