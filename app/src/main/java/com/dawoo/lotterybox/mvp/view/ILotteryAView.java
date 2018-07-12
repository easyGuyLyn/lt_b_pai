package com.dawoo.lotterybox.mvp.view;

/**
 * Created by b on 18-2-22.
 */

public interface ILotteryAView extends IBaseLotteryView{
/*    void onAwardResults(List<CQSSCAwardResultBean> awardResultBeans);

    void onAwardResultsAndNoOpen(List<AwardResultBean> awardResultBeans);

    void onExpectData(ExpectDataBean expectDataBean);

    void onLotteryOdd(Map<String,LotteryOddBean> o);

    void onLtToken(String token);

    void onSubmitBetOrder(SubmitOrderResultBean submitOrderResultBean);*/

    /**
     * 机选
     * @param position
     */
    void onComputerSelect(int position);

    void onBetBtEnable(boolean isBtEnable);

    void onMosaicNum(String buffer);

    void onComputBetInfo(int bets);

    void onShowBetInfo(String buffer);
}
