package com.dawoo.lotterybox.bean.lottery.lotteryenum;

import com.dawoo.lotterybox.bean.lottery.lotteryenum.ICodeEnum;

public enum LotteryPlayEnum implements ICodeEnum {

    //时时彩
    ONE_DIGITAL("ssc","one_digital", "一字定位"),
    ONE_BIG_SMALL("ssc","one_big_small", "一字大小"),
    ONE_SINGLE_DOUBLE("ssc","one_single_double", "一字单双"),
    ONE_PRIME_COMBINED("ssc","one_prime_combined", "一字质合"),
    ONE_COMBINATION("ssc","one_combination", "一字组合"),
    TWO_DIGITAL("ssc","two_digital", "二字定位"),
    TWO_COMBINATION("ssc","two_combination", "二字组合"),
    TWO_SUM_SINGLE_DOUBLE("ssc","two_sum_single_double", "二字和数单双"),
    THREE_DIGITAL("ssc","three_digital", "三字定位"),
    THREE_COMBINATION("ssc","three_combination", "三字组合"),
    FIVE_SUM_BIG_SMALL("ssc","five_sum_big_small", "五字和数大小"),
    FIVE_SUM_SINGLE_DOUBLE("ssc","five_sum_single_double", "五字和数单双"),
    DRAGON_TIGER_TIE("ssc","dragon_tiger_tie", "龙虎和"),
    GROUP_THREE("ssc","group_three", "组选三"),
    GROUP_SIX("ssc","group_six", "组选六"),
    SPAN("ssc","span", "跨度"),

    SSC_WUXING_ZHIXUAN("ssc","ssc_wuxing_zhixuan", "五星直选"),
    SSC_SIXING_ZHIXUAN("ssc","ssc_sixing_zhixuan", "四星直选"),
    SSC_SANXING_ZHIXUAN("ssc","ssc_sanxing_zhixuan", "三星直选"),
    SSC_SANXING_ZUXUAN("ssc","ssc_sanxing_zuxuan", "三星组选"),
    SSC_SANXING_TESHU("ssc","ssc_sanxing_teshu", "三星特殊"),
    SSC_ERXING_ZHIXUAN("ssc","ssc_erxing_zhixuan", "二星直选"),
    SSC_ERXING_ZUXUAN("ssc","ssc_erxing_zuxuan", "二星组选"),
    SSC_YIXING("ssc","ssc_yixing", "定位胆"),
    SSC_BUDINGWEI_WUXING("ssc","ssc_budingwei_wuxing", "五星不定位"),
    SSC_BUDINGWEI_SIXING("ssc","ssc_budingwei_sixing", "四星不定位"),
    SSC_BUDINGWEI_SANXING("ssc","ssc_budingwei_sanxing", "三星不定位"),
    SSC_DAXIAODANSHUANG_SANXING("ssc","ssc_daxiaodanshuang_sanxing", "三星大小单双"),
    SSC_DAXIAODANSHUANG_ERXING("ssc","ssc_daxiaodanshuang_erxing", "二星大小单双"),
    SSC_RENXUAN4_ZHIXUAN("ssc","ssc_renxuan4_zhixuan", "任选四直选"),
    SSC_RENXUAN4_ZUXUAN("ssc","ssc_renxuan4_zuxuan", "任选四组选"),
    SSC_RENXUAN3_ZHIXUAN("ssc","ssc_renxuan3_zhixuan", "任选三直选"),
    SSC_RENXUAN3_ZUXUAN("ssc","ssc_renxuan3_zuxuan", "任选三组选"),
    SSC_RENXUAN2_ZHIXUAN("ssc","ssc_renxuan2_zhixuan", "任选二直选"),
    SSC_RENXUAN2_ZUXUAN("ssc","ssc_renxuan2_zuxuan", "任选二组选"),


