package com.dawoo.lotterybox.view.fragment.bill_details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.coretool.util.date.DateUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.activity.CalendarActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author jack
 * @date 18-2-18
 * 充值
 */

public class RechargeFragment extends BaseFragment implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.day_before_rl)
    RelativeLayout dayBeforeRl;
    @BindView(R.id.day_before_rlg)
    RelativeLayout dayBeforeRlg;
    @BindView(R.id.date_time)
    TextView date_time;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.data_day_rl)
    RelativeLayout dataDayRl;
    @BindView(R.id.next_day)
    TextView nextDay;
    @BindView(R.id.day_next)
    RelativeLayout dayNext;
    @BindView(R.id.day_nextg)
    RelativeLayout dayNextg;
    @BindView(R.id.game_record_species_tv)
    TextView gameRecordSpeciesTv;
    @BindView(R.id.game_record_species_rv)
    RelativeLayout gameRecordSpeciesRv;
    @BindView(R.id.bill_details_all_all)
    TextView billDetailsAllAll;
    @BindView(R.id.game_record_betting_total_rv)
    RelativeLayout gameRecordBettingTotalRv;
    @BindView(R.id.bill_details_all_balance)
    TextView billDetailsAllBalance;
    @BindView(R.id.game_record_winning_amount_tv_g)
    TextView gameRecordWinningAmountTvG;
    @BindView(R.id.game_record_winning_amount_rb)
    RelativeLayout gameRecordWinningAmountRb;
    @BindView(R.id.bill_details_all_time)
    TextView billDetailsAllTime;
    @BindView(R.id.game_record_profit_and_loss_tv_g)
    TextView gameRecordProfitAndLossTvG;
    @BindView(R.id.game_record_profit_and_loss_rv)
    RelativeLayout gameRecordProfitAndLossRv;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @BindView(R.id.bill_details_all_recharge_amount)
    TextView billDetailsAllRechargeAmount;
    @BindView(R.id.ll_bottom_bootom)
    LinearLayout llBottomBootom;
    private String START_Time;
    private String END_Time;
    private String Now_DayTime;
    private String CurrentDate;

    public RechargeFragment() {
    }

    @Override
    protected void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_billing_details_recharg, container, false);
        unbinder = ButterKnife.bind(this, v);
        initView();
        ClickListener();
        return v;
    }

    private void initView() {
        START_Time = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        END_Time = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
        date_time.setText(DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()).substring(0, 4));
        textView5.setText(DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()).substring(5, DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()).length()));
        CurrentDate = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateUtils.getFrontDay(new Date(), 30).getTime());
        Now_DayTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis());
    }

    private void ClickListener() {
        dayBeforeRl.setOnClickListener(this::onClick);
        dataDayRl.setOnClickListener(this::onClick);
        dayNext.setOnClickListener(this::onClick);
        billDetailsAllBalance.setOnClickListener(this::onClick);
        gameRecordWinningAmountTvG.setOnClickListener(this::onClick);
        billDetailsAllTime.setOnClickListener(this::onClick);
        gameRecordProfitAndLossTvG.setOnClickListener(this::onClick);
        billDetailsAllAll.setOnClickListener(this::onClick);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //前一天
            case R.id.day_before_rl:
                START_Time = DateTool.getTimeFromLong(DateTool.FMT_DATE, DateTool.getLongFromTime(DateTool.FMT_DATE, START_Time) - 24 * 60 * 60 * 60);
                Log.e("TAG", "NowDay+startTime" + START_Time);

                isShowSelectTimeComponent();

                break;

            //选择日期
            case R.id.data_day_rl:
                startActivityForResult(new Intent(getContext(), CalendarActivity.class)
                        .putExtra(CalendarActivity.START_TIME, DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis()))
                        .putExtra(CalendarActivity.END_TIME, DateTool.getTimeFromLong(DateTool.FMT_DATE, System.currentTimeMillis())), ConstantValue.CalendarRequestCode);
                break;

            //下一天
            case R.id.day_next:

                END_Time = DateTool.getNextDay(END_Time);
                isShowSelectTimeComponent();

                MSPropties.showMeg(getActivity(), "ssssssssssssssssssss");


                break;
            //余额　　　上
            case R.id.bill_details_all_balance:
                billDetailsAllBalance.setVisibility(View.GONE);
                gameRecordWinningAmountTvG.setVisibility(View.VISIBLE);
                break;
            //余额　　　下
            case R.id.game_record_winning_amount_tv_g:
                billDetailsAllBalance.setVisibility(View.VISIBLE);
                gameRecordWinningAmountTvG.setVisibility(View.GONE);
                break;

            //时间　　　上
            case R.id.bill_details_all_time:
                billDetailsAllTime.setVisibility(View.GONE);
                gameRecordProfitAndLossTvG.setVisibility(View.VISIBLE);
                break;

            //时间　　　下
            case R.id.game_record_profit_and_loss_tv_g:
                billDetailsAllTime.setVisibility(View.VISIBLE);
                gameRecordProfitAndLossTvG.setVisibility(View.GONE);
                break;
            //展示的全部的数据
            case R.id.bill_details_all_all:


                break;
            default:
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ConstantValue.CalendarRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                String[] times = data.getStringArrayExtra(CalendarActivity.RESULT_DATE_ARREY);
                START_Time = times[0];
                END_Time = times[1];
                date_time.setText(START_Time.substring(0, 4));


                Log.e("TAG", "onactivityforresult+START_Time      " + START_Time);
                Log.e("TAG", "onactivityforresult+END_Time      " + END_Time);
                isShowSelectTimeComponent();
            }
        }
    }


    private void isShowSelectTimeComponent() {
        if (!START_Time.equals(Now_DayTime)) {
            textView5.setText(START_Time.substring(5, START_Time.length()) + "/" + END_Time.substring(5, START_Time.length()));
        }

        if (START_Time.equals(CurrentDate)) {
            dayBeforeRl.setVisibility(View.GONE);
            dayBeforeRlg.setVisibility(View.VISIBLE);
        } else {
            dayBeforeRl.setVisibility(View.VISIBLE);
            dayBeforeRlg.setVisibility(View.GONE);
        }

        if (END_Time.equals(Now_DayTime)) {
            dayNext.setVisibility(View.GONE);
            dayNextg.setVisibility(View.VISIBLE);
        } else {
            dayNextg.setVisibility(View.GONE);
            dayNext.setVisibility(View.VISIBLE);
        }
    }


}

