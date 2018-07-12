package com.dawoo.lotterybox.view.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IUpUerRealNameView;
import com.dawoo.lotterybox.mvp.view.IUserInfoMation;
import com.dawoo.lotterybox.util.RexUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;

import butterknife.BindView;

/**
 * @author jack
 * @date 18-2-9
 * 昵称界面
 */

public class NickNameActivity extends BaseActivity implements IUserInfoMation {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.nicknane_et)
    EditText nicknaneEt;
    @BindView(R.id.bt_submit)
    Button updatename;
    @BindView(R.id.tv_title_show)
    TextView tvTitleShow;
    private Context context;
    private UserPresenter userPresenter;
    /**
     * 0 是修改昵称
     * １　是修改玩家真实姓名
     */
    private int type = 0;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_nickname);
        context = this;
        type = getIntent().getIntExtra("type", 0);
    }


    @Override
    protected void initViews() {

        userPresenter = new UserPresenter<IUserInfoMation>(context, this);
        if (type == 0) {
            headView.setHeader(getResources().getString(R.string.updata_nickname), true);
            nicknaneEt.setHint("请勿以真实姓名/手机号作为昵称(2到15个字符)");
            tvTitleShow.setText("给自己取一个喜欢的昵称");
        } else if (type == 1) {
            headView.setHeader(getString(R.string.update_real_name), true);
            nicknaneEt.setHint("请输入真实姓名，否则将影响提现。");
            tvTitleShow.setText("输入你的真实姓名");
        }
    }

    @Override
    protected void onDestroy() {
        userPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected void initData() {
        updatename.setOnClickListener(v -> {
            String nickname = nicknaneEt.getText().toString();
            if ("".equals(nickname)){
                ToastUtils.showShort("输入为空");
                return;
            }
            if (!RexUtils.isNickName(nickname)){
                ToastUtils.showShort("昵称错误，请重新输入");
                return;
            }
            if (type == 0) {
                userPresenter.upDateNickName(nickname);
            } else if (type == 1) {
                userPresenter.upDateRealName(nickname);
            }


        });
    }

    @Override
    public void onResultNickName(Object o) {
        if (type == 0) {
            User user = DataCenter.getInstance().getUser();
            String nickName = nicknaneEt.getText().toString();
            user.setNickname(nickName);
            RxBus.get().post(ConstantValue.EVENT_UPDATE_NICKNAME, nickName);
        } else if (type == 1) {
            User user = DataCenter.getInstance().getUser();
            String realName = nicknaneEt.getText().toString();
            user.setRealName(realName);
            RxBus.get().post(ConstantValue.EVENT_UPDATE_REAL_NAMW, realName);
        }

        finish();

    }


}
