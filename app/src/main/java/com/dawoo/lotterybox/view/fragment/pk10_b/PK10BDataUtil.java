package com.dawoo.lotterybox.view.fragment.pk10_b;

import android.content.res.Resources;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by archar on 18-3-21.
 */

public class PK10BDataUtil {

    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    public synchronized static List<Handicap> getHandiCaps() {
        return BLotteryOddFactory.getInstance().getmHistorys();
    }

    //初始化  pk10b 数字盘的基础数据源
    public static List<PlayTypeBean.PlayBean> initPK10BSZPData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean1.setSampleBet("如现场第1个开奖号码为3号，投注冠军为3则视为中奖，其它号码视为不中奖。");
        playBean1.setSingleExplain("10");
        playBean1.setPlayTypeName(getRec().getString(R.string.gj));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean2.setSampleBet("如现场第2个开奖号码为3号，投注第二名为3则视为中奖，其它号码视为不中奖。");
        playBean2.setSingleExplain("9");
        playBean2.setPlayTypeName(getRec().getString(R.string.yj));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean3.setSampleBet("如现场第3个开奖号码为3号，投注第三名为3则视为中奖，其它号码视为不中奖。");
        playBean3.setSingleExplain("8");
        playBean3.setPlayTypeName(getRec().getString(R.string.jj));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean4.setSampleBet("如现场第4个开奖号码为3号，投注第四名为3则视为中奖，其它号码视为不中奖。");
        playBean4.setSingleExplain("7");
        playBean4.setPlayTypeName(getRec().getString(R.string.dsm));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean5.setSampleBet("如现场第5个开奖号码为3号，投注第五名为3则视为中奖，其它号码视为不中奖。");
        playBean5.setSingleExplain("6");
        playBean5.setPlayTypeName(getRec().getString(R.string.dwm));


        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean6.setSampleBet("如现场第6个开奖号码为3号，投注第六名为3则视为中奖，其它号码视为不中奖。");
        playBean6.setSingleExplain("5");
        playBean6.setPlayTypeName(getRec().getString(R.string.dlm));


        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean7.setSampleBet("如现场第7个开奖号码为3号，投注第七名为3则视为中奖，其它号码视为不中奖。");
        playBean7.setSingleExplain("7");
        playBean7.setPlayTypeName(getRec().getString(R.string.dqm));


        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean8.setSampleBet("如现场第8个开奖号码为3号，投注第八名为3则视为中奖，其它号码视为不中奖。");
        playBean8.setSingleExplain("6");
        playBean8.setPlayTypeName(getRec().getString(R.string.dbm));

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean9.setSampleBet("如现场第9个开奖号码为3号，投注第九名为3则视为中奖，其它号码视为不中奖。");
        playBean9.setSingleExplain("5");
        playBean9.setPlayTypeName(getRec().getString(R.string.djm));

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean10.setSampleBet("如现场第10个开奖号码为3号，投注第十名为3则视为中奖，其它号码视为不中奖。");
        playBean10.setSingleExplain("4");
        playBean10.setPlayTypeName(getRec().getString(R.string.dshm));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        playBeanList.add(playBean6);
        playBeanList.add(playBean7);
        playBeanList.add(playBean8);
        playBeanList.add(playBean9);
        playBeanList.add(playBean10);
        return playBeanList;
    }


    //初始化 pk10b数字盘 的布局 的数据源
    public static List<PlayDetailBean> init_PK10B_SZP_Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SZP));
            if (i < 10) {
                playDetailBean.setNum("0" + i);
            } else {
                playDetailBean.setNum(i + "");
            }
            playDetailBean.setKind(kind);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.bjpk10));
            playDetailBean.setPlayCode(getRec().getString(R.string.pk10_digital));

            if (playDetailBean.getKind().equals(getRec().getString(R.string.gj))) {
                playDetailBean.setLr(getNumByIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(0, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.champion));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.yj))) {
                playDetailBean.setLr(getNumByIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(1, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.runner_up));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.jj))) {
                playDetailBean.setLr(getNumByIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(2, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.third_runner));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dsm))) {
                playDetailBean.setLr(getNumByIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.fourth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dwm))) {
                playDetailBean.setLr(getNumByIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.fifth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dlm))) {
                playDetailBean.setLr(getNumByIndex(5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(5, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.sixth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dqm))) {
                playDetailBean.setLr(getNumByIndex(6, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(6, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.seventh_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dbm))) {
                playDetailBean.setLr(getNumByIndex(7, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(7, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.eighth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.djm))) {
                playDetailBean.setLr(getNumByIndex(8, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(8, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ninth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dshm))) {
                playDetailBean.setLr(getNumByIndex(9, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByIndex(9, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.tenth_place));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  pk10b 双面的基础数据源
    public static List<PlayTypeBean.PlayBean> initPK10BSMData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean1.setSampleBet("如现场第1个开奖号码为3号，投注冠军为3则视为中奖，其它号码视为不中奖。");
        playBean1.setPlayTypeName(getRec().getString(R.string.gj));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean2.setSampleBet("如现场第2个开奖号码为3号，投注第二名为3则视为中奖，其它号码视为不中奖。");
        playBean2.setPlayTypeName(getRec().getString(R.string.yj));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean3.setSampleBet("如现场第3个开奖号码为3号，投注第三名为3则视为中奖，其它号码视为不中奖。");
        playBean3.setPlayTypeName(getRec().getString(R.string.jj));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean4.setSampleBet("如现场第4个开奖号码为3号，投注第四名为3则视为中奖，其它号码视为不中奖。");
        playBean4.setPlayTypeName(getRec().getString(R.string.dsm));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean5.setSampleBet("如现场第5个开奖号码为3号，投注第五名为3则视为中奖，其它号码视为不中奖。");
        playBean5.setPlayTypeName(getRec().getString(R.string.dwm));


        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean6.setSampleBet("如现场第6个开奖号码为3号，投注第六名为3则视为中奖，其它号码视为不中奖。");
        playBean6.setPlayTypeName(getRec().getString(R.string.dlm));


        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean7.setSampleBet("如现场第7个开奖号码为3号，投注第七名为3则视为中奖，其它号码视为不中奖。");
        playBean7.setPlayTypeName(getRec().getString(R.string.dqm));


        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean8.setSampleBet("如现场第8个开奖号码为3号，投注第八名为3则视为中奖，其它号码视为不中奖。");
        playBean8.setPlayTypeName(getRec().getString(R.string.dbm));

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean9.setSampleBet("如现场第9个开奖号码为3号，投注第九名为3则视为中奖，其它号码视为不中奖。");
        playBean9.setPlayTypeName(getRec().getString(R.string.djm));

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双龙虎的结果为派彩依据");
        playBean10.setSampleBet("如现场第10个开奖号码为3号，投注第十名为3则视为中奖，其它号码视为不中奖。");
        playBean10.setPlayTypeName(getRec().getString(R.string.dshm));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        playBeanList.add(playBean6);
        playBeanList.add(playBean7);
        playBeanList.add(playBean8);
        playBeanList.add(playBean9);
        playBeanList.add(playBean10);
        return playBeanList;
    }

    //初始化 pk10b双面 的布局 的数据源
    public static List<PlayDetailBean> init_PK10B_SM_Data(String kind) {
        String[] arrays;

        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.gj))
                || kind.equals(getRec().getString(R.string.yj))
                || kind.equals(getRec().getString(R.string.jj))
                || kind.equals(getRec().getString(R.string.dsm))
                || kind.equals(getRec().getString(R.string.dwm))) {
            arrays = new String[]{"大", "小", "单", "双", "龙", "虎"};
        } else {
            arrays = new String[]{"大", "小", "单", "双"};
        }

        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SM));
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setKind(kind);
            playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
            playDetailBean.setCode(getRec().getString(R.string.bjpk10));
            if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pk10_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pk10_single_double));
            } else if (playDetailBean.getNum().equals("龙") || playDetailBean.getNum().equals("虎")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pk10_dragon_tiger));
            }
            if (playDetailBean.getKind().equals(getRec().getString(R.string.gj))) {
                playDetailBean.setLr(getWordNumByIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(0, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.champion));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.yj))) {
                playDetailBean.setLr(getWordNumByIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(1, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.runner_up));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.jj))) {
                playDetailBean.setLr(getWordNumByIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(2, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.third_runner));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dsm))) {
                playDetailBean.setLr(getWordNumByIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.fourth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dwm))) {
                playDetailBean.setLr(getWordNumByIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.fifth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dlm))) {
                playDetailBean.setLr(getWordNumByIndex(5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(5, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.sixth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dqm))) {
                playDetailBean.setLr(getWordNumByIndex(6, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(6, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.seventh_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dbm))) {
                playDetailBean.setLr(getWordNumByIndex(7, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(7, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.eighth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.djm))) {
                playDetailBean.setLr(getWordNumByIndex(8, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(8, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ninth_place));
            } else if (playDetailBean.getKind().equals(getRec().getString(R.string.dshm))) {
                playDetailBean.setLr(getWordNumByIndex(9, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordNumByIndex(9, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.tenth_place));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  pk10b 冠亚和的基础数据源
    public static List<PlayTypeBean.PlayBean> initPK10BGYHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与号码为派彩依据");
        playBean1.setSampleBet("如现场第1个开奖号码为3号，投注冠军为3则视为中奖，其它号码视为不中奖。");
        playBean1.setPlayTypeName(getRec().getString(R.string.gyjh));

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("冠军、亚军、季军、第四、第五、第六、第七、第八、第九、第十名出现的顺序与大小单双的关系为派彩依据");
        playBean2.setSampleBet("如现场第1个开奖号码为3号，投注冠军为单，则中奖。");
        playBean2.setPlayTypeName(getRec().getString(R.string.gyjhdsdx));

        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        return playBeanList;
    }

    //初始化 pk10b冠亚和的布局 的数据源
    public static List<PlayDetailBean> init_PK10B_GYH_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"42.0", "42.0", "20.0", "20.0",
                "13.0", "13.0", "9.0", "9.0",
                "8.64", "9.0", "9.0", "13.0",
                "13.0", "20.0", "20.0", "42.0",
                "42.0",
                "1.97", "1.97", "1.97", "1.97"};

        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.gyjh))) {
            for (int i = 3; i < 20; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.GYJ));
                playDetailBean.setKind(kind);
                playDetailBean.setOdd(arrays[i - 3]);
                playDetailBean.setNum(i + "");
                playDetailBean.setCode(getRec().getString(R.string.bjpk10));
                if (playDetailBean.getNum().equals("11")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_alone_11));
                } else if (playDetailBean.getNum().equals("3") || playDetailBean.getNum().equals("4")
                        || playDetailBean.getNum().equals("18") || playDetailBean.getNum().equals("19")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_alone_34));
                } else if (playDetailBean.getNum().equals("5") || playDetailBean.getNum().equals("6")
                        || playDetailBean.getNum().equals("16") || playDetailBean.getNum().equals("17")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_alone_56));
                } else if (playDetailBean.getNum().equals("7") || playDetailBean.getNum().equals("8")
                        || playDetailBean.getNum().equals("14") || playDetailBean.getNum().equals("15")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_alone_78));
                } else if (playDetailBean.getNum().equals("9") || playDetailBean.getNum().equals("10")
                        || playDetailBean.getNum().equals("12") || playDetailBean.getNum().equals("13")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_alone_910));
                }
                playDetailBean.setLr(getNumByCount(playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByCount(playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.champion_up_sum));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.gyjhdsdx))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.GYJ));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("和大");
                } else if (i == 1) {
                    playDetailBean.setNum("和小");
                } else if (i == 2) {
                    playDetailBean.setNum("和单");
                } else if (i == 3) {
                    playDetailBean.setNum("和双");
                }
                playDetailBean.setCode(getRec().getString(R.string.bjpk10));
                if (playDetailBean.getNum().equals("和大") || playDetailBean.getNum().equals("和小")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_big_small));
                } else if (playDetailBean.getNum().equals("和单") || playDetailBean.getNum().equals("和双")) {
                    playDetailBean.setPlayCode(getRec().getString(R.string.champion_up_single_double));
                }
                playDetailBean.setLr(getNumByCount(playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumByCount(playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.champion_up_sum));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        }

        return list;
    }

    public static List<String> getNum1_10() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            if (i == 10) {
                list.add(i + "");
            } else {
                list.add("0" + i);
            }

        }
        return list;
    }

    public static int getBgPk10(String code) {
        switch (code) {
            case "01":
                return R.mipmap.car_1;
            case "02":
                return R.mipmap.car_2;
            case "03":
                return R.mipmap.car_3;
            case "04":
                return R.mipmap.car_4;
            case "05":
                return R.mipmap.car_5;
            case "06":
                return R.mipmap.car_6;
            case "07":
                return R.mipmap.car_7;
            case "08":
                return R.mipmap.car_8;
            case "09":
                return R.mipmap.car_9;
            case "10":
                return R.mipmap.car_10;
            default:
                return 0;
        }
    }

    /**
     * 根据下标获取冷热遗漏数据
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getNumByIndex(int index, String betNum, int type) {
        if (getHandiCaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandiCaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length != 10) {
                continue;
            }
            String indexCode = openCodes[index];
            if (indexCode.equalsIgnoreCase(betNum)) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            }

            if (type == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 双面文字冷热 遗漏数据
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getWordNumByIndex(int index, String betNum, int type) {
        if (getHandiCaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandiCaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length != 10) {
                continue;
            }
            int indexCode = Integer.parseInt(openCodes[index]);
            if (betNum.equalsIgnoreCase("大")) {
                if (indexCode > 5) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("小")) {
                if (indexCode < 6) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }

            } else if (betNum.equalsIgnoreCase("单")) {
                if (indexCode % 2 == 1) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("双")) {
                if (indexCode % 2 == 0) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("龙")) {
                if (index > 4) {
                    continue;
                }
                int secCode = Integer.parseInt(openCodes[9 - index]);
                if (indexCode > secCode) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("虎")) {
                if (index > 4) {
                    continue;
                }
                int secCode = Integer.parseInt(openCodes[9 - index]);
                if (indexCode < secCode) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            }

            if (type == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 根据和  大小获取冷热 遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumByCount(String betNum, int type) {
        if (getHandiCaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandiCaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length != 10) {
                continue;
            }
            int count = Integer.valueOf(openCodes[0]) + Integer.valueOf(openCodes[1]);
            if (isNum(betNum)) {
                if (betNum.equalsIgnoreCase(String.valueOf(count))) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else {
                if (betNum.equalsIgnoreCase("和大")) {
                    if (count > 11) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("和小")) {
                    if (count < 11) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("和单")) {
                    if (count % 2 == 1) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("和双")) {
                    if (count % 2 == 0) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (type == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    private static boolean isNum(String betNum) {
        return Pattern.compile("[0-9]{1,2}").matcher(betNum).matches();
    }
}
