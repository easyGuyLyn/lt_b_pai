package com.dawoo.lotterybox.view.fragment.qt;

import android.content.res.Resources;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by archar on 18-3-21.
 */

public class QTBDataUtil {
    public static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    public synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    private synchronized static List<Handicap> getHandicap() {
        return BLotteryOddFactory.getInstance().getmHistorys();
    }

    //初始化  一字定位的数据源
    public static List<PlayTypeBean.PlayBean> initYZDWData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("取百位为基准，自0~9、大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean3.setSampleBet("百位选了9，开奖号码百位是9，就中奖了");
        playBean3.setSingleExplain("9");
        playBean3.setPlayTypeName(getRec().getString(R.string.bw));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("取十位为基准，自0~9、大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean4.setSampleBet("十位选了9，开奖号码十位是9，就中奖了");
        playBean4.setSingleExplain("9");
        playBean4.setPlayTypeName(getRec().getString(R.string.sw));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("取个位为基准，自0~9、大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean5.setSampleBet("个位选了9，开奖号码个位是9，就中奖了");
        playBean5.setSingleExplain("9");
        playBean5.setPlayTypeName(getRec().getString(R.string.gw));


        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        return playBeanList;
    }

    //初始化 一字定位 0-9 大小单双质和 的布局 的数据源
    public static List<PlayDetailBean> init_YZDW_0_9_DXDSZH_Data(String kind, String childType) {
        String[] arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "大", "小", "单", "双", "质", "合"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.YZDW));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            if (i >= 10) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
            } else {
                playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            }
            playDetailBean.setCode(getRec().getString(R.string.qt));


