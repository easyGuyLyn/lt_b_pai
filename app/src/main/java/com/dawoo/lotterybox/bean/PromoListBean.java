package com.dawoo.lotterybox.bean;

import java.util.List;

/**
 * Created by b on 18-3-15.
 */

public class PromoListBean {

    /**
     * error : 0
     * code : null
     * message : null
     * data : [{"name":"首存送","startTime":1493615537000,"endTime":1541826737000,"activityName":"双十一","id":"17","activityCover":"135/headImage/1/1493882010023.png"}]
     * extend : {"totalCount":4}
     */

    private int error;
    private Object code;
    private Object message;
    private ExtendBean extend;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ExtendBean {
        /**
         * totalCount : 4
         */

        private int totalCount;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DataBean {
        /**
         * name : 首存送
         * startTime : 1493615537000
         * endTime : 1541826737000
         * activityName : 双十一
         * id : 17
         * activityCover : 135/headImage/1/1493882010023.png
         */

        private String name;
        private long startTime;
        private long endTime;
        private String activityName;
        private int id;
        private String activityCover;
        private String activityType;

        public String getActivityType() {
            return activityType;
        }

        public void setActivityType(String activityType) {
            this.activityType = activityType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivityCover() {
            return activityCover;
        }

        public void setActivityCover(String activityCover) {
            this.activityCover = activityCover;
        }
    }
}
