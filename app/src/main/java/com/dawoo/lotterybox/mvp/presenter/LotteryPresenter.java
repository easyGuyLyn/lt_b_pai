package com.dawoo.lotterybox.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BBetParamForm;
import com.dawoo.lotterybox.bean.BetOrderABean;
import com.dawoo.lotterybox.bean.BetOrderBean;
import com.dawoo.lotterybox.bean.BetParamBean;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.SaveOrderResult;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.mvp.view.IBaseLotteryView;
import com.dawoo.lotterybox.mvp.view.IBaseView;
import com.dawoo.lotterybox.mvp.view.ILotteryAView;
import com.dawoo.lotterybox.mvp.view.LotteryBView;
import com.dawoo.lotterybox.net.rx.ProgressSubscriber;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.GsonUtil;
import com.dawoo.lotterybox.util.SharePreferenceUtil;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Subscription;

/**
 * 彩票相关的presenter
 * Created by b on 18-2-22.
 */

public class LotteryPresenter<T extends IBaseView> extends BaseLotteryPresenter {


    private final Context mContext;
    private T mView;

    public LotteryPresenter(Context context, T mView, String code) {
        super(context, mView);

        mContext = context;
        this.mView = mView;
        mCode = code;
    }


    /**
     * A下单
     */
    public void saveBetOrderA(BetParamBean betParamBean) {
        List<BetOrderBean> betOrderBeans = new ArrayList<>();
        BetOrderBean betOrderBean = new BetOrderBean();
        betOrderBean.setPlayCode(betParamBean.getPlayCode());
        betOrderBean.setBetCode(betParamBean.getBetCode());
        betOrderBean.setBetCount(betParamBean.getBetCount());
        betOrderBean.setBetAmount(betParamBean.getBetAmount());
        betOrderBean.setBetNum(betParamBean.getBetNum());
        betOrderBean.setOdd(betParamBean.getLotteryOddBeans().get(betParamBean.getMaxOddPosition()).getOdd() + "");
        betOrderBean.setMultiple(betParamBean.getMultiple());
        betOrderBean.setBonusModel(betParamBean.getBonusModel());
        betOrderBean.setRebate(betParamBean.getNowRebate() == -1 ? betParamBean.getLotteryOddBeans().get(betParamBean.getMaxOddPosition()).getRebate() : betParamBean.getNowRebate());
        betOrderBeans.add(betOrderBean);

        BetOrderABean betOrderABean = new BetOrderABean();
        betOrderABean.setCode(betParamBean.getCode());
        betOrderABean.setExpect(betParamBean.getExpect());
        betOrderABean.setTotalMoney(betParamBean.getBetAmount());
        betOrderABean.setQuantity(betParamBean.getQuantity());
        betOrderABean.setPlayModel(betParamBean.getPlayModel());
        betOrderABean.setBetOrders(betOrderBeans);
        String betForm = new Gson().toJson(betOrderABean);


        Subscription subscription = mModel.saveBetOrder(new ProgressSubscriber<>(o ->
                        ((IBaseLotteryView) mView).onSaveBetOrder((SaveOrderResult) o), mContext),
                betParamBean.getToken(),
                betForm
        );
        subList.add(subscription);
    }

    /**
     * 下单
     */
    public void saveBetOrderB(String token, BBetParamForm bBetParamForm) {
        Subscription subscription = mModel.saveBetOrder(new ProgressSubscriber<>(o ->
                        ((LotteryBView) mView).onSaveBetOrder((SaveOrderResult) o), mContext),
                token,
                GsonUtil.GsonString(bBetParamForm));

        subList.add(subscription);
    }

