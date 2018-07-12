package com.dawoo.lotterybox.view.activity.lottery.ssc;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.waypaper.WayPaperFormAdapter;
import com.dawoo.lotterybox.bean.WayPaperButtonBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.mvp.presenter.WayPaperPresenter;
import com.dawoo.lotterybox.mvp.view.IWayPaperView;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;

/**
 * 时时彩路纸图
 * Created by b on 18-2-26.
 */

public class SSCWayPaperActivity extends BaseActivity implements IWayPaperView {

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tv_which_periods)
    TextView mTvWhichPeriods;
    @BindView(R.id.tv_award_num)
    TextView mTvAwardNum;
    @BindView(R.id.tv_periods_stop)
    TextView mTvPeriodsStop;
    @BindView(R.id.tv_timer)
    TextView mTvTimer;
    @BindView(R.id.rlv_lottery_record)
    RecyclerView mRlvLotteryRecord;
    @BindView(R.id.tv_miss_top)
    TextView mTvMissTop;
    @BindView(R.id.rlv_way_paper_bt)
    RecyclerView mRlvWayPaperBt;
    @BindView(R.id.lLayout_ssc_bottom_second)
    NestedScrollView mLLayoutSscBottomSecond;
    @BindView(R.id.tv_results_status)
    TextView mTvResultsStatus;
    @BindView(R.id.rlv_form)
    RecyclerView mRlvForm;
    @BindView(R.id.tv_double_way_paper)
    TextView mTvDoubleWayPaper;


    List<WayPaperButtonBean> mButtonBeans = new ArrayList<>();
    WayPaperButtonBean mWayPaperButtonBean;
    private WayPaperPresenter mWayPaperPresenter;
    private CountDownTimerUtils mCountDownTimerUtils;
    private AwardResultsQuickAdapter mQuickAdapter;
    List<Handicap> mHandicaps = new ArrayList<>();
