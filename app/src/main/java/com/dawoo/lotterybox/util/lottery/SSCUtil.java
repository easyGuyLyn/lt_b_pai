package com.dawoo.lotterybox.util.lottery;

/**
 * 时时彩工具类
 * Created by benson on 18-2-13.
 */

public class SSCUtil {

    /**
     * 和值
     *
     * @param arr
     * @return
     */
    public static int getHeZhi(String[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Integer.parseInt(arr[i]);
        }
        return sum;
    }

    public static String getZuLiuOrZUSan(int code3, int code4, int code5) {
        if (code3 != code4 && code3 != code5 && code4 != code5) {
            return "组六";
        } else if (code3 == code4 && code4 == code5) {
            return "豹子";
        }
        return "组三";
    }

    /**
     * 组六 或者 组三
     *
     * @return
     */
    public static String getZuLiuOrZUSan2(int code3, int code4, int code5) {
        if (code3 != code4 && code3 != code5 && code4 != code5) {
            return "六";
        } else if (code3 == code4 && code4 == code5) {
            return "豹子";
        }
        return "三";
    }

    /**
     * 获取时时彩大小单双
     *
     * @return
     */
    public static String getDaXiaoDanShuang(int x) {
        //  大小
        if (x < 5 && x >= 0) {
            // 小
            if (x % 2 == 0) {
                return "小双";
            } else {
                return "小单";
            }
        } else if (x < 10 && x >= 5) {
            // 小
            if (x % 2 == 0) {
                return "大双";
            } else {
                return "大单";
            }
        }

        return "";
    }

}
