package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.bean.TeamBillBean;
import com.dawoo.lotterybox.bean.TeamMemberBean;
import com.dawoo.lotterybox.bean.TeamReportBean;
import com.dawoo.lotterybox.bean.TeamStateBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by alex on 18-4-24.
 *
 * @author alex
 */

public interface ITeamService {

    /**
     * 首页统计数据
     *
     * @return
     */
    @GET("mobile/team/data-stats.html")
    Observable<HttpResult<TeamStateBean>> getTeamDataState(@Query("search.startDate") String startDate
            , @Query("search.endDate") String endDate, @Query("dateFlag") int dateFlag);

    /**
     * 首页统计数据搜索
     *
     * @return
     */
    @GET("mobile/team/data-stats.html")
    Observable<HttpResult<TeamStateBean>> getTeamDataStateSearch(@Query("search.startDate") String startDate
            , @Query("search.endDate") String endDate, @Query("dateFlag") String dateFlag);

    /**
     * 成员列表
     */
    @GET("mobile/team/get-members.html")
    Observable<HttpResult<List<TeamMemberBean>>> getTeamMember(@Query("search.startDate") String startDate
            , @Query("search.endDate") String endDate, @Query("search.playerType") String playerType
            , @Query("paging.pageSize") String pageSize, @Query("paging.pageNumber") String pageNumber);

    /**
     * 团队报表
     */
    @GET("mobile/team/get-members-report.html")
    Observable<HttpResult<List<TeamReportBean>>> getTeamReport(@Query("search.startDate") String startDate
            , @Query("search.endDate") String endDate, @Query("paging.pageSize") String pageSize
            , @Query("paging.pageNumber") String pageNumber);

    /**
     * 账变明细
     */
    @GET("mobile/billchange/get-team-changes.html")
    Observable<HttpResult<List<TeamBillBean>>> getTeamChanges(@Query("search.queryStartDate") String startDate
            , @Query("search.queryEndDate") String endDate, @Query("search.way") String way
            , @Query("search.type") String type, @Query("search.item") String item);
}
