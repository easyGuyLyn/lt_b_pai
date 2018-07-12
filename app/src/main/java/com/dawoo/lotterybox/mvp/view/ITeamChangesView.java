package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.TeamBillBean;

import java.util.List;

/**
 * Created by alex on 18-4-30.
 */

public interface ITeamChangesView extends IBaseView {
    void onTeamChangrsResult(List<TeamBillBean> teamBillBeans);
}
