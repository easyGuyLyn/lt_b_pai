package com.dawoo.lotterybox.bean;


/**
 * Created by jack on 18-2-18.
 */

public interface DateResources {
    /**
     *账单明细的title数据
     */
    String[] tabtitles = new String[]{"全部", "取款", "存款", "游戏","优惠"};

    /**
     * 充值记录的title数据
     */
    String[] recharagerecord = new String[]{"存款","取款"};
    /**
     * 充值的数据
     */
    String[] moneyType = {"100元", "200元", "300元", "500元", "1000元", "5000元", "10000元", "50000元"};
}
