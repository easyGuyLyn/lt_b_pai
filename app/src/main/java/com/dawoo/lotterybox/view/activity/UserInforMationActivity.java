package com.dawoo.lotterybox.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.activity.ActivityStackManager;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.ISignOutView;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.McGlideEngine;
import com.dawoo.lotterybox.view.activity.setting.AvatarChooseActivity;
import com.dawoo.lotterybox.view.activity.setting.LockActivity;
import com.dawoo.lotterybox.view.activity.setting.FundPasswoldActivity;
import com.dawoo.lotterybox.view.activity.setting.NickNameActivity;
import com.dawoo.lotterybox.view.activity.setting.UpdatePasswordActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.SettingService;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author jack
 * @date 18-2-8
 * 用戶信息中心
 */

public class UserInforMationActivity extends BaseActivity implements ISignOutView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.mine_avatar)
    CircleImageView mine_avatar;
    @BindView(R.id.account_name)
    RelativeLayout accountName;
    @BindView(R.id.account_account)
    TextView accountAccount;
    @BindView(R.id.account_ID)
    TextView accountID;
    @BindView(R.id.account_name_tv)
    TextView accountNameTv;
    @BindView(R.id.account_funding_passwold)
    RelativeLayout accountFundingPasswold;
    @BindView(R.id.account_lock_screen_gesture)
    RelativeLayout accountLockScreenGesture;
    @BindView(R.id.account_device_lock_open)
    ImageView accountDeviceLockOpen;
    @BindView(R.id.account_device_lock_close)
    ImageView accountDeviceLockClose;
    @BindView(R.id.logout)
    Button login;
    @BindView(R.id.forget_pwd_rv)
    RelativeLayout forGetpwdRv;
    @BindView(R.id.mine_avatar_rv)
    RelativeLayout mine_avatar_rv;
    public static int REQUEST_CODE_CHOOSE = 200;
    @BindView(R.id.tv_set_ss_code)
    TextView tvSetSsCode;
    @BindView(R.id.tv_set_gesture_code)
    TextView tvSetGestureCode;
    @BindView(R.id.go_real_change)
    ImageView goRealChange;
    private UserPresenter mUserPresenter;

    private Context context;
    private boolean hasSecPwd;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_usermation);
        context = this;
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.account_title), true);
        mUserPresenter = new UserPresenter<ISignOutView>(context, this);
        hasSecPwd = DataCenter.getInstance().getUser().isHasSecPwd();
        String ssString = hasSecPwd ? "修改" : "设置";
        tvSetSsCode.setText(ssString);
        boolean isGestrue = SPUtils.getInstance().getString(SPConfig.DEVICE_CODE).length() == 0;
        String gestrueString = isGestrue ? "修改" : "设置";
        tvSetGestureCode.setText(gestrueString);
        setData();
    }

    private void setData() {
        User user = DataCenter.getInstance().getUser();
        accountAccount.setText(user.getUsername());
        accountNameTv.setText(user.getNickname());
        accountID.setText(user.getRealname());
        if (!TextUtils.isEmpty(user.getRealname())) {
            goRealChange.setVisibility(View.INVISIBLE);
        }
        setHead();
    }

    void setHead() {
        String url = DataCenter.getInstance().getUser().getAvatarUrl();
        int headIcon = PackageInfoUtil.getResource(BoxApplication.getContext(), url);
        Glide.with(this).load(headIcon).into(mine_avatar);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        mUserPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.logout, R.id.mine_avatar, R.id.account_name, R.id.account_account,
            R.id.rl_real_name, R.id.account_funding_passwold,
            R.id.account_lock_screen_gesture, R.id.account_device_lock_open, R.id.forget_pwd_rv,
            R.id.account_device_lock_close, R.id.mine_avatar_rv})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_avatar_rv:
                ActivityUtils.startActivity(AvatarChooseActivity.class);
               // choosePhoto();
                break;
            case R.id.mine_avatar:
                ActivityUtils.startActivity(AvatarChooseActivity.class);
               // choosePhoto();
                break;
            case R.id.account_name:
                startActivity(new Intent(context, NickNameActivity.class).putExtra("type", 0));
                break;
            case R.id.account_account:

                break;
            case R.id.rl_real_name:
                if (DataCenter.getInstance().getUser().getRealname()!=null) {
                    ActivityUtil.gotoCustomerService();
                }else {
                    //真实姓名
                    startActivity(new Intent(context, NickNameActivity.class).putExtra("type", 1));
                }

                break;
            case R.id.account_funding_passwold:
                if (hasSecPwd) {
                    //验证原密码
                    startActivity(new Intent(UserInforMationActivity.this, FundPasswoldActivity.class).putExtra("type", 0));
                } else {
                    //设置密码
                    startActivity(new Intent(UserInforMationActivity.this, FundPasswoldActivity.class).putExtra("type", 1));
                }

                break;
            case R.id.account_lock_screen_gesture:
                //设置锁屏手势
                Intent intent = new Intent(UserInforMationActivity.this, LockActivity.class);
                if (SPUtils.getInstance().getString(SPConfig.DEVICE_CODE).length() != 0) {
                    intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.RESET_LOCK);
                } else {

                    intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.SET_LOCK);
                }
                startActivity(intent);
                break;
            case R.id.account_device_lock_open:
                accountDeviceLockOpen.setVisibility(View.GONE);
                accountDeviceLockClose.setVisibility(View.VISIBLE);
                break;
            case R.id.account_device_lock_close:
                accountDeviceLockOpen.setVisibility(View.VISIBLE);
                accountDeviceLockClose.setVisibility(View.GONE);
                break;
            case R.id.logout:
                MSPropties.signOut(mUserPresenter, context);
                break;
            case R.id.forget_pwd_rv:
                MSPropties.startActivity(UpdatePasswordActivity.class);
                break;
            default:
        }
    }

    private void choosePhoto() {
        AndPermission
                .with(UserInforMationActivity.this)
                .permission(Permission.CAMERA,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .rationale((context, permissions, executor) -> {
                    SettingService settingService = AndPermission.permissionSetting(UserInforMationActivity.this);
                    settingService.execute();
                    settingService.cancel();
                })
                .onGranted(permissions -> {
                    Matisse.from(UserInforMationActivity.this)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.dawoo.lotterybox.fileprovider"))
                            .maxSelectable(1)
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.text_120_sp))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.85f)
                            .imageEngine(new McGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                }).onDenied(permissions -> {

        }).start();
    }

    private void showMes(String msg) {
        ToastUtil.showToastShort(context, msg);
    }

    /**
     * 用戶登出
     *
     * @param o
     */
    @Override
    public void onResult(Object o) {
        DataCenter.getInstance().clearUser();
        RxBus.get().post(ConstantValue.EVENT_TYPE_LOGOUT, "logout");
        ActivityStackManager.getInstance().finishToActivity(MainActivity.class, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 1 && requestCode == 1 && data != null) {

            accountNameTv.setText(data.getStringExtra("accountName"));
        } else if (data != null) {
            List<Uri> uris = Matisse.obtainResult(data);
            RxBus.get().post(ConstantValue.EVENT_UPDATE_AVATER, uris.get(0));
            Glide.with(UserInforMationActivity.this).load(uris.get(0)).into(mine_avatar);

        }

    }

    /**
     * 设置了手势密码
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_SET_SS_CODE)})
    public void updateSS(Uri uri) {
        tvSetSsCode.setText("修改");
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_NICKNAME)})
    public void updateNiceName(String uri) {
        accountNameTv.setText(uri);
    }


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_REAL_NAMW)})
    public void updateRealName(String uri) {
        goRealChange.setVisibility(View.INVISIBLE);
        accountID.setText(uri);
    }
    /**
     * 修改头像
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_AVATER)})
    public void updateAvater(String uri) {
        Glide.with(UserInforMationActivity.this).load(PackageInfoUtil.getResource(BoxApplication.getContext(),uri)).into(mine_avatar);
    }

}
