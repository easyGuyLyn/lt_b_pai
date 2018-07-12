package com.dawoo.lotterybox.util;

import com.dawoo.lotterybox.bean.BBetParamForm;
import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.playtype.PostPlayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archar on 18-3-12.
 * 数据中介
 */

public class Lottery_b_saveBetOrder {

    public static BBetParamForm initBetParamFormm(String expect, BBetParamForm bBetParamForm, List<PostPlayBean> postPlayBeans, String lotteryCode) {
        List<BetOrdersListBean> betOrders = new ArrayList<>();
        Double totalMoney = 0.00;
        for (PostPlayBean postPlayBean : postPlayBeans) {
            BetOrdersListBean betOrdersListBean = new BetOrdersListBean();
            betOrdersListBean.setBetAmount(postPlayBean.getMoney());
            betOrdersListBean.setBetCode(postPlayBean.getBetCode());
            betOrdersListBean.setBetNum(postPlayBean.getPostNum());
            betOrdersListBean.setPlayCode(postPlayBean.getPlayCode());
            betOrdersListBean.setOdd(postPlayBean.getOdd());
            totalMoney += Double.parseDouble(postPlayBean.getMoney());
            betOrders.add(betOrdersListBean);
        }
        bBetParamForm.setBetOrders(betOrders);
        bBetParamForm.setCode(lotteryCode);
        bBetParamForm.setQuantity(postPlayBeans.size() + "");
        bBetParamForm.setTotalMoney(totalMoney + "");
        bBetParamForm.setExpect(expect);
        return bBetParamForm;
    }

    public static void initBetParamForm(BBetParamForm bBetParamForm, List<BetOrdersListBean> postPlayBeans) {
        Double countMoney = 0.00;
        for (BetOrdersListBean bean : postPlayBeans) {
            countMoney += Double.parseDouble(bean.getBetAmount());
        }
        bBetParamForm.setQuantity(postPlayBeans.size() + "");
        bBetParamForm.setBetOrders(postPlayBeans);
        bBetParamForm.setTotalMoney(String.valueOf(countMoney));
    }
}
