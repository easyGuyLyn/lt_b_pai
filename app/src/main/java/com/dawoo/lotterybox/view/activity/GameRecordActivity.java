package com.dawoo.lotterybox.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
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
import com.dawoo.lotterybox.bean.CalendarExtraBean;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.lottery.LotterySimpleBean;
import com.dawoo.lotterybox.bean.record.AssetsBean;
import com.dawoo.lotterybox.bean.record.NoteRecordHisData;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;
import com.dawoo.lotterybox.bean.record.ProfitBean;
import com.dawoo.lotterybox.mvp.presenter.RecordPresenter;
import com.dawoo.lotterybox.mvp.view.INoteRecordHisView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.GameUtil;
import com.dawoo.lotterybox.util.NoteCalendarUtil;
import com.dawoo.lotterybox.util.GameUtil;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.record.NoteRecordDetailActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author jack
 * @date 18-2-15
 * 游戏记录
 */

public class GameRecordActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener, INoteRecordHisView {
    private static final int CHOOSE_CALENDAR_REQUEST_CODE = 001;

    @BindView(R.id.head_view)
    HeaderView mHeaderView;
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

    @BindView(R.id.tv_note_sort)
    TextView mTvNoteSort;

    @BindView(R.id.tv_yinkui_sort)
    TextView mTvYinkuiSort;

    @BindView(R.id.tv_time_sort)
    TextView mTvTimeSort;

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


    //底部
    @BindView(R.id.account_balance)
    TextView mAccountBalance;
    @BindView(R.id.winning_amount)
    TextView mWinningAmount;
    @BindView(R.id.iv_note_arrow)
    ImageView mIvNoteArrow;

    @BindView(R.id.note_total)
    TextView mNoteTotal;
    @BindView(R.id.yinKui)
    TextView mYinKui;

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

    private RecordPresenter mRecordPresenter;
    private CodeListAdapter mCodeListAdapter;
    private NoteRecordAdapter mNoteRecordAdapter;
    private List<NoteRecordHis> mHisList = new ArrayList<>();
    private List<CalendarExtraBean> mCalendarExtraBeanList = new ArrayList<>();//要传给日历的数据
    /**
     * 服务器搜索参数
     */
    private int mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
    private int mPageSize = ConstantValue.RECORD_LIST_PAGE_SIZE;
    private String mQueryStartDate;//开始下注时间  默认预加载31的数据
    private String mQueryEndDate;//最后下注时间  默认今天