//    img_gray_arrow_up

    public static void goLztActivity(Context context, String code) {
        Intent intent = new Intent(context, SSCWayPaperActivity.class);
        intent.putExtra(LT_CODE, code);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_cqssc_way_paper);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.way_paper), true);
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(mLLayoutSscBottomSecond);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    protected void initData() {
        initButtonData();
        initBt();
        mQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_ssc_b_lottery_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        View headView = getLayoutInflater().inflate(R.layout.layout_ssc_way_paper_record_head, (ViewGroup) mRlvLotteryRecord.getParent(), false);
        mQuickAdapter.addHeaderView(headView);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mQuickAdapter);

        mWayPaperPresenter = new WayPaperPresenter(this, this, getIntent().getStringExtra(LT_CODE));
        mWayPaperPresenter.getResultByCode();
        mWayPaperPresenter.getLotteryExpect();
        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
    }


    @Override
    public void onResultByCode(List<Handicap> handicapList) {
        if (handicapList != null) {
            mHandicaps = handicapList;
            mQuickAdapter.setNewData(mHandicaps);
            mTvWhichPeriods.setText(getString(R.string.which_periods, handicapList.get(0).getExpect()));
            mTvAwardNum.setText(handicapList.get(0).getOpenCode());
            setWayPaperForm();
        }
    }

    boolean isOpenWayPaper = true;
    @OnClick(R.id.tv_double_way_paper)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_double_way_paper:
                if (isOpenWayPaper){
                    mTvDoubleWayPaper.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this,R.mipmap.img_lzt_up),null,null,null);
                    mRlvWayPaperBt.setVisibility(View.GONE);
                    mTvResultsStatus.setVisibility(View.GONE);
                    mRlvForm.setVisibility(View.GONE);
                }else {
                    mTvDoubleWayPaper.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this,R.mipmap.img_lzt_down),null,null,null);
                    mRlvWayPaperBt.setVisibility(View.VISIBLE);
                    mTvResultsStatus.setVisibility(View.VISIBLE);
                    mRlvForm.setVisibility(View.VISIBLE);
                }
                isOpenWayPaper = !isOpenWayPaper;
                break;
        }
    }

    private boolean isOpen = false;

    @Override
    public void onLotteryExpect(BaseHandicap expectDataBean) {
        if (expectDataBean != null) {
            if (expectDataBean.getLeftOpenTime() == 0 && expectDataBean.getLeftTime() == 0) {
                ToastUtil.showToastLong(this,getString(R.string.getexpect_error));
                return;
            }
            String status = "";
            long time = 0;
            if (expectDataBean.getLeftOpenTime() > 0) {
                status = getString(R.string.not_bet);
                time = expectDataBean.getLeftOpenTime() * 1000;
                isOpen = false;

            } else {
                status = getString(R.string.stop);
                time = expectDataBean.getLeftTime() * 1000;
                isOpen = true;
            }

            mCountDownTimerUtils.cancel();
            mCountDownTimerUtils.setMillisInFuture(time)
                    .setCountDownInterval(1000)
                    .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                    .setTickDelegate(mTickDelegate)
                    .start();
            Object[] str = {expectDataBean.getExpect(), status};
            mTvPeriodsStop.setText(getString(R.string.which_periods_stop, str));

        }
    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm:ss");

        @Override
        public void onTick(long pMillisUntilFinished) {
            String str1 = sdf1.format(pMillisUntilFinished);
            mTvTimer.setText(str1);
        }
    };

    //倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTvTimer.setText("00:00");
            mWayPaperPresenter.getLotteryExpect();
            if (isOpen)
                mWayPaperPresenter.getResultByCode();
        }
    };

    private void initBt() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRlvWayPaperBt.setNestedScrollingEnabled(false);
        mRlvWayPaperBt.setLayoutManager(manager);
        ButtonQuickAdapter mBtAdapter = new ButtonQuickAdapter(R.layout.item_way_paper_bt);
        mRlvWayPaperBt.setAdapter(mBtAdapter);
        mBtAdapter.setNewData(mButtonBeans);
        mBtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!view.isSelected()) {
                    view.setSelected(true);
                    mButtonBeans.get(position).setSelect(true);
                    for (int i = 0; i < mButtonBeans.size(); i++) {
                        if (i != position) {
                            mRlvWayPaperBt.getChildAt(i).setSelected(false);
                            mButtonBeans.get(i).setSelect(false);
                        }
                    }
                    mWayPaperButtonBean = mButtonBeans.get(position);
                    setWayPaperForm();
                }
            }
        });
        mWayPaperButtonBean = mButtonBeans.get(0);
    }

    private void setWayPaperForm() {
        List<List<String>> statusList = mWayPaperPresenter.classification(mHandicaps, mWayPaperButtonBean);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mWayPaperButtonBean.getBtName() + " ");
        if (mWayPaperButtonBean.getStatus() == 0) {
            stringBuffer.append(getString(R.string.big) + "(" + mWayPaperPresenter.big + ")");
            stringBuffer.append(getString(R.string.small) + "(" + mWayPaperPresenter.small + ")");
        } else if (mWayPaperButtonBean.getStatus() == 1) {
            stringBuffer.append(getString(R.string.single) + "(" + mWayPaperPresenter.single + ")");
            stringBuffer.append(getString(R.string.double_) + "(" + mWayPaperPresenter.double_ + ")");
        } else {
            stringBuffer.append(getString(R.string.dragon) + "(" + mWayPaperPresenter.dragon + ")");
            stringBuffer.append(getString(R.string.tiger) + "(" + mWayPaperPresenter.tiger + ")");
            stringBuffer.append(getString(R.string.he) + "(" + mWayPaperPresenter.he + ")");
        }
        mTvResultsStatus.setText(stringBuffer);

        int count = 0;
        for (List<String> strings : statusList) {
            if (strings.size() > count)
                count = strings.size();
        }
        String nullStr = "";
        for (List<String> strings : statusList) {
            for (int i = 0; i < count; i++) {
                if (strings.size() < (i + 1)) {
                    strings.add(nullStr);
                }
            }
        }
        WayPaperFormAdapter wayPaperFormAdapter = new WayPaperFormAdapter(this, statusList);
        mRlvForm.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRlvForm.setAdapter(wayPaperFormAdapter);
    }

    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String openCode = cqsscAwardResultBean.getOpenCode();
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, openCode);
            helper.setText(R.id.tv_status, Lottery_B_DataUtils.getFiveStarStatus(openCode));
        }
    }

    class ButtonQuickAdapter extends BaseQuickAdapter {
        public ButtonQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            WayPaperButtonBean buttonBean = (WayPaperButtonBean) item;
            helper.setText(R.id.tv_ball_status, buttonBean.getBtName());
            helper.itemView.setSelected(buttonBean.isSelect());

        }
    }


    private void initButtonData() {
        WayPaperButtonBean wayPaperButtonBean1 = new WayPaperButtonBean();
        wayPaperButtonBean1.setBtName(getString(R.string.all_dragon_and_tiger));
        wayPaperButtonBean1.setBallNumber(-1);
        wayPaperButtonBean1.setStatus(2);
        wayPaperButtonBean1.setSelect(true);
        mButtonBeans.add(wayPaperButtonBean1);
        WayPaperButtonBean wayPaperButtonBean2 = new WayPaperButtonBean();
        wayPaperButtonBean2.setBtName(getString(R.string.all_big_small));
        wayPaperButtonBean2.setBallNumber(-1);
        wayPaperButtonBean2.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean2);
        WayPaperButtonBean wayPaperButtonBean3 = new WayPaperButtonBean();
        wayPaperButtonBean3.setBtName(getString(R.string.all_single_double));
        wayPaperButtonBean3.setBallNumber(-1);
        wayPaperButtonBean3.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean3);
        WayPaperButtonBean wayPaperButtonBean4 = new WayPaperButtonBean();
        wayPaperButtonBean4.setBtName(getString(R.string.first_big_small));
        wayPaperButtonBean4.setBallNumber(0);
        wayPaperButtonBean4.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean4);
        WayPaperButtonBean wayPaperButtonBean5 = new WayPaperButtonBean();
        wayPaperButtonBean5.setBtName(getString(R.string.first_single_double));
        wayPaperButtonBean5.setBallNumber(0);
        wayPaperButtonBean5.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean5);
        WayPaperButtonBean wayPaperButtonBean6 = new WayPaperButtonBean();
        wayPaperButtonBean6.setBtName(getString(R.string.second_big_small));
        wayPaperButtonBean6.setBallNumber(1);
        wayPaperButtonBean6.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean6);
        WayPaperButtonBean wayPaperButtonBean7 = new WayPaperButtonBean();
        wayPaperButtonBean7.setBtName(getString(R.string.second_single_double));
        wayPaperButtonBean7.setBallNumber(1);
        wayPaperButtonBean7.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean7);
        WayPaperButtonBean wayPaperButtonBean8 = new WayPaperButtonBean();
        wayPaperButtonBean8.setBtName(getString(R.string.third_big_small));
        wayPaperButtonBean8.setBallNumber(2);
        wayPaperButtonBean8.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean8);
        WayPaperButtonBean wayPaperButtonBean9 = new WayPaperButtonBean();
        wayPaperButtonBean9.setBtName(getString(R.string.third_single_double));
        wayPaperButtonBean9.setBallNumber(2);
        wayPaperButtonBean9.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean9);
        WayPaperButtonBean wayPaperButtonBean10 = new WayPaperButtonBean();
        wayPaperButtonBean10.setBtName(getString(R.string.fourth_big_small));
        wayPaperButtonBean10.setBallNumber(3);
        wayPaperButtonBean10.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean10);
        WayPaperButtonBean wayPaperButtonBean11 = new WayPaperButtonBean();
        wayPaperButtonBean11.setBtName(getString(R.string.fourth_single_double));
        wayPaperButtonBean11.setBallNumber(3);
        wayPaperButtonBean11.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean11);
        WayPaperButtonBean wayPaperButtonBean12 = new WayPaperButtonBean();
        wayPaperButtonBean12.setBtName(getString(R.string.fifth_big_small));
        wayPaperButtonBean12.setBallNumber(4);
        wayPaperButtonBean12.setStatus(0);
        mButtonBeans.add(wayPaperButtonBean12);
        WayPaperButtonBean wayPaperButtonBean13 = new WayPaperButtonBean();
        wayPaperButtonBean13.setBtName(getString(R.string.fifth_single_double));
        wayPaperButtonBean13.setBallNumber(4);
        wayPaperButtonBean13.setStatus(1);
        mButtonBeans.add(wayPaperButtonBean13);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimerUtils.cancel();
    }
}
