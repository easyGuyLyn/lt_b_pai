package com.dawoo.lotterybox.util.lottery.initdata;

import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benson on 18-3-15.
 */

public class QTDataAUtil {
    List<PlayTypeBean> mPlayTypeBeans = new ArrayList<>();

    public  void initData(){
        initDWD();
        initSanXingZhiXuan();
        initSanXingZuXuan();
        initQianErZhiXuan();
        initQianErZuXuan();
        initHouErZhiXuan();
        initHouErZuXuan();
        initBuDingWei();
        String gsonString = GsonUtil.GsonString(mPlayTypeBeans);
    }

    private void initHouErZuXuan() {
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("后二");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("后二组选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("后二");
        playBean.setScheme("后二组选");
        playBean.setPlayTypeName("组选复式");
        playBean.setPlayTypeCode("pl3_erxing_zuxuan");
        playBean.setBetCode("pl3_erxing_zuxuan_hefs");

        playBean.setSampleBet("投注方案：58");
        playBean.setSampleOpen("开奖号码：后二位58，即中后二星组选");
        playBean.setPlayTypeExplain("从0-9中选2个号码组成一注，所选号码与开奖号码的十位、个位相同，顺序不限（不含对子号），即为中奖。");
        playBean.setSingleExplain("从0-9中任意选择2个或2个以上号码。 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("组选");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        layoutBean4.setSelectMin(2);
        mLayoutBeans.add(layoutBean4);

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("后二");
        playBean2.setScheme("后二组选");
        playBean2.setPlayTypeName("组选单式");
        playBean2.setPlayTypeCode("pl3_erxing_zuxuan");
        playBean2.setBetCode("pl3_erxing_zuxuan_heds");

        playBean2.setSampleBet("投注方案：58");
        playBean2.setSampleOpen("开奖号码：前二位58，即中后二组选");
        playBean2.setPlayTypeExplain("手动输入一个2位数号码组成一注，所选号码的十位、个位与开奖号码的后两位相同，且数字不同，顺序不限，即为中奖。");
        playBean2.setSingleExplain("至少输入1个两位数号码。十位与个位的数字应不同。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(2);
        layoutBean2.setSelectMin(2);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }

    private void initHouErZhiXuan() {
        //后二直选
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("后二");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("后二直选");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("后二");
        playBean.setScheme("后二直选");
        playBean.setPlayTypeName("直选复式");
        playBean.setPlayTypeCode("pl3_erxing_zhixuan");
        playBean.setBetCode("pl3_erxing_zhixuan_hefs");

        playBean.setSampleBet("投注方案：58");
        playBean.setSampleOpen("开奖号码：后二位58，即中后二星直选");
        playBean.setPlayTypeExplain("从十位、个位中选择一个2位数号码组成一注，所选号码与开奖号码的十位、个位相同，且顺序一致，即为中奖。");
        playBean.setSingleExplain("从十、个位各选一个号码组成一注 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();

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

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("后二");
        playBean2.setScheme("后二直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("pl3_erxing_zhixuan");
        playBean2.setBetCode("pl3_erxing_zhixuan_heds");

        playBean2.setSampleBet("投注方案：58");
        playBean2.setSampleOpen("开奖号码：前二位58，即中前二星直选");
        playBean2.setPlayTypeExplain("手动输入号码，至少输入1个2位数号码组成一注，所输入号码与开奖号码的前2位相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("输入2个有效号码组成1注");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(2);
        layoutBean2.setSelectMin(2);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }

    private void initQianErZuXuan() {
        //前二组选
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("前二");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("前二组选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("前二");
        playBean.setScheme("前二组选");
        playBean.setPlayTypeName("组选复式");
        playBean.setPlayTypeCode("pl3_erxing_zuxuan");
        playBean.setBetCode("pl3_erxing_zuxuan_qefs");

        playBean.setSampleBet("投注方案：58");
        playBean.setSampleOpen("开奖号码：前二位58，即中前二星组选");
        playBean.setPlayTypeExplain("从0-9中选2个号码组成一注，所选号码与开奖号码的百位、十位相同，顺序不限（不含对子号），即为中奖。");
        playBean.setSingleExplain("从0-9中任意选择2个或2个以上号码。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("组选");
        layoutBean3.setSelectMin(2);
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("二星");
        playBean2.setScheme("前二组选");
        playBean2.setPlayTypeName("组选单式");
        playBean2.setPlayTypeCode("pl3_erxing_zuxuan");
        playBean2.setBetCode("pl3_erxing_zuxuan_qeds");

        playBean2.setSampleBet("投注方案：58");
        playBean2.setSampleOpen("开奖号码：前二位58，即中前二星组选");
        playBean2.setPlayTypeExplain("手动输入2个号码组成一注，所输入的号码与当期中奖号码的前2个号码相同，顺序不限，即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个两位数号码组成一注。两位数字应不同。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2= new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(2);
        layoutBean2.setSelectMin(2);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }

    private void initQianErZhiXuan() {
        //前二直选
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("前二");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("前二直选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("前二");
        playBean.setScheme("前二直选");
        playBean.setPlayTypeName("直选复式");
        playBean.setPlayTypeCode("pl3_erxing_zhixuan");
        playBean.setBetCode("pl3_erxing_zhixuan_qefs");

        playBean.setSampleBet("投注方案：58");
        playBean.setSampleOpen("开奖号码：前二位58，即中前二星直选");
        playBean.setPlayTypeExplain("从百位、十位中选择一个2位数号码组成一注，所选号码与开奖号码的前2位相同，且顺序一致，即为中奖。");
        playBean.setSingleExplain("竞猜开奖号码的前两位");
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

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("前二");
        playBean2.setScheme("前二直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("pl3_erxing_zhixuan");
        playBean2.setBetCode("pl3_erxing_zhixuan_qeds");

        playBean2.setSampleBet("投注方案：58");
        playBean2.setSampleOpen("开奖号码：前二位58，即中前二星直选");
        playBean2.setPlayTypeExplain("手动输入号码，至少输入1个2位数号码组成一注，所输入号码与开奖号码的前2位相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("输入2个有效号码组成1注");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2= new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(2);
        layoutBean2.setSelectMin(2);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }

    private void initSanXingZuXuan() {
        //三星组选
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("三星");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("组选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("三星");
        playBean.setScheme("组选");
        playBean.setPlayTypeName("组三复式");
        playBean.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean.setBetCode("pl3_sanxing_zuxuan_z3fs");

        playBean.setSampleBet("投注方案：588");
        playBean.setSampleOpen("开奖号码：588(顺序不限)，即中组三。");
        playBean.setPlayTypeExplain("从0-9中选择2个数字组成两注，且所选号码与开奖号码(三个数字当中有二个数字相同)的百位、十位、个位相同，顺序不限，即为中奖。");
        playBean.setSingleExplain("从0-9中任意选择2个或2个以上号码。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setSelectMin(2);
        layoutBean1.setLayoutTitle("组三");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("三星");
        playBean2.setScheme("组选");
        playBean2.setPlayTypeName("组三单式");
        playBean2.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean2.setBetCode("pl3_sanxing_zuxuan_z3ds");

        playBean2.setSampleBet("投注方案：588");
        playBean2.setSampleOpen("开奖号码：588(顺序不限)，即中组三。");
        playBean2.setPlayTypeExplain("手动输入组三号码，3个数字为一注，所选号码与开奖号码(三个数字当中有二个数字相同)的百位、十位、个位相同，顺序不限，即为中奖");
        playBean2.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字中必须有二个数字相同）。 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean12 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean12.setLayoutType(2);
        layoutBean12.setSelectMin(3);
        mLayoutBeans2.add(layoutBean12);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("三星");
        playBean3.setScheme("组选");
        playBean3.setPlayTypeName("组六复式");
        playBean3.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean3.setBetCode("pl3_sanxing_zuxuan_z6fs");

        playBean3.setSampleBet("投注方案：563");
        playBean3.setSampleOpen("开奖号码：563(顺序不限)，即中组六。");
        playBean3.setPlayTypeExplain("从0-9中任意选择3个号码组成一注，所选号码与开奖号码(三个数字全不相同)的百位、十位、个位相同，顺序不限，即为中奖。");
        playBean3.setSingleExplain("从0-9中任意选择3个或3个以上号码。 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean13 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean13.setLayoutType(0);
        layoutBean13.setSelectMin(3);
        layoutBean13.setLayoutTitle("组六");
        layoutBean13.setShowRightMenuType(0);
        layoutBean13.setChildItemCount(10);
        mLayoutBeans3.add(layoutBean13);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setMainType("三星");
        playBean4.setScheme("组选");
        playBean4.setPlayTypeName("组六单式");
        playBean4.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean4.setBetCode("pl3_sanxing_zuxuan_z6ds");

        playBean4.setSampleBet("投注方案：563");
        playBean4.setSampleOpen("开奖号码：563(顺序不限)，即中组六。");
        playBean4.setPlayTypeExplain("手动输入组六号码，3个数字为一注，所选号码与开奖号码(三个数字全不相同)的百位、十位、个位相同，顺序不限，即为中奖");
        playBean4.setSingleExplain("手动输入号码，至少输入1个三位数号码（三个数字完全不相同）。 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans4 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean14 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean14.setLayoutType(2);
        layoutBean14.setSelectMin(3);
        mLayoutBeans4.add(layoutBean14);

        playBean4.setLayoutBeans(mLayoutBeans4);
        playBeans.add(playBean4);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setMainType("三星");
        playBean5.setScheme("组选");
        playBean5.setPlayTypeName("混合组选");
        playBean5.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean5.setBetCode("pl3_sanxing_zuxuan_hhzx");

        playBean5.setSampleBet("投注方案：563");
        playBean5.setSampleOpen("开奖号码：563(顺序不限)，即中组六。");
        playBean5.setPlayTypeExplain("手动输入组三或组六号码，3个数字为一注，所选号码与开奖号码的百位、十位、个位相同，顺序不限，即为中奖。");
        playBean5.setSingleExplain("手动输入号码，至少输入1个三位数号码(不包含豹子号)。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans5 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean15 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean15.setLayoutType(2);
        layoutBean15.setSelectMin(3);
        mLayoutBeans5.add(layoutBean15);

        playBean5.setLayoutBeans(mLayoutBeans5);
        playBeans.add(playBean5);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setMainType("三星");
        playBean6.setScheme("组选");
        playBean6.setPlayTypeName("组选和值");
        playBean6.setPlayTypeCode("pl3_sanxing_zuxuan");
        playBean6.setBetCode("pl3_sanxing_zuxuan_zxhz");

        playBean6.setSampleBet("投注方案：21");
        playBean6.setSampleOpen("开奖号码：588(和值21)，即中组选和值。");
        playBean6.setPlayTypeExplain("开奖号码为组三或组六形态，所选数值等于开奖号码的百位、十位、个位三个数字相加之和，即为中奖。");
        playBean6.setSingleExplain("从1-26中至少选择1个号码。 ");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans6 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean16 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean16.setLayoutType(0);
        layoutBean16.setLayoutTitle("和值");
        layoutBean16.setShowRightMenuType(1);
        layoutBean16.setStartNumber(1);
        layoutBean16.setChildItemCount(26);
        layoutBean16.setSplitCharacter("|");
        mLayoutBeans6.add(layoutBean16);

        playBean6.setLayoutBeans(mLayoutBeans6);
        playBeans.add(playBean6);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }

    private void initSanXingZhiXuan() {
        //三星直选
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("三星");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("直选");
        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("三星");
        playBean.setScheme("直选");
        playBean.setPlayTypeName("直选复式");
        playBean.setPlayTypeCode("pl3_sanxing_zhixuan");
        playBean.setBetCode("pl3_sanxing_zhixuan_fs");

        playBean.setSampleBet("投注方案：345");
        playBean.setSampleOpen("开奖号码：345，即中三星直选。");
        playBean.setPlayTypeExplain("从百位、十位、个位中选择一个3位数号码组成一注，所选号码与开奖号码的百位、十位、个位相同，且顺序一致，即为中奖");
        playBean.setSingleExplain("竞猜全部的开奖号码");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("百位");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        mLayoutBeans.add(layoutBean1);

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

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setMainType("三星");
        playBean2.setScheme("直选");
        playBean2.setPlayTypeName("直选单式");
        playBean2.setPlayTypeCode("pl3_sanxing_zhixuan");
        playBean2.setBetCode("pl3_sanxing_zhixuan_ds");

        playBean2.setSampleBet("投注方案：345");
        playBean2.setSampleOpen("开奖号码：345，即中三星直选。");
        playBean2.setPlayTypeExplain("手动输入一个3位数号码组成一注，所选号码的百位、十位、个位与开奖号码相同，且顺序一致，即为中奖。");
        playBean2.setSingleExplain("手动输入号码，至少输入1个3位数号码组成一注。");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans2 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(2);
        layoutBean2.setSelectMin(3);
        mLayoutBeans2.add(layoutBean2);

        playBean2.setLayoutBeans(mLayoutBeans2);
        playBeans.add(playBean2);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setMainType("三星");
        playBean3.setScheme("直选");
        playBean3.setPlayTypeName("直选和值");
        playBean3.setPlayTypeCode("pl3_sanxing_zhixuan");
        playBean3.setBetCode("pl3_sanxing_zhixuan_hz");

        playBean3.setSampleBet("投注方案：和值1");
        playBean3.setSampleOpen("开奖号码：开奖号码 001、010、100，即中三星直选和值。");
        playBean3.setPlayTypeExplain("从0-27中任意选择1个或1个以上号码，所选数值等于开奖号码的百位、十位、个位相加之和，即为中奖");
        playBean3.setSingleExplain("竞猜全部开奖号码的和值");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans3 = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("直选和值");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(28);
        layoutBean3.setSplitCharacter("|");
        mLayoutBeans3.add(layoutBean3);

        playBean3.setLayoutBeans(mLayoutBeans3);
        playBeans.add(playBean3);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        playTypeBean.setPlayBeans(playBeans);
        mPlayTypeBeans.add(playTypeBean);
    }


    private void initDWD() {
        //定胆位
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("定胆位");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("定位胆");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("定胆位");
        playBean.setScheme("定胆位");
        playBean.setPlayTypeName("定胆位");
        playBean.setPlayTypeCode("pl3_yixing");
        playBean.setBetCode("pl3_yixing_dwd");

        playBean.setSampleBet("投注方案：百位1");
        playBean.setSampleOpen("开奖号码：百位1，即中定位胆百位");
        playBean.setPlayTypeExplain("从百、十、个位任意位置上至少选择一个以上号码，所选号码与相同位置上的开奖号码一致，即为中奖。");
        playBean.setSingleExplain("至少选择1个号码");
        playBean.setRelation(false);
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

        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);
        playTypeBean.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean);
    }

    private void initBuDingWei() {
        //三星不定位
        PlayTypeBean playTypeBean = new PlayTypeBean();
        playTypeBean.setParentTitle("不定位");
        playTypeBean.setTotalType("官方玩法");
        playTypeBean.setType("不定位");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
        playBean.setMainType("不定位");
        playBean.setScheme("不定位");
        playBean.setPlayTypeName("三星一码");
        playBean.setPlayTypeCode("pl3_budingwei_sanxing");
        playBean.setBetCode("pl3_budingwei_sxym");

        playBean.setSampleBet("投注方案：1");
        playBean.setSampleOpen("开奖号码：开奖号码至少出现1个1，即中三星一码不定位。");
        playBean.setPlayTypeExplain("从0-9中选择1个号码，每注由1号码组成，只要开奖号码的百位、十位、个位中包含所选号码，即为中奖");
        playBean.setSingleExplain("至少选择1个号码");
        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("不定位");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        mLayoutBeans.add(layoutBean3);


        playBean.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean);
        playTypeBean.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean);
    }


}
