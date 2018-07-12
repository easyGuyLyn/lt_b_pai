package com.dawoo.lotterybox.bean;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据中心
 * Created by benson on 18-1-26.
 */

public class DataCenter {
    private static DataCenter instance;
    private static User mUser = new User();
    private static SysInfo mSysInfo = new SysInfo();
    private static Map<String,String> mLotteryType = new HashMap<>();

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }


    public void setUser(User user) {
//        mUser.setDomain(user.getDomain());
//        mUser.setImgDomain(user.getImgDomain());
        mUser.setLogin(user.isLogin());
//        mUser.setUserId(user.getUserId());
        mUser.setUsername(user.getUsername());
        mUser.setPassword(user.getPassword());
//        mUser.setNickname(user.getNickname());
//        mUser.setBalance(user.getBalance());
        mUser.setAvatarUrl(user.getAvatarUrl());
        mUser.setToken(user.getToken());
        mUser.setRefreshToken(user.getRefreshToken());
        mUser.setExpire(user.getExpire());
    }

    public void clearUser() {
        mUser.setDomain("");
        mUser.setImgDomain("");
        mUser.setLogin(false);
        mUser.setUserId(0);
        mUser.setUsername("");
        mUser.setPassword("");
        mUser.setNickname("");
        mUser.setBalance("");
        mUser.setAvatarUrl("");
        mUser.setToken("");
        mUser.setRefreshToken("");
        mUser.setAuthKey("");
        mUser.setExpire(0);
    }

    public User getUser() {
        return mUser;
    }

    public DataCenter setDomain(String domain) {
        mUser.setDomain(domain);
        return instance;
    }

    public DataCenter setImgDomain(String domain) {
        mUser.setImgDomain(domain);
        return instance;
    }


    public boolean isLogin() {
        return mUser.isLogin();
    }


    public String getDomain() {
        return mUser.getDomain();
    }

    public SysInfo getSysInfo() {
        return mSysInfo;
    }

    public String getImgDomain() {
        return mUser.getImgDomain();
    }

    public Map<String, String> getmLotteryType() {
        return mLotteryType;
    }

    public void setmLotteryType(Map<String, String> mLotteryType) {
        DataCenter.mLotteryType = mLotteryType;
    }
}
