package com.dawoo.lotterybox.view.activity.setting;


import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IUpDatePasswoldView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.RexUtils;
import com.dawoo.lotterybox.util.RxUtils;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.LoginActivity;
import com.dawoo.lotterybox.view.activity.SecurityCenterActivity;
import com.dawoo.lotterybox.view.activity.UserInforMationActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-11
 * 更改密碼
 */

public class UpdatePasswordActivity extends BaseActivity implements IUpDatePasswoldView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.input_old_psw)
    EditText inputOldPsw;
    @BindView(R.id.input_new_psw)
    EditText inputNewPsw;
    @BindView(R.id.confirm_new_psw)
    EditText confirmNewPsw;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.iv_old_PWD)
    ImageView ivOldPWD;
    @BindView(R.id.iv_new_PWD)
    ImageView ivNewPWD;
    @BindView(R.id.iv_new_twice_PWD)
    ImageView ivNewTwicePWD;
    private Context context;
    private UserPresenter userPresenter;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_forgetpassworld);
        context = this;

    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.confirm_password), true);
        RxBus.get().register(this);
        userPresenter = new UserPresenter<IUpDatePasswoldView>(context, this);
    }

    @Override
    protected void initData() {
        processState(ivOldPWD, inputOldPsw);
        processState(ivNewPWD, inputNewPsw);
        processState(ivNewTwicePWD, confirmNewPsw);
        ViewUtils.noSpaceEditText(inputNewPsw);
        ViewUtils.noSpaceEditText(confirmNewPsw);


    }

    private void processState(ImageView imageView, EditText editText) {
        imageView.setOnClickListener(v -> {
            if (imageView.isSelected()) {
                imageView.setSelected(false);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                imageView.setSelected(true);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            }
            editText.setSelection(editText.getText().length());
        });
    }



    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        userPresenter.onDestory();
        super.onDestroy();
    }

    @OnClick({R.id.bt_submit, R.id.tv_forgetpassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.input_old_psw:

                break;
            case R.id.input_new_psw:
                break;
            case R.id.confirm_new_psw:
                break;
            case R.id.bt_submit:
                String oldpsw = inputOldPsw.getText().toString().trim();
                String newpaw = inputNewPsw.getText().toString().trim();
                String configpsw = confirmNewPsw.getText().toString().trim();
                if ("".equals(oldpsw)) {
                    showMsg("原密码不能为空");
                    return;
                }
                if ("".equals(newpaw)) {
                    showMsg("新密码不能为空");
                    return;
                }
                if ("".equals(configpsw)) {
                    showMsg("确认密码不能为空");
                    return;
                }
                if (!oldpsw.equals(SPUtils.getInstance().getString(SPConfig.PASSWORD))) {
                    showMsg("原密码不正确");
                    return;
                }

                if (!newpaw.equals(configpsw)) {
                    showMsg("两次密码输入不一致");
                    return;
                }
                if (oldpsw.equals(newpaw)) {
                    showMsg("新密码不能和原密码相同");
                    return;
                }
                if (!RexUtils.isPassWord(oldpsw)){
                    showMsg("密码格式错误");
                    return;
                }
                LogUtils.e(oldpsw);
                LogUtils.e(newpaw);
                userPresenter.upDatePasswold(oldpsw, newpaw);
                break;
            //忘记密码
            case R.id.tv_forgetpassword:
                ActivityUtil.gotoCustomerService();
                break;
            default:
        }
    }


    private void showMsg(String msg) {
        MSPropties.showMeg(context, msg);
    }

    @Override
    public void onResult(Object o) {
        MSPropties.showMeg(this, "修改密码成功，请重新登录");
        //通知其他界面登出
        DataCenter.getInstance().getUser().setLogin(false);
        RxBus.get().post(ConstantValue.EVENT_TYPE_LOGOUT, "logout");
        startActivity(new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        ActivityUtils.finishActivity(UserInforMationActivity.class);
        ActivityUtils.finishActivity(SecurityCenterActivity.class);
        ActivityUtils.finishActivity(SettingActivity.class);
        finish();
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
}
