package com.dawoo.lotterybox.view.activity.lottery.lhc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lhc.LHCAwardAdapter;
import com.dawoo.lotterybox.adapter.lhc.LHCParentPlayAdapter;
import com.dawoo.lotterybox.bean.BBetParamForm;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.ChooseMoney;

import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.KeyMap;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.playtype.ParentPlayTypeBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.mvp.presenter.LotteryBPresenter;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.Lottery_b_saveBetOrder;
import com.dawoo.lotterybox.util.SharePreferenceUtil;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Animal_Add_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Animal_Even_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Animal_One_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Animal_last_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Color_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Com_Match_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Mantissa_Even_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_TM_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_Un_Match_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_ZMT_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_ZM_1_6_Fragment;
import com.dawoo.lotterybox.view.fragment.lhc.LHC_ZM_Fragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.SpaceItemDecoration;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.dawoo.lotterybox.view.view.TimeTextView;
import com.dawoo.lotterybox.view.view.XNumberKeyboardView;
import com.dawoo.lotterybox.view.view.bottomsheet.SlidingUpPanelLayout;
import com.dawoo.lotterybox.view.view.dialog.HowToPlayDialog;
import com.dawoo.lotterybox.view.view.popu.SmallHelperPopuWindow;
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
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.PLAY_MODEL;
import static com.dawoo.lotterybox.view.activity.lottery.LotteryAShoppingCartActivity.SHOP_DATA;

/**
 * Created by rain on 18-3-6.
 */

public class HKSMActivity extends BaseActivity implements LotteryBView, CompoundButton.OnCheckedChangeListener {
    final String openTag = "0";
    final String closeTag = "1";
    long randomclickTime;
    final int postTime = 50;
    @BindView(R.id.iv_go_back)
    ImageView mIvGoBack;
    @BindView(R.id.tv_activity_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right_menu)
    TextView mTvRightMenu;
    @BindView(R.id.sm_code_0)
    TextView smCode0;
    @BindView(R.id.sm_code_1)
    TextView smCode1;
    @BindView(R.id.sm_code_2)
    TextView smCode2;
    @BindView(R.id.sm_code_3)
    TextView smCode3;
    @BindView(R.id.sm_code_4)
    TextView smCode4;
    @BindView(R.id.sm_code_5)
    TextView smCode5;
    @BindView(R.id.sm_code_6)
    TextView smCode6;
    @BindView(R.id.sm_code_name_0)
    TextView smCodeName0;
    @BindView(R.id.sm_code_name_1)
    TextView smCodeName1;
    @BindView(R.id.sm_code_name_2)
    TextView smCodeName2;
    @BindView(R.id.sm_code_name_3)
    TextView smCodeName3;
    @BindView(R.id.sm_code_name_4)
    TextView smCodeName4;
    @BindView(R.id.sm_code_name_5)
    TextView smCodeName5;
    @BindView(R.id.sm_code_name_6)
    TextView smCodeName6;
    //虚线
    @BindView(R.id.sm_drashes_0)
    View drash0;
    @BindView(R.id.sm_drashes_1)
    View drash1;
    @BindView(R.id.tv_periods)
    TextView smLastStageTV;
    @BindView(R.id.tv_now_stage)
    TextView smNowSatgeTV;
    @BindView(R.id.sm_timer)
    TimeTextView smTimerView;
    @BindView(R.id.tv_now_stage_state)
    TextView nowStageStatuTV;

