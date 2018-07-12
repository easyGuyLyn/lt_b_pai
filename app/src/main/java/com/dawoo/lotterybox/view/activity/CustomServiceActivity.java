package com.dawoo.lotterybox.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack on 18-2-8.
 * 客服界面
 */

public class CustomServiceActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.custom_weixin)
    RelativeLayout customWeixin;
    @BindView(R.id.custom_qq)
    RelativeLayout customQq;
    @BindView(R.id.custom_one)
    RelativeLayout customOne;
    private Context context;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_customservice);
        context = this;
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.custom_contans), true);
    }

    @Override
    protected void initData() {

    }



    @OnClick({R.id.head_view, R.id.textView2, R.id.custom_weixin, R.id.custom_qq, R.id.custom_one})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.textView2:
                break;
            //微信
            case R.id.custom_weixin:
                showMsg("微信");
                break;
            //qq
            case R.id.custom_qq:
                showMsg("qq");
                break;
            //客服一
            case R.id.custom_one:
                showMsg("客服一");
                break;
            default:
        }
    }

    private void showMsg(String msg) {
        ToastUtil.showResLong(context, msg);
    }
}
