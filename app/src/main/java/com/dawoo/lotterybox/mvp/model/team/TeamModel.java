package com.dawoo.lotterybox.mvp.model.team;

import com.dawoo.lotterybox.bean.TeamBillBean;
import com.dawoo.lotterybox.bean.TeamMemberBean;
import com.dawoo.lotterybox.bean.TeamReportBean;
import com.dawoo.lotterybox.bean.TeamStateBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.service.ITeamService;
import com.dawoo.lotterybox.mvp.service.IWithDrawsService;
import com.dawoo.lotterybox.net.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-24.
 * @author alex
 */

public class TeamModel extends BaseModel implements ITeamModel {
    @Override
    public Subscription getTeamDataState(Subscriber subscriber, String startDate, String endDate, int dateFlag) {
        Observable<TeamStateBean> observable = RetrofitHelper.getService(ITeamService.class).getTeamDataState(startDate, endDate, dateFlag).map(new HttpResultFunc<>());
        return toSubscribe(observable,subscriber);
    }

    @Override
    public Subscription getTeamDataStateSearch(Subscriber subscriber, String startDate, String endDate) {
        Observable<TeamStateBean> observable = RetrofitHelper.getService(ITeamService.class).getTeamDataStateSearch(startDate, endDate,"-1").map(new HttpResultFunc<>());
        return toSubscribe(observable,subscriber);
    }

    @Override
    public Subscription getTeamMember(Subscriber subscriber, String startDate, String endDate, String playerType, String pageSize, String pageNumber) {
        Observable<List<TeamMemberBean>> observable = RetrofitHelper.getService(ITeamService.class).getTeamMember(startDate, endDate, playerType,pageSize,pageNumber).map(new HttpResultFunc<>());
        return toSubscribe(observable,subscriber);
    }

    @Override
    public Subscription getTeamReport(Subscriber subscriber, String startDate, String endDate, String pageSize, String pageNumber) {
        Observable<List<TeamReportBean>> observable = RetrofitHelper.getService(ITeamService.class).getTeamReport(startDate, endDate, pageSize,pageNumber).map(new HttpResultFunc<>());
        return toSubscribe(observable,subscriber);
    }

    @Override
    public Subscription getTeamChanges(Subscriber subscriber, String startDate, String endDate, String way, String type,String item) {
        Observable<List<TeamBillBean>> observable = RetrofitHelper.getService(ITeamService.class).getTeamChanges(startDate, endDate, way, type,item).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }


}
