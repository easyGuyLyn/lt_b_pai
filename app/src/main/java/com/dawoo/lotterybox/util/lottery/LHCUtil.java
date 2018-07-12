package com.dawoo.lotterybox.util.lottery;

import android.content.Context;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BBetParamForm;

import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.ChooseMoney;
import com.dawoo.lotterybox.bean.KeyMap;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.GsonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by benson on 18-2-15.
 */

public class LHCUtil {
    private static String[] mYears = BoxApplication.getContext().getResources().getStringArray(R.array.sheng_xiao_array);
    private static final List<Integer> red = Arrays.asList(1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46);
    private static final List<Integer> blue = Arrays.asList(3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48);
    private static final List<Integer> green = Arrays.asList(5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49);
    private static final String LHC_PLAY_EXPLAIN_FILE = "hk_lhc_play_explain.json";
    private static Map<String, KeyMap.PlayExplainBean> explainMap = new HashMap<>();

    /**
     * 根据六合彩传入的球获取生肖
     *
     * @param code
     * @return
     */
    public static String getShengXiao(String code) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            String tYear = sdf.format(new Date());
            int tYearInt = Integer.parseInt(tYear);
            // 今年生肖
            String year = getYear(tYearInt);
            int tIndex = getIndex(year);

            // code 转型并根据新组成的生肖返回
            int index = (Integer.parseInt(code) + tIndex - 1) % 12;

            if (index > 0 && index < 12) {
                return mYears[index];
            } else {
                return mYears[0];
            }

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据传入的年获取生肖
     *
     * @param year
     * @return
     */
    private static String getYear(Integer year) {
        String[] years = new String[]{
                "鼠", "牛", "虎", "兔",
                "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪"
        };
        if (year < 1900) {
            return "未知";
        }

        Integer start = 1900;
        return years[(year - start) % years.length];
    }


