package com.dawoo.lotterybox.util;

import com.dawoo.coretool.util.math.BigDemicalUtil;

import org.apache.http.util.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by rain on 18-5-10.
 */

public class BalanceUtils {
    /**
     * 获取double金额
     *
     * @param inBalance
     * @return
     */
    public static double getDouble(String inBalance) {

        double outBalance = 0.00;
        if (!TextUtils.isEmpty(inBalance)) {
            outBalance = Double.parseDouble(inBalance.replaceAll(",", ""));
        }
        return BigDemicalUtil.round(outBalance, 2);
    }

    /**
     * 获取规则String
     *
     * @param inBalance
     * @return
     */
    public static String getScalsBalance(String inBalance) {
        if (TextUtils.isEmpty(inBalance)) {
            return "0.00";
        }
        double d = Double.parseDouble(inBalance.replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("###,##0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(d);
    }

    public static String getScalsBalance(double inBalance) {
        return new DecimalFormat("###,##0.00").format(inBalance);
    }

    /**
     * 保留3位小数
     * @param inBalance
     * @return
     */
    public  static String getThreeBalance(String inBalance){
        if (TextUtils.isEmpty(inBalance)) {
            return "0.000";
        }
        return new DecimalFormat("###,##0.000").format(Double.parseDouble(inBalance.replaceAll(",", "")));
    }
    public  static String getThreeBalance(double inBalance){

        return new DecimalFormat("###,##0.000").format(inBalance);
    }
}
