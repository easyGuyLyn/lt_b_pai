package com.dawoo.lotterybox.bean;

/**
 * Created by benson on 18-2-7.
 */

public class LoginBean {
    /**
     * token : e1231c83c9e66bae94e193936a8d1525
     * expire : 43200
     * refreshToken : 8a42d48612ecce25c450c247226cf7e2
     */

    private String token;
    private int expire;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
