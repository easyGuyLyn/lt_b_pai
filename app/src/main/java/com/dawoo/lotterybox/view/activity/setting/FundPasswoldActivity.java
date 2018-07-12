package com.dawoo.lotterybox.view.activity.setting;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.InfoMation;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IUpdatePermissionPasswordView;
import com.dawoo.lotterybox.util.PWKeyboardManager;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.PasswordInputView;
import com.dawoo.lotterybox.view.view.VirtualKeyboardView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jack on 18-2-11.
 * 资金密码
 *
 * @author alex
 */

public class FundPasswoldActivity extends BaseActivity implements IUpdatePermissionPasswordView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.trader_pwd_set_pwd_edittext)
    PasswordInputView traderPwdSetPwdEdittext;
    @BindView(R.id.virtualKeyboardView)
    VirtualKeyboardView virtualKeyboardView;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    private int type;
    private Context context;
    private String oldSSCode;
    private PWKeyboardManager pwKeyboardManager;
    private UserPresenter userPresenter;
    private int inputTime = -1;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_funding_passwold);
        context = this;
        RxBus.get().register(this);

    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.funding_passwold), true);
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {

            tvNotice.setText("您已设置过安全密码，请验证以修改密码");

        } else {
            //设置过安全密码
            tvNotice.setText(getString(R.string.funding_passwold_text));
            inputTime = 0;

        }
        userPresenter = new UserPresenter<IUpdatePermissionPasswordView>(this, this);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        userPresenter = new UserPresenter<>(this, this);
        /**
         * 如果账户之前设置了安全密码 先进行校验
         * 根据账户信息里面返回的字段进行判断
         * 如果没有直接调接口
         */
        pwKeyboardManager = new PWKeyboardManager(this, virtualKeyboardView, traderPwdSetPwdEdittext, number -> {
            pwKeyboardManager.hideKeyborad();
            LogUtils.e(">>>>", number);
            if (type == 0) {
                //教研安全密码
                userPresenter.qureyPermissionPassword(number);
            } else {
                if (inputTime == 0) {
                    //第一次输入
                    tvNotice.setText("请再次输入已确认");
                    oldSSCode = number;
                    pwKeyboardManager.clearData();
                    inputTime++;
                    pwKeyboardManager.showKeyborad();
                } else if (inputTime == 1) {
                    //输入两次开始校验
                    if (oldSSCode.equals(number)) {
                        userPresenter.IUpdatePermissionPassword(number);
                    } else {
                        pwKeyboardManager.clearData();
                        ToastUtils.showShort("两次输入不相同请重新输入");
                        pwKeyboardManager.showKeyborad();
                        tvNotice.setText(getString(R.string.funding_passwold_text));
                        inputTime = 0;
                    }
                }

            }
        });

    }

    @Override
    public void onResult(InfoMation infoMation) {
        //设置了手势密码
        if (type == 0) {
            //验证成功
            ToastUtils.showShort("资金密码验证成功");
            pwKeyboardManager.clearData();
            pwKeyboardManager.showKeyborad();
            type = 1;
            tvNotice.setText(getString(R.string.funding_passwold_text));
            inputTime = 0;
        } else {
            RxBus.get().post(ConstantValue.EVENT_SET_SS_CODE, "资金密码设置成功");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
        userPresenter.onDestory();
    }
    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
        pwKeyboardManager.clearData();
        pwKeyboardManager.showKeyborad();
    }


}
