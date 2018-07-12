package com.dawoo.lotterybox.view.activity.account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseActivity;
import com.dawoo.lotterybox.bean.WithDrawsAduitBean;
import com.dawoo.lotterybox.mvp.presenter.WithDrawsPresenter;
import com.dawoo.lotterybox.mvp.view.IWithDAView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.CalculateUtils;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.setting.WithDrawsAuditDetailActivity;
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
 * @author alex
 *         稽核详情
 */
@BindLayout(R.layout.activity_with_draws_audit)
public class WithDrawsAuditActivity extends SuperBaseActivity implements IWithDAView, DefaultCallback, OnRefreshListener {


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    @BindView(R.id.tv_toggle_cunkuan)
    TextView tvToggleCunkuan;
    @BindView(R.id.iv_all_click)
    ImageView ivAllClick;
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
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.srl_content)
    SmartRefreshLayout srlContent;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.iv_author)
    ImageView ivAuthor;
    @BindView(R.id.tv_cunkuang)
    TextView tvCunkuang;
    @BindView(R.id.tv_youhuijinger)
    TextView tvYouhuijinger;
    @BindView(R.id.tv_rigongzi)
    TextView tvRigongzi;
    @BindView(R.id.tv_zhongjiang)
    TextView tvZhongjiang;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.tv_xingzhengfei)
    TextView tvXingzhengfei;
    @BindView(R.id.rl_bottom_ss)
    RelativeLayout rlBottom;
    private WithDrawsPresenter<IWithDAView> withDrawsPresenter;
    private WithDrawsAduitAdapter withDrawsAduitAdapter;
    List<WithDrawsAduitBean> datas = new ArrayList<>();
    List<WithDrawsAduitBean> changeData = new ArrayList<>();
    private boolean isUpBlance;
    private boolean isUpCost;
    private boolean isUpFee;
    private boolean isUpTime;


    @Override
    protected void initViews() {
        RxBus.get().register(this);
        titleName.setText("存款稽核列表");
        flRightClick.setOnClickListener(v -> finish());
        ViewUtils.setLinearManager(this, rvContent);
        srlContent.setRefreshHeader(new SmartRefreshHeader(this));

    }

    @Override
    protected void initData() {
        srlContent.setOnRefreshListener(this);
        withDrawsPresenter = new WithDrawsPresenter<>(this, this);
        withDrawsAduitAdapter = new WithDrawsAduitAdapter();
        withDrawsAduitAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        rvContent.setAdapter(withDrawsAduitAdapter);
        srlContent.autoRefresh();
        withDrawsAduitAdapter.setOnItemClickListener((adapter, view, position) -> {
            int id = changeData.get(position).getId();
            Intent intent = new Intent(WithDrawsAuditActivity.this, WithDrawsAuditDetailActivity.class);
            intent.putExtra("id",id+"");
            ActivityUtils.startActivity(intent);
        });

        leftBtn.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                finish();
            }
        });

        llAll.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (isUpBlance) {
                    isUpBlance = false;
                    ivAllClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    Collections.reverse(changeData);
                } else {
                    isUpBlance = true;
                    ivAllClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    collectionAll();

                }
                withDrawsAduitAdapter.setNewData(changeData);
            }

        });

        llType.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (isUpCost) {
                    isUpCost = false;
                    ivTypeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    //collectionBalance();
                    Collections.reverse(changeData);
                } else {
                    isUpCost = true;
                    ivTypeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    collectionBalance();
                }
                withDrawsAduitAdapter.setNewData(changeData);
            }
        });

        llBalance.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (isUpFee) {
                    isUpFee = false;
                    ivBalanceClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    //collectType();
                    Collections.reverse(changeData);

                } else {
                    isUpFee = true;
                    ivBalanceClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    collectType();
                }
                withDrawsAduitAdapter.setNewData(changeData);
            }
        });

        llTime.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (isUpTime) {
                    isUpTime = false;
                    ivTimeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_down_up));
                    // collectionTime();
                    Collections.reverse(changeData);
                } else {
                    isUpTime = true;
                    ivTimeClick.setImageDrawable(getResources().getDrawable(R.mipmap.img_up_down));
                    collectionTime();
                }
                withDrawsAduitAdapter.setNewData(changeData);

            }
        });
    }

    private void collectionAll() {
        Collections.sort(changeData, (o1, o2) -> {
            if (o1.getDepositAmount() > o2.getDepositAmount()) {
                return -1;
            }
            return 0;
        });
    }

    private void collectionBalance() {
        Collections.sort(changeData, (o1, o2) -> {
            if (o1.getAdminCost() > o2.getAdminCost()) {
                return -1;
            }
            return 0;
        });
    }

    private void collectType() {
        Collections.sort(changeData, (o1, o2) -> {
            if (o1.getDeductFavorable() > o2.getDeductFavorable()) {
                return -1;
            }
            return 0;
        });
    }

    private void collectionTime() {
        Collections.sort(changeData, (o1, o2) -> {
            if (o1.getOrderTime() > o2.getOrderTime()) {
                return -1;
            }
            return 0;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void IWithDrawsAduitResult(List<WithDrawsAduitBean> drawsAduitBeanList) {
        datas = drawsAduitBeanList;
        changeData = drawsAduitBeanList;
        srlContent.finishRefresh();
        collectionAll();
        Collections.reverse(changeData);
        withDrawsAduitAdapter.setNewData(changeData);
        ByeBurgerBehavior.from(findViewById(R.id.rl_bottom_ss)).show();
        //计算总值
        double tallIn = 0;
        double costAll = 0;
        double faveLL = 0;
        for (int i = 0; i < drawsAduitBeanList.size(); i++) {

            tallIn = CalculateUtils.add(tallIn, drawsAduitBeanList.get(i).getDepositAmount());
        }
        for (int i = 0; i < drawsAduitBeanList.size(); i++) {
            costAll = CalculateUtils.add(costAll, drawsAduitBeanList.get(i).getAdminCost());
        }
        for (int i = 0; i < drawsAduitBeanList.size(); i++) {
            faveLL = CalculateUtils.add(faveLL, drawsAduitBeanList.get(i).getDeductFavorable());
        }
        tvCunkuang.setText(NumberFormaterUtils.formaterD2S(tallIn));
        tvXingzhengfei.setText(NumberFormaterUtils.formaterD2S(costAll));
        tvYouhuijinger.setText(NumberFormaterUtils.formaterD2S(faveLL));
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

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        ByeBurgerBehavior.from(rlBottom).hide();
        withDrawsPresenter.withDrawsAuidtList(this);
    }

    class WithDrawsAduitAdapter extends BaseQuickAdapter<WithDrawsAduitBean, WithDrawsAduitAdapter.WithDrawsAduitHolder> {

        public WithDrawsAduitAdapter() {
            super(R.layout.item_withdraws_aduit);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void convert(WithDrawsAduitHolder helper, WithDrawsAduitBean item) {
            helper.money.setText(NumberFormaterUtils.formaterD2S(item.getDepositAmount()));
            helper.tvXingzhengfei.setText(NumberFormaterUtils.formaterD2S(item.getDeductFavorable()));
            helper.tvFee.setText(NumberFormaterUtils.formaterD2S(item.getAdminCost()));
            LogUtils.e(item.getAuditTime());
            Date date = new Date(item.getOrderTime());
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTool.FMT_DATE);
            String format = simpleDateFormat.format(date);
            LogUtils.e("format",format);
            helper.tvTime.setText(format);
        }

        class WithDrawsAduitHolder extends BaseViewHolder {
            @BindView(R.id.money)
            TextView money;
            @BindView(R.id.tv_fee)
            TextView tvFee;
            @BindView(R.id.tv_xingzhengfei)
            TextView tvXingzhengfei;
            @BindView(R.id.tv_time)
            TextView tvTime;

            public WithDrawsAduitHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        withDrawsPresenter.onDestory();
    }

    String msg = "";

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        if (msg.equals(message)) {
        } else {
            ToastUtils.showShort(message);
            msg = message;
        }
        srlContent.finishRefresh();
    }
}
