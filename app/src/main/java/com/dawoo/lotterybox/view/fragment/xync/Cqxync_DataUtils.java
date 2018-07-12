package com.dawoo.lotterybox.view.fragment.xync;

import android.content.Context;
import android.content.res.Resources;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.initdata.BLotteryOddFactory;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 18-3-9.
 */

public class Cqxync_DataUtils {
    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    private synchronized static List<Handicap> getHandicaps() {
        return BLotteryOddFactory.getInstance().getmHistorys();
    }

    //初始化重庆幸运农场   龙虎盘数据
    public static List<PlayTypeBean.PlayBean> initCQXYNDiaTigData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();
        for (int i = 0; i < BoxApplication.getContext().getResources().getStringArray(R.array.cqxync_DATDate).length; i++) {
            PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
            playBean.setPlayTypeExplain("I just fill in the data");
            playBean.setSampleBet("I just fill in the data");
            playBean.setPlayTypeName(BoxApplication.getContext().getResources().getStringArray(R.array.cqxync_DATDate)[i]);
            playBeanList.add(playBean);
        }
        return playBeanList;
    }

    //初始化幸运农场布局数据源
    public static List<PlayDetailBean> init_CQXYNC_DvsT_Data(String kind) {
        String[] arrays;
        arrays = new String[]{"龙", "虎"};
        List<PlayDetailBean> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setNum(arrays[i]);
            playDetailBean.setType(BoxApplication.getContext().getResources().getString(R.string.sfc_dragon_tiger));
            playDetailBean.setKind(kind);
            playDetailBean.setOdd(BoxApplication.getContext().getString(R.string.bv_1_985));
            playDetailBean.setPlayCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger));
            playDetailBean.setCode(BoxApplication.getContext().getResources().getString(R.string.cqxync));


            if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT2))) {
                playDetailBean.setLr(getNumByCompare(0, 1, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_12));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT3))) {
                playDetailBean.setLr(getNumByCompare(0, 2, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_13));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT4))) {
                playDetailBean.setLr(getNumByCompare(0, 3, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_14));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT5))) {
                playDetailBean.setLr(getNumByCompare(0, 4, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_15));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT6))) {
                playDetailBean.setLr(getNumByCompare(0, 5, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_16));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT7))) {
                playDetailBean.setLr(getNumByCompare(0, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_17));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D1vsT8))) {
                playDetailBean.setLr(getNumByCompare(0, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_18));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT3))) {
                playDetailBean.setLr(getNumByCompare(1, 2, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_23));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT4))) {
                playDetailBean.setLr(getNumByCompare(1, 3, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_24));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT5))) {
                playDetailBean.setLr(getNumByCompare(1, 4, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_25));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT6))) {
                playDetailBean.setLr(getNumByCompare(1, 5, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_26));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT7))) {
                playDetailBean.setLr(getNumByCompare(1, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_27));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D2vsT8))) {
                playDetailBean.setLr(getNumByCompare(1, 8, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_28));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D3vsT4))) {
                playDetailBean.setLr(getNumByCompare(2, 3, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_34));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D3vsT5))) {
                playDetailBean.setLr(getNumByCompare(2, 4, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_35));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D3vsT6))) {
                playDetailBean.setLr(getNumByCompare(2, 5, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_36));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D3vsT7))) {
                playDetailBean.setLr(getNumByCompare(2, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_37));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D3vsT8))) {
                playDetailBean.setLr(getNumByCompare(2, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_38));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D4vsT5))) {
                playDetailBean.setLr(getNumByCompare(3, 4, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_45));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D4vsT6))) {
                playDetailBean.setLr(getNumByCompare(3, 5, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_46));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D4vsT7))) {
                playDetailBean.setLr(getNumByCompare(3, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_47));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D4vsT8))) {
                playDetailBean.setLr(getNumByCompare(3, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_48));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D5vsT6))) {
                playDetailBean.setLr(getNumByCompare(4, 5, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_56));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D5vsT7))) {
                playDetailBean.setLr(getNumByCompare(4, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_57));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D5vsT8))) {
                playDetailBean.setLr(getNumByCompare(4, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_58));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D6vsT7))) {
                playDetailBean.setLr(getNumByCompare(5, 6, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_67));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D6vsT8))) {
                playDetailBean.setLr(getNumByCompare(5, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_68));
            } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.D7vsT8))) {
                playDetailBean.setLr(getNumByCompare(6, 7, playDetailBean.getNum()));
                playDetailBean.setBetCode(BoxApplication.getContext().getString(R.string.sfc_dragon_tiger_78));
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
     * 这边不需要填充那些数据
     *
     * @return
     */
    //初始化重庆幸运农场
    public static List<PlayTypeBean.PlayBean> initCQXYNCSMData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();
        for (int i = 0; i < BoxApplication.getContext().getResources().getStringArray(R.array.cqxync_SMDate).length; i++) {
            PlayTypeBean.PlayBean playBean = new PlayTypeBean.PlayBean();
            playBean.setPlayTypeExplain("I just fill in the data");
            playBean.setSampleBet("I just fill in the data");
            playBean.setPlayTypeName(BoxApplication.getContext().getResources().getStringArray(R.array.cqxync_SMDate)[i]);
            playBeanList.add(playBean);
        }
        return playBeanList;
    }

    //    //初始化幸运农场布局数据源
    public static List<PlayDetailBean> init_CQXYNC_SM_Data(String kind) {
        String[] arrays;
        if (kind.equals(getRec().getString(R.string.sum))) {
            arrays = new String[]{"总单", "总双", "总大", "总小", "总尾大", "总尾小"};
            List<PlayDetailBean> list = new ArrayList<>();
            for (int i = 0; i < arrays.length; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(BoxApplication.getContext().getResources().getString(R.string.SM));
                playDetailBean.setNum(arrays[i]);
                playDetailBean.setKind(kind);
                playDetailBean.setOdd(BoxApplication.getContext().getResources().getString(R.string.bv_1_985));
                playDetailBean.setCode(BoxApplication.getContext().getResources().getString(R.string.cqxync));
                playDetailBean.setLr(getNumByCount(playDetailBean.getNum()));
                if (playDetailBean.getNum().equals("总大") || playDetailBean.getNum().equals("总小")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sum8_big_small));
                } else if (playDetailBean.getNum().equals("总单") || playDetailBean.getNum().equals("总双")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sum8_single_double));
                } else if (playDetailBean.getNum().equals("总尾大") || playDetailBean.getNum().equals("总尾小")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sum8_mantissa_big_small));
                }

                if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.sum))) {
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sum8));
                }
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
            return list;
        } else {
            arrays = new String[]{"大", "小", "单", "双", "尾大", "尾小", "合单", "合双"};
            List<PlayDetailBean> list = new ArrayList<>();
            for (int i = 0; i < arrays.length; i++) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(BoxApplication.getContext().getResources().getString(R.string.SM));
                playDetailBean.setNum(arrays[i]);
                playDetailBean.setKind(kind);
                playDetailBean.setOdd(BoxApplication.getContext().getResources().getString(R.string.bv_1_985));
                playDetailBean.setCode(BoxApplication.getContext().getResources().getString(R.string.cqxync));
                if (playDetailBean.getNum().equals("大") || playDetailBean.getNum().equals("小")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_big_small));
                } else if (playDetailBean.getNum().equals("单") || playDetailBean.getNum().equals("双")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_single_double));
                } else if (playDetailBean.getNum().equals("尾大") || playDetailBean.getNum().equals("尾小")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_mantissa_big_small));
                } else if (playDetailBean.getNum().equals("合单") || playDetailBean.getNum().equals("合双")) {
                    playDetailBean.setPlayCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sum_single_double));
                }

                if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.first_ball))) {
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_first));
                    playDetailBean.setLr(getNumByIndex(0, playDetailBean.getNum()));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.second_ball))) {
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_second));
                    playDetailBean.setLr(getNumByIndex(1, playDetailBean.getNum()));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.third_ball))) {
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_third));
                    playDetailBean.setLr(getNumByIndex(2, playDetailBean.getNum()));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.fourth_ball))) {
                    playDetailBean.setLr(getNumByIndex(3, playDetailBean.getNum()));
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_fourth));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.fifth_ball))) {
                    playDetailBean.setLr(getNumByIndex(4, playDetailBean.getNum()));
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_fifth));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.sixth_ball))) {
                    playDetailBean.setLr(getNumByIndex(5, playDetailBean.getNum()));
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_sixth));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.seventh_ball))) {
                    playDetailBean.setLr(getNumByIndex(6, playDetailBean.getNum()));
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_seventh));
                } else if (playDetailBean.getKind().equals(BoxApplication.getContext().getResources().getString(R.string.eighth_ball))) {
                    playDetailBean.setLr(getNumByIndex(7, playDetailBean.getNum()));
                    playDetailBean.setBetCode(BoxApplication.getContext().getResources().getString(R.string.sfc_eighth));
                }
                LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
                if (lotteryOddBean != null) {
                    playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
                }
                list.add(playDetailBean);
            }
            return list;
        }
    }

    //初始化数据
    public static List<PlayTypeBean.PlayBean> initCQXYNCBGYHData() {
        List<PlayTypeBean.PlayBean> playBeanList = new ArrayList<>();

        PlayTypeBean.PlayBean playBean1 = new PlayTypeBean.PlayBean();
        playBean1.setPlayTypeExplain("I just fill in the data");
        playBean1.setSampleBet("I just fill in the data");
        playBean1.setPlayTypeName(BoxApplication.getContext().getResources().getString(R.string.first_ball));
        playBeanList.add(playBean1);
        return playBeanList;
    }

    //初始化 球 的布局 的数据源
    public static List<PlayDetailBean> init_CQXYNC_LH_Data(String kid, String TypeNumber) {
        String[] arrays;
        arrays = new String[]{"19.8", "19.8", "19.8", "19.8",
                "19.8", "19.8", "19.8", "19.8",
                "19.8", "19.8", "19.8", "19.8",
                "19.8", "19.8", "19.8", "19.8",
                "19.8", "19.8", "19.8", "19.8"};
        List<PlayDetailBean> list = new ArrayList<>();
        String betCode = "";
        int codeIndex = 0;
        if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.first_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_first);
            codeIndex = 0;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.second_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_second);
            codeIndex = 1;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.third_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_third);
            codeIndex = 2;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.fourth_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_fourth);
            codeIndex = 3;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.fifth_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_fifth);
            codeIndex = 4;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.sixth_ball))) {
            codeIndex = 5;
            betCode = BoxApplication.getContext().getString(R.string.sfc_sixth);
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.seventh_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_seventh);
            codeIndex = 6;
        } else if (TypeNumber.equals(BoxApplication.getContext().getString(R.string.eighth_ball))) {
            betCode = BoxApplication.getContext().getString(R.string.sfc_eighth);
            codeIndex = 7;
        } else {
            betCode = "";
        }
        for (int i = 0; i < arrays.length; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setNum(i < 9 ? "0" + (i + 1) + "" : (i + 1) + "");
            playDetailBean.setType(TypeNumber);
            playDetailBean.setKind(TypeNumber);
            playDetailBean.setOdd(arrays[i]);
            playDetailBean.setLr(getNumByIndex(codeIndex, playDetailBean.getNum()));
            playDetailBean.setPlayCode(BoxApplication.getContext().getString(R.string.sfc_digital));
            playDetailBean.setBetCode(betCode);
            playDetailBean.setCode(BoxApplication.getContext().getResources().getString(R.string.cqxync));
//            list.add(playDetailBean);
            LotteryOddBean lotteryOddBean = getOddMap().get(playDetailBean.getBetCode() + ":" + playDetailBean.getNum());
            if (lotteryOddBean != null) {
                playDetailBean.setOdd(lotteryOddBean.getOdd() + "");
            }
            list.add(playDetailBean);
        }
        return list;
    }

    /**
     * @param codeNumber
     * @return
     */
    public static int getCode(String codeNumber) {
        switch (codeNumber) {
            case "01":
                return R.mipmap.sfc1;
            case "02":
                return R.mipmap.sfc2;
            case "03":
                return R.mipmap.sfc3;
            case "04":
                return R.mipmap.sfc4;
            case "05":
                return R.mipmap.sfc5;
            case "06":
                return R.mipmap.sfc6;
            case "07":
                return R.mipmap.sfc7;
            case "08":
                return R.mipmap.sfc8;
            case "09":
                return R.mipmap.sfc9;
            case "10":
                return R.mipmap.sfc10;
            case "11":
                return R.mipmap.sfc11;
            case "12":
                return R.mipmap.sfc12;
            case "13":
                return R.mipmap.sfc13;
            case "14":
                return R.mipmap.sfc14;
            case "15":
                return R.mipmap.sfc15;
            case "16":
                return R.mipmap.sfc16;
            case "17":
                return R.mipmap.sfc17;
            case "18":
                return R.mipmap.sfc18;
            case "19":
                return R.mipmap.sfc19;
            case "20":
                return R.mipmap.sfc20;
            default:
                return 0;
        }
    }

    /**
     * 设置大小单双总和
     *
     * @param nums
     * @return
     */
    public static String bigAndDorL(String[] nums) {
        Context context = BoxApplication.getContext();
        int ConutNumber = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1])
                + Integer.valueOf(nums[2])
                + Integer.valueOf(nums[3])
                + Integer.valueOf(nums[4])
                + Integer.valueOf(nums[5])
                + Integer.valueOf(nums[6])
                + Integer.valueOf(nums[7]);
        String bigOrSmall = ConutNumber > 81 ? context.getResources().getString(R.string.big) : context.getResources().getString(R.string.small);
        String singleOrDouble = ConutNumber % 2 == 0 ? context.getString(R.string.double_) : context.getResources().getString(R.string.single);
        return String.valueOf(ConutNumber) + " " + bigOrSmall + " " + singleOrDouble;
    }

    static int getCount(String[] nums) {
        int ConutNumber = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1])
                + Integer.valueOf(nums[2])
                + Integer.valueOf(nums[3])
                + Integer.valueOf(nums[4])
                + Integer.valueOf(nums[5])
                + Integer.valueOf(nums[6])
                + Integer.valueOf(nums[7]);
        return ConutNumber;
    }

    private static String getNumByCount(String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicaps().size() == 0) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] opencodes = handicap.getOpenCode().split(",");
            if (opencodes.length < 8) {
                continue;
            }
            int countNum = getCount(opencodes);
            if (betNum.equalsIgnoreCase("总大")) {
                if (countNum > 81) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总小")) {
                if (countNum < 81) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总单")) {
                if (countNum % 2 == 1) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总双")) {
                if (countNum % 2 == 0) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总尾大")) {
                if (isLastBig(countNum + "")) {
                    if (type == 1) {
                        num++;
                    } else {
                        break;
                    }
                }
            } else if (betNum.equalsIgnoreCase("总尾小")) {
                if (!isLastBig(countNum + "")) {
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
     * 单个球的冷热
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getNumByIndex(int index, String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicaps().size() == 0) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] opencodes = handicap.getOpenCode().split(",");
            if (opencodes.length <= index) {
                continue;
            }
            if (opencodes[index].equalsIgnoreCase(betNum)) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else {
                if (betNum.equalsIgnoreCase("大")) {
                    if (isBig(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("小")) {
                    if (!isBig(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("单")) {
                    if (!isEven(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("双")) {
                    if (isEven(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("尾大")) {
                    if (isLastBig(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("尾小")) {
                    if (!isLastBig(opencodes[index])) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("合单")) {
                    if (!isEven(getCountNum(opencodes[index]))) {
                        if (type == 1) {
                            num++;
                        } else {
                            break;
                        }
                    }
                } else if (betNum.equalsIgnoreCase("合双")) {
                    if (isEven(getCountNum(opencodes[index]))) {
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
     * 单个球大小
     *
     * @param code
     * @return
     */
    static boolean isBig(String code) {
        int coding = Integer.parseInt(code);
        if (coding >= 11) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isLastBig(String code) {
        if (code.length() > 1) {
            code = code.substring(1);
        }
        int coding = Integer.parseInt(code);
        if (coding >= 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取合数
     *
     * @param code
     * @return
     */
    static String getCountNum(String code) {
        if (code.length() == 1) {
            return code;
        } else {
            int first = Integer.parseInt(code.substring(0, 1));
            int sec = Integer.parseInt(code.substring(1));
            int count = first + sec;
            return String.valueOf(count);
        }
    }

    /**
     * 偶数
     *
     * @param code
     * @return
     */
    static boolean isEven(String code) {
        int coding = Integer.parseInt(code);
        if (coding % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    static String getNumByCompare(int first, int sec, String betNum) {
        int type = BaseLotteryBActivity.changeType;
        if (type == 0 || getHandicaps().size() == 0) {
            return "";
        }
        int num = 0;
        for (Handicap handicap : getHandicaps()) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length <= sec) {
                continue;
            }
            int firstCode = Integer.parseInt(openCodes[first]);
            int secCode = Integer.parseInt(openCodes[sec]);
            if (betNum.equalsIgnoreCase("龙") && firstCode > sec) {
                if (type == 1) {
                    num++;
                } else {
                    break;
                }
            } else if (betNum.equalsIgnoreCase("虎") && firstCode < sec) {
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
