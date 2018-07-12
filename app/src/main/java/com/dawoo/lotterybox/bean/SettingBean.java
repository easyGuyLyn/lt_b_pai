package com.dawoo.lotterybox.bean;

/**
 * Created by alex on 18-4-5.
 */

public class SettingBean {

    /**
     * award_push : {"id":10651,"userId":10128,"paramType":"push","paramCode":"award_push","paramValue":"","active":true,"orderNum":1,"remark":"中奖推送"}
     * open_push : {"id":10652,"userId":10128,"paramType":"push","paramCode":"open_push","paramValue":"","active":true,"orderNum":2,"remark":"开奖推送"}
     * shake : {"id":10653,"userId":10128,"paramType":"shake","paramCode":"shake","paramValue":"yes","active":true,"orderNum":3,"remark":"摇一摇机选"}
     * voice : {"id":10654,"userId":10128,"paramType":"voice","paramCode":"voice","paramValue":"yes","active":true,"orderNum":4,"remark":"声音提醒"}
     * gesture : {"id":10655,"userId":10128,"paramType":"lock","paramCode":"gesture","paramValue":"","active":true,"orderNum":5,"remark":"手势锁屏"}
     * animation : {"id":10656,"userId":10128,"paramType":"award","paramCode":"animation","paramValue":"no","active":true,"orderNum":6,"remark":"开奖动画"}
     * device : {"id":10657,"userId":10128,"paramType":"lock","paramCode":"device","paramValue":"no","active":true,"orderNum":7,"remark":"设备锁"}
     */

    private AwardPushBean award_push;
    private OpenPushBean open_push;
    private ShakeBean shake;
    private VoiceBean voice;
    private GestureBean gesture;
    private AnimationBean animation;
    private DeviceBean device;

    public AwardPushBean getAward_push() {
        return award_push;
    }

    public void setAward_push(AwardPushBean award_push) {
        this.award_push = award_push;
    }

    public OpenPushBean getOpen_push() {
        return open_push;
    }

    public void setOpen_push(OpenPushBean open_push) {
        this.open_push = open_push;
    }

    public ShakeBean getShake() {
        return shake;
    }

    public void setShake(ShakeBean shake) {
        this.shake = shake;
    }

    public VoiceBean getVoice() {
        return voice;
    }

    public void setVoice(VoiceBean voice) {
        this.voice = voice;
    }

    public GestureBean getGesture() {
        return gesture;
    }

    public void setGesture(GestureBean gesture) {
        this.gesture = gesture;
    }

    public AnimationBean getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationBean animation) {
        this.animation = animation;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public static class AwardPushBean {
        /**
         * id : 10651
         * userId : 10128
         * paramType : push
         * paramCode : award_push
         * paramValue :
         * active : true
         * orderNum : 1
         * remark : 中奖推送
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class OpenPushBean {
        /**
         * id : 10652
         * userId : 10128
         * paramType : push
         * paramCode : open_push
         * paramValue :
         * active : true
         * orderNum : 2
         * remark : 开奖推送
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class ShakeBean {
        /**
         * id : 10653
         * userId : 10128
         * paramType : shake
         * paramCode : shake
         * paramValue : yes
         * active : true
         * orderNum : 3
         * remark : 摇一摇机选
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class VoiceBean {
        /**
         * id : 10654
         * userId : 10128
         * paramType : voice
         * paramCode : voice
         * paramValue : yes
         * active : true
         * orderNum : 4
         * remark : 声音提醒
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class GestureBean {
        /**
         * id : 10655
         * userId : 10128
         * paramType : lock
         * paramCode : gesture
         * paramValue :
         * active : true
         * orderNum : 5
         * remark : 手势锁屏
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class AnimationBean {
        /**
         * id : 10656
         * userId : 10128
         * paramType : award
         * paramCode : animation
         * paramValue : no
         * active : true
         * orderNum : 6
         * remark : 开奖动画
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class DeviceBean {
        /**
         * id : 10657
         * userId : 10128
         * paramType : lock
         * paramCode : device
         * paramValue : no
         * active : true
         * orderNum : 7
         * remark : 设备锁
         */

        private int id;
        private int userId;
        private String paramType;
        private String paramCode;
        private String paramValue;
        private boolean active;
        private int orderNum;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getParamCode() {
            return paramCode;
        }

        public void setParamCode(String paramCode) {
            this.paramCode = paramCode;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
