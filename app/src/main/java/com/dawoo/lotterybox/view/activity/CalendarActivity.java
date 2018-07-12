package com.dawoo.lotterybox.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.coretool.util.date.DateUtils;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.CalendarDay;
import com.dawoo.lotterybox.bean.CalendarExtraBean;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by archar on 18-2-14.
 */

public class CalendarActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.rv_calendar_all)
    RecyclerView mRvCalendarAll;

    public static final String CALENDAR_EXTRA_LIST = "calendar_extra_list";
    public static final String START_TIME = "start_time";//传进来的开始时间 yyyy-MM-dd
    public static final String END_TIME = "end_time";//传进来的结束时间  yyyy-MM-dd
    public static final String RESULT_DATE_ARREY = "date_array";//传出的时间数组

    private CalendarAllAdapter mCalendarAllAdapter;
    private String mStartTime = "";//传进来的开始时间 yyyy-MM-dd
    private String mEndTime = ""; //传进来的结束时间  yyyy-MM-dd


    private List<CalendarExtraBean> mCalendarExtraBeanList = new ArrayList<>();//日历额外数据的list
    private List<String> mYMList = new ArrayList<>();//有几个月的数据
    private List<CalendarDay> mCurrentMonthDays = new ArrayList<>();//当月的天的集合
    private int mCurrentFisDayWeek; //当月的第一天是星期几
    private List<CalendarDay> mLastMonthDays = new ArrayList<>();//上个月的天的集合
    private int mLastFistDayWeek;//上个月的第一天是星期几
    private long startDay;//31天内的开始的时间
    private long currentDay;//31天内的结束的时间
    private String mCurrentMonth;//当前的月
    private String mLastMonth;//上个月
    private Handler mHandler = new Handler();

    private CalendarDay[] selectTimeArr = new CalendarDay[2];//开始时间和结束时间
    private volatile int count = 1;
    private boolean mShowExtraData;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_calendar);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.title_calendar), true);
        mCalendarAllAdapter = new CalendarAllAdapter(R.layout.layout_item_custom_calendar);
        mCalendarAllAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mRvCalendarAll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        mRvCalendarAll.setAdapter(mCalendarAllAdapter);
    }

    @Override
    protected void initData() {
        if (getIntent().getParcelableArrayListExtra(CALENDAR_EXTRA_LIST) != null) {
            mCalendarExtraBeanList = getIntent().getParcelableArrayListExtra(CALENDAR_EXTRA_LIST);
            if (mCalendarExtraBeanList.size() == 0) {
                mShowExtraData = false;
            } else {
                mShowExtraData = true;
            }
            Log.e("lynnn", mCalendarExtraBeanList.size() + "");
        } else {
            mShowExtraData = false;
        }
        if (getIntent().getStringExtra(START_TIME) != null) {
            mStartTime = getIntent().getStringExtra(START_TIME);
            Log.e("lynzz mStartTime", mStartTime + "");
        }
        if (getIntent().getStringExtra(END_TIME) != null) {
            mEndTime = getIntent().getStringExtra(END_TIME);
        }
        setData();
    }


    private void setData() {
        currentDay = new Date().getTime();
        startDay = (DateUtils.getFrontDay(new Date(currentDay), 31)).getTime();


        String currentMonthStartTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getBeginDayOfMonth().getTime());
        String currentMonthEndTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getEndDayOfMonth().getTime());
        mCurrentFisDayWeek = DateTool.getWeekByDateStr(currentMonthStartTime);
        String ymStart = currentMonthStartTime.substring(0, 7);
        mCurrentMonth = currentMonthStartTime.substring(5, 7);

        String lastMonthStartTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getBeginDayOfLastMonth().getTime());
        String lastMonthEndTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getEndDayOfLastMonth().getTime());
        mLastFistDayWeek = DateTool.getWeekByDateStr(lastMonthStartTime);
        String ymEnd = lastMonthStartTime.substring(0, 7);
        mLastMonth = lastMonthStartTime.substring(5, 7);

        mYMList.add(ymStart);
        mYMList.add(ymEnd);


        int currentStartD = Integer.parseInt(currentMonthStartTime.substring(8, 10));
        int currentEndD = Integer.parseInt(currentMonthEndTime.substring(8, 10));
        int allStartDays = currentEndD - currentStartD + 1;
        resolveMonthDays(allStartDays, ymStart, mCurrentMonthDays, 1);


        int lastStartD = Integer.parseInt(lastMonthStartTime.substring(8, 10));
        int lastEndD = Integer.parseInt(lastMonthEndTime.substring(8, 10));
        int allLastDays = lastEndD - lastStartD + 1;

        resolveMonthDays(allLastDays, ymEnd, mLastMonthDays, 2);

        mCalendarAllAdapter.setNewData(mYMList);
    }

    /**
     * 处理每月的数据
     *
     * @param dayLength
     * @param YM
     * @param monthDays
     * @param count
     */
    private void resolveMonthDays(int dayLength, String YM, List<CalendarDay> monthDays, int count) {
        for (int i = 1; i < dayLength + 1; i++) {
            CalendarDay calendarDay = new CalendarDay();
            calendarDay.setDayNum(i + "");
            if (i < 10) {
                calendarDay.setTime(DateTool.getLongFromTime(DateTool.FMT_DATE, YM + "-0" + i));
            } else {
                calendarDay.setTime(DateTool.getLongFromTime(DateTool.FMT_DATE, YM + "-" + i));
            }

            if (calendarDay.getTime() >= startDay && calendarDay.getTime() <= currentDay) {
                calendarDay.setArea(true);
            } else {
                calendarDay.setArea(false);
            }

            if (DateTool.getTimeFromLong(DateTool.FMT_DATE, calendarDay.getTime()).equals(mStartTime)
                    || DateTool.getTimeFromLong(DateTool.FMT_DATE, calendarDay.getTime()).equals(mEndTime)) {
                calendarDay.setSelected(true);
                Log.e("lynzz", calendarDay.getDayNum() + "");
            }

            for (CalendarExtraBean calendarExtraBean : mCalendarExtraBeanList) {
                String betTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, calendarExtraBean.getTime());
                if (i < 10) {
                    if (betTime.equals(YM + "-0" + i)) {
                        calendarDay.setData1(calendarExtraBean.getValue() + "");
                    }
                } else {
                    if (betTime.equals(YM + "-" + i)) {
                        calendarDay.setData1(calendarExtraBean.getValue() + "");
                    }
                }
            }
            monthDays.add(calendarDay);
        }

        int addData = 0;
        if (count == 1) {
            addData = mCurrentFisDayWeek - 1;
        } else if (count == 2) {
            addData = mLastFistDayWeek - 1;
        }
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (int i = 0; i < addData; i++) {
            calendarDays.add(new CalendarDay());
        }
        monthDays.addAll(0, calendarDays);//后移几个数据
    }


    private class CalendarAllAdapter extends BaseQuickAdapter {

        public CalendarAllAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            String content = (String) item;

            helper.setText(R.id.tv_ym, DateTool.getTimeFromLong(
                    DateTool.FMT_DATE_ZN, DateTool.getLongFromTime(DateTool.FMT_DATE_YM, content)));

            RecyclerView rv_calendar = helper.getView(R.id.rv_calendar);
            rv_calendar.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
            CalendarAdapter calendarAdapter = new CalendarAdapter(R.layout.layout_calendar_item);
            if (rv_calendar.getItemDecorationCount() == 0) {
                rv_calendar.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(mContext, 4)));
            }
            rv_calendar.setAdapter(calendarAdapter);
            if (helper.getAdapterPosition() == 0) {
                calendarAdapter.setNewData(mCurrentMonthDays);
            } else if (helper.getAdapterPosition() == 1) {
                calendarAdapter.setNewData(mLastMonthDays);
            }
        }
    }


    private class CalendarAdapter extends BaseQuickAdapter {
        public CalendarAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            CalendarDay calendarDay = (CalendarDay) item;
            LinearLayout ll_calendar_item_bg = helper.getView(R.id.ll_calendar_item_bg);
            TextView tv_num_calendar = helper.getView(R.id.tv_num_calendar);
            TextView tv_data1 = helper.getView(R.id.tv_data1);

            if (calendarDay.getDayNum() == null) {
                ll_calendar_item_bg.setBackgroundColor(getResources().getColor(R.color.white));
                tv_num_calendar.setBackgroundColor(getResources().getColor(R.color.white));
                tv_data1.setTextColor(getResources().getColor(R.color.white));
                return;
            }

            tv_num_calendar.setText(calendarDay.getDayNum());

            if (TextUtils.isEmpty(calendarDay.getData1())) {
                tv_data1.setText("0.00");
            } else {
                tv_data1.setText(BalanceUtils.getScalsBalance(calendarDay.getData1()));
            }

            if (calendarDay.isArea()) {
                tv_data1.setVisibility(View.VISIBLE);
                if (calendarDay.isSelected()) {
                    ll_calendar_item_bg.setBackgroundColor(getResources().getColor(R.color.custom_qq));
                    tv_num_calendar.setTextColor(getResources().getColor(R.color.white));
                    tv_data1.setTextColor(getResources().getColor(R.color.white));
                } else {
                    if (DateTool.getTimeFromLong(DateTool.FMT_DATE, calendarDay.getTime())
                            .equals(DateTool.getTimeFromLong(DateTool.FMT_DATE, new Date().getTime()))) {
                        tv_num_calendar.setTextColor(getResources().getColor(R.color.red_btn_bg));
                    } else {
                        tv_num_calendar.setTextColor(getResources().getColor(R.color.custom_qq));
                    }
                    ll_calendar_item_bg.setBackgroundColor(getResources().getColor(R.color.custom_blue_light));
                    tv_data1.setTextColor(getResources().getColor(R.color.color_close_theme));

                }

                ll_calendar_item_bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ll_calendar_item_bg.setBackgroundColor(getResources().getColor(R.color.custom_qq));
                        tv_num_calendar.setTextColor(getResources().getColor(R.color.white));
                        tv_data1.setTextColor(getResources().getColor(R.color.white));

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                chooseData(calendarDay);
                            }
                        }, 1);
                    }
                });
            } else {
                tv_data1.setVisibility(View.GONE);
                ll_calendar_item_bg.setBackgroundColor(getResources().getColor(R.color.color_gray_efefef));
                tv_num_calendar.setTextColor(getResources().getColor(R.color.color_gray_666666));
            }

            if (!mShowExtraData) {
                tv_data1.setVisibility(View.GONE);
            }
        }
    }

    private synchronized void chooseData(CalendarDay calendarDay) {
        if (count == 1) {
            for (CalendarDay bean : mCurrentMonthDays) {
                bean.setSelected(false);
            }
            for (CalendarDay bean : mLastMonthDays) {
                bean.setSelected(false);
            }
        }


        String time = DateTool.getTimeFromLong(DateTool.FMT_DATE, calendarDay.getTime());
        String month = time.substring(5, 7);
        if (month.equals(mCurrentMonth)) {
            for (CalendarDay bean : mCurrentMonthDays) {
                if (bean.getTime() == calendarDay.getTime()) {
                    bean.setSelected(true);
                }
            }
            mCalendarAllAdapter.notifyDataSetChanged();
        } else {
            for (CalendarDay bean : mLastMonthDays) {
                if (bean.getTime() == calendarDay.getTime()) {
                    bean.setSelected(true);
                }
            }
            mCalendarAllAdapter.notifyDataSetChanged();
        }
        if (count == 1) {
            selectTimeArr[0] = calendarDay;
            count = 2;
        } else if (count == 2) {
            selectTimeArr[1] = calendarDay;
            count = 1;
            outPutData();
        }
    }

    /**
     * 返回数据到前一个页面
     */
    private void outPutData() {
        String[] resultArray = new String[2];
        if (selectTimeArr[0].getTime() == selectTimeArr[1].getTime()) {
            resultArray[0] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[0].getTime());
            resultArray[1] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[1].getTime());
        } else if (selectTimeArr[0].getTime() > selectTimeArr[1].getTime()) {
            resultArray[0] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[1].getTime());
            resultArray[1] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[0].getTime());
        } else {
            resultArray[0] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[0].getTime());
            resultArray[1] = DateTool.getTimeFromLong(DateTool.FMT_DATE, selectTimeArr[1].getTime());
        }

        Intent intent = new Intent();
        intent.putExtra(RESULT_DATE_ARREY, resultArray);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
