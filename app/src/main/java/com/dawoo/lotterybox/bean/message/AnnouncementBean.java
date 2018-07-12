package com.dawoo.lotterybox.bean.message;


import com.dawoo.lotterybox.net.BaseHttpResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by archar on 18-4-26.
 */

public class AnnouncementBean extends BaseHttpResult {

    private Count extend;

    private List<NoticeListBean> data;

    public Count getExtend() {
        return extend;
    }

    public void setExtend(Count extend) {
        this.extend = extend;
    }

    public List<NoticeListBean> getNoticeListBeans() {
        return data;
    }

    public void setNoticeListBeans(List<NoticeListBean> noticeListBeans) {
        this.data = noticeListBeans;
    }

    public static class NoticeListBean implements Serializable{
        private long createTime;
        private int id;
        private String content;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public static class Count {
        private int totalCount;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }
}
