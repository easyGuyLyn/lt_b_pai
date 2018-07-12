package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;

import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.bean.TeamBillBean;
import com.dawoo.lotterybox.bean.TeamMemberBean;
import com.dawoo.lotterybox.bean.TeamReportBean;
import com.dawoo.lotterybox.bean.TeamStateBean;
import com.dawoo.lotterybox.mvp.model.BaseModel;
import com.dawoo.lotterybox.mvp.model.setting.ISettingModel;
import com.dawoo.lotterybox.mvp.model.setting.SettingModel;
import com.dawoo.lotterybox.mvp.model.team.ITeamModel;
import com.dawoo.lotterybox.mvp.model.team.TeamModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.ISystemSettingView;
import com.dawoo.lotterybox.mvp.view.ITeamChangesView;
import com.dawoo.lotterybox.mvp.view.ITeamMemberView;
import com.dawoo.lotterybox.mvp.view.ITeamReportView;
import com.dawoo.lotterybox.mvp.view.ITeamStateView;
import com.dawoo.lotterybox.mvp.view.IUpdateSettingView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.net.rx.DefaultSubscriber;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by alex on 18-4-24.
 * @author alex
 */

public class TeamPresserter<T extends IBaseView> extends BasePresenter {
    private Context mContext;
    private T mView;
    private final ITeamModel mModel;
    private int pageNumber=0;
    private int pageSize=10;
    public TeamPresserter(Context mContext, T view) {
        super(mContext, view);
        this.mContext = mContext;
        this.mView = view;
        mModel = new TeamModel();
    }

    public void getTeamDataState(DefaultCallback defaultCallback, String startDate
            , String endDate, int dateFlag) {
        Subscription subscription = mModel.getTeamDataState(new DefaultSubscriber<>(o -> ((ITeamStateView) mView).onTeamStateResult((TeamStateBean) o), defaultCallback), startDate, endDate, dateFlag);
        subList.add(subscription);
    }
    public void getTeamDataState( String startDate
            , String endDate) {
        Subscription subscription = mModel.getTeamDataStateSearch(new ProgressSubscriber(o -> ((ITeamStateView) mView).onTeamStateResult((TeamStateBean) o), mContext), startDate, endDate);
        subList.add(subscription);
    }


    public void getTeamMember(String startDate
            , String endDate, String playerType
            , String pageSize, String pageNumber) {
        Subscription subscription = mModel.getTeamMember(new ProgressSubscriber(o -> ((ITeamMemberView) mView).onRefreshResult((List<TeamMemberBean>) o), mContext), startDate,
                endDate, playerType, pageSize, pageNumber);
        subList.add(subscription);
    }

    public void getTeamMemberRefresh(DefaultCallback defaultCallback,String startDate
            , String endDate, String playerType) {
        pageNumber=1;
        Subscription subscription = mModel.getTeamMember(new DefaultSubscriber<>(o -> ((ITeamMemberView) mView).onRefreshResult((List<TeamMemberBean>) o), defaultCallback), startDate,
                endDate, playerType, String.valueOf(pageSize), String.valueOf(pageNumber));
        subList.add(subscription);
    }
    public void getTeamMemberLoadMore(DefaultCallback defaultCallback,String startDate
            , String endDate, String playerType) {
        pageNumber++;
        Subscription subscription = mModel.getTeamMember(new DefaultSubscriber<>(o -> ((ITeamMemberView) mView).onLoaderMoreResult((List<TeamMemberBean>) o), defaultCallback), startDate,
                endDate, playerType, String.valueOf(pageSize), String.valueOf(pageNumber));
        subList.add(subscription);
    }

    public void getTeamReport(String startDate
            , String endDate, String pageSize
            , String pageNumber) {
        Subscription subscription = mModel.getTeamReport(new ProgressSubscriber(o -> ((ITeamReportView) mView).onRefreshResult((List<TeamReportBean>) o), mContext), startDate, endDate,
                pageSize, pageNumber);
        subList.add(subscription);
    }


    public void getTeamReportRefresh(String startDate,String endDate,DefaultCallback defaultCallback){
        pageNumber=1;
        Subscription subscription = mModel.getTeamReport(new DefaultSubscriber<>(o -> ((ITeamReportView) mView).onRefreshResult((List<TeamReportBean>) o), defaultCallback), startDate, endDate,
                String.valueOf(pageSize), String.valueOf(pageNumber));
        subList.add(subscription);
    }
    public void getTeamReportLoaderMore(String startDate,String endDate,DefaultCallback defaultCallback){
        pageNumber++;
        Subscription subscription = mModel.getTeamReport(new DefaultSubscriber<>(o -> ((ITeamReportView) mView).onLoadMoreResult((List<TeamReportBean>) o), defaultCallback), startDate, endDate,
                String.valueOf(pageSize), String.valueOf(pageNumber));
        subList.add(subscription);
    }

    public void getTeamChanges(String startDate,String endDate,String way,String type,String item){
        Subscription subscription = mModel.getTeamChanges(new ProgressSubscriber(o -> ((ITeamChangesView) mView).onTeamChangrsResult((List<TeamBillBean>) o), mContext), startDate, endDate,way,type,item);
        subList.add(subscription);
    }
    public void getTeamChangesRefresh(String startDate,String endDate,String way,String type,String item,DefaultCallback defaultCallback){
        Subscription subscription = mModel.getTeamChanges(new DefaultSubscriber<>(o -> ((ITeamChangesView) mView).onTeamChangrsResult((List<TeamBillBean>) o), defaultCallback), startDate, endDate,way,type,item);
        subList.add(subscription);
    }
}
