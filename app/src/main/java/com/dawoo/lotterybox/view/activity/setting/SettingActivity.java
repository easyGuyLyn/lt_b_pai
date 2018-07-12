package com.dawoo.lotterybox.view.activity.setting;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.mvp.presenter.SettingPresenter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.ISignOutView;
import com.dawoo.lotterybox.mvp.view.IUpdateSettingView;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.UserInforMationActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-8
 * 设置界面
 */

public class SettingActivity extends BaseActivity implements ISignOutView, IUpdateSettingView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.setting_account_information)
    TextView settingAccountInformation;
    @BindView(R.id.setting_push_and_remind)
    TextView settingPushAndRemind;
    @BindView(R.id.setting_shake_shake_election_open)
    ImageView settingShakeShakeElectionOpen;
    @BindView(R.id.setting_shake_shake_election_close)
    ImageView settingShakeShakeElectionClose;
    @BindView(R.id.setting_sound_open)
    ImageView settingSoundOpen;
    @BindView(R.id.setting_sound_close)
    ImageView settingSoundClose;
    @BindView(R.id.setting_lock_screen_gesture_open)
    ImageView settingLockScreenGestureOpen;
    @BindView(R.id.setting_lock_screen_gesture_close)
    ImageView settingLockScreenGestureClose;
    @BindView(R.id.setting_winning_animation_open)
    ImageView settingWinningAnimationOpen;
    @BindView(R.id.setting_winning_animation_close)
    ImageView settingWinningAnimationClose;
    @BindView(R.id.logout)
    Button logout;
    private Context context;


    private UserPresenter mUserPresenter;
    private SettingPresenter<IUpdateSettingView> mSettingPresenter;
    private boolean isShake;
    private boolean isVoice;
    private boolean isGesture;
    private boolean isAnimation;

    private boolean isShakeClick;
    private boolean isVoiceClick;
    private boolean isGestureClick;
    private boolean isAnimationClick;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_setting);
        context = this;
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.account_setting), true);
        //获取本地设置
        isShake = "yes".equals(SPUtils.getInstance().getString(SPConfig.SHAKE));
        if (isShake) {
            //开启摇一摇
            openShake();
        } else {
            closeShake();
        }
        isVoice = "yes".equals(SPUtils.getInstance().getString(SPConfig.VOICE));
        if (isVoice) {
            //开启摇一摇
            openVoice();
        } else {
            closeVoice();
        }
        isGesture = "yes".equals(SPUtils.getInstance().getString(SPConfig.GESTURE));
        if (isGesture) {
            //开启摇一摇
            openGesture();

        } else {
            closeGesture();
        }
        isAnimation = "yes".equals(SPUtils.getInstance().getString(SPConfig.ANIMARION));
        if (isAnimation) {
            //开启摇一摇
            openAnimation();
        } else {
            closeAnimation();
        }

    }

    @Override
    protected void initData() {
        mUserPresenter = new UserPresenter<ISignOutView>(context, this);
        mSettingPresenter = new SettingPresenter<>(this, this);

    }


    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        mUserPresenter.onDestory();
        mSettingPresenter.onDestory();
        super.onDestroy();
    }

    @OnClick({R.id.head_view, R.id.setting_account_information, R.id.setting_push_and_remind, R.id.setting_shake_shake_election_open, R.id.setting_shake_shake_election_close, R.id.setting_sound_open, R.id.setting_sound_close, R.id.setting_lock_screen_gesture_open, R.id.setting_lock_screen_gesture_close, R.id.setting_winning_animation_open, R.id.setting_winning_animation_close, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            //账号资料
            case R.id.setting_account_information:
                MSPropties.startActivity(UserInforMationActivity.class);
                break;
            //推送和提醒
            case R.id.setting_push_and_remind:
                MSPropties.startActivity(PushAndremindActvity.class);
                break;
            //摇一摇机选 关闭
            case R.id.setting_shake_shake_election_open:
                isShakeClick = true;
                mSettingPresenter.upDateSystemSetting(getParams(SPConfig.SHAKE, SPConfig.SHAKE, "no"));
                break;
            //摇一摇机选 开
            case R.id.setting_shake_shake_election_close:
                isShakeClick = true;
                mSettingPresenter.upDateSystemSetting(getParams(SPConfig.SHAKE, SPConfig.SHAKE, "yes"));
                break;
            //声音 关闭
            case R.id.setting_sound_open:
                isVoiceClick = true;
                mSettingPresenter.upDateSystemSetting(getParams(SPConfig.VOICE, SPConfig.VOICE, "no"));
                break;
            //声音 开启
            case R.id.setting_sound_close:
                isVoiceClick = true;
                mSettingPresenter.upDateSystemSetting(getParams(SPConfig.VOICE, SPConfig.VOICE, "yes"));
                break;
            //手勢   关闭
            case R.id.setting_lock_screen_gesture_open:
                isGestureClick = true;
                mSettingPresenter.upDateSystemSetting(getParams("lock", SPConfig.GESTURE, "no"));
                break;
            // 手势  开启
            case R.id.setting_lock_screen_gesture_close:
                isGestureClick = true;
                mSettingPresenter.upDateSystemSetting(getParams("lock", SPConfig.GESTURE, "yes"));
                break;
            //中奖动画 关闭
            case R.id.setting_winning_animation_open:
                isAnimationClick = true;
                mSettingPresenter.upDateSystemSetting(getParams("award", SPConfig.ANIMARION, "no"));
                break;
            //中奖动画 开启
            case R.id.setting_winning_animation_close:
                isAnimationClick = true;
                mSettingPresenter.upDateSystemSetting(getParams("award", SPConfig.ANIMARION, "yes"));
                break;
            //退出登录
            case R.id.logout:
                MSPropties.signOut(mUserPresenter, context);
                break;

            default:
        }
    }

    private void openAnimation() {
        settingWinningAnimationOpen.setVisibility(View.VISIBLE);
        settingWinningAnimationClose.setVisibility(View.GONE);
    }

    private void closeAnimation() {
        settingWinningAnimationOpen.setVisibility(View.GONE);
        settingWinningAnimationClose.setVisibility(View.VISIBLE);
    }

    private void openVoice() {
        settingSoundOpen.setVisibility(View.VISIBLE);
        settingSoundClose.setVisibility(View.GONE);
        LogUtils.e(">>>>", "开启声音");
    }

    private void closeVoice() {
        settingSoundOpen.setVisibility(View.GONE);
        settingSoundClose.setVisibility(View.VISIBLE);
        LogUtils.e(">>>>", "关闭声音");
    }

    private void openShake() {
        settingShakeShakeElectionOpen.setVisibility(View.VISIBLE);
        settingShakeShakeElectionClose.setVisibility(View.GONE);
        LogUtils.e(">>>>", "开启摇一摇");
    }

    private void closeShake() {
        settingShakeShakeElectionOpen.setVisibility(View.GONE);
        settingShakeShakeElectionClose.setVisibility(View.VISIBLE);
        LogUtils.e(">>>>", "关闭摇一摇");
    }

    private void openGesture() {
        settingLockScreenGestureOpen.setVisibility(View.VISIBLE);
        settingLockScreenGestureClose.setVisibility(View.GONE);
    }

    private void closeGesture() {
        settingLockScreenGestureOpen.setVisibility(View.GONE);
        settingLockScreenGestureClose.setVisibility(View.VISIBLE);
    }


    /**
     * 用户登出
     *
     * @param o
     */
    @Override
    public void onResult(Object o) {
        DataCenter.getInstance().clearUser();
        RxBus.get().post(ConstantValue.EVENT_TYPE_LOGOUT, "logout");
        MSPropties.showMeg(context, "退出成功");
        finish();
    }

    private Map<String, String> getParams(String paramType, String paramCode, String paramValue) {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", paramType);
        params.put("code", paramCode);
        params.put("value", paramValue);
        return params;
    }

    @Override
    public void onUpdate(Object o) {
        //手势密码
        if (isShakeClick) {
            isShakeClick = false;
            if (isShake) {
                isShake = false;
                closeShake();
                SPUtils.getInstance().put(SPConfig.SHAKE,"no");
            } else {
                isShake = true;
                openShake();
                SPUtils.getInstance().put(SPConfig.SHAKE,"yes");
            }
        }
        if (isGestureClick) {
            isGestureClick = false;
            if (isGesture) {
                isGesture = false;
                closeGesture();
                SPUtils.getInstance().put(SPConfig.GESTURE,"no");
            } else {
                isGesture = true;
                openGesture();
                SPUtils.getInstance().put(SPConfig.GESTURE,"yes");
            }
        }

        if (isAnimationClick) {
            isAnimationClick = false;
            if (isAnimation) {
                isAnimation = false;
                closeAnimation();
                SPUtils.getInstance().put(SPConfig.ANIMARION,"no");
            } else {
                isAnimation = true;
                openAnimation();
                SPUtils.getInstance().put(SPConfig.ANIMARION,"yes");
            }
        }

        if (isVoiceClick) {
            isVoiceClick = false;
            if (isVoice) {
                isVoice = false;
                closeVoice();
                SPUtils.getInstance().put(SPConfig.VOICE,"no");
            } else {
                isVoice = true;
                openVoice();
                SPUtils.getInstance().put(SPConfig.VOICE,"yes");
            }
        }


    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
        if (isShakeClick) {
            isShakeClick = false;
        }

        if (isGestureClick) {
            isGestureClick = false;
        }

        if (isAnimationClick) {
            isAnimationClick = false;
        }

        if (isVoiceClick) {
            isVoiceClick = false;
        }
    }
}