    /**
     * 机选
     */
    public void computerSelect(PlayTypeBean.PlayBean mPlayTypeBean) {
        Random random = new Random();
        List<Integer> selects = new ArrayList<>();
        int pos = random.nextInt(mPlayTypeBean.getLayoutBeans().size());
        for (int i = 0; i < mPlayTypeBean.getLayoutBeans().size(); i++) {
            List<Integer> randoms = new ArrayList<>();
            if (mPlayTypeBean.isRelation() || pos == i)
                for (int h = 0; h < mPlayTypeBean.getLayoutBeans().get(i).getSelectMin(); h++) {
                    if (mPlayTypeBean.getLayoutBeans().get(i).isSelectEqual()) {
                        int position = random.nextInt(mPlayTypeBean.getLayoutBeans().get(i).getChildItemCount());
                        randoms.add(position);
                    } else {
                        boolean isEqual = true;
                        while (isEqual) {
                            int position = random.nextInt(mPlayTypeBean.getLayoutBeans().get(i).getChildItemCount());
                            if (!selects.contains(position)) {
                                selects.add(position);
                                randoms.add(position);
                                isEqual = false;
                            }
                        }
                    }

                }
            for (int j = 0; j < mPlayTypeBean.getLayoutBeans().get(i).getChildItemCount(); j++) {
                if (randoms.contains(j))
                    mPlayTypeBean.getLayoutBeans().get(i).getChildLayoutBeans().get(j).setSelected(true);
                else
                    mPlayTypeBean.getLayoutBeans().get(i).getChildLayoutBeans().get(j).setSelected(false);
            }
            ((ILotteryAView) mView).onComputerSelect(i + 1);
        }
    }


    /**
     * 初始化layoutChild参数
     */
    public void initLayoutChildBean(PlayTypeBean.PlayBean mPlayTypeBean) {
        for (int i = 0; i < mPlayTypeBean.getLayoutBeans().size(); i++) {
            if (mPlayTypeBean.getLayoutBeans().get(i).getChildLayoutBeans() == null || mPlayTypeBean.getLayoutBeans().get(i).getChildLayoutBeans().size() == 0) {  //初始化子布局子item数据
                List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> mChildLayoutBeans = new ArrayList<>();
                for (int j = 0; j < mPlayTypeBean.getLayoutBeans().get(i).getChildItemCount(); j++) {
                    PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
                    childLayoutBean.setNumber(String.valueOf(j + mPlayTypeBean.getLayoutBeans().get(i).getStartNumber()));
                    mChildLayoutBeans.add(childLayoutBean);
                }
                mPlayTypeBean.getLayoutBeans().get(i).setChildLayoutBeans(mChildLayoutBeans);
            }
        }
    }

