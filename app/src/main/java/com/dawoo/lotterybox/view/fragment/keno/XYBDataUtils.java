package com.dawoo.lotterybox.view.fragment.keno;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.TextView;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;
import com.dawoo.lotterybox.view.activity.lottery.keno.XY28Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by archar on 18-3-21.
 */

public class XYBDataUtils {
    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    private synchronized static List<Handicap> getHandicaps() {
        return BLotteryOddFactory.getInstance().getmHistorys();
    }

    private static final int[] GREEN = {1, 4, 7, 10, 16, 19, 22, 25};
    private static final int[] BLUE = {2, 5, 8, 11, 17, 20, 23, 26};
    private static final int[] RED = {3, 6, 9, 12, 15, 18, 21, 24};

    //初始化  xy28  混合的基础数据源
    public static List<PlayTypeBean.PlayBean> initXy28HHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("从大双,小单,大单,小双中至少选一注，当期开奖号码和值，符合投注组合，即中奖。");
        playBean1.setSampleBet("从大双,小单,大单,小双中至少选一注，当期开奖号码和值，符合投注组合，即中奖。");
        playBean1.setSingleExplain("豹子");
        playBean1.setPlayTypeName(getRec().getString(R.string.HH));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 xy28  混合的布局 的数据源
    public static List<PlayDetailBean> init_Xy28_HH_Data(String kind) {
        String[] arrays = new String[]{"大", "小", "单", "双", "大单", "小单", "大双", "小双",
                "极大", "极小", "红波", "绿波", "蓝波", "豹子"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.HH));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.xy28));
            playDetailBean.setBetCode(getRec().getString(R.string.xy28_sum3));
            if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setOdd(getRec().getString(R.string.bv_1_985));
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_single_double));
            } else if (playDetailBean.getNum().equals("大单") || playDetailBean.getNum().equals("小单")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_half));
            } else if (playDetailBean.getNum().equals("大双") || playDetailBean.getNum().equals("小双")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_half));
            } else if (playDetailBean.getNum().equals("极大") || playDetailBean.getNum().equals("极小")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_extreme));
            } else if (playDetailBean.getNum().equals("红波") || playDetailBean.getNum().equals("绿波") || playDetailBean.getNum().equals("蓝波")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_colour));
            } else if (playDetailBean.getNum().equals("豹子")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.xy28_three_same));
            }
            playDetailBean.setLr(getNumbyCount(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getNumbyCount(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  xy28  和值特码的基础数据源
    public static List<PlayTypeBean.PlayBean> initXy28HZTMData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("从0-27中选取一个以上的数字，投注号码与开奖号码和值相同，即中奖。");
        playBean1.setSampleBet("从0-27中选取一个以上的数字，投注号码与开奖号码和值相同，即中奖。");
        playBean1.setSingleExplain("10");
        playBean1.setPlayTypeName(getRec().getString(R.string.HZTM));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 xy28 和值特码的布局 的数据源
    public static List<PlayDetailBean> init_Xy28_HZTM_Data(String kind) {

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.HZTM));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(i + "");
            if (i == 0 || i == 27) {
                playDetailBean.setOdd("");
            } else if (i == 1 || i == 26) {
                playDetailBean.setOdd("");
            } else if (i == 2 || i == 25) {
                playDetailBean.setOdd("");
            } else if (i == 3 || i == 24) {
                playDetailBean.setOdd("");
            } else if (i == 4 || i == 23) {
                playDetailBean.setOdd("");
            } else if (i == 5 || i == 22) {
                playDetailBean.setOdd("");
            } else if (i == 6 || i == 21) {
                playDetailBean.setOdd("");
            } else if (i == 7 || i == 20) {
                playDetailBean.setOdd("");
            } else if (i == 8 || i == 19) {
                playDetailBean.setOdd("");
            } else if (i == 9 || i == 18) {
                playDetailBean.setOdd("");
            } else if (i == 10 || i == 17) {
                playDetailBean.setOdd("");
            } else if (i == 11 || i == 16) {
                playDetailBean.setOdd("");
            } else if (i == 12 || i == 15) {
                playDetailBean.setOdd("");
            } else if (i == 13 || i == 14) {
                playDetailBean.setOdd("");
            }
            playDetailBean.setCode(getRec().getString(R.string.xy28));
            playDetailBean.setBetCode(getRec().getString(R.string.xy28_sum3));
            playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_digital));
            playDetailBean.setLr(getNumbyCount(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getNumbyCount(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  xy28  特码包三的基础数据源
    public static List<PlayTypeBean.PlayBean> init28TMBSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("从0-27中任选3个号码组成1注，任意一个选号与开奖号码和值相同，即中奖。");
        playBean1.setSampleBet("从0-27中任选3个号码组成1注，任意一个选号与开奖号码和值相同，即中奖。");
        playBean1.setSingleExplain("10");
        playBean1.setPlayTypeName(getRec().getString(R.string.TMBS));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 xy28 特码包三的布局 的数据源
    public static List<PlayDetailBean> init_Xy28_TMBS_Data(String kind) {

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.TMBS));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(i + "");
            playDetailBean.setOdd("");
            playDetailBean.setCode(getRec().getString(R.string.xy28));
            playDetailBean.setBetCode(getRec().getString(R.string.xy28_sum3));
            playDetailBean.setPlayCode(getRec().getString(R.string.xy28_sum3_digital_three));
            playDetailBean.setLr(getNumbyCount(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getNumbyCount(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + getRec().getString(R.string.TMBS));
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    //初始化  bjkl8  选几 的基础数据源
    public static List<PlayTypeBean.PlayBean> initBJKL8ChooseData(int type) {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        if (type == 1) {
            playBean1.setPlayTypeExplain("投注的1个号码与当期摇出的20个号码中的任1个号码相同，则中奖。 ");
            playBean1.setSampleBet("投注的1个号码与当期摇出的20个号码中的任1个号码相同，则中奖。");
            playBean1.setSingleExplain("1");
            playBean1.setPlayTypeName(getRec().getString(R.string.x1));
            LotteryOddBean lotteryOddBean5 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选一");
            if (lotteryOddBean5 != null) {
                playBean1.setScheme(lotteryOddBean5.getOdd() + "");
            }

        } else if (type == 2) {
            playBean1.setPlayTypeExplain("投注的2个号码与当期摇出的20个号码中的任2个号码相同，则中奖。 ");
            playBean1.setSampleBet("投注的2个号码与当期摇出的20个号码中的任2个号码相同，则中奖。");
            playBean1.setSingleExplain("1,2");
            playBean1.setPlayTypeName(getRec().getString(R.string.x2));

            LotteryOddBean lotteryOddBean5 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选二");
            if (lotteryOddBean5 != null) {
                playBean1.setScheme(lotteryOddBean5.getOdd() + "");
            }


        } else if (type == 3) {
            playBean1.setPlayTypeExplain("投注的3个号码为一组合，若其中2个是开奖中的号码，即为三中二，视为中奖；若3个都是开奖中的号码，即为三中三，其余情形视为不中奖。");
            playBean1.setSampleBet("投注的3个号码为一组合，若其中2个是开奖中的号码，即为三中二，视为中奖");
            playBean1.setSingleExplain("1,2,3");
            playBean1.setPlayTypeName(getRec().getString(R.string.x3));

            LotteryOddBean lotteryOddBean5 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选三-中3");
            LotteryOddBean lotteryOddBean4 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选三-中2");
            if (lotteryOddBean5 != null) {
                playBean1.setScheme(lotteryOddBean5.getOdd() + ",");
            }
            if (lotteryOddBean4 != null) {
                playBean1.setScheme(playBean1.getScheme() + "" + lotteryOddBean4.getOdd() + ",");
            }

        } else if (type == 4) {
            playBean1.setPlayTypeExplain("投注的4个号码为一组合，若其中2个是开奖中的号码，即为四中二，视为中奖；若其中3个是开奖中的号码，即为四中三；若4个都是开奖中的号码，即为四中四，其余情形视为不中奖。 ");
            playBean1.setSampleBet("投注的4个号码为一组合，若其中2个是开奖中的号码，即为四中二，视为中奖");
            playBean1.setSingleExplain("1,2,3,4");
            playBean1.setPlayTypeName(getRec().getString(R.string.x4));

            LotteryOddBean lotteryOddBean5 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选四-中4");
            LotteryOddBean lotteryOddBean4 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选四-中3");
            LotteryOddBean lotteryOddBean3 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选四-中2");
            if (lotteryOddBean5 != null) {
                playBean1.setScheme(lotteryOddBean5.getOdd() + ",");
            }
            if (lotteryOddBean4 != null) {
                playBean1.setScheme(playBean1.getScheme() + "" + lotteryOddBean4.getOdd() + ",");
            }
            if (lotteryOddBean3 != null) {
                playBean1.setScheme(playBean1.getScheme() + "" + lotteryOddBean3.getOdd());
            }

        } else if (type == 5) {
            playBean1.setPlayTypeExplain("投注的5个号码为一组合，若其中3个是开奖中的号码，即为五中三，视为中奖；若其中4个号码是开奖中的号码，即为五中四；若5个都是开奖中的号码，即为五中五，其余情形视为不中奖。");
            playBean1.setSampleBet("投注的5个号码为一组合，若其中3个是开奖中的号码，即为五中三，视为中奖；");
            playBean1.setSingleExplain("1,2,3,4,5");
            playBean1.setPlayTypeName(getRec().getString(R.string.x5));
            LotteryOddBean lotteryOddBean5 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选五-中5");
            LotteryOddBean lotteryOddBean4 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选五-中4");
            LotteryOddBean lotteryOddBean3 = getOddMap().get(getRec().getString(R.string.keno_selection) + ":选五-中3");
            if (lotteryOddBean5 != null) {
                playBean1.setScheme(lotteryOddBean5.getOdd() + ",");
            }
            if (lotteryOddBean4 != null) {
                playBean1.setScheme(playBean1.getScheme() + "" + lotteryOddBean4.getOdd() + ",");
            }
            if (lotteryOddBean3 != null) {
                playBean1.setScheme(playBean1.getScheme() + "" + lotteryOddBean3.getOdd());
            }

        }

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 bjkl8 选几的布局 的数据源
    public static List<PlayDetailBean> init_BJKl8_Choose_Data(String kind) {

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 1; i < 81; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(kind);
            playDetailBean.setKind(kind);
            playDetailBean.setNum(i + "");
            playDetailBean.setCode(getRec().getString(R.string.bjkl8));
            playDetailBean.setBetCode(getRec().getString(R.string.keno_selection));
            if (kind.equals(getRec().getString(R.string.x1))) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_selection_one));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":选一");
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
            } else if (kind.equals(getRec().getString(R.string.x2))) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_selection_two));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":选二");
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
            } else if (kind.equals(getRec().getString(R.string.x3))) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_selection_three));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":选三-中3");
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
            } else if (kind.equals(getRec().getString(R.string.x4))) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_selection_four));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":选四-中4");
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
            } else if (kind.equals(getRec().getString(R.string.x5))) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_selection_five));
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":选五-中5");
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
            }
            playDetailBean.setLr(getNumbynum(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getNumbynum(playDetailBean.getNum(), 2));
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  bjkl8  其他 和值的基础数据源
    public static List<PlayTypeBean.PlayBean> initBJKL8OtherData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("以所有开出的全部20个号码加起来的和值来判定。 \n" +
                "总单/双：20个号码加总的和值为单，叫做和单；20个号码加总的和值为双，叫做和双。 \n" +
                "总大/小：20个号码加总的和值大于810，为和大；20个号码加总的和值小于810，则为和小。 \n" +
                "和值810：20个号码加总的和值等于810，叫和值810。 ");
        playBean1.setSampleBet("开奖号码为1，2，3，4，5，6，7，8，9，10，11，12，13，14，15，16，17，18，19，20；那么此20个开奖号码的和值总和为210，则为小，为双。则投注小和双者中奖。投注大、单、和值810者不中奖。");
        playBean1.setSingleExplain("810");
        playBean1.setPlayTypeName(getRec().getString(R.string.bj8_hz));

        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("上下盘：开奖号码1至40为上盘号码，41至80为下盘号码。 \n" +
                "开出的20个号码中：如上盘号码（1-40）在此局开出号码数目占多数时，此局为上盘； \n" +
                "下盘号码（41-80）在此局开出号码数目占多数时，此局为下盘； \n" +
                "上盘号码（1－40）和下盘号码（41-80）在此局开出的数目相同时（各10个数字），此局为中盘");
        playBean2.setSampleBet("※举例：此局开出1，2，3，4，5，6，7，8，9，10，11，12，13，14，15，16，17，18，19，20. 此局为上盘。 \n" +
                "此局开出41，42，43，44，45，46，47，48，49，50，51，52，53，54，55，56，57，58，59，60 此局为下盘。 \n" +
                "此局开出1，2，3，4，5，6，7，8，9，10，41，42，43，44，45，46，47，48，49，50 此局为中盘。");
        playBean2.setSingleExplain("上");
        playBean2.setPlayTypeName(getRec().getString(R.string.bj8_szxp));


        PlayTypeBean.PlayBean playBean3 = new PlayTypeBean.PlayBean();
        playBean3.setPlayTypeExplain("开奖号码中1，3，5，7，…，75，77，79为奇数号码，2，4，6，8，……，76，78，80为偶数号码。 当期开出的20个中奖号码中，如奇数号码数目占多数时（超过10个），则为奇盘，投注奇者中奖； 偶数号码占多数时（超过10个），则为偶盘，投注偶者中奖，如果奇数和偶数号码数目相同时（均为 10个），则为和，投注和者中奖。 ");
        playBean3.setSampleBet("※举例：此期开出1，3，5，7，9，11，13，15，17，19，21，22，24，26，28，30，32，34，46，68， 其中奇数11个偶数9个，此期为奇盘。 \n" +
                "：此期开出2，4，6，8，10，12，14，16，44，48，66，68，25，27，31，35，37，39，41，55， 其中偶数12个奇数8个，此期为偶盘。 \n" +
                "：此期开出2，4，6，8，10，12，14，16，18，20，41，43，45，47，49，51，53，55，57，59， 其中奇数10个偶数10个，此期为和。");
        playBean3.setSingleExplain("奇");
        playBean3.setPlayTypeName(getRec().getString(R.string.bj8_qohp));


        PlayTypeBean.PlayBean playBean4 = new PlayTypeBean.PlayBean();
        playBean4.setPlayTypeExplain("开出的20个号码的总和分在5个段，以金、木、水、火、土命名：金（210～695）、木（696～763）、水（764～855）、火（856～923）和土（924～1410）。");
        playBean4.setSampleBet("开奖号码为01、04、05、10、11、13、20、27、30、32、33、36、40、47、54、59、61、64、67、79，总和是693，则总分数在210－695段内， 则开出的是「金」。下注「金」为赢，反之则输。");
        playBean4.setSingleExplain("金");
        playBean4.setPlayTypeName(getRec().getString(R.string.bj8_wx));

        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        playBeanList.add(playBean3);
        playBeanList.add(playBean4);
        return playBeanList;
    }

    //初始化 bjkl8 other 的布局 的数据源
    public static List<PlayDetailBean> init_Bjkl8_onther_Data(String kind) {

        String[] arrays = new String[0];
        if (kind.equals(getRec().getString(R.string.bj8_hz))) {
            arrays = new String[]{"单", "双", "大", "小", "810"};
        } else if (kind.equals(getRec().getString(R.string.bj8_szxp))) {
            arrays = new String[]{"上", "中", "下"};
        } else if (kind.equals(getRec().getString(R.string.bj8_qohp))) {
            arrays = new String[]{"奇", "偶", "和"};
        } else if (kind.equals(getRec().getString(R.string.bj8_wx))) {
            arrays = new String[]{"金", "木", "水", "火", "土"};
        }


        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(kind);
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setCode(getRec().getString(R.string.bjkl8));
            if (kind.equals(getRec().getString(R.string.bj8_hz))) {
                if (playDetailBean.getNum().equals(arrays[4])) {
                    playDetailBean.setOdd("");
                    playDetailBean.setPlayCode(getRec().getString(R.string.keno_sum20_big_small));
                } else if (playDetailBean.getNum().equals(arrays[2]) || playDetailBean.getNum().equals(arrays[3])) {
                    playDetailBean.setOdd("");
                    playDetailBean.setPlayCode(getRec().getString(R.string.keno_sum20_big_small));
                } else if (playDetailBean.getNum().equals(arrays[0]) || playDetailBean.getNum().equals(arrays[1])) {
                    playDetailBean.setOdd("");
                    playDetailBean.setPlayCode(getRec().getString(R.string.keno_sum20_single_double));
                }
                playDetailBean.setBetCode(getRec().getString(R.string.keno_sum20));
            } else if (kind.equals(getRec().getString(R.string.bj8_szxp))) {
                if (playDetailBean.getNum().equals(arrays[1])) {
                    playDetailBean.setOdd("");
                } else {
                    playDetailBean.setOdd("");
                }
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_up_down));
                playDetailBean.setBetCode(getRec().getString(R.string.keno_number));
            } else if (kind.equals(getRec().getString(R.string.bj8_qohp))) {
                if (playDetailBean.getNum().equals(arrays[1])) {
                    playDetailBean.setOdd("");
                } else {
                    playDetailBean.setOdd("");
                }
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_odd_even));
                playDetailBean.setBetCode(getRec().getString(R.string.keno_number));
            } else if (kind.equals(getRec().getString(R.string.bj8_wx))) {
                if (playDetailBean.getNum().equals(arrays[0]) || playDetailBean.getNum().equals(arrays[4])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[1]) || playDetailBean.getNum().equals(arrays[3])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[2])) {
                    playDetailBean.setOdd("");
                }
                playDetailBean.setPlayCode(getRec().getString(R.string.keno_sum20_elements));
                playDetailBean.setBetCode(getRec().getString(R.string.keno_sum20));
            }
            playDetailBean.setLr(getNumbyWord(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getNumbyWord(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    public static int getBackShapeColor(String num) {
        switch (num) {
            case "0":
            case "13":
            case "14":
            case "27":
                return R.drawable.shape_ball_gray;
            case "1":
            case "4":
            case "7":
            case "10":
            case "16":
            case "19":
            case "22":
            case "25":
                return R.drawable.shape_ball_green;
            case "2":
            case "5":
            case "8":
            case "11":
            case "17":
            case "20":
            case "23":
            case "26":
                return R.drawable.shape_ball_blue;
            case "3":
            case "6":
            case "9":
            case "12":
            case "15":
            case "18":
            case "21":
            case "24":
                return R.drawable.shape_ball_red;
            default:
                return 0;
        }
    }

    private static String getNumbyCount(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] opencodes = handicap.getOpenCode().split(",");
            if (opencodes.length != 3) {
                continue;
            }
            int countNum = 0;
            for (String item : opencodes) {
                countNum += Integer.parseInt(item);
            }
            if (String.valueOf(countNum).equalsIgnoreCase(betNum)) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else {
                if (betNum.equalsIgnoreCase("大")) {
                    if (countNum > 13) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("小")) {
                    if (countNum < 14) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("单")) {
                    if (countNum % 2 == 1) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("双")) {
                    if (countNum % 2 == 0) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("极大")) {
                    if (countNum > 21) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("极小")) {
                    if (countNum < 6) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("大单")) {
                    if (countNum > 13 && countNum % 2 == 1) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("大双")) {
                    if (countNum > 13 && countNum % 2 == 0) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("小单")) {
                    if (countNum < 14 && countNum % 2 == 1) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("小双")) {
                    if (countNum < 14 && countNum % 2 == 0) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("豹子")) {
                    if (opencodes[0].equalsIgnoreCase(opencodes[1]) && opencodes[1].equalsIgnoreCase(opencodes[2])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("红波")) {
                    if (Arrays.asList(RED).contains(countNum)) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                }
                if (betNum.equalsIgnoreCase("蓝波")) {
                    if (Arrays.asList(BLUE).contains(countNum)) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                }
                if (betNum.equalsIgnoreCase("绿波")) {
                    if (Arrays.asList(GREEN).contains(countNum)) {
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

    /**
     * 北京快乐8数字玩法冷热 遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumbynum(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
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
     * 北京快乐8文字玩法冷热 遗漏
     *
     * @param betNum
     * @return
     */
    private static String getNumbyWord(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            List<String> openCodes = Arrays.asList(handicap.getOpenCode().split(","));
            if (openCodes.size() != 20) {
                continue;
            }
            if (betNum.equalsIgnoreCase("单")) {
                if (getCount(openCodes) % 2 == 1) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("双")) {
                if (getCount(openCodes) % 2 == 0) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("大")) {
                if (getCount(openCodes) > 810) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("小")) {
                if (getCount(openCodes) < 810) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("810")) {
                if (getCount(openCodes) == 810) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("上")) {
                if (Integer.parseInt(openCodes.get(10)) <= 40) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("下")) {
                if (Integer.parseInt(openCodes.get(9)) >= 41) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("中")) {
                if (Integer.parseInt(openCodes.get(9)) < 41 && Integer.parseInt(openCodes.get(10)) > 40) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("奇")) {
                if (getEvenCount(openCodes) < 10) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("偶")) {
                if (getEvenCount(openCodes) > 10) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("和")) {
                if (getEvenCount(openCodes) == 10) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("金")) {
                if (getCount(openCodes) < 696) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("木")) {
                int numCount = getCount(openCodes);
                if (numCount > 695 && num < 764) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("水")) {
                int numCount = getCount(openCodes);
                if (numCount > 763 && num < 856) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("火")) {
                int numCount = getCount(openCodes);
                if (numCount > 855 && num < 924) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("土")) {
                int numCount = getCount(openCodes);
                if (numCount > 925) {
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


    public static int getCount(List<String> openCodes) {
        int countNum = 0;
        for (String item : openCodes) {
            countNum += Integer.parseInt(item);
        }
        return countNum;
    }

    /**
     * 偶数
     *
     * @param openCodes
     * @return
     */
    public static int getEvenCount(List<String> openCodes) {
        int countNum = 0;
        for (String item : openCodes) {
            if (Integer.parseInt(item) % 2 == 0) {
                countNum++;
            }
        }
        return countNum;
    }

    /**
     * 北京快乐8的 合数单双金木水火土
     */
    public static String getBjkl8RewardStatus(String openCode, TextView tv_ge, TextView tv_sb, TextView tv_doubleorSingle, TextView tv_jmsht) {
        if (TextUtils.isEmpty(openCode)) return "";
        int he = 0;
        String sb = "";
        String doubleorSingle = "";
        String sx = "";
        String oh = "";
        String jmsht = "";

        List<String> openCodes = Arrays.asList(openCode.split(","));
        for (String code : openCodes) {
            he = he + Integer.parseInt(code);
        }

        if (he >= 810) {
            sb = "大";
        } else {
            sb = "小";
        }

        if (he % 2 == 0) {
            doubleorSingle = "双";
        } else {
            doubleorSingle = "单";
        }

        if (Integer.parseInt(openCodes.get(10)) <= 40) {
            sx = "上";
        } else if (Integer.parseInt(openCodes.get(9)) >= 41) {
            sx = "中";
        } else if (Integer.parseInt(openCodes.get(9)) < 41 && Integer.parseInt(openCodes.get(10)) > 40) {
            sx = "下";
        }

        if (getEvenCount(openCodes) == 10) {
            oh = "和";
        } else if (getEvenCount(openCodes) > 10) {
            oh = "偶";
        } else {
            oh = "奇";
        }

        if (getCount(openCodes) < 696) {
            jmsht = "金";
        } else if (getCount(openCodes) > 695 && getCount(openCodes) < 764) {
            jmsht = "木";
        } else if (getCount(openCodes) > 763 && getCount(openCodes) < 856) {
            jmsht = "水";
        } else if (getCount(openCodes) > 855 && getCount(openCodes) < 924) {
            jmsht = "火";
        } else if (getCount(openCodes) > 925) {
            jmsht = "土";
        }

        tv_ge.setText("" + he);
        tv_sb.setText(sb);
        tv_doubleorSingle.setText(doubleorSingle);
        tv_jmsht.setText(jmsht);

        return "和: " + he + " " + sb + " " + doubleorSingle + " " + sx + " " + oh + " " + jmsht;
    }

}
