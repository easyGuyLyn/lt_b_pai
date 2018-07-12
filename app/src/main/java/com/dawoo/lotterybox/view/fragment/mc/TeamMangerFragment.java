package com.dawoo.lotterybox.view.fragment.mc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;
import com.dawoo.lotterybox.bean.TeamStateBean;
import com.dawoo.lotterybox.mvp.presenter.TeamPresserter;
import com.dawoo.lotterybox.mvp.view.ITeamStateView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.team.OpenAccountActivity;
import com.dawoo.lotterybox.view.activity.team.TeamBillRecordActivity;
import com.dawoo.lotterybox.view.activity.team.TeamMemberActivity;
import com.dawoo.lotterybox.view.activity.team.TeamReportFromActivity;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.SmartRefreshHeader;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created by alex on 18-4-19.
 *
 * @author alex
 */


@SuppressLint("ValidFragment")
@BindLayout(R.layout.fragment_team_manager)
public class TeamMangerFragment extends SuperBaseFragment implements ITeamStateView, DefaultCallback, OnRefreshListener {
    public static final int TODAY = 0;
    public static final int THREEDAY = 1;
    public static final int WEEK = 2;
    public static final int MOUTH = 3;
    @BindView(R.id.tv_team_number)
    TextView tvTeamNumber;
    @BindView(R.id.tv_team_number_detail)
    TextView tvTeamNumberDetail;
    @BindView(R.id.tv_agent_number)
    TextView tvAgentNumber;
    @BindView(R.id.tv_agent_detail)
    TextView tvAgentDetail;
    @BindView(R.id.tv_in)
    TextView tvIn;
    @BindView(R.id.tv_aviable)
    TextView tvAviable;
    @BindView(R.id.tv_out)
    TextView tvOut;
    @BindView(R.id.tv_win)
    TextView tvWin;
    @BindView(R.id.tv_team_member)
    TextView tvTeamMember;
    @BindView(R.id.tv_team_capture)
    TextView tvTeamCapture;
    @BindView(R.id.tv_team_bill)
    TextView tvTeamBill;
    @BindView(R.id.tv_open_account_center)
    TextView tvOpenAccountCenter;
    @BindView(R.id.srl_root_view)
    SmartRefreshLayout srlRootView;
    private int type;
    private TeamPresserter<ITeamStateView> teamPresserter;
    private TeamStateBean bean;
    private String startDate = "";
    private String endDate = "";

    @SuppressLint("ValidFragment")
    private TeamMangerFragment(int type) {
        this.type = type;
    }

    public static TeamMangerFragment getInstance(int type) {
        return new TeamMangerFragment(type);
    }


    @Override
    protected void initView() {
        RxBus.get().register(this);

    }

    @Override
    protected void initData() {
        //需要传入不同的参数获取不同的值
        teamPresserter = new TeamPresserter<>(getActivity(), this);
        srlRootView.setRefreshHeader(new SmartRefreshHeader(getContext()));
        srlRootView.setOnRefreshListener(this);

        tvTeamMember.setOnClickListener(v -> {
            //时间区间
            Intent intent = new Intent(getActivity(), TeamMemberActivity.class);
            processDate(intent);
            ActivityUtils.startActivity(intent);
        });

        tvTeamCapture.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TeamReportFromActivity.class);
            processDate(intent);
            ActivityUtils.startActivity(intent);
        });

        tvTeamBill.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TeamBillRecordActivity.class);
            processDate(intent);
            ActivityUtils.startActivity(intent);
        });

        tvOpenAccountCenter.setOnClickListener(v -> ActivityUtils.startActivity(OpenAccountActivity.class));

    }

    private void processDate(Intent intent) {
        if ("".equals(startDate)) {
            String goStartDate;
            String goEndDate;
            switch (type) {
                case TODAY:
                    goStartDate = DateTool.getNowDay();
                    goEndDate = DateTool.getDateLimit(-1);
                    intent.putExtra("startData", goStartDate);
                    intent.putExtra("endData", goEndDate);
                    LogUtils.e("TODAY", goStartDate + "/" + goEndDate);
                    break;
                case THREEDAY:
                    goStartDate = DateTool.getDateLimit(3);
                    goEndDate = DateTool.getDateLimit(-1);
                    intent.putExtra("startData", goStartDate);
                    intent.putExtra("endData", goEndDate);
                    LogUtils.e("THREEDAY", goStartDate + "/" + goEndDate);
                    break;
                case WEEK:
                    goStartDate = DateTool.getDateLimit(7);
                    goEndDate = DateTool.getDateLimit(-1);
                    intent.putExtra("startData", goStartDate);
                    intent.putExtra("endData", goEndDate);
                    LogUtils.e("WEEK", goStartDate + "/" + goEndDate);
                    break;
                case MOUTH:
                    goStartDate = DateTool.getDateLimit(30);
                    goEndDate = DateTool.getDateLimit(-1);
                    intent.putExtra("startData", goStartDate);
                    intent.putExtra("endData", goEndDate);
                    LogUtils.e("MOUTH", goStartDate + "/" + goEndDate);
                    break;
                default:

            }
        } else {
            intent.putExtra("startData", startDate);
            intent.putExtra("endData", endDate);
        }

    }

    @Override
    protected void superLoadData() {
        if (bean == null) {
            srlRootView.autoRefresh();
        } else {
            processData();
        }

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onTeamStateResult(TeamStateBean stateBean) {
        bean = stateBean;
        processData();
    }

    @SuppressLint("SetTextI18n")
    private void processData() {
        tvTeamNumberDetail.setText(bean.getTotalPeople() + "");
        tvAgentDetail.setText(bean.getTotalAgent() + "");
        tvIn.setText(bean.getDepositTotal() == null ? "0.00" : NumberFormaterUtils.formaterS2S(bean.getDepositTotal()));
        tvOut.setText(bean.getWithdrawTotal() == null ? "0.00" : NumberFormaterUtils.formaterS2S(bean.getWithdrawTotal()));
        tvAviable.setText(bean.getEffectiveVolume() == null ? "0.00" : NumberFormaterUtils.formaterS2S(bean.getEffectiveVolume()));
        tvWin.setText(bean.getBetTotal() == null ? "0.00" : NumberFormaterUtils.formaterS2S(bean.getBetTotal()));
    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
    }


    @Override
    public void start() {

    }

    @Override
    public void complete() {
        if (srlRootView != null) {
            srlRootView.finishRefresh();
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        this.startDate = "";
        this.endDate = "";
        switch (type) {
            case TODAY:
                teamPresserter.getTeamDataState(this, "", "", TODAY);
                break;
            case THREEDAY:
                teamPresserter.getTeamDataState(this, "", "", THREEDAY);
                break;
            case WEEK:
                teamPresserter.getTeamDataState(this, "", "", WEEK);
                break;
            case MOUTH:
                teamPresserter.getTeamDataState(this, "", "", MOUTH);
                break;
            default:
        }
    }

    public void searchDate(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        teamPresserter.getTeamDataState(startDate, endDate);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
    @Subscribe(tags = @Tag(ConstantValue.EVENT_SIMPLE_OA_SUCCEED))
    public void createAccountSucceed(String message) {
        srlRootView.autoRefresh();
    }
}
