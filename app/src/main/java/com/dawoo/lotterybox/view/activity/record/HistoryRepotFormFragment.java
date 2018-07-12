package com.dawoo.lotterybox.view.activity.record;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.dawoo.lotterybox.bean.CalendarExtraBean;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.lottery.LotterySimpleBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;
import com.dawoo.lotterybox.bean.record.NoteRecordHisData;
import com.dawoo.lotterybox.bean.record.ProfitBean;
import com.dawoo.lotterybox.mvp.presenter.RecordPresenter;
import com.dawoo.lotterybox.mvp.view.INoteRecordHisView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.NoteCalendarUtil;
import com.dawoo.lotterybox.util.RecordFilterDataUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.anim.HistoryReportAnimSetUtil;
import com.dawoo.lotterybox.view.activity.CalendarActivity;
import com.dawoo.lotterybox.view.activity.GameRecordActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by archar on 18-2-7.
 * <p>
 * 投注  历史报表
 */

public class HistoryRepotFormFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, INoteRecordHisView {

    private static final int CHOOSE_CALENDAR_REQUEST_CODE = 001;
    @BindView(R.id.status_tabLayOut)
    TabLayout mStatusTabLayOut;
    Unbinder unbinder;
    @BindView(R.id.tv_note_theDayBefore)
    TextView mTvNoteTheDayBefore;
    @BindView(R.id.tv_yinKui_dayBefore)
    TextView mTvYinKuiDayBefore;
    @BindView(R.id.rl_actionTheDayBefore)
    RelativeLayout mRlActionTheDayBefore;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    @BindView(R.id.tv_md)
    TextView mTvMd;
    @BindView(R.id.tv_yinKui_md)
    TextView mTvYinKuiMd;
    @BindView(R.id.rl_ActionCalendar)
    RelativeLayout mRlActionCalendar;
    @BindView(R.id.tv_note_theDayAfter)
    TextView mTvNoteTheDayAfter;
    @BindView(R.id.tv_yinKui_dayAfter)
    TextView mTvYinKuiDayAfter;
    @BindView(R.id.rl_actionTheDayAfter)
    RelativeLayout mRlActionTheDayAfter;
    @BindView(R.id.tv_note_allCode)
    TextView mTvNoteAllCode;
    @BindView(R.id.rl_action_allCode)
    RelativeLayout mRlActionAllCode;
    @BindView(R.id.tv_note_sort)
    TextView mTvNoteSort;
    @BindView(R.id.rl_action_note_sort)
    RelativeLayout mRlActionNoteSort;
    @BindView(R.id.tv_yinkui_sort)
    TextView mTvYinkuiSort;
    @BindView(R.id.rl_action_yinkui_sort)
    RelativeLayout mRlActionYinkuiSort;
    @BindView(R.id.tv_time_sort)
    TextView mTvTimeSort;
    @BindView(R.id.rl_action_time_sort)
    RelativeLayout mRlActionTimeSort;
    @BindView(R.id.tv_note_sort_down)
    TextView mTvNoteSortDown;
    @BindView(R.id.tv_yinkui_sort_down)
    TextView mTvYinkuiSortDown;
    @BindView(R.id.tv_time_sort_down)
    TextView mTvTimeSortDown;
    @BindView(R.id.ry_lottery_codeName)
    RecyclerView mCodeNameRecyclerView;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;

