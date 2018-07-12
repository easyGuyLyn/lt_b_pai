package com.dawoo.lotterybox.bean.message;

import java.io.Serializable;

/**
 * Created by archar on 18-4-27.
 */

public class MailDetailBean extends BaseMailBean implements Serializable{
    private String receiverUsername;
    private String sendType;

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
