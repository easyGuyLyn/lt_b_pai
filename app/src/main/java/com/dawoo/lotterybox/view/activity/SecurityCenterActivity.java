package com.dawoo.lotterybox.view.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.util.GetPhoneMessage;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.SharePreferenceUtil;
import com.dawoo.lotterybox.view.activity.setting.LockActivity;
import com.dawoo.lotterybox.view.activity.setting.FundPasswoldActivity;
import com.dawoo.lotterybox.view.activity.setting.UpdatePasswordActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.apache.http.util.TextUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jack on 18-2-9.
 * 安全中心
 */

public class SecurityCenterActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.securtycentr_name)
    TextView securtycentrName;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.account_name)
    TextView accountName;
    @BindView(R.id.account_change_passwold)
    RelativeLayout accountChangePasswold;
    @BindView(R.id.securitycenter_funding_password)
    RelativeLayout securitycenterFundingPassword;
    @BindView(R.id.securitycenter_lock_screen_gesture)
    RelativeLayout securitycenterLockScreenGesture;
    @BindView(R.id.securitycenter_device_lock_open)
    ImageView securitycenterDeviceLockOpen;
    @BindView(R.id.securitycenter_device_lock_close)
    ImageView securitycenterDeviceLockClose;
    @BindView(R.id.securitycenter_device_lock)
    RelativeLayout securitycenterDeviceLock;
    @BindView(R.id.tv_ss_code)
    TextView tvSsCode;
    @BindView(R.id.tv_set_gesture_code)
    TextView tvSetGestureCode;
    private Context context;
    private boolean hasSecPwd;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_securtycenter);
        context = this;
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.securitycenter), true);
        hasSecPwd = DataCenter.getInstance().getUser().isHasSecPwd();
        String ssString = hasSecPwd ? "修改" : "设置";
        tvSsCode.setText(ssString);
        boolean isGestrue = SPUtils.getInstance().getString(SPConfig.DEVICE_CODE).length() == 0;
        String gestrueString = isGestrue ? "修改" : "设置";
        tvSetGestureCode.setText(gestrueString);
    }

    @Override
    protected void initData() {
        securtycentrName.setText("亲爱的："+DataCenter.getInstance().getUser().getUsername());
    }

    @OnClick({R.id.account_name, R.id.account_change_passwold, R.id.securitycenter_funding_password, R.id.securitycenter_lock_screen_gesture, R.id.securitycenter_device_lock_open, R.id.securitycenter_device_lock_close, R.id.securitycenter_device_lock})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_name:
                break;
            case R.id.account_change_passwold:
                startActivity(new Intent(context, UpdatePasswordActivity.class));
                break;
            //资金密码
            case R.id.securitycenter_funding_password:
                if (hasSecPwd) {
                    //验证原密码
                    startActivity(new Intent(SecurityCenterActivity.this, FundPasswoldActivity.class).putExtra("type", 0));
                } else {
                    //设置密码
                    startActivity(new Intent(SecurityCenterActivity.this, FundPasswoldActivity.class).putExtra("type", 1));
                }

                break;
            case R.id.securitycenter_lock_screen_gesture:
                Intent intent = new Intent(SecurityCenterActivity.this, LockActivity.class);
                if (SPUtils.getInstance().getString(SPConfig.DEVICE_CODE).length() != 0) {
                    intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.RESET_LOCK);
                } else {
                    //TODO:未设置提示设置
                    intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.SET_LOCK);
                }
                startActivity(intent);
                break;
            case R.id.securitycenter_device_lock_open:
                break;
            case R.id.securitycenter_device_lock_close:
                break;
            case R.id.securitycenter_device_lock:
                AndPermission.with(this)
                        .permission(Permission.READ_PHONE_STATE)
                        .onGranted(permissions -> willSetDeviceLock()).onDenied(permissions -> willSetDeviceLock()).start();

                break;
            default:
        }
    }

    private void willSetDeviceLock() {
        final String deviceId = GetPhoneMessage.getDeviceId(context);
        if (TextUtils.isEmpty(SharePreferenceUtil.getDeviceId())) {
            /**
             * 調接口把设备号传上去　　本地也保存一份　　不一定是這個信息　　　deviceId只是模拟数据  根据需求来
             */
            if (!TextUtils.isEmpty(deviceId)) {
                SharePreferenceUtil.savaDeviceId(context, deviceId);
                Log.e("TAG", "SharePreferenceUtil.getDeviceId()" + SharePreferenceUtil.getDeviceId() + "我是保存的设备号信息");

            } else {
                MSPropties.showMeg(context, "获取设备号失败");
            }
        } else {
            /**
             * 修改绑定设备的状态信息
             */
            MSPropties.showMeg(BoxApplication.getContext(), "暂未保存设备信息");

        }
    }

    /**
     * 设置了安全密码
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_SET_SS_CODE)})
    public void updateSS(String uri) {
        ToastUtils.showShort(uri);
        tvSsCode.setText("修改");
        hasSecPwd=true;
        DataCenter.getInstance().getUser().setHasSecPwd(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
