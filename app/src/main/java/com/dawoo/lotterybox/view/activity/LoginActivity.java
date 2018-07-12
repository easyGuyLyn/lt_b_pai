package com.dawoo.lotterybox.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.util.activity.KeyboardUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.LoginBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.ILoginView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.util.SharePreferenceUtil;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 * Created by benson on 17-12-21.
 */

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.user_pwd_iv)
    ImageView mUserPwdIv;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.input_type_iv)
    ImageView mInputTypeIv;
    @BindView(R.id.forget_pwd_tv)
    TextView mForgetPwdTv;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.tv_head)
    TextView mCopyRightTv;
    @BindView(R.id.password_switch)
    Switch mPasswordSwitch;
    private UserPresenter mPresenter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.title_name_activity_login), true);
        mNameEt.setText(SPUtils.getInstance().getString("username"));
        mNameEt.setSelection(mNameEt.getText().toString().trim().length());
    }

    @Override
    protected void initData() {
        mCopyRightTv.setText(ConstantValue.COPY_RIGHT);
        mPresenter = new UserPresenter<>(this, this);
        if (TextUtils.isEmpty(SharePreferenceUtil.getPassWord(this))) {
            mPasswordSwitch.setChecked(false);
        } else {
            mPasswordSwitch.setChecked(true);
            mPwdEt.setText(SharePreferenceUtil.getPassWord(this));
        }
        mPasswordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPasswordSwitch.setChecked(isChecked);
            }
        });
    }

    private void saveOrCleanPwd() {
        if (mPasswordSwitch.isChecked()) {
            SharePreferenceUtil.savePassWord(this, mPwdEt.getText().toString());
        } else {
            SharePreferenceUtil.savePassWord(this, "");
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }


    @Override
    public void onLoginResult(LoginBean loginBean) {
        if (loginBean != null) {
            String name = mNameEt.getText().toString().trim();
            String pwd = mPwdEt.getText().toString().trim();
            SPUtils.getInstance().put(SPConfig.USERNAME, name);
            SPUtils.getInstance().put(SPConfig.PASSWORD, pwd);
            User user = new User();
            user.setUsername(name);
            user.setLogin(true);
            user.setToken(loginBean.getToken());
            user.setRefreshToken(loginBean.getRefreshToken());
            user.setExpire(loginBean.getExpire());
            user.setPassword(pwd);
            user.setAvatarUrl(mPresenter.getHeadIcon());
            DataCenter.getInstance().setUser(user);
            RxBus.get().post(ConstantValue.EVENT_TYPE_LOGINED, "login");
            saveOrCleanPwd();
            finish();
        }
    }

    @Override
    public void doLogin() {
        KeyboardUtil.hideInputKeyboard(this);
        String name = mNameEt.getText().toString().trim();
        String pwd = mPwdEt.getText().toString().trim();
        String appKey = getResources().getString(R.string.app_key);
        String appSecret = getResources().getString(R.string.app_secret);
        String serialNo = DataCenter.getInstance().getSysInfo().getMac();
        mPresenter.login(name, pwd, appKey, appSecret, serialNo);
    }

    @Override
    public void doPwdToggle() {
        if (mInputTypeIv.isSelected()) {
            mInputTypeIv.setSelected(false);
            mPwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            mInputTypeIv.setSelected(true);
            mPwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
        mPwdEt.setSelection(mPwdEt.getText().length());
        KeyboardUtil.hideInputKeyboard(this);
    }


    @OnClick({R.id.input_type_iv, R.id.forget_pwd_tv, R.id.login_btn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.input_type_iv:
                doPwdToggle();
                break;
            case R.id.forget_pwd_tv:
                ActivityUtil.gotoCustomerService();
                break;
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.register_btn:
                startRegisterActivity();
                break;
            default:
        }
    }

    private void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra(RegisterActivity.MODE, RegisterActivity.MODE_STANDAR);
        startActivity(intent);
    }

    String msg = "";

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION))
    public void onReturnError(String message) {
        if (msg.equals(message)) {
        } else {
            ToastUtils.showShort(message);
            msg = message;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