            if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_one_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_one_single_double));
            } else if (playDetailBean.getNum().equals("质") || playDetailBean.getNum().equals("合")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_one_prime_combined));
            } else {
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_one_digital));
            }

            if (kind.equals(getRec().getString(R.string.bw))) {
                // 百定位
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_hundred));
                playDetailBean.setLr(getNumBYIndex(0, playDetailBean.getNum()));
            } else if (kind.equals(getRec().getString(R.string.sw))) {
                // 十定位
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_ten));
                playDetailBean.setLr(getNumBYIndex(1, playDetailBean.getNum()));
            } else if (kind.equals(getRec().getString(R.string.gw))) {
                // 个定位
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_one));
                playDetailBean.setLr(getNumBYIndex(2, playDetailBean.getNum()));
            }

            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  二字定位的数据源
    public static List<PlayTypeBean.PlayBean> initRZDWData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean3.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean3.setSingleExplain("0,9");
        playBean3.setPlayTypeName(getRec().getString(R.string.bs));

        PlayTypeBean.PlayBean playBean31 = new PlayTypeBean.PlayBean();
        playBean31.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean31.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean31.setSingleExplain("0,9");
        playBean31.setPlayTypeName(getRec().getString(R.string.bg));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean4.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean4.setSingleExplain("0,9");
        playBean4.setPlayTypeName(getRec().getString(R.string.sg));


        playBeanList.add(playBean3);
        playBeanList.add(playBean31);
        playBeanList.add(playBean4);
        return playBeanList;
    }


    //初始化 二字定位 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_RZDW_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.RZDW));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_97));
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_two_digital));

            if (kind.equals(getRec().getString(R.string.bs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_hundred_ten_中2));
            } else if (kind.equals(getRec().getString(R.string.bg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_hundred_one_中2));
            } else if (kind.equals(getRec().getString(R.string.sg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_ten_one_中2));
            }
            if (childType.equalsIgnoreCase("百")) {
                playDetailBean.setLr(getNumBYIndex(0, playDetailBean.getNum()));
            } else if (childType.equalsIgnoreCase("十")) {
                playDetailBean.setLr(getNumBYIndex(1, playDetailBean.getNum()));
            } else if (childType.equalsIgnoreCase("个")) {
                playDetailBean.setLr(getNumBYIndex(1, playDetailBean.getNum()));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":中2");
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  三字定位的数据源
    public static List<PlayTypeBean.PlayBean> initSZDWData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean10.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean10.setSingleExplain("0,9,2");
        playBean10.setPlayTypeName(getRec().getString(R.string.bsg));

        playBeanList.add(playBean10);
        return playBeanList;
    }


    //初始化 三字定位 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_SZDW_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SZDW));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_840));
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_three_digital));

            if (kind.equals(getRec().getString(R.string.bsg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_hundred_ten_one_中3));
            }
            if (childType.equalsIgnoreCase("百")) {
                playDetailBean.setLr(getNumBYIndex(0, playDetailBean.getNum()));
            } else if (childType.equalsIgnoreCase("十")) {
                playDetailBean.setLr(getNumBYIndex(1, playDetailBean.getNum()));
            } else if (childType.equalsIgnoreCase("个")) {
                playDetailBean.setLr(getNumBYIndex(1, playDetailBean.getNum()));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":中3");
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  一字组合的数据源
    public static List<PlayTypeBean.PlayBean> initYZZHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("0~9任选1个号进行投注，当开奖结果[百位、十位、个位]任一数与所选的号码相同时，即为中奖。同个号码出现多次时只计一次中奖");
        playBean1.setSampleBet("下注一字【5号】＄100，一字賠率2.404,五颗球开出9，5，8, 派彩为＄240.4");
        playBean1.setSingleExplain("1");
        playBean1.setPlayTypeName(getRec().getString(R.string.zh_yzzh));


        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 一字组合 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_YZZH_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.zh_yzzh));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_one_combination));
            if (kind.equals(getRec().getString(R.string.zh_yzzh))) {
                playDetailBean.setOdd("3.5");
                playDetailBean.setBetCode(getRec().getString(R.string.pl3_all_three));
            }
            playDetailBean.setLr(getNumBYGroup(playDetailBean.getNum()));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  二字组合的数据源
    public static List<PlayTypeBean.PlayBean> initRZZHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("在该位任选一个号码");
        playBean3.setSampleBet("投注者购买二字组合，选择2个相同号码如为11，当期开奖结果如为11x、1x1、x11、皆视为中奖。（x=0~9任一数）;投注者购买二字组合，选择2个不同号码如为12，当期开奖结果如为12x、1x2、21x、2x1、x12、x21皆视为中奖。（x=0~9任一数）");
        playBean3.setSingleExplain("0");
        playBean3.setPlayTypeName(getRec().getString(R.string.zh_dyq));

        PlayTypeBean.PlayBean playBean31 = new PlayTypeBean.PlayBean();
        playBean31.setPlayTypeExplain("在该位任选一个号码");
        playBean31.setSampleBet("投注者购买二字组合，选择2个相同号码如为11，当期开奖结果如为11x、1x1、x11、皆视为中奖。（x=0~9任一数）;投注者购买二字组合，选择2个不同号码如为12，当期开奖结果如为12x、1x2、21x、2x1、x12、x21皆视为中奖。（x=0~9任一数）");
        playBean31.setSingleExplain("0");
        playBean31.setPlayTypeName(getRec().getString(R.string.zh_drq));


        playBeanList.add(playBean3);
        playBeanList.add(playBean31);
        return playBeanList;
    }

    //初始化 二字组合 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_RZZH_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.zh_rzzh));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_two_combination));
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  三字组合的数据源
    public static List<PlayTypeBean.PlayBean> initSZZHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("在该位任选一个号码");
        playBean3.setSampleBet("投注者购买三字组合，选择号码为111，当期开奖结果如为111则视为中奖；投注者购买三字组合，选择号码为112，当期开奖结果如为112、121、211皆视为中奖；自0~9号任选3个号且3个号都不同时（如123），当开奖结果与所选号码相同但顺序不同时，即为中奖");
        playBean3.setSingleExplain("0");
        playBean3.setPlayTypeName(getRec().getString(R.string.zh_dyq));

        PlayTypeBean.PlayBean playBean31 = new PlayTypeBean.PlayBean();
        playBean31.setPlayTypeExplain("在该位任选一个号码");
        playBean31.setSampleBet("投注者购买三字组合，选择号码为111，当期开奖结果如为111则视为中奖；投注者购买三字组合，选择号码为112，当期开奖结果如为112、121、211皆视为中奖；自0~9号任选3个号且3个号都不同时（如123），当开奖结果与所选号码相同但顺序不同时，即为中奖");
        playBean31.setSingleExplain("0");
        playBean31.setPlayTypeName(getRec().getString(R.string.zh_drq));

        PlayTypeBean.PlayBean playBean32 = new PlayTypeBean.PlayBean();
        playBean32.setPlayTypeExplain("在该位任选一个号码");
        playBean32.setSampleBet("投注者购买三字组合，选择号码为111，当期开奖结果如为111则视为中奖；投注者购买三字组合，选择号码为112，当期开奖结果如为112、121、211皆视为中奖；自0~9号任选3个号且3个号都不同时（如123），当开奖结果与所选号码相同但顺序不同时，即为中奖");
        playBean32.setSingleExplain("0");
        playBean32.setPlayTypeName(getRec().getString(R.string.zh_dsq));

        playBeanList.add(playBean3);
        playBeanList.add(playBean31);
        playBeanList.add(playBean32);
        return playBeanList;
    }

    //初始化 三字组合 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_SZZH_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.zh_szzh));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_three_combination));
            playDetailBean.setLr(getNumBYGroup(playDetailBean.getNum()));
            list.add(playDetailBean);
        }
        return list;
    }


    //百十和数 的kind
    public static List<PlayTypeBean.PlayBean> initBSHSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖结果百十位数的总和值与若投注百十位数字的总和值，即为中奖。");
        playBean1.setSampleBet("投注者购买和数1，当期开奖结果如为001、010、100此三种和皆为1，或者买了单，则视为中奖。");
        playBean1.setSingleExplain("1");
        playBean1.setPlayTypeName(getRec().getString(R.string.bshs));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("开奖结果单双相同时，即为中奖。");
        playBean11.setSampleBet("当期开奖结果如为001、010、100,买了单，则视为中奖。");
        playBean11.setSingleExplain("单");
        playBean11.setPlayTypeName(getRec().getString(R.string.bshssm));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("开奖结果百十二位数的总和值尾数若与投注百十二位数的总和值尾数相同或者大小质合吻合时，即为中奖。 ");
        playBean2.setSampleBet("投注者购买百十和尾数4，当期开奖结果如为138、225、317此三种和的尾数皆为4，则视为中奖。");
        playBean2.setSingleExplain("4");
        playBean2.setPlayTypeName(getRec().getString(R.string.bshsws));

        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("开奖结果百十二位数的总和值与大小质合吻合时，即为中奖。 ");
        playBean22.setSampleBet("投注者购买百十和尾数大，当期开奖结果如为578、485、377此三种和的尾数皆为大，则视为中奖。");
        playBean22.setSingleExplain("大");
        playBean22.setPlayTypeName(getRec().getString(R.string.bshswssm));


        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean2);
        playBeanList.add(playBean22);
        return playBeanList;
    }

    //百十和数 的数据源
    public static List<PlayDetailBean> init_BSHS_Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.bshs))) {
            for (int i = 0; i < 19; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bshs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_digital));
                playDetailBean.setBetCode("pl3_hundred_ten_sum");
                playDetailBean.setLr(getNumBySum(new int[]{0, 1}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bshssm))) {
            for (int i = 0; i < 2; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bshs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("单");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_hundred_ten_sum");
                } else if (i == 1) {
                    playDetailBean.setNum("双");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_hundred_ten_sum");
                }
                playDetailBean.setLr(getNumBySum(new int[]{0, 1}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bshsws))) {
            for (int i = 0; i < 10; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bshsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "尾");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa));
                playDetailBean.setBetCode("pl3_hundred_ten_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 1}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bshswssm))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bshsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("尾大");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 1) {
                    playDetailBean.setNum("尾小");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 2) {
                    playDetailBean.setNum("尾质");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                } else if (i == 3) {
                    playDetailBean.setNum("尾合");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                }
                playDetailBean.setBetCode("pl3_hundred_ten_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 1}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        }
        return list;
    }


    //百个和数 的kind
    public static List<PlayTypeBean.PlayBean> initBGHSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖结果百个位数的总和值与若投注百个位数字的总和值或者单双相同时，即为中奖。");
        playBean1.setSampleBet("投注者购买和数1，当期开奖结果如为001、010、100此三种和皆为1，则视为中奖。");
        playBean1.setSingleExplain("1");
        playBean1.setPlayTypeName(getRec().getString(R.string.bghs));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("开奖结果百个位数的总和值与所投注单双相同时，即为中奖。");
        playBean11.setSampleBet("投注者购买单，当期开奖结果如为001、110、100此三种和皆为单，则视为中奖。");
        playBean11.setSingleExplain("单");
        playBean11.setPlayTypeName(getRec().getString(R.string.bghssm));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("开奖结果百个二位数的总和值尾数若与投注百个二位数的总和值尾数相同或者大小质合吻合时，即为中奖。 ");
        playBean2.setSampleBet("投注者购买百个和尾数4，当期开奖结果如为133、222、311此三种和的尾数皆为4，则视为中奖。");
        playBean2.setSingleExplain("4");
        playBean2.setPlayTypeName(getRec().getString(R.string.bghsws));

        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("开奖结果百个二位数的总和值与大小质合吻合时，即为中奖。 ");
        playBean22.setSampleBet("投注者购买百个和尾数大，当期开奖结果如为578、985、677此三种和的尾数皆为大，则视为中奖。");
        playBean22.setSingleExplain("大");
        playBean22.setPlayTypeName(getRec().getString(R.string.bghswssm));

        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean2);
        playBeanList.add(playBean22);
        return playBeanList;
    }

    //百个和数 的数据源
    public static List<PlayDetailBean> init_BGHS_Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.bghs))) {
            for (int i = 0; i < 19; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_digital));
                playDetailBean.setBetCode("pl3_hundred_one_sum");
                playDetailBean.setLr(getNumBySum(new int[]{0, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bghssm))) {
            for (int i = 0; i < 2; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("单");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_hundred_one_sum");
                } else if (i == 1) {
                    playDetailBean.setNum("双");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_hundred_one_sum");
                }
                playDetailBean.setLr(getNumBySum(new int[]{0, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bghsws))) {
            for (int i = 0; i < 10; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "尾");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa));
                playDetailBean.setBetCode("pl3_hundred_one_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum() );
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 2}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bghswssm))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("尾大");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 1) {
                    playDetailBean.setNum("尾小");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 2) {
                    playDetailBean.setNum("尾质");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                } else if (i == 3) {
                    playDetailBean.setNum("尾合");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                }
                playDetailBean.setBetCode("pl3_hundred_one_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 2}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        }
        return list;
    }

    //十ge和数 的kind
    public static List<PlayTypeBean.PlayBean> initSGHSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖结果十个位数的总和值与若投注十个位数字的总和值或者单双相同时，即为中奖。");
        playBean1.setSampleBet("投注者购买和数1，当期开奖结果如为001、010、100此三种和皆为1，则视为中奖。");
        playBean1.setSingleExplain("1");
        playBean1.setPlayTypeName(getRec().getString(R.string.sghs));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("开奖结果十个位数的总和值与所投注单双相同时，即为中奖。");
        playBean11.setSampleBet("投注者购买单，当期开奖结果如为001、110、101此三种和皆为单，则视为中奖。");
        playBean11.setSingleExplain("单");
        playBean11.setPlayTypeName(getRec().getString(R.string.sghssm));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("开奖结果十个二位数的总和值尾数若与投注十个二位数的总和值尾数相同或者大小质合吻合时，即为中奖。 ");
        playBean2.setSampleBet("投注者购买十个和尾数4，当期开奖结果如为131、222、313此三种和的尾数皆为4，则视为中奖。");
        playBean2.setSingleExplain("4");
        playBean2.setPlayTypeName(getRec().getString(R.string.sghsws));

        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("开奖结果十个二位数的总和值与大小质合吻合时，即为中奖。 ");
        playBean22.setSampleBet("投注者购买十个和尾数大，当期开奖结果如为578、987、677此三种和的尾数皆为大，则视为中奖。");
        playBean22.setSingleExplain("大");
        playBean22.setPlayTypeName(getRec().getString(R.string.sghswssm));

        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean2);
        playBeanList.add(playBean22);
        return playBeanList;
    }

    //十ge和数 的数据源
    public static List<PlayDetailBean> init_SGHS_Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.sghs))) {
            for (int i = 0; i < 19; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.sghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_digital));
                playDetailBean.setBetCode("pl3_ten_one_sum");
                playDetailBean.setLr(getNumBySum(new int[]{1, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.sghssm))) {
            for (int i = 0; i < 2; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.sghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("单");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_ten_one_sum");
                } else if (i == 1) {
                    playDetailBean.setNum("双");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_single_double));
                    playDetailBean.setBetCode("pl3_ten_one_sum");
                }
                playDetailBean.setLr(getNumBySum(new int[]{1, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.sghsws))) {
            for (int i = 0; i < 10; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.sghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "尾");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa));
                playDetailBean.setBetCode("pl3_ten_one_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{1, 2}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.sghswssm))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.sghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("尾大");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 1) {
                    playDetailBean.setNum("尾小");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_big_small));
                } else if (i == 2) {
                    playDetailBean.setNum("尾质");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                } else if (i == 3) {
                    playDetailBean.setNum("尾合");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum2_mantissa_prime_combined));
                }
                playDetailBean.setBetCode("pl3_ten_one_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{1, 2}, playDetailBean.getNum()));
                list.add(playDetailBean);
            }
        }
        return list;
    }


    //bai十ge和数 的kind
    public static List<PlayTypeBean.PlayBean> initBSGHSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖结果百十个三位数的总和值与若投注百十个位数字的总和值相同时，即为中奖。 ");
        playBean1.setSampleBet("投注者购买和数1，当期开奖结果如为001、010、100此三种和皆为1，则视为中奖。");
        playBean1.setSingleExplain("1");
        playBean1.setPlayTypeName(getRec().getString(R.string.bsghs));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("开奖结果百十个位数的总和值与所投注单双相同时，即为中奖。");
        playBean11.setSampleBet("投注者购买单，当期开奖结果如为001、100、001此三种和皆为单，则视为中奖。");
        playBean11.setSingleExplain("单");
        playBean11.setPlayTypeName(getRec().getString(R.string.bsghssm));

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("开奖结果百十个三位数的总和值的个位数字与若投注百十个位数字的总和值的个位数相同时，即为中奖。");
        playBean2.setSampleBet("投注者购买百十个和尾数3，当期开奖结果如为373、490、968此三种和的尾数皆为3，则视为中奖。");
        playBean2.setSingleExplain("3");
        playBean2.setPlayTypeName(getRec().getString(R.string.bsghsws));

        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("开奖结果百十个二位数的总和值与大小质合吻合时，即为中奖。 ");
        playBean22.setSampleBet("投注者购买百十个和尾数大，当期开奖结果如为578、985、677此三种和的尾数皆为大，则视为中奖。");
        playBean22.setSingleExplain("大");
        playBean22.setPlayTypeName(getRec().getString(R.string.bsghswssm));

        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean2);
        playBeanList.add(playBean22);
        return playBeanList;
    }

    //bai十ge和数 的数据源
    public static List<PlayDetailBean> init_BSGHS_Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        if (kind.equals(getRec().getString(R.string.bsghs))) {
            for (int i = 0; i < 28; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bsghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_digital));
                playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                playDetailBean.setLr(getNumBySum(new int[]{0, 1, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bsghssm))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bsghs));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("单");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_single_double));
                    playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                } else if (i == 1) {
                    playDetailBean.setNum("双");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_single_double));
                    playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                }else if (i == 2) {
                    playDetailBean.setNum("大");
                    playDetailBean.setPlayCode("pl3_sum3_big_small");
                    playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                }else if (i == 3) {
                    playDetailBean.setNum("小");
                    playDetailBean.setPlayCode("pl3_sum3_big_small");
                    playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                }
                playDetailBean.setLr(getNumBySum(new int[]{0, 1, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }

        } else if (kind.equals(getRec().getString(R.string.bsghsws))) {
            for (int i = 0; i < 10; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bsghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                playDetailBean.setNum(i + "尾");
                playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_mantissa));
                playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 1, 2}, playDetailBean.getNum()));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        } else if (kind.equals(getRec().getString(R.string.bsghswssm))) {
            for (int i = 0; i < 4; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(getRec().getString(R.string.bsghsws));
                playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
                playDetailBean.setKind(kind);
                if (i == 0) {
                    playDetailBean.setNum("尾大");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_mantissa_big_small));
                } else if (i == 1) {
                    playDetailBean.setNum("尾小");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_mantissa_big_small));
                } else if (i == 2) {
                    playDetailBean.setNum("尾质");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_mantissa_prime_combined));
                } else if (i == 3) {
                    playDetailBean.setNum("尾合");
                    playDetailBean.setPlayCode(getRec().getString(R.string.pl3_sum3_mantissa_prime_combined));
                }
                playDetailBean.setLr(getNumBySumLast(new int[]{0, 1, 2}, playDetailBean.getNum()));
                playDetailBean.setBetCode("pl3_hundred_ten_one_sum");
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
        }
        return list;
    }

    //初始化  组选三的数据源
    public static List<PlayTypeBean.PlayBean> initZX3Data() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("会员可以挑选5~10个号码，当开奖结果中有且只有两个号码重复，则视为中奖。挑选不同个数号码有其相对应的赔率。 \n" +
                "如果是选择 1、2、3、4、5中的任何两个号码，且其中有一个号码重复则中奖。 ");
        playBean1.setSampleBet("112、344，若是开出豹子则不算中奖。");
        playBean1.setSingleExplain("012345");
        playBean1.setPlayTypeName(getRec().getString(R.string.ZXS));

        playBeanList.add(playBean1);
        return playBeanList;
    }


    //初始化 组选3 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_ZX3_0_9_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.ZXS));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_group_three));
            playDetailBean.setBetCode("pl3_group3");
            playDetailBean.setLr(getNumBydouble(playDetailBean.getNum()));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":5");
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  组选6的数据源
    public static List<PlayTypeBean.PlayBean> initZX6Data() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("会员可以挑选4~8个号码，当开奖结果[千位、百位、个位]都出现在所下注的号码中且没有任何号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率，中奖赔率以所选号码中的最小赔率计算派彩。  ");
        playBean1.setSampleBet("如果是选择(1、2、3、4)，则开奖结果[千位、百位、个位]为123、124、134、234都中奖，其他都是不中奖。例如：112、133、145、444等都是不中奖。");
        playBean1.setSingleExplain("0123");
        playBean1.setPlayTypeName(getRec().getString(R.string.ZXL));

        playBeanList.add(playBean1);

        return playBeanList;
    }


    //初始化 组选6 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_ZX6_0_9_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.ZXL));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode(getRec().getString(R.string.pl3_group_six));
            playDetailBean.setBetCode("pl3_group6");
            playDetailBean.setLr(getNumByUnLike(playDetailBean.getNum()));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":4");
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  跨度的数据源
    public static List<PlayTypeBean.PlayBean> initKDData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("以开奖三个号码的最大差距(跨度)，作为中奖的依据。会员可以选择0~9的任一跨度。 ");
        playBean1.setSampleBet("开奖结果为3，4，8。中奖的跨度为5。(最大号码 8减最小号码 3 = 5),若开奖结果三个号码都相同，则中奖的跨度为0。");
        playBean1.setSingleExplain("2");
        playBean1.setPlayTypeName(getRec().getString(R.string.KD));


        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 跨度 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_KD_0_9_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.KD));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.looteery_qt));
            playDetailBean.setPlayCode("pl3_span");
            playDetailBean.setBetCode("pl3_span");
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            playDetailBean.setLr(getNumByLevel(playDetailBean.getNum()));
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    private static String getNumBYIndex(int index, String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length != 3) {
                return "";
            }
            String indexCode = openCodes[index];
            if (indexCode.equalsIgnoreCase(betNum)) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else {
                int code = Integer.parseInt(indexCode);
                if (betNum.equalsIgnoreCase("大")) {
                    if (code > 4) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("小")) {
                    if (code < 5) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("单")) {
                    if (code % 2 == 1) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("双")) {
                    if (code % 2 == 0) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("质")) {
                    if (Lottery_B_DataUtils.isPrime(code)) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("合")) {
                    if (!Lottery_B_DataUtils.isPrime(code)) {
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

    /**
     * 组合
     *
     * @param betNum
     * @return
     */
    private static String getNumBYGroup(String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            if (handicap.getOpenCode().contains(betNum)) {
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
     * 根据位置获取合数 冷热遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumBySum(int[] indexs, String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;



        for (Handicap handicap : getHandicap()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            int count = 0;
            if (openCodes.length != 3) {
                continue;
            }
            for (int i : indexs) {
                count += Integer.parseInt(openCodes[i]);
            }
            if (betNum.equalsIgnoreCase(String.valueOf(count))) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("单") && count % 2 == 1) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }

            } else if (betNum.equalsIgnoreCase("双") && count % 2 == 0) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("大") && count > 13) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }

            } else if (betNum.equalsIgnoreCase("小") && count < 14) {
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
     * 根据位置获取合数尾数 冷热遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumBySumLast(int[] indexs, String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            int count = 0;
            int lastCount = 0;
            if (openCodes.length != 3) {
                continue;
            }
            for (int i : indexs) {
                count += Integer.parseInt(openCodes[i]);
            }
            if (count < 10) {
                lastCount = count;
            } else {
                String countString = String.valueOf(count);
                lastCount = Integer.parseInt(countString.charAt(countString.length() - 1) + "");
            }
            if (betNum.equalsIgnoreCase(String.valueOf(lastCount))) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("大") && lastCount > 4) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }

            } else if (betNum.equalsIgnoreCase("小") && lastCount < 5) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("单") && lastCount % 2 == 1) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }

            } else if (betNum.equalsIgnoreCase("双") && lastCount % 2 == 0) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("和") && !Lottery_B_DataUtils.isPrime(lastCount)) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }

            } else if (betNum.equalsIgnoreCase("质") && Lottery_B_DataUtils.isPrime(lastCount)) {
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
     * 根据位置获取合数尾数 冷热遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumBydouble(String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes[0].equalsIgnoreCase(openCodes[1]) || openCodes[0].equalsIgnoreCase(openCodes[2]) || openCodes[1].equalsIgnoreCase(openCodes[2])) {
                if (!(openCodes[0].equalsIgnoreCase(openCodes[1]) && openCodes[1].equalsIgnoreCase(openCodes[2]))) {
                    if (betNum.equalsIgnoreCase(openCodes[1])) {
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

    /**
     * 根据位置获取合数尾数 冷热遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumByUnLike(String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (!(openCodes[0].equalsIgnoreCase(openCodes[1]) || openCodes[0].equalsIgnoreCase(openCodes[2]) || openCodes[1].equalsIgnoreCase(openCodes[2]))) {
                if (handicap.getOpenCode().contains(betNum)) {
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
     * 获取跨度 冷热遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumByLevel(String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicap().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicap()) {
            List<String> openCodes = Arrays.asList(handicap.getOpenCode().split(","));
            Collections.sort(openCodes);
            int count = Math.abs(Integer.parseInt(openCodes.get(2)) - Integer.parseInt(openCodes.get(0)));

            if (betNum.equalsIgnoreCase(String.valueOf(count))) {
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
}

