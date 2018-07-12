package com.dawoo.lotterybox.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.ValidateUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.LoginBean;
import com.dawoo.lotterybox.bean.OALinkBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.bean.InfoMation;
import com.dawoo.lotterybox.mvp.model.user.IUserModel;
import com.dawoo.lotterybox.mvp.model.user.UserModel;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.ILoginView;
import com.dawoo.lotterybox.mvp.view.IMCenterFragmentView;
import com.dawoo.lotterybox.mvp.view.IOALinklView;
import com.dawoo.lotterybox.mvp.view.IRegisterView;
import com.dawoo.lotterybox.mvp.view.ISignOutView;
import com.dawoo.lotterybox.mvp.view.IUpDatePasswoldView;
import com.dawoo.lotterybox.mvp.view.IUpUerRealNameView;
import com.dawoo.lotterybox.mvp.view.IUpdatePermissionPasswordView;
import com.dawoo.lotterybox.mvp.view.IUserInfoMation;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.net.rx.DefaultSubscriber;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SPConfig;

import java.text.DecimalFormat;
import java.util.Random;

import rx.Subscription;

/**
 * 用户相关的presenter
 * Created by benson on 18-1-7.
 */

public class UserPresenter<T extends IBaseView> extends BasePresenter {
    private final Context mContext;
    private T mView;
    private final IUserModel mModel;

