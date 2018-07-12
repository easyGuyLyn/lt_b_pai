package com.dawoo.lotterybox.bean;

/**
 * Created by b on 18-4-19.
 * 用于数据变动传递通知更改
 */

public class OrderEventBean {

    private int changeDataType;     //购物车对BetParamBean集合数据进行操作类型  -1已下单，全部删除  1000删除  2000为修改倍数  3000为添加  千后面的零头代表index   如：1001，第一条数据删除了
    private BetOrderBean mBetOrderBean;

    public int getChangeDataType() {
        return changeDataType;
    }

    public void setChangeDataType(int changeDataType) {
        this.changeDataType = changeDataType;
    }

    public BetOrderBean getBetOrderBean() {
        return mBetOrderBean;
    }

    public void setBetOrderBean(BetOrderBean betOrderBean) {
        mBetOrderBean = betOrderBean;
    }
}
