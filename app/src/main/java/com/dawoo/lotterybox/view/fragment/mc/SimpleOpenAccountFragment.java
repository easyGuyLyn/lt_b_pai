package com.dawoo.lotterybox.view.fragment.mc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IRegisterView;
import com.dawoo.lotterybox.util.BottomDialogUtils;
import com.dawoo.lotterybox.util.RexUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;


import butterknife.BindView;

/**
 * Created by alex on 18-4-21.
 *
 * @author alex
 */
@BindLayout( R.layout.fragment_simple_open_account)
public class SimpleOpenAccountFragment extends SuperBaseFragment implements IRegisterView {
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_open_accout)
    RelativeLayout rlOpenAccout;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_open_account)
    TextView tvOpenAccount;
    private UserPresenter<SimpleOpenAccountFragment> simpleOAPresenter;

    public static SimpleOpenAccountFragment newInstance() {
        Bundle args = new Bundle();
        SimpleOpenAccountFragment fragment = new SimpleOpenAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        RxBus.get().register(this);
        rlOpenAccout.setOnClickListener(v -> BottomDialogUtils.with(getActivity())
                .showSelectDialog("请选择开户类别", "代理".equals(tvType.getText().toString()), select -> tvType.setText(select)));

    }


    @Override
    protected void initData() {
        simpleOAPresenter = new UserPresenter<>(getActivity(), this);
        tvOpenAccount.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                String account = etAccount.getText().toString().trim();
                String code = SimpleOpenAccountFragment.this.etCode.getText().toString().trim();
                if ("".equals(account)){
                    ToastUtils.showShort("账户不能为空");
                    return;
                }
                if ("".equals(code)){
                    ToastUtils.showShort("密码不能为空");
                    return;
                }
                if (!RexUtils.isAccount(account)){
                    ToastUtils.showShort("账户格式错误");
                    return;
                }
                if (!RexUtils.isPassWord(code)){
                    ToastUtils.showShort("密码格式错误");
                    return;
                }

                simpleOAPresenter.createAccount(account, code, code, "4", "代理".equals(tvType.getText().toString()) ? "agent" : "member", DataCenter.getInstance().getUser().getUserId()+"");

            }
        });

    }

    @Override
    protected void superLoadData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BottomDialogUtils.with(getActivity()).destory();
        RxBus.get().unregister(this);
        simpleOAPresenter.onDestory();
    }

    @Override
    public void onRigsterResult(boolean isSuccess) {
        if (isSuccess){
            ToastUtils.showShort("开户成功");
            RxBus.get().post(ConstantValue.EVENT_SIMPLE_OA_SUCCEED,"开户成功");
        }else {
            ToastUtils.showShort("开户失败");
        }

    }

    @Override
    public void doRigster() {

    }

    @Override
    public void doPwdToggle() {

    }

    @Override
    public void doConPwdToggle() {

    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION))
    public void onReturnError(String message) {
        ToastUtils.showShort(message);
    }


}
