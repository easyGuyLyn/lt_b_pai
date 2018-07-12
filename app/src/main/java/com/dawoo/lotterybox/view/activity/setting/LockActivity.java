package com.dawoo.lotterybox.view.activity.setting;

import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.LoginBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.ILoginView;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.List;

import butterknife.BindView;

/**
 * Created by alex on 18-3-20.
 *
 * @author alex
 *         设备锁 调用时传递对应的类型
 *         0 表示设置设备锁
 *         1 表示启用设备锁
 *         2 重新设置设备锁
 */

public class LockActivity extends BaseActivity implements PatternLockViewListener, ILoginView {
    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.patter_lock_view)
    PatternLockView patterLockView;
    public static final String LOCK_TYPE = "lock_type";
    public static final int SET_LOCK = 0;
    public static final int UN_LOCK = 1;
    public static final int RESET_LOCK = 2;
    private int type;
    private int inPutTime = 0;
    private UserPresenter<LockActivity> lockActivityUserPresenter;

    @Override
    protected void createLayoutView() {
        RxBus.get().register(this);
        setContentView(R.layout.activity_lock);


    }

    @Override
    protected void initViews() {
        patterLockView.addPatternLockListener(this);
        leftBtn.setOnClickListener(v -> finish());

    }


    @Override
    protected void initData() {
        type = getIntent().getIntExtra(LOCK_TYPE, 0);
        switch (type) {
            case SET_LOCK:
                profileName.setText(R.string.lock_setting_code);
                break;
            case UN_LOCK:
                leftBtn.setVisibility(View.GONE);
                profileName.setText(R.string.lock_welcomne);
                break;
            case RESET_LOCK:
                profileName.setText(R.string.set_before_code);
                break;
            default:
        }
    }

    //手势密码回调
    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {
        // profileName.setText(progressPattern.size() + "");

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        if (pattern.size() < 6) {
            patterLockView.clearPattern();
            ToastUtils.showLong(getResources().getString(R.string.diot_must_more_than_six));
        } else {
            if (type == SET_LOCK) {
                if (inPutTime == 1) {
                    String firstCode = SPUtils.getInstance().getString(SPConfig.FIRST_CODE);
                    String secondCode = PatternLockUtils.patternToSha1(patterLockView, pattern);
                    if (firstCode.equals(secondCode)) {
                        //两次验证成功
                        SPUtils.getInstance().put(SPConfig.DEVICE_CODE, secondCode);
                        SPUtils.getInstance().put(SPConfig.IS_OPEN_CODE, true);
                        ToastUtils.showShort(getResources().getString(R.string.setting_succeed));
                        finish();
                    } else {
                        //两次不一致重置
                        inPutTime = 0;
                        patterLockView.clearPattern();
                        profileName.setText(R.string.lock_setting_code);
                        ToastUtils.showShort(getResources().getString(R.string.please_reset));
                    }
                } else {
                    SPUtils.getInstance().put(SPConfig.FIRST_CODE, PatternLockUtils.patternToSha1(patterLockView, pattern));
                    patterLockView.clearPattern();
                    inPutTime++;
                    profileName.setText(R.string.lock_code_plz_commit);
                }

            } else if (type == UN_LOCK) {
                //验证成功
                String deviceCode = SPUtils.getInstance().getString(SPConfig.DEVICE_CODE);
                String commitCode = PatternLockUtils.patternToSha1(patterLockView, pattern);
                if (deviceCode.equals(commitCode)) {
                    ToastUtils.showShort(R.string.lock_commit_succeed);
                    if (DataCenter.getInstance().getUser().isLogin()) {
                        finish();
                    } else {
                        lockActivityUserPresenter = new UserPresenter<>(LockActivity.this, this);
                        doLogin();
                    }


                } else {
                    patterLockView.clearPattern();
                    ToastUtils.showShort(getResources().getString(R.string.lock_code_error));
                }

            } else if (type == RESET_LOCK) {
                String deviceCode = SPUtils.getInstance().getString(SPConfig.DEVICE_CODE);
                String commitCode = PatternLockUtils.patternToSha1(patterLockView, pattern);
                if (deviceCode.equals(commitCode)) {
                    type = SET_LOCK;
                    profileName.setText(R.string.lock_setting_code);
                    patterLockView.clearPattern();
                } else {

                    ToastUtils.showShort(getResources().getString(R.string.lock_code_error));
                }

            } else {
                finish();

            }


        }
    }

    @Override
    public void onCleared() {

    }


    @Override
    public void onLoginResult(LoginBean loginBean) {
        if (loginBean != null) {
            User user = new User();
            user.setLogin(true);
            user.setToken(loginBean.getToken());
            user.setRefreshToken(loginBean.getRefreshToken());
            user.setExpire(loginBean.getExpire());
            DataCenter.getInstance().setUser(user);
            RxBus.get().post(ConstantValue.EVENT_TYPE_LOGINED, "login");
            finish();
        }
    }

    @Override
    public void doLogin() {
        String name = SPUtils.getInstance().getString(SPConfig.USERNAME);
        String pwd = SPUtils.getInstance().getString(SPConfig.PASSWORD);
        String appKey = getResources().getString(R.string.app_key);
        String appSecret = getResources().getString(R.string.app_secret);
        String serialNo = DataCenter.getInstance().getSysInfo().getMac();
        lockActivityUserPresenter.login(name, pwd, appKey, appSecret, serialNo);

    }

    @Override
    public void doPwdToggle() {

    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION))
    public void onError(String message) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        if (lockActivityUserPresenter != null) {
            lockActivityUserPresenter.onDestory();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (type == UN_LOCK) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
        }
        return false;
    }
}