    /**
     * 清空选择
     */
    public void clearSelect(PlayTypeBean.PlayBean mPlayBean) {
        List<PlayTypeBean.PlayBean.LayoutBean> layoutBeans = mPlayBean.getLayoutBeans();
        for (PlayTypeBean.PlayBean.LayoutBean layoutBean : layoutBeans) {
            for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : layoutBean.getChildLayoutBeans()) {
                childLayoutBean.setSelected(false); //清理之前的数据
            }
        }
    }

    /**
     * 当前选择是否构成注单
     */
    public void isBetBtEnable(PlayTypeBean.PlayBean mPlayTypeBean) {
        boolean isEnable = false;
        List<PlayTypeBean.PlayBean.LayoutBean> layoutBeans = mPlayTypeBean.getLayoutBeans();
        if (layoutBeans.get(0).getLayoutType() == 0) {
            List<List<String>> listNums = new ArrayList<>();
            List<Integer> selects = new ArrayList<>();
            for (PlayTypeBean.PlayBean.LayoutBean layoutBean : layoutBeans) {
                if (layoutBean.getLayoutType() == 0) {
                    int count = 0;
                    List<String> listNum = new ArrayList<>();
                    for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : layoutBean.getChildLayoutBeans()) {
                        if (childLayoutBean.isSelected()) {
                            count += 1;
                            listNum.add(childLayoutBean.getNumber());
                        }
                    }
                    listNums.add(listNum);
                    if (count >= layoutBean.getSelectMin())
                        isEnable = true;
                    else {
                        if (mPlayTypeBean.isRelation())
                            isEnable = false;
                    }
                    selects.add(count);
                }
                if (mPlayTypeBean.isRelation() && !isEnable) {
                    break;
                }
            }
            if (isEnable) {
                if (layoutBeans.size() == 1)
                    computBetInfo(true, listNums, mPlayTypeBean);
                else
                    computBetInfo(false, listNums, mPlayTypeBean);
                mosaicNum(listNums, mPlayTypeBean);
            }
            ((ILotteryAView) mView).onBetBtEnable(isEnable);
        }
    }

    /**
     * 拼接已选择参数
     */
    private void mosaicNum(List<List<String>> listNums, PlayTypeBean.PlayBean mPlayTypeBean) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < listNums.size(); i++) {
            List<String> listNum = listNums.get(i);
            for (int j = 0; j < listNum.size(); j++) {
                if (listNums.size() == 1 && !TextUtils.isEmpty(mPlayTypeBean.getLayoutBeans().get(0).getSplitCharacter())) {
                    stringBuffer.append(j == (listNum.size() - 1) ? listNum.get(j) : listNum.get(j) + mPlayTypeBean.getLayoutBeans().get(0).getSplitCharacter());
                } else
                    stringBuffer.append(j == (listNum.size() - 1) ? listNum.get(j) : listNum.get(j) + ",");
            }
            if (i != listNums.size() - 1)
                stringBuffer.append("|");
        }
        ((ILotteryAView) mView).onMosaicNum(stringBuffer.toString());
    }


    /**
     * 计算已选择构成总注数
     */
    private void computBetInfo(Boolean isSinge, List<List<String>> selects, PlayTypeBean.PlayBean mPlayTypeBean) {
        if (selects.size() == 0)
            return;
        int bets = 0;  //总注数
        if (isSinge) {
            List<String> selectNums = selects.get(0);
            if ("前二".equals(mPlayTypeBean.getMainType()) && "直选和值".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    if (select < 10) {
                        bets += (select + 1);
                    } else {
                        bets += (19 - select);
                    }
                }
            } else if ("前二".equals(mPlayTypeBean.getMainType()) && "组选和值".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    if (select < 10) {
                        if (select % 2 == 1) {
                            select += 1;
                        }
                        bets += select / 2;
                    } else {
                        bets += 4;
                        if (select % 2 == 1) {
                            select -= 1;
                        }
                        bets -= ((select - 10) / 2);
                    }
                }
            } else if ("前二".equals(mPlayTypeBean.getMainType()) && "直选跨度".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    if (select == 0) {
                        bets += 10;
                    } else {
                        bets += (20 - (select * 2));
                    }
                }
            } else if ("前二".equals(mPlayTypeBean.getMainType()) && "组选包胆".equals(mPlayTypeBean.getPlayTypeName())) {
                bets += 9;
            } else if ("组选包胆".equals(mPlayTypeBean.getPlayTypeName())) {
                bets += 54;
            } else if ("直选和值".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    if (select < 10) {
                        for (int j = 0; j < select + 1; j++) {
                            bets += (j + 1);
                        }
                    } else if (select < 14) {
                        bets += 55;
                        for (int j = 0; j < select - 9; j++) {
                            bets += (8 - (j * 2));
                        }
                    } else if (select < 19) {
                        bets += 75;
                        for (int j = 0; j < select - 13; j++) {
                            bets -= (j * 2);
                        }
                    } else {
                        bets += 55;
                        for (int j = 0; j < select - 18; j++) {
                            bets -= (10 - j);
                        }
                    }
                }
            } else if ("组选和值".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    bets += computeGroupSelectAndValue(select);
                }
            } else if ("直选跨度".equals(mPlayTypeBean.getPlayTypeName())) {
                for (int i = 0; i < selectNums.size(); i++) {
                    int select = Integer.valueOf(selectNums.get(i));
                    if (select == 0) {
                        bets += 10;
                    } else {
                        if (select < 6) {
                            bets += 54;
                            for (int j = 0; j < select - 1; j++) {
                                bets += (42 - (j * 12));
                            }
                        } else {
                            bets += 150;
                            for (int j = 0; j < select - 5; j++) {
                                bets -= (6 + (j * 12));
                            }
                        }
                    }
                }
            } else {
                int divisor = 0;
                int divisor2 = 0;
                int num1 = 0;
                int num2 = 0;
                for (int i = 0; i < mPlayTypeBean.getLayoutBeans().get(0).getSelectMin(); i++) {
                    if (i == 0) {
                        divisor = selects.get(0).size();
                        divisor2 = mPlayTypeBean.getLayoutBeans().get(0).getSelectMin();
                        num1 = divisor;
                        num2 = divisor2;
                    } else {
                        num1 *= (divisor - i);
                        num2 *= (divisor2 - i);
                    }
                }
                if ("组三复式".equals(mPlayTypeBean.getPlayTypeName())) {
                    bets = num1 / num2 * 2;
                } else
                    bets = num1 / num2;
            }
        } else {
            if (mPlayTypeBean.getPlayTypeName().endsWith("组合")) {
                for (int i = 0; i < selects.size(); i++) {
                    if (i == 0)
                        bets = selects.get(i).size();
                    else {
                        bets *= selects.get(i).size();
                    }
                }
                bets *= 3;
            } else {
                for (int i = 0; i < selects.size(); i++) {
                    if (i == 0)
                        bets = selects.get(i).size();
                    else {
                        if (mPlayTypeBean.isRelation())
                            bets *= selects.get(i).size();
                        else bets += selects.get(i).size();
                    }
                }
            }
        }
        ((ILotteryAView) mView).onComputBetInfo(bets);
    }

    /**
     * 组选和值
     */
    private int computeGroupSelectAndValue(int number) {
        int andValue = 0;
        if (number < 14) {
            if (number < 8) {
                switch (number) {
                    case 1:
                        andValue = 1;
                        break;
                    case 2:
                        andValue = 2;
                        break;
                    case 3:
                        andValue = 2;
                        break;
                    case 4:
                        andValue = 4;
                        break;
                    case 5:
                        andValue = 5;
                        break;
                    case 6:
                        andValue = 6;
                        break;
                    case 7:
                        andValue = 8;
                        break;
                }
            } else {
                switch (number) {
                    case 8:
                        andValue = 10;
                        break;
                    case 9:
                        andValue = 11;
                        break;
                    case 10:
                        andValue = 13;
                        break;
                    case 11:
                        andValue = 14;
                        break;
                    case 12:
                        andValue = 14;
                        break;
                    case 13:
                        andValue = 15;
                        break;
                }
            }
        } else {
            if (number < 20) {
                switch (number) {
                    case 14:
                        andValue = 15;
                        break;
                    case 15:
                        andValue = 14;
                        break;
                    case 16:
                        andValue = 14;
                        break;
                    case 17:
                        andValue = 13;
                        break;
                    case 18:
                        andValue = 11;
                        break;
                    case 19:
                        andValue = 10;
                        break;
                }
            } else {
                switch (number) {
                    case 20:
                        andValue = 8;
                        break;
                    case 21:
                        andValue = 6;
                        break;
                    case 22:
                        andValue = 5;
                        break;
                    case 23:
                        andValue = 4;
                        break;
                    case 24:
                        andValue = 2;
                        break;
                    case 25:
                        andValue = 2;
                        break;
                    case 26:
                        andValue = 1;
                        break;
                }
            }
        }
        return andValue;
    }

    /**
     * 计算拼接当前选择构成的注单详细信息
     * <p>
     * 奖金= 最大奖金（odd）- 当前读条比例*基数；如定位胆odd=19.6;baseNum=20;拖动到0.046时候，奖金显示为19.6-0.046*20=18.68；
     * 返回金额 = 下注金额*返点比例；
     */
    DecimalFormat mDecimalFormat = new DecimalFormat("############.###");

    @SuppressLint("StringFormatMatches")
    public void setShowBetInfo(int bets, BetParamBean mBetParamBean) {
        if (bets == 0) {
            return;
        }
        LotteryOddBean lotteryOddBean = null;
        int maxOddPosition = 0;
        if (mBetParamBean.getLotteryOddBeans() == null)
            return;
        for (int i = 0; i < mBetParamBean.getLotteryOddBeans().size(); i++) {
            if (i == 0)
                lotteryOddBean = mBetParamBean.getLotteryOddBeans().get(0);
            else if (lotteryOddBean.getOdd() < mBetParamBean.getLotteryOddBeans().get(i).getOdd()) {
                lotteryOddBean = mBetParamBean.getLotteryOddBeans().get(i);
                maxOddPosition = i;
            }
        }
        mBetParamBean.setMaxOddPosition(maxOddPosition);
        double maxWinning;  //单注可中奖金币
        if (mBetParamBean.getNowRebate() == -1)
            maxWinning = (lotteryOddBean.getOdd() - lotteryOddBean.getBaseNum() * lotteryOddBean.getRebate());
        else
            maxWinning = (lotteryOddBean.getOdd() - lotteryOddBean.getBaseNum() * mBetParamBean.getNowRebate());
        double allBetGold = 0;  //总投注金币
        if ("1".equals(mBetParamBean.getBonusModel())) {
            allBetGold = bets * 1;
        } else if ("10".equals(mBetParamBean.getBonusModel())) {
            allBetGold = bets * 0.1;
            maxWinning = maxWinning * 0.1;
        } else if ("100".equals(mBetParamBean.getBonusModel())) {
            allBetGold = bets * 0.01;
            maxWinning = maxWinning * 0.01;
        }
        allBetGold *= mBetParamBean.getModel();
        long multiple = Long.valueOf(mBetParamBean.getMultiple());
        allBetGold *= multiple;
        maxWinning *= multiple;
        double backGold;                            //返点金币
        if (mBetParamBean.getNowRebate() != -1) {
            backGold = allBetGold * mBetParamBean.getNowRebate();
        } else {
            backGold = allBetGold * lotteryOddBean.getRebate();
        }

        StringBuffer stringBuffer = new StringBuffer();
        Object[] str1 = {bets + "", BalanceUtils.getScalsBalance(maxWinning)};
        stringBuffer.append(mContext.getString(R.string.bet_info_1, str1));
        stringBuffer.append(mContext.getString(R.string.bet_info_2, BalanceUtils.getScalsBalance(allBetGold)));
        if (mBetParamBean.getLotteryOddBeans().get(0).getRebate() != 0)
            stringBuffer.append(mContext.getString(R.string.bet_info_3, BalanceUtils.getScalsBalance(backGold)));
        mBetParamBean.setBetAmount(mDecimalFormat.format(allBetGold));
        mBetParamBean.setBetCount(bets);
        ((ILotteryAView) mView).onShowBetInfo(stringBuffer.toString());
    }


    /**
     * 初始化金币记忆
     */
    public void initMemoryButtonUI(String mLotteryCode, ImageView mIvMemory, TextView mTvInputNote) {
        if (mLotteryCode == null || mIvMemory == null || mTvInputNote == null) {
            return;
        }
        if (SharePreferenceUtil.getMemoryFunction(mContext, mLotteryCode).equals("-1")) {
            mIvMemory.setImageResource(R.mipmap.memery_off);
        } else {
            mIvMemory.setImageResource(R.mipmap.memery_on);
            mTvInputNote.setText(SharePreferenceUtil.getMemoryFunction(mContext, mLotteryCode));
        }
    }

    /**
     * 开关金币记忆
     */
    public void changeMemoryFuction(String mLotteryCode, ImageView mIvMemory, TextView mTvInputNote) {
        if (mLotteryCode == null || mIvMemory == null || mTvInputNote == null) {
            return;
        }
        if (SharePreferenceUtil.getMemoryFunction(mContext, mLotteryCode).equals("-1")) {
            mIvMemory.setImageResource(R.mipmap.memery_on);
            SharePreferenceUtil.putMemoryFunction(mContext, mTvInputNote.getText().toString(), mLotteryCode);
        } else {
            mIvMemory.setImageResource(R.mipmap.memery_off);
            SharePreferenceUtil.putMemoryFunction(mContext, "-1", mLotteryCode);
        }
    }


    @Override
    public void onDestory() {
        super.onDestory();
    }
}
