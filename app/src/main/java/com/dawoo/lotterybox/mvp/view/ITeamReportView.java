package com.dawoo.lotterybox.mvp.view;

import com.dawoo.lotterybox.bean.TeamReportBean;

import java.util.List;

/**
 * Created by alex on 18-4-24.
 */

public interface ITeamReportView extends IBaseView {
    void onRefreshResult(List<TeamReportBean> o);

    void onLoadMoreResult(List<TeamReportBean> o);
}
