package com.dawoo.lotterybox.util.lottery;

import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryBettingEnum;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryPlayEnum;

/**
 * 彩票工具类
 * Created by benson on 18-2-13.
 */

public class LotteryUtil {

    /**
     * 根据彩种代码获取彩种名称
     *
     * @param code
     * @return
     */
    public static String getLotteryNameByCode(String code) {
        for (LotteryEnum num : LotteryEnum.values()) {
            if (num.getCode().equalsIgnoreCase(code)) {
                return num.getTrans();
            }
        }
        return "";
    }
    /**
     * 根据彩票玩法
     *
     * @param betCode
     * @return
     */
    public static String getPlayNameByCode(String betCode,String playCode) {
        String playName="";
        for (LotteryBettingEnum num : LotteryBettingEnum.values()) {
            if (num.getCode().equalsIgnoreCase(betCode)) {
                playName+=num.getTrans();
                break;
            }
        }

        for(LotteryPlayEnum num:LotteryPlayEnum.values()){
            if (num.getCode().equalsIgnoreCase(playCode)) {
                playName+=num.getTrans();
                break;
            }
        }
        return playName;
    }

    /**
     * 根据彩票玩法  有@
     *
     * @param betCode
     * @return
     */
    public static String getPlayNameByCode2(String betCode,String playCode) {
        String playName="";
        for (LotteryBettingEnum num : LotteryBettingEnum.values()) {
            if (num.getCode().equalsIgnoreCase(betCode)) {
                playName+=num.getTrans();
                break;
            }
        }

        for(LotteryPlayEnum num:LotteryPlayEnum.values()){
            if (num.getCode().equalsIgnoreCase(playCode)) {
                playName+=("@"+num.getTrans());
                break;
            }
        }
        return playName;
    }

    /**
     * 根据彩票玩法
     *
     * @param betCode
     * @return
     */
    public static String getBetNameByCode(String betCode) {
        String playName="";
        for (LotteryBettingEnum num : LotteryBettingEnum.values()) {
            if (num.getCode().equalsIgnoreCase(betCode)) {
                playName+=num.getTrans();
                break;
            }
        }
        return playName;
    }
    public static String getPlayName(String playCode){
        String playName="";

        for(LotteryPlayEnum num:LotteryPlayEnum.values()){
            if (num.getCode().equalsIgnoreCase(playCode)) {
                playName+=num.getTrans();
                break;
            }
        }
        return playName;
    }
}
