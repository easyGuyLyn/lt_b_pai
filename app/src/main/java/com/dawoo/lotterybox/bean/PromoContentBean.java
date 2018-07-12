package com.dawoo.lotterybox.bean;

/**
 * Created by b on 18-3-16.
 */

public class PromoContentBean {


    /**
     * id : 100060
     * activityName : devin test2
     * startTime : 1523794527000
     * endTime : 1524140127000
     * rank : all
     * activityType : first_deposit
     * activityTag : deposit
     * activityRule : {"depositAmountGe":[50.0],"percentageHandsel":[1.0],"regularHandsel":null,"preferentialAudit":[null],"preferentialAmountLimit":111.0,"depositWay":"wechatpay_fast","mosaicGold":true,"preferentialValue":null,"registAudit":null,"effectiveTime":null}
     * activityCover : lb/6/activity/1/1523794571084.png
     * activityDescription : <p>11111</p>
     * createUsername : m_devin@deve
     * createTime : 1523794571126
     * display : true
     * needCheck : false
     * status : normal
     * activityTimeStatus : processing
     */

    private int id;
    private String activityName;
    private long startTime;
    private long endTime;
    private String rank;
    private String activityType;
    private String activityTag;
    private String activityRule;
    private String activityCover;
    private String activityDescription;
    private String createUsername;
    private long createTime;
    private boolean display;
    private boolean needCheck;
    private String status;
    private String activityTimeStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag;
    }

    public String getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule;
    }

    public String getActivityCover() {
        return activityCover;
    }

    public void setActivityCover(String activityCover) {
        this.activityCover = activityCover;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(boolean needCheck) {
        this.needCheck = needCheck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityTimeStatus() {
        return activityTimeStatus;
    }

    public void setActivityTimeStatus(String activityTimeStatus) {
        this.activityTimeStatus = activityTimeStatus;
    }
}
