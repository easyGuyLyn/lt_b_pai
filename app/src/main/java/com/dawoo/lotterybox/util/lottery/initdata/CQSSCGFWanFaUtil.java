package com.dawoo.lotterybox.util.lottery.initdata;

import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-2-19.
 */

public class CQSSCGFWanFaUtil {

    List<PlayTypeBean> mPlayTypeBeans = new ArrayList<>();

    public void initData(){
        initHSData();
        initQSData();
        initWXData();
        initSXData();
        initQEData();
        initBDWData();
        initDDWData();
        String gsonString = GsonUtil.GsonString(mPlayTypeBeans);
    }

    private void initDDWData(){

        //定胆位
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("定胆位");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("定位胆");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("定胆位");
        playBean1.setScheme("定胆位");
        playBean1.setPlayTypeName("定胆位");
        playBean1.setPlayTypeCode("ssc_yixing");
        playBean1.setBetCode("ssc_yixing_dwd");

        playBean1.setSampleBet("投注方案：万位1 ");
        playBean1.setSampleOpen("开奖号码：万位1，即中定位胆万位。");
        playBean1.setPlayTypeExplain("从万、千、百、十、个位中至少选择1个号码组成一注，每注由一个号码组成，所选号码与相同位置上的开奖号码一致，即为中奖。");
        playBean1.setSingleExplain("在万位、千位、百位、十位、个位任意位置上任意选择1个或1个以上号码。");
        playBean1.setRelation(false);

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("万位");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("千位");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        mLayoutBeans.add(layoutBean2);

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("百位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("十位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("个位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans.add(layoutBean5);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    //五星
    private void initWXData(){


        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("五星");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("五星直选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("五星");
        playBean1.setScheme("五星直选");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("ssc_wuxing_zhixuan");
        playBean1.setBetCode("ssc_wuxing_zhixuan_fs");

        playBean1.setSampleBet("投注方案：13456 ");
        playBean1.setSampleOpen("开奖号码：13456，即中五星直选。");
        playBean1.setPlayTypeExplain("从万、千、百、十、个位中至少各选择1个号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。");
        playBean1.setSingleExplain("从万位、千位、百位、十位、个位各选一个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("万位");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("千位");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        mLayoutBeans.add(layoutBean2);

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("百位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("十位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("个位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans.add(layoutBean5);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("五星");
        playBean2.setScheme("五星直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("ssc_wuxing_zhixuan");
        playBean2.setBetCode("ssc_wuxing_zhixuan_ds");

        playBean2.setSampleBet("投注方案：23456 ");
        playBean2.setSampleOpen("开奖号码：23456，即中五星直选。");
        playBean2.setPlayTypeExplain("手动输入一个五位数号码组成一注，所选的号码万、千、百、十、个位与号码相同，且顺序一致即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个五位数号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(2);
        layoutBean6.setSelectMin(5);
        mLayoutBeans2.add(layoutBean6);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean1.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initSXData(){
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("四星");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("四星直选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("四星");
        playBean1.setScheme("四星直选");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("ssc_sixing_zhixuan");
        playBean1.setBetCode("ssc_sixing_zhixuan_fs");

        playBean1.setSampleBet("投注方案：3456 ");
        playBean1.setSampleOpen("开奖号码后四位：3456，即中四星直选。");
        playBean1.setPlayTypeExplain("从千、百、十、个位中至少各选择1个号码组成一注，所选号码与开奖号码后四位相同，且顺序一致，即为中奖。");
        playBean1.setSingleExplain("从千位、百位、十位、个位各选一个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("千位");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        mLayoutBeans.add(layoutBean2);

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("百位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("十位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("个位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans.add(layoutBean5);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("四星");
        playBean2.setScheme("四星直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("ssc_sixing_zhixuan");
        playBean2.setBetCode("ssc_sixing_zhixuan_ds");

        playBean2.setSampleBet("投注方案：23456 ");
        playBean2.setSampleOpen("开奖号码：23456，即中五星直选。");
        playBean2.setPlayTypeExplain("手动输入一个五位数号码组成一注，所选的号码万、千、百、十、个位与号码相同，且顺序一致即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个五位数号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(2);
        layoutBean6.setSelectMin(4);
        mLayoutBeans2.add(layoutBean6);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initHSData(){
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("后三");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("后三直选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("后三");
        playBean1.setScheme("后三直选");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean1.setBetCode("ssc_sanxing_zhixuan_hsfs");

        playBean1.setSampleBet("投注方案：345 ");
        playBean1.setSampleOpen("开奖号码后三位：345，即中后三位直选。");
        playBean1.setPlayTypeExplain("从百、十、个位中至少各选择1个号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。");
        playBean1.setSingleExplain("从百位、十位、个位各选一个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("百位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("十位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("个位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans.add(layoutBean5);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("后三");
        playBean2.setScheme("后三直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean2.setBetCode("ssc_sanxing_zhixuan_hsds");

        playBean2.setSampleBet("投注方案：345");
        playBean2.setSampleOpen("开奖号码：345，即中后三直选。");
        playBean2.setPlayTypeExplain("从百、十、个位中至少各选择1个号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个3位数号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(2);
        layoutBean6.setSelectMin(3);
        mLayoutBeans2.add(layoutBean6);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("后三");
        playBean3.setScheme("后三直选");
        playBean3.setPlayTypeName("后三组合");
        playBean3.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean3.setBetCode("ssc_sanxing_zhixuan_hszh");

        playBean3.setSampleBet("投注方案：购买：6+7+8，由以下三注：678(三星)、78(二星)、8(一星)构成 ");
        playBean3.setSampleOpen("开奖号码：678，即可中奖 一星、二星、三星各一注。");
        playBean3.setPlayTypeExplain("从百、十、个位中至少各选择1个号码组成1-3星的组合共三注，当个位数与开奖号码相同，则中一个3等奖；如果个位和十位号码和开奖号码相同，则中一个3等奖以及一个2等奖，以次类推，最高可中3个奖。");
        playBean3.setSingleExplain("从百位、十位、个位各选一个号码组成三注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean7 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean7.setLayoutType(0);
        layoutBean7.setLayoutTitle("百位");
        layoutBean7.setShowRightMenuType(0);
        layoutBean7.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean7);

        PlayTypeBean.PlayBean.LayoutBean layoutBean8 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean8.setLayoutType(0);
        layoutBean8.setLayoutTitle("十位");
        layoutBean8.setShowRightMenuType(0);
        layoutBean8.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean8);

        PlayTypeBean.PlayBean.LayoutBean layoutBean9 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean9.setLayoutType(0);
        layoutBean9.setLayoutTitle("个位");
        layoutBean9.setShowRightMenuType(0);
        layoutBean9.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean9);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setMainType("后三");
        playBean4.setScheme("后三直选");
        playBean4.setPlayTypeName("直选和值");
        playBean4.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean4.setBetCode("ssc_sanxing_zhixuan_hshz");

        playBean4.setSampleBet("投注方案：和值：1");
        playBean4.setSampleOpen("开奖号码：后三位001、010、100，即中后三直选。");
        playBean4.setPlayTypeExplain("所选号码数值等于开奖号码的百、十、个三个数值相加之和，即为中奖。");
        playBean4.setSingleExplain("从0-27中任意选择1个或1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans4 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean10 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean10.setLayoutType(0);
        layoutBean10.setLayoutTitle("和值");
        layoutBean10.setShowRightMenuType(1);
        layoutBean10.setChildItemCount(27);
        layoutBean10.setSplitCharacter("|");
        mLayoutBeans4.add(layoutBean10);

        playBean4.setLayoutBeans(mLayoutBeans4);
        playBeans.add(playBean4);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setMainType("后三");
        playBean5.setScheme("后三直选");
        playBean5.setPlayTypeName("直选跨度");
        playBean5.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean5.setBetCode("ssc_sanxing_zhixuan_hskd");

        playBean5.setSampleBet("投注方案：跨度：8");
        playBean5.setSampleOpen("开奖号码：**129，最大号码9与最小号码1相减值等于8，所选号与跨度号码相同即中奖。");
        playBean5.setPlayTypeExplain("所选数值与开奖号码后三位最大和最小数字相减之差，即为中奖。");
        playBean5.setSingleExplain("从0-9中选择1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans5 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean11 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean11.setLayoutType(0);
        layoutBean11.setLayoutTitle("跨度");
        layoutBean11.setShowRightMenuType(0);
        layoutBean11.setChildItemCount(10);
        mLayoutBeans5.add(layoutBean11);

        playBean5.setLayoutBeans(mLayoutBeans5);
        playBeans.add(playBean5);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean1.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean2 = new PlayTypeBean();
        playTypeBean2.setParentTitle("后三");
        playTypeBean2.setTotalType("官方玩法");
        playTypeBean2.setType("后三组选");
        List<PlayTypeBean.PlayBean> playBeans2 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setMainType("后三");
        playBean6.setScheme("后三组选");
        playBean6.setPlayTypeName("组三复式");
        playBean6.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean6.setBetCode("ssc_sanxing_zuxuan_hsz3fs");

        playBean6.setSampleBet("投注方案：588");
        playBean6.setSampleOpen("开奖号码：后三位588(顺序不限)，即可中后三组选三。");
        playBean6.setPlayTypeExplain("从0-9号码中至少选择2个号码组成两注，所选号码与开奖号码的百、十、个位相同，且顺序不限，即为中奖。");
        playBean6.setSingleExplain("从0-9中任意选择2个或2个以上号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans6 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean12 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean12.setLayoutType(0);
        layoutBean12.setLayoutTitle("组三");
        layoutBean12.setShowRightMenuType(0);
        layoutBean12.setChildItemCount(10);
        layoutBean12.setSelectMin(2);
        mLayoutBeans6.add(layoutBean12);

        playBean6.setLayoutBeans(mLayoutBeans6);
        playBeans2.add(playBean6);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setMainType("后三");
        playBean7.setScheme("后三组选");
        playBean7.setPlayTypeName("组三单式");
        playBean7.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean7.setBetCode("ssc_sanxing_zuxuan_hsz3ds");

        playBean7.setSampleBet("投注方案：001 ");
        playBean7.setSampleOpen("开奖号码：后三位010(顺序不限)，即中后三组选三。");
        playBean7.setPlayTypeExplain("手动输入一个3位数号码组成一注，三个数字中必须有两个数字相同，输入的数字与开奖的百、十、个位相同，顺序不限，即为中奖。");
        playBean7.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字中必须有二个数字相同）。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans7 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean13 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean13.setLayoutType(2);
        layoutBean13.setSelectMin(3);
        mLayoutBeans7.add(layoutBean13);

        playBean7.setLayoutBeans(mLayoutBeans7);
        playBeans2.add(playBean7);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setMainType("后三");
        playBean8.setScheme("后三组选");
        playBean8.setPlayTypeName("组六复式");
        playBean8.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean8.setBetCode("ssc_sanxing_zuxuan_hsz6fs");

        playBean8.setSampleBet("投注方案：258");
        playBean8.setSampleOpen("开奖号码：后三位852(顺序不限)，即可中后三组选六。");
        playBean8.setPlayTypeExplain("从0-9号码中至少选择3个号码组成一注，所选号码与开奖号码的百、十、个位相同，且顺序不限，即为中奖。");
        playBean8.setSingleExplain("从0-9中任意选择3个或3个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans8 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean14 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean14.setLayoutType(0);
        layoutBean14.setLayoutTitle("组六");
        layoutBean14.setShowRightMenuType(0);
        layoutBean14.setChildItemCount(10);
        layoutBean14.setSelectMin(3);
        mLayoutBeans8.add(layoutBean14);

        playBean8.setLayoutBeans(mLayoutBeans8);
        playBeans2.add(playBean8);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setMainType("后三");
        playBean9.setScheme("后三组选");
        playBean9.setPlayTypeName("组六单式");
        playBean9.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean9.setBetCode("ssc_sanxing_zuxuan_hsz6ds");

        playBean9.setSampleBet("投注方案：123 ");
        playBean9.setSampleOpen("开奖号码：后三位321(顺序不限)，即中后三组选六。");
        playBean9.setPlayTypeExplain("手动输入一个3位数号码组成一注，所选的号码与开奖的号码的百、十、个位相同，顺序不限，即为中奖。");
        playBean9.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字完全不相同）。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans9 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean15 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean15.setLayoutType(2);
        layoutBean15.setSelectMin(3);
        mLayoutBeans9.add(layoutBean15);

        playBean9.setLayoutBeans(mLayoutBeans9);
        playBeans2.add(playBean9);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setMainType("后三");
        playBean10.setScheme("后三组选");
        playBean10.setPlayTypeName("混合组选");
        playBean10.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean10.setBetCode("ssc_sanxing_zuxuan_hshhzx");

        playBean10.setSampleBet("投注方案：001和123 ");
        playBean10.setSampleOpen("开奖号码：后三位010(顺序不限)，即中后三组选三，或后三位312(顺序不限)，即中后三组选六。");
        playBean10.setPlayTypeExplain("手动输入一个3位数号码组成一注(不包含豹子号)，开奖号码后3位为组选三或组选六形态，投注号码与开奖号码后三位相同，顺序不限，即为中奖。");
        playBean10.setSingleExplain("手动至少输入三个号码构成一注(不包含豹子号)。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans10 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean16 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean16.setLayoutType(2);
        layoutBean16.setSelectMin(3);
        mLayoutBeans10.add(layoutBean16);

        playBean10.setLayoutBeans(mLayoutBeans10);
        playBeans2.add(playBean10);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setMainType("后三");
        playBean11.setScheme("后三组选");
        playBean11.setPlayTypeName("组选和值");
        playBean11.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean11.setBetCode("ssc_sanxing_zuxuan_hszxhz");

        playBean11.setSampleBet("投注方案：和值：3");
        playBean11.setSampleOpen("开奖号码：后三位003(顺序不限)，即中后三组选三，或者后三位012(顺序不限)即中后三组选六。");
        playBean11.setPlayTypeExplain("开奖号码后3位数号码组成一注(不包含豹子号)，开奖号码后3位为组选三或组选六形态，所选数值等于开奖号码的百、十、个位三个数字相加之和，即为中奖。");
        playBean11.setSingleExplain("从1-26中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans11 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean17= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean17.setLayoutType(0);
        layoutBean17.setShowRightMenuType(1);
        layoutBean17.setLayoutTitle("和值");
        layoutBean17.setStartNumber(1);
        layoutBean17.setChildItemCount(26);
        layoutBean17.setSplitCharacter("|");
        mLayoutBeans11.add(layoutBean17);

        playBean11.setLayoutBeans(mLayoutBeans11);
        playBeans2.add(playBean11);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setMainType("后三");
        playBean12.setScheme("后三组选");
        playBean12.setPlayTypeName("组选包胆");
        playBean12.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean12.setBetCode("ssc_sanxing_zuxuan_hszxbd");

        playBean12.setSampleBet("投注方案：包胆3");
        playBean12.setSampleOpen("开奖号码：后三位3XX或者33X，即中后三组选三，后三位3XY，即中后三组选六");
        playBean12.setPlayTypeExplain("从0-9号码中任意选择一个胆码，开奖号码后三位为组选三或组选六形态(不含豹子号)，投注号码与开奖后三位中任意一位相同，即为中奖。");
        playBean12.setSingleExplain("从0-9中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans12 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean18= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean18.setLayoutType(0);
        layoutBean18.setShowRightMenuType(2);
        layoutBean18.setLayoutTitle("包胆");
        layoutBean18.setChildItemCount(10);
        layoutBean18.setSelectMax(1);
        mLayoutBeans12.add(layoutBean18);

        playBean12.setLayoutBeans(mLayoutBeans12);
        playBeans2.add(playBean12);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean2.setPlayBeans(playBeans2);
        mPlayTypeBeans.add(playTypeBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean3 = new PlayTypeBean();
        playTypeBean3.setParentTitle("后三");
        playTypeBean3.setTotalType("官方玩法");
        playTypeBean3.setType("后三其它");
        List<PlayTypeBean.PlayBean> playBeans3 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean13 = new PlayTypeBean.PlayBean();
        playBean13.setMainType("后三");
        playBean13.setScheme("后三其它");
        playBean13.setPlayTypeName("和值尾数");
        playBean13.setPlayTypeCode("ssc_sanxing_teshu");
        playBean13.setBetCode("ssc_sanxing_zuxuan_hshzws");

        playBean13.setSampleBet("投注方案：和值尾数8 ");
        playBean13.setSampleOpen("开奖号码：后三位936，和值尾数为8，即中和值尾数。");
        playBean13.setPlayTypeExplain("所选号码等于开奖号码的百、十、个位数字相加之和的尾数，即为中奖。");
        playBean13.setSingleExplain("从0-9中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans13 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean19= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean19.setLayoutType(0);
        layoutBean19.setShowRightMenuType(0);
        layoutBean19.setLayoutTitle("和值尾数");
        layoutBean19.setChildItemCount(10);
        layoutBean19.setSplitCharacter("|");
        mLayoutBeans13.add(layoutBean19);

        playBean13.setLayoutBeans(mLayoutBeans13);
        playBeans3.add(playBean13);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean14 = new PlayTypeBean.PlayBean();
        playBean14.setMainType("后三");
        playBean14.setScheme("后三其它");
        playBean14.setPlayTypeName("特殊号");
        playBean14.setPlayTypeCode("ssc_sanxing_teshu");
        playBean14.setBetCode("ssc_sanxing_zuxuan_hsts");

        playBean14.setSampleBet("投注方案：豹子顺子对子 ");
        playBean14.setSampleOpen("开奖号码：后三位888，即中豹子；后三位678，即中顺子；后三位558，即中对子。");
        playBean14.setPlayTypeExplain("所选号码特殊属性与开奖号码后三位号码属性一致，即为中奖。其中：1.顺子号的百、十、个位不分顺序(特别号码：019、089也是顺子号)；2.对子号指的是开奖号码的后三位当中，任意两位数字相同的三位数号码");
        playBean14.setSingleExplain("选择1个或一个以上特殊号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans14 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean20= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean20.setLayoutType(0);
        layoutBean20.setShowRightMenuType(2);
        layoutBean20.setLayoutTitle("特殊号");
        layoutBean20.setChildItemCount(3);
        layoutBean20.setSplitCharacter("|");

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("豹子");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean1 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean1.setNumber("顺子");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean2.setNumber("对子");
        childLayoutBeans.add(childLayoutBean);
        childLayoutBeans.add(childLayoutBean1);
        childLayoutBeans.add(childLayoutBean2);
        layoutBean20.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans14.add(layoutBean20);

        playBean14.setLayoutBeans(mLayoutBeans14);
        playBeans3.add(playBean14);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean3.setPlayBeans(playBeans3);
        mPlayTypeBeans.add(playTypeBean3);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    }

    private void initQSData(){
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("前三");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("前三直选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("前三");
        playBean1.setScheme("前三直选");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean1.setBetCode("ssc_sanxing_zhixuan_qsfs");

        playBean1.setSampleBet("投注方案：345 ");
        playBean1.setSampleOpen("开奖号码:前三位：345，即中前三位直选。");
        playBean1.setPlayTypeExplain("从万、千、百位中至少各选择1个号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。");
        playBean1.setSingleExplain("从万位、千位、百位各选一个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("万位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("千位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("百位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans.add(layoutBean5);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("前三");
        playBean2.setScheme("前三直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean2.setBetCode("ssc_sanxing_zhixuan_qsds");
        playBean2.setSampleBet("投注方案：345");
        playBean2.setSampleOpen("开奖号码：345，即中前三直选。");
        playBean2.setPlayTypeExplain("从万、千、百位中至少各选择1个号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个3位数号码组成一注。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(2);
        layoutBean6.setSelectMin(3);
        mLayoutBeans2.add(layoutBean6);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("前三");
        playBean3.setScheme("前三直选");
        playBean3.setPlayTypeName("前三组合");
        playBean3.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean3.setBetCode("ssc_sanxing_zhixuan_qszh");

        playBean3.setSampleBet("投注方案：购买：6+7+8，由以下三注：678(三星)、78(二星)、8(一星)构成 ");
        playBean3.setSampleOpen("开奖号码：678，即可中奖 一星、二星、三星各一注。");
        playBean3.setPlayTypeExplain("从万、千、百位中至少各选择1个号码组成1-3星的组合共三注，当百位数与开奖号码相同，则中一个3等奖；如果百位和千位号码和开奖号码相同，则中一个3等奖以及一个2等奖，以次类推，最高可中3个奖。");
        playBean3.setSingleExplain("从万位、千位、百位各选一个号码组成三注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean7 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean7.setLayoutType(0);
        layoutBean7.setLayoutTitle("万位");
        layoutBean7.setShowRightMenuType(0);
        layoutBean7.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean7);

        PlayTypeBean.PlayBean.LayoutBean layoutBean8 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean8.setLayoutType(0);
        layoutBean8.setLayoutTitle("千位");
        layoutBean8.setShowRightMenuType(0);
        layoutBean8.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean8);

        PlayTypeBean.PlayBean.LayoutBean layoutBean9 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean9.setLayoutType(0);
        layoutBean9.setLayoutTitle("百位");
        layoutBean9.setShowRightMenuType(0);
        layoutBean9.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean9);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setMainType("前三");
        playBean4.setScheme("前三直选");
        playBean4.setPlayTypeName("直选和值");
        playBean4.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean4.setBetCode("ssc_sanxing_zhixuan_qshz");

        playBean4.setSampleBet("投注方案：和值：1");
        playBean4.setSampleOpen("开奖号码：前三位001、010、100，即中前三直选。");
        playBean4.setPlayTypeExplain("所选号码数值等于开奖号码的万、千、百三个数值相加之和，即为中奖。");
        playBean4.setSingleExplain("从0-27中任意选择1个或1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans4 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean10 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean10.setLayoutType(0);
        layoutBean10.setLayoutTitle("和值");
        layoutBean10.setShowRightMenuType(1);
        layoutBean10.setChildItemCount(27);
        layoutBean10.setSplitCharacter("|");
        mLayoutBeans4.add(layoutBean10);

        playBean4.setLayoutBeans(mLayoutBeans4);
        playBeans.add(playBean4);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setMainType("前三");
        playBean5.setScheme("前三直选");
        playBean5.setPlayTypeName("直选跨度");
        playBean5.setPlayTypeCode("ssc_sanxing_zhixuan");
        playBean5.setBetCode("ssc_sanxing_zhixuan_qskd");

        playBean5.setSampleBet("投注方案：跨度：8");
        playBean5.setSampleOpen("开奖号码：**129，最大号码9与最小号码1相减值等于8，所选号与跨度号码相同即中奖。");
        playBean5.setPlayTypeExplain("所选数值与开奖号码前三位最大和最小数字相减之差，即为中奖。");
        playBean5.setSingleExplain("从0-9中选择1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans5 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean11 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean11.setLayoutType(0);
        layoutBean11.setLayoutTitle("跨度");
        layoutBean11.setShowRightMenuType(0);
        layoutBean11.setChildItemCount(10);
        mLayoutBeans5.add(layoutBean11);

        playBean5.setLayoutBeans(mLayoutBeans5);
        playBeans.add(playBean5);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean1.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean2 = new PlayTypeBean();
        playTypeBean2.setParentTitle("前三");
        playTypeBean2.setTotalType("官方玩法");
        playTypeBean2.setType("前三组选");
        List<PlayTypeBean.PlayBean> playBeans2 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setMainType("前三");
        playBean6.setScheme("前三组选");
        playBean6.setPlayTypeName("组三复式");
        playBean6.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean6.setBetCode("ssc_sanxing_zuxuan_qsz3fs");

        playBean6.setSampleBet("规则 投注方案：588");
        playBean6.setSampleOpen("开奖号码：后三位588(顺序不限)，即可中后三组选三。");
        playBean6.setPlayTypeExplain("从0-9号码中至少选择2个号码组成两注，所选号码与开奖号码的百、十、个位相同，且顺序不限，即为中奖。");
        playBean6.setSingleExplain("从0-9中任意选择2个或2个以上号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans6 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean12 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean12.setLayoutType(0);
        layoutBean12.setLayoutTitle("组三");
        layoutBean12.setShowRightMenuType(0);
        layoutBean12.setChildItemCount(10);
        layoutBean12.setSelectMin(2);
        mLayoutBeans6.add(layoutBean12);

        playBean6.setLayoutBeans(mLayoutBeans6);
        playBeans2.add(playBean6);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setMainType("前三");
        playBean7.setScheme("前三组选");
        playBean7.setPlayTypeName("组三单式");
        playBean7.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean7.setBetCode("ssc_sanxing_zuxuan_qsz3ds");

        playBean7.setSampleBet("投注方案：001 ");
        playBean7.setSampleOpen("开奖号码：前三位010(顺序不限)，即中前三组选三。");
        playBean7.setPlayTypeExplain("手动输入一个3位数号码组成一注，三个数字中必须有两个数字相同，输入的数字与开奖的万、千、百位相同，顺序不限，即为中奖。");
        playBean7.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字中必须有二个数字相同）。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans7 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean13 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean13.setLayoutType(2);
        layoutBean13.setSelectMin(3);
        mLayoutBeans7.add(layoutBean13);

        playBean7.setLayoutBeans(mLayoutBeans7);
        playBeans2.add(playBean7);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setMainType("前三");
        playBean8.setScheme("前三组选");
        playBean8.setPlayTypeName("组六复式");
        playBean8.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean8.setBetCode("ssc_sanxing_zuxuan_qsz6fs");

        playBean8.setSampleBet("投注方案：258");
        playBean8.setSampleOpen("开奖号码：前三位852(顺序不限)，即可中前三组选六。");
        playBean8.setPlayTypeExplain("从0-9号码中至少选择3个号码组成一注，所选号码与开奖号码的万、千、百位相同，且顺序不限，即为中奖。");
        playBean8.setSingleExplain("从0-9中任意选择3个或3个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans8 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean14 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean14.setLayoutType(0);
        layoutBean14.setLayoutTitle("组六");
        layoutBean14.setShowRightMenuType(0);
        layoutBean14.setChildItemCount(10);
        layoutBean14.setSelectMin(3);
        mLayoutBeans8.add(layoutBean14);

        playBean8.setLayoutBeans(mLayoutBeans8);
        playBeans2.add(playBean8);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setMainType("前三");
        playBean9.setScheme("前三组选");
        playBean9.setPlayTypeName("组六单式");
        playBean9.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean9.setBetCode("ssc_sanxing_zuxuan_qsz6ds");

        playBean9.setSampleBet("投注方案：123 ");
        playBean9.setSampleOpen("开奖号码：前三位321(顺序不限)，即中前三组选六。");
        playBean9.setPlayTypeExplain("手动输入一个3位数号码组成一注，所选的号码与开奖的号码的万、千、百位相同，顺序不限，即为中奖。");
        playBean9.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字完全不相同）。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans9 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean15 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean15.setLayoutType(2);
        layoutBean15.setSelectMin(3);
        mLayoutBeans9.add(layoutBean15);

        playBean9.setLayoutBeans(mLayoutBeans9);
        playBeans2.add(playBean9);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setMainType("前三");
        playBean10.setScheme("前三组选");
        playBean10.setPlayTypeName("混合组选");
        playBean10.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean10.setBetCode("ssc_sanxing_zuxuan_qshhzx");

        playBean10.setSampleBet("投注方案：001和123 ");
        playBean10.setSampleOpen("开奖号码：前三位010(顺序不限)，即中前三组选三，或前三位312(顺序不限)，即中前三组选六。");
        playBean10.setPlayTypeExplain("手动输入一个3位数号码组成一注(不包含豹子号)，开奖号码前3位为组选三或组选六形态，投注号码与开奖号码前三位相同，顺序不限，即为中奖。");
        playBean10.setSingleExplain("手动至少输入三个号码构成一注(不包含豹子号)。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans10 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean16 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean16.setLayoutType(2);
        layoutBean16.setSelectMin(3);
        mLayoutBeans10.add(layoutBean16);

        playBean10.setLayoutBeans(mLayoutBeans10);
        playBeans2.add(playBean10);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setMainType("前三");
        playBean11.setScheme("前三组选");
        playBean11.setPlayTypeName("组选和值");
        playBean11.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean11.setBetCode("ssc_sanxing_zuxuan_qszxhz");

        playBean11.setSampleBet("投注方案：和值：3");
        playBean11.setSampleOpen("开奖号码：前三位003(顺序不限)，即中前三组选三，或者前三位012(顺序不限)即中前三组选六。");
        playBean11.setPlayTypeExplain("开奖号码前3位数号码组成一注(不包含豹子号)，开奖号码前3位为组选三或组选六形态，所选数值等于开奖号码的万、千、百位三个数字相加之和，即为中奖。");
        playBean11.setSingleExplain("从1-26中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans11 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean17= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean17.setLayoutType(0);
        layoutBean17.setShowRightMenuType(1);
        layoutBean17.setStartNumber(1);
        layoutBean17.setLayoutTitle("和值");
        layoutBean17.setChildItemCount(26);
        layoutBean17.setSplitCharacter("|");
        mLayoutBeans11.add(layoutBean17);

        playBean11.setLayoutBeans(mLayoutBeans11);
        playBeans2.add(playBean11);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setMainType("前三");
        playBean12.setScheme("前三组选");
        playBean12.setPlayTypeName("组选包胆");
        playBean12.setPlayTypeCode("ssc_sanxing_zuxuan");
        playBean12.setBetCode("ssc_sanxing_zuxuan_qszxbd");

        playBean12.setSampleBet("投注方案：包胆3");
        playBean12.setSampleOpen("开奖号码：前三位3XX或者33X，即中前三组选三，前三位3XY，即中前三组选六");
        playBean12.setPlayTypeExplain("从0-9号码中任意选择一个胆码，开奖号码前三位为组选三或组选六形态(不含豹子号)，投注号码与开奖前三位中任意一位相同，即为中奖。");
        playBean12.setSingleExplain("从0-9中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans12 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean18= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean18.setLayoutType(0);
        layoutBean18.setShowRightMenuType(2);
        layoutBean18.setLayoutTitle("包胆");
        layoutBean18.setChildItemCount(10);
        layoutBean18.setSelectMax(1);
        mLayoutBeans12.add(layoutBean18);

        playBean12.setLayoutBeans(mLayoutBeans12);
        playBeans2.add(playBean12);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean2.setPlayBeans(playBeans2);
        mPlayTypeBeans.add(playTypeBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean3 = new PlayTypeBean();
        playTypeBean3.setParentTitle("前三");
        playTypeBean3.setTotalType("官方玩法");
        playTypeBean3.setType("前三其它");
        List<PlayTypeBean.PlayBean> playBeans3 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean13 = new PlayTypeBean.PlayBean();
        playBean13.setMainType("前三");
        playBean13.setScheme("前三其它");
        playBean13.setPlayTypeName("和值尾数");
        playBean13.setPlayTypeCode("ssc_sanxing_teshu");
        playBean13.setBetCode("ssc_sanxing_zuxuan_qshzws");

        playBean13.setSampleBet("投注方案：和值尾数8 ");
        playBean13.setSampleOpen("开奖号码：前三位936，和值尾数为8，即中和值尾数。");
        playBean13.setPlayTypeExplain("所选号码等于开奖号码的万、千、百位数字相加之和的尾数，即为中奖。");
        playBean13.setSingleExplain("从0-9中选择1个号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans13 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean19= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean19.setLayoutType(0);
        layoutBean19.setShowRightMenuType(0);
        layoutBean19.setLayoutTitle("和值尾数");
        layoutBean19.setChildItemCount(10);
        layoutBean19.setSplitCharacter("|");
        mLayoutBeans13.add(layoutBean19);

        playBean13.setLayoutBeans(mLayoutBeans13);
        playBeans3.add(playBean13);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean14 = new PlayTypeBean.PlayBean();
        playBean14.setMainType("前三");
        playBean14.setScheme("前三其它");
        playBean14.setPlayTypeName("特殊号");
        playBean14.setPlayTypeCode("ssc_sanxing_teshu");
        playBean14.setBetCode("ssc_sanxing_zuxuan_qsts");

        playBean14.setSampleBet("投注方案：豹子顺子对子 ");
        playBean14.setSampleOpen("开奖号码：前三位888，即中豹子；前三位678，即中顺子；前三位558，即中对子。");
        playBean14.setPlayTypeExplain("所选号码特殊属性与开奖号码前三位号码属性一致，即为中奖。其中：1.顺子号的万、千、百位不分顺序(特别号码：019、089也是顺子号)；2.对子号指的是开奖号码的前三位当中，任意两位数字相同的三位数号码");
        playBean14.setSingleExplain("选择1个或一个以上特殊号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans14 = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean20= new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean20.setLayoutType(0);
        layoutBean20.setShowRightMenuType(2);
        layoutBean20.setLayoutTitle("特殊号");
        layoutBean20.setChildItemCount(3);
        layoutBean20.setSplitCharacter("|");

        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean.setNumber("豹子");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean1 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean1.setNumber("顺子");
        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2 = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
        childLayoutBean2.setNumber("对子");
        childLayoutBeans.add(childLayoutBean);
        childLayoutBeans.add(childLayoutBean1);
        childLayoutBeans.add(childLayoutBean2);
        layoutBean20.setChildLayoutBeans(childLayoutBeans);
        mLayoutBeans14.add(layoutBean20);

        playBean14.setLayoutBeans(mLayoutBeans14);
        playBeans3.add(playBean14);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        playTypeBean3.setPlayBeans(playBeans3);
        mPlayTypeBeans.add(playTypeBean3);
    }

    private void initQEData(){
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("前二");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("前二直选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("前二");
        playBean1.setScheme("前二直选");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("ssc_erxing_zhixuan");
        playBean1.setBetCode("ssc_erxing_zhixuan_qefs");

        playBean1.setSampleBet("投注方案：58 ");
        playBean1.setSampleOpen("开奖号码前两位：58，即中前二直选。");
        playBean1.setPlayTypeExplain("从万、千位中至少各选择1个号码组成一注，所选号码与开奖号码的前2位相同，且顺序一致，即为中奖。");
        playBean1.setSingleExplain("从万位、千位中至少各选1个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("万位");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("千位");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        mLayoutBeans.add(layoutBean2);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("前二");
        playBean2.setScheme("前二直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("ssc_erxing_zhixuan");
        playBean2.setBetCode("ssc_erxing_zhixuan_qeds");

        playBean2.setSampleBet("投注方案：58 ");
        playBean2.setSampleOpen("开奖号码前两位：58，即中前二直选。");
        playBean2.setPlayTypeExplain("手动输入2个号码组成一注，输入号码的万、千位与开奖号码相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个二位数号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(2);
        layoutBean3.setSelectMin(2);
        mLayoutBeans2.add(layoutBean3);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("前二");
        playBean3.setScheme("前二直选");
        playBean3.setPlayTypeName("直选和值");
        playBean3.setPlayTypeCode("ssc_erxing_zhixuan");
        playBean3.setBetCode("ssc_erxing_zhixuan_qehz");

        playBean3.setSampleBet("投注方案：和值1 ");
        playBean3.setSampleOpen("开奖号码前两位：01或10，即中前二和值。");
        playBean3.setPlayTypeExplain("开奖号码的万、千位中两个数字相加之和等于所选和值，即为中奖。");
        playBean3.setSingleExplain("从0-18中任意选择1个或1个以上的和值号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("和值");
        layoutBean4.setShowRightMenuType(1);
        layoutBean4.setChildItemCount(19);
        layoutBean4.setSplitCharacter("|");
        mLayoutBeans3.add(layoutBean4);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setMainType("前二");
        playBean4.setScheme("前二直选");
        playBean4.setPlayTypeName("直选跨度");
        playBean4.setPlayTypeCode("ssc_erxing_zhixuan");
        playBean4.setBetCode("ssc_erxing_zhixuan_qekd");

        playBean4.setSampleBet("投注方案：跨度9 ");
        playBean4.setSampleOpen("开奖号码：90***，最大值9与最小值0相减之差即为跨度值，当所选号与跨度号码相同，即为中奖。");
        playBean4.setPlayTypeExplain("所选数值等于前二位最大数与最小数相减之差，即为中奖。");
        playBean4.setSingleExplain("从0-9中任意选择1个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans4 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("跨度");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans4.add(layoutBean5);

        playBean4.setLayoutBeans(mLayoutBeans4);
        playBeans.add(playBean4);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean1.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean2 = new PlayTypeBean();
        playTypeBean2.setParentTitle("前二");
        playTypeBean2.setTotalType("官方玩法");
        playTypeBean2.setType("前二组选");
        List<PlayTypeBean.PlayBean> playBeans2 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setMainType("前二");
        playBean5.setScheme("前二组选");
        playBean5.setPlayTypeName("组选复式");
        playBean5.setPlayTypeCode("ssc_erxing_zuxuan");
        playBean5.setBetCode("ssc_erxing_zuxuan_qefs");

        playBean5.setSampleBet("投注方案：58 ");
        playBean5.setSampleOpen("开奖号码：前两位 58 或 85(顺序不限，不含对子号)，即为中奖。");
        playBean5.setPlayTypeExplain("从0-9号码中选取两个号码组成一注，所选号码与开奖号码的万、千位相同，顺序不限，即为中奖。");
        playBean5.setSingleExplain("从0-9中任意选择2个或2个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans5 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(0);
        layoutBean6.setLayoutTitle("组选");
        layoutBean6.setShowRightMenuType(0);
        layoutBean6.setChildItemCount(10);
        layoutBean6.setSelectMin(2);
        mLayoutBeans5.add(layoutBean6);

        playBean5.setLayoutBeans(mLayoutBeans5);
        playBeans2.add(playBean5);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setMainType("前二");
        playBean6.setScheme("前二组选");
        playBean6.setPlayTypeName("组选单式");
        playBean6.setPlayTypeCode("ssc_erxing_zuxuan");
        playBean6.setBetCode("ssc_erxing_zuxuan_qeds");

        playBean6.setSampleBet("投注方案：58 ");
        playBean6.setSampleOpen("开奖号码：前二位 58 或者 85(顺序不限，不含对子号)，即中前二组选。");
        playBean6.setPlayTypeExplain("手动输入2个号码组成一注，输入号码的万、千位与开奖号码相同，且顺序不限，即为中奖。");
        playBean6.setSingleExplain("手动输入号码，至少输入1个二位数号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans6 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean7 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean7.setLayoutType(2);
        layoutBean7.setSelectMin(2);
        mLayoutBeans6.add(layoutBean7);

        playBean6.setLayoutBeans(mLayoutBeans6);
        playBeans2.add(playBean6);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setMainType("前二");
        playBean7.setScheme("前二组选");
        playBean7.setPlayTypeName("组选和值");
        playBean7.setPlayTypeCode("ssc_erxing_zuxuan");
        playBean7.setBetCode("ssc_erxing_zuxuan_qehz");

        playBean7.setSampleBet("投注方案：和值1 ");
        playBean7.setSampleOpen("开奖号码前两位：01或10(顺序不限，不含对子号)，即中前二组选。");
        playBean7.setPlayTypeExplain("开奖号码的万、千位中两个数字相加之和等于所选号(不含对子号)，即为中奖。");
        playBean7.setSingleExplain("从1-17中任意选择1个或者1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans7 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean8 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean8.setLayoutType(0);
        layoutBean8.setLayoutTitle("和值");
        layoutBean8.setShowRightMenuType(0);
        layoutBean8.setStartNumber(1);
        layoutBean8.setChildItemCount(17);
        layoutBean8.setSplitCharacter("|");
        mLayoutBeans7.add(layoutBean8);

        playBean7.setLayoutBeans(mLayoutBeans7);
        playBeans2.add(playBean7);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setMainType("前二");
        playBean8.setScheme("前二组选");
        playBean8.setPlayTypeName("组选包胆");
        playBean8.setPlayTypeCode("ssc_erxing_zuxuan");
        playBean8.setBetCode("ssc_erxing_zuxuan_qebd");

        playBean8.setSampleBet("投注方案：包胆8 ");
        playBean8.setSampleOpen("开奖号码：前二位 8×，且×不等于8，即中前二组选。");
        playBean8.setPlayTypeExplain("从0-9号码中任意选取一个胆码，开奖号码前二位各不相同(不含对子号)，投注号码与开奖号码前二位中任意一位相同，即为中奖。");
        playBean8.setSingleExplain("从0-9中任意选择1个包胆号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans8 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean9 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean9.setLayoutType(0);
        layoutBean9.setLayoutTitle("胆码");
        layoutBean9.setShowRightMenuType(2);
        layoutBean9.setChildItemCount(10);
        layoutBean9.setSelectMax(1);
        mLayoutBeans8.add(layoutBean9);

        playBean8.setLayoutBeans(mLayoutBeans8);
        playBeans2.add(playBean8);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean2.setPlayBeans(playBeans2);
        mPlayTypeBeans.add(playTypeBean2);

    }

    private void initBDWData(){
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("不定位");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("三星");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("不定位");
        playBean1.setScheme("三星");
        playBean1.setPlayTypeName("前三一码");
        playBean1.setPlayTypeCode("ssc_budingwei_sanxing");
        playBean1.setBetCode("ssc_budingwei_q3ym");

        playBean1.setSampleBet("投注方案：1 ");
        playBean1.setSampleOpen("开奖号码：前三位，至少出现1个1，即中前三一码不定位。");
        playBean1.setPlayTypeExplain("从0-9中任意选择1个以上号码，每组由一个号码组成，只要开奖号码万、千、百位中包含所选号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean1.setSingleExplain("从0-9中任意选择1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("不定位");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("不定位");
        playBean2.setScheme("三星");
        playBean2.setPlayTypeName("前三二码");
        playBean2.setPlayTypeCode("ssc_budingwei_sanxing");
        playBean2.setBetCode("ssc_budingwei_q3em");

        playBean2.setSampleBet("投注方案：12 ");
        playBean2.setSampleOpen("开奖号码：前三位，至少出现1和2各一个，即中前三二码不定位。");
        playBean2.setPlayTypeExplain("从0-9中至少 选择2个以上号码，每组由2个号码组成，只要开奖号码万、千、百位中同时包含所选的2个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean2.setSingleExplain("从0-9中任意选择2个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("不定位");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        layoutBean2.setSelectMin(2);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("不定位");
        playBean3.setScheme("三星");
        playBean3.setPlayTypeName("后三一码");
        playBean3.setPlayTypeCode("ssc_budingwei_sanxing");
        playBean3.setBetCode("ssc_budingwei_h3ym");

        playBean3.setSampleBet("投注方案：1 ");
        playBean3.setSampleOpen("开奖号码：后三位至少出现1个1，即中后三一码不定位。");
        playBean3.setPlayTypeExplain("从0-9中任意选择1个以上号码，每组由一个号码组成，只要开奖号码百、十、个位中包含所选号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean3.setSingleExplain("从0-9中任意选择1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("不定位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean3);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setMainType("不定位");
        playBean4.setScheme("三星");
        playBean4.setPlayTypeName("后三二码");
        playBean4.setPlayTypeCode("ssc_budingwei_sanxing");
        playBean4.setBetCode("ssc_budingwei_h3em");

        playBean4.setSampleBet("投注方案：12 ");
        playBean4.setSampleOpen("开奖号码：后三位至少出现1和2各一个，即中后三二码不定位。");
        playBean4.setPlayTypeExplain("从0-9中至少 选择2个以上号码，每组由2个号码组成，只要开奖号码百、十、个位中同时包含所选的2个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean4.setSingleExplain("从0-9中任意选择2个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans4 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("不定位");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        layoutBean4.setSelectMin(2);
        mLayoutBeans4.add(layoutBean4);

        playBean4.setLayoutBeans(mLayoutBeans4);
        playBeans.add(playBean4);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean1.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean2 = new PlayTypeBean();
        playTypeBean2.setParentTitle("不定位");
        playTypeBean2.setTotalType("官方玩法");
        playTypeBean2.setType("四星");
        List<PlayTypeBean.PlayBean> playBeans2 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setMainType("不定位");
        playBean5.setScheme("四星");
        playBean5.setPlayTypeName("前四一码");
        playBean5.setPlayTypeCode("ssc_budingwei_sixing");
        playBean5.setBetCode("ssc_budingwei_q4ym");

        playBean5.setSampleBet("投注方案：1 ");
        playBean5.setSampleOpen("开奖号码：前四位至少出现1个1，即中前四不定位。");
        playBean5.setPlayTypeExplain("从0-9中任意选择1个以上号码，每组由一个号码组成，只要开奖号码万、千、百、十位中包含所选号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean5.setSingleExplain("从0-9中任意选择1个以上号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans5 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("不定位");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        mLayoutBeans5.add(layoutBean5);

        playBean5.setLayoutBeans(mLayoutBeans5);
        playBeans2.add(playBean5);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setMainType("不定位");
        playBean6.setScheme("四星");
        playBean6.setPlayTypeName("前四二码");
        playBean6.setPlayTypeCode("ssc_budingwei_sixing");
        playBean6.setBetCode("ssc_budingwei_q4em");

        playBean6.setSampleBet("投注方案：12 ");
        playBean6.setSampleOpen("开奖号码：前四位至少出现1和2各一个，即中前四二码不定位。");
        playBean6.setPlayTypeExplain("从0-9中至少选择2个以上号码，每组由2个号码组成，只要开奖号码万、千、百、十位中同时包含所选的2个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean6.setSingleExplain("从0-9中任意选择2个以上号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans6 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(0);
        layoutBean6.setLayoutTitle("不定位");
        layoutBean6.setShowRightMenuType(0);
        layoutBean6.setChildItemCount(10);
        layoutBean6.setSelectMin(2);
        mLayoutBeans6.add(layoutBean6);

        playBean6.setLayoutBeans(mLayoutBeans6);
        playBeans2.add(playBean6);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setMainType("不定位");
        playBean7.setScheme("四星");
        playBean7.setPlayTypeName("后四一码");
        playBean7.setPlayTypeCode("ssc_budingwei_sixing");
        playBean7.setBetCode("ssc_budingwei_h4ym");

        playBean7.setSampleBet("投注方案：1 ");
        playBean7.setSampleOpen("开奖号码：后四位至少出现1个1，即中后四不定位。");
        playBean7.setPlayTypeExplain("从0-9中任意选择1个以上号码，每组由一个号码组成，只要开奖号码千、百、十、个位中包含所选号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean7.setSingleExplain("从0-9中任意选择1个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans7 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean7 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean7.setLayoutType(0);
        layoutBean7.setLayoutTitle("不定位");
        layoutBean7.setShowRightMenuType(0);
        layoutBean7.setChildItemCount(10);
        mLayoutBeans7.add(layoutBean7);

        playBean7.setLayoutBeans(mLayoutBeans7);
        playBeans2.add(playBean7);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setMainType("不定位");
        playBean8.setScheme("四星");
        playBean8.setPlayTypeName("后四二码");
        playBean8.setPlayTypeCode("ssc_budingwei_sixing");
        playBean8.setBetCode("ssc_budingwei_h4em");

        playBean8.setSampleBet("投注方案：12 ");
        playBean8.setSampleOpen("开奖号码：后四位至少出现1和2各一个，即中后四二码不定位。");
        playBean8.setPlayTypeExplain("从0-9中至少选择2个以上号码，每组由2个号码组成，只要开奖号码千、百、十、个位中同时包含所选的2个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean8.setSingleExplain("从0-9中任意选择2个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans8 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean8 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean8.setLayoutType(0);
        layoutBean8.setLayoutTitle("不定位");
        layoutBean8.setShowRightMenuType(0);
        layoutBean8.setChildItemCount(10);
        layoutBean8.setSelectMin(2);
        mLayoutBeans8.add(layoutBean8);

        playBean8.setLayoutBeans(mLayoutBeans8);
        playBeans2.add(playBean8);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean2.setPlayBeans(playBeans2);
        mPlayTypeBeans.add(playTypeBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean playTypeBean3 = new PlayTypeBean();
        playTypeBean3.setParentTitle("不定位");
        playTypeBean3.setTotalType("官方玩法");
        playTypeBean3.setType("五星");
        List<PlayTypeBean.PlayBean> playBeans3 = new ArrayList<>();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setMainType("不定位");
        playBean9.setScheme("五星");
        playBean9.setPlayTypeName("五星二码");
        playBean9.setPlayTypeCode("ssc_budingwei_wuxing");
        playBean9.setBetCode("ssc_budingwei_wxem");

        playBean9.setSampleBet("投注方案：12 ");
        playBean9.setSampleOpen("开奖号码：至少出现1和2各一个，即中五星二码不定位。");
        playBean9.setPlayTypeExplain("从0-9中至少选择2个以上号码，每组由2个号码组成，只要开奖号码中同时包含所选的2个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean9.setSingleExplain("从0-9中任意选择2个以上号码。 ");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans9 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean9 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean9.setLayoutType(0);
        layoutBean9.setLayoutTitle("不定位");
        layoutBean9.setShowRightMenuType(0);
        layoutBean9.setChildItemCount(10);
        layoutBean9.setSelectMin(2);
        mLayoutBeans9.add(layoutBean9);

        playBean9.setLayoutBeans(mLayoutBeans9);
        playBeans3.add(playBean9);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setMainType("不定位");
        playBean10.setScheme("五星");
        playBean10.setPlayTypeName("五星三码");
        playBean10.setPlayTypeCode("ssc_budingwei_wuxing");
        playBean10.setBetCode("ssc_budingwei_wxsm");

        playBean10.setSampleBet("投注方案：123 ");
        playBean10.setSampleOpen("开奖号码：至少出现1和2及3各一个，即中五星三码不定位。");
        playBean10.setPlayTypeExplain("从0-9中至少选择3个以上号码，每组由3个号码组成，只要开奖号码中同时包含所选的3个号码，即为中奖。（同个号码出现多次只计一次中奖）");
        playBean10.setSingleExplain("从0-9中任意选择3个以上号码。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans10 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean10 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean10.setLayoutType(0);
        layoutBean10.setLayoutTitle("不定位");
        layoutBean10.setShowRightMenuType(0);
        layoutBean10.setChildItemCount(10);
        layoutBean10.setSelectMin(3);
        mLayoutBeans10.add(layoutBean10);

        playBean10.setLayoutBeans(mLayoutBeans10);
        playBeans3.add(playBean10);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean3.setPlayBeans(playBeans3);
        mPlayTypeBeans.add(playTypeBean3);
    }

}
