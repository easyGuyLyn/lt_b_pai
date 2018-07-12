package com.dawoo.lotterybox.view.activity.lottery;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BBetParamForm;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.ChooseMoney;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.bean.playtype.PostPlayBean;
import com.dawoo.lotterybox.mvp.presenter.LotteryPresenter;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.GsonUtil;
import com.dawoo.lotterybox.util.LotteryBMemoryUtil;
import com.dawoo.lotterybox.util.Lottery_b_saveBetOrder;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.anim.LotteryBAnimSetUtil;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTB_History_QuickAdapter;
import com.dawoo.lotterybox.view.view.ArcView;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;
import com.dawoo.lotterybox.view.view.TimeTextView;
import com.dawoo.lotterybox.view.view.XNumberKeyboardView;
import com.dawoo.lotterybox.view.view.bottomsheet.SlidingUpPanelLayout;
import com.dawoo.lotterybox.view.view.dialog.HowToPlayDialog;
import com.dawoo.lotterybox.view.view.popu.SmallHelperPopuWindow;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.PLAY_MODEL;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.SHOP_DATA;
import static com.dawoo.lotterybox.view.view.LotteryBFragmentManager.TOP_FRGMENT;

/**
 * 基本彩票投注activity
 * 包括120的彩票列表
 * 小助手
 * <p>
 * 表单 下单提示，投注确认，提交表单
 */
