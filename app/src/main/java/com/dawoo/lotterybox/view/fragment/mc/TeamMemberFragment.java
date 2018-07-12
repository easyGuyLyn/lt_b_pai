package com.dawoo.lotterybox.view.fragment.mc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;
import com.dawoo.lotterybox.bean.TeamMemberBean;
import com.dawoo.lotterybox.mvp.presenter.TeamPresserter;
import com.dawoo.lotterybox.mvp.view.ITeamMemberView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.view.EditDailyWageDialog;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.SmartRefreshHeader;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by alex on 18-4-20.
 *
 * @author alex
 */

@SuppressLint("ValidFragment")
@BindLayout(R.layout.fragment_team_member)
public class TeamMemberFragment extends SuperBaseFragment implements OnRefreshListener, OnLoadMoreListener, ITeamMemberView, DefaultCallback {
    @BindView(R.id.rv_member_content)
    RecyclerView rvMemberContent;
    @BindView(R.id.srl_content)
    SmartRefreshLayout srlContent;
    private int type;
    private MemberAdapter memberAdapter;
    private List<String> list;
    public static final int AGENT = 0;
    public static final int MEMBER = 1;
    public static final int ALL = 2;
    private TeamPresserter<ITeamMemberView> teamPresenter;
    private String startData;
    private String endData;
    private List<TeamMemberBean> datas = new ArrayList<>();

    @SuppressLint("ValidFragment")
    private TeamMemberFragment(int type, String startData, String endData) {
        this.type = type;
        this.startData = startData;
        this.endData = endData;
    }

    public static TeamMemberFragment getInstance(int type, String startData, String endData) {
        return new TeamMemberFragment(type, startData, endData);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }


    @Override
    protected void initView() {
        ViewUtils.setLinearManager(getContext(), rvMemberContent);
        srlContent.setRefreshHeader(new SmartRefreshHeader(getContext()));
        srlContent.setOnRefreshListener(this);
        srlContent.setOnLoadMoreListener(this);
        teamPresenter = new TeamPresserter<>(getActivity(), this);
        memberAdapter = new MemberAdapter();
        rvMemberContent.setAdapter(memberAdapter);
        memberAdapter.setEmptyView(emptyView);

    }

    @Override
    protected void initData() {
        memberAdapter.setNewData(datas);
    }

    @Override
    protected void superLoadData() {
        if (datas.size() == 0) {
            srlContent.autoRefresh();
        } else {
            memberAdapter.setNewData(datas);
        }

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        switch (type) {
            case AGENT:
                teamPresenter.getTeamMemberLoadMore(this, startData, endData, "agent");
                break;
            case MEMBER:
                teamPresenter.getTeamMemberLoadMore(this, startData, endData, "member");
                break;

            case ALL:
                teamPresenter.getTeamMemberLoadMore(this, startData, endData, "");
                break;
            default:
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        switch (type) {
            case AGENT:
                teamPresenter.getTeamMemberRefresh(this, startData, endData, "agent");
                break;
            case MEMBER:
                teamPresenter.getTeamMemberRefresh(this, startData, endData, "member");
                break;
            case ALL:
                teamPresenter.getTeamMemberRefresh(this, startData, endData, "");
            default:
        }
    }


    @Override
    public void start() {

    }

    @Override
    public void complete() {
        if (srlContent!=null){
            srlContent.finishRefresh();
            srlContent.finishLoadMore();
        }


    }

    @Override
    public void onRefreshResult(List<TeamMemberBean> o) {
        datas.clear();
        datas.addAll(o);
        memberAdapter.setNewData(datas);
    }

    @Override
    public void onLoaderMoreResult(List<TeamMemberBean> o) {
        datas.addAll(o);
        memberAdapter.addData(o);
    }

    public void searchDay(String start, String end, String playType) {
        teamPresenter.getTeamMember(start, end, playType, "100", "1");
    }


    class MemberAdapter extends BaseQuickAdapter<TeamMemberBean, MemberAdapter.MemberHolder> {

        MemberAdapter() {
            super(R.layout.item_agent);

        }

        @Override
        protected void convert(MemberHolder helper, TeamMemberBean item) {
            helper.tvAccount.setText(item.getPlayerName());
            Date date = new Date(item.getCreateTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTool.FMT_DATE);
            String format = simpleDateFormat.format(date);
            helper.tvRegisterTime.setText(format);
            helper.tvAllIn.setText("agent".equals(item.getPlayerType()) ? "代理" : "会员");
            helper.tvAllWin.setText(NumberFormaterUtils.formaterD2S(item.getBalance()));
            helper.tvDelete.setOnClickListener(v -> {
                String ac="确实要删除"+helper.tvAllIn.getText().toString()+"吗?";
                noticeDialog.setContentString(ac).show(alertDialog -> {
                    alertDialog.dismiss();
                    //TODO:请求删除
                    memberAdapter.remove(helper.getPosition());
                });
            });
            //分红
            helper.tvFenghong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //日工资
            helper.tvRigongzi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editDailyWageDialog.show(new EditDailyWageDialog.CommitCallback() {
                        @Override
                        public void commit(String str1, String str2, String str3, String str4) {

                        }
                    });
                }
            });
        }

        class MemberHolder extends BaseViewHolder {
            @BindView(R.id.tv_account)
            TextView tvAccount;
            @BindView(R.id.tv_all_in)
            TextView tvAllIn;
            @BindView(R.id.tv_all_win)
            TextView tvAllWin;
            @BindView(R.id.tv_register_time)
            TextView tvRegisterTime;
            @BindView(R.id.tv_rigongzi)
            TextView tvRigongzi;
            @BindView(R.id.tv_fenghong)
            TextView tvFenghong;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            public MemberHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        teamPresenter.onDestory();
    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        SingleToast.showMsg(message);
        srlContent.finishRefresh();
        srlContent.finishLoadMore();
    }
}
