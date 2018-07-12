package com.dawoo.lotterybox.mvp.service;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.bean.LoginBean;
import com.dawoo.lotterybox.bean.LoginOutBean;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.OALinkBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.bean.InfoMation;
import com.dawoo.lotterybox.bean.WithDrawBean;
import com.dawoo.lotterybox.net.HttpResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 用户相关的接口
 *
 * @author benson
 * @date 17-12-21
 */

public interface IUserService {

    /**
     * 登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("passport/login.html")
    Observable<HttpResult<LoginBean>> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("appKey") String appKey,
            @Field("appSecret") String appSecret,
            @Field("serialNo") String serialNo);

    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/register.html")
    Observable<HttpResult<Boolean>> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("confirmPassword") String confirmPassword,
            @Field("createChannel") String createChannel,
            @Field("playerType") String playerType,
            @Field("mode") String mode);

    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/register.html")
    Observable<HttpResult<Boolean>> createAccount(
            @Field("username") String username,
            @Field("password") String password,
            @Field("confirmPassword") String confirmPassword,
            @Field("createChannel") String createChannel,
            @Field("playerType") String playerType,
            @Field("parentId") String parentId);

    /**
     * 获取玩家信息
     *
     * @return
     */
    @GET("mobile/account/get-user-info.html")
    Observable<HttpResult<User>> getUserInfo();

    /**
     * 用戶登出
     *
     * @return
     */
    @GET("mobile/account/logout.html")
    Observable<HttpResult<Boolean>> signOut();

    @FormUrlEncoded
    @POST("mobile/account/update-password.html")
    Observable<HttpResult<Boolean>> upDatePasswold(
            @Field("oldPwd") String oldPwd,
            @Field("newPwd") String newPwd);
//account/update-nickname

    /**
     * 修改玩家昵称
     *
     * @param nickName
     * @return
     */
    @FormUrlEncoded
    @POST("mobile/account/update-nickname.html")
    Observable<HttpResult<InfoMation>> upDateNickName(
            @Field("nickName") String nickName);

    /**
     * 修改玩家真实姓名
     */
    @FormUrlEncoded
    @POST("mobile/account/update-real-name.html")
    Observable<HttpResult<InfoMation>> upDateRealName(
            @Field("realName") String nickName);

    /**
     * 修改安全密碼
     */
    @FormUrlEncoded
    @POST("mobile/account/update-permission-password.html")
    Observable<HttpResult<InfoMation>> upDatePermissionPassword(
            @Field("permissionPwd") String permissionPwd);

    /**
     * 教研安全密码
     */
    @FormUrlEncoded
    @POST("mobile/account/query-permission-password.html")
    Observable<HttpResult<InfoMation>> queryPermissionPassword(
            @Field("permissionPwd") String permissionPwd);

    @GET("mobile/account/create-link-account.html")
    Observable<HttpResult<OALinkBean>> getCreateAccocuntLink();

}
