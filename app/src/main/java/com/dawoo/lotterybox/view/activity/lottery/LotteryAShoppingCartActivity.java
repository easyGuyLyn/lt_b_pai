package com.dawoo.lotterybox.view.activity.lottery;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetOrderABean;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.mvp.presenter.LotteryPresenter;
import com.dawoo.lotterybox.mvp.view.ILotteryAView;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShopResultActivity.ORDER_NUMBER;

/**
 * Created by b on 18-2-14.
 * 购物车
 */

public class LotteryAShoppingCartActivity extends BaseActivity implements ILotteryAView {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tv_bet_state)
    TextView mTvBetState;
    @BindView(R.id.tv_timer_time)
    TextView mTvTimerTime;
    @BindView(R.id.ll_timer)
    LinearLayout mLlTimer;
    @BindView(R.id.tv_clear_list)
    TextView mTvClearList;
    @BindView(R.id.tv_comput_select)
    TextView mTvComputSelect;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.rl_menu_and_state)
    RelativeLayout mRlMenuAndState;
    @BindView(R.id.rlv_bet_list)
    RecyclerView mRlvBetList;
    @BindView(R.id.service_agreement)
    TextView mServiceAgreement;
    @BindView(R.id.tv_all_bet)
    TextView mTvAllBet;
    @BindView(R.id.tv_submit_bet)
    TextView mTvSubmitBet;
    private ShopCartQuickAdapter mMQuickAdapter;
    public static final String SHOP_DATA = "shop_data";
    public static final String PLAY_MODEL = "play_model";
    public static final String PLAY_BEAN = "Play_bean";
    double mTotalMoney; //总金钱
    int mQuantity; //总单数
    int mBets;     //总注数
    private LotteryPresenter mLotteryPresenter;
    private CountDownTimerUtils mCountDownTimerUtils;
    private List<BetOrderBean> mBetOrderBeans = new ArrayList<>();
    private String mExpect;     //期数
    private String mToken;      //防重token
    private String mCode;       //彩种coed
    private String playModel;   //官方or传统
    private PlayTypeBean.PlayBean mPlayBean;  //官方机选一注用到参数
    BetOrderBean mRecordBetOrderBean;
    BetOrderBean mComputeBetOrderBean;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_base_ssc_shop_cart);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        playModel = getIntent().getStringExtra(PLAY_MODEL);
        mCode = getIntent().getStringExtra(LT_CODE);
        mBetOrderBeans = getIntent().getParcelableArrayListExtra(SHOP_DATA);
        mHeadView.setHeader(getIntent().getStringExtra(LT_NAME), true);
        if (DataCenter.getInstance().isLogin()) {
            mTvBalance.setText(DataCenter.getInstance().getUser().getBalance());
        }
        if ("tradition".equals(playModel))
            mMQuickAdapter = new ShopCartQuickAdapter(R.layout.item_shop_cart_b);
            else
            mMQuickAdapter = new ShopCartQuickAdapter(R.layout.item_shop_cart);
        mMQuickAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mRlvBetList.setLayoutManager(new LinearLayoutManager(this));
        mRlvBetList.setAdapter(mMQuickAdapter);
        mMQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_bet_delete:
                        mTotalMoney -= Double.valueOf(mBetOrderBeans.get(position).getBetAmount());
                        mQuantity--;
                        mBets -= mBetOrderBeans.get(position).getBetCount();
                        Object[] objects = {mBets, mTotalMoney};
                        mTvAllBet.setText(getString(R.string.all_bet, objects));
                        mBetOrderBeans.remove(position);
                        mMQuickAdapter.notifyDataSetChanged();
                        OrderEventBean orderEventBean = new OrderEventBean();
                        orderEventBean.setChangeDataType(1000 + position);
                        RxBus.get().post(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE, orderEventBean);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        for (BetOrderBean betOrderBean : mBetOrderBeans) {
            mTotalMoney += Double.valueOf(betOrderBean.getBetAmount());
            mBets += betOrderBean.getBetCount();
            mQuantity++;
        }
        if ("tradition".equals(playModel)) {
            mTvComputSelect.setVisibility(View.GONE);
        } else {
            mPlayBean = getIntent().getParcelableExtra(PLAY_BEAN);
            if (mPlayBean.getPlayTypeName().contains("单式"))
                mTvComputSelect.setVisibility(View.GONE);
            mRecordBetOrderBean = new BetOrderBean();
            mRecordBetOrderBean.setPlayCode(mBetOrderBeans.get(0).getPlayCode());
            mRecordBetOrderBean.setBetCode(mBetOrderBeans.get(0).getBetCode());
            mRecordBetOrderBean.setOdd(mBetOrderBeans.get(0).getOdd());
        }
        mMQuickAdapter.setNewData(mBetOrderBeans);
        Object[] objects = {mBets, mTotalMoney};
        mTvAllBet.setText(getString(R.string.all_bet, objects));

        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
        mLotteryPresenter = new LotteryPresenter(this, this, mCode);
        mLotteryPresenter.getLotteryExpect();
        mLotteryPresenter.getLtToken();
    }

    public void sureDialogShow() {
        if (mBetOrderBeans.size() != 0)
            mLotteryPresenter.sureNoteDialogShow(getIntent().getStringExtra(LT_NAME), mExpect, mBets + "", mTotalMoney + "");
    }

    @OnClick({R.id.tv_submit_bet, R.id.tv_clear_list, R.id.tv_comput_select})
    public void onShopCartClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit_bet:   //确认投注
                sureDialogShow();
                break;
            case R.id.tv_clear_list:   //清空列表
                clearData();
                break;
            case R.id.tv_comput_select:  //机选一注
                computeSelect();
                break;
        }

    }

    private void computeSelect() {

        mComputeBetOrderBean = null;
        mComputeBetOrderBean = new BetOrderBean();
        mComputeBetOrderBean.setBetCode(mRecordBetOrderBean.getBetCode());
        mComputeBetOrderBean.setPlayCode(mRecordBetOrderBean.getPlayCode());
        mComputeBetOrderBean.setOdd(mRecordBetOrderBean.getOdd());
        mComputeBetOrderBean.setMultiple("1");
        mComputeBetOrderBean.setBonusModel("1");
        mComputeBetOrderBean.setRebate(0);

        mLotteryPresenter.clearSelect(mPlayBean);
        mLotteryPresenter.computerSelect(mPlayBean);
        mLotteryPresenter.isBetBtEnable(mPlayBean);
    }

    private void clearData() {
        mBetOrderBeans.clear();
        mMQuickAdapter.notifyDataSetChanged();
        mBets = 0;
        mTotalMoney = 0;
        Object[] objects = {mBets, mTotalMoney};
        mTvAllBet.setText(getString(R.string.all_bet, objects));
        OrderEventBean orderEventBean = new OrderEventBean();
        orderEventBean.setChangeDataType(-1);
        RxBus.get().post(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE, orderEventBean);
    }

    @Override
    public void onResultByCode(List<Handicap> handicapList) {

    }

    @Override
    public void onOneResultByCode(List<Handicap> handicapList) {

    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {

    }

    @Override
    public void onRecentRecords(List<HandicapWithOpening> handicapWithOpeningList) {

    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {
        long hourTime = 3600 * 1000;
        long minTime = 60 * 1000;
        @Override
        public void onTick(long pMillisUntilFinished) {
            StringBuffer buffer = new StringBuffer();
            long hour = pMillisUntilFinished / hourTime;
            long min = (pMillisUntilFinished % hourTime) / minTime;
            long sec = ((pMillisUntilFinished % hourTime) % minTime) / 1000;
            if (mCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())) {
                if (hour < 10) {
                    buffer.append("0" + hour + ":");
                } else {
                    buffer.append(hour + ":");
                }
            } else {
                min = pMillisUntilFinished / minTime;
            }
            if (min < 10) {
                buffer.append("0" + min + ":");
            } else {
                buffer.append(min + ":");
            }
            if (sec < 10) {
                buffer.append("0" + sec);
            } else {
                buffer.append(sec);
            }
            mTvTimerTime.setText(buffer);
        }
    };

    //倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            if (mCode.equalsIgnoreCase(LotteryEnum.HKLHC.getCode())
                    || mCode.equalsIgnoreCase(LotteryEnum.FC3D.getCode())) {
                mTvTimerTime.setText("00:00:00");
            }else{
                mTvTimerTime.setText("00:00");
            }

            mLotteryPresenter.getLotteryExpect();
        }
    };

    @Override
    public void onLotteryExpect(BaseHandicap expectDataBean) {
        if (expectDataBean != null) {
            mExpect = expectDataBean.getExpect();
            if (expectDataBean.getLeftOpenTime() == 0 && expectDataBean.getLeftTime() == 0) {
                ToastUtil.showToastLong(this,getString(R.string.getexpect_error));
                return;
            }
            String status = "";
            long time = 0;
            if (expectDataBean.getLeftOpenTime() > 0) {
                status = getString(R.string.not_bet);
                time = expectDataBean.getLeftOpenTime() * 1000;
            } else {
                status = getString(R.string.stop);
                time = expectDataBean.getLeftTime() * 1000;
            }
            mCountDownTimerUtils.cancel();
            mCountDownTimerUtils.setMillisInFuture(time)
                    .setCountDownInterval(1000)
                    .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                    .setTickDelegate(mTickDelegate)
                    .start();
            Object[] str = {expectDataBean.getExpect(), status};
            mTvBetState.setText(getString(R.string.which_periods_stop, str));

        }
    }

    @Override
    public void onLotteryOdd(Map<String, LotteryOddBean> o) {

    }

    @Override
    public void onLtToken(String token) {
        if (token != null)
            mToken = token;
    }

    @Override
    public void onSaveBetOrder(SaveOrderResult submitOrderResultBean) {
        if (submitOrderResultBean != null) {
            mToken = submitOrderResultBean.getExtend();
            if (TextUtils.isEmpty(submitOrderResultBean.getCode()) && submitOrderResultBean.getError() == 0) {
                DataCenter.getInstance().getUser().setBalance(BalanceUtils.getScalsBalance(submitOrderResultBean.getData().getBalance())); //设置用户余额
                RxBus.get().post(ConstantValue.EVENT_TYPE_USER_INFO, "mcfragment_user_info");
                Intent intent = new Intent(this, LotteryAShopResultActivity.class);
                intent.putExtra(ORDER_NUMBER, submitOrderResultBean.getData().getBillNo());
                intent.putExtra(LT_CODE, getIntent().getStringExtra(LT_CODE));
                intent.putExtra(LT_NAME, getIntent().getStringExtra(LT_NAME));
                startActivity(intent);
                OrderEventBean orderEventBean = new OrderEventBean();
                orderEventBean.setChangeDataType(-1);
                RxBus.get().post(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE, orderEventBean);
                finish();
            } else
                ToastUtil.showToastLong(this, submitOrderResultBean.getMessage());
        }
    }

    @Override
    public void onSureBetOrder() {
        BetOrderABean betOrderABean = new BetOrderABean();
        betOrderABean.setCode(mCode);
        betOrderABean.setExpect(mExpect);
        betOrderABean.setTotalMoney(mTotalMoney + "");
        betOrderABean.setQuantity(mQuantity + "");
        betOrderABean.setPlayModel(playModel);
        betOrderABean.setBetOrders(mBetOrderBeans);
        String betForm = new Gson().toJson(betOrderABean);
        mLotteryPresenter.saveBetOrder(mToken, betForm);
    }

    @Override
    public void onComputerSelect(int position) {

    }

    @Override
    public void onBetBtEnable(boolean isBtEnable) {
        if (isBtEnable){
            double allBetGold = 0;  //总投注金币
            if ("1".equals(mComputeBetOrderBean.getBonusModel())) {
                allBetGold = mComputeBetOrderBean.getBetCount() * 1;
            } else if ("10".equals(mComputeBetOrderBean.getBonusModel())) {
                allBetGold = mComputeBetOrderBean.getBetCount() * 0.1;
            } else if ("100".equals(mComputeBetOrderBean.getBonusModel())) {
                allBetGold = mComputeBetOrderBean.getBetCount() * 0.01;
            }
            allBetGold *= 2;   //目前model 固定为2
            allBetGold *= Long.valueOf(mComputeBetOrderBean.getMultiple());
            mComputeBetOrderBean.setBetAmount(allBetGold + "");

            mBetOrderBeans.add( 0,mComputeBetOrderBean);
            mMQuickAdapter.notifyDataSetChanged();
            mTotalMoney += Double.valueOf(mComputeBetOrderBean.getBetAmount());
            mQuantity++;
            this.mBets += mComputeBetOrderBean.getBetCount();
            Object[] objects = {mBets, mTotalMoney};
            mTvAllBet.setText(getString(R.string.all_bet, objects));
            OrderEventBean orderEventBean = new OrderEventBean();
            orderEventBean.setChangeDataType(3000);
            orderEventBean.setBetOrderBean(mComputeBetOrderBean);
            RxBus.get().post(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE, orderEventBean);
        }
    }

    @Override
    public void onMosaicNum(String buffer) {
        mComputeBetOrderBean.setBetNum(buffer);
    }

    @Override
    public void onComputBetInfo(int bets) {
        mComputeBetOrderBean.setBetCount(bets);
    }

    @Override
    public void onShowBetInfo(String buffer) {

    }

    class ShopCartQuickAdapter extends BaseQuickAdapter {
        public ShopCartQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            BetOrderBean betOrderBean = (BetOrderBean) item;
            helper.setText(R.id.tv_bet_num, betOrderBean.getBetNum());
            String type = LotteryUtil.getBetNameByCode(betOrderBean.getBetCode()) + " " + LotteryUtil.getPlayName(betOrderBean.getPlayCode());

            EditText multiple = helper.getView(R.id.et_multiple);
            multiple.setTag(betOrderBean);
            multiple.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (multiple.getTag() != betOrderBean)
                        return;
                    if (TextUtils.isEmpty(s))
                        return;
                    if (s.equals("0")) {
                        multiple.setText("1");
                    }
                    if ("tradition".equals(playModel)){
                        mTotalMoney -= Double.valueOf(betOrderBean.getBetAmount());
                        double money = Double.valueOf(s.toString());
                        mTotalMoney += money;
                        betOrderBean.setBetAmount(s.toString());
                        Object[] objects = {betOrderBean.getBetCount(), BalanceUtils.getScalsBalance(betOrderBean.getBetAmount())};
                        helper.setText(R.id.tv_bet_info,getString(R.string.the_bets_money,objects));
                    }else {
                        mTotalMoney -= Double.valueOf(betOrderBean.getBetAmount());
                        double money = Double.valueOf(betOrderBean.getBetAmount()) / Double.valueOf(betOrderBean.getMultiple());
                        double money_ = money * Double.valueOf(s.toString());
                        mTotalMoney += money_;
                        betOrderBean.setBetAmount(money_ + "");
                        betOrderBean.setMultiple(s.toString());
                        Object[] objects = {type, betOrderBean.getBetCount(), betOrderBean.getMultiple(), BalanceUtils.getScalsBalance(betOrderBean.getBetAmount())};
                        helper.setText(R.id.bet_comput, getString(R.string.shop_bet_info, objects));
                    }
                    Object[] objects = {mBets, BalanceUtils.getScalsBalance(mTotalMoney)};
                    mTvAllBet.setText(getString(R.string.all_bet, objects));
                    OrderEventBean orderEventBean = new OrderEventBean();
                    orderEventBean.setChangeDataType(2000 + helper.getLayoutPosition());
                    orderEventBean.setBetOrderBean(betOrderBean);
                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE, orderEventBean);

                }
            });

            if ("tradition".equals(playModel)){
                helper.setText(R.id.bet_comput,type);
                multiple.setText(Math.round(Double.valueOf(betOrderBean.getBetAmount()))+"");
                Object[] objects = {betOrderBean.getBetCount(), BalanceUtils.getScalsBalance(betOrderBean.getBetAmount())};
                helper.setText(R.id.tv_bet_info,getString(R.string.the_bets_money,objects));
            }else {
                Object[] objects = {type, betOrderBean.getBetCount(), betOrderBean.getMultiple(), BalanceUtils.getScalsBalance(betOrderBean.getBetAmount())};
                helper.setText(R.id.bet_comput, getString(R.string.shop_bet_info, objects));
                multiple.setText(betOrderBean.getMultiple());
            }

            helper.addOnClickListener(R.id.iv_bet_delete);

        }
    }

    /**
     * 加载帐户数据后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_USER_INFO)})
    public void onUserInfoCallBack(String s) {
        // 当加载用户相关信息后显示用户信息
        if (DataCenter.getInstance().isLogin()) {
            mTvBalance.setText(DataCenter.getInstance().getUser().getBalance());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimerUtils.cancel();
        RxBus.get().unregister(this);
    }
}
