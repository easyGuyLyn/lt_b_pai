package com.dawoo.lotterybox.view.fragment.mc;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;
import com.dawoo.lotterybox.bean.TeamBillBean;
import com.dawoo.lotterybox.bean.TypeBean;
import com.dawoo.lotterybox.bean.record.recordnum.BillItemEnum;
import com.dawoo.lotterybox.bean.record.recordnum.BillTypeEnum;
import com.dawoo.lotterybox.bean.record.recordnum.BillWayEnum;
import com.dawoo.lotterybox.mvp.presenter.TeamPresserter;
import com.dawoo.lotterybox.mvp.view.ITeamChangesView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.BottomDialogUtils;
import com.dawoo.lotterybox.util.CalculateUtils;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.PullTabHelper;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.SmartRefreshHeader;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wingsofts.byeburgernavigationview.ByeBurgerBehavior;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-30.
 *
 * @author alex
 */

@SuppressLint("ValidFragment")
@BindLayout(R.layout.fragment_team_bill_recoed)
public class TeamBillRecordFragment extends SuperBaseFragment implements ITeamChangesView, OnRefreshListener, DefaultCallback {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ll_middle)
    LinearLayout llMiddle;

    public static final int ALL = 0;
    public static final int DEPOSIT = 1;
    public static final int WITHDRAWS = 2;
    public static final int GAME = 3;
    public static final int OFFER = 4;
    @BindView(R.id.tv_before_day)
    TextView tvBeforeDay;
    @BindView(R.id.day_before_rlg)
    RelativeLayout dayBeforeRlg;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_mouth_day)
    TextView tvMouthDay;
    @BindView(R.id.rl_choose_day)
    RelativeLayout rlChooseDay;
    @BindView(R.id.tv_next_day)
    TextView tvNextDay;
    @BindView(R.id.day_nextg)
    RelativeLayout dayNextg;
    @BindView(R.id.tv_note_childtype)
    TextView tvNoteChildtype;
    @BindView(R.id.iv_type_click)
    ImageView ivTypeClick;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_note_allCode)
    TextView tvNoteAllCode;
    @BindView(R.id.iv_all_click)
    ImageView ivAllClick;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
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
    @BindView(R.id.srl_content)
    SmartRefreshLayout srlContent;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.iv_author)
    ImageView ivAuthor;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_one_show)
    TextView tvOneShow;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_three_show)
    TextView tvThreeShow;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_two_show)
    TextView tvTwoShow;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tv_four_show)
    TextView tvFourShow;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.ll_in_one)
    LinearLayout llInOne;
    @BindView(R.id.ll_in_three)
    LinearLayout llInThree;
    @BindView(R.id.ll_in_two)
    LinearLayout llInTwo;
    @BindView(R.id.ll_in_four)
    LinearLayout llInFour;

    private String startDate;
    private String endDate;
    private int type;
    private boolean isBalanceUp;
    private boolean isTimeUp;
    private String way = "";
    private String tyPe = "";
    private String item = "";

    private List<TypeBean> itemData = new ArrayList<>();
    private List<TypeBean> typeData = new ArrayList<>();
    private List<TeamBillBean> billData = new ArrayList<>();

    private PullTabHelper pullTabItemHelper;
    private PullTabHelper pullTabTypeHelper;
    private TeamBillAdapter teamBillAdapter;

    private TeamPresserter<ITeamChangesView> changesViewTeamPresserter;
    private ByeBurgerBehavior from;

    @Override
    public void onPause() {
        super.onPause();
        BottomDialogUtils.with(getActivity()).destoryTimePick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BottomDialogUtils.with(getActivity()).destoryTimePick();
        RxBus.get().unregister(this);
        changesViewTeamPresserter.onDestory();
    }

    private TeamBillRecordFragment(int type, String startDate, String endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void processWay() {
        processFirstData();
        switch (type) {
            case 0:
                way = "";
                processPullTabItemData(BillWayEnum.DEPOSIT);
                processPullTabItemData(BillWayEnum.WITHDRAW);
                processPullTabItemData(BillWayEnum.GAME);
                processPullTabItemData(BillWayEnum.FAVORABLE);

                processPullTabTypeData(BillWayEnum.DEPOSIT);
                processPullTabTypeData(BillWayEnum.WITHDRAW);
                processPullTabTypeData(BillWayEnum.GAME);
                processPullTabTypeData(BillWayEnum.FAVORABLE);
                tvOne.setText("存款总额:");
                tvTwo.setText("取款总额:");
                tvThree.setText("游戏总额:");
                tvFour.setText("优惠总额:");
                break;
            case 1:
                way = BillWayEnum.DEPOSIT.getCode();
                processPullTabItemData(BillWayEnum.DEPOSIT);
                processPullTabTypeData(BillWayEnum.DEPOSIT);
                tvOne.setText("存款总额:");
                llInTwo.setVisibility(View.GONE);
                llInThree.setVisibility(View.GONE);
                llInFour.setVisibility(View.GONE);
                break;
            case 2:
                way = BillWayEnum.WITHDRAW.getCode();
                processPullTabItemData(BillWayEnum.WITHDRAW);
                processPullTabTypeData(BillWayEnum.WITHDRAW);
                tvOne.setText("取款总额:");
                llInTwo.setVisibility(View.GONE);
                llInThree.setVisibility(View.GONE);
                llInFour.setVisibility(View.GONE);
                break;
            case 3:
                way = BillWayEnum.GAME.getCode();
                processPullTabItemData(BillWayEnum.GAME);
                processPullTabTypeData(BillWayEnum.GAME);
                tvOne.setText("游戏总额:");
                llInTwo.setVisibility(View.GONE);
                llInThree.setVisibility(View.GONE);
                llInFour.setVisibility(View.GONE);
                break;
            case 4:
                way = BillWayEnum.FAVORABLE.getCode();
                processPullTabItemData(BillWayEnum.FAVORABLE);
                processPullTabTypeData(BillWayEnum.FAVORABLE);
                tvOne.setText("优惠总额:");
                llInTwo.setVisibility(View.GONE);
                llInThree.setVisibility(View.GONE);
                llInFour.setVisibility(View.GONE);
                break;
            default:
        }
    }

    private void processFirstData() {
        TypeBean itemFirst = new TypeBean();
        itemFirst.setMessage("全部");
        itemFirst.setItem("");
        itemFirst.setItem("");
        itemFirst.setSelect(true);
        itemData.add(itemFirst);
        TypeBean typeFirst = new TypeBean();
        typeFirst.setMessage("全部");
        typeFirst.setItem("");
        typeFirst.setItem("");
        typeFirst.setSelect(true);
        typeData.add(typeFirst);
    }

    private void processPullTabTypeData(BillWayEnum billWayEnum) {

        for (BillTypeEnum typeEnum : BillTypeEnum.values()) {
            if (typeEnum.getParent().getCode().equals(billWayEnum.getCode())) {
                TypeBean typeBean = new TypeBean();
                typeBean.setType(typeEnum.getCode());
                typeBean.setItem("");
                typeBean.setMessage(typeEnum.getTrans());
                typeData.add(typeBean);
            }
        }
    }

    private void processPullTabItemData(BillWayEnum billWayEnum) {
        for (BillTypeEnum typeEnum : BillTypeEnum.values()) {
            if (typeEnum.getParent().getCode().equals(billWayEnum.getCode())) {
                for (BillItemEnum itemEnum : BillItemEnum.values()) {
                    if (itemEnum.getParent().getCode().equals(typeEnum.getCode())) {
                        TypeBean typeBean = new TypeBean();
                        typeBean.setType(typeEnum.getCode());
                        typeBean.setItem(itemEnum.getCode());
                        typeBean.setMessage(itemEnum.getTrans());
                        itemData.add(typeBean);
                    }
                }
            }
        }
    }

    public static TeamBillRecordFragment instance(int type, String startDate, String endDate) {
        return new TeamBillRecordFragment(type, startDate, endDate);
    }


    @Override
    protected void initView() {
        RxBus.get().register(this);
        ViewUtils.setLinearManager(getActivity(), rvContent);
        srlContent.setRefreshHeader(new SmartRefreshHeader(getContext()));
        from = ByeBurgerBehavior.from(rlBottom);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        processWay();
        processTitleDate();
        processHelper();
        teamBillAdapter = new TeamBillAdapter();
        rvContent.setAdapter(teamBillAdapter);
        teamBillAdapter.setEmptyView(emptyView);
        srlContent.setOnRefreshListener(this);
        changesViewTeamPresserter = new TeamPresserter<>(getContext(), this);
        rlChooseDay.setOnClickListener(v -> BottomDialogUtils.with(getActivity())
                .showTimePickDialog((startDate, endDate) -> {
                    this.startDate = startDate;
                    this.endDate = endDate;
                    processTitleDate();
                    getDataWithDialog();

                }));
        llAll.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                pullTabTypeHelper.turnOffPop();
                pullTabItemHelper.toggleShow(llMiddle);

            }
        });
        llType.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                pullTabItemHelper.turnOffPop();
                pullTabTypeHelper.toggleShow(llMiddle);
            }
        });
        llBalance.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                turnOffAll();
                if (isBalanceUp) {
                    isBalanceUp = false;
                    ivBalanceClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    Collections.reverse(billData);
                } else {
                    isBalanceUp = true;
                    ivBalanceClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    collectionBalance();
                }
                teamBillAdapter.setNewData(billData);
            }
        });
        llTime.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                turnOffAll();
                if (isTimeUp) {
                    isTimeUp = false;
                    ivTimeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    Collections.reverse(billData);
                } else {
                    isTimeUp = true;
                    ivTimeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    collectionTime();
                }
                teamBillAdapter.setNewData(billData);
            }
        });

        tvBeforeDay.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startDate = DateTool.getAnyDateLimit(DateTool.stringM2Date(startDate), 1);
                processTitleDate();
                getDataWithDialog();
            }
        });

        tvNextDay.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                endDate = DateTool.getAnyDateLimit(DateTool.stringM2Date(endDate), -1);
                getDataWithDialog();
                processTitleDate();
            }
        });
    }

    private void processHelper() {
        pullTabItemHelper = new PullTabHelper(getActivity(), itemData, ivAllClick,
                (type, item) -> {
                    this.tyPe = type;
                    this.item = item;
                    getDataWithDialog();
                });
        pullTabTypeHelper = new PullTabHelper(getActivity(), typeData, ivTypeClick,
                (type, item) -> {
                    this.tyPe = type;
                    this.item = item;
                    getDataWithDialog();
                });
    }

    private void getDataWithDialog() {
        changesViewTeamPresserter.getTeamChanges(startDate, endDate, way, tyPe, item);
    }


    @SuppressLint("SetTextI18n")
    private void processTitleDate() {
        String[] splitStart = startDate.split("-");
        String[] splitEnd = endDate.split("-");
        if (splitStart[0].equals(splitEnd[0])) {
            tvYear.setText(splitStart[0]);
        } else {
            tvYear.setText(splitStart[0] + "/" + splitEnd[0]);
        }
        tvMouthDay.setText(splitStart[1] + "-" + splitStart[2] + "/" + splitEnd[1] + "-" + splitEnd[2]);
    }

    @Override
    protected void superLoadData() {
        if (billData.size() == 0) {
            srlContent.autoRefresh();
        } else {
            teamBillAdapter.setNewData(billData);
        }

    }


    @Override
    public void onTeamChangrsResult(List<TeamBillBean> teamBillBeans) {
        billData = teamBillBeans;
        calculateAll();
        from.show();
        teamBillAdapter.setNewData(billData);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        changesViewTeamPresserter.getTeamChangesRefresh(startDate, endDate, way, tyPe, item, this);
        from.hide();
    }

    @Override
    public void start() {

    }

    @Override
    public void complete() {
        if (srlContent != null) {
            srlContent.finishRefresh();
        }

    }

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        ToastUtils.showShort(message);
        srlContent.finishRefresh();
    }

    public void turnOffAll() {
        pullTabItemHelper.turnOffPop();
        pullTabTypeHelper.turnOffPop();
    }

    private void collectionBalance() {
        Collections.sort(billData, (o1, o2) -> {
            if (Double.valueOf(o1.getBalance()) > Double.valueOf(o2.getBalance())) {
                return -1;
            }
            return 0;
        });
    }

    private void collectionTime() {
        Collections.sort(billData, (o1, o2) -> {
            if (o1.getCompletionTime() > o2.getCompletionTime()) {
                return -1;
            }
            return 0;
        });
    }

    //计算底部的值
    @SuppressLint("SetTextI18n")
    private void calculateAll() {
        if (type != 0) {
            double blance = 0;
            for (int i = 0; i < billData.size(); i++) {
                blance = CalculateUtils.add(blance, Double.valueOf(billData.get(i).getMoney()));
            }
            tvOneShow.setText(NumberFormaterUtils.formaterD2S(blance));
        } else {
            double blanceOne = 0;
            double blanceTwo = 0;
            double blanceThree = 0;
            double blanceFour = 0;
            for (int i = 0; i < billData.size(); i++) {
                String way = billData.get(i).getWay();
                double balance = Double.valueOf(billData.get(i).getMoney());

                if (BillWayEnum.DEPOSIT.getCode().equals(way)) {
                    blanceOne = CalculateUtils.add(blanceOne, balance);
                } else if (BillWayEnum.WITHDRAW.getCode().equals(way)) {
                    blanceTwo = CalculateUtils.add(blanceTwo, balance);
                } else if (BillWayEnum.GAME.getCode().equals(way)) {
                    blanceThree = CalculateUtils.add(blanceThree, balance);
                } else if (BillWayEnum.FAVORABLE.getCode().equals(way)) {
                    blanceFour = CalculateUtils.add(blanceFour, balance);
                }
            }
            tvOneShow.setText(NumberFormaterUtils.formaterD2S(blanceOne));
            tvTwoShow.setText(NumberFormaterUtils.formaterD2S(blanceTwo));
            tvThreeShow.setText(NumberFormaterUtils.formaterD2S(blanceThree));
            tvFourShow.setText(NumberFormaterUtils.formaterD2S(blanceFour));
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    class TeamBillAdapter extends BaseQuickAdapter<TeamBillBean, TeamBillAdapter.TeamBillHolder> {


        public TeamBillAdapter() {
            super(R.layout.adapter_bill_item_layout);
        }

        @Override
        protected void convert(TeamBillHolder helper, TeamBillBean item) {
            Date date = new Date(item.getCompletionTime());
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTool.FMT_DATE);
            String format = simpleDateFormat.format(date);
            helper.timeTv.setText(format);
            helper.balanceNum.setText(NumberFormaterUtils.formaterS2S(item.getBalance()));
            helper.moneyNum.setText(NumberFormaterUtils.formaterS2S(item.getMoney()));
            for (int i = 0; i < itemData.size(); i++) {
                if (item.getItem().equals(itemData.get(i).getItem())) {
                    helper.childName.setText(itemData.get(i).getMessage());
                }
            }
            for (int i = 0; i < typeData.size(); i++) {
                if (item.getType().equals(typeData.get(i).getType())) {
                    helper.parentName.setText(typeData.get(i).getMessage());
                }
            }
            if (helper.getPosition() % 2 == 0) {
                helper.llRootView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            } else {
                helper.llRootView.setBackgroundColor(mContext.getResources().getColor(R.color.bgColor));
            }
        }

        class TeamBillHolder extends BaseViewHolder {
            @BindView(R.id.parent_name)
            TextView parentName;
            @BindView(R.id.child_name)
            TextView childName;
            @BindView(R.id.money_num)
            TextView moneyNum;
            @BindView(R.id.balance_num)
            TextView balanceNum;
            @BindView(R.id.time_tv)
            TextView timeTv;
            @BindView(R.id.ll_root_view)
            LinearLayout llRootView;

            public TeamBillHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
