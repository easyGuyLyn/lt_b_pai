package com.dawoo.lotterybox.bean.message;


import com.dawoo.lotterybox.net.BaseHttpResult;

import java.util.List;

/**
 * Created by archar on 18-4-26.
 */

public class ReceiveMsgBean extends BaseHttpResult {

    private Count extend;

    private List<ReceiveMsgListBean> data;

    public Count getExtend() {
        return extend;
    }

    public void setExtend(Count extend) {
        this.extend = extend;
    }

    public List<ReceiveMsgListBean> getReceiveMsgListBeans() {
        return data;
    }

    public void setReceiveMsgListBeans(List<ReceiveMsgListBean> receiveMsgListBeans) {
        this.data = receiveMsgListBeans;
    }

    public static class ReceiveMsgListBean extends BaseMailBean {

        /**
         * id : 103158
         * title : 888888888888888
         * content : 888
         * createTime : 1524483739524
         * createUsername : m_stark
         * readStatus : 0
         * sendType : 1
         * sendCount : 0
         */

        private String sendType;
        private int sendCount;

        public String getSendType() {
            return sendType;
        }

        public void setSendType(String sendType) {
            this.sendType = sendType;
        }

        public int getSendCount() {
            return sendCount;
        }

        public void setSendCount(int sendCount) {
            this.sendCount = sendCount;
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
