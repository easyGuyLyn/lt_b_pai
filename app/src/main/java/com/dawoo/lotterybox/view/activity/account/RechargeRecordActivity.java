package com.dawoo.lotterybox.view.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.coretool.util.date.DateUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.BillAdapter;
import com.dawoo.lotterybox.bean.BillAmountBean;
import com.dawoo.lotterybox.bean.record.BillCommonBean;
import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.bean.record.recordnum.BaseSelectedBean;
import com.dawoo.lotterybox.mvp.presenter.BillPresenter;
import com.dawoo.lotterybox.mvp.view.IBillHisView;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.BillHistUtl;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.NoteCalendarUtil;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.CalendarActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-16
 * 充值
 */

public class RechargeRecordActivity extends BaseActivity implements OnRefreshListener, IBillHisView {
    private static final int CHOOSE_CALENDAR_REQUEST_CODE = 001;
    @BindView(R.id.tl_tab)
    TabLayout mStatusTabLayOut;
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.input_tv)
    TextView inputTV;
    @BindView(R.id.out_tv)
    TextView outTV;

    @BindView(R.id.textView5)
    TextView dateTV;
    @BindView(R.id.year_tv)
    TextView yearTV;
    @BindView(R.id.tv_note_allCode)
    TextView parentCodeTV;

    @BindView(R.id.ry_lottery_codeName)
    RecyclerView mCodeNameRecyclerView;

    @BindView(R.id.tv_yinkui_sort)
    TextView mTvYinkuiSort;
    @BindView(R.id.tv_yinkui_sort_down)
    TextView mTvYinkuiSortDown;
    @BindView(R.id.tv_balance_sort)
    TextView mTvBalanceSort;
    @BindView(R.id.tv_balance_sort_down)
    TextView mTvBalanceSortDown;
    @BindView(R.id.tv_time_sort)
    TextView mTvTimeSort;
    @BindView(R.id.tv_time_sort_down)
    TextView mTvTimeSortDown;
    @BindView(R.id.day_before_tv)
    View beforeTV;
    @BindView(R.id.next_day)
    View nextTV;
    @BindView(R.id.before_money)
    TextView beforeMTV;
    @BindView(R.id.date_money)
    TextView dateMTV;
    @BindView(R.id.next_money)
    TextView nextMTV;

    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.bef)
    TextView mBef;
    @BindView(R.id.calender_iv)
    ImageView mCalenderIv;
    @BindView(R.id.calender_rl)
    RelativeLayout mCalenderRl;
    @BindView(R.id.data_day_rl)
    RelativeLayout mDataDayRl;
    @BindView(R.id.next)
    TextView mNext;
    @BindView(R.id.rl_action_allCode)
    RelativeLayout mRlActionAllCode;
    @BindView(R.id.rl_action_balance_sort)
    RelativeLayout mRlActionBalanceSort;
    @BindView(R.id.rl_action_yinkui_sort)
    RelativeLayout mRlActionYinkuiSort;
    @BindView(R.id.rl_action_time_sort)
    RelativeLayout mRlActionTimeSort;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;

    private String mTabType;
    private List<String> tabIndicators = new ArrayList<>();
    BillPresenter mPresenter;

    public static final String deposit = "deposit";//存款
    public static final String withdraw = "withdraw";//取款

    public static final String[] status = new String[]{deposit, withdraw};
    private String mQueryStartDate;//开始时间  默认预加载31天的数据
    private String mQueryEndDate;//最后时间  默认今天
    private String mShowStartDate;//开始下注时间  "yyyy-MM-dd"
    private String mShowEndDate;//最后下注时间    "yyyy-MM-dd"
    private List<BaseSelectedBean> parentBeans = new ArrayList<>();
    private int parentIndex = 0;
    private CodeListAdapter mCodeListAdapter;
    private boolean mPlSort; //是否按金额排序
    private boolean mTimeSort;//是否按时间排序
    private boolean mStatuSort;//按状态排序
    private BillAdapter<BillItemBean> mAdapter;
    private List<BillItemBean> mAllDatas = new ArrayList<>();
    private int index = 0;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_recharge_details);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.bill_details_in_out), true);
        boolean isDeposit = getIntent().getBooleanExtra("isDeposit", true);
        if (!isDeposit) {
            index = 1;
        }
        initFragmentDate();
        initCodeNameRecyclerView();

    }

    private void initFragmentDate() {
        mPresenter = new BillPresenter(this, this);
        initContent();
        initTable();
        initSelectedData();
        mQueryStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime());
        mQueryEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        yearTV.setText(mShowEndDate.substring(0, 4));
        dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
        mAdapter = new BillAdapter<BillItemBean>(R.layout.adapter_recharge_layout, 1);
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mSwipeTarget.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mSwipeTarget.setAdapter(mAdapter);
        mAdapter.setNewData(mAllDatas);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);
        setBeforeAndAfterVisbility();
    }

    private void initContent() {
        tabIndicators = MSPropties.getTablaout(tabIndicators);
        mTabType = status[index];
    }

    private void initTable() {
        for (int i = 0; i < tabIndicators.size(); i++) {
            if (i == index) {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(tabIndicators.get(i)), true);
            } else {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(tabIndicators.get(i)), false);
            }
        }
        initTabListener();
    }

    void initSelectedData() {
        parentIndex = 0;
        parentBeans = BillHistUtl.getChildByCode(mTabType, "");
        parentCodeTV.setText(parentBeans.get(parentIndex).getName());
    }

    private void initCodeNameRecyclerView() {
        mCodeListAdapter = new CodeListAdapter(R.layout.recyclerview_list_item_code_name_view);
        mCodeListAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mCodeNameRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(this, 8)));
        mCodeNameRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mCodeNameRecyclerView.setAdapter(mCodeListAdapter);
    }

    /**
     * 初始化 tab  listener
     */
    private void initTabListener() {
        mStatusTabLayOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mStatusTabLayOut.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        mStatusTabLayOut.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mCodeNameRecyclerView.getVisibility() == View.VISIBLE) {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                }
                mTabType = status[tab.getPosition()];
                initSelectedData();
                initData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getBillAssert(mShowStartDate, mShowEndDate, mTabType, parentBeans.get(parentIndex).getCode());
        mPresenter.getBillsrecharge(mShowStartDate, mShowEndDate, mTabType, parentBeans.get(parentIndex).getCode());
        setBeforeAndAfterVisbility();
    }

    void filterData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                BillHistUtl.sortListData(mAllDatas, mPlSort, mStatuSort, mTimeSort);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //资金记录数据
    @Override
    public void getBillsData(List<BillItemBean> mDatas) {
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
        this.mAllDatas.clear();
        this.mAllDatas.addAll(mDatas);
        filterData();
    }

    /**
     * 资金记录总额报表
     *
     * @param o
     */
    @Override
    public void getBillCount(BillCommonBean o) {


    }

    @Override
    public void getBillAmount(BillAmountBean o) {
        int origion = getResources().getColor(R.color.text_note_record_yellow);
        String leftWord = "";
        if (mTabType.equalsIgnoreCase(deposit)) {
            String deposit = "线上支付:    " + BalanceUtils.getScalsBalance(o.getOnlinemoney());
            SpannableString depositSpan = new SpannableString(deposit);
            depositSpan.setSpan(new ForegroundColorSpan(origion), deposit.indexOf(":") + 1, depositSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            String out = "公司入款:    " + BalanceUtils.getScalsBalance(o.getCompanymoney());
            SpannableString outSpan = new SpannableString(out);
            outSpan.setSpan(new ForegroundColorSpan(origion), out.indexOf(":") + 1, outSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            inputTV.setText(depositSpan);
            outTV.setText(outSpan);
            leftWord = "存款";
        } else {
            String deposit = "提现总额:    " + BalanceUtils.getScalsBalance(o.getPlayermoney());
            SpannableString depositSpan = new SpannableString(deposit);
            depositSpan.setSpan(new ForegroundColorSpan(origion), deposit.indexOf(":") + 1, depositSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            inputTV.setText(depositSpan);
            outTV.setText("");
            leftWord = "提现";
        }

        SpannableString beforeSpan = new SpannableString(leftWord + BalanceUtils.getScalsBalance(o.getBeforemoney()));
        SpannableString dateSpan = new SpannableString(leftWord + BalanceUtils.getScalsBalance(o.getMoney()));
        SpannableString nextSpan = new SpannableString(leftWord + BalanceUtils.getScalsBalance(o.getAftermoney()));
        beforeMTV.setText(beforeSpan);
        dateMTV.setText(dateSpan);
        nextMTV.setText(nextSpan);
    }


    @OnClick({R.id.rl_action_allCode, R.id.rl_action_balance_sort, R.id.rl_action_yinkui_sort, R.id.rl_action_time_sort,
            R.id.day_before_tv, R.id.data_day_rl, R.id.next_day})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.day_before_tv:
                if (mShowStartDate.equals(mQueryStartDate)) {
                    SingleToast.showMsg("暂时只能为您展示30天内记录");
                    return;
                }
                String yesterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowStartDate) - 24 * 60 * 60 * 1000);
                mShowStartDate = yesterDay;
                yearTV.setText(mShowStartDate.substring(0, 4));
                dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                initData();
                break;
            case R.id.data_day_rl:
                jumpToCalendar();
                break;
            case R.id.next_day:
                if (mShowEndDate.equals(mQueryEndDate)) {
                    return;
                }
                String afterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowEndDate) + 24 * 60 * 60 * 1000);
                mShowEndDate = afterDay;
                yearTV.setText(mShowStartDate.substring(0, 4));
                dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                initData();
                break;
            case R.id.rl_action_allCode:
                if (mCodeNameRecyclerView.getVisibility() == View.GONE) {
                    mCodeListAdapter.replaceData(parentBeans);
                    mCodeNameRecyclerView.setVisibility(View.VISIBLE);

                } else {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_action_balance_sort:
                setMoneySort();
                break;
            case R.id.rl_action_yinkui_sort:
                setStatusSort();
                break;
            case R.id.rl_action_time_sort:
                setTimeSort();
                break;
        }
    }

    //前一天  后一天的  显示和隐藏
    private void setBeforeAndAfterVisbility() {
        if (mShowStartDate.equals(DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime()))) {
            beforeTV.setClickable(false);
            mBef.setVisibility(View.GONE);
            beforeMTV.setVisibility(View.GONE);
        } else {
            beforeTV.setClickable(true);
            mBef.setVisibility(View.VISIBLE);
            beforeMTV.setVisibility(View.VISIBLE);
        }

        if (mShowEndDate.equals(DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()))) {
            nextTV.setClickable(false);
            mNext.setVisibility(View.GONE);
            nextMTV.setVisibility(View.GONE);
        } else {
            nextTV.setClickable(true);
            mNext.setVisibility(View.VISIBLE);
            nextMTV.setVisibility(View.VISIBLE);
        }
    }

    void setStatusSort() {
        mStatuSort = !mStatuSort;
        if (mStatuSort) {
            mTvYinkuiSort.setVisibility(View.GONE);
            mTvYinkuiSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvYinkuiSort.setVisibility(View.VISIBLE);
            mTvYinkuiSortDown.setVisibility(View.GONE);
        }
        BillHistUtl.sortFromStatus(mStatuSort, mAllDatas);
        mAdapter.notifyDataSetChanged();
    }

    void setMoneySort() {
        mPlSort = !mPlSort;
        if (mPlSort) {
            mTvBalanceSort.setVisibility(View.GONE);
            mTvBalanceSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvBalanceSort.setVisibility(View.VISIBLE);
            mTvBalanceSortDown.setVisibility(View.GONE);
        }
        BillHistUtl.sortFormBalance(mPlSort, mAllDatas);
        mAdapter.notifyDataSetChanged();
    }

    void setTimeSort() {
        mTimeSort = !mTimeSort;
        if (mTimeSort) {
            mTvTimeSort.setVisibility(View.GONE);
            mTvTimeSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvTimeSort.setVisibility(View.VISIBLE);
            mTvTimeSortDown.setVisibility(View.GONE);
        }
        BillHistUtl.sortFormTime(mTimeSort, mAllDatas);
        mAdapter.notifyDataSetChanged();
    }

    //跳转到日历
    private void jumpToCalendar() {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(CalendarActivity.START_TIME, mShowStartDate);
        intent.putExtra(CalendarActivity.END_TIME, mShowEndDate);
        startActivityForResult(intent, CHOOSE_CALENDAR_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_CALENDAR_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String[] dataArray = data.getStringArrayExtra(CalendarActivity.RESULT_DATE_ARREY);
                mShowStartDate = dataArray[0];
                mShowEndDate = dataArray[1];
                yearTV.setText(mShowStartDate.substring(0, 4));
                dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                initData();
                setBeforeAndAfterVisbility();
            }
        }
    }

    @Override
    public void onRefresh() {
        initData();
    }

    private class CodeListAdapter extends BaseQuickAdapter {

        public CodeListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            BaseSelectedBean LotterySimpleBean = (BaseSelectedBean) item;
            helper.setText(R.id.tv_code_name, LotterySimpleBean.getName());
            if (LotterySimpleBean.getCode().equals(parentBeans.get(parentIndex).getCode())) {
                ((TextView) helper.getView(R.id.tv_code_name)).setTextColor(getResources().getColor(R.color.red_btn_bg));
            }
            helper.setTextColor(R.id.tv_code_name, getResources().getColor(R.color.color_gray_666666));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                    parentIndex = parentBeans.indexOf(item);
                    parentCodeTV.setText(LotterySimpleBean.getName());
                    mPresenter.getBillsrecharge(mShowStartDate, mShowEndDate, mTabType, LotterySimpleBean.getCode());
                }
            });
        }
    }


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        LogUtils.d(s);
        //  收起刷新
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }
}
