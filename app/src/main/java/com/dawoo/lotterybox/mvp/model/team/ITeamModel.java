package com.dawoo.lotterybox.mvp.model.team;

import com.dawoo.lotterybox.mvp.model.BaseModel;

import retrofit2.http.Query;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by alex on 18-4-24.
 * @author alex
 */

public interface ITeamModel {
    Subscription getTeamDataState(Subscriber subscriber,String startDate
            ,  String endDate,int dateFlag);

    Subscription getTeamDataStateSearch(Subscriber subscriber,String startDate
            ,  String endDate);


    Subscription getTeamMember(Subscriber subscriber,String startDate
            ,  String endDate,String playerType
            , String pageSize, String pageNumber);

    Subscription getTeamReport(Subscriber subscriber,  String startDate
            ,  String endDate,  String pageSize
            ,  String pageNumber);
    Subscription getTeamChanges(Subscriber subscriber, String startDate, String endDate, String pageSize, String pageNumber,String item);
}