    /**
     * 根据传入的生肖获取下标
     *
     * @param year
     * @return
     */
    private static int getIndex(String year) {
        for (int i = 0; i < mYears.length; i++) {
            if (mYears[i].equals(year)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 获取红蓝绿波
     */
    public static String getBallColor(String code) {
        try {
            int codeInt = Integer.parseInt(code);
            return getBallColor(codeInt);
        } catch (Exception e) {
            return "red";
        }

    }

    public static String getBallColor(int codeInt) {
        int[] red = new int[]{1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46};
        int[] blue = new int[]{3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48};
        int[] green = new int[]{5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49};
        for (int i = 0; i < red.length; i++) {
            if (codeInt == red[i]) {
                return "red";
            } else if (codeInt == blue[i]) {
                return "blue";
            } else if (codeInt == green[i]) {
                return "green";
            }
        }
        return "red";
    }

    /**
     * 获取红蓝绿波
     */
    public static int getBallbg(String code) {
        int codeInt = Integer.parseInt(code);
        if (red.contains(codeInt)) {
            return R.mipmap.bo_red_bg;
        } else if (blue.contains(codeInt)) {
            return R.mipmap.bo_blue_bg;
        } else if (green.contains(codeInt)) {
            return R.mipmap.bo_green_bg;
        }

        return R.mipmap.bo_grey_bg;
    }

    /**
     * 获取大小
     */
    public static String getBigOrSmall(String code) {
        int codeInt = Integer.parseInt(code);
        return getBigOrSmall(codeInt);
    }

    /**
     * 获取大小
     */
    public static String getBigOrSmall(int code) {
        if (code > 24) {
            return "大";
        }

        return "小";
    }

    /**
     * 获取单双
     */
    public static String getSingleOrDouble(String code) {
        int codeInt = Integer.parseInt(code);
        if (codeInt % 2 == 0) {
            return "双";
        }

        return "单";
    }

    /**
     * 获取合数
     */
    public static String getAddNum(String code) {
        int codeInt = Integer.parseInt(code);
        if (codeInt > 10) {
            int sum = Integer.valueOf(code.substring(0, 1)) + Integer.valueOf(code.substring(1));
            return String.valueOf(sum);
        }

        return String.valueOf(codeInt);
    }

    /**
     * 获取合数
     */
    public static int getAddNum(int code) {
        String codes = String.valueOf(code);
        if (code > 10) {
            int sum = Integer.valueOf(codes.substring(0, 1)) + Integer.valueOf(codes.substring(1));
            return sum;
        }

        return code;
    }

    /**
     * 获取尾数
     */
    public static String getLastNum(String code) {
        int codeInt = Integer.parseInt(code);
        if (codeInt > 10) {
            return code.substring(1);
        }

        return String.valueOf(codeInt);
    }

    /**
     * 获取球号
     *
     * @param code 根据球色大小单双区分
     * @return
     */
    public static String getBollsByName(String code) {
        String codes = "";
        if (code.contains("红")) {
            for (Integer item : red) {
                if (code.contains("单")) {
                    if (item % 2 == 1) {
                        codes += "," + item;
                    }
                } else if (code.contains("双")) {
                    if (item % 2 == 0) {
                        codes += "," + item;
                    }
                } else if (code.contains("大")) {
                    if (item > 24) {
                        codes += "," + item;
                    }
                } else if (code.contains("小")) {
                    if (item < 25) {
                        codes += "," + item;
                    }
                }
            }
        } else if (code.contains("蓝")) {
            for (Integer item : blue) {
                if (code.contains("单")) {
                    if (item % 2 == 1) {
                        codes += "," + item;
                    }
                } else if (code.contains("双")) {
                    if (item % 2 == 0) {
                        codes += "," + item;
                    }
                } else if (code.contains("大")) {
                    if (item > 24) {
                        codes += "," + item;
                    }
                } else if (code.contains("小")) {
                    if (item < 25) {
                        codes += "," + item;

                    }
                }
            }
        } else {
            for (Integer item : green) {
                if (code.contains("单")) {
                    if (item % 2 == 1) {
                        codes += "," + item;

                    }
                } else if (code.contains("双")) {
                    if (item % 2 == 0) {
                        codes += "," + item;

                    }
                } else if (code.contains("大")) {
                    if (item > 24) {
                        codes += "," + item;

                    }
                } else if (code.contains("小")) {
                    if (item < 25) {
                        codes += "," + item;

                    }
                }
            }
        }
        return codes.substring(1, codes.length());
    }

    public static String getCodesByShenxiao(String code) {
        String codes = "";
        switch (code) {
            case "鼠":
                codes = "11,23,35,47";
                break;
            case "牛":
                codes = "10,22,34,46";
                break;
            case "虎":
                codes = "9,21,33,45";
                break;
            case "兔":
                codes = "8,20,32,44";
                break;
            case "龙":
                codes = "7,19,31,43";
                break;
            case "蛇":
                codes = "6,18,30,42";
                break;
            case "马":
                codes = "5,17,29,41";
                break;
            case "羊":
                codes = "4,16,28,40";
                break;
            case "猴":
                codes = "3,15,27,39";
                break;
            case "鸡":
                codes = "2,14,26,38";
                break;
            case "狗":
                codes = "1,13,25,37,49";
                break;
            case "猪":
                codes = "12,24,36,48";
                break;


        }
        try {
            return codes;
        } catch (Exception e) {
            return "";
        }


    }

    public static String getCodesBynum(String code) {
        String codes = "";
        switch (code) {
            case "0":
                codes = "10,20,30,40";
                break;
            case "1":
                codes = "1,11,21,31,41";
                break;
            case "2":
                codes = "2,12,22,32,42";
                break;
            case "3":
                codes = "3,13,23,33,43";
                break;
            case "4":
                codes = "4,14,24,34,44";
                break;
            case "5":
                codes = "5,15,25,35,45";
                break;
            case "6":
                codes = "6,16,26,36,46";
                break;
            case "7":
                codes = "7,17,27,37,47";
                break;
            case "8":
                codes = "8,18,28,38,48";
                break;
            case "9":
                codes = "9,19,29,39,49";
                break;
        }
        try {
            return codes;
        } catch (Exception e) {
            return "";
        }


    }

    /**
     * 填充球的颜色，号码，生肖
     *
     * @param ballView
     * @param codeNameView
     * @param code
     */
    public static void initBall(TextView ballView, TextView codeNameView, String code) {
        if (code.equalsIgnoreCase("50")) {
            ballView.setText("?");
        } else {
            ballView.setText(code);
        }

        ballView.setBackgroundResource(getBallbg(code));
        codeNameView.setText(getShengXiao(code));
    }
    public static void initBallText(TextView ballView, TextView codeNameView, String code){
        if (code.equalsIgnoreCase("50")) {
            ballView.setText("?");
        } else {
            ballView.setText(code);
        }
        codeNameView.setText(getShengXiao(code));
    }
    //初始化 底部选择钱的数量的数据源
    public static List<ChooseMoney> initBottomChooseMoneyData() {
        List<ChooseMoney> list = new ArrayList<>();
        int[] countArray = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000};
        String baseDrawableStr = "niuniu_chouma";
        for (int i = 1; i < 9; i++) {
            ChooseMoney chooseMoney = new ChooseMoney();
            chooseMoney.setCount(countArray[i - 1]);
            chooseMoney.setDrawableId(baseDrawableStr + i);
            list.add(chooseMoney);
        }
        return list;
    }

    public static KeyMap.PlayExplainBean getExplianbyCode(String beCode) {
        KeyMap.PlayExplainBean explain;
        if (explainMap == null || explainMap.isEmpty()) {
            setExplainMap();
        }
        explain = explainMap.get(beCode);
        return explain;
    }

    private static void setExplainMap() {
        String jsonSTring = AssetsReader.getJson(BoxApplication.getContext(), LHC_PLAY_EXPLAIN_FILE);
        List<KeyMap> keyMaps = GsonUtil.jsonToList(jsonSTring, KeyMap.class);
        for (KeyMap keyMap : keyMaps) {
            explainMap.put(keyMap.getKey(), keyMap.getValue());
        }
    }

    /**
     * 随机数组
     * @param size
     * @param min
     * @param max
     * @return
     */
    public static int[] getRandomList(int size,int min,int max){
        List<Integer> alls = new ArrayList<>();
        for(int i=min;i<max;i++){
           alls.add(i);
        }

        int[] result = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(alls.size() - 1 - i);
            int randomRes = alls.get(randomIndex);
            result[i] = randomRes;
            int temp = alls.get(randomIndex);
            alls.set(randomIndex,alls.get(alls.size()-1-i));
            alls.set(alls.size()-1-i,temp);
        }
        return result;
    }

}
