package com.dawoo.lotterybox.bean.message;


import com.dawoo.lotterybox.net.BaseHttpResult;

import java.util.List;

/**
 * Created by archar on 18-4-26.
 */

public class SendMsgBean extends BaseHttpResult {

    private Count extend;

    private List<SendMsgListBean> data;

    public Count getExtend() {
        return extend;
    }

    public void setExtend(Count extend) {
        this.extend = extend;
    }

    public List<SendMsgListBean> getSendMsgListBeans() {
        return data;
    }

    public void setSendMsgListBeans(List<SendMsgListBean> sendMsgListBeans) {
        this.data = sendMsgListBeans;
    }

    public static class SendMsgListBean extends BaseMailBean {

        /**
         * createtime : 1524120227969
         * receiverusername : devin
         * id : 102774
         * readstatus : 0
         * title : 12
         * content : 13
         * createusername : devin1
         */

        private String receiverUsername;

        public String getReceiverusername() {
            return receiverUsername;
        }

        public void setReceiverusername(String receiverusername) {
            this.receiverUsername = receiverusername;
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
