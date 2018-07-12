package com.dawoo.lotterybox.util.lottery.initdata;

import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-3-8.
 */

public class PK10ADataUtil {

    List<PlayTypeBean> mPlayTypeBeans = new ArrayList<>();

    private List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> mChildLayoutBeans = new ArrayList<>();

    public void initData(){
        initChildBeans();
        initDDW();
        initQY();
        initQE();
        initQS();
        String gsonString = GsonUtil.GsonString(mPlayTypeBeans);
    }

    private void initChildBeans(){
        for (int i = 1; i < 11 ; i++){
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean  = new PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean();
            if (i < 10 ){
                childLayoutBean.setNumber("0"+i);
            }else childLayoutBean.setNumber(""+i);
            mChildLayoutBeans.add(childLayoutBean);
        }
    }

    private void initDDW(){
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
        playBean1.setPlayTypeCode("pk10_yixing");
        playBean1.setBetCode("pk10_yixing_dwd");

        playBean1.setSampleBet("投注方案：冠军01 ");
        playBean1.setSampleOpen("开奖号码：01，02，03，04，05，06，07，08，09，10即中奖。");
        playBean1.setPlayTypeExplain("从冠军到第十名任意位置上，至少选择一个或一个以上的号码，每注由1个号码开组成，所选号码与开奖位置上的号码一致，即为中奖。");
        playBean1.setSingleExplain("从第一名到第十名任意位置上选择1个或1个以上号码。");
        playBean1.setRelation(false);

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("冠军");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        layoutBean1.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("亚军");
        layoutBean2.setShowRightMenuType(0);
        layoutBean2.setChildItemCount(10);
        layoutBean2.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean2);

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("季军");
        layoutBean3.setShowRightMenuType(0);
        layoutBean3.setChildItemCount(10);
        layoutBean3.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean3);

        PlayTypeBean.PlayBean.LayoutBean layoutBean4 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean4.setLayoutType(0);
        layoutBean4.setLayoutTitle("第四名");
        layoutBean4.setShowRightMenuType(0);
        layoutBean4.setChildItemCount(10);
        layoutBean4.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean4);

        PlayTypeBean.PlayBean.LayoutBean layoutBean5 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean5.setLayoutType(0);
        layoutBean5.setLayoutTitle("第五名");
        layoutBean5.setShowRightMenuType(0);
        layoutBean5.setChildItemCount(10);
        layoutBean5.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean5);

        PlayTypeBean.PlayBean.LayoutBean layoutBean6 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean6.setLayoutType(0);
        layoutBean6.setLayoutTitle("第六名");
        layoutBean6.setShowRightMenuType(0);
        layoutBean6.setChildItemCount(10);
        layoutBean6.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean6);

        PlayTypeBean.PlayBean.LayoutBean layoutBean7 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean7.setLayoutType(0);
        layoutBean7.setLayoutTitle("第七名");
        layoutBean7.setShowRightMenuType(0);
        layoutBean7.setChildItemCount(10);
        layoutBean7.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean7);

        PlayTypeBean.PlayBean.LayoutBean layoutBean8 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean8.setLayoutType(0);
        layoutBean8.setLayoutTitle("第八名");
        layoutBean8.setShowRightMenuType(0);
        layoutBean8.setChildItemCount(10);
        layoutBean8.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean8);

        PlayTypeBean.PlayBean.LayoutBean layoutBean9 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean9.setLayoutType(0);
        layoutBean9.setLayoutTitle("第九名");
        layoutBean9.setShowRightMenuType(0);
        layoutBean9.setChildItemCount(10);
        layoutBean9.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean9);

        PlayTypeBean.PlayBean.LayoutBean layoutBean10 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean10.setLayoutType(0);
        layoutBean10.setLayoutTitle("第十名");
        layoutBean10.setShowRightMenuType(0);
        layoutBean10.setChildItemCount(10);
        layoutBean10.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean10);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initQY() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("前一");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("前一");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("前一");
        playBean1.setScheme("前一");
        playBean1.setPlayTypeName("前一");
        playBean1.setPlayTypeCode("pk10_qy_zhixuan");
        playBean1.setBetCode("pk10_zhixuan_qyfs");

        playBean1.setSampleBet("投注方案：01 ");
        playBean1.setSampleOpen("开奖号码：01，02，03，04，05，06，07，08，09，10即可中前一直选。");
        playBean1.setPlayTypeExplain("从01-10中至少选择一个号码组成一注，所选号码与开奖号码第一位相同即中奖。");
        playBean1.setSingleExplain("从第一名中至少选择1个号码组成一注。");

        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("冠军");
        layoutBean1.setShowRightMenuType(0);
        layoutBean1.setChildItemCount(10);
        layoutBean1.setChildLayoutBeans(mChildLayoutBeans);
        mLayoutBeans.add(layoutBean1);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initQE() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("前二");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("前二");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("前二");
        playBean1.setScheme("前二");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("pk10_qe_zhixuan");
        playBean1.setBetCode("pk10_zhixuan_qefs");

        playBean1.setSampleBet("投注方案：冠军01 ，亚军02 ");
        playBean1.setSampleOpen("开奖号码：01，02，03，04，05，06，07，08，09，10即可中前二直选。");
        playBean1.setPlayTypeExplain("从冠军，亚军位至少各选择一个号码组成一注，开奖号码中第一，第二位与所选号按位相同，即为中奖。");
        playBean1.setSingleExplain("从第一名，第二名中至少选择1个号码组成一注。");



        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("冠军");
        layoutBean1.setShowRightMenuType(2);
        layoutBean1.setChildItemCount(10);
        layoutBean1.setChildLayoutBeans(mChildLayoutBeans);
        layoutBean1.setSelectEqual(false);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("亚军");
        layoutBean2.setShowRightMenuType(2);
        layoutBean2.setChildItemCount(10);
        layoutBean2.setChildLayoutBeans(mChildLayoutBeans);
        layoutBean2.setSelectEqual(false);
        mLayoutBeans.add(layoutBean2);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

    private void initQS() {
        PlayTypeBean playTypeBean1 = new PlayTypeBean();
        playTypeBean1.setParentTitle("前三");
        playTypeBean1.setTotalType("官方玩法");
        playTypeBean1.setType("前三");

        List<PlayTypeBean.PlayBean> playBeans = new ArrayList<>();
        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setMainType("前三");
        playBean1.setScheme("前三");
        playBean1.setPlayTypeName("直选复式");
        playBean1.setPlayTypeCode("pk10_qs_zhixuan");
        playBean1.setBetCode("pk10_zhixuan_qsfs");

        playBean1.setSampleBet("投注方案：冠军01 ，亚军02 ，季军03");
        playBean1.setSampleOpen("开奖号码：01，02，03，04，05，06，07，08，09，10即可中前三直选。");
        playBean1.setPlayTypeExplain("从冠军，亚军，季军位至少各选择一各号码组成一注，开奖号码中第一，第二，第三位与所选号按位相同，即为中奖。");
        playBean1.setSingleExplain("从第一名，第二名，第三名中至少选择1个号码组成一注。");



        List<PlayTypeBean.PlayBean.LayoutBean> mLayoutBeans = new ArrayList<>();
        PlayTypeBean.PlayBean.LayoutBean layoutBean1 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean1.setLayoutType(0);
        layoutBean1.setLayoutTitle("冠军");
        layoutBean1.setShowRightMenuType(2);
        layoutBean1.setChildItemCount(10);
        layoutBean1.setChildLayoutBeans(mChildLayoutBeans);
        layoutBean1.setSelectEqual(false);
        mLayoutBeans.add(layoutBean1);

        PlayTypeBean.PlayBean.LayoutBean layoutBean2 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean2.setLayoutType(0);
        layoutBean2.setLayoutTitle("亚军");
        layoutBean2.setShowRightMenuType(2);
        layoutBean2.setChildItemCount(10);
        layoutBean2.setChildLayoutBeans(mChildLayoutBeans);
        layoutBean2.setSelectEqual(false);
        mLayoutBeans.add(layoutBean2);

        PlayTypeBean.PlayBean.LayoutBean layoutBean3 = new PlayTypeBean.PlayBean.LayoutBean();
        layoutBean3.setLayoutType(0);
        layoutBean3.setLayoutTitle("季军");
        layoutBean3.setShowRightMenuType(2);
        layoutBean3.setChildItemCount(10);
        layoutBean3.setChildLayoutBeans(mChildLayoutBeans);
        layoutBean3.setSelectEqual(false);
        mLayoutBeans.add(layoutBean3);

        playBean1.setLayoutBeans(mLayoutBeans);
        playBeans.add(playBean1);
        playTypeBean1.setPlayBeans(playBeans);

        mPlayTypeBeans.add(playTypeBean1);
    }

}
