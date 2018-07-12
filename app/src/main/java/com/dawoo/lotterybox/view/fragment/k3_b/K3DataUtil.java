package com.dawoo.lotterybox.view.fragment.k3_b;

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

/**
 * Created by archar on 18-3-21.
 */

public class K3DataUtil {
    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    private synchronized static List<Handicap> getHandicaps() {
        return BLotteryOddFactory.getInstance().getmHistorys();
    }

    //初始化  jsk3 b 点数的基础数据源
    public static List<PlayTypeBean.PlayBean> initDSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("三个开奖号码总和值11~17 为大；总和值4~10 为小；三个开奖号码总和5、7、9、11、13、15、17为单；4、6、8、10、12、14、16为双；若三个号码相同、则不算中奖。");
        playBean1.setSampleBet("开奖号码：5,6,6。选了大，中奖");
        playBean1.setSingleExplain("大");
        playBean1.setPlayTypeName(getRec().getString(R.string.SM));


        PlayTypeBean.PlayBean playBean2 = new PlayTypeBean.PlayBean();
        playBean2.setPlayTypeExplain("开奖号码总和值为4、5、6、7、8、9、10、11、12、13、14、15、16、17 时，即为中奖； ");
        playBean2.setSampleBet("千位选了9，开奖号码千位是9，就中奖了");
        playBean2.setSingleExplain("9");
        playBean2.setPlayTypeName(getRec().getString(R.string.DS));


