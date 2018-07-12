package com.dawoo.lotterybox.bean;

import com.blankj.utilcode.util.LogUtils;
import com.dawoo.lotterybox.bean.record.MutType;

import java.text.DecimalFormat;

/**
 * Created by benson on 17-12-21.
 */

public class User extends LoginBean {

    /**
     * 域名线路
     */
    private String domain;

    /**
     * 图片的服务器域名
     */
    private String imgDomain;

    /**
     * 是否登录
     */
    private boolean isLogin;

    /**
     * 玩家id
     */
    private long userId;
    /**
     * 玩家账号
     */
    private String username;
    /**
     * 用户的登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 玩家钱包余额
     */
    private String balance;

    /**
     * 头像相对路径
     */
    private String avatarUrl;

    /**
     * 是否绑定银行卡
     */
    private boolean isBindBankCard;

    /**
     * 是否设置安全密码
     */
    private boolean hasSecPwd;

    /**
     * 玩家等级
     */
    private String playerLevel;

    private String playerType;

    /**
     * 真实姓名
     *
     * @return
     */
    private String realname;

    private MutType mutType;

    private String authKey;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }


    public MutType getMutType() {
        return mutType;
    }

    public void setMutType(MutType mutType) {
        this.mutType = mutType;
    }

    public String getRealName() {
        return realname;
    }

    public void setRealName(String realname) {
        this.realname = realname;
    }

    public boolean isHasSecPwd() {
        return hasSecPwd;
    }

    public void setHasSecPwd(boolean hasSecPwd) {
        this.hasSecPwd = hasSecPwd;
    }

    public String getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(String playerLevel) {
        this.playerLevel = playerLevel;
    }

    public User() {
    }


    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;

    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isBindBankCard() {
        return isBindBankCard;
    }

    public void setBindBankCard(boolean bindBankCard) {
        isBindBankCard = bindBankCard;
    }
}

