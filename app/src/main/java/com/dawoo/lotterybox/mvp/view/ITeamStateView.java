package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.TeamStateBean;

/**
 * Created by alex on 18-4-24.
 */

public interface ITeamStateView extends IBaseView{
    void onTeamStateResult(TeamStateBean o);
}