        playBeanList.add(playBean1);
        playBeanList.add(playBean2);
        return playBeanList;
    }

    //初始化 jsk3 b 点数的布局 的数据源
    public static List<PlayDetailBean> init_DS_Data(String kind) {
        String[] arrays = new String[0];
        if (kind.equals(getRec().getString(R.string.DS))) {
            arrays = new String[]{"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        } else if (kind.equals(getRec().getString(R.string.SM))) {
            arrays = new String[]{"大", "小", "单", "双"};
        }

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.DS));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            if (kind.equals(getRec().getString(R.string.DS))) {
                if (playDetailBean.getNum().equals(arrays[0])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[1])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[2])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[3])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[4])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[5])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[6])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[7])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[8])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[9])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[10])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[11])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[12])) {
                    playDetailBean.setOdd("");
                } else if (playDetailBean.getNum().equals(arrays[13])) {
                    playDetailBean.setOdd("");
                }
            } else if (kind.equals(getRec().getString(R.string.SM))) {
                if (playDetailBean.getNum().equals(arrays[0])) {
                    playDetailBean.setOdd("");
                } else {
                    playDetailBean.setOdd("");
                }
            }
            playDetailBean.setCode(getRec().getString(R.string.jsk3));
            if (playDetailBean.getNum().equals("4") || playDetailBean.getNum().equals("17")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_417));
            } else if (playDetailBean.getNum().equals("5") || playDetailBean.getNum().equals("16")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_516));
            } else if (playDetailBean.getNum().equals("6") || playDetailBean.getNum().equals("15")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_615));
            } else if (playDetailBean.getNum().equals("7") || playDetailBean.getNum().equals("14")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_714));
            } else if (playDetailBean.getNum().equals("8") || playDetailBean.getNum().equals("13")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_813));
            } else if (playDetailBean.getNum().equals("9") || playDetailBean.getNum().equals("12")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_912));
            } else if (playDetailBean.getNum().equals("10") || playDetailBean.getNum().equals("11")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_1011));
            } else if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_big_small));
            } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                playDetailBean.setPlayCode(getRec().getString(R.string.points_single_double));
            }
            playDetailBean.setBetCode(getRec().getString(R.string.points));
            playDetailBean.setLr(getCountNum(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getCountNum(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  jsk3 b 三军的基础数据源
    public static List<PlayTypeBean.PlayBean> initSJData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("三个开奖号码其中一个与所选号码相同时、即为中奖。");
        playBean1.setSampleBet("如开奖号码为1、1、3，则投注三军1或三军3皆视为中奖。");
        playBean1.setSingleExplain("一点");
        playBean1.setPlayTypeName(getRec().getString(R.string.SJ));

        playBeanList.add(playBean1);
        return playBeanList;
    }


    //初始化 jsk3 b 三军的布局 的数据源
    public static List<PlayDetailBean> init_SJ_Data(String kind) {
        String[] arrays = new String[]{"1", "2", "3", "4", "5", "6"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.SJ));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd("");
            playDetailBean.setCode(getRec().getString(R.string.jsk3));
            playDetailBean.setPlayCode("armed_forces");
            playDetailBean.setBetCode("armed_forces");
            playDetailBean.setLr(getOneNum(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getOneNum(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  jsk3 b 围骰/全筛的基础数据源
    public static List<PlayTypeBean.PlayBean> initWSQSData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖号码三字同号、且与所选择的围骰组合相符时，即为中奖;全选围骰组合、开奖号码三字同号，即为中奖。");
        playBean1.setSampleBet("如开奖号码为1、1、1，则投注111皆视为中奖。");
        playBean1.setSingleExplain("111");
        playBean1.setPlayTypeName(getRec().getString(R.string.WS_QS));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 jsk3 b 围骰/全筛的布局 的数据源
    public static List<PlayDetailBean> init_WS_QS_Data(String kind) {
        String[] arrays = new String[]{"111", "222", "333", "444", "555", "666", "全骰"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.WS_QS));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            if (playDetailBean.getNum().equals("全骰")) {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.full_dice));
            } else {
                playDetailBean.setOdd("");
                playDetailBean.setPlayCode(getRec().getString(R.string.dice));
            }
            playDetailBean.setCode(getRec().getString(R.string.jsk3));
            playDetailBean.setLr(getSimpleNum(playDetailBean.getNum(),1));
            playDetailBean.setYl(getSimpleNum(playDetailBean.getNum(),2));
            playDetailBean.setBetCode("dice_full_dice");
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  jsk3 b 长盘的基础数据源
    public static List<PlayTypeBean.PlayBean> initCPData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("任选一长牌组合、当开奖结果任2码与所选组合相同时，即为中奖。 ");
        playBean1.setSampleBet("如开奖号码为1、2、3、则投注长牌12、长牌23、长牌13皆视为中奖。");
        playBean1.setSingleExplain("1、2");
        playBean1.setPlayTypeName(getRec().getString(R.string.CP));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 jsk3 b 长盘的布局 的数据源
    public static List<PlayDetailBean> init_CP_Data(String kind) {
        String[] arrays = new String[]{"12", "13", "14", "15", "16", "23", "24", "25", "26", "34", "35", "36", "45", "46", "56"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.CP));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd("");
            playDetailBean.setCode(getRec().getString(R.string.jsk3));
            playDetailBean.setPlayCode(getRec().getString(R.string.long_card));
            playDetailBean.setBetCode(getRec().getString(R.string.long_card));
            playDetailBean.setLr(getunLikeNum(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getunLikeNum(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }


    //初始化  jsk3 b 短盘的基础数据源
    public static List<PlayTypeBean.PlayBean> initDPData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("开奖号码任两字同号、且与所选择的短牌组合相符时，即为中奖。");
        playBean1.setSampleBet("如开奖号码为1、1、3、则投注短牌1、1，即为中奖。");
        playBean1.setSingleExplain("2、2");
        playBean1.setPlayTypeName(getRec().getString(R.string.DP));

        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 jsk3 b 短盘的布局 的数据源
    public static List<PlayDetailBean> init_DP_Data(String kind) {
        String[] arrays = new String[]{"11", "22", "33", "44", "55", "66"};

        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(getRec().getString(R.string.DP));
            playDetailBean.setKind(kind);
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setOdd("");
            playDetailBean.setCode(getRec().getString(R.string.jsk3));
            playDetailBean.setPlayCode(getRec().getString(R.string.short_card));
            playDetailBean.setBetCode(getRec().getString(R.string.short_card));
            playDetailBean.setLr(getDoubleNum(playDetailBean.getNum(), 1));
            playDetailBean.setYl(getDoubleNum(playDetailBean.getNum(), 2));
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    public static int getFastIcon(String code) {
        switch (code) {
            case "1":
                return R.mipmap.fast1;
            case "2":
                return R.mipmap.fast2;
            case "3":
                return R.mipmap.fast3;
            case "4":
                return R.mipmap.fast4;
            case "5":
                return R.mipmap.fast5;
            case "6":
                return R.mipmap.fast6;
            default:
                return 0;
        }
    }

    /**
     * 和值 的冷热 遗漏数据
     *
     * @param betNum
     * @return
     */
    public static String getCountNum(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            int count = 0;
            for (String item : openCodes) {
                count += Integer.valueOf(item);
            }
            if (betNum.equalsIgnoreCase(String.valueOf(count))) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("大")) {
                if (count > 10) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("小")) {
                if (count < 11) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("单")) {
                if (count % 2 == 1) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("双")) {
                if (count % 2 == 0) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
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
     * 三军玩法
     *
     * @param betNum
     * @return
     */
    public static String getOneNum(String betNum, int type) {
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
     * 豹子玩法的冷热遗漏数据
     *
     * @param betNum
     * @return
     */
    public static String getSimpleNum(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length != 3) {
                continue;
            }
            if (openCodes[0].equalsIgnoreCase(openCodes[1]) && openCodes[1].equalsIgnoreCase(openCodes[2])) {
                if (betNum.equalsIgnoreCase("全骰")) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                } else if (betNum.contains(openCodes[0])) {
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
     * 二同号
     *
     * @param betNum
     * @return
     */
    private static String getDoubleNum(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            List<String> opencodes = Arrays.asList(handicap.getOpenCode().split(","));
            Collections.sort(opencodes);
            if (opencodes.get(0).equalsIgnoreCase(opencodes.get(1)) || opencodes.get(1).equalsIgnoreCase(opencodes.get(2))) {
                if (betNum.contains(opencodes.get(1))) {
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
     * 二不同号
     *
     * @param betNum
     * @return
     */
    private static String getunLikeNum(String betNum, int type) {
        if (getHandicaps().isEmpty()) {
            return "";
        }
        int num = 0;
        List<String> betNums = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            betNums.add(betNum.charAt(i) + "");
        }
        for (Handicap handicap : getHandicaps()) {
            String opencodes = handicap.getOpenCode();
            if (opencodes.contains(betNums.get(0)) && opencodes.contains(betNums.get(1))) {
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