    //底部
    @BindView(R.id.account_balance)
    TextView mAccountBalance;
    @BindView(R.id.winning_amount)
    TextView mWinningAmount;
    @BindView(R.id.iv_note_arrow)
    ImageView mIvNoteArrow;
    @BindView(R.id.bottom_1)
    LinearLayout mBottom1;
    @BindView(R.id.note_total)
    TextView mNoteTotal;
    @BindView(R.id.yinKui)
    TextView mYinKui;
    @BindView(R.id.bottom_2)
    LinearLayout mBottom2;
    @BindView(R.id.note_avail)
    TextView mNoteAvail;
    @BindView(R.id.rebate)
    TextView mRebate;
    @BindView(R.id.bottom_3)
    LinearLayout mBottom3;
    @BindView(R.id.unopen_prize_amount)
    TextView mUnopenPrizeAmount;
    @BindView(R.id.cancel_order)
    TextView mCancelOrder;
    @BindView(R.id.bottom_4)
    LinearLayout mBottom4;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.ll_assets)
    LinearLayout mll_assets;
    @BindView(R.id.tv_cancle_type)
    TextView mTvCancleType;

    public static final String all = "";
    public static final String pending = "pending";//未开奖
    public static final String wining = "wining";//已中奖
    public static final String nowin = "nowin";//未中奖
    public static final String revoke_sys = "revoke_sys";//系统撤单
    public static final String revoke_self = "revoke_self";//主动撤单
    public static final String revocation = "revocation";//系统撤销
    public static final String[] status = new String[]{all, pending, wining, nowin, revoke_sys, revoke_self, revocation};


    private RecordPresenter mRecordPresenter;
    private String[] mTabStatusArray;
    private CodeListAdapter mCodeListAdapter;
    private NoteRecordAdapter mNoteRecordAdapter;
    private List<NoteRecordHis> mHisListShow = new ArrayList<>();//列表的数据集
    private List<CalendarExtraBean> mCalendarExtraBeanList = new ArrayList<>();//要传给日历的30天的list数据

    private int mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
    private int mPageSize = ConstantValue.RECORD_LIST_PAGE_SIZE;

    private String mTabType = "";//当前的状态类型
    private String mCode = "";//当前搜素所需的彩种，默认未全部
    private boolean mNoteSort;//是否按投注排序
    private boolean mPlSort; //是否按盈亏排序
    private boolean mTimeSort;//是否按时间排序
    private String mShowStartDate;//开始下注时间  本地搜索时间  "yyyy-MM-dd"
    private String mShowEndDate;//最后下注时间  本地搜索时间  "yyyy-MM-dd"

    private AnimatorSet mAnimSetBottom = new AnimatorSet();
    private AnimatorSet mAnimSetBottomIcon = new AnimatorSet();

    public HistoryRepotFormFragment() {
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        mRecordPresenter.onDestory();
        RxBus.get().unregister(this);
        mAnimSetBottom.cancel();
        mAnimSetBottomIcon.cancel();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history_report_form, container, false);
        RxBus.get().register(this);
        unbinder = ButterKnife.bind(this, v);
        initViews();
        initData();
        initListener();
        return v;
    }


    private void initViews() {
        mTabStatusArray = getResources().getStringArray(R.array.note_tab_status);
        mShowStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mTvYear.setText(mShowStartDate.substring(0, 4));
        mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
        initCodeNameRecyclerView();
        initNoteRecordRecyclerView();
        setBeforeAndAfterVisbility();
        mBottom3.setVisibility(View.VISIBLE);
        mBottom4.setVisibility(View.VISIBLE);
        HistoryReportAnimSetUtil.setMargins(mll_assets, 0, 0, 0, DensityUtil.dp2px(mContext, -40));
        // 设置 下拉刷新，加载更多
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    private void initCodeNameRecyclerView() {
        mCodeListAdapter = new CodeListAdapter(R.layout.recyclerview_list_item_code_name_view);
        mCodeListAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mCodeNameRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(mContext, 8)));
        mCodeNameRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mCodeNameRecyclerView.setAdapter(mCodeListAdapter);
    }

    private void initNoteRecordRecyclerView() {
        mNoteRecordAdapter = new NoteRecordAdapter(R.layout.recyclerview_list_item_note_record_view);
        mNoteRecordAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mSwipeTarget.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mSwipeTarget.setAdapter(mNoteRecordAdapter);
        mNoteRecordAdapter.setNewData(mHisListShow);
    }

    private void initListener() {
        initTabListener();
        setBottomAnimListener();
    }

    private void initData() {
        mRecordPresenter = new RecordPresenter(mContext, this);
        mSwipeToLoadLayout.setRefreshing(true);
        onRefresh();
        initTab();
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
                if (tab.getPosition() == 0) {
                    mTabType = "";
                } else {
                    mTabType = tab.getPosition() + "";
                }
                onRefresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    /**
     * 初始化 tab ui
     */
    private void initTab() {
        for (int i = 0; i < mTabStatusArray.length; i++) {
            if (i == 0) {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(mTabStatusArray[i]), true);
            } else {
                mStatusTabLayOut.addTab(mStatusTabLayOut.newTab().setText(mTabStatusArray[i]), false);
            }
        }
    }


    @OnClick({R.id.rl_actionTheDayBefore, R.id.rl_ActionCalendar, R.id.rl_actionTheDayAfter, R.id.rl_action_allCode, R.id.rl_action_note_sort, R.id.rl_action_yinkui_sort, R.id.rl_action_time_sort})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.rl_action_allCode) {
            if (mCodeNameRecyclerView.getVisibility() == View.GONE) {
                mCodeNameRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mCodeNameRecyclerView.setVisibility(View.GONE);
            }
            return;
        } else if (view.getId() == R.id.rl_action_note_sort) {
            setNoteSort();
            return;
        } else if (view.getId() == R.id.rl_action_yinkui_sort) {
            setPlSort();
            return;
        } else if (view.getId() == R.id.rl_action_time_sort) {
            setTimeSort();
            return;
        }

        if (!DataCenter.getInstance().isLogin()) {
            ActivityUtil.startLoginActivity();
            return;
        }

        switch (view.getId()) {
            case R.id.rl_actionTheDayBefore:
                String yesterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowStartDate) - 24 * 60 * 60 * 1000);
                mShowStartDate = yesterDay;
                mTvYear.setText(mShowStartDate.substring(0, 4));
                mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                onRefresh();
                break;
            case R.id.rl_ActionCalendar:
                jumpToCalendar();
                break;
            case R.id.rl_actionTheDayAfter:
                String afterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowEndDate) + 24 * 60 * 60 * 1000);
                mShowEndDate = afterDay;
                mTvYear.setText(mShowStartDate.substring(0, 4));
                mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                onRefresh();
                break;
            default:
        }
    }

    //前一天  后一天的  显示和隐藏
    private void setBeforeAndAfterVisbility() {
        if (mShowStartDate.equals(DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime()))) {
            mRlActionTheDayBefore.setClickable(false);
            mTvNoteTheDayBefore.setVisibility(View.GONE);
            mTvYinKuiDayBefore.setVisibility(View.GONE);
        } else {
            mRlActionTheDayBefore.setClickable(true);
            mTvNoteTheDayBefore.setVisibility(View.VISIBLE);
            mTvYinKuiDayBefore.setVisibility(View.VISIBLE);
        }

        if (mShowEndDate.equals(DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()))) {
            mRlActionTheDayAfter.setClickable(false);
            mTvYinKuiDayAfter.setVisibility(View.GONE);
            mTvNoteTheDayAfter.setVisibility(View.GONE);
        } else {
            mRlActionTheDayAfter.setClickable(true);
            mTvYinKuiDayAfter.setVisibility(View.VISIBLE);
            mTvNoteTheDayAfter.setVisibility(View.VISIBLE);
        }
    }

    private boolean isOpen;

    @OnClick(R.id.ll_assets)
    public void onBottomViewClicked() {
        if (!isOpen) {
            HistoryReportAnimSetUtil.doAnimAll(mll_assets, mIvNoteArrow, DensityUtil.dp2px(mContext, -40), mAnimSetBottom, mAnimSetBottomIcon);
        } else {
            HistoryReportAnimSetUtil.doAnimAll(mll_assets, mIvNoteArrow, DensityUtil.dp2px(mContext, 40), mAnimSetBottom, mAnimSetBottomIcon);
        }
    }

    //坚挺动画
    private void setBottomAnimListener() {
        mAnimSetBottomIcon.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mll_assets.setClickable(false);
                if (!isOpen) {
                    mIvNoteArrow.setImageResource(R.mipmap.img_gray_arrow_up);
                } else {
                    mIvNoteArrow.setImageResource(R.mipmap.img_grayarrow_down);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mll_assets.setClickable(true);
                if (isOpen) {
                    mIvNoteArrow.setImageResource(R.mipmap.img_grayarrow_down);
                } else {
                    mIvNoteArrow.setImageResource(R.mipmap.img_gray_arrow_up);
                }
                isOpen = !isOpen;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mll_assets.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    //跳转到日历
    private void jumpToCalendar() {
        Intent intent = new Intent(getActivity(), CalendarActivity.class);
        intent.putParcelableArrayListExtra(CalendarActivity.CALENDAR_EXTRA_LIST, (ArrayList<? extends Parcelable>) mCalendarExtraBeanList);
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
                mTvYear.setText(mShowStartDate.substring(0, 4));
                mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                setBeforeAndAfterVisbility();
                onRefresh();
            }
        }
    }


    private void setNoteSort() {
        mNoteSort = !mNoteSort;
        mPlSort = false;
        mTimeSort = false;
        if (mNoteSort) {
            mTvNoteSort.setVisibility(View.GONE);
            mTvNoteSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvNoteSort.setVisibility(View.VISIBLE);
            mTvNoteSortDown.setVisibility(View.GONE);
        }
        mTvYinkuiSort.setVisibility(View.VISIBLE);
        mTvYinkuiSortDown.setVisibility(View.GONE);
        mTvTimeSort.setVisibility(View.VISIBLE);
        mTvTimeSortDown.setVisibility(View.GONE);
        filterData();
    }

    private void setPlSort() {
        mPlSort = !mPlSort;
        mNoteSort = false;
        mTimeSort = false;
        if (mPlSort) {
            mTvYinkuiSort.setVisibility(View.GONE);
            mTvYinkuiSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvYinkuiSort.setVisibility(View.VISIBLE);
            mTvYinkuiSortDown.setVisibility(View.GONE);
        }
        mTvNoteSort.setVisibility(View.VISIBLE);
        mTvNoteSortDown.setVisibility(View.GONE);
        mTvTimeSort.setVisibility(View.VISIBLE);
        mTvTimeSortDown.setVisibility(View.GONE);
        filterData();
    }

    private void setTimeSort() {
        mTimeSort = !mTimeSort;
        mPlSort = false;
        mNoteSort = false;
        if (mTimeSort) {
            mTvTimeSort.setVisibility(View.GONE);
            mTvTimeSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvTimeSort.setVisibility(View.VISIBLE);
            mTvTimeSortDown.setVisibility(View.GONE);
        }
        mTvYinkuiSort.setVisibility(View.VISIBLE);
        mTvYinkuiSortDown.setVisibility(View.GONE);
        mTvNoteSort.setVisibility(View.VISIBLE);
        mTvNoteSortDown.setVisibility(View.GONE);
        filterData();
    }


    @Override
    public void onLoadMore() {
        if (!DataCenter.getInstance().isLogin()) {
            SingleToast.showMsg("请先登录～");
            mSwipeToLoadLayout.setLoadingMore(false);
            return;
        }
        mSwipeToLoadLayout.setLoadingMore(true);
        mRecordPresenter.getMoreOrders(mCode, "", mTabType
                , mShowStartDate, mShowEndDate, mPageSize + "", mPageNumber + "", "");
    }

    @Override
    public void onRefresh() {
        if (mCodeListAdapter.getData().isEmpty()) {
            mRecordPresenter.getLottery();
        }

        if (!DataCenter.getInstance().isLogin()) {
            SingleToast.showMsg(getString(R.string.plea_login));
            mSwipeToLoadLayout.setRefreshing(false);
            return;
        }
        mCalendarExtraBeanList.clear();
        if (mTabType.equals("2")) {
            mRecordPresenter.getRecentProfit("1", mCode);
        } else if (mTabType.equals("3")) {
            mRecordPresenter.getRecentProfit("2", mCode);
        } else if (mTabType.equals("")) {
            mRecordPresenter.getRecentProfit("", mCode);
        }
        mRecordPresenter.getAssets(mShowStartDate, mShowEndDate, mTabType, mCode);
        mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        mRecordPresenter.getOrders(mCode, "", mTabType
                , mShowStartDate, mShowEndDate, mPageSize + "", mPageNumber + "", "");
    }

    @Override
    public void onRefreshResult(Object o) {
        NoteRecordHisData noteRecordHisData = (NoteRecordHisData) o;
        mHisListShow = noteRecordHisData.getData();
        filterData();
        mPageNumber++;
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
    }


    //根据当前的  时间  过滤数据
    private void filterData() {
        RecordFilterDataUtils.sortData(mNoteSort, mPlSort, mTimeSort, mHisListShow);
        mNoteRecordAdapter.setNewData(mHisListShow);
    }


    @Override
    public void onLoadMoreResult(Object o) {
        NoteRecordHisData noteRecordHisData = (NoteRecordHisData) o;
        List<NoteRecordHis> list = noteRecordHisData.getData();
        if (mHisListShow.size() >= noteRecordHisData.getExtend().getTotalCount()) {
            if (mSwipeToLoadLayout.isLoadingMore()) {
                mSwipeToLoadLayout.setLoadingMore(false);
            }
            SingleToast.showMsg(getString(R.string.NO_MORE_DATA));
            return;
        }
        if (list != null && list.size() > 0) {
            mHisListShow.addAll(list);
            filterData();
            mPageNumber++;
        }

        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void onLotteryDataResult(Object o) {
        List<LotterySimpleBean> list = (List<LotterySimpleBean>) o;
        if (list != null && list.size() > 0) {
            LotterySimpleBean lotterySimpleBean = new LotterySimpleBean();
            lotterySimpleBean.setName(getString(R.string.note_status_all));
            lotterySimpleBean.setCode("");
            list.add(0, lotterySimpleBean);
        }
        mCodeListAdapter.setNewData(list);
    }

    @Override
    public void onAssetsResult(Object o) {
        AssetsBean assetsBean = (AssetsBean) o;

        //底部
        mAccountBalance.setText(BalanceUtils.getScalsBalance(assetsBean.getBalance() + ""));
        DataCenter.getInstance().getUser().setBalance(mAccountBalance.getText().toString());
        RxBus.get().post(ConstantValue.EVENT_TYPE_USER_INFO, "mcfragment_user_info");
        mWinningAmount.setText(BalanceUtils.getScalsBalance(assetsBean.getPayout() + ""));
        mNoteTotal.setText(BalanceUtils.getScalsBalance(assetsBean.getBetAmount() + ""));
        mYinKui.setText(BalanceUtils.getScalsBalance(assetsBean.getProfit() + ""));
        mNoteAvail.setText(BalanceUtils.getScalsBalance(assetsBean.getEffective() + ""));
        mRebate.setText(BalanceUtils.getScalsBalance(assetsBean.getRebate() + ""));
        mUnopenPrizeAmount.setText(BalanceUtils.getScalsBalance(assetsBean.getUnOpen() + ""));
        if (mTabType.equals("5")) {
            mTvCancleType.setText(getString(R.string.cancel_order_back));
            mCancelOrder.setText(BalanceUtils.getScalsBalance(assetsBean.getRepeal() + ""));
        } else {
            mTvCancleType.setText(getString(R.string.cancel_order));
            mCancelOrder.setText(BalanceUtils.getScalsBalance(assetsBean.getRevoke() + ""));
        }

        //上面日历
        mTvYinKuiDayBefore.setText(getString(R.string.account_yinKui, BalanceUtils.getScalsBalance(assetsBean.getBeforeProfit() + "")));
        mTvYinKuiDayAfter.setText(getString(R.string.account_yinKui, BalanceUtils.getScalsBalance(assetsBean.getAfterProfit() + "")));
        mTvYinKuiMd.setText(getString(R.string.account_yinKui, BalanceUtils.getScalsBalance(assetsBean.getProfit() + "")));

        setBeforeAndAfterVisbility();
    }

    @Override
    public void onRecentProfit(Object o) {
        List<ProfitBean> list = (List<ProfitBean>) o;
        setProfitData(list);
    }

    //得到每天的盈亏数据
    private void setProfitData(List<ProfitBean> list) {
        mCalendarExtraBeanList.clear();
        for (ProfitBean profitBean : list) {
            CalendarExtraBean calendarExtraBean = new CalendarExtraBean();
            calendarExtraBean.setValue(profitBean.getProfit());
            calendarExtraBean.setTime(DateTool.getLongFromTime(DateTool.FMT_DATE, profitBean.getTdate()));
            mCalendarExtraBeanList.add(calendarExtraBean);
        }
    }


    private class CodeListAdapter extends BaseQuickAdapter {

        public CodeListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            LotterySimpleBean LotterySimpleBean = (LotterySimpleBean) item;
            helper.setText(R.id.tv_code_name, LotterySimpleBean.getName());
            if (LotterySimpleBean.getCode().equals("") && mCode.equals("")) {
                ((TextView) helper.getView(R.id.tv_code_name)).setTextColor(getResources().getColor(R.color.red_btn_bg));
            } else if (LotterySimpleBean.getCode().equals(mCode)) {
                ((TextView) helper.getView(R.id.tv_code_name)).setTextColor(getResources().getColor(R.color.red_btn_bg));
            }

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCodeNameRecyclerView.setVisibility(View.GONE);
                    mCode = LotterySimpleBean.getCode();
                    mTvNoteAllCode.setText(LotterySimpleBean.getName());
                    onRefresh();
                    mCodeListAdapter.notifyDataSetChanged();
                }
            });
        }
    }


    private class NoteRecordAdapter extends BaseQuickAdapter {

        public NoteRecordAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            NoteRecordHis recordHis = (NoteRecordHis) item;
            helper.setText(R.id.tv_item_codeName, recordHis.getCodeName());
            helper.setText(R.id.tv_item_codeExpect, mContext.getResources().getString(R.string.the_expect, recordHis.getExpect()));
            helper.setText(R.id.tv_item_betCode, recordHis.getPlayName());
            helper.setText(R.id.tv_item_betNum, recordHis.getBetNum());
            if (recordHis.getPayout() > 0) {
                helper.setText(R.id.tv_item_pl_result, BalanceUtils.getScalsBalance(recordHis.getPayout() + ""));
            } else {
                helper.setText(R.id.tv_item_pl_result, BalanceUtils.getScalsBalance(recordHis.getProfit() + ""));
            }

            helper.setText(R.id.tv_item_time_yyMMdd, DateTool.getTimeFromLong(DateTool.FMT_DATE, recordHis.getBetTime()));
            helper.setText(R.id.tv_item_time_HHmmss, DateTool.getTimeFromLong(DateTool.FMT_TIME_3, recordHis.getBetTime()));

            if (recordHis.getStatus().equals(status[1])) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_unLottery));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_blue));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_blue));
            } else if (recordHis.getStatus().equals(status[2])) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_winning));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_green));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_green));
            } else if (recordHis.getStatus().equals(status[3])) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_lost));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_red));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_red));
            } else if (recordHis.getStatus().equals(status[6])) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_is_cancle_back));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.colorPrimary));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.colorPrimary));
                helper.setText(R.id.tv_item_pl_result,"");
            } else {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_is_cancle));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.colorPrimary));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.colorPrimary));
                helper.setText(R.id.tv_item_pl_result,"");
            }

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteRecordDetailActivity.goNoteRecordDetailActivity(getActivity(),recordHis.getId(),NoteRecordDetailActivity.ORIGN_SELF);
                }
            });

            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.bgColor));
            }
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        LogUtils.d(s);
        //  收起刷新
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    /**
     * 登录成功后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGINED)})
    public void onLoginCallBack(String s) {
        // 登录成功后加载
        onRefresh();
    }
}