    @BindView(R.id.rlv_lottery_record)
    RecyclerView mRlvLotteryRecord;
    @BindView(R.id.rlv_game_parentTitle)
    RecyclerView rlv_game_parentTitle;
    @BindView(R.id.lLayout_ssc_bottom_second)
    RelativeLayout mLLayoutSscBottomSecond;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.hot_and_cold)
    CheckBox mHotAndCold;
    @BindView(R.id.cb_omit)
    CheckBox mCbOmit;
    @BindView(R.id.fragment_content)
    FrameLayout mFragmentContent;
    @BindView(R.id.rl_head)
    RelativeLayout mRlHead;
    @BindView(R.id.iv_random)
    ImageView mIvRandom;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mSlidingLayout;

    //投注Lyaout（Views）
    @BindView(R.id.rl_touzhu)
    LinearLayout mRlTouzhu;
    @BindView(R.id.tv_tz_count_money)
    TextView mTvTzCountMoney;
    @BindView(R.id.tv_tz_prizedMoney)
    TextView mTvTzPrizedMoney;
    @BindView(R.id.tv_clear)
    TextView mTvClear;
    @BindView(R.id.b_tz)
    Button mBTz;
    @BindView(R.id.b_reset)
    Button mBReset;
    @BindView(R.id.rv_tz_choose)
    RecyclerView mRvTzChoose;
    @BindView(R.id.rl_bottom_content)
    RelativeLayout mRlBottomContent;
    @BindView(R.id.iv_memory)
    ImageView mIvMemory;
    @BindView(R.id.view_keyboard)
    XNumberKeyboardView mViewKeyboard;
    @BindView(R.id.tv_input_note)
    TextView mTvInputNote;
    @BindView(R.id.tv_bottom_content_init)
    Button reInitBt;
    @BindView(R.id.iv_open_close_ui)
    ImageView iv_open_close_ui;
    @BindView(R.id.top_ll)
    LinearLayout mLlTop;
    @BindView(R.id.iv_anim_dog)
    ImageView mIv_anim_dog;
    @BindView(R.id.ll_anim_dog)
    LinearLayout mLl_anim_dog;

    private String name, code;
    private SmallHelperPopuWindow mPopupWindow;
    private LotteryBPresenter mCQSSCPrecenter;
    private CountDownTimerUtils mCountDownTimerUtils;
    private LHCAwardAdapter mAwardAdapter;
    private LHCParentPlayAdapter mParentPlayAdapter;
    private FragmentManager mFragmentManager;
    private List<PlayDetailBean> mSelectedPlays = new ArrayList<>();
    private boolean mIsShowKeyBoard;//是否展示着数字软键盘
    private boolean mChooseMoneyBottm;//选择金钱滑动控制
    private Handler mHandler = new Handler();
    private List<BetOrdersListBean> postPlayBeanList = new ArrayList<>();
    private String parentType = "";
    private BottomChooseMoneyAdapter mBottomChooseMoneyAdapter;//选择金钱适配器
    private HowToPlayDialog explainDialog;
    private BBetParamForm mBetParamForm = new BBetParamForm();//提交参数对象
    private List<Handicap> mHandicaps = new ArrayList<>();
    private String token;
    private boolean isOpening;

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    private BaseHandicap postHandicap;
    private AnimationDrawable mAnimationDrawable;//fragment切换之间的填充动画

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_hk_sm);
        name = getIntent().getStringExtra(ConstantValue.LT_NAME);
        code = getIntent().getStringExtra(ConstantValue.LT_CODE);
        String model = getIntent().getStringExtra(ConstantValue.LT_MODLE);
        if (code == null) {
            ToastUtil.showToastShort(this, "六合彩代码初始化错误");
            finish();
            return;
        }
        if (model != null && !model.isEmpty()) {
            mBetParamForm.setPlayModel(model);
        }
        mBetParamForm.setCode(code);
        explainDialog = new HowToPlayDialog(this);
        RxBus.get().register(this);
        LHCDataUtils.hot = 0;
    }


    @Override
    protected void initViews() {
        reInitBt.setVisibility(View.GONE);
        mPopupWindow = new SmallHelperPopuWindow(this, code);
        drash0.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        drash1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mFragmentManager = getSupportFragmentManager();
        mTvTitle.setText(name);
        mAwardAdapter = new LHCAwardAdapter(R.layout.item_lhc_lottery_result);
        mAwardAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        mAwardAdapter.setEnableLoadMore(false);
        mRlvLotteryRecord.setAdapter(mAwardAdapter);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(this));
        mParentPlayAdapter = new LHCParentPlayAdapter(R.layout.layout_parent_play_type_item, null);
        rlv_game_parentTitle.setAdapter(mParentPlayAdapter);
        rlv_game_parentTitle.setLayoutManager(new LinearLayoutManager(this));
        rlv_game_parentTitle.getParent().requestDisallowInterceptTouchEvent(true);
        mCountDownTimerUtils = CountDownTimerUtils.getCountDownTimer();
        mBottomChooseMoneyAdapter = new BottomChooseMoneyAdapter(R.layout.layout_item_choose_money);
        mRvTzChoose.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvTzChoose.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(this, 8)));
        mRvTzChoose.setAdapter(mBottomChooseMoneyAdapter);
        mSlidingLayout.setTouchEnabled(false);
        initListeners();

        if (DataCenter.getInstance().isLogin()) {
            mTvBalance.setText(DataCenter.getInstance().getUser().getBalance());
        } else {
            mTvBalance.setText("0.00");
        }
        mFragmentContent.setBackgroundResource(R.color.sm_bg);
        mLl_anim_dog.setBackgroundResource(R.color.sm_bg);
        mIv_anim_dog.setImageResource(R.drawable.anim_refresh_head_dog_eat_short);
        mAnimationDrawable = (AnimationDrawable) mIv_anim_dog.getDrawable();
        mAnimationDrawable.start();
    }

    void initListeners() {
        mParentPlayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            long clickTime = 0;

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (((ParentPlayTypeBean) adapter.getItem(position)).isChoose()) {
                    return;
                }
                if (System.currentTimeMillis() - clickTime < 500) {
                    return;
                }
                clickTime = System.currentTimeMillis();
                LHCDataUtils.refreshChooseData(adapter.getData(), position);
                adapter.notifyDataSetChanged();
                showFragment(position);
                clearSelected();
            }
        });

        mRlHead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeSlidingLayOutWithMove();
                return false;
            }
        });
        mTvInputNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mIsShowKeyBoard) {
                    mViewKeyboard.setVisibility(View.VISIBLE);
                    mLlTop.setVisibility(View.GONE);
                } else {
                    mViewKeyboard.setVisibility(View.GONE);
                    mLlTop.setVisibility(View.VISIBLE);
                }
                mIsShowKeyBoard = !mIsShowKeyBoard;
                return false;
            }
        });

        mViewKeyboard.setIOnKeyboardListener(new XNumberKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                StringBuilder old = new StringBuilder(mTvInputNote.getText().toString());
                mTvInputNote.setText(old.append(text));
                showTouZhuView();
            }

            @Override
            public void onDeleteKeyEvent() {
                mTvInputNote.setText("");
                mTvInputNote.setHint(getString(R.string.hand_input));
                showTouZhuView();
            }

            @Override
            public void onConfirm() {
                mViewKeyboard.setVisibility(View.GONE);
            }
        });
        mHotAndCold.setOnCheckedChangeListener(this);
        mCbOmit.setOnCheckedChangeListener(this);
        mTvInputNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mIvMemory.getTag().toString().equalsIgnoreCase(openTag)) {
                    SharePreferenceUtil.putMemoryFunction(HKSMActivity.this, mTvInputNote.getText().toString().trim(), code);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mBottomChooseMoneyAdapter.setNewData(LHCUtil.initBottomChooseMoneyData());
        mCQSSCPrecenter = new LotteryBPresenter(this, this, code);
        mCQSSCPrecenter.getRecentRecords();
        mCQSSCPrecenter.getResultByCode();
        mCQSSCPrecenter.getLotteryExpect(false);
        mCQSSCPrecenter.getLtToken();
        mCQSSCPrecenter.getLotteryOdd("");
        mParentPlayAdapter.addData(LHCDataUtils.initChooseData());
    }

    @OnClick({R.id.iv_go_back, R.id.tv_right_menu, R.id.iv_memory
            , R.id.tv_clear, R.id.b_tz, R.id.b_reset, R.id.iv_list_next, R.id.iv_random, R.id.tv_bottom_content_init})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;

            case R.id.tv_right_menu:
                if (mPopupWindow.isShowing())
                    mPopupWindow.dismiss();
                else
                    mPopupWindow.doTogglePopupWindow(view);
                break;
            case R.id.tv_clear:
                mTvInputNote.setText("");
                LHCDataUtils.changeUIList(HKSMActivity.this, parentType, mSelectedPlays, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
                break;
            case R.id.b_tz:
                if (TextUtils.isEmpty(mTvInputNote.getText())) {
                    SingleToast.showMsg(getString(R.string.please_write_money));
                    return;
                }

                int endIndex = mTvTzCountMoney.getText().toString().indexOf("注");
                String countNum = mTvTzCountMoney.getText().toString().substring(1, endIndex);
                if (countNum.length() > 3) {
                    SingleToast.showMsg("投注数太多，请重新选择");
                    return;
                }
                if (postHandicap == null) {
                    SingleToast.showMsg("获取盘口信息中，请稍后");
                    mCQSSCPrecenter.getLotteryExpect();
                    return;
                }

                if (!DataCenter.getInstance().isLogin()) {
                    SingleToast.showMsg("您还未登录，请先登录");
                    ActivityUtil.startLoginActivity();
                    return;
                }
                postPlayBeanList = LHCDataUtils.changeUIGetPostList(this, parentType, mSelectedPlays, mTvInputNote);
                Lottery_b_saveBetOrder.initBetParamForm(mBetParamForm, postPlayBeanList);
                double intputMoney = Double.parseDouble(mBetParamForm.getTotalMoney());
                double allBalance = 0;
                if (!DataCenter.getInstance().getUser().getBalance().isEmpty()) {
                    allBalance = BalanceUtils.getDouble(DataCenter.getInstance().getUser().getBalance());
                }
                if (intputMoney > allBalance) {
                    SingleToast.showMsg("金额不足，请调整投注金额");
                    return;
                }
                doPlaceOrder();
                break;
            case R.id.iv_memory:
                changeMemoryFuction();
                break;
            case R.id.b_reset:
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_SELECTED).putExtra("isRandom", false));
                clearSelected();
                break;
            case R.id.iv_random:
                if (System.currentTimeMillis() - randomclickTime < 300) {
                    return;
                }
                randomclickTime = System.currentTimeMillis();
                clearSelectedData();
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_SELECTED).putExtra("isRandom", true));
                break;
            case R.id.iv_list_next:
                if (mChooseMoneyBottm) {
                    mChooseMoneyBottm = false;
                    mRvTzChoose.smoothScrollToPosition(mBottomChooseMoneyAdapter.getItemCount() - 1);
                } else {
                    mRvTzChoose.smoothScrollToPosition(0);
                    mChooseMoneyBottm = true;
                }
                break;
            case R.id.tv_bottom_content_init:
                if (mHandicaps.isEmpty()) {
                    mCQSSCPrecenter.getResultByCode();
                }
                if (postHandicap == null) {
                    mCQSSCPrecenter.getLotteryExpect();
                }
                if (token == null || token.isEmpty()) {
                    mCQSSCPrecenter.getLtToken();
                }
                if (LHCDataUtils.getmAllLotteryOdds().isEmpty()) {
                    mCQSSCPrecenter.getLotteryOdd("");
                }
                break;
        }

    }

    List<BetOrderBean> mBetOrderBeans = new ArrayList<>();

    //跳转注单
    private void doPlaceOrder() {
        for (BetOrdersListBean betOrdersListBean : mBetParamForm.getBetOrders()) {
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

        Intent intent = new Intent(this, LotteryAShoppingCartActivity.class);
        intent.putExtra(LT_NAME, getIntent().getStringExtra(LT_NAME));
        intent.putExtra(LT_CODE, getIntent().getStringExtra(LT_CODE));
        intent.putExtra(PLAY_MODEL, "tradition");
        intent.putParcelableArrayListExtra(SHOP_DATA, (ArrayList<? extends Parcelable>) mBetOrderBeans);
        startActivity(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_SELECTED).putExtra("isRandom", false));
        clearSelected();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE)})
    public void orderDataChange(OrderEventBean orderEventBean) {
        int type = orderEventBean.getChangeDataType();
        if (type == -1) {
            mBetOrderBeans.clear();
        } else if (type >= 3000) {
            mBetOrderBeans.add(0, orderEventBean.getBetOrderBean());
        } else if (type >= 2000) {
            mBetOrderBeans.get(type % 2000).setMultiple(orderEventBean.getBetOrderBean().getMultiple());
            mBetOrderBeans.get(type % 2000).setBetAmount(orderEventBean.getBetOrderBean().getBetAmount());
        } else if (type >= 1000) {
            mBetOrderBeans.remove(type % 1000);
        }
    }


    @Override
    public void onResultByCode(List<Handicap> awardResultBeans) {
        if (awardResultBeans != null) {
            mAwardAdapter.addData(awardResultBeans);
            mHandicaps = awardResultBeans;
            LHCDataUtils.setmHandicaps(mHandicaps);
            mPopupWindow.setHandicaps(mHandicaps);
        }
    }

    @Override
    public void onOneResultByCode(List<Handicap> handicapList) {

    }

    @Override
    public void onRecentCloseExpect(Handicap handicap) {

    }

    @Override
    public void onRecentRecords(List<HandicapWithOpening> awardResultBeans) {
        if (awardResultBeans == null || awardResultBeans.isEmpty()) {
            isOpening = true;
            changeOpenAnimation();
            smLastStageTV.setText("开奖中");
            return;
        }
        smLastStageTV.setText(getString(R.string.which_periods, awardResultBeans.get(0).getExpect()));
        if (mCountDownTimerUtils != null) {
            mCountDownTimerUtils.cancel();
        }
        if (!awardResultBeans.get(0).getOpenCode().isEmpty()) {
            isOpening = false;
            if (mThead.isAlive()) {
                mThead.interrupt();
            }
            String[] openCodes = awardResultBeans.get(0).getOpenCode().split(",");
            LHCUtil.initBall(smCode0, smCodeName0, openCodes[0]);
            LHCUtil.initBall(smCode1, smCodeName1, openCodes[1]);
            LHCUtil.initBall(smCode2, smCodeName2, openCodes[2]);
            LHCUtil.initBall(smCode3, smCodeName3, openCodes[3]);
            LHCUtil.initBall(smCode4, smCodeName4, openCodes[4]);
            LHCUtil.initBall(smCode5, smCodeName5, openCodes[5]);
            LHCUtil.initBall(smCode6, smCodeName6, openCodes[6]);
        } else {
            smLastStageTV.setText(getString(R.string.which_periods, awardResultBeans.get(0).getExpect()) + "～开奖中...");
            isOpening = true;
            changeOpenAnimation();
        }
    }

    /**
     * 盘口数据
     *
     * @param expectDataBean
     */
    @Override
    public void onLotteryExpect(BaseHandicap expectDataBean) {
        if (expectDataBean == null) {
            mCQSSCPrecenter.getLotteryExpect();
            return;
        }
        if (expectDataBean.getLeftOpenTime() == 0 && expectDataBean.getLeftTime() == 0) {
            mCQSSCPrecenter.getLotteryExpect();
            return;
        }
        postHandicap = expectDataBean;
        smNowSatgeTV.setText(getString(R.string.which_periods, expectDataBean.getExpect()));
        if (mCountDownTimerUtils != null) {
            mCountDownTimerUtils.cancel();
        }
        mBetParamForm.setExpect(expectDataBean.getExpect());
        if (expectDataBean.isOpening() || expectDataBean.getLeftTime() <= 0) {
            mCountDownTimerUtils.setMillisInFuture(expectDataBean.getLeftOpenTime() * 1000)
                    .setFinishDelegate(mLeftTimeFinishDelegate)
                    .setTickDelegate(mTickDelegate)
                    .setCountDownInterval(1000)
                    .start();
            nowStageStatuTV.setText("封单中");
        } else {
            mCountDownTimerUtils.setMillisInFuture(expectDataBean.getLeftTime() * 1000)
                    .setFinishDelegate(mLeftOpenTimeFinishDelegate)
                    .setTickDelegate(mTickDelegate)
                    .setCountDownInterval(1000)
                    .start();
            nowStageStatuTV.setText("截止");
        }
    }

    @Override
    public void onLotteryOdd(Map<String, LotteryOddBean> o) {
        if (o.values().isEmpty()) {
            ToastUtil.showToastShort(this, "数据获取失败，请检查您的网络是否异常");
            return;
        } else {
            LHCDataUtils.setmAllLotteryOdds(o.values());
            LotteryBFragmentManager.getInstance().setFirstFrgment(mFragmentManager, LHC_TM_Fragment.class);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        },200);
    }

    @Override
    public void onLtToken(String token) {
        if (token != null) {
            this.token = token;
        }
    }

    @Override
    public void onSaveBetOrder(SaveOrderResult submitOrderResultBean) {
        if (submitOrderResultBean != null) {
            token = submitOrderResultBean.getExtend();
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_SELECTED).putExtra("isRandom", false));
            clearSelected();
            SingleToast.showMsg(submitOrderResultBean.getMessage() == null ? "注单成功" : submitOrderResultBean.getMessage());
            mIvMemory.callOnClick();
            mTvBalance.setText(submitOrderResultBean.getData().getBalance() + "");
            DataCenter.getInstance().getUser().setBalance(submitOrderResultBean.getData().getBalance());

        }
    }

    @Override
    public void onSureBetOrder() {
        mCQSSCPrecenter.saveBetOrderB(token, mBetParamForm);
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
            if (hour < 10) {
                buffer.append("0" + hour + ":");
            } else {
                buffer.append(hour + ":");
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
            smTimerView.setText(buffer);
        }
    };

    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            smTimerView.setText("00:00:00");
            mCQSSCPrecenter.getLotteryExpect();
            mCQSSCPrecenter.getRecentRecords();
        }
    };

    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            smTimerView.setText("00:00:00");
            mCQSSCPrecenter.getLotteryExpect();
            mCQSSCPrecenter.getRecentRecords();
        }
    };

    private void showFragment(int position) {
        switch (position) {
            case 0:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_TM_Fragment.class, mLl_anim_dog);
                break;
            case 1:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_ZM_Fragment.class, mLl_anim_dog);
                break;
            case 2:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_ZMT_Fragment.class, mLl_anim_dog);
                break;
            case 3:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_ZM_1_6_Fragment.class, mLl_anim_dog);
                break;
            case 4:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Color_Fragment.class, mLl_anim_dog);
                break;
            case 5:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Animal_One_Fragment.class, mLl_anim_dog);
                break;
            case 6:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Animal_last_Fragment.class, mLl_anim_dog);
                break;
            case 7:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Com_Match_Fragment.class, mLl_anim_dog);
                break;
            case 8:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Animal_Add_Fragment.class, mLl_anim_dog);
                break;
            case 9:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Animal_Even_Fragment.class, mLl_anim_dog);
                break;
            case 10:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Mantissa_Even_Fragment.class, mLl_anim_dog);
                break;
            case 11:
                LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, LHC_Un_Match_Fragment.class, mLl_anim_dog);
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.hot_and_cold:
                    LHCDataUtils.hot = 1;
                    mCbOmit.setChecked(false);
                    break;
                case R.id.cb_omit:
                    LHCDataUtils.hot = 2;
                    mHotAndCold.setChecked(false);
                default:
                    break;
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_LR));
        }
        if (!mCbOmit.isChecked() && !mHotAndCold.isChecked()) {
            LHCDataUtils.hot = 0;
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ConstantValue.EVENT_CHANGE_LR));
        }
    }

    private class BottomChooseMoneyAdapter extends BaseQuickAdapter {

        public BottomChooseMoneyAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            ChooseMoney chooseMoney = (ChooseMoney) item;
            helper.setImageResource(R.id.iv_money_count, getResId(getApplication(), chooseMoney.getDrawableId()));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(mTvInputNote.getText())) {
                        mTvInputNote.setText(chooseMoney.getCount() + "");
                    } else {
                        int oldCount = Integer.parseInt(mTvInputNote.getText().toString());
                        if (oldCount == chooseMoney.getCount()) {
                            return;
                        }
                        mTvInputNote.setText((chooseMoney.getCount()) + "");
                    }

                    LHCDataUtils.changeUIList(HKSMActivity.this, parentType, mSelectedPlays, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);

                }
            });
        }
    }

    public static int getResId(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }


    /**
     * 清空选中信息
     */
    public void clearSelected() {
        if (mSelectedPlays.size() > 0) {
            mSelectedPlays.clear();
            offTouZhuView();
        }
    }

    /**
     * 清空选中信息
     */
    public void clearSelectedData() {
        if (mSelectedPlays.size() > 0) {
            mSelectedPlays.clear();
        }
    }

    public void addSelected(PlayDetailBean bean, int minSelected) {
        mSelectedPlays.add(bean);
        if (mSelectedPlays.size() >= minSelected) {
            showTouZhuView();
        }
    }

    /**
     * 玩法选中事件的调用
     *
     * @param bean
     * @param minSelected
     */
    public void setSelectedItem(PlayDetailBean bean, int minSelected) {
        if (mSelectedPlays.isEmpty()) {
            mSelectedPlays.add(bean);
        } else {
            boolean isRemove = false;
            for (PlayDetailBean item : mSelectedPlays) {
                if (item.getNum().equalsIgnoreCase(bean.getNum()) && item.getChildType().equalsIgnoreCase(bean.getChildType()) &&
                        item.getBetCode().equalsIgnoreCase(bean.getBetCode())) {
                    mSelectedPlays.remove(item);
                    isRemove = true;
                    break;
                }
            }
            if (!isRemove) {
                mSelectedPlays.add(bean);
            }
        }
        if (mSelectedPlays.size() >= minSelected) {
            showTouZhuView();
        } else {
            offTouZhuView();
        }

    }

    /**
     * 显示投注页面，刷新投注汇总信息，生成投注数据
     */
    public void showTouZhuView() {
        if (mRlTouzhu.getVisibility() != View.VISIBLE) {
            mRlTouzhu.setVisibility(View.VISIBLE);
        }
        LHCDataUtils.changeUIList(HKSMActivity.this, parentType, mSelectedPlays, mTvTzCountMoney, mTvTzPrizedMoney, mTvInputNote);
    }

    /**
     * 关闭投注窗口，当选中数据为0时清空数据
     */
    public void offTouZhuView() {
        if (mSelectedPlays.size() == 0) {
            if (mIvMemory.getTag().equals(closeTag)) {
                mTvInputNote.setText("");
            }
            mTvTzCountMoney.setText(R.string.zs_default);
            mTvTzPrizedMoney.setText(R.string.ys_default);
            mSelectedPlays.clear();
        }
        if (mRlTouzhu.getVisibility() == View.VISIBLE) {
            mViewKeyboard.setVisibility(View.GONE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRlTouzhu.setVisibility(View.GONE);
                    if (mLlTop.getVisibility() == View.GONE) {
                        mLlTop.setVisibility(View.VISIBLE);
                    }
                }
            }, 10);
        }
        mIsShowKeyBoard = false;
    }

    private void changeSlidingLayOutWithMove() {
        if (mSlidingLayout != null &&
                (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            iv_open_close_ui.setImageResource(R.drawable.ic_expand_less_black_40dp);
        }

        if (mSlidingLayout != null &&
                (mSlidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED)) {
            mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            iv_open_close_ui.setImageResource(R.drawable.ic_expand_more_black_40dp);
        }

    }

    /**
     * 玩法展示
     *
     * @param explain
     */
    public void showExplainDialog(KeyMap.PlayExplainBean explain) {
        explainDialog.setPlayWay(explain.getExplain());
        explainDialog.setLotteryNOteNumber(explain.getSimple());
        explainDialog.setPlayProgram(explain.getScheme());
        explainDialog.show();
    }

    @Override
    public void finish() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            return;
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        if (explainDialog != null) {
            explainDialog.onDestory();
        }
        if (mThead != null && isOpening) {
            isOpening = false;
            mThead.interrupt();
        }
        mCountDownTimerUtils.cancel();
        mCQSSCPrecenter.onDestory();
        LHCDataUtils.listMaps.clear();
        RxBus.get().unregister(this);
        LotteryBFragmentManager.getInstance().clear();
        mAnimationDrawable.stop();
        super.onDestroy();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        LogUtils.d(s);
        //  收起刷新
        if (mHandicaps.isEmpty()) {
            reInitBt.setVisibility(View.VISIBLE);
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
        } else {
            mTvBalance.setText("0.00");
        }
    }

    void changeOpenAnimation() {
        if (mThead.isInterrupted()) {
            mThead.start();
            return;
        }
        mThead.start();
    }

    Thread mThead = new Thread(new Runnable() {
        @Override
        public void run() {

            while (isOpening && !mThead.isInterrupted()) {
                synchronized (smCode0) {
                    int[] openCodes = LHCUtil.getRandomList(7, 1, 49);
                    String[] codes = new String[7];
                    for (int i = 0; i < openCodes.length; i++) {
                        if (openCodes[i] < 10) {
                            codes[i] = "0" + openCodes[i];
                        } else {
                            codes[i] = "" + openCodes[i];
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LHCUtil.initBall(smCode0, smCodeName0, codes[0]);
                            LHCUtil.initBall(smCode1, smCodeName1, codes[1]);
                            LHCUtil.initBall(smCode2, smCodeName2, codes[2]);
                            LHCUtil.initBall(smCode3, smCodeName3, codes[3]);
                            LHCUtil.initBall(smCode4, smCodeName4, codes[4]);
                            LHCUtil.initBall(smCode5, smCodeName5, codes[5]);
                            LHCUtil.initBall(smCode6, smCodeName6, codes[6]);
                        }
                    });
                    try {
                        Thread.sleep(800);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    @Override
    protected void onPause() {
        super.onPause();
        if (isOpening) {
            mThead.interrupt();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOpening && mThead.isInterrupted()) {
            mThead.start();
        }
    }

    /**
     * 开关金币记忆
     */
    public void changeMemoryFuction() {
        String tag = (String) mIvMemory.getTag();
        if (tag == closeTag) {
            mIvMemory.setImageResource(R.mipmap.memery_on);
            if (mTvInputNote.getText().toString().isEmpty()) {
                String money = SharePreferenceUtil.getMemoryFunction(this, code);
                if (money.equalsIgnoreCase("-1")) {
                    return;
                } else {
                    mTvInputNote.setText(money);
                }
            } else {
                SharePreferenceUtil.putMemoryFunction(this, mTvInputNote.getText().toString(), code);
            }
            mIvMemory.setTag(openTag);
        } else {
            mIvMemory.setTag(closeTag);
            mIvMemory.setImageResource(R.mipmap.memery_off);
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewKeyboard.getVisibility() == View.VISIBLE) {
            mViewKeyboard.setVisibility(View.GONE);
            mLlTop.setVisibility(View.VISIBLE);
            return;
        } else if (mRlTouzhu.getVisibility() == View.VISIBLE) {
            mRlTouzhu.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();

    }
}
