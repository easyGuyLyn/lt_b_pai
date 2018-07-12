package com.dawoo.lotterybox.util;

import android.support.annotation.NonNull;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by alex on 18-5-10.
 * @author alex
 */

public class NumberFormaterUtils extends BalanceUtils{

    public static String formaterD2S(double number){
       // DecimalFormat numberFormat = prcessFormat();
        return  BalanceUtils.getScalsBalance(number);
    }

    @NonNull
    private static DecimalFormat prcessFormat() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        df.setGroupingUsed(false);
        return df;
    }

    public static Double formaterS2D(String number){
        number= number.replaceAll(",","");
        DecimalFormat numberFormat = prcessFormat();
        return  Double.valueOf(numberFormat.format(Double.valueOf(number)));
    }
    public static String formater(double number){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(number);
    }
    public static String formater(String number){
        double num = Double.parseDouble(number.replaceAll(",",""));
        return formater(num);
    }
    public static String formaterS2S(String number){
//        BalanceUtils.
//        number= number.replaceAll(",","");
//        DecimalFormat numberFormat = prcessFormat();
        return  BalanceUtils.getScalsBalance(number);
    }

    public static Double formaterD2D(Double number){
        DecimalFormat numberFormat = prcessFormat();
        return  Double.valueOf(numberFormat.format(number));
    }



}
