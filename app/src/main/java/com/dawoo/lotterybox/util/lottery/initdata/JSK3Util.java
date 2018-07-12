package com.dawoo.lotterybox.util.lottery.initdata;

import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-3-11.
 */

public class JSK3Util {

    List<PlayTypeBean> mPlayTypeBeans = new ArrayList<>();

    public void initData(){
        initSTHTX();
        initSTHDX();
        initETHFX();
        initETHDX();
        initSBTH();
        initEBTH();
        initSLHTX();
        String gsonString = GsonUtil.GsonString(mPlayTypeBeans);
    }

    private void initSTHTX() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("三同号通选");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("三同号通选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("三同号通选");
        playBean1.setScheme("三同号通选");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_same_num");
        playBean1.setBetCode("k3_tongxuan_santong");

        playBean1.setSampleBet("对所有相同的三个号码（111、222、333、444、555、666）进行投注。");
        playBean1.setSampleOpen("三位号码全相同即中奖。");
        playBean1.setPlayTypeExplain("三位号码全相同即中奖。");
        playBean1.setSingleExplain("全包豹子(111,222,333,444,555,666)投注，开出任意豹子即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("三同号");
        layoutBean1.setShowRightMenuType(2);
        layoutBean1.setChildItemCount(1);

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("通选");
        childLayoutBeans.add(childLayoutBean);
        layoutBean1.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initSTHDX() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("三同号单选");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("三同号单选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("三同号单选");
        playBean1.setScheme("三同号单选");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_same_num");
        playBean1.setBetCode("k3_danxuan_santong");

        playBean1.setSampleBet("对相同的三个号码（111、222、333、444、555、666）中的任意一个进行投注。");
        playBean1.setSampleOpen("所选号码开出即中奖");
        playBean1.setPlayTypeExplain("所选号码开出即中奖。");
        playBean1.setSingleExplain("单选1个豹子(111,222,333,444,555,666)投注，选号与开奖号码一致即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("三同号");
        layoutBean1.setShowRightMenuType(1);
        layoutBean1.setSplitCharacter("|");
        layoutBean1.setChildItemCount(6);

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("111");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean2.setNumber("222");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean3 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean3.setNumber("333");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean4 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean4.setNumber("444");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean5 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean5.setNumber("555");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean6 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean6.setNumber("666");
        childLayoutBeans.add(childLayoutBean);
        childLayoutBeans.add(childLayoutBean2);
        childLayoutBeans.add(childLayoutBean3);
        childLayoutBeans.add(childLayoutBean4);
        childLayoutBeans.add(childLayoutBean5);
        childLayoutBeans.add(childLayoutBean6);
        layoutBean1.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);

    }

    private void initETHFX() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("二同号复选");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("二同号复选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("二同号复选");
        playBean1.setScheme("二同号复选");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_same_num");
        playBean1.setBetCode("k3_fuxuan_ertong");

        playBean1.setSampleBet("从11～66中任选1个或多个号码。");
        playBean1.setSampleOpen("开奖号码中包含选择的对子即中奖");
        playBean1.setPlayTypeExplain("中奖号码(不是豹子号)中包括所选的号码即为中奖。如买的是11,则中奖号码为112,则中奖。");
        playBean1.setSingleExplain("选择对子(11,22,33,44,55,66)投注，开奖号码中包含选择的对子即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("二同号");
        layoutBean1.setShowRightMenuType(1);
        layoutBean1.setSplitCharacter("|");
        layoutBean1.setChildItemCount(6);

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("11");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean2.setNumber("22");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean3 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean3.setNumber("33");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean4 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean4.setNumber("44");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean5 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean5.setNumber("55");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean6 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean6.setNumber("66");
        childLayoutBeans.add(childLayoutBean);
        childLayoutBeans.add(childLayoutBean2);
        childLayoutBeans.add(childLayoutBean3);
        childLayoutBeans.add(childLayoutBean4);
        childLayoutBeans.add(childLayoutBean5);
        childLayoutBeans.add(childLayoutBean6);
        layoutBean1.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initETHDX() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("二同号单选");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("二同号单选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("二同号单选");
        playBean1.setScheme("二同号单选");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_same_num");
        playBean1.setBetCode("k3_danxuan_ertong");

        playBean1.setSampleBet("选择1对相同号码（11 , 22 , 33 , 44 , 55 , 66）和1个不同号码(1 , 2 , 3 , 4 , 5 , 6)投注。");
        playBean1.setSampleOpen("选号与开奖号码一致即中奖。");
        playBean1.setPlayTypeExplain("选号与奖号相同（顺序不限）。");
        playBean1.setSingleExplain("选择1个对子和1个不同号码投注，选号与开奖号码一致即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("二同号");
        layoutBean1.setShowRightMenuType(2);
        layoutBean1.setSelectEqual(false);
        layoutBean1.setChildItemCount(6);

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("11");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean2.setNumber("22");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean3 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean3.setNumber("33");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean4 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean4.setNumber("44");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean5 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean5.setNumber("55");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean6 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean6.setNumber("66");
        childLayoutBeans.add(childLayoutBean);
        childLayoutBeans.add(childLayoutBean2);
        childLayoutBeans.add(childLayoutBean3);
        childLayoutBeans.add(childLayoutBean4);
        childLayoutBeans.add(childLayoutBean5);
        childLayoutBeans.add(childLayoutBean6);
        layoutBean1.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("二不同");
        layoutBean2.setShowRightMenuType(2);
        layoutBean2.setSelectEqual(false);
        layoutBean2.setChildItemCount(6);

//        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans2 = new ArrayList<>();
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean11 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean11.setNumber("1");
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean22 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean22.setNumber("2");
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean33 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean33.setNumber("3");
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean44 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean44.setNumber("4");
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean55 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean55.setNumber("5");
//        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean66 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
//        childLayoutBean66.setNumber("6");
//        childLayoutBeans.add(childLayoutBean11);
//        childLayoutBeans.add(childLayoutBean22);
//        childLayoutBeans.add(childLayoutBean33);
//        childLayoutBeans.add(childLayoutBean44);
//        childLayoutBeans.add(childLayoutBean55);
//        childLayoutBeans.add(childLayoutBean66);
//        layoutBean2.setChildLayoutBeans(childLayoutBeans2);
        mLayoutBeans.add(layoutBean2);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initSBTH() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("三不同号");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("三不同号");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("三不同号");
        playBean1.setScheme("三不同号");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_diff_num");
        playBean1.setBetCode("k3_sanbutong");

        playBean1.setSampleBet("从1～6中任选3个或多个号码。");
        playBean1.setSampleOpen("选号与开奖号码一致即中奖。");
        playBean1.setPlayTypeExplain("中奖号码为三个不同的号码，且全部在所购买的号码中，即为中奖。");
        playBean1.setSingleExplain("至少选择3个不同号码投注，选号与开奖号码一致即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("三同号");
        layoutBean1.setShowRightMenuType(1);
        layoutBean1.setChildItemCount(6);
        layoutBean1.setStartNumber(1);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initEBTH() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("二不同号");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("二不同号");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("二不同号");
        playBean1.setScheme("二不同号");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_diff_num");
        playBean1.setBetCode("k3_erbutong");

        playBean1.setSampleBet("从1～6中任选2个或多个号码。");
        playBean1.setSampleOpen("选号与开奖号码一致即中奖。");
        playBean1.setPlayTypeExplain("所选号码与开奖号码任意2个号码相同，即中奖。");
        playBean1.setSingleExplain("至少选择2个不同号码投注，选号与开奖号码一致即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("二不同");
        layoutBean1.setShowRightMenuType(1);
        layoutBean1.setChildItemCount(6);
        layoutBean1.setStartNumber(1);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initSLHTX() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("三连号通选");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("三连号通选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("三连号通选");
        playBean1.setScheme("三连号通选");
        playBean1.setPlayTypeName("标准选号");
        playBean1.setPlayTypeCode("k3_three_straight");
        playBean1.setBetCode("k3_tongxuan_sanlian");

        playBean1.setSampleBet("对所有3个相连的号码（123、234、345、456）进行投注。");
        playBean1.setSampleOpen("开出相应顺子即中奖。");
        playBean1.setPlayTypeExplain("中奖号码为123、234、345、456之一即中奖。");
        playBean1.setSingleExplain("全包顺子(123,234,345,456)投注，开出任意顺子即中奖。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("三连号");
        layoutBean1.setShowRightMenuType(2);
        layoutBean1.setChildItemCount(1);

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("通选");
        childLayoutBeans.add(childLayoutBean);
        layoutBean1.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

}
