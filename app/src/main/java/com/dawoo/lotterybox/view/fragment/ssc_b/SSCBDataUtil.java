package com.dawoo.lotterybox.view.fragment.ssc_b;

import android.content.res.Resources;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by archar on 18-3-21.
 */

public class SSCBDataUtil {
    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    //初始化  ssc b 数字盘的基础数据源
    public static List<PlayTypeBean.PlayBean> initSZPData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("于万位自0~9任选1个号进行投注，当开奖结果与所选的定位与号码相同且顺序一致时，即为中奖。");
        playBean1.setSampleBet("万位选了9，开奖号码万位是9，就中奖了");
        playBean1.setSingleExplain("9");
        playBean1.setPlayTypeName(getRec().getString(R.string.ww));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("于千位自0~9任选1个号进行投注，当开奖结果与所选的定位与号码相同且顺序一致时，即为中奖。");
        playBean2.setSampleBet("千位选了9，开奖号码千位是9，就中奖了");
        playBean2.setSingleExplain("9");
        playBean2.setPlayTypeName(getRec().getString(R.string.qw));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("于百位自0~9任选1个号进行投注，当开奖结果与所选的定位与号码相同且顺序一致时，即为中奖。");
        playBean3.setSampleBet("百位选了9，开奖号码百位是9，就中奖了");
        playBean3.setSingleExplain("9");
        playBean3.setPlayTypeName(getRec().getString(R.string.bw));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("于十位自0~9任选1个号进行投注，当开奖结果与所选的定位与号码相同且顺序一致时，即为中奖。");
        playBean4.setSampleBet("十位选了9，开奖号码十位是9，就中奖了");
        playBean4.setSingleExplain("9");
        playBean4.setPlayTypeName(getRec().getString(R.string.sw));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("于个位自0~9任选1个号进行投注，当开奖结果与所选的定位与号码相同且顺序一致时，即为中奖。");
        playBean5.setSampleBet("个位选了9，开奖号码个位是9，就中奖了");
        playBean5.setSingleExplain("9");
        playBean5.setPlayTypeName(getRec().getString(R.string.gw));

        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        return playBeanList;
    }


    //初始化 数字盘 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_SZP_0_9Data(String kind) {
        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SZP));
            playDetailBean.setNum(i + "");
            playDetailBean.setKind(kind);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.one_digital));
            if (kind.equals(getRec().getString(R.string.ww))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand));
                playDetailBean.setLr(getNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(0, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.qw))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand));
                playDetailBean.setLr(getNumbyIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(1, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.bw))) {
                playDetailBean.setBetCode(getRec().getString(R.string.hundred));
                playDetailBean.setLr(getNumbyIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(2, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.sw))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_));
                playDetailBean.setLr(getNumbyIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(3, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.gw))) {
                playDetailBean.setBetCode(getRec().getString(R.string.one_));
                playDetailBean.setLr(getNumbyIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(4, playDetailBean.getNum(), 2));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  双面的基础数据源
    public static List<PlayTypeBean.PlayBean> initSMData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("取万位为基准，大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean1.setSampleBet("投注者购买万位小，当期开奖结果如为20352（2为小），则视为中奖");
        playBean1.setSingleExplain("大");
        playBean1.setPlayTypeName(getRec().getString(R.string.ww));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("取千位为基准，大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean2.setSampleBet("投注者购买千位小，当期开奖结果如为20352（0为小），则视为中奖");
        playBean2.setSingleExplain("大");
        playBean2.setPlayTypeName(getRec().getString(R.string.qw));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("取百位为基准，大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean3.setSampleBet("投注者购买百位小，当期开奖结果如为20352（3为小），则视为中奖");
        playBean3.setSingleExplain("大");
        playBean3.setPlayTypeName(getRec().getString(R.string.bw));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("取十位为基准，大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean4.setSampleBet("投注者购买十位小，当期开奖结果如为20342（4为小），则视为中奖");
        playBean3.setSingleExplain("大");
        playBean4.setPlayTypeName(getRec().getString(R.string.sw));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("取个位为基准，大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean5.setSampleBet("投注者购买个位小，当期开奖结果如为20352（2为小），则视为中奖");
        playBean5.setSingleExplain("大");
        playBean5.setPlayTypeName(getRec().getString(R.string.gw));

        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setPlayTypeExplain(
                "开奖结果所有号码总和的为23、24、25、26、27、28、29、30、31、32、33、34、35、36、37、38、39、40、41、42、43、44、45时为“大”， 若为0、1、2、3、4、5、6、7、8、9、10、11、12、13、14、15、16、17、18、19、20、21、22时为“小”，当投注和数大小与开奖结果的和数大小相符时，即为中奖。 五颗球开出1，2，3，4，5 派彩为＄105\n" +
                        "五顆球开出1，2，3，5，5 派彩为＄105\n" +
                        "五顆球开出1，2，5，5，5 派彩为＄105\n" +
                        "前三：0~9任选1个号进行投注，当开奖结果[万位、千位、百位]任一数与所选的号码相同时，即为中奖。");
        playBean6.setSampleBet("投注者购买总大，当期开奖结果如为20976（万2+千0+百9+十7+个6=24为大），则视为中奖。");
        playBean6.setSingleExplain("总大");
        playBean6.setPlayTypeName(getRec().getString(R.string.zh));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        playBeanList.add(playBean6);

        return playBeanList;
    }


    //初始化 双面 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_SM_0_9Data(boolean isSignal, String kind) {
        String[] arrays;
        if (isSignal) {
            arrays = new String[]{"大", "小", "单", "双", "质", "合"};
        } else {
            arrays = new String[]{"总大", "总小", "总单", "总双"};
        }
        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SM));
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setKind(kind);
            playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_single_double));
            } else if (playDetailBean.getNum().equals("质") || playDetailBean.getNum().equals("合")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_prime_combined));
            } else if (playDetailBean.getNum().equals("总大") || playDetailBean.getNum().equals("总小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.five_sum_big_small));
            } else if (playDetailBean.getNum().equals("总单") || playDetailBean.getNum().equals("总双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.five_sum_single_double));
            }

            if (kind.equals(getRec().getString(R.string.ww))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand));
                playDetailBean.setLr(getWordsNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(0, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.qw))) {
                playDetailBean.setLr(getWordsNumbyIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(1, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.thousand));
            } else if (kind.equals(getRec().getString(R.string.bw))) {
                playDetailBean.setLr(getWordsNumbyIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(2, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.hundred));
            } else if (kind.equals(getRec().getString(R.string.sw))) {
                playDetailBean.setLr(getWordsNumbyIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_));
            } else if (kind.equals(getRec().getString(R.string.gw))) {
                playDetailBean.setLr(getWordsNumbyIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.one_));
            } else if (kind.equals(getRec().getString(R.string.zh))) {
                playDetailBean.setLr(getWordsNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(0, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.five_sum));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  一字定位的数据源
    public static List<PlayTypeBean.PlayBean> initYZDWData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("取万位为基准，自0~9、大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean1.setSampleBet("万位选了9，开奖号码万位是9，就中奖了");
        playBean1.setSingleExplain("9");
        playBean1.setPlayTypeName(getRec().getString(R.string.ww));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("取千位为基准，自0~9、大小、单双、质合任选1个号进行投注，当开奖结果与所选号码相同时，即为中奖。");
        playBean2.setSampleBet("千位选了9，开奖号码千位是9，就中奖了");
        playBean2.setSingleExplain("9");
        playBean2.setPlayTypeName(getRec().getString(R.string.qw));


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

        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        playBeanList.add(playBean5);
        return playBeanList;
    }

    //初始化 一字定位 0-9 大小单双质和 的布局 的数据源
    public static List<PlayDetailBean> init_YZDW_0_9_DXDSZH_Data(boolean zonghe, String kind, String childType) {
        String[] arrays;
        if (!zonghe) {
            arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "大", "小", "单", "双", "质", "合"};
        } else {
            arrays = new String[]{"总大", "总小", "总单", "总双"};
        }

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.YZDW));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            if (zonghe) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
            } else {
                if (i >= 10) {
                    playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
                } else {
                    playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
                }
            }
            playDetailBean.setCode(getRec().getString(R.string.cqssc));

            if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_single_double));
            } else if (playDetailBean.getNum().equals("质") || playDetailBean.getNum().equals("合")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_prime_combined));
            } else if (playDetailBean.getNum().equals("总大") || playDetailBean.getNum().equals("总小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.five_sum_big_small));
            } else if (playDetailBean.getNum().equals("总单") || playDetailBean.getNum().equals("总双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.five_sum_single_double));
            } else {
                playDetailBean.setPlayCode(getRec().getString(R.string.one_digital));
            }

            if (zonghe) {
                playDetailBean.setBetCode(getRec().getString(R.string.five_sum));
                playDetailBean.setLr(getWordsNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getWordsNumbyIndex(0, playDetailBean.getNum(), 2));
            } else {
                if (kind.equals(getRec().getString(R.string.ww))) {
                    if (isNum(playDetailBean.getNum())) {
                        playDetailBean.setLr(getNumbyIndex(0, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getNumbyIndex(0, playDetailBean.getNum(), 2));
                    } else {
                        playDetailBean.setLr(getWordsNumbyIndex(0, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getWordsNumbyIndex(0, playDetailBean.getNum(), 2));
                    }
                    playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand));
                } else if (kind.equals(getRec().getString(R.string.qw))) {
                    if (isNum(playDetailBean.getNum())) {
                        playDetailBean.setLr(getNumbyIndex(1, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getNumbyIndex(1, playDetailBean.getNum(), 2));
                    } else {
                        playDetailBean.setLr(getWordsNumbyIndex(1, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getWordsNumbyIndex(1, playDetailBean.getNum(), 2));
                    }
                    playDetailBean.setBetCode(getRec().getString(R.string.thousand));
                } else if (kind.equals(getRec().getString(R.string.bw))) {
                    if (isNum(playDetailBean.getNum())) {
                        playDetailBean.setLr(getNumbyIndex(2, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getNumbyIndex(2, playDetailBean.getNum(), 2));
                    } else {
                        playDetailBean.setLr(getWordsNumbyIndex(2, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getWordsNumbyIndex(2, playDetailBean.getNum(), 2));
                    }
                    playDetailBean.setBetCode(getRec().getString(R.string.hundred));
                } else if (kind.equals(getRec().getString(R.string.sw))) {
                    if (isNum(playDetailBean.getNum())) {
                        playDetailBean.setLr(getNumbyIndex(3, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getNumbyIndex(3, playDetailBean.getNum(), 2));
                    } else {
                        playDetailBean.setLr(getWordsNumbyIndex(3, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getWordsNumbyIndex(3, playDetailBean.getNum(), 2));
                    }
                    playDetailBean.setBetCode(getRec().getString(R.string.ten_));
                } else if (kind.equals(getRec().getString(R.string.gw))) {
                    if (isNum(playDetailBean.getNum())) {
                        playDetailBean.setLr(getNumbyIndex(4, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getNumbyIndex(4, playDetailBean.getNum(), 2));
                    } else {
                        playDetailBean.setLr(getWordsNumbyIndex(4, playDetailBean.getNum(), 1));
                        playDetailBean.setYl(getWordsNumbyIndex(4, playDetailBean.getNum(), 2));
                    }
                    playDetailBean.setBetCode(getRec().getString(R.string.one_));
                }
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

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean1.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean1.setSingleExplain("0,9");
        playBean1.setPlayTypeName(getRec().getString(R.string.wq));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean11.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean11.setSingleExplain("0,9");
        playBean11.setPlayTypeName(getRec().getString(R.string.wb));


        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean12.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean12.setSingleExplain("0,9");
        playBean12.setPlayTypeName(getRec().getString(R.string.ws));


        PlayTypeBean.PlayBean playBean13 = new PlayTypeBean.PlayBean();
        playBean13.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean13.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean13.setSingleExplain("0,9");
        playBean13.setPlayTypeName(getRec().getString(R.string.wg));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean2.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean2.setSingleExplain("0,9");
        playBean2.setPlayTypeName(getRec().getString(R.string.qb));

        PlayTypeBean.PlayBean playBean21 = new PlayTypeBean.PlayBean();
        playBean21.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean21.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean21.setSingleExplain("0,9");
        playBean21.setPlayTypeName(getRec().getString(R.string.qs));


        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("任选二位，自0~9任选2个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean22.setSampleBet("选了 0,9，开奖号码对应位是0,9，就中奖了");
        playBean22.setSingleExplain("0,9");
        playBean22.setPlayTypeName(getRec().getString(R.string.qg));


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


        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean12);
        playBeanList.add(playBean13);
        playBeanList.add(playBean2);
        playBeanList.add(playBean21);
        playBeanList.add(playBean22);
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
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.two_digital));

            if (kind.equals(getRec().getString(R.string.wq))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_thousand));
            } else if (kind.equals(getRec().getString(R.string.wb))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_hundred));
            } else if (kind.equals(getRec().getString(R.string.ws))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_ten_));
            } else if (kind.equals(getRec().getString(R.string.wg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_one_));
            } else if (kind.equals(getRec().getString(R.string.qb))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_hundred));
            } else if (kind.equals(getRec().getString(R.string.qs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_ten_));
            } else if (kind.equals(getRec().getString(R.string.qg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_one_));
            } else if (kind.equals(getRec().getString(R.string.bs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.hundred_ten_));
            } else if (kind.equals(getRec().getString(R.string.bg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.hundred_one_));
            } else if (kind.equals(getRec().getString(R.string.sg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_one_));
            }

            if (childType.equalsIgnoreCase("万")) {
                playDetailBean.setLr(getNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(0, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("千")) {
                playDetailBean.setLr(getNumbyIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(1, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("百")) {
                playDetailBean.setLr(getNumbyIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(2, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("十")) {
                playDetailBean.setLr(getNumbyIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(3, playDetailBean.getNum(), 2));
            } else {
                playDetailBean.setLr(getNumbyIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(4, playDetailBean.getNum(), 2));
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

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean1.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean1.setSingleExplain("0 9 2");
        playBean1.setPlayTypeName(getRec().getString(R.string.wqb));

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean2.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean2.setSingleExplain("0 9 2");
        playBean2.setPlayTypeName(getRec().getString(R.string.wqs));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean3.setSingleExplain("0 9 2");
        playBean3.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean3.setPlayTypeName(getRec().getString(R.string.wqg));


        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean4.setSingleExplain("0 9 2");
        playBean4.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean4.setPlayTypeName(getRec().getString(R.string.wbs));

        PlayTypeBean.PlayBean playBean5 = new PlayTypeBean.PlayBean();
        playBean5.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean5.setSingleExplain("0 9 2");
        playBean5.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean5.setPlayTypeName(getRec().getString(R.string.wbg));


        PlayTypeBean.PlayBean playBean6 = new PlayTypeBean.PlayBean();
        playBean6.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean6.setSingleExplain("0 9 2");
        playBean6.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean6.setPlayTypeName(getRec().getString(R.string.wsg));


        PlayTypeBean.PlayBean playBean7 = new PlayTypeBean.PlayBean();
        playBean7.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean7.setSingleExplain("0 9 2");
        playBean7.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean7.setPlayTypeName(getRec().getString(R.string.qbs));


        PlayTypeBean.PlayBean playBean8 = new PlayTypeBean.PlayBean();
        playBean8.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean8.setSingleExplain("0 9 2");
        playBean8.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean8.setPlayTypeName(getRec().getString(R.string.qbg));

        PlayTypeBean.PlayBean playBean9 = new PlayTypeBean.PlayBean();
        playBean9.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean9.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean9.setSingleExplain("0 9 2");
        playBean9.setPlayTypeName(getRec().getString(R.string.qsg));


        PlayTypeBean.PlayBean playBean10 = new PlayTypeBean.PlayBean();
        playBean10.setPlayTypeExplain("任选三位，自0~9任选3个号进行投注，当开奖结果与所选号码相同且顺序一致时，即为中奖");
        playBean10.setSampleBet("选了 0,9，2,开奖号码对应位是0,9，2,就中奖了");
        playBean10.setSingleExplain("0 9 2");
        playBean10.setPlayTypeName(getRec().getString(R.string.bsg));

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
            playDetailBean.setPlayCode(getRec().getString(R.string.three_digital));

            if (kind.equals(getRec().getString(R.string.wqb))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_thousand_hundred));
            } else if (kind.equals(getRec().getString(R.string.wqs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_thousand_ten));
            } else if (kind.equals(getRec().getString(R.string.wqg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_thousand_one));
            } else if (kind.equals(getRec().getString(R.string.wbs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_hundred_ten));
            } else if (kind.equals(getRec().getString(R.string.wbg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_hundred_one));
            } else if (kind.equals(getRec().getString(R.string.wsg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_ten_one));
            } else if (kind.equals(getRec().getString(R.string.qbs))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_hundred_ten));
            } else if (kind.equals(getRec().getString(R.string.qbg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_hundred_one));
            } else if (kind.equals(getRec().getString(R.string.qsg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_ten_one));
            } else if (kind.equals(getRec().getString(R.string.bsg))) {
                playDetailBean.setBetCode(getRec().getString(R.string.hundred_ten_one));
            }

            if (childType.equalsIgnoreCase("万")) {
                playDetailBean.setLr(getNumbyIndex(0, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(0, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("千")) {
                playDetailBean.setLr(getNumbyIndex(1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(1, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("百")) {
                playDetailBean.setLr(getNumbyIndex(2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(2, playDetailBean.getNum(), 2));
            } else if (childType.equalsIgnoreCase("十")) {
                playDetailBean.setLr(getNumbyIndex(3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(3, playDetailBean.getNum(), 2));
            } else {
                playDetailBean.setLr(getNumbyIndex(4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyIndex(4, playDetailBean.getNum(), 2));
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
        playBean1.setPlayTypeExplain("0~9任选1个号进行投注，当开奖结果[万位、千位、百位、十位、个位]任一数与所选的号码相同时，即为中奖。同个号码出现多次时只计一次中奖");
        playBean1.setSampleBet("下注一字【5号】＄100，一字賠率2.404,五颗球开出9，5，8，3，5 派彩为＄240.4");
        playBean1.setSingleExplain("0");
        playBean1.setPlayTypeName(getRec().getString(R.string.q5yzzh));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("0~9任选1个号进行投注，当开奖结果[万位、千位、百位]任一数与所选的号码相同时，即为中奖。");
        playBean2.setSampleBet("下注万位【9号】，五颗球开出9，5，8，3，5 , 万位相同，中奖");
        playBean2.setSingleExplain("0");
        playBean2.setPlayTypeName(getRec().getString(R.string.q3yzzh));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("0~9任选1个号进行投注，当开奖结果[千位、百位、十位]任一数与所选的号码相同时，即为中奖。");
        playBean3.setSampleBet("下注千【5号】，五颗球开出9，5，8，3，5 , 千位相同，中奖");
        playBean3.setSingleExplain("0");
        playBean3.setPlayTypeName(getRec().getString(R.string.z3yzzh));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("0~9任选1个号进行投注，当开奖结果[百位、十位、个位]任一数与所选的号码相同时，即为中奖。");
        playBean4.setSampleBet("下注百【8号】，五颗球开出9，5，8，3，5 , 百位相同，中奖");
        playBean4.setSingleExplain("0");
        playBean4.setPlayTypeName(getRec().getString(R.string.h3yzzh));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        return playBeanList;
    }

    //初始化 一字组合 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_YZZH_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.YZZH));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.one_combination));
            if (kind.equals(getRec().getString(R.string.q5yzzh))) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_98));
                playDetailBean.setBetCode(getRec().getString(R.string.one_all_five));
                playDetailBean.setLr(getNumbyGround(0, 5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(0, 5, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.q3yzzh))) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_3_03));
                playDetailBean.setBetCode(getRec().getString(R.string.one_first_three));
                playDetailBean.setLr(getNumbyGround(0, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(0, 3, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.z3yzzh))) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_3_03));
                playDetailBean.setBetCode(getRec().getString(R.string.one_in_three));
                playDetailBean.setLr(getNumbyGround(1, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(1, 4, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.h3yzzh))) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_3_03));
                playDetailBean.setBetCode(getRec().getString(R.string.one_after_three));
                playDetailBean.setLr(getNumbyGround(2, 5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(2, 5, playDetailBean.getNum(), 2));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  组选三的数据源
    public static List<PlayTypeBean.PlayBean> initZX3Data() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("会员可以挑选5~10个号码，当开奖结果[万位、千位、百位]中有且只有两个号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率。如果是选择(1、2、3、4、5)，则只要开奖结果[万位、千位、百位]中，有出现1、2、3、4、5中的任何两个号码，且其中有一个号码重复则中奖。 ");
        playBean1.setSampleBet("※例如：112、344，若是开出豹子则不算中奖。 ");
        playBean1.setSingleExplain("0 1 2 3 4 5");
        playBean1.setPlayTypeName(getRec().getString(R.string.q3zx3));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("会员可以挑选5~10个号码，当开奖结果[千位、百位、十位]中有且只有两个号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率。如果是选择(1、2、3、4、5)，则只要开奖结果[千位、百位、十位]中，有出现1、2、3、4、5中的任何两个号码，且其中有一个号码重复则中奖。 ");
        playBean11.setSampleBet("※例如：112、344，若是开出豹子则不算中奖。 ");
        playBean11.setSingleExplain("0 1 2 3 4 5");
        playBean11.setPlayTypeName(getRec().getString(R.string.z3zx3));


        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setPlayTypeExplain("会员可以挑选5~10个号码，当开奖结果[百位、十位、个位]中有且只有两个号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率。如果是选择(1、2、3、4、5)，则只要开奖结果[百位、十位、个位]中，有出现1、2、3、4、5中的任何两个号码，且其中有一个号码重复则中奖。 ");
        playBean12.setSampleBet("※例如：112、344，若是开出豹子则不算中奖。");
        playBean12.setSingleExplain("0 1 2 3 4 5");
        playBean12.setPlayTypeName(getRec().getString(R.string.h3zx3));


        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean12);

        return playBeanList;
    }


    //初始化 组选3 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_ZX3_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.ZXS));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.group_three));
            if (kind.equals(getRec().getString(R.string.q3zx3))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group3_first_three));
                playDetailBean.setLr(getNumbyGround(0, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(0, 3, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.z3zx3))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group3_in_three));
                playDetailBean.setLr(getNumbyGround(1, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(1, 4, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.h3zx3))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group3_after_three));
                playDetailBean.setLr(getNumbyGround(2, 5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(2, 5, playDetailBean.getNum(), 2));
            }
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
        playBean1.setPlayTypeExplain("会员可以挑选4~8个号码，当开奖结果[万位、千位、百位]都出现在所下注的号码中且没有任何号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率，中奖赔率以所选号码中的最小赔率计算派彩。  ");
        playBean1.setSampleBet("如果是选择(1、2、3、4)，则开奖结果[万位、千位、百位]为123、124、134、234都中奖，其他都是不中奖。例如：112、133、145、444等都是不中奖。");
        playBean1.setSingleExplain("0 1 2 3");
        playBean1.setPlayTypeName(getRec().getString(R.string.q3zx6));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("会员可以挑选4~8个号码，当开奖结果[千位、百位、十位]都出现在所下注的号码中且没有任何号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率，中奖赔率以所选号码中的最小赔率计算派彩。  ");
        playBean11.setSampleBet("如果是选择(1、2、3、4)，则开奖结果[千位、百位、十位]为123、124、134、234都中奖，其他都是不中奖。例如：112、133、145、444等都是不中奖。");
        playBean11.setSingleExplain("0 1 2 3");
        playBean11.setPlayTypeName(getRec().getString(R.string.z3zx6));


        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setPlayTypeExplain("会员可以挑选4~8个号码，当开奖结果[百位、十位、个位]都出现在所下注的号码中且没有任何号码重复，则视为中奖。挑选不同个数的号码有其相对应的赔率，中奖赔率以所选号码中的最小赔率计算派彩。");
        playBean12.setSampleBet("如果是选择(1、2、3、4)，则开奖结果[百位、十位、个位]为123、124、134、234都中奖，其他都是不中奖。例如：112、133、145、444等都是不中奖。");
        playBean12.setSingleExplain("0 1 2 3");
        playBean12.setPlayTypeName(getRec().getString(R.string.h3zx6));


        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean12);

        return playBeanList;
    }


    //初始化 组选6 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_ZX6_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.ZXL));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd(getRec().getString(R.string.bv_9_85));
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.group_six));
            if (kind.equals(getRec().getString(R.string.q3zx6))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group6_first_three));
                playDetailBean.setLr(getNumbyGround(0, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(0, 3, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.z3zx6))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group6_in_three));
                playDetailBean.setLr(getNumbyGround(1, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(1, 4, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.h3zx6))) {
                playDetailBean.setBetCode(getRec().getString(R.string.group6_after_three));
                playDetailBean.setLr(getNumbyGround(2, 5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbyGround(2, 5, playDetailBean.getNum(), 2));
            }
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
        playBean1.setPlayTypeExplain("以开奖结果[万位、千位、百位]的最大差距（跨度），作为中奖依据。会员可以选择0~9的任一跨度。");
        playBean1.setSampleBet("开奖结果为3、4、8、7、6。中奖的跨度为5。（最大号码8减最小号码3=5）。");
        playBean1.setSingleExplain("0");
        playBean1.setPlayTypeName(getRec().getString(R.string.q3kd));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("以开奖结果[千位、百位、十位]的最大差距（跨度），作为中奖依据。会员可以选择0~9的任一跨度。 ");
        playBean2.setSampleBet("开奖结果为3、4、8、7、6。中奖的跨度为4。（最大号码8减最小号码4=4）");
        playBean2.setSingleExplain("0");
        playBean2.setPlayTypeName(getRec().getString(R.string.z3kd));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("以开奖结果[百位、十位、个位]的最大差距（跨度），作为中奖依据。会员可以选择0~9的任一跨度。 ");
        playBean3.setSampleBet("开奖结果为3、4、8、7、6。中奖的跨度为2。（最大号码8减最小号码6=2）。");
        playBean3.setSingleExplain("0");
        playBean3.setPlayTypeName(getRec().getString(R.string.h3kd));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        return playBeanList;
    }

    //初始化 跨度 0-9 的布局 的数据源
    public static List<PlayDetailBean> init_KD_0_9_Data(String kind, String childType) {
        String[] arrays;
        arrays = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.KD));
            playDetailBean.setKind(kind);
            playDetailBean.setChildType(childType);
            playDetailBean.setNum(arrays[i]);
            if (i == 0) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_71));
            } else if (i == 1) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_14_8));
            } else if (i == 2) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_8_1));
            } else if (i == 3) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_6_2));
            } else if (i == 4) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_5_4));
            } else if (i == 5) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_5_2));
            } else if (i == 6) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_5_4));
            } else if (i == 7) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_6_2));
            } else if (i == 8) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_8_1));
            } else if (i == 9) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_14_4));
            }
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.span));
            if (kind.equals(getRec().getString(R.string.q3kd))) {
                playDetailBean.setBetCode(getRec().getString(R.string.span_first_three));
                playDetailBean.setLr(getNumSpanbyGround(0, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumSpanbyGround(0, 3, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.z3kd))) {
                playDetailBean.setBetCode(getRec().getString(R.string.span_in_three));
                playDetailBean.setLr(getNumSpanbyGround(1, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumSpanbyGround(1, 4, playDetailBean.getNum(), 2));
            } else if (kind.equals(getRec().getString(R.string.h3kd))) {
                playDetailBean.setBetCode(getRec().getString(R.string.span_after_three));
                playDetailBean.setLr(getNumSpanbyGround(2, 5, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumSpanbyGround(2, 5, playDetailBean.getNum(), 2));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  龙虎的数据源
    public static List<PlayTypeBean.PlayBean> initLHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean1.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！ ");
        playBean1.setSingleExplain("龙");
        playBean1.setPlayTypeName(getRec().getString(R.string.wq));

        PlayTypeBean.PlayBean playBean11 = new PlayTypeBean.PlayBean();
        playBean11.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean11.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean11.setSingleExplain("龙");
        playBean11.setPlayTypeName(getRec().getString(R.string.wb));


        PlayTypeBean.PlayBean playBean12 = new PlayTypeBean.PlayBean();
        playBean12.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean12.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean12.setSingleExplain("龙");
        playBean12.setPlayTypeName(getRec().getString(R.string.ws));


        PlayTypeBean.PlayBean playBean13 = new PlayTypeBean.PlayBean();
        playBean13.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean13.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean13.setSingleExplain("龙");
        playBean13.setPlayTypeName(getRec().getString(R.string.wg));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean2.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean2.setSingleExplain("龙");
        playBean2.setPlayTypeName(getRec().getString(R.string.qb));

        PlayTypeBean.PlayBean playBean21 = new PlayTypeBean.PlayBean();
        playBean21.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean21.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean21.setSingleExplain("龙");
        playBean21.setPlayTypeName(getRec().getString(R.string.qs));


        PlayTypeBean.PlayBean playBean22 = new PlayTypeBean.PlayBean();
        playBean22.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean22.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean22.setSingleExplain("龙");
        playBean22.setPlayTypeName(getRec().getString(R.string.qg));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean3.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean3.setSingleExplain("龙");
        playBean3.setPlayTypeName(getRec().getString(R.string.bs));

        PlayTypeBean.PlayBean playBean31 = new PlayTypeBean.PlayBean();
        playBean31.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean31.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean31.setSingleExplain("龙");
        playBean31.setPlayTypeName(getRec().getString(R.string.bg));

        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("龙虎是以开奖结果的五个数字作为基准，取任意位置（万、千、百、十、个）的数字进行组合大小比对的一种玩法； 当投注龙/虎时，开奖结果为和局，那么押注龙/虎视为不中奖； 当投注“和”时，开奖结果为龙/虎，投注“和”视为不中奖；");
        playBean4.setSampleBet("开奖结果为：2,1,3,5,2 万为龙、千为龙虎时：结果 龙(2）大于虎（1），即为开龙；如万为龙，个为虎时，结果一样大，即为开和局！");
        playBean4.setSingleExplain("龙");
        playBean4.setPlayTypeName(getRec().getString(R.string.sg));


        playBeanList.add(playBean1);
        playBeanList.add(playBean11);
        playBeanList.add(playBean12);
        playBeanList.add(playBean13);
        playBeanList.add(playBean2);
        playBeanList.add(playBean21);
        playBeanList.add(playBean22);
        playBeanList.add(playBean3);
        playBeanList.add(playBean31);
        playBeanList.add(playBean4);
        return playBeanList;
    }

    //初始化 龙虎 龙和虎 的布局 的数据源
    public static List<PlayDetailBean> init_LH_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"龙", "和", "虎"};
        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.LH));
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setKind(kind);
            if (playDetailBean.getNum().equals(arrays[0])) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_98));
            } else if (playDetailBean.getNum().equals(arrays[1])) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_2_07));
            } else if (playDetailBean.getNum().equals(arrays[2])) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_98));
            }
            playDetailBean.setCode(getRec().getString(R.string.cqssc));
            playDetailBean.setPlayCode(getRec().getString(R.string.dragon_tiger_tie));
            if (kind.equals(getRec().getString(R.string.wq))) {
                playDetailBean.setLr(getNumbetween(0, 1, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(0, 1, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_thousand));
            } else if (kind.equals(getRec().getString(R.string.wb))) {
                playDetailBean.setLr(getNumbetween(0, 2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(0, 2, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_hundred));
            } else if (kind.equals(getRec().getString(R.string.ws))) {
                playDetailBean.setLr(getNumbetween(0, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(0, 3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_ten_));
            } else if (kind.equals(getRec().getString(R.string.wg))) {
                playDetailBean.setLr(getNumbetween(0, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(0, 4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_thousand_one_));
            } else if (kind.equals(getRec().getString(R.string.qb))) {
                playDetailBean.setLr(getNumbetween(1, 2, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(1, 2, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_hundred));
            } else if (kind.equals(getRec().getString(R.string.qs))) {
                playDetailBean.setLr(getNumbetween(1, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(1, 3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_ten_));
            } else if (kind.equals(getRec().getString(R.string.qg))) {
                playDetailBean.setLr(getNumbetween(1, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(1, 4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.thousand_one_));
            } else if (kind.equals(getRec().getString(R.string.bs))) {
                playDetailBean.setLr(getNumbetween(2, 3, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(2, 3, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.hundred_ten_));
            } else if (kind.equals(getRec().getString(R.string.bg))) {
                playDetailBean.setLr(getNumbetween(2, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(2, 4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.hundred_one_));
            } else if (kind.equals(getRec().getString(R.string.sg))) {
                playDetailBean.setLr(getNumbetween(3, 4, playDetailBean.getNum(), 1));
                playDetailBean.setYl(getNumbetween(3, 4, playDetailBean.getNum(), 2));
                playDetailBean.setBetCode(getRec().getString(R.string.ten_one_));
            }
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    /**
     * 根据下标获取冷热 遗漏数据
     * used in 数字
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getNumbyIndex(int index, String betNum, int changeType) {
        if (BLotteryOddFactory.getInstance().getmHistorys().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : BLotteryOddFactory.getInstance().getmHistorys()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length <= index) {
                continue;
            }
            String code = openCodes[index];
            if (code.equalsIgnoreCase(betNum)) {
                if (changeType == 1) {
                    num++;
                } else {
                    break;
                }
            }
            if (changeType == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 根据下标获取冷热 遗漏数据
     * used in 数字
     *
     * @param betNum
     * @return
     */
    private static String getNumbyGround(int from, int to, String betNum, int changeType) {
        if (BLotteryOddFactory.getInstance().getmHistorys().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : BLotteryOddFactory.getInstance().getmHistorys()) {
            List<String> openCodes = Arrays.asList(handicap.getOpenCode().split(","));
            if (openCodes.size() <= to) {
                continue;
            }
            openCodes = openCodes.subList(from, to);
            if (openCodes.contains(betNum)) {
                if (changeType == 1) {
                    num++;
                } else {
                    break;
                }
            }
            if (changeType == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 根据下标获取冷热 遗漏数据
     * used in 数字
     *
     * @param betNum
     * @return
     */
    private static String getNumSpanbyGround(int from, int to, String betNum, int changeType) {
        if (BLotteryOddFactory.getInstance().getmHistorys().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : BLotteryOddFactory.getInstance().getmHistorys()) {
            List<String> openCodes = Arrays.asList(handicap.getOpenCode().split(","));
            if (openCodes.size() <= to) {
                continue;
            }
            openCodes = openCodes.subList(from, to);
            int max = Integer.valueOf(Collections.max(openCodes));
            int min = Integer.valueOf(Collections.min(openCodes));
            if (betNum.equalsIgnoreCase(String.valueOf(max - min))) {
                if (changeType == 1) {
                    num++;
                } else {
                    break;
                }
            }
            if (changeType == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    private static String getNumbetween(int first, int sec, String betNum, int changeType) {
        if (BLotteryOddFactory.getInstance().getmHistorys().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : BLotteryOddFactory.getInstance().getmHistorys()) {
            List<String> openCodes = Arrays.asList(handicap.getOpenCode().split(","));

            int firstCode = Integer.valueOf(openCodes.get(first));
            int secCode = Integer.valueOf(openCodes.get(sec));
            String wordNum;
            if (firstCode == secCode) {
                wordNum = "和";
            } else if (firstCode > secCode) {
                wordNum = "龙";
            } else {
                wordNum = "虎";
            }
            if (betNum.equalsIgnoreCase(wordNum)) {
                if (changeType == 1) {
                    num++;
                } else {
                    break;
                }
            }
            if (changeType == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 根据下标文字玩法的获取冷热 遗漏数据
     * usesd in 双面
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getWordsNumbyIndex(int index, String betNum, int changeType) {
        if (BLotteryOddFactory.getInstance().getmHistorys().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : BLotteryOddFactory.getInstance().getmHistorys()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length <= index) {
                continue;
            }
            int count = 0;
            if (betNum.contains("总")) {
                for (String simple : openCodes) {
                    count += Integer.valueOf(simple);
                }
            }
            int code = Integer.parseInt(openCodes[index]);
            if (betNum.equalsIgnoreCase("大")) {
                if (code > 4) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("小")) {
                if (code < 5) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("单")) {
                if (code % 2 == 1) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }

            } else if (betNum.equalsIgnoreCase("双")) {
                if (code % 2 == 0) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("质")) {
                if (isPrime(code)) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("合")) {
                if (!isPrime(code)) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总大")) {
                if (count > 22) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总小")) {
                if (count < 23) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总单")) {
                if (count % 2 == 1) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总双")) {
                if (count % 2 == 0) {
                    if (changeType == 1) {
                        num++;
                        continue;
                    } else {
                        break;
                    }
                }
            }

            if (changeType == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 是否是质数
     *
     * @param n
     * @return
     */
    public static boolean isPrime(long n) {
        if (n > 2 && (n & 1) == 0)
            return false;
                /* 运用试除法:
           * 1.只有奇数需要被测试
           * 2.测试范围从2与根号{n},反之亦然 */
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    private static boolean isNum(String betNum) {
        return Pattern.compile("[0-9]").matcher(betNum).matches();
    }

    /**
     * 清除选中状态
     *
     * @param mList
     * @return
     */
    public static List<PlayDetailBean> clearSelected(List<PlayDetailBean> mList) {
        if (!mList.isEmpty()) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setSelected(false);
            }
        }
        return mList;
    }

    /**
     * 随机数组
     *
     * @param size
     * @param min
     * @param max
     * @return
     */
    public static int[] getRandomList(int size, int min, int max) {
        List<Integer> alls = new ArrayList<>();
        for (int i = min; i < max; i++) {
            alls.add(i);
        }

        int[] result = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(alls.size() - 1 - i);
            int randomRes = alls.get(randomIndex);
            result[i] = randomRes;
            int temp = alls.get(randomIndex);
            alls.set(randomIndex, alls.get(alls.size() - 1 - i));
            alls.set(alls.size() - 1 - i, temp);
        }
        return result;
    }
}
