package com.dawoo.lotterybox.util.lottery;

/**
 * 快三
 * Created by benson on 18-2-15.
 */

public class K3Util {
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

    /**
     * 三不同号 二同号  豹子
     *
     * @return
     */
    public static String getK3SanBuTongHao(String[] arr) {
        int one = Integer.parseInt(arr[0]);
        int two = Integer.parseInt(arr[1]);
        int three = Integer.parseInt(arr[2]);

        if (one != two && one != three && two != three) {

            if (one < two && two < three && (one + 1) == two && (two + 1) == three) {
                return "三连号";
            } else if (one > two && two > three && (one - 1) == two && (two - 1) == three) {
                return "三连号";
            }
            return "三不同号";
        } else if (one == two && one == three) {
            return "三同号";
        }
        return "二同号";
    }
}
