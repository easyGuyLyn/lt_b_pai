package com.dawoo.lotterybox.view.activity.record;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lottery_rcd.OpenRecentRcdAdapter;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.mvp.presenter.LotteryRecordPresenter;
import com.dawoo.lotterybox.mvp.view.IRecentOpenRecView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.chart.ssc.ChartSSCActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.RENCT_DATA;

/**
 * 根据彩种展示最近的开奖记录
 * 比如重庆时时彩最近100期的开奖历史数据
 * Created by benson on 18-2-18.
 */

public class RecentOpenRecActivity extends BaseActivity implements IRecentOpenRecView, OnRefreshListener, OnLoadMoreListener {
    public static final String LOTTERY_CODE = "LOTTERY_CODE";
    private final String mPageSize = "120";
    @BindView(R.id.left_btn)
    FrameLayout mLeftBtn;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.trend_icon_iv)
    ImageView mTrendIconIv;
    @BindView(R.id.trend_icon_tv)
    TextView mTrendIconTv;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.go_to_lottery_btn)
    Button mButton;
    private LotteryRecordPresenter mPresenter;
    private String mCode;
    private OpenRecentRcdAdapter mAdapter;
    private ArrayList<Handicap> mRencentList = new ArrayList<>();

    public static void goRecentOPenRecActivity(Context context, String code) {
        Intent intent = new Intent(context, RecentOpenRecActivity.class);
        intent.putExtra(LOTTERY_CODE, code);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_recent_open_record);
    }

    @Override
    protected void initViews() {
        mTitleName.setText("开奖结果");
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mAdapter = new OpenRecentRcdAdapter(new ArrayList());
//        int padding = DensityUtil.dp2px(this, 10);
//        mRecyclerView.setPadding(padding, 0, padding, 0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mCode = getIntent().getStringExtra(LOTTERY_CODE);
        mPresenter = new LotteryRecordPresenter<>(this, this);
        mPresenter.getRecentRecords(mCode, mPageSize);

        mButton.setText("投注" + LotteryUtil.getLotteryNameByCode(mCode));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @OnClick({R.id.left_btn, R.id.trend_icon_iv, R.id.trend_icon_tv, R.id.go_to_lottery_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.trend_icon_iv:
            case R.id.trend_icon_tv:
                startTrendCharActivity();
                break;
            case R.id.go_to_lottery_btn:
                ActivityUtil.startNoteActivity(mCode);
                break;
        }
    }

    /**
     * 进入趋势图
     */
    void startTrendCharActivity() {
        ActivityUtil.startChartActivity(this, mRencentList, mCode);
    }

    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mSwipeToLoadLayout.setLoadingMore(false);
                    ToastUtil.showToastShort(RecentOpenRecActivity.this, getString(R.string.NO_MORE_DATA));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        // mPresenter.loadMoreRecentRecords(mCode, mPageSize);
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshRecentRecords(mCode, mPageSize);
    }

    @Override
    public void onRecentRecResult(List<HandicapWithOpening> list) {
        setData(list);

    }

    @Override
    public void onRefreshRecResult(List<HandicapWithOpening> list) {
        mSwipeToLoadLayout.setRefreshing(false);
        setData(list);
    }

    @Override
    public void onLoadMoreRecResult(List<HandicapWithOpening> list) {

    }


    void setData(List<HandicapWithOpening> list) {
        mRencentList.clear();
        mRencentList.addAll(list);
        mAdapter.setNewData(mPresenter.addItemType2(list));
    }

}
