package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.TeamMemberBean;

import java.util.List;

/**
 * Created by alex on 18-4-24.
 */

public interface ITeamMemberView extends IBaseView {
    void onRefreshResult(List<TeamMemberBean> o);

    void onLoaderMoreResult(List<TeamMemberBean> o);
}
