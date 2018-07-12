package com.dawoo.lotterybox.bean;

/**
 * 系统信息
 * 版本码
 * 版本号
 * mac待定
 * Created by benson on 18-2-1.
 */

public class SysInfo {
    private String versionName;
    private String versionCode;
    private String mac;

    public SysInfo() {
    }

    public void initSysInfo(String versionName, String versionCode, String mac) {
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.mac = mac;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
