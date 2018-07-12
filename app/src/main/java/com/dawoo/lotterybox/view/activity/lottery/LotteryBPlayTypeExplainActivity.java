package com.dawoo.lotterybox.view.activity.lottery;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;

/**
 * Created by archar on 18-3-12.
 */

public class LotteryBPlayTypeExplainActivity extends BaseActivity {
    public static final String LT_CODE = "lottery_code";
    public static final String HkLHC = "hklhc";

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.fl_webViewLayout)
    FrameLayout mFlWebViewLayout;


    private WebView mWebview;

    private String mCurrentLotteryCode;

    public static void goWfActivity(Context context, String lotteryCode) {
        Intent intent = new Intent(context, LotteryBPlayTypeExplainActivity.class);
        intent.putExtra(LT_CODE, lotteryCode);
        context.startActivity(intent);
    }


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_lottery_b_explain);
    }

    @Override
    protected void initViews() {
        mCurrentLotteryCode = getString(R.string.cqssc);
        mHeadView.setHeader(getString(R.string.wf_pop), true);
        if (getIntent().getStringExtra(LT_CODE) != null) {
            mCurrentLotteryCode = getIntent().getStringExtra(LT_CODE);
        }
        createWebView();
    }


    private void createWebView() {
        mWebview = new WebView(BoxApplication.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mWebview.setLayoutParams(layoutParams);
        mFlWebViewLayout.addView(mWebview);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        mWebview.setVerticalScrollBarEnabled(true);
        mWebview.setBackgroundColor(getResources().getColor(R.color.custom_blue_light));
    }

    @Override
    protected void initData() {

        if (mCurrentLotteryCode.endsWith("ssc")) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/sscB_explain.html");
        } else if (mCurrentLotteryCode.equals(getString(R.string.bjpk10))
                || mCurrentLotteryCode.equals(getString(R.string.xyft))
                || mCurrentLotteryCode.equals(getString(R.string.jspk10))) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/bjPk10B_explain.html");
        } else if (mCurrentLotteryCode.endsWith("k3")) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/jsk3B_explain.html");
        } else if (mCurrentLotteryCode.equals(getString(R.string.bjkl8))) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/bjkl8_explain.html");
        } else if (mCurrentLotteryCode.equals(getString(R.string.xy28))) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/xy28_explain.html");
        } else if (mCurrentLotteryCode.equalsIgnoreCase(HkLHC)) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/hklhc_explain.html");
        } else if (mCurrentLotteryCode.equals(getString(R.string.cqxync))
                || mCurrentLotteryCode.equals(getString(R.string.gdkl10))) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/cqxync.html");
        } else if (mCurrentLotteryCode.equals(LotteryEnum.FC3D.getCode())
                || mCurrentLotteryCode.equals(LotteryEnum.TCPL3.getCode())) {
            mWebview.loadUrl("file:///android_asset/html/playexplain/qt_explain.html");
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.clearHistory();
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }

}
