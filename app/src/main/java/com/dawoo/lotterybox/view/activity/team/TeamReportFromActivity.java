package com.dawoo.lotterybox.view.activity.team;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.bean.TeamReportBean;
import com.dawoo.lotterybox.mvp.presenter.TeamPresserter;
import com.dawoo.lotterybox.mvp.view.ITeamReportView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.CalculateUtils;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.SmartRefreshHeader;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author alex
 */
@BindLayout(R.layout.activity_team_report_from)
public class TeamReportFromActivity extends SuperBaseActivity implements OnRefreshListener, OnLoadMoreListener, ITeamReportView, DefaultCallback {

    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    @BindView(R.id.tv_note_allCode)
    TextView tvNoteAllCode;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_note_childtype)
    TextView tvNoteChildtype;
    @BindView(R.id.iv_type_click)
    ImageView ivTypeClick;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_yinkui_sort)
    TextView tvYinkuiSort;
    @BindView(R.id.iv_balance_click)
    ImageView ivBalanceClick;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.tv_time_sort)
    TextView tvTimeSort;
    @BindView(R.id.iv_time_click)
    ImageView ivTimeClick;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.ll_middle)
    LinearLayout llMiddle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.srl_content)
    SmartRefreshLayout srlContent;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.iv_author)
    ImageView ivAuthor;
    @BindView(R.id.tv_zongcunkuang)
    TextView tvZongcunkuang;
    @BindView(R.id.tv_touzhuer)
    TextView tvTouzhuer;
    @BindView(R.id.tv_rigongzi)
    TextView tvRigongzi;
    @BindView(R.id.tv_zhongjiang)
    TextView tvZhongjiang;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_zongquer)
    TextView tvZongquer;
    @BindView(R.id.tv_zhongjianger)
    TextView tvZhongjianger;
    @BindView(R.id.tv_fenghong)
    TextView tvFenghong;
    @BindView(R.id.tv_yingkui)
    TextView tvYingkui;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private int removeLength;
    private boolean isOped;
    private TeamPresserter<TeamReportFromActivity> teamPresserter;
    private String startData;
    private String endData;
    private TeamReportFromAdapter teamReportFromAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {

        teamPresserter = new TeamPresserter<>(this, this);
        startData = getIntent().getStringExtra("startData");
        endData = getIntent().getStringExtra("endData");
        ViewUtils.setLinearManager(this, rvContent);
        rvContent.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        titleName.setText("团队报表");
        srlContent.setRefreshHeader(new SmartRefreshHeader(this));
        srlContent.setOnRefreshListener(this);
        srlContent.setOnLoadMoreListener(this);
        srlContent.autoRefresh();
        leftBtn.setOnClickListener(v -> finish());
        rlBottom.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int rlBottomHeight = rlBottom.getHeight();
            removeLength = rlBottomHeight / 2 - 10;
        });
        ivAuthor.setOnClickListener(v -> {
            if (isOped) {
                //关闭
                isOped = false;
                rlBottom.animate().translationY(removeLength / 18);
                ivAuthor.setImageDrawable(getResources().getDrawable(R.mipmap.ic_up));
            } else {
                //打开
                isOped = true;
                rlBottom.animate().translationY(-removeLength);
                ivAuthor.setImageDrawable(getResources().getDrawable(R.mipmap.ic_down));

            }


        });
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isOped = false;
                ivAuthor.setImageDrawable(getResources().getDrawable(R.mipmap.ic_up));
            }
        });

    }

    @Override
    protected void initData() {
        teamReportFromAdapter = new TeamReportFromAdapter();
        rvContent.setAdapter(teamReportFromAdapter);
        teamReportFromAdapter.setEmptyView(getLayoutInflater().inflate(R.layout.empty_view, null));
        teamReportFromAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view,null));
        teamReportFromAdapter.setOnItemClickListener((adapter, view, position) -> {
            //传参数过去显示
            TeamReportBean teamReportBean = (TeamReportBean) adapter.getData().get(position);
            Intent intent = new Intent(TeamReportFromActivity.this, TeamReportFromDetailActivity.class);
            intent.putExtra("playerName",teamReportBean.getPlayername());
            intent.putExtra("deposittotal", NumberFormaterUtils.formaterD2S(teamReportBean.getDeposittotal()));
            intent.putExtra("withdrawtotal", NumberFormaterUtils.formaterD2S(teamReportBean.getWithdrawtotal()));
            intent.putExtra("effectivevolume", NumberFormaterUtils.formaterD2S(teamReportBean.getEffectivevolume()));
            intent.putExtra("favorable", NumberFormaterUtils.formaterD2S(teamReportBean.getFavorable()));
            intent.putExtra("payout", NumberFormaterUtils.formaterD2S(teamReportBean.getPayout()));
            intent.putExtra("win", NumberFormaterUtils.formaterD2S(CalculateUtils.round(teamReportBean.getPayout() - teamReportBean.getEffectivevolume() - teamReportBean.getRebate(),3) ));
            startActivity(intent);
        });
    }



    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        teamPresserter.getTeamReportLoaderMore(startData, endData, this);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        teamPresserter.getTeamReportRefresh(startData, endData, this);
    }

    @Override
    public void start() {

    }

    @Override
    public void complete() {
        srlContent.finishRefresh();
        srlContent.finishLoadMore();
    }

    @Override
    public void onRefreshResult(List<TeamReportBean> o) {
        teamReportFromAdapter.setNewData(o);
        srlContent.finishRefresh();
    }

    @Override
    public void onLoadMoreResult(List<TeamReportBean> o) {
        teamReportFromAdapter.addData(o);
        srlContent.finishLoadMore();
    }


    class TeamReportFromAdapter extends BaseQuickAdapter<TeamReportBean, TeamReportFromAdapter.TeamReportFromHolder> {

        TeamReportFromAdapter() {
            super(R.layout.item_new_team_report_from);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void convert(TeamReportFromHolder helper, TeamReportBean item) {
            helper.tvAccount.setText(item.getPlayername());
            helper.tvVolume.setText(NumberFormaterUtils.formaterD2S(item.getEffectivevolume()));
            helper.tvPayout.setText(NumberFormaterUtils.formaterD2S(item.getPayout()));
            helper.tvWin.setText(NumberFormaterUtils.formaterD2S(CalculateUtils.round(item.getPayout() - item.getEffectivevolume() - item.getRebate() ,3)));
            if (helper.getPosition() % 2 == 0) {
                helper.llRootItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            } else {
                helper.llRootItem.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor));
            }
        }

        class TeamReportFromHolder extends BaseViewHolder {
            @BindView(R.id.tv_account)
            TextView tvAccount;
            @BindView(R.id.tv_volume)
            TextView tvVolume;
            @BindView(R.id.tv_payout)
            TextView tvPayout;
            @BindView(R.id.tv_win)
            TextView tvWin;
            @BindView(R.id.ll_root_item)
            LinearLayout llRootItem;

            public TeamReportFromHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
        srlContent.finishRefresh();
        srlContent.finishLoadMore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        teamPresserter.onDestory();
    }
}