    //pk10
    PK10_DIGITAL("pk10","pk10_digital", "定位"),
    PK10_BIG_SMALL("pk10","pk10_big_small", "大小"),
    PK10_SINGLE_DOUBLE("pk10","pk10_single_double", "单双"),
    PK10_DRAGON_TIGER("pk10","pk10_dragon_tiger", "龙虎"),
    CHAMPION_UP_34("pk10","champion_up_34", "冠亚军和[3,4,18,19]"),
    CHAMPION_UP_56("pk10","champion_up_56", "冠亚军和[5,6,16,17]"),
    CHAMPION_UP_78("pk10","champion_up_78", "冠亚军和[7,8,14,15]"),
    CHAMPION_UP_910("pk10","champion_up_910", "冠亚军和[9,10,12,13]"),
    CHAMPION_UP_ALONE_11("pk10","champion_up_alone_11", "独立冠亚军和[11]"),
    CHAMPION_UP_ALONE_34("pk10","champion_up_alone_34", "独立冠亚军和[3,4,18,19]"),
    CHAMPION_UP_ALONE_56("pk10","champion_up_alone_56", "独立冠亚军和[5,6,16,17]"),
    CHAMPION_UP_ALONE_78("pk10","champion_up_alone_78", "独立冠亚军和[7,8,14,15]"),
    CHAMPION_UP_ALONE_910("pk10","champion_up_alone_910", "独立冠亚军和[9,10,12,13]"),
    CHAMPION_UP_BIG_SMALL("pk10","champion_up_big_small", "冠亚军和大小"),
    CHAMPION_UP_SINGLE_DOUBLE("pk10","champion_up_single_double", "冠亚军和单双"),
    CHAMPION_UP_HALF("pk10","champion_up_half", "冠亚军和半特"),
    PK10_QY_ZHIXUAN("pk10","pk10_qy_zhixuan", "前一直选"),
    PK10_QE_ZHIXUAN("pk10","pk10_qe_zhixuan", "前二直选"),
    PK10_QS_ZHIXUAN("pk10","pk10_qs_zhixuan", "前三直选"),
    PK10_YIXING("pk10","pk10_yixing", "定位胆"),


    //六合彩
    SPECIAL_DIGITAL("lhc","special_digital", "特码"),
    SPECIAL_BIG_SMALL("lhc","special_big_small", "特码大小"),
    SPECIAL_SINGLE_DOUBLE("lhc","special_single_double", "特码单双"),
    SPECIAL_HALF("lhc","special_half", "特码半特"),
    SPECIAL_SUM_BIG_SMALL("lhc","special_sum_big_small", "特码合数大小"),
    SPECIAL_SUM_SINGLE_DOUBLE("lhc","special_sum_single_double", "特码合数单双"),
    SPECIAL_MANTISSA("lhc","special_mantissa", "特码尾数"),
    SPECIAL_MANTISSA_BIG_SMALL("lhc","special_mantissa_big_small", "特码尾数大小"),
    SPECIAL_COLOUR("lhc","special_colour", "特码色波"),
    SEVEN_SUM_BIG_SMALL("lhc","seven_sum_big_small", "总和大小"),
    SEVEN_SUM_SINGLE_DOUBLE("lhc","seven_sum_single_double", "总和单双"),
    POSITIVE_DIGITAL("lhc","positive_digital", "正码"),
    POSITIVE_SPECIAL_DIGITAL("lhc","positive_special_digital", "正码特"),
    POSITIVE_BIG_SMALL("lhc","positive_big_small", "正码1-6大小"),
    POSITIVE_SINGLE_DOUBLE("lhc","positive_single_double", "正码1-6单双"),
    POSITIVE_COLOUR("lhc","positive_colour", "正码1-6色波"),
    POSITIVE_SUM_BIG_SMALL("lhc","positive_sum_big_small", "正码1-6合数大小"),
    POSITIVE_SUM_SINGLE_DOUBLE("lhc","positive_sum_single_double", "正码1-6合数单双"),
    POSITIVE_MANTISSA_BIG_SMALL("lhc","positive_mantissa_big_small", "正码1-6尾数大小"),
    LHC_HALF_COLOUR_BIG_SMALL("lhc", "lhc_half_colour_big_small", "半波大小"),
    LHC_HALF_COLOUR_SINGLE_DOUBLE("lhc", "lhc_half_colour_single_double", "半波单双"),
    LHC_SPECIAL_ZODIAC("lhc","special_zodiac", "特肖"),
    LHC_ONE_ZODIAC("lhc","lhc_one_zodiac", "一肖"),
    LHC_FOUR_ALL_IN("lhc","lhc_four_all_in", "四全中"),
    LHC_THREE_ALL_IN("lhc","lhc_three_all_in", "三全中"),
    LHC_THREE_IN_TWO("lhc","lhc_three_in_two", "三中二"),
    LHC_TWO_ALL_IN("lhc","lhc_two_all_in", "二全中"),
    LHC_TWO_IN_SPECIAL("lhc","lhc_two_in_special", "二中特"),
    LHC_SPECIAL_STRAND("lhc","lhc_special_strand", "特串"),
    LHC_SUM_ZODIAC("lhc","lhc_sum_zodiac", "合肖"),
    LHC_TWO_ZODIAC_LINK("lhc","lhc_two_zodiac_link", "二肖连"),
    LHC_THREE_ZODIAC_LINK("lhc","lhc_three_zodiac_link", "三肖连"),
    LHC_FOUR_ZODIAC_LINK("lhc","lhc_four_zodiac_link", "四肖连"),
    LHC_FIVE_ZODIAC_LINK("lhc","lhc_five_zodiac_link", "五肖连"),
    LHC_TWO_MANTISSA_LINK("lhc","lhc_two_mantissa_link", "二尾连"),
    LHC_THREE_MANTISSA_LINK("lhc","lhc_three_mantissa_link", "三尾连"),
    LHC_FOUR_MANTISSA_LINK("lhc","lhc_four_mantissa_link", "四尾连"),
    LHC_FIVE_MANTISSA_LINK("lhc","lhc_five_mantissa_link", "五尾连"),
    LHC_FIVE_NO_IN("lhc","lhc_five_no_in", "五不中"),
    LHC_SIX_NO_IN("lhc","lhc_six_no_in", "六不中"),
    LHC_SEVEN_NO_IN("lhc","lhc_seven_no_in", "七不中"),
    LHC_EIGHT_NO_IN("lhc","lhc_eight_no_in", "八不中"),
    LHC_NINE_NO_IN("lhc","lhc_nine_no_in", "九不中"),
    LHC_TEN_NO_IN("lhc","lhc_ten_no_in", "十不中"),
    LHC_ELEVEN_NO_IN("lhc","lhc_eleven_no_in", "十一不中"),
    LHC_TWELVE_NO_IN("lhc","lhc_twelve_no_in", "十二不中"),

