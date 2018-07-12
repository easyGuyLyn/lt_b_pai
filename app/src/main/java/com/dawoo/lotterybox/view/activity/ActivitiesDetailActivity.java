package com.dawoo.lotterybox.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.PromoContentBean;
import com.dawoo.lotterybox.mvp.presenter.PromoPresenter;
import com.dawoo.lotterybox.mvp.view.IPromoContentView;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 活动详情
 * Created by b on 18-3-16.
 */

public class ActivitiesDetailActivity extends BaseActivity implements IPromoContentView {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.activities_title)
    TextView mActivitiesTitle;
    @BindView(R.id.activities_time)
    TextView mActivitiesTime;
    @BindView(R.id.activities_content)
    TextView mActivitiesContent;
    @BindView(R.id.wv_content)
    WebView mWebView;
    public static final String ACTIVITUES_ID = "activities_id";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_activities_detail);

    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.activities_detal_activity),true);
    }

    @Override
    protected void initData() {
        PromoPresenter promoPresenter = new PromoPresenter(this,this);
        promoPresenter.getPromoContent(getIntent().getIntExtra(ACTIVITUES_ID,0));
    }

    @Override
    public void onPromoContent(PromoContentBean promoContentBeans) {
        if (promoContentBeans != null ){
            mActivitiesTitle.setText(promoContentBeans.getActivityName());
            Object[] times = {formatter.format(promoContentBeans.getStartTime()), formatter.format(promoContentBeans.getEndTime())};
            mActivitiesTime.setText(getString(R.string.promo_time,times));
            if (promoContentBeans.getActivityDescription().startsWith("<")){
                mActivitiesContent.setVisibility(View.GONE);
                String demain = DataCenter.getInstance().getDomain();
                String flag = "/fserver";
                String content = promoContentBeans.getActivityDescription();
                content = content.replace(flag, demain + flag);
                mWebView.loadData(content, "text/html", "UTF-8");
            }else {
                mWebView.setVisibility(View.GONE);
                mActivitiesContent.setText(promoContentBeans.getActivityDescription());  //纯文本
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                mActivitiesContent.setText(Html.fromHtml(promoContentBeans.getActivityDescription(), Html.FROM_HTML_MODE_LEGACY));
//            } else {
//                mActivitiesContent.setText(Html.fromHtml(promoContentBeans.getActivityDescription()));
//            }
        }
    }
}