    public UserPresenter(Context context, T mView) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mModel = new UserModel();
    }

    /**
     * 登录
     */
    public void login(String name, String pwd, String appKey, String appSecret, String serialNo) {
        Subscription subscription = mModel.login(new ProgressSubscriber(o ->
                        ((ILoginView) mView).onLoginResult((LoginBean) o), mContext),
                name,
                pwd,
                appKey,
                appSecret,
                serialNo);
        subList.add(subscription);
    }

    /**
     * 获取头像
     *
     * @return
     */
    public String getHeadIcon() {
        String name = SPUtils.getInstance().getString(SPConfig.USERNAME);
        String url = SPUtils.getInstance().getString(name);
        if (TextUtils.isEmpty(url)) {
            // [0,1)  [0,42)  [1,43)
            int max = 11;
            int min = 1;
            Random random = new Random();
            int num = random.nextInt(max) + min;
            url = "a" + num;
            setIcon(url);
        }
        return url;
    }

    /**
     * 设置头像
     *
     * @return
     */
    public void setIcon(String url) {
        String name = SPUtils.getInstance().getString(SPConfig.USERNAME);
        SPUtils.getInstance().put(name, url);
    }

    /**
     * 登录
     */
    public void register(String name, String pwd, String confirmPwd, String createChannel, String playerType, String mode) {
        if (!validate(name, pwd, confirmPwd)) {
            return;
        }


        Subscription subscription = mModel.register(new ProgressSubscriber(o ->
                        ((IRegisterView) mView).onRigsterResult((Boolean) o), mContext),
                name,
                pwd,
                confirmPwd,
                createChannel,
                playerType,
                mode);
        subList.add(subscription);
    }

    public void createAccount(String name, String pwd, String confirmPwd, String createChannel, String playerType, String parentId) {
        if (!validate(name, pwd, confirmPwd)) {
            return;
        }


        Subscription subscription = mModel.createAccount(new ProgressSubscriber(o ->
                        ((IRegisterView) mView).onRigsterResult((Boolean) o), mContext),
                name,
                pwd,
                confirmPwd,
                createChannel,
                playerType,
                parentId);
        subList.add(subscription);
    }

    /**
     * 注册的验证
     *
     * @param name
     * @param pwd
     * @param confirmPwd
     * @return
     */
    public boolean validate(String name, String pwd, String confirmPwd) {
        // 判断空
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showResShort(mContext, R.string.validate_register_user);
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showResShort(mContext, R.string.validate_register_pwd);
            return false;
        }
        if (TextUtils.isEmpty(confirmPwd)) {
            ToastUtil.showResShort(mContext, R.string.validate_register_confirm_pwd);
            return false;
        }

        // 密码不相等
        if (!pwd.equals(confirmPwd)) {
            ToastUtil.showResShort(mContext, R.string.validate_register_pwd_not_same);
            return false;
        }

        // 位数不正确
        if (name.length() < 4 || name.length() > 16) {
            ToastUtil.showResShort(mContext, R.string.validate_register_user_regular_correct);
            return false;
        }
        if (pwd.length() < 6 || pwd.length() > 16) {
            ToastUtil.showResShort(mContext, R.string.validate_register_pwd_regular_correct);
            return false;
        }

        // 格式不正确
        if (!ValidateUtil.isStringFormatCorrect(name)) {
            ToastUtil.showResShort(mContext, R.string.validate_register_regular_correct);
            return false;
        }

        return true;
    }


    /**
     * 获取用户信息
     */
    public void getUerInfo() {
        Subscription subscription = mModel.getUserInfo(new ProgressSubscriber(o -> ((IMCenterFragmentView) mView).onInfoResult(o), mContext));
        subList.add(subscription);
    }

    /**
     * 获取用户信息
     */
    public void getUerInfoWithoutDialog(DefaultCallback defaultCallback) {
        Subscription subscription = mModel.getUserInfo(new DefaultSubscriber<>(o -> ((IMCenterFragmentView) mView).onInfoResult(o), defaultCallback));
        subList.add(subscription);
    }

    public void setUserWithoutToken(User userWithoutToken) {
        User user = DataCenter.getInstance().getUser();
        user.setNickname(userWithoutToken.getNickname());
        user.setBalance(NumberFormaterUtils.formaterS2S(userWithoutToken.getBalance()));
        user.setUserId(userWithoutToken.getUserId());
        user.setHasSecPwd(userWithoutToken.isHasSecPwd());
        user.setPlayerLevel(userWithoutToken.getPlayerLevel());
        user.setRealName(userWithoutToken.getRealName());
        user.setPlayerType(userWithoutToken.getPlayerType());
        user.setAuthKey(userWithoutToken.getAuthKey());
    }


    /**
     * 用户登出
     */
    public void signOut() {
        Subscription subscription = mModel.signOut(new ProgressSubscriber(o -> ((ISignOutView) mView).onResult(o), mContext));
        subList.add(subscription);
    }


    /**
     * 修改密码
     */
    public void upDatePasswold(String oldPwd, String newPwd) {
        Subscription subscription = mModel.upDatePasswold(new ProgressSubscriber(o -> ((IUpDatePasswoldView) mView).onResult((Object) o), mContext), oldPwd, newPwd);
        subList.add(subscription);
    }

    /**
     * 修改玩家昵称
     */
    public void upDateNickName(String nickname) {
        Subscription subscription = mModel.upDateNickName(new ProgressSubscriber(o -> ((IUserInfoMation) mView).onResultNickName((Object) o), mContext), nickname);
        subList.add(subscription);
    }

    /**
     * 修改玩家真实姓名
     */
    public void upDateRealName(String realName) {
        Subscription subscription = mModel.upDateRealName(new ProgressSubscriber(o -> ((IUserInfoMation) mView).onResultNickName((Object) o), mContext), realName);
        subList.add(subscription);
    }


    /**
     * 修改安全密碼
     */
    public void IUpdatePermissionPassword(String upDatePermissionPassword) {
        Subscription subscription = mModel.upDatePermissionPassword(new ProgressSubscriber(o -> ((IUpdatePermissionPasswordView) mView).onResult((InfoMation) o), mContext), upDatePermissionPassword);
        subList.add(subscription);
    }

    /**
     * 校验安全密碼
     */
    public void qureyPermissionPassword(String upDatePermissionPassword) {
        Subscription subscription = mModel.quirePermissionPassword(new ProgressSubscriber(o -> ((IUpdatePermissionPasswordView) mView).onResult((InfoMation) o), mContext), upDatePermissionPassword);
        subList.add(subscription);
    }

    /**
     * 合并Ｕser和Setting,my bank card的请求
     */
    public void getUserInfoWithSystemSetting() {
        Subscription subscription = mModel.getUserInfoWithSystemSetting(new ProgressSubscriber(o -> ((IMCenterFragmentView) mView).onInfoResult(o), mContext));
        subList.add(subscription);
    }


    public void getCreateOALink() {
        Subscription subscription = mModel.getCreateAccocuntLink(new ProgressSubscriber(o -> ((IOALinklView) mView).onLinkResult((OALinkBean) o), mContext));
        subList.add(subscription);
    }


    @Override
    public void onDestory() {
        super.onDestory();
    }
}
