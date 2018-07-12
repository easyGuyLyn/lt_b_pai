package com.dawoo.lotterybox.view.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BuildConfig;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.PromoListBean;
import com.dawoo.lotterybox.mvp.presenter.PromoPresenter;
import com.dawoo.lotterybox.mvp.view.IPromoListView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.NetUtil;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 活动列表
 * Created by b on 18-2-8.
 */

public class ActivitiesActivity extends BaseActivity implements IPromoListView, OnRefreshListener {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    private ActivitiesQuickAdapter mActivitiesQuickAdapter;
    List<PromoListBean.DataBean> mDatas = new ArrayList<>();
    private PromoPresenter mPromoPresenter;
    private int pageNumber = 1;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_activities);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.activities_activity), true);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);
        mSwipeToLoadLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mPromoPresenter = new PromoPresenter(this, this);
        mPromoPresenter.getPromoList(pageNumber);


        mActivitiesQuickAdapter = new ActivitiesQuickAdapter(R.layout.item_activities_rlv);
        mSwipeTarget.setLayoutManager(new LinearLayoutManager(this));
        mActivitiesQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
//        mSwipeTarget.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mSwipeTarget.setAdapter(mActivitiesQuickAdapter);
        mActivitiesQuickAdapter.setNewData(mDatas);
        mActivitiesQuickAdapter.setOnItemClickListener((adapter, view, position) -> {
//            Intent intent = new Intent(ActivitiesActivity.this, ActivitiesDetailActivity.class);
//            intent.putExtra(ActivitiesDetailActivity.ACTIVITUES_ID, mDatas.get(position).getId());
//            startActivity(intent);

            int id = mDatas.get(position).getId();
            String urlSuffix = ConstantValue.PROMO_DETAIL_URL + id;
            String url = NetUtil.handleUrl(BuildConfig.HOST_URL, urlSuffix);
            ActivityUtil.startWebView(url, ConstantValue.WEBVIEW_TYPE_ORDINARY);
        });
    }

    @Override
    public void onGetPromoList(PromoListBean promoListBean) {
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
        if (promoListBean != null && promoListBean.getData() != null) {
            if (pageNumber == 1)
                mDatas.clear();
            mDatas.addAll(promoListBean.getData());
            mActivitiesQuickAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        pageNumber = 1;
        mPromoPresenter.getPromoList(pageNumber);
    }




    class ActivitiesQuickAdapter extends BaseQuickAdapter {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        public ActivitiesQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PromoListBean.DataBean dataBean = (PromoListBean.DataBean) item;
            String url = NetUtil.handleUrl(BuildConfig.HOST_IMG_URL, dataBean.getActivityCover());
            Glide.with(ActivitiesActivity.this).load(url).into((ImageView) helper.getView(R.id.iv_item_activities));
            helper.setText(R.id.tv_item_activities_content, dataBean.getActivityName());
            Object[] times = {formatter.format(dataBean.getStartTime()), formatter.format(dataBean.getEndTime())};
            helper.setText(R.id.tv_item_activities_time, getString(R.string.promo_time, times));
        }
    }
}