    //快3
    POINTS_BIG_SMALL("k3","points_big_small", "点数大小"),
    POINTS_SINGLE_DOUBLE("k3","points_single_double", "点数单双"),
    POINTS_417("k3","points_417", "点数[4,17]"),
    POINTS_516("k3","points_516", "点数[5,16]"),
    POINTS_615("k3","points_615", "点数[6,15]"),
    POINTS_714("k3","points_714", "点数[7,14]"),
    POINTS_813("k3","points_813", "点数[8,13]"),
    POINTS_912("k3","points_912", "点数[9,12]"),
    POINTS_1011("k3","points_1011", "点数[10,11]"),
    ARMED_FORCES("k3","armed_forces", "三军"),
    DICE("k3","dice", "围骰"),
    FULL_DICE("k3","full_dice", "全骰"),
    LONG_CARD("k3","long_card", "长牌"),
    SHORT_CARD("k3","short_card", "短牌"),
    K3_SAME_NUM("k3","k3_same_num", "同号"),
    K3_DIFF_NUM("k3","k3_diff_num", "不同号"),
    K3_THREE_STRAIGHT("k3","k3_three_straight", "三连号"),

    //十分彩(重庆幸运农场,广东快乐十分)
    SFC_DIGITAL("sfc","sfc_digital", "定位"),
    SFC_BIG_SMALL("sfc","sfc_big_small", "大小"),
    SFC_SINGLE_DOUBLE("sfc","sfc_single_double", "单双"),
    SFC_MANTISSA_BIG_SMALL("sfc","sfc_mantissa_big_small", "尾数大小"),
    SFC_SUM_SINGLE_DOUBLE("sfc","sfc_sum_single_double", "合数单双"),
    SFC_SUM8_BIG_SMALL("sfc","sfc_sum8_big_small", "总和大小"),
    SFC_SUM8_SINGLE_DOUBLE("sfc","sfc_sum8_single_double", "总和单双"),
    SFC_SUM8_MANTISSA_BIG_SMALL("sfc","sfc_sum8_mantissa_big_small", "总和尾数大小"),
    SFC_DRAGON_TIGER("sfc","sfc_dragon_tiger", "龙虎"),
    //快乐彩(幸运28,北京快乐8)
    KENO_SELECTION_ONE("keno","keno_selection_one", "选一"),
    KENO_SELECTION_TWO("keno","keno_selection_two", "选二"),
    KENO_SELECTION_THREE("keno","keno_selection_three", "选三"),
    KENO_SELECTION_FOUR("keno","keno_selection_four", "选四"),
    KENO_SELECTION_FIVE("keno","keno_selection_five", "选五"),
    KENO_SUM20_BIG_SMALL("keno","keno_sum20_big_small", "和值大小"),
    KENO_SUM20_SINGLE_DOUBLE("sfc","keno_sum20_single_double", "和值单双"),
    KENO_SUM20_FIVE_ELEMENTS("keno","keno_sum20_elements", "五行"),
    KENO_UP_DOWN("keno","keno_up_down", "上中下"),
    KENO_ODD_EVEN("keno","keno_odd_even", "奇偶和"),