    /**
     * 本地搜素参数
     */
    private String mCode = "";//当前搜素所需的彩种，默认未全部
    private boolean mNoteSort;//是否按投注排序
    private boolean mPlSort; //是否按盈亏排序
    private boolean mTimeSort;//是否按时间排序
    private String mShowStartDate;//开始下注时间  本地搜索时间  "yyyy-MM-dd"
    private String mShowEndDate;//最后下注时间  本地搜索时间  "yyyy-MM-dd"

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecordPresenter.onDestory();
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_game_record);

    }

    @Override
    protected void initViews() {
        mHeaderView.setHeader(getString(R.string.game_record), true);
        mStatusTabLayOut.setVisibility(View.GONE);
        mQueryStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime());
        mShowStartDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mShowEndDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        mTvYear.setText(mShowStartDate.substring(0, 4));
        mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
        initCodeNameRecyclerView();
        initNoteRecordRecyclerView();
        // 设置 下拉刷新，加载更多
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        setBeforeAndAfterVisbility();
    }

    private void initCodeNameRecyclerView() {
        mCodeListAdapter = new CodeListAdapter(R.layout.recyclerview_list_item_code_name_view);
        mCodeListAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mCodeNameRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(this, 8)));
        mCodeNameRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mCodeNameRecyclerView.setAdapter(mCodeListAdapter);
    }

    private void initNoteRecordRecyclerView() {
        mNoteRecordAdapter = new NoteRecordAdapter(R.layout.recyclerview_list_item_note_record_view);
        mNoteRecordAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mSwipeTarget.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mSwipeTarget.setAdapter(mNoteRecordAdapter);
        mNoteRecordAdapter.setNewData(mHisList);
    }

    @Override
    protected void initData() {
        if (mRecordPresenter == null) {
            mRecordPresenter = new RecordPresenter(this, this);
        }
        onRefresh();
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
                if (mShowStartDate.equals(mQueryStartDate)) {
                    SingleToast.showMsg("暂时只能为您展示30天内记录");
                    return;
                }
                String yesterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowStartDate) - 24 * 60 * 60 * 1000);
                mShowStartDate = yesterDay;
                mTvYear.setText(mShowStartDate.substring(0, 4));
                mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                initData();
                break;
            case R.id.rl_ActionCalendar:
                jumpToCalendar();
                break;
            case R.id.rl_actionTheDayAfter:
                if (mShowEndDate.equals(mShowEndDate)) {
                    return;
                }
                String afterDay = DateTool.getTimeFromLong(
                        DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, mShowEndDate) + 24 * 60 * 60 * 1000);
                mShowEndDate = afterDay;
                mTvYear.setText(mShowStartDate.substring(0, 4));
                mTvMd.setText(NoteCalendarUtil.reolveDate(mShowStartDate, mShowEndDate));
                initData();
                break;
            default:
        }
    }


    @OnClick(R.id.ll_assets)
    public void onBottomViewClicked() {
        if (mBottom3.getVisibility() == View.VISIBLE) {
            mIvNoteArrow.setImageResource(R.mipmap.img_gray_arrow_up);
            mBottom3.setVisibility(View.GONE);
            mBottom4.setVisibility(View.GONE);
        } else {
            mIvNoteArrow.setImageResource(R.mipmap.img_grayarrow_down);
            mBottom3.setVisibility(View.VISIBLE);
            mBottom4.setVisibility(View.VISIBLE);
        }
    }

    //跳转到日历
    private void jumpToCalendar() {
        Intent intent = new Intent(this, CalendarActivity.class);
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
                initData();
                setBeforeAndAfterVisbility();
            }
        }
    }


    private void setNoteSort() {
        mNoteSort = !mNoteSort;
        if (mNoteSort) {
            mTvNoteSort.setVisibility(View.GONE);
            mTvNoteSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvNoteSort.setVisibility(View.VISIBLE);
            mTvNoteSortDown.setVisibility(View.GONE);
        }
        GameUtil.sortFormNote(mNoteSort, mHisList);
        mNoteRecordAdapter.notifyDataSetChanged();
    }

    private void setPlSort() {
        mPlSort = !mPlSort;
        if (mPlSort) {
            mTvYinkuiSort.setVisibility(View.GONE);
            mTvYinkuiSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvYinkuiSort.setVisibility(View.VISIBLE);
            mTvYinkuiSortDown.setVisibility(View.GONE);
        }
        GameUtil.sortFormProfit(mPlSort, mHisList);
        mNoteRecordAdapter.notifyDataSetChanged();
    }

    private void setTimeSort() {
        mTimeSort = !mTimeSort;
        if (mTimeSort) {
            mTvTimeSort.setVisibility(View.GONE);
            mTvTimeSortDown.setVisibility(View.VISIBLE);
        } else {
            mTvTimeSort.setVisibility(View.VISIBLE);
            mTvTimeSortDown.setVisibility(View.GONE);
        }
        GameUtil.sortFormBetTime(mTimeSort, mHisList);
        mNoteRecordAdapter.notifyDataSetChanged();
    }


    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.setLoadingMore(true);
        mRecordPresenter.getOrders(mCode, mShowStartDate, mShowEndDate, mPageSize + "", mPageNumber + "");
    }

    @Override
    public void onRefresh() {
        if (mCodeListAdapter.getData().isEmpty()) {
            mRecordPresenter.getLottery();
        }
        if (!DataCenter.getInstance().isLogin()) {
            mSwipeToLoadLayout.setRefreshing(false);
            return;
        }
        mRecordPresenter.getRecentProfit("", mCode);
        mRecordPresenter.getAssets(mShowStartDate, mShowEndDate, "", mCode);
        mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        mRecordPresenter.getOrders(mCode, mShowStartDate, mShowEndDate, mPageSize + "", mPageNumber + "");
    }

    @Override
    public void onRefreshResult(Object o) {
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
        NoteRecordHisData noteRecordHisData = (NoteRecordHisData) o;
        if (noteRecordHisData.getData() == null || noteRecordHisData.getData().isEmpty()) {
            mSwipeToLoadLayout.setLoadMoreEnabled(false);
            noteRecordHisData.setData(new ArrayList<>());
        } else {
            mSwipeToLoadLayout.setLoadMoreEnabled(true);
        }
        if (mPageNumber == ConstantValue.RECORD_LIST_PAGE_NUMBER) {
            mHisList.clear();
        }
        mHisList.addAll(noteRecordHisData.getData());
        if (mHisList.size() >= noteRecordHisData.getExtend().getTotalCount()) {
            mSwipeToLoadLayout.setLoadMoreEnabled(false);
        }
        filterData();
        mPageNumber++;

    }


    //根据当前的  时间 彩种  状态  过滤数据
    private void filterData() {
        GameUtil.sortData(mNoteSort, mPlSort, mTimeSort, mHisList);
        mNoteRecordAdapter.notifyDataSetChanged();
    }


    @Override
    public void onLoadMoreResult(Object o) {

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
        mAccountBalance.setText(NumberFormaterUtils.formaterD2S(assetsBean.getBalance()));
        mWinningAmount.setText(NumberFormaterUtils.formaterD2S(assetsBean.getPayout()));
        mNoteTotal.setText(NumberFormaterUtils.formaterD2S(assetsBean.getBetAmount()));
        mYinKui.setText(NumberFormaterUtils.formaterD2S(assetsBean.getProfit()));
        mNoteAvail.setText(NumberFormaterUtils.formaterD2S(assetsBean.getEffective()));
        mRebate.setText(NumberFormaterUtils.formaterD2S(assetsBean.getRebate()));
        mUnopenPrizeAmount.setText(NumberFormaterUtils.formaterD2S(assetsBean.getUnOpen()));
        mCancelOrder.setText(NumberFormaterUtils.formaterD2S(assetsBean.getRevoke()));

        //上面日历
        mTvYinKuiDayBefore.setText(getString(R.string.account_yinKui, NumberFormaterUtils.formaterD2S(assetsBean.getBeforeProfit())));
        mTvYinKuiDayAfter.setText(getString(R.string.account_yinKui, NumberFormaterUtils.formaterD2S(assetsBean.getAfterProfit())));
        mTvYinKuiMd.setText(getString(R.string.account_yinKui, NumberFormaterUtils.formaterD2S(assetsBean.getProfit())));
        setBeforeAndAfterVisbility();
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
                    initData();
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
                helper.setText(R.id.tv_item_pl_result, NumberFormaterUtils.formaterD2S(recordHis.getPayout()));
            } else {
                helper.setText(R.id.tv_item_pl_result, NumberFormaterUtils.formaterD2S(recordHis.getProfit()));
            }
            helper.setText(R.id.tv_item_time_yyMMdd, DateTool.getTimeFromLong(DateTool.FMT_DATE, recordHis.getBetTime()));
            helper.setText(R.id.tv_item_time_HHmmss, DateTool.getTimeFromLong(DateTool.FMT_TIME_3, recordHis.getBetTime()));

            if (recordHis.getStatus().equals("pending")) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_unLottery));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_blue));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_blue));
            } else if (recordHis.getStatus().equals("wining")) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_winning));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_green));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_green));
            } else if (recordHis.getStatus().equals("nowin")) {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_lost));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.history_item_red));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.history_item_red));
            } else {
                helper.setText(R.id.tv_item_prize_status, getResources().getString(R.string.note_status_is_cancle));
                helper.setTextColor(R.id.tv_item_pl_result, getResources().getColor(R.color.colorPrimary));
                helper.setTextColor(R.id.tv_item_prize_status, getResources().getColor(R.color.colorPrimary));
            }

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteRecordDetailActivity.goNoteRecordDetailActivity(GameRecordActivity.this,recordHis.getId(),NoteRecordDetailActivity.ORIGN_SELF);
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