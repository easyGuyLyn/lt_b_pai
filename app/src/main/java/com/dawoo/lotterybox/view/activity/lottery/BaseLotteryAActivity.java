package com.dawoo.lotterybox.view.activity.lottery;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.ssc.GamePlayAdapter;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.mvp.presenter.LotteryPresenter;
import com.dawoo.lotterybox.mvp.view.ILotteryAView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.GsonUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.NoteRcdActivity;
import com.dawoo.lotterybox.view.activity.record.CharseNumRecordFragment;
import com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment;
import com.dawoo.lotterybox.view.activity.record.RecentOpenRecActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.PlayExplainActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCWayPaperActivity;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.HeaderLotteryAView;
import com.dawoo.lotterybox.view.view.PlayTypePopupWindow;
import com.dawoo.lotterybox.view.view.TimeTextView;
import com.dawoo.lotterybox.view.view.dialog.BettingSetDialog;
import com.dawoo.lotterybox.view.view.dialog.BottomNumDialog;
import com.dawoo.lotterybox.view.view.dialog.HowToPlayDialog;
import com.dawoo.lotterybox.view.view.dialog.OrderTipDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.PLAY_BEAN;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.PLAY_MODEL;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.SHOP_DATA;

/**
 * 彩票A盘base
 * Created by b on 18-3-27.
 */

public class BaseLotteryAActivity extends BaseActivity implements ILotteryAView, HeaderLotteryAView.HeadPopupItemClick {

    @BindView(R.id.lLayout_ssc_bottom_second)
    protected LinearLayout mLLayoutSscBottomSecond;
    @BindView(R.id.head_view)
    protected HeaderLotteryAView mHeadView;
    @BindView(R.id.rlv_lottery_record)
    protected RecyclerView mRlvLotteryRecord;
    @BindView(R.id.rlv_game_play)
    protected RecyclerView mRlvGamePlay;
    @BindView(R.id.tv_now_stage)
    protected TextView mTvNowStage;
    @BindView(R.id.tv_now_stage_state)
    protected TextView mTvNowStageState;
    @BindView(R.id.ttv_timer)
    protected TimeTextView mTimeTextView;
    @BindView(R.id.tv_periods)
    protected TextView mTvPeriods;
    @BindView(R.id.tv_computer_select)
    protected TextView mTvComputerSelect;
    @BindView(R.id.tv_setting_special)
    protected TextView mTvSettingSpecial;
    @BindView(R.id.et_multiple)
    protected TextView mEtMultiple;
    @BindView(R.id.tv_place_order)
    protected TextView mTvPlaceOrder;
    @BindView(R.id.tv_bet_info)
    protected TextView mTvBetInfo;
    @BindView(R.id.ll_bottom_menu)
    protected LinearLayout mLlBottomMenu;
    @BindView(R.id.ll_stop_sale)
    public LinearLayout lLStopSale;


    private BottomSheetBehavior mBottomSheetBehavior;
    private PlayTypePopupWindow mPlayTypePopupWindow;
    public PlayTypeBean.PlayBean mPlayTypeBean;//当前的玩法对象

