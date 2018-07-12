package com.dawoo.lotterybox.view.activity.lottery.pk10;

import android.util.Log;
import android.view.View;

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
import com.dawoo.lotterybox.util.LotteryBMemoryUtil;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;
import com.dawoo.lotterybox.view.fragment.pk10_b.PK10_GYH_Fragment;
import com.dawoo.lotterybox.view.fragment.pk10_b.PK10_SM_Fragment;
import com.dawoo.lotterybox.view.fragment.pk10_b.PK10_SZP_Fragment;
import com.dawoo.lotterybox.view.view.CountDownTimerUtils;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.dawoo.lotterybox.view.view.XNumberKeyboardView;
import com.dawoo.lotterybox.view.view.sortcar.SortCarSurfaceView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * Created by archar on 18-3-27.
 */

public class PK10BActivity extends BaseLotteryBActivity implements LotteryBView, BaseLotteryBActivity.OnTheNextToDoListener {

    private SortCarSurfaceView mSortCarView;

    @Override
    protected void createLayoutView() {
        super.createLayoutView();
        setContentView(R.layout.activity_pk10_b);
        RxBus.get().register(this);
        // 当前彩种代码
        mLotteryCode = getIntent().getStringExtra(LT_CODE);
        mLotteryName = getIntent().getStringExtra(LT_NAME);
        mSortCarView = findViewById(R.id.carView);
        mHistoryAdapter = new AwardResultsQuickAdapter(R.layout.item_pk10_lottery_result);
        setOnTheNextToDoListener(this);
    }


    @Override
    public void onNextInit() {
        OPEN_DELATED = 10000;
        setTickDelegate(mTickDelegate);
        setLeftOpenTimeFinishDelegate(mLeftOpenTimeFinishDelegate);
        setLeftTimeFinishDelegate(mLeftTimeFinishDelegate);
        mTvInputNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsShowKeyBoard) {
                    mViewKeyboard.setVisibility(View.VISIBLE);
                    mRlHead.setVisibility(View.GONE);
                    mLlTop.setVisibility(View.GONE);
                    mSortCarView.setVisibility(View.GONE);
                } else {
                    mViewKeyboard.setVisibility(View.GONE);
                    mRlHead.setVisibility(View.VISIBLE);
                    mLlTop.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSortCarView.setVisibility(View.VISIBLE);
                        }
                    }, 200);
                }
                mIsShowKeyBoard = !mIsShowKeyBoard;
            }
        });

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
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSortCarView.setVisibility(View.VISIBLE);
                    }
                }, 200);

            }
        });
    }

    @Override
    public void onNextDataInit() {
        LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, PK10_SZP_Fragment.class);
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
        mSortCarView.setRandPlay();
    }

    public void offTouZhuView() {
        mTvInputNote.setText("");
        mTvInputNote.setHint(getString(R.string.hand_input));
        mTvTzCountMoney.setText(R.string.zs_default);
        mTvTzPrizedMoney.setText(R.string.ys_default);
        mPostPlayBeans.clear();
        mPlayDetailBeans.clear();
        mViewKeyboard.setVisibility(View.GONE);
        mLlTop.setVisibility(View.VISIBLE);
        mPresenter.initMemoryButtonUI(mLotteryCode, mIvMemory, mTvInputNote);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSortCarView.setVisibility(View.VISIBLE);
            }
        }, 200);
        mRlHead.setVisibility(View.VISIBLE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRlTouzhu.setVisibility(View.GONE);
            }
        }, 10);
        mIsShowKeyBoard = false;
    }


    @Override
    public void onNextShowFragment(PlayChooseBean playChooseBean) {
        String name = playChooseBean.getBetName();
        if (name.equals(mPlayChooseBeans.get(0).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, PK10_SZP_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(1).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, PK10_SM_Fragment.class, mLl_anim_dog);
        } else if (name.equals(mPlayChooseBeans.get(2).getBetName())) {
            LotteryBFragmentManager.getInstance().switchFragment(mFragmentManager, PK10_GYH_Fragment.class, mLl_anim_dog);
        }
    }


    public void setResultToBall(String string) {
        String[] numbers = string.split(",");
        mTvXdh.setText(Lottery_B_DataUtils.getPK10StarStatus(numbers));
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            nums.add(Integer.valueOf(numbers[i]));
        }
        mSortCarView.setResultAndPlay(nums);
    }

    //距离开盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftOpenTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            openLottery();
            mPresenter.getLotteryExpect();
        }
    };
    //倒计时期间回调
    CountDownTimerUtils.TickDelegate mTickDelegate = new CountDownTimerUtils.TickDelegate() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm:ss");

        @Override
        public void onTick(long pMillisUntilFinished) {
            Log.e("lyn onTick", pMillisUntilFinished + "");
            String str1 = sdf1.format(pMillisUntilFinished);
            mTimeTextView.setText(str1);
        }
    };


    //距离封盘时间 倒计时结束回调
    CountDownTimerUtils.FinishDelegate mLeftTimeFinishDelegate = new CountDownTimerUtils.FinishDelegate() {
        @Override
        public void onFinish() {
            mTimeTextView.setText("00:00");
            mLlFendan.setVisibility(View.VISIBLE);
            mSortCarView.setFenDanUI();
            mTvXdh.setText("");
            mPresenter.getRecentCloseExpect();
            mPresenter.getLotteryExpect();
        }
    };


    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String[] nums = cqsscAwardResultBean.getOpenCode().split(",");
            helper.setText(R.id.tv_no, getString(R.string.which_periods_, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_award_results, cqsscAwardResultBean.getOpenCode().replace(",", " "));
            StringBuffer strNum = new StringBuffer();
            int num = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]);
            strNum.append(num + ",");
            strNum.append((num % 2) == 0 ? getString(R.string.double_) + "," : getString(R.string.single) + ",");
            strNum.append(num < 12 ? getString(R.string.small) + "," : getString(R.string.big) + ",");
            strNum.append(Integer.valueOf(nums[0]) > Integer.valueOf(nums[9]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[1]) > Integer.valueOf(nums[8]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[2]) > Integer.valueOf(nums[7]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[3]) > Integer.valueOf(nums[6]) ? getString(R.string.dragon) + "," : getString(R.string.tiger) + ",");
            strNum.append(Integer.valueOf(nums[4]) > Integer.valueOf(nums[5]) ? getString(R.string.dragon) : getString(R.string.tiger));
            helper.setText(R.id.tv_status, strNum);
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
        mSortCarView.destroyAll();
        RxBus.get().unregister(this);
        super.onDestroy();
    }
}