    XY28_SUM3_BIG_SMALL("keno","xy28_sum3_big_small", "大小"),
    XY28_SUM3_SINGLE_DOUBLE("keno","xy28_sum3_single_double", "单双"),
    XY28_SUM3_HALF("keno","xy28_sum3_half", "半特"),
    XY28_SUM3_EXTREME("keno","xy28_sum3_extreme", "极值"),
    XY28_SUM3_COLOUR("keno","xy28_sum3_colour", "色波"),
    XY28_THREE_SAME("keno","xy28_three_same", "豹子"),
    XY28_SUM3_DIGITAL("keno","xy28_sum3_digital", "特码"),
    XY28_SUM3_DIGITAL_THREE("keno","xy28_sum3_digital_three", "特码包三"),
    //其他(福彩3D,体彩排列3)
    PL3_ONE_DIGITAL("qt","pl3_one_digital", "一字定位"),
    PL3_ONE_BIG_SMALL("qt","pl3_one_big_small", "一字大小"),
    PL3_ONE_SINGLE_DOUBLE("qt","pl3_one_single_double", "一字单双"),
    PL3_ONE_PRIME_COMBINED("qt","pl3_one_prime_combined", "一字质合"),
    PL3_TWO_DIGITAL("qt","pl3_two_digital", "二字定位"),
    PL3_THREE_DIGITAL("qt","pl3_three_digital", "三字定位"),
    PL3_ONE_COMBINATION("qt","pl3_one_combination", "一字组合"),
    PL3_TWO_COMBINATION("qt","pl3_two_combination", "二字组合"),
    PL3_THREE_COMBINATION("qt","pl3_three_combination", "三字组合"),
    PL3_SUM2_DIGITAL("qt","pl3_sum2_digital", "二字和数"),
    PL3_SUM2_SINGLE_DOUBLE("qt","pl3_sum2_single_double", "二字和数单双"),
    PL3_SUM2_MANTISSA("qt","pl3_sum2_mantissa", "二字和数尾数"),
    PL3_SUM2_MANTISSA_BIG_SMALL("qt","pl3_sum2_mantissa_big_small", "二字和数尾数大小"),
    PL3_SUM2_MANTISSA_PRIME_COMBINED("qt","pl3_sum2_mantissa_prime_combined", "二字和数尾数质合"),
    PL3_SUM3_DIGITAL("qt","pl3_sum3_digital", "三字和数"),
    PL3_SUM3_MANTISSA("qt","pl3_sum3_mantissa", "三字和数尾数"),
    PL3_SUM3_BIG_SMALL("qt","pl3_sum3_big_small", "三字和数大小"),
    PL3_SUM3_SINGLE_DOUBLE("qt","pl3_sum3_single_double", "三字和数单双"),
    PL3_SUM3_MANTISSA_BIG_SMALL("qt","pl3_sum3_mantissa_big_small", "三字和数尾数大小"),
    PL3_SUM3_MANTISSA_PRIME_COMBINED("qt","pl3_sum3_mantissa_prime_combined", "三字和数尾数质合"),
    PL3_GROUP_THREE("qt","pl3_group_three", "组选三"),
    PL3_GROUP_SIX("qt","pl3_group_six", "组选六"),
    PL3_SPAN("qt","pl3_span", "跨度"),

    PL3_SANXING_ZHIXUAN("qt","pl3_sanxing_zhixuan", "三星直选"),
    PL3_SANXING_ZUXUAN("qt","pl3_sanxing_zuxuan", "三星组选"),
    PL3_ERXING_ZHIXUAN("qt","pl3_erxing_zhixuan", "二星直选"),
    PL3_ERXING_ZUXUAN("qt","pl3_erxing_zuxuan", "二星组选"),
    PL3_YIXING("qt","pl3_yixing", "定位胆"),
    PL3_BUDINGWEI_SANXING("qt","pl3_budingwei_sanxing", "三星不定位"),
    ;

    private String type;
    private String code;
    private String trans;

    LotteryPlayEnum(String type, String code, String trans) {
        this.type = type;
        this.code = code;
        this.trans = trans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
}
