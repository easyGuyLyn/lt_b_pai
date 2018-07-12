package com.dawoo.lotterybox.view.fragment.bill_details;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
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
 * 账单明细
 */

public class BillingDetailsActivity extends BaseActivity implements IBillHisView, OnRefreshListener, OnLoadMoreListener {
    private static final int CHOOSE_CALENDAR_REQUEST_CODE = 001;
    @BindView(R.id.tl_tab)
    TabLayout mStatusTabLayOut;
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.input_tv)
    TextView inputTV;
    @BindView(R.id.out_tv)
    TextView outTV;
    @BindView(R.id.game_tv)
    TextView gameTV;
    @BindView(R.id.sale_tv)
    TextView saleTV;
    @BindView(R.id.textView5)
    TextView dateTV;
    @BindView(R.id.year_tv)
    TextView yearTV;
    @BindView(R.id.tv_note_allCode)
    TextView parentCodeTV;
    @BindView(R.id.tv_note_childtype)
    TextView childCodeTV;
    @BindView(R.id.ry_lottery_codeName)
    RecyclerView mCodeNameRecyclerView;

    @BindView(R.id.tv_yinkui_sort)
    TextView mTvYinkuiSort;
    @BindView(R.id.tv_yinkui_sort_down)
    TextView mTvYinkuiSortDown;
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
    @BindView(R.id.rl_action_note_sort)
    RelativeLayout mRlActionNoteSort;
    @BindView(R.id.rl_action_yinkui_sort)
    RelativeLayout mRlActionYinkuiSort;
    @BindView(R.id.rl_action_time_sort)
    RelativeLayout mRlActionTimeSort;
    @BindView(R.id.ll_bb)
    LinearLayout mLlBb;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;

    private String mTabType;
    private List<String> tabIndicators = new ArrayList<>();
    BillPresenter mPresenter;
    public static final String all = "";
    public static final String deposit = "deposit";//存款
    public static final String withdraw = "withdraw";//取款
    public static final String game = "game";//游戏
    public static final String favorable = "favorable";//优惠
    public static final String[] status = new String[]{all, withdraw, deposit, game, favorable};
    private String mQueryStartDate;//开始下注时间  默认预加载31天的数据
    private String mQueryEndDate;//最后下注时间  默认今天
    private String mShowStartDate;//开始下注时间  本地搜索时间  "yyyy-MM-dd"
    private String mShowEndDate;//最后下注时间  本地搜索时间  "yyyy-MM-dd"
    private List<BaseSelectedBean> parentBeans = new ArrayList<>();
    private List<BaseSelectedBean> childBeans = new ArrayList<>();
    private int pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
    private int parentIndex = 0;
    private int childIndex = 0;
    private boolean isParent = true;
    private CodeListAdapter mCodeListAdapter;
    private boolean mPlSort; //是否按金额排序
    private boolean mTimeSort;//是否按时间排序
    private BillAdapter<BillItemBean> mAdapter;
    private List<BillItemBean> mAllDatas = new ArrayList<>();

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_billing_detals);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.bill_details), true);
        initFragmentDate();
        initCodeNameRecyclerView();
        mPresenter = new BillPresenter(this, this);
    }

    private void initFragmentDate() {
        initContent();
        initTable();
        initSelectedData();
        mQueryStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime());
        mQueryEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        yearTV.setText(mShowStartDate.substring(0, 4));
        dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
        mAdapter = new BillAdapter<BillItemBean>(R.layout.adapter_bill_item_layout);
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mSwipeTarget.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mSwipeTarget.setAdapter(mAdapter);
        mAdapter.setNewData(mAllDatas);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        setBeforeAndAfterVisbility();
    }

    private void initContent() {
        tabIndicators = MSPropties.getTabLayout(tabIndicators);
        mTabType = status[0];
    }

    private void initTable() {
        for (int i = 0; i < tabIndicators.size(); i++) {
            if (i == 0) {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(tabIndicators.get(i)), true);
            } else {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(tabIndicators.get(i)), false);
            }
        }
        initTabListener();
    }

    void initSelectedData() {
        pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        parentIndex = 0;
        childIndex = 0;
        parentBeans = BillHistUtl.getParentByCode(mTabType);
        childBeans = BillHistUtl.getChildByCode(mTabType, parentBeans.get(parentIndex).getCode());
        parentCodeTV.setText(parentBeans.get(parentIndex).getName());
        childCodeTV.setText(childBeans.get(childIndex).getName());

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
        mStatusTabLayOut.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                mStatusTabLayOut.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });
        mStatusTabLayOut.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mCodeNameRecyclerView.getVisibility() == View.VISIBLE) {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                }
                mTabType = status[tab.getPosition()];
                initSelectedData();
                pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
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
        mPresenter.getBillCount(mShowStartDate, mShowEndDate);
        mPresenter.getBills(pageNum, mShowStartDate, mShowEndDate, mTabType, parentBeans.get(parentIndex).getCode(), childBeans.get(childIndex).getCode());
        setBeforeAndAfterVisbility();
    }

    void filterData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                BillHistUtl.sethowHistory(mAllDatas,
                        mPlSort, mTimeSort);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    //资金记录数据
    @Override
    public void getBillsData(List<BillItemBean> mDatas) {
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
        if (mDatas != null && !mDatas.isEmpty() && mDatas.size() == ConstantValue.RECORD_LIST_PAGE_SIZE) {
            mSwipeToLoadLayout.setLoadMoreEnabled(true);
        } else {
            mSwipeToLoadLayout.setLoadMoreEnabled(false);
        }
        if (pageNum == ConstantValue.RECORD_LIST_PAGE_NUMBER) {
            this.mAllDatas.clear();
        }
        this.mAllDatas.addAll(mDatas);
        pageNum++;
        filterData();
    }

    /**
     * 资金记录总额报表
     *
     * @param o
     */
    @Override
    public void getBillCount(BillCommonBean o) {
        int origion = getResources().getColor(R.color.text_note_record_yellow);

        String deposit = "存款总额:    " + BalanceUtils.getScalsBalance(o.getDepositmoney());
        SpannableString depositSpan = new SpannableString(deposit);
        depositSpan.setSpan(new ForegroundColorSpan(origion), deposit.indexOf(":") + 1, depositSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        String out = "取款总额:    " + BalanceUtils.getScalsBalance(o.getWithdrawmoney());
        SpannableString outSpan = new SpannableString(out);
        outSpan.setSpan(new ForegroundColorSpan(origion), out.indexOf(":") + 1, outSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        String sale = "优惠总额:    " + BalanceUtils.getScalsBalance(o.getFavorablemoney());
        SpannableString saleSpan = new SpannableString(sale);
        saleSpan.setSpan(new ForegroundColorSpan(origion), sale.indexOf(":") + 1, saleSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        String game = "游戏收入:    " + BalanceUtils.getScalsBalance(o.getIncomemoney());
        SpannableString gameSpan = new SpannableString(game);
        gameSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.history_item_green)), game.indexOf(":") + 1, gameSpan.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        inputTV.setText(depositSpan);
        outTV.setText(outSpan);
        saleTV.setText(saleSpan);
        gameTV.setText(gameSpan);
    }

    @Override
    public void getBillAmount(BillAmountBean o) {

    }


    @OnClick({R.id.rl_action_allCode, R.id.tv_note_childtype, R.id.rl_action_yinkui_sort, R.id.rl_action_time_sort,
            R.id.day_before_tv, R.id.data_day_rl, R.id.next_day})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.day_before_tv:
                String yesterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowStartDate) - 24 * 60 * 60 * 1000);
                mShowStartDate = yesterDay;
                yearTV.setText(mShowStartDate.substring(0, 4));
                dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                setBeforeAndAfterVisbility();
                pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
                initData();

                break;
            case R.id.data_day_rl:
                jumpToCalendar();
                break;
            case R.id.next_day:
                String afterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowEndDate) + 24 * 60 * 60 * 1000);
                mShowEndDate = afterDay;
                yearTV.setText(mShowStartDate.substring(0, 4));
                dateTV.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                setBeforeAndAfterVisbility();
                pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
                initData();
                break;
            case R.id.rl_action_allCode:
                if (mCodeNameRecyclerView.getVisibility() == View.GONE) {
                    mCodeListAdapter.replaceData(parentBeans);
                    mCodeNameRecyclerView.setVisibility(View.VISIBLE);
                    isParent = true;
                } else {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_note_childtype:
                if (mCodeNameRecyclerView.getVisibility() == View.GONE) {
                    isParent = false;
                    mCodeListAdapter.replaceData(childBeans);
                    mCodeNameRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_action_yinkui_sort:
                setMoneySort();
                break;
            case R.id.rl_action_time_sort:
                setTimeSort();
                break;
            default:
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

    void setMoneySort() {
        mPlSort = !mPlSort;
        if (mPlSort) {
            mTvYinkuiSort.setVisibility(View.GONE);
            mTvYinkuiSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvYinkuiSort.setVisibility(View.VISIBLE);
            mTvYinkuiSortDown.setVisibility(View.GONE);
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
                pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
                initData();
                setBeforeAndAfterVisbility();
            }
        }
    }

    @Override
    public void onRefresh() {
        pageNum = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        initData();
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBills(pageNum, mShowStartDate, mShowEndDate, mTabType, parentBeans.get(parentIndex).getCode(), childBeans.get(childIndex).getCode());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class CodeListAdapter extends BaseQuickAdapter {

        public CodeListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            BaseSelectedBean LotterySimpleBean = (BaseSelectedBean) item;
            helper.setText(R.id.tv_code_name, LotterySimpleBean.getName());
            if (isParent && LotterySimpleBean.getCode().equals(parentBeans.get(parentIndex).getCode())) {
                ((TextView) helper.getView(R.id.tv_code_name)).setTextColor(getResources().getColor(R.color.red_btn_bg));
            } else if (!isParent && LotterySimpleBean.getCode().equals(childBeans.get(childIndex).getCode())) {
                ((TextView) helper.getView(R.id.tv_code_name)).setTextColor(getResources().getColor(R.color.red_btn_bg));
            } else {
                helper.setTextColor(R.id.tv_code_name, getResources().getColor(R.color.color_gray_666666));
            }
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                    if (isParent) {
                        parentIndex = parentBeans.indexOf(item);
                        childIndex = 0;
                        childBeans = BillHistUtl.getChildByCode(mTabType, parentBeans.get(parentIndex).getCode());
                        parentCodeTV.setText(LotterySimpleBean.getName());
                        childCodeTV.setText("全部");
                    } else {
                        childIndex = childBeans.indexOf(item);
                        childCodeTV.setText(LotterySimpleBean.getName());
                    }
                    filterData();
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
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }
}
