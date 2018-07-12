package com.dawoo.lotterybox.view.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 *         意见反馈
 */

public class FeedBackNextActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.flash_back)
    TextView flashBack;
    @BindView(R.id.black_screen)
    TextView blackScreen;
    @BindView(R.id.crashed)
    TextView crashed;
    @BindView(R.id.interface_dislocation)
    TextView interfaceDislocation;
    private Context context;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_feekback_next);
        context = this;
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.feekback_next), true);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.flash_back, R.id.caton, R.id.black_screen, R.id.crashed, R.id.interface_dislocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //卡顿
            case R.id.caton:
                goNext("caton");
                break;
            //卡顿
            case R.id.flash_back:
                goNext("crash");
                break;
            //黑屏
            case R.id.black_screen:
                goNext("black");
                break;
            //死机
            case R.id.crashed:
                goNext("halted");
                break;
            //界面错位
            case R.id.interface_dislocation:
                goNext("ui");
                break;

            default:
        }

    }

    private void goNext(String type) {
        startActivity(new Intent(FeedBackNextActivity.this, FeekBackSubmitActivity.class).putExtra("type", type));
    }



}