    private CountDownTimerUtils mCountDownTimerUtils;
    private OrderTipDialog mOrderTipDialog;
    public GamePlayAdapter mGamePlayAdapter;
    protected LotteryPresenter mLotteryPresenter;
    BetParamBean mBetParamBean = new BetParamBean(); //投注所需参数实体类
    public List<BetOrderBean> mBetOrderBeans = new ArrayList<>();  //已有记录
    private BottomNumDialog mBottomNumDialog;
    private List<Handicap> mHandicapList = new ArrayList<>();
    protected String dataFile;
    private boolean isComputerSelect = false;  //是否已机选
    protected String mLotteryCode;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_base_ssc);
    }

    @Override
    protected void initViews() {
        mHeadView.setPopupItemClick(this);
        mBottomSheetBehavior = BottomSheetBehavior.from(mLLayoutSscBottomSecond);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvGamePlay.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initData() {
        List<PlayTypeBean> mList = GsonUtil.jsonToList(AssetsReader.getJson(this, dataFile), PlayTypeBean.class);
        mPlayTypeBean = mList.get(0).getPlayBeans().get(0);
        if (mPlayTypeBean.getMainType().equals(mPlayTypeBean.getPlayTypeName()))
            mHeadView.setPlayMethodLeftText(mPlayTypeBean.getMainType());
        else
            mHeadView.setPlayMethodLeftText(mPlayTypeBean.getMainType() + " " + mPlayTypeBean.getPlayTypeName());
        mGamePlayAdapter = new GamePlayAdapter(this, mPlayTypeBean);
        mRlvGamePlay.setAdapter(mGamePlayAdapter);
        initPlayTypePopWindow(mList);

        initBetParamBean();
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryPresenter = new LotteryPresenter(this, this, mLotteryCode);
        mLotteryPresenter.initLayoutChildBean(mPlayTypeBean);
        mLotteryPresenter.getResultByCode();
        mLotteryPresenter.getLotteryExpect();
        mLotteryPresenter.getLotteryOdd(mPlayTypeBean.getBetCode());
        mLotteryPresenter.getLtToken();
        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
    }

    private void initBetParamBean() {
        mBetParamBean.setCode(getIntent().getStringExtra(LT_CODE));
        mBetParamBean.setPlayCode(mPlayTypeBean.getPlayTypeCode());
        mBetParamBean.setBetCode(mPlayTypeBean.getBetCode());
        mBetParamBean.setPlayTypeName(mPlayTypeBean.getMainType() + mPlayTypeBean.getPlayTypeName());
        mEtMultiple.setText(mBetParamBean.getMultiple());
    }

    /**
     * 初始化头部玩法弹窗
     *
     * @param mList
     */
    private void initPlayTypePopWindow(List<PlayTypeBean> mList) {
        mPlayTypePopupWindow = new PlayTypePopupWindow(this, getIntent().getStringExtra(LT_CODE), mList);
        mPlayTypePopupWindow.setOnClickPlayType(new PlayTypePopupWindow.OnClickPlayType() {
            @Override
            public void callBackTypeName(PlayTypeBean.PlayBean playTypeBean) {
                mPlayTypeBean = playTypeBean;
                mLotteryPresenter.initLayoutChildBean(mPlayTypeBean);
                mGamePlayAdapter.setNewData(mPlayTypeBean);
                if (mPlayTypeBean.getMainType().equals(mPlayTypeBean.getPlayTypeName()))
                    mHeadView.setPlayMethodLeftText(mPlayTypeBean.getMainType());
                else
                    mHeadView.setPlayMethodLeftText(mPlayTypeBean.getMainType() + " " + mPlayTypeBean.getPlayTypeName());
                mLotteryPresenter.getLotteryOdd(mPlayTypeBean.getBetCode());
                mTvBetInfo.setVisibility(View.GONE);
                mBetParamBean.setPlayCode(mPlayTypeBean.getPlayTypeCode());
                mBetParamBean.setBetCode(mPlayTypeBean.getBetCode());

                changePlayBean(mPlayTypeBean);
                openHotColdOrOmit(mGamePlayAdapter.type);  //上个玩法保留的是否开启冷热遗漏状态
            }
        });
    }

    @OnClick({R.id.tv_play_method, R.id.tv_place_order, R.id.tv_setting_special, R.id.tv_computer_select, R.id.et_multiple})
    public void onBaseClick(View v) {
        switch (v.getId()) {
            case R.id.tv_play_method:
                // 玩法弹窗打开或关闭
                mPlayTypePopupWindow.doTogglePopupWindow(v, mPlayTypeBean);
                break;

            case R.id.tv_place_order:
                // 下单
                doPlaceOrder();
                break;

            case R.id.tv_setting_special:
                // 订单配置设置
                BettingSetDialog mBettingSetDialog = new BettingSetDialog(this, mBetParamBean);
                mBettingSetDialog.show();
                break;

            case R.id.tv_computer_select:  // 机选
                if (isComputerSelect) {
                    clearSelect();
                } else {
                    doComputerSelect();
                }
                break;
            case R.id.et_multiple:
                // 键盘输入框
                createBottomNum();
                break;
        }
    }

    /**
     * 执行下单
     */
    public void doPlaceOrder() {
        if (mBetParamBean.isBetHint()) {
            goPlaceOrder();
        } else {
            mOrderTipDialog = new OrderTipDialog(this, mBetParamBean);
            mOrderTipDialog.show();
        }
    }


    /**
     * 执行机选
     */
    public void doComputerSelect() {
        if (mPlayTypeBean.getLayoutBeans().get(0).getLayoutType() == 0) {
            mLotteryPresenter.computerSelect(mPlayTypeBean);
        } else {
            ToastUtil.showToastLong(this, "不支持机选");
        }
        mLotteryPresenter.isBetBtEnable(mPlayTypeBean);
    }

    private void clearSelect() {   //清除选择
        isComputerSelect = false;
        mTvBetInfo.setVisibility(View.GONE);
        mTvPlaceOrder.setEnabled(false);
        if (mPlayTypeBean.getLayoutBeans().get(0).getLayoutType() == 2) {
            EditText editText = mRlvGamePlay.getLayoutManager().findViewByPosition(1).findViewById(R.id.et_bet_input);
            editText.setText("");//清除输入框内的数据
            return;
        }
        mLotteryPresenter.clearSelect(mPlayTypeBean);
        mTvComputerSelect.setText(getString(R.string.machine_selection));
        mGamePlayAdapter.notifyDataSetChanged();
    }

    public void goPlaceOrder() {
        BetOrderBean betOrderBean = new BetOrderBean();
        betOrderBean.setParam(mBetParamBean);
        mBetOrderBeans.add(0, betOrderBean);
        Intent intent = new Intent(this, LotteryAShoppingCartActivity.class);
        intent.putExtra(LT_NAME, getIntent().getStringExtra(LT_NAME));
        intent.putExtra(LT_CODE, getIntent().getStringExtra(LT_CODE));
        intent.putExtra(PLAY_MODEL, "official");
        intent.putExtra(PLAY_BEAN, mPlayTypeBean);
        intent.putParcelableArrayListExtra(SHOP_DATA, (ArrayList<? extends Parcelable>) mBetOrderBeans);
        startActivity(intent);
        mTvBetInfo.setVisibility(View.GONE);
        clearSelect();
        mBetParamBean.setMultiple("1");
        mEtMultiple.setText(mBetParamBean.getMultiple());
    }

    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm:ss");

        @Override
        public void onTick(long pMillisUntilFinished) {
            String str1 = sdf1.format(pMillisUntilFinished);
            mTimeTextView.setText(str1);
        }
    };

    //设置倒计时期间回调
    protected void setTickDelegate(CountDownTimerUtils.TickDelegate tickDelegate) {
        mTickDelegate = tickDelegate;
    }


    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate;

    //设置开盘倒计时
    protected void setLeftOpenTimeFinishDelegate(CountDownTimerUtils.FinishDelegate finishDelegate) {
        mLeftOpenTimeFinishDelegate = finishDelegate;
    }

    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate;

    //设置封盘倒计时
    protected void setLeftTimeFinishDelegate(CountDownTimerUtils.FinishDelegate finishDelegate) {
        mLeftTimeFinishDelegate = finishDelegate;
    }

    @Override
    public void OnClickItem(int viewId) {
        switch (viewId) {
            case R.id.tv_lzt_pop:
                SSCWayPaperActivity.goLztActivity(this, getIntent().getStringExtra(LT_CODE));
                break;
            case R.id.tv_wf_pop:
                Intent intent = new Intent(this, PlayExplainActivity.class);
                intent.putExtra(PlayExplainActivity.ASSETS_FILE_NAME, dataFile);
                startActivity(intent);
                break;
            case R.id.tv_trend_chart:
                mHeadView.dismissPop();
                goTrendChart(mHandicapList);
                break;
            case R.id.tv_recent_awards:
                RecentOpenRecActivity.goRecentOPenRecActivity(this, getIntent().getStringExtra(LT_CODE));
                break;
            case R.id.tv_my_bet:
                NoteRcdActivity.goRcordActivity(this, HistoryRepotFormFragment.class.getSimpleName());
                break;
            case R.id.tv_my_chase_number:
                NoteRcdActivity.goRcordActivity(this, CharseNumRecordFragment.class.getSimpleName());
                break;
        }
    }

    /**
     * 更换玩法
     */
    protected void changePlayBean(PlayTypeBean.PlayBean playBean) {
    }

    /**
     * 跳转折线图
     */
    protected void goTrendChart(List<Handicap> mHandicapList) {
        ActivityUtil.startChartActivity(this, mHandicapList, mLotteryCode);
    }

    /**
     * 打开冷热或遗漏
     *
     * @param type -1 ：关闭    0：冷热   1：遗漏
     */
    public void openHotColdOrOmit(int type) {
    }

    /**
     * 开奖
     */
    protected void onOpenLottery() {
    }

    /**
     * 历史数据
     */
    protected void onResultByCodeChild(List<Handicap> handicapList) {
    }

    /**
     * 设置正在开奖ui
     */
    protected void openLottery() {
    }

    /**
     * 设置开奖结果
     */
    protected void setResultToBall(String lotteryCode) {
    }

    @Override
    public void onRecentRecords(List<HandicapWithOpening> awardResultBeans) {

    }

    //show游戏说明dialog
    public void showHowPlayDialog() {
        HowToPlayDialog howToPlayDialog = new HowToPlayDialog(this);
        howToPlayDialog.setPlayWay(mPlayTypeBean.getPlayTypeExplain());
        howToPlayDialog.setSamleBettingProgramTv(mPlayTypeBean.getSampleBet());
        howToPlayDialog.setLotteryNOteNumber(mPlayTypeBean.getSampleOpen());
        howToPlayDialog.show();
    }

    @Override
    public void onResultByCode(List<Handicap> handicapList) {
        if (handicapList != null) {
            mHandicapList = handicapList;
            mTvPeriods.setText(getIntent().getStringExtra(LT_NAME) + " " + getString(R.string.which_periods, handicapList.get(0).getExpect()));
            onResultByCodeChild(mHandicapList);
        }
        mLotteryPresenter.getRecentCloseExpect();
    }

    @Override
    public void onOneResultByCode(List<Handicap> handicapList) {
//        if (handicapList != null) {
//            if (mHandicapList != null && mHandicapList.size() - 1 > 0) {
//                mHandicapList.remove(mHandicapList.size() - 1);
//            }
//            mHandicapList.addAll(0, handicapList);
//            mTvPeriods.setText(getIntent().getStringExtra(LT_NAME) + " " + getString(R.string.which_periods, handicapList.get(0).getExpect()));
//            onResultByCodeChild(mHandicapList);
//        }
    }

    boolean isFirstOpenLottery = true; //第一次进入

    @Override
    public void onRecentCloseExpect(Handicap handicap) {
        if (handicap != null && !TextUtils.isEmpty(handicap.getOpenCode())) {
            setResultToBall(handicap.getOpenCode());  //设置开奖结果
            if (mHandicapList.size() != 0){
                if (!handicap.getExpect().equals(mHandicapList.get(0).getExpect())) {
                    if (mHandicapList != null && mHandicapList.size() - 1 > 0) {
                        mHandicapList.remove(mHandicapList.size() - 1);
                        mHandicapList.add(0, handicap);
                        onResultByCodeChild(mHandicapList);
                    }

                }
            }

        } else {
            if (isFirstOpenLottery)//第一次进入正在开奖，设置开奖动画。
                openLottery();
            countDownTimer.cancel();
            countDownTimer.start();
        }
        if (handicap != null && !TextUtils.isEmpty(handicap.getExpect())) {
            mTvPeriods.setText(getIntent().getStringExtra(LT_NAME) + " " + getString(R.string.which_periods, handicap.getExpect()));
        }
        isFirstOpenLottery = false;
    }


    boolean isNotLotteryWait = false; //处理某些彩种没有封盘的情况

    @Override
    public void onLotteryExpect(BaseHandicap expectDataBean) {
        if (expectDataBean != null) {
            if (expectDataBean.getLeftOpenTime() == 0 && expectDataBean.getLeftTime() == 0) {
                ToastUtil.showToastLong(this, getString(R.string.getexpect_error));
                return;
            }
            mBetParamBean.setExpect(expectDataBean.getExpect());
            mTvNowStage.setText(getString(R.string.which_periods, expectDataBean.getExpect()));
            if (expectDataBean.getLeftOpenTime() > 0) {
                mCountDownTimerUtils.cancel();
                mCountDownTimerUtils.setMillisInFuture(expectDataBean.getLeftOpenTime() * 1000)
                        .setCountDownInterval(1000)
                        .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                        .setTickDelegate(mTickDelegate)
                        .start();
                mTvNowStageState.setText(R.string.not_bet);
                lLStopSale.setVisibility(View.VISIBLE);
                isNotLotteryWait = false;
            } else {
                mCountDownTimerUtils.cancel();
                mCountDownTimerUtils.setMillisInFuture((expectDataBean.getLeftTime()+2) * 1000 )
                        .setCountDownInterval(1000)
                        .setFinishDelegate(mLeftTimeFinishDelegate)
                        .setTickDelegate(mTickDelegate)
                        .start();
                mTvNowStageState.setText(R.string.stop);
                if (isNotLotteryWait)
                    onOpenLottery();
                isNotLotteryWait = true;
            }
        }
    }


    protected CountDownTimer countDownTimer = new CountDownTimer(15000, 15000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            mLotteryPresenter.getRecentCloseExpect();
        }
    };

    @Override
    public void onLotteryOdd(Map<String, LotteryOddBean> o) {
        List<LotteryOddBean> mLotteryOddBeans = new ArrayList<>();  //当前玩法赔率集合
        if (o != null) {
            for (Map.Entry<String, LotteryOddBean> entity : o.entrySet()) {
                LotteryOddBean lotteryOddBean = entity.getValue();
                if (lotteryOddBean != null)
                    mLotteryOddBeans.add(lotteryOddBean);
            }
        }
        if (mLotteryOddBeans.size() != 0) {
            mBetParamBean.setLotteryOddBeans(mLotteryOddBeans);
        }
    }

    @Override
    public void onLtToken(String token) {
        if (token != null) {
            mBetParamBean.setToken(token);
        }
    }

    @Override
    public void onSaveBetOrder(SaveOrderResult submitOrderResultBean) {
//        if (submitOrderResultBean != null) {
//            mBetParamBean.setToken(submitOrderResultBean.getExtend());
//            if (submitOrderResultBean.getError() == 0)
//                ToastUtil.showToastLong(this, "下单成功");
//            else
//                ToastUtil.showToastLong(this, submitOrderResultBean.getMessage());
//        }
    }

    @Override
    public void onSureBetOrder() {
//        mLotteryPresenter.saveBetOrderA(mBetParamBean);

    }

    @Override
    public void onComputerSelect(int position) {
        mGamePlayAdapter.notifyItemChanged(position);
    }

    @Override
    public void onBetBtEnable(boolean isBtEnable) {
        mTvPlaceOrder.setEnabled(isBtEnable);
        if (isBtEnable) {
            isComputerSelect = isBtEnable;
            mTvComputerSelect.setText(getString(R.string.clear_all));
            mTvBetInfo.setVisibility(View.VISIBLE);
        } else {
            mTvComputerSelect.setText(getString(R.string.machine_selection));
            mTvBetInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMosaicNum(String buffer) {
        mBetParamBean.setBetNum(buffer);
    }

    @Override
    public void onComputBetInfo(int bets) {
        mLotteryPresenter.setShowBetInfo(bets, mBetParamBean);
    }

    @Override
    public void onShowBetInfo(String buffer) {
        mTvBetInfo.setText(buffer);
    }

    public void isBetBtEnable() {
        mLotteryPresenter.isBetBtEnable(mPlayTypeBean);
    }

    public void setShowBetInfo(int bets) {
        mLotteryPresenter.setShowBetInfo(bets, mBetParamBean);
        mEtMultiple.setText(mBetParamBean.getMultiple());   //同步设置dialog返回时设置的倍数
        if ("1".equals(mBetParamBean.getBonusModel())) {
            mTvSettingSpecial.setText(getString(R.string.tv_setting_special));
        } else if ("10".equals(mBetParamBean.getBonusModel())) {
            mTvSettingSpecial.setText(getString(R.string.tv_setting_horn));
        } else if ("100".equals(mBetParamBean.getBonusModel())) {
            mTvSettingSpecial.setText(getString(R.string.tv_setting_branch));
        }
    }

    public void computBetInfoByInput(String str, int bets) {
        if (TextUtils.isEmpty(str)) {
            mTvPlaceOrder.setEnabled(false);
            mTvBetInfo.setVisibility(View.GONE);
        } else {
            mBetParamBean.setBetNum(str);
            setShowBetInfo(bets);
            mTvBetInfo.setVisibility(View.VISIBLE);
            mTvPlaceOrder.setEnabled(true);
        }
    }

    void createBottomNum() {
        if (mBottomNumDialog == null) {
            mBottomNumDialog = new BottomNumDialog(this, mEtMultiple);
            mBottomNumDialog.setListener(mBottomNumDialogShowOrHide);
            mBottomNumDialog.show();
        } else {
            mBottomNumDialog.show();
        }
    }

    BottomNumDialog.BottomNumDialogShowOrHide mBottomNumDialogShowOrHide = new BottomNumDialog.BottomNumDialogShowOrHide() {

        @Override
        public void show() {
            int bottomHeight = mBottomNumDialog.mLLBottomNum.getHeight();
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLlBottomMenu.getLayoutParams();
            lp.setMargins(0, 0, 0, bottomHeight);
            mLlBottomMenu.setLayoutParams(lp);
        }

        @Override
        public void hide() {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLlBottomMenu.getLayoutParams();
            lp.setMargins(0, 0, 0, 0);
            mLlBottomMenu.setLayoutParams(lp);
            String multiple = mEtMultiple.getText().toString().trim();
            if (!TextUtils.isEmpty(multiple)) {
                mBetParamBean.setMultiple(multiple);
                mLotteryPresenter.setShowBetInfo(mBetParamBean.getBetCount(), mBetParamBean);
            } else {
                mEtMultiple.setText(mBetParamBean.getMultiple());
            }
        }
    };

    /**
     * 返回隐藏dialog键盘
     */
    @Override
    public void onBackPressed() {
        if (mBottomNumDialog != null && mBottomNumDialog.mBaseDialog.isShowing()) {
            mBottomNumDialog.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    public void orderDataChange(OrderEventBean orderEventBean) {
        int type = orderEventBean.getChangeDataType();
        if (type == -1) {
            mBetOrderBeans.clear();
        } else if (type >= 3000) {
            mBetOrderBeans.add(0, orderEventBean.getBetOrderBean());
        } else if (type >= 2000) {
            if (mBetOrderBeans.size() == 0)
                return;
            mBetOrderBeans.get(type % 2000).setMultiple(orderEventBean.getBetOrderBean().getMultiple());
            mBetOrderBeans.get(type % 2000).setBetAmount(orderEventBean.getBetOrderBean().getBetAmount());
        } else if (type >= 1000) {
            if (mBetOrderBeans.size() == 0)
                return;
            mBetOrderBeans.remove(type % 1000);
        }
    }

    @Override
    protected void onDestroy() {
        mPlayTypePopupWindow.dissMissPopWindow();
        mCountDownTimerUtils.cancel();
        countDownTimer.cancel();
        mHeadView.dismissPop();
        mLotteryPresenter.onDestory();
        if (mBottomNumDialog != null)
            mBottomNumDialog.onDestory();
        super.onDestroy();
    }
}
