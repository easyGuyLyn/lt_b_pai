package com.dawoo.lotterybox.view.activity.lottery;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.bean.lottery.OrderInfo;
import com.dawoo.lotterybox.mvp.presenter.OrderInfoPresenter;
import com.dawoo.lotterybox.mvp.view.IOrderView;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.NoteRcdActivity;
import com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * Created by b on 18-4-17.
 * 投注结果
 */

public class LotteryAShopResultActivity extends BaseActivity implements IOrderView {

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tv_expect)
    TextView mTvExpect;
    @BindView(R.id.tv_bet_info)
    TextView mTvBetInfo;
    @BindView(R.id.tv_view_record)
    TextView mTvViewRecord;
    @BindView(R.id.tv_continue_bet)
    TextView mTvContinueBet;
    @BindView(R.id.rlv_order_info)
    RecyclerView mRlvOrderInfo;

    public static final String ORDER_NUMBER = "order_number";
    private OrderQuickAdapter mQuickAdapter;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_lottery_result);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.order_result), true);
        mQuickAdapter = new OrderQuickAdapter(R.layout.item_shop_result);
        mQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mRlvOrderInfo.setLayoutManager(new LinearLayoutManager(this));
        mRlvOrderInfo.setAdapter(mQuickAdapter);
    }

    @Override
    protected void initData() {
        OrderInfoPresenter orderInfoPresenter = new OrderInfoPresenter(this, this);
        orderInfoPresenter.getOrderInfo(getIntent().getStringExtra(ORDER_NUMBER), getIntent().getStringExtra(LT_CODE));

    }


    @Override
    public void onOrderResult(OrderInfo orderInfo) {
        if (orderInfo != null) {
            int orders = 0;
            double totalMoney = 0;
            List<OrderInfo.OrderListBean> orderList = orderInfo.getOrderList();
            mQuickAdapter.setNewData(orderList);
            for (OrderInfo.OrderListBean orderListBean : orderList) {
                orders ++;
                totalMoney += orderListBean.getBetAmount();
            }
            mTvExpect.setText(getString(R.string.which_periods, orderInfo.getOrderList().get(0).getExpect()));
            Object[] objects = { orders , totalMoney};
            mTvBetInfo.setText(getString(R.string.the_order_info,objects));
        }


    }

    @OnClick({R.id.tv_view_record ,R.id.tv_continue_bet})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_view_record:  //投注记录
                NoteRcdActivity.goRcordActivity(this, HistoryRepotFormFragment.class.getSimpleName());
                break;
            case R.id.tv_continue_bet: //继续投注
                finish();
                break;
        }
    }

    class OrderQuickAdapter extends BaseQuickAdapter {
        public OrderQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            OrderInfo.OrderListBean orderListBean = (OrderInfo.OrderListBean) item;
            helper.setText(R.id.play_name, LotteryUtil.getLotteryNameByCode(orderListBean.getCode()));
            helper.setText(R.id.tv_which_expect, getString(R.string.which_periods, orderListBean.getExpect()));
            helper.setText(R.id.tv_bet_time, DateTool.getTimeFromLong(DateTool.FMT_DATE_TIME, orderListBean.getBetTime()));
            helper.setText(R.id.tv_play_type_name, LotteryUtil.getPlayNameByCode2(orderListBean.getBetCode(), orderListBean.getPlayCode()));
            helper.setText(R.id.tv_bet_num, orderListBean.getBetNum());
            Object[] objects = {orderListBean.getMultiple(), BalanceUtils.getScalsBalance(orderListBean.getBetAmount())};
            helper.setText(R.id.tv_bet_info, getString(R.string.the_times_money, objects));
            helper.setText(R.id.tv_note_number, getString(R.string.note_number, orderListBean.getId()));
            helper.setText(R.id.tv_order_number, getString(R.string.order_number_, orderListBean.getBillNo()));
        }
    }
}
