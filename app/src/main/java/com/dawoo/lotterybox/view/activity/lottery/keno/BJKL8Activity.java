package com.dawoo.lotterybox.view.activity.lottery.keno;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.OrderEventBean;
import com.dawoo.lotterybox.bean.lottery.BaseHandicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_Other_Fragment;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_X1_Fragment;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_X2_Fragment;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_X3_Fragment;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_X4_Fragment;
import com.dawoo.lotterybox.view.fragment.keno.BJKL8_X5_Fragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.dawoo.lotterybox.view.view.NOMoveStaggeredGridLayoutManager;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * Created by archar on 18-3-26.
 */

public class BJKL8Activity extends BaseLotteryBActivity implements LotteryBView, BaseLotteryBActivity.OnTheNextToDoListener {

    private RecyclerView mRv_numList;
    private LastResultsQuickAdapter mLastResultsQuickAdapter;//上面开奖结果适配器
    private List<String> mFendanListData = new ArrayList<>();//封单数据源
    private List<String> mAnimListData = new ArrayList<>();//anim

    @Override
    protected void createLayoutView() {
        super.createLayoutView();
        setContentView(R.layout.activity_bjkl8);
        RxBus.get().register(this);
        // 当前彩种代码
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryName = getIntent().getStringExtra(LT_NAME);
        mRv_numList = findViewById(R.id.rv_numList);
        mHistoryAdapter = new AwardResultsQuickAdapter(R.layout.item_bjkl8_lottery_result);
        setOnTheNextToDoListener(this);
    }

    @Override
    public void onNextInit() {
        OPEN_DELATED = 20000;
        mLastResultsQuickAdapter = new LastResultsQuickAdapter(R.layout.layout_item_last_result_num);
        mRv_numList.setLayoutManager(new NOMoveStaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL));
        mRv_numList.setAdapter(mLastResultsQuickAdapter);
        setTickDelegate(mTickDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        for (int i = 0; i < 20; i++) {
            mFendanListData.add("a");
            mAnimListData.add("b");
        }
    }

    @Override
    public void onNextDataInit() {
        LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X5_Fragment.class);
    }

    @Override
    public void onNextResultByCode(List<Handicap> handicapList) {
        setResultToBall(handicapList.get(0).getOpenCode());
    }

    @Override
    public void onNextRecentRecords(List<HandicapWithOpening> handicapWithOpeningList) {

    }

    @Override
    public void openLottery() {
        mLlFendan.setVisibility(View.GONE);
        doRandomUIChange();
    }

    @Override
    public void onNextShowFragment(PlayChooseBean playChooseBean) {
        String name = playChooseBean.getBetName();
        if (name.equals(mPlayChooseBeans.get(0).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X5_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(1).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X4_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(2).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X3_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(3).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X2_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(4).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_X1_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(5).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, BJKL8_Other_Fragment.class, mLl_anim_dog);
        }
    }

    private void doRandomUIChange() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLastResultsQuickAdapter.setNewData(mAnimListData);
            }
        }, 1200);
    }


    public int getImageResourceFormNumber(String number) {
        int imageResource = 0;
        if ("0".equals(number))
            imageResource = R.mipmap.item0;
        else if ("1".equals(number))
            imageResource = R.mipmap.item1;
        else if ("2".equals(number))
            imageResource = R.mipmap.item2;
        else if ("3".equals(number))
            imageResource = R.mipmap.item3;
        else if ("4".equals(number))
            imageResource = R.mipmap.item4;
        else if ("5".equals(number))
            imageResource = R.mipmap.item5;
        else if ("6".equals(number))
            imageResource = R.mipmap.item6;
        else if ("7".equals(number))
            imageResource = R.mipmap.item7;
        else if ("8".equals(number))
            imageResource = R.mipmap.item8;
        else if ("9".equals(number))
            imageResource = R.mipmap.item9;

        return imageResource;
    }

    public void setResultToBall(String string) {
        String[] numbers = string.split(",");
        if (numbers == null || numbers.length == 0) return;
        mLastResultsQuickAdapter.setNewData(Arrays.asList(numbers));
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
    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            openLottery();
            mPresenter.getLotteryExpect();
        }
    };
    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            mLlFendan.setVisibility(View.VISIBLE);
            mLastResultsQuickAdapter.setNewData(mFendanListData);
            mTvXdh.setText("");
            mPresenter.getRecentCloseExpect();
            mPresenter.getLotteryExpect();
        }
    };


    class LastResultsQuickAdapter extends BaseQuickAdapter {
        public LastResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            String num = (String) item;
            String numTen = num.substring(0, 1);
            String numOne = null;
            if (num.length() == 2) {
                numOne = num.substring(1, 2);
            }
            ImageView iv_num_ten = helper.getView(R.id.iv_num_ten);
            ImageView iv_num_one = helper.getView(R.id.iv_num_one);
            ImageView iv_ask = helper.getView(R.id.iv_ask);
            if (numTen.equals("a")) {
                iv_num_ten.setVisibility(View.GONE);
                iv_num_one.setVisibility(View.GONE);
                iv_ask.setVisibility(View.VISIBLE);
            } else if (numTen.equals("b")) {
                iv_num_ten.setVisibility(View.VISIBLE);
                iv_num_one.setVisibility(View.VISIBLE);
                iv_ask.setVisibility(View.GONE);
                iv_num_ten.setImageResource(R.drawable.anim_bjkl8_result);
                iv_num_one.setImageResource(R.drawable.anim_bjkl8_result1);
                ((AnimationDrawable) iv_num_ten.getDrawable()).start();
                ((AnimationDrawable) iv_num_one.getDrawable()).start();
            } else {
                iv_num_ten.setVisibility(View.VISIBLE);
                iv_num_one.setVisibility(View.VISIBLE);
                iv_ask.setVisibility(View.GONE);
                Glide.with(BJKL8Activity.this).load(getImageResourceFormNumber(numTen)).into(iv_num_ten);
                Glide.with(BJKL8Activity.this).load(getImageResourceFormNumber(numOne)).into(iv_num_one);
            }
        }
    }


    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String openCode = cqsscAwardResultBean.getOpenCode();
            helper.setText(R.id.tv_no, getString(R.string.which_periods, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, cqsscAwardResultBean.getOpenCode().replace(",", " "));
            helper.setText(R.id.tv_award_status, Lottery_B_DataUtils.getBjkl8RewardStatus(openCode));

            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_s));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1));
            }
        }
    }


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_LOTTERY_CART_DATA_CHANGE)})
    public void orderDataChange(OrderEventBean orderEventBean) {
        super.orderDataChange(orderEventBean);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }
}