public class BaseLotteryBActivity extends BaseActivity implements LotteryBView, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.left_btn)
    protected
    FrameLayout mLeftBtn;
    @BindView(R.id.rlv_lottery_record)
    protected
    RecyclerView mRlvLotteryRecord;
    @BindView(R.id.title_name)
    protected
    TextView mTitleName;
    @BindView(R.id.tv_periods)
    protected
    TextView mTvPeriods;
    @BindView(R.id.tv_now_stage)
    protected
    TextView mNextExpectTv;
    @BindView(R.id.tv_now_stage_state)
    protected
    TextView mNextExpectTvState;
    @BindView(R.id.iv_memory)
    protected
    ImageView mIvMemory;
    @BindView(R.id.tv_input_note)
    protected
    TextView mTvInputNote;
    @BindView(R.id.rl_touzhu)
    protected
    LinearLayout mRlTouzhu;
    @BindView(R.id.view_keyboard)
    protected
    XNumberKeyboardView mViewKeyboard;
    @BindView(R.id.tv_tz_count_money)
    protected
    TextView mTvTzCountMoney;
    @BindView(R.id.tv_tz_prizedMoney)
    protected
    TextView mTvTzPrizedMoney;
    @BindView(R.id.ll_top)
    protected
    LinearLayout mLlTop;
    @BindView(R.id.rl_head)
    protected
    RelativeLayout mRlHead;
    @BindView(R.id.rlv_game_parentTitle)
    protected
    RecyclerView mPlayTypeRecyclerView;
    @BindView(R.id.sliding_layout)
    protected
    SlidingUpPanelLayout mSlidingLayout;
    @BindView(R.id.rv_tz_choose)
    protected
    RecyclerView mMoneyRecyclerView;
    @BindView(R.id.ll_fendan)
    protected
    LinearLayout mLlFendan;
    @BindView(R.id.ttv_timer)
    protected
    TimeTextView mTimeTextView;
    @BindView(R.id.tv_xdh)
    protected
    TextView mTvXdh;
    @BindView(R.id.hot_and_cold)
    CheckBox mHotAndCold;
    @BindView(R.id.cb_omit)
    CheckBox mCbOmit;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.lLayout_ssc_bottom_second)
    RelativeLayout lLayout_ssc_bottom_second;
    @BindView(R.id.tv_bottom_content_init)
    Button tv_bottom_content_init;
    @BindView(R.id.iv_open_close_ui)
    ImageView iv_open_close_ui;
    @BindView(R.id.ll_base)
    RelativeLayout mLl_base;
    @BindView(R.id.ll_nums)
    LinearLayout mLl_nums;
    @BindView(R.id.iv_anim_dog)
    ImageView mIv_anim_dog;
    @BindView(R.id.fragment_content)
    protected
    FrameLayout mFrameLayout;
    @BindView(R.id.ll_anim_dog)
    protected
    LinearLayout mLl_anim_dog;
    @BindView(R.id.arc_view)
    ArcView arcView;
    @BindView(R.id.show_result_iv)
    ImageView showResultIV;
    protected long OPEN_DELATED = 10000;//开奖延迟 默认10s  具体可由子类再改
    protected String mLotteryCode;//当前的彩种code
    protected String mLotteryName;//当前的彩种名
    protected LotteryPresenter mPresenter;
    protected CountDownTimerUtils mCDTimerUtils;
    protected BaseQuickAdapter mHistoryAdapter;
    // 余下彩票需要做的事情，需要继承该接口
    protected OnTheNextToDoListener mOnTheNextToDoListener;
    protected SmallHelperPopuWindow mSmallHelper;//小助手
    CountDownTimerUtils.TickDelegate mTickDelegate;//倒计时期间回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate;//距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate;//距离封盘时间 倒计时结束回调
    boolean isNotLotteryWait = false; //处理某些彩种没有封盘的情况
    protected String mCurrentExpect;//当前的期号
    protected List<PlayChooseBean> mPlayChooseBeans = new ArrayList<>();//导航数据源
    protected BottomChooseMoneyAdapter mBottomChooseMoneyAdapter;//选择金钱适配器
    protected List<PlayDetailBean> mPlayDetailBeans = new ArrayList<>();//任何玩法所选好的对象
    protected List<PostPlayBean> mPostPlayBeans = new ArrayList<>();//注单清单
    protected String mCurrentPlayType;
    protected Handler mHandler;
    protected boolean mIsShowKeyBoard;//是否展示着数字软键盘
    protected boolean mChooseMoneyBottm;//选择金钱滑动控制
    protected PlayTypeChooseQuickAdapter mPlayTypeChooseQuickAdapter;//导航选择适配器
    protected FragmentManager mFragmentManager;
    protected OpenLotteryRunnable mOpenLotteryRunnable = new OpenLotteryRunnable();//延时开奖 runnable
    protected HowToPlayDialog mHowToPlayDialog;//玩法说明dialog
    private BBetParamForm mBBetParamForm = new BBetParamForm();//提交参数对象
    public static int changeType = 0;//0 1 冷热 2 遗漏
    private String token = "";//token

    //动画组
    private AnimatorSet mAnimSetPeriods = new AnimatorSet();//期数动画
    private AnimatorSet mAnimSetPeriodsNext = new AnimatorSet();//下一期期数动画
    private AnimatorSet mAnimSetXh = new AnimatorSet();//形態动画
    private AnimatorSet mAnimSetNums = new AnimatorSet();//号码动画
    private AnimatorSet mAnimSetStatus = new AnimatorSet();//盘口状态动画
    private AnimatorSet mAnimSetTime = new AnimatorSet();//时间view动画
    private AnimationDrawable mAnimationDrawable;//fragment切换之间的填充动画

    @Override
    protected void createLayoutView() {
        // 需要初始化
        // 当前彩种代码
        mLotteryCode = "";
        mLotteryName = "";
        // 120期历史列表ItemView
        mHistoryAdapter = new QTB_History_QuickAdapter(R.layout.item_ssc_b_lottery_result);
        changeType = 0;
    }

    @Override
    protected void initViews() {
        if (!initCheck(true)) {
            return;
        }
        initHistoryRecycleView();
        initPlayTypeView();
        initMoneyChooseView();
        mIv_anim_dog.setImageResource(R.drawable.anim_refresh_head_dog_eat_short);
        mAnimationDrawable = (AnimationDrawable) mIv_anim_dog.getDrawable();
        mAnimationDrawable.start();
        mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        iv_open_close_ui.setImageResource(R.drawable.ic_expand_less_black_40dp);
        mLl_base.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1_aplph));
        mHowToPlayDialog = new HowToPlayDialog(this);
        mSmallHelper = new SmallHelperPopuWindow(this, mLotteryCode);
        mHotAndCold.setOnCheckedChangeListener(this);
        mCbOmit.setOnCheckedChangeListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setBalanceData();
    }

    //设置账户数据
    private void setBalanceData() {
        if (DataCenter.getInstance().isLogin()) {
            mTvBalance.setText(DataCenter.getInstance().getUser().getBalance());
        } else {
            mTvBalance.setText("0.00");
        }
    }

    @Override
    protected void initData() {
        if (!initCheck(false)) {
            return;
        }
        mHandler = new Handler();
        mLl_nums.setVisibility(View.GONE);
        initCodeName();
        initPlayTypeData();
        initPresent();
        initListeners();
        mCDTimerUtils = CountDownTimerUtils.getCountDownTimer(); // 计数倒计时
        mOnTheNextToDoListener.onNextInit(); // 接下来初始化
    }

    /**
     * 初始化
     * 彩种code
     * 彩种name
     */
    private void initCodeName() {
        if (getIntent().getStringExtra(LT_CODE) != null) {
            mLotteryCode = getIntent().getStringExtra(LT_CODE);
            mLotteryName = getIntent().getStringExtra(LT_NAME);
            mTitleName.setText(mLotteryName);
        }
    }

    /**
     * 初始化presenter
     */
    void initPresent() {
        mPresenter = new LotteryPresenter(this, this, mLotteryCode);
        BLotteryOddFactory.getInstance().clearOddMap();
        mPresenter.getResultByCode(); // 120期数据
        mPresenter.getLtToken(); // 防止重复token
        mPresenter.getLotteryOdd(""); // 赔率
        mPresenter.initMemoryButtonUI(mLotteryCode, mIvMemory, mTvInputNote);//金币记忆
    }


    @Override
    public void onResultByCode(List<Handicap> handicapList) {
        if (handicapList != null && handicapList.size() != 0) {
            mHistoryAdapter.setNewData(handicapList);
            mRlvLotteryRecord.smoothScrollToPosition(0);
            BLotteryOddFactory.getInstance().setmHistorys(handicapList);
            mOnTheNextToDoListener.onNextResultByCode(handicapList);
            mSmallHelper.setHandicaps(handicapList);
            mLl_nums.setVisibility(View.VISIBLE);
            mTvPeriods.setText(mLotteryName + " " + getString(R.string.which_periods, handicapList.get(0).getExpect()));
            LotteryBAnimSetUtil.doPeriodTextViewAnim(mAnimSetPeriods, mTvPeriods, DensityUtil.dp2px(this, 30));
            LotteryBAnimSetUtil.doXhAnim(mAnimSetXh, mTvXdh, DensityUtil.dp2px(this, 30));
            LotteryBAnimSetUtil.doNumsAnim(mAnimSetNums, mLl_nums, DensityUtil.dp2px(this, 500));
            mPresenter.getRecentCloseExpect();
        }
        mPresenter.getLotteryExpect(); // 彩票期数
    }

    @Override
    public void onOneResultByCode(List<Handicap> handicapList) {
//        if (handicapList != null && handicapList.size() != 0) {
//            mHistoryAdapter.addData(0, handicapList.get(0));
//            mRlvLotteryRecord.smoothScrollToPosition(0);
//            BLotteryOddFactory.getInstance().setmHistorys(mHistoryAdapter.getData());
//            mOnTheNextToDoListener.onNextResultByCode(mHistoryAdapter.getData());
//            mLl_nums.setVisibility(View.VISIBLE);
//            mSmallHelper.setHandicaps(mHistoryAdapter.getData());
//            mTvPeriods.setText(mLotteryName + " " + getString(R.string.which_periods, handicapList.get(0).getExpect()));
//        }
    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {
        mTvPeriods.setText(mLotteryName + " " + getString(R.string.which_periods, handicap.getExpect()));
        if (null != handicap
                && !TextUtils.isEmpty(handicap.getOpenCode())) {
            if (mHistoryAdapter.getData().size() > 0 && ((Handicap) (mHistoryAdapter.getData().get(0))).getExpect().equals(handicap.getExpect())) {
                return;
            }
            mHistoryAdapter.getData().add(0, handicap);
            mHistoryAdapter.notifyDataSetChanged();
            mRlvLotteryRecord.smoothScrollToPosition(0);
            BLotteryOddFactory.getInstance().setmHistorys(mHistoryAdapter.getData());
            mOnTheNextToDoListener.onNextResultByCode(mHistoryAdapter.getData());
            mSmallHelper.setHandicaps(mHistoryAdapter.getData());
            mLl_nums.setVisibility(View.VISIBLE);
            LotteryBAnimSetUtil.doXhAnim(mAnimSetXh, mTvXdh, DensityUtil.dp2px(this, 30));
        } else {
            mTvXdh.setText("");
            mOnTheNextToDoListener.openLottery();
            mHandler.postDelayed(mOpenLotteryRunnable, OPEN_DELATED);
        }
    }

    @Override
    public void onRecentRecords(List<HandicapWithOpening> handicapWithOpeningList) {
        mOnTheNextToDoListener.onNextRecentRecords(handicapWithOpeningList);
    }


    @Override
    public void onLotteryExpect(BaseHandicap baseHandicap) {
        // 处理彩票的盘口
        doHandicap(baseHandicap);
    }

    @Override
    public void onLotteryOdd(Map<String, LotteryOddBean> o) {
        if (o == null) return;
        tv_bottom_content_init.setVisibility(View.GONE);
        BLotteryOddFactory.getInstance().setStringLotteryOddBeanMap(o);
        resfreshOddData();
    }

    @Override
    public void onLtToken(String token) {
        if (token != null) {
            this.token = token;
        }
    }

    @Override
    public void onSaveBetOrder(SaveOrderResult saveOrderResult) {
        if (saveOrderResult != null) {
            this.token = saveOrderResult.getExtend();
            RxBus.get().post(TOP_FRGMENT, "-1");
            offTouZhuView();
            SingleToast.showMsg(saveOrderResult.getMessage() == null ? "注单成功" : saveOrderResult.getMessage());
            mTvBalance.setText(saveOrderResult.getData().getBalance() + "");
            DataCenter.getInstance().getUser().setBalance(saveOrderResult.getData().getBalance());
        }
    }

    @Override
    public void onSureBetOrder() {
        mPresenter.saveBetOrderB(token, mBBetParamForm);
    }

    /**
     * 检查需要的变量初始化
     *
     * @return
     */
    private boolean initCheck(boolean haveToast) {
        if (mHistoryAdapter == null) {
            if (haveToast) {
                SingleToast.showMsg("请先初始化历史数据适配器");
            }
            return false;
        }
        if (mLotteryCode == null) {
            if (haveToast) {
                SingleToast.showMsg("请先初始化彩种代码");
            }
            return false;
        }
        return true;
    }


    void doHandicap(BaseHandicap baseHandicap) {
        if (baseHandicap != null) {
            if (baseHandicap.getLeftOpenTime() == 0 && baseHandicap.getLeftTime() == 0) {
                SingleToast.showMsg("盘口数据获取失败");
                //mPresenter.getLotteryExpect();
                return;
            }
            Log.e("lynn", baseHandicap.getLeftOpenTime() + "//" + baseHandicap.getLeftTime());

            mCurrentExpect = baseHandicap.getExpect();
            if (TextUtils.isEmpty(mNextExpectTv.getText().toString())) {
                LotteryBAnimSetUtil.doPeriodTextViewAnim(mAnimSetPeriodsNext, mNextExpectTv, DensityUtil.dp2px(this, 30));
            }
            mNextExpectTv.setText(getString(R.string.which_periods, baseHandicap.getExpect()));
            // 设置封单倒计时
            if (baseHandicap.getLeftOpenTime() > 0) {
                mCDTimerUtils.cancel();
                mCDTimerUtils.setMillisInFuture(baseHandicap.getLeftOpenTime() * 1000)
                        .setCountDownInterval(1000)
                        .setTickDelegate(mTickDelegate)
                        .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                        .start();
                if (mLotteryCode.equals(LotteryEnum.BJKL8.getCode())) {
                    LotteryBAnimSetUtil.doStatusAnimBjkl8(mAnimSetStatus, mNextExpectTvState, DensityUtil.dp2px(this, 30));
                    LotteryBAnimSetUtil.doStatusAnimBjkl8(mAnimSetTime, mTimeTextView, DensityUtil.dp2px(this, 30));
                } else {
                    if (TextUtils.isEmpty(mNextExpectTvState.getText().toString())) {
                        LotteryBAnimSetUtil.doStatusAnim(mAnimSetStatus, mNextExpectTvState, DensityUtil.dp2px(this, 60));
                        LotteryBAnimSetUtil.doTimeAnim(mAnimSetTime, mTimeTextView, DensityUtil.dp2px(this, 50));
                    }
                }
                mNextExpectTvState.setText(R.string.not_bet);
                isNotLotteryWait = false;
                mTvXdh.setText("");
            } else {
                // 设置投注截止倒计时
                mCDTimerUtils.cancel();
                mCDTimerUtils.setMillisInFuture((baseHandicap.getLeftTime() + 1) * 1000)
                        .setCountDownInterval(1000)
                        .setTickDelegate(mTickDelegate)
                        .setFinishDelegate(mLeftTimeFinishDelegate)
                        .start();
                if (mLotteryCode.equals(LotteryEnum.BJKL8.getCode())) {
                    LotteryBAnimSetUtil.doStatusAnimBjkl8(mAnimSetStatus, mNextExpectTvState, DensityUtil.dp2px(this, 30));
                    LotteryBAnimSetUtil.doStatusAnimBjkl8(mAnimSetTime, mTimeTextView, DensityUtil.dp2px(this, 30));
                } else {
                    if (TextUtils.isEmpty(mNextExpectTvState.getText().toString())) {
                        LotteryBAnimSetUtil.doStatusAnim(mAnimSetStatus, mNextExpectTvState, DensityUtil.dp2px(this, 60));
                        LotteryBAnimSetUtil.doTimeAnim(mAnimSetTime, mTimeTextView, DensityUtil.dp2px(this, 50));
                    }
                }
                mNextExpectTvState.setText(R.string.stop);
                if (isNotLotteryWait) {
                    mTvXdh.setText("");
                    mOnTheNextToDoListener.openLottery();
                }
                isNotLotteryWait = true;
            }
        }
    }


    private void initHistoryRecycleView() {
        mHistoryAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mRlvLotteryRecord.setAdapter(mHistoryAdapter);
    }

    /**
     * 初始化玩法view
     */
    private void initPlayTypeView() {
        mPlayTypeChooseQuickAdapter = new PlayTypeChooseQuickAdapter(R.layout.choose_play_type_item_layout);
        mPlayTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayTypeRecyclerView.setAdapter(mPlayTypeChooseQuickAdapter);
        mPlayTypeRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
    }

    /**
     * 初始化玩法
     * 初始化默认玩法和默认投注fragment
     * 加载玩法列表数据
     */
    private void initPlayTypeData() {
        mFragmentManager = getSupportFragmentManager();
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                mPlayChooseBeans = Lottery_B_DataUtils.getPlayTypeNameList(mLotteryCode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayTypeChooseQuickAdapter.setNewData(mPlayChooseBeans);
                        mCurrentPlayType = mPlayChooseBeans.get(0).getBetName();
                    }
                });
            }
        });
        mOnTheNextToDoListener.onNextDataInit();
    }


    /**
     * 更新第一个fragment的赔率数据
     */
    private void resfreshOddData() {
        RxBus.get().post(LotteryBFragmentManager.FIRST_FRGMENT, "-1");
    }


    /**
     * 初始化金币选择器
     */
    private void initMoneyChooseView() {
        mBottomChooseMoneyAdapter = new BottomChooseMoneyAdapter(R.layout.layout_item_choose_money);
        mMoneyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mMoneyRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(this, 8)));
        mMoneyRecyclerView.setAdapter(mBottomChooseMoneyAdapter);
        mBottomChooseMoneyAdapter.setNewData(Lottery_B_DataUtils.initBottomChooseMoneyData());
    }


    /**
     * 设置接下来的要做的接口监听器
     */
    protected void setOnTheNextToDoListener(OnTheNextToDoListener onTheNextToDoListener) {
        mOnTheNextToDoListener = onTheNextToDoListener;
    }

    /**
     * 设置开奖完成监听
     */
    protected void setTickDelegate(CountDownTimerUtils.TickDelegate delegate) {
        mTickDelegate = delegate;
    }

    /**
     * 设置距离开奖
     */
    protected void setLeftOpenTimeFinishDelegate(CountDownTimerUtils.FinishDelegate delegate) {
        mLeftOpenTimeFinishDelegate = delegate;
    }

    /**
     * 设置距离封盘
     */
    protected void setLeftTimeFinishDelegate(CountDownTimerUtils.FinishDelegate delegate) {
        mLeftTimeFinishDelegate = delegate;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.hot_and_cold:
                    changeType = 1;
                    mCbOmit.setChecked(false);
                    RxBus.get().post(ConstantValue.EVENT_CHANGE_LR, "-1");
                    break;
                case R.id.cb_omit:
                    changeType = 2;
                    mHotAndCold.setChecked(false);
                    RxBus.get().post(ConstantValue.EVENT_CHANGE_LR, "-1");
                    break;
            }
        }
        if (!mHotAndCold.isChecked() && !mCbOmit.isChecked()) {
            changeType = 0;
            RxBus.get().post(ConstantValue.EVENT_CHANGE_LR, "-1");
        }
    }

    /**
     * 子类彩票处理
     */
    public interface OnTheNextToDoListener {
        void onNextInit();

        void onNextDataInit();

        void onNextResultByCode(List<Handicap> handicapList);

        void onNextRecentRecords(List<HandicapWithOpening> handicapWithOpeningList);

        void openLottery();

        void onNextShowFragment(PlayChooseBean playChooseBean);
    }

    //子fragment 投了某个注
    public void onTouZhuView(PlayDetailBean bean) {
        if (BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap().size() == 0) {
            resetPresenter();
            return;
        }
        if (mPlayDetailBeans.contains(bean)) {
            mPlayDetailBeans.remove(bean);
        } else {
            mPlayDetailBeans.add(bean);
        }
        if (mPlayDetailBeans.size() == 0) {
            offTouZhuView();
        } else {
            mRlTouzhu.setVisibility(View.VISIBLE);
            mPostPlayBeans = Lottery_B_DataUtils.updateTouZhuViewUI(mPlayDetailBeans, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
        }
    }

    /**
     * 重新初始化界面  在服务端赔率数据异常的时候
     */
    private void resetPresenter() {
        SingleToast.showMsg("赔率数据异常，重新拉取中~");
        RxBus.get().post(TOP_FRGMENT, "-1");
        offTouZhuView();
        initPresent();
    }

    //展示玩法说明
    public void showHowPlayDialog(PlayTypeBean.PlayBean playBean) {
        mHowToPlayDialog.setPlayWay(playBean.getPlayTypeExplain());
        mHowToPlayDialog.setLotteryNOteNumber(playBean.getSampleBet());
        mHowToPlayDialog.setSamleBettingProgramTv(playBean.getSingleExplain());
        mHowToPlayDialog.show();
    }


    //返回内容区域的高度
    public int getBottomContentHeight() {
        return lLayout_ssc_bottom_second.getHeight() - DensityUtil.dp2px(this, 60);
    }


    //关闭投注窗口，且清空数据
    public void offTouZhuView() {
        mTvInputNote.setText("");
        mTvInputNote.setHint(getString(R.string.hand_input));
        mTvTzCountMoney.setText(R.string.zs_default);
        mTvTzPrizedMoney.setText(R.string.ys_default);
        mPostPlayBeans.clear();
        mPlayDetailBeans.clear();
        mViewKeyboard.setVisibility(View.GONE);
        mLlTop.setVisibility(View.VISIBLE);
        mRlHead.setVisibility(View.VISIBLE);
        mPresenter.initMemoryButtonUI(mLotteryCode, mIvMemory, mTvInputNote);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRlTouzhu.setVisibility(View.GONE);
            }
        }, 10);
        mIsShowKeyBoard = false;
    }


    private void initListeners() {
        mSlidingLayout.setTouchEnabled(false);
        mRlHead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeSlidingLayOutWithMove();
                return false;
            }
        });
        /**
         *金钱输入框监听
         */
        mTvInputNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsShowKeyBoard) {
                    mViewKeyboard.setVisibility(View.VISIBLE);
                    mRlHead.setVisibility(View.GONE);
                    mLlTop.setVisibility(View.GONE);
                } else {
                    mViewKeyboard.setVisibility(View.GONE);
                    mRlHead.setVisibility(View.VISIBLE);
                    mLlTop.setVisibility(View.VISIBLE);
                }
                mIsShowKeyBoard = !mIsShowKeyBoard;
            }
        });

        /**
         * 键盘监听
         */
        mViewKeyboard.setIOnKeyboardListener(new XNumberKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                StringBuilder old = new StringBuilder(mTvInputNote.getText().toString());
                mTvInputNote.setText(old.append(text));
                mPostPlayBeans = Lottery_B_DataUtils.updateTouZhuViewUI(mPlayDetailBeans, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
                LotteryBMemoryUtil.setMemoryData(mLotteryCode, mTvInputNote);
            }

            @Override
            public void onDeleteKeyEvent() {
                mTvInputNote.setText("");
                mTvInputNote.setHint(getString(R.string.hand_input));
                mPostPlayBeans = Lottery_B_DataUtils.updateTouZhuViewUI(mPlayDetailBeans, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
                LotteryBMemoryUtil.setMemoryData(mLotteryCode, mTvInputNote);
            }

            @Override
            public void onConfirm() {
                mViewKeyboard.setVisibility(View.GONE);
                mRlHead.setVisibility(View.VISIBLE);
                mLlTop.setVisibility(View.VISIBLE);
            }
        });
        /**
         * 时间动画监听
         */
        mAnimSetTime.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                        iv_open_close_ui.setImageResource(R.drawable.ic_expand_more_black_40dp);
                    }
                }, LotteryBAnimSetUtil.DURATION / 2);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mSlidingLayout != null &&
                (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED
                        || mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        } else if (mRlTouzhu.getVisibility() == View.VISIBLE &&
                mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            RxBus.get().post(TOP_FRGMENT, "-1");
            offTouZhuView();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 滑动layout的进出开关
     */
    private void changeSlidingLayOutWithMove() {
        if (mRlTouzhu.getVisibility() == View.VISIBLE &&
                mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            return;
        if (mSlidingLayout != null &&
                (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            iv_open_close_ui.setImageResource(R.drawable.ic_expand_less_black_40dp);
            arcView.setVisibility(View.GONE);
            showResultIV.setVisibility(View.VISIBLE);
        }

        if (mSlidingLayout != null &&
                (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED)) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            iv_open_close_ui.setImageResource(R.drawable.ic_expand_more_black_40dp);
            arcView.setVisibility(View.VISIBLE);
            showResultIV.setVisibility(View.GONE);
        }
    }

    /**
     * 玩法列表
     */
    class PlayTypeChooseQuickAdapter extends BaseQuickAdapter {
        public PlayTypeChooseQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayChooseBean playChooseBean = (PlayChooseBean) item;
            helper.setText(R.id.tv_sscb_palyType, playChooseBean.getBetName());
            if (playChooseBean.isSelected()) {
                helper.itemView.setSelected(true);
                helper.getView(R.id.tv_sscb_palyType).setSelected(true);
            } else {
                helper.itemView.setSelected(false);
                helper.getView(R.id.tv_sscb_palyType).setSelected(false);
            }
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap().size() == 0) {
                        resetPresenter();
                        return;
                    }
                    mCurrentPlayType = playChooseBean.getBetName();
                    Lottery_B_DataUtils.refreshChooseData(mPlayChooseBeans, helper.getAdapterPosition());
                    notifyDataSetChanged();
                    offTouZhuView();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mOnTheNextToDoListener.onNextShowFragment(playChooseBean);
                            mPresenter.initMemoryButtonUI(mLotteryCode, mIvMemory, mTvInputNote);
                        }
                    });

                }
            });
        }
    }


    /**
     * 金币列表
     */
    private class BottomChooseMoneyAdapter extends BaseQuickAdapter {

        public BottomChooseMoneyAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            ChooseMoney chooseMoney = (ChooseMoney) item;
            helper.setImageResource(R.id.iv_money_count, getResId(BaseLotteryBActivity.this, chooseMoney.getDrawableId()));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(mTvInputNote.getText())) {
                        mTvInputNote.setText(chooseMoney.getCount() + "");
                    } else {
                        long oldCount = Long.parseLong(mTvInputNote.getText().toString());
                        mTvInputNote.setText((chooseMoney.getCount() + oldCount) + "");
                    }
                    mPostPlayBeans = Lottery_B_DataUtils.updateTouZhuViewUI(mPlayDetailBeans, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
                    LotteryBMemoryUtil.setMemoryData(mLotteryCode, mTvInputNote);
                }
            });
        }

        public int getResId(Context context, String name) {
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        }
    }

    /**
     * 延时开奖
     */
    private class OpenLotteryRunnable implements Runnable {
        @Override
        public void run() {
            if (!isDestroyed()) {
                mPresenter.getRecentCloseExpect();
            }
        }

    }


    @Override
    protected void onDestroy() {
        mCDTimerUtils.cancel();
        mPresenter.onDestory();
        LotteryBFragmentManager.getInstance().clear();
        mSmallHelper.dissMissPopWindow();
        mHowToPlayDialog.onDestory();
        mHandler.removeCallbacks(mOpenLotteryRunnable);
        mAnimSetPeriods.cancel();
        mAnimSetXh.cancel();
        mAnimSetStatus.cancel();
        mAnimSetTime.cancel();
        mAnimSetPeriodsNext.cancel();
        mAnimationDrawable.stop();
        super.onDestroy();
    }

    @OnClick({R.id.left_btn, R.id.tv_small_helper, R.id.iv_memory
            , R.id.tv_clear, R.id.b_tz, R.id.b_reset, R.id.iv_list_next, R.id.iv_random, R.id.tv_bottom_content_init})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_small_helper:
                mSmallHelper.doTogglePopupWindow(view);
                break;
            case R.id.iv_memory:
                mPresenter.changeMemoryFuction(mLotteryCode, mIvMemory, mTvInputNote);
                break;
            case R.id.tv_clear:
                //清楚金额
                mTvInputNote.setText("");
                mPostPlayBeans = Lottery_B_DataUtils.updateTouZhuViewUI(mPlayDetailBeans, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
                LotteryBMemoryUtil.setMemoryData(mLotteryCode, mTvInputNote);
                break;
            case R.id.b_tz:
                // 投注
                if (mLlFendan.getVisibility() == View.VISIBLE) {
                    SingleToast.showMsg("封单中~");
                    return;
                }
                if (TextUtils.isEmpty(mTvInputNote.getText().toString().trim())) {
                    SingleToast.showMsg(getString(R.string.please_write_money));
                    return;
                }
                if (!DataCenter.getInstance().isLogin()) {
                    SingleToast.showMsg("您还未登录，请先登录");
                    ActivityUtil.startLoginActivity();
                    return;
                }
                if (Lottery_B_DataUtils.checkPostBeans(mCurrentPlayType, mPostPlayBeans)) {
                    Lottery_b_saveBetOrder.initBetParamFormm(mCurrentExpect, mBBetParamForm, mPostPlayBeans, mLotteryCode);
                    double intputMoney = Double.parseDouble(mBBetParamForm.getTotalMoney());
                    double allBalance = 0;
                    if (!DataCenter.getInstance().getUser().getBalance().isEmpty()) {
                        allBalance = Double.parseDouble(DataCenter.getInstance().getUser().getBalance().replaceAll(",", ""));
                    }
                    if (intputMoney > allBalance) {
                        SingleToast.showMsg("金额不足，请调整投注金额");
                        return;
                    }
                    doPlaceOrder();
//                    mPresenter.sureNoteDialogShow(mLotteryName, mCurrentExpect, mBBetParamForm.getBetOrders().size() + "", mBBetParamForm.getTotalMoney());
                }
                break;
            case R.id.b_reset:
                //重置按钮
                RxBus.get().post(TOP_FRGMENT, "-1");
                offTouZhuView();
                break;
            case R.id.iv_list_next:
                if (mChooseMoneyBottm) {
                    mChooseMoneyBottm = false;
                    mMoneyRecyclerView.smoothScrollToPosition(mBottomChooseMoneyAdapter.getItemCount() - 1);
                } else {
                    mMoneyRecyclerView.smoothScrollToPosition(0);
                    mChooseMoneyBottm = true;
                }
                break;
            case R.id.iv_random:
                if (mLlFendan.getVisibility() == View.VISIBLE) {
                    SingleToast.showMsg("封单中~");
                    return;
                }
                mPlayDetailBeans.clear();
                RxBus.get().post(ConstantValue.EVENT_CHANGE_SELECTED, "-1");
                break;
            case R.id.tv_bottom_content_init:
                initPresent();
                break;

        }
    }

    List<BetOrderBean> mBetOrderBeans = new ArrayList<>();

    /**
     * 跳转注单
     */
    private void doPlaceOrder() {
        Log.e("lottery_b_order_json", GsonUtil.GsonString(mBBetParamForm));
        for (BetOrdersListBean betOrdersListBean : mBBetParamForm.getBetOrders()) {
            BetOrderBean betOrderBean = new BetOrderBean();
            betOrderBean.setPlayCode(betOrdersListBean.getPlayCode());
            betOrderBean.setBetCode(betOrdersListBean.getBetCode());
            betOrderBean.setBetNum(betOrdersListBean.getBetNum());
            betOrderBean.setBetCount(1);
            betOrderBean.setBetAmount(betOrdersListBean.getBetAmount());
            betOrderBean.setOdd(betOrdersListBean.getOdd());
            betOrderBean.setMultiple("1");
            betOrderBean.setBonusModel("1");
            betOrderBean.setRebate(0);
            mBetOrderBeans.add(betOrderBean);
        }
        //重置
        RxBus.get().post(TOP_FRGMENT, "-1");
        offTouZhuView();

        Intent intent = new Intent(this, LotteryAShoppingCartActivity.class);
        intent.putExtra(LT_NAME, getIntent().getStringExtra(LT_NAME));
        intent.putExtra(LT_CODE, getIntent().getStringExtra(LT_CODE));
        intent.putExtra(PLAY_MODEL, "tradition");
        intent.putParcelableArrayListExtra(SHOP_DATA, (ArrayList<? extends Parcelable>) mBetOrderBeans);
        startActivity(intent);

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

    public void clearSelected() {
        mPostPlayBeans.clear();
        mPlayDetailBeans.clear();
    }
}
