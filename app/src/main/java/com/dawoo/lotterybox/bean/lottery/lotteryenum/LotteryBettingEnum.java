package com.dawoo.lotterybox.bean.lottery.lotteryenum;


import android.text.TextUtils;

public enum LotteryBettingEnum implements ICodeEnum {
    //时时彩
    TEN_THOUSAND("ssc", "ten_thousand", "万位"),
    THOUSAND("ssc", "thousand", "千位"),
    HUNDRED("ssc", "hundred", "百位"),
    TEN("ssc", "ten", "十位"),
    ONE("ssc", "one", "个位"),
    ONE_ALL_FIVE("ssc", "one_all_five", "全五一字组合"),
    ONE_FIRST_THREE("ssc", "one_first_three", "前三一字组合"),
    ONE_IN_THREE("ssc", "one_in_three", "中三一字组合"),
    ONE_AFTER_THREE("ssc", "one_after_three", "后三一字组合"),
    SSC_WAN_THOUSAND("ssc", "ten_thousand_thousand", "万千"),
    SSC_WAN_HUNDRED("ssc", "ten_thousand_hundred", "万百"),
    SSC_WAN_TEN("ssc", "ten_thousand_ten", "万十"),
    SSC_WAN_ONE("ssc", "ten_thousand_one", "万个"),
    SSC_THOUSAND_HUNDRED("ssc", "thousand_hundred", "千百"),
    SSC_THOUSAND_TEN("ssc", "thousand_ten", "千十"),
    SSC_THOUSAND_ONE("ssc", "thousand_one", "千个"),
    SSC_HUNDRED_TEN("ssc", "hundred_ten", "百十"),
    SSC_HUNDRED_ONE("ssc", "hundred_one", "百个"),
    SSC_TEN_ONE("ssc", "ten_one", "十个"),
    TWO_FIRST_THREE("ssc", "two_first_three", "前三二字组合"),
    TWO_IN_THREE("ssc", "two_in_three", "中三二字组合"),
    TWO_AFTER_THREE("ssc", "two_after_three", "后三二字组合"),
    TEN_THOUSAND_THOUSAND_HUNDRED("ssc", "ten_thousand_thousand_hundred", "万千百"),
    TEN_THOUSAND_THOUSAND_TEN("ssc", "ten_thousand_thousand_ten", "万千十"),
    TEN_THOUSAND_THOUSAND_ONE("ssc", "ten_thousand_thousand_one", "万千个"),
    TEN_THOUSAND_HUNDRED_TEN("ssc", "ten_thousand_hundred_ten", "万百十"),
    TEN_THOUSAND_HUNDRED_ONE("ssc", "ten_thousand_hundred_one", "万百个"),
    TEN_THOUSAND_TEN_ONE("ssc", "ten_thousand_ten_one", "万十个"),
    THOUSAND_HUNDRED_TEN("ssc", "thousand_hundred_ten", "千百十"),
    THOUSAND_HUNDRED_ONE("ssc", "thousand_hundred_one", "千百个"),
    THOUSAND_TEN_ONE("ssc", "thousand_ten_one", "千十个"),
    HUNDRED_TEN_ONE("ssc", "hundred_ten_one", "百十个"),
    FIVE_SUM("ssc", "five_sum", "总和"),
    GROUP3_FIRST_THREE("ssc", "group3_first_three", "前三组选三"),
    GROUP3_IN_THREE("ssc", "group3_in_three", "中三组选三"),
    GROUP3_AFTER_THREE("ssc", "group3_after_three", "后三组选三"),
    GROUP6_FIRST_THREE("ssc", "group6_first_three", "前三组选六"),
    GROUP6_IN_THREE("ssc", "group6_in_three", "中三组选六"),
    GROUP6_AFTER_THREE("ssc", "group6_after_three", "后三组选六"),
    SPAN_FIRST_THREE("ssc", "span_first_three", "前三跨度"),
    SPAN_IN_THREE("ssc", "span_in_three", "中三跨度"),
    SPAN_AFTER_THREE("ssc", "span_after_three", "后三跨度"),

    SSC_WUXING_ZXFS("ssc","ssc_wuxing_zhixuan_fs", "五星直选复式"),
    SSC_WUXING_ZXDS("ssc","ssc_wuxing_zhixuan_ds", "五星直选单式"),
    SSC_SIXING_ZXFS("ssc","ssc_sixing_zhixuan_fs", "四星直选复式"),
    SSC_SIXING_ZXDS("ssc","ssc_sixing_zhixuan_ds", "四星直选单式"),

    SSC_SANXING_QSZXFS("ssc","ssc_sanxing_zhixuan_qsfs", "前三直选复式"),
    SSC_SANXING_QSZXDS("ssc","ssc_sanxing_zhixuan_qsds", "前三直选单式"),
    SSC_SANXING_QSZXZH("ssc","ssc_sanxing_zhixuan_qszh", "前三直选组合"),
    SSC_SANXING_QSZXHZ("ssc","ssc_sanxing_zhixuan_qshz", "前三直选和值"),
    SSC_SANXING_QSZXKD("ssc","ssc_sanxing_zhixuan_qskd", "前三直选跨度"),
    SSC_SANXING_QSZ3FS("ssc","ssc_sanxing_zuxuan_qsz3fs", "前三组三复式"),
    SSC_SANXING_QSZ3DS("ssc","ssc_sanxing_zuxuan_qsz3ds", "前三组三单式"),
    SSC_SANXING_QSZ6FS("ssc","ssc_sanxing_zuxuan_qsz6fs", "前三组六复式"),
    SSC_SANXING_QSZ6DS("ssc","ssc_sanxing_zuxuan_qsz6ds", "前三组六单式"),

    SSC_SANXING_QSHHZX("ssc","ssc_sanxing_zuxuan_qshhzx", "前三混合组选"),
    SSC_SANXING_QSZUXHZ("ssc","ssc_sanxing_zuxuan_qszxhz", "前三组选和值"),
    SSC_SANXING_QSZXBD("ssc","ssc_sanxing_zuxuan_qszxbd", "前三组选包胆"),
    SSC_SANXING_QSHZWS("ssc","ssc_sanxing_zuxuan_qshzws", "前三和值尾数"),
    SSC_SANXING_QSTS("ssc","ssc_sanxing_zuxuan_qsts", "前三特殊号"),

    SSC_SANXING_HSZXFS("ssc","ssc_sanxing_zhixuan_hsfs", "后三直选复式"),
    SSC_SANXING_HSZXDS("ssc","ssc_sanxing_zhixuan_hsds", "后三直选单式"),
    SSC_SANXING_HSZXZH("ssc","ssc_sanxing_zhixuan_hszh", "后三直选组合"),
    SSC_SANXING_HSZXHZ("ssc","ssc_sanxing_zhixuan_hshz", "后三直选和值"),
    SSC_SANXING_HSZXKD("ssc","ssc_sanxing_zhixuan_hskd", "后三直选跨度"),
    SSC_SANXING_HSZ3FS("ssc","ssc_sanxing_zuxuan_hsz3fs", "后三组三复式"),
    SSC_SANXING_HSZ3DS("ssc","ssc_sanxing_zuxuan_hsz3ds", "后三组三单式"),
    SSC_SANXING_HSZ6FS("ssc","ssc_sanxing_zuxuan_hsz6fs", "后三组六复式"),
    SSC_SANXING_HSZ6DS("ssc","ssc_sanxing_zuxuan_hsz6ds", "后三组六单式"),
    SSC_SANXING_HSHHZX("ssc","ssc_sanxing_zuxuan_hshhzx", "后三混合组选"),
    SSC_SANXING_HSZUXHZ("ssc","ssc_sanxing_zuxuan_hszxhz", "后三组选和值"),
    SSC_SANXING_HSZXBD("ssc","ssc_sanxing_zuxuan_hszxbd", "后三组选包胆"),
    SSC_SANXING_HSHZWS("ssc","ssc_sanxing_zuxuan_hshzws", "后三和值尾数"),
    SSC_SANXING_HSTS("ssc","ssc_sanxing_zuxuan_hsts", "后三特殊号"),

    SSC_ERXING_QEZXFS("ssc","ssc_erxing_zhixuan_qefs", "前二直选复式"),
    SSC_ERXING_QEZXDS("ssc","ssc_erxing_zhixuan_qeds", "前二直选单式"),
    //    SSC_ERXING_QEZXZH("ssc","ssc_erxing_zhixuan_qeds", "前二直选组合"),
    SSC_ERXING_QEZXHZ("ssc","ssc_erxing_zhixuan_qehz", "前二直选和值"),
    SSC_ERXING_QEZXKD("ssc","ssc_erxing_zhixuan_qekd", "前二直选跨度"),


    SSC_ERXING_QEZUXFS("ssc","ssc_erxing_zuxuan_qefs", "前二组选复式"),
    SSC_ERXING_QEZUXDS("ssc","ssc_erxing_zuxuan_qeds", "前二组选单式"),
    SSC_ERXING_QEZUXHZ("ssc","ssc_erxing_zuxuan_qehz", "前二组选和值"),
    SSC_ERXING_QEZUXBD("ssc","ssc_erxing_zuxuan_qebd", "前二组选包胆"),

    SSC_YIXING_DWD("ssc","ssc_yixing_dwd", "定位胆"),

    SSC_BUDINGWEI_WXSM("ssc","ssc_budingwei_wxsm", "五星三码不定位"),
    SSC_BUDINGWEI_WXEM("ssc","ssc_budingwei_wxem", "五星二码不定位"),
    SSC_BUDINGWEI_Q4EM("ssc","ssc_budingwei_q4em", "前四二码不定位"),
    SSC_BUDINGWEI_Q4YM("ssc","ssc_budingwei_q4ym", "前四一码不定位"),
    SSC_BUDINGWEI_H4EM("ssc","ssc_budingwei_h4em", "后四二码不定位"),
    SSC_BUDINGWEI_H4YM("ssc","ssc_budingwei_h4ym", "后四一码不定位"),
    SSC_BUDINGWEI_Q3EM("ssc","ssc_budingwei_q3em", "前三二码不定位"),
    SSC_BUDINGWEI_Q3YM("ssc","ssc_budingwei_q3ym", "前三一码不定位"),
    SSC_BUDINGWEI_H3EM("ssc","ssc_budingwei_h3em", "后三二码不定位"),
    SSC_BUDINGWEI_H3YM("ssc","ssc_budingwei_h3ym", "后三一码不定位"),

    SSC_DAXIAODANSHUANG_Q3("ssc","ssc_daxiaodanshuang_q3", "前三大小单双"),
    SSC_DAXIAODANSHUANG_H3("ssc","ssc_daxiaodanshuang_h3", "后三大小单双"),
    SSC_DAXIAODANSHUANG_Q2("ssc","ssc_daxiaodanshuang_q2", "前二大小单双"),
    SSC_DAXIAODANSHUANG_H2("ssc","ssc_daxiaodanshuang_h2", "后二大小单双"),

    SSC_RENXUAN4_ZXFS("ssc","ssc_renxuan4_zxfs", "任选四直选复式"),
    SSC_RENXUAN4_ZXDS("ssc","ssc_renxuan4_zxds", "任选四直选单式"),
    SSC_RENXUAN4_ZX24("ssc","ssc_renxuan4_zx24", "任选四组选24"),
    SSC_RENXUAN4_ZX12("ssc","ssc_renxuan4_zx12", "任选四组选12"),
    SSC_RENXUAN4_ZX6("ssc","ssc_renxuan4_zx6", "任选四组选6"),
    SSC_RENXUAN4_ZX4("ssc","ssc_renxuan4_zx4", "任选四组选4"),

    SSC_RENXUAN3_ZXFS("ssc","ssc_renxuan3_zxfs", "任选三直选复式"),
    SSC_RENXUAN3_ZXDS("ssc","ssc_renxuan3_zxds", "任选三直选单式"),
    SSC_RENXUAN3_ZXHZ("ssc","ssc_renxuan3_zxhz", "任选三直选和值"),
    SSC_RENXUAN3_Z3FS("ssc","ssc_renxuan3_z3fs", "任选三组三复式"),
    SSC_RENXUAN3_Z3DS("ssc","ssc_renxuan3_z3ds", "任选三组三单式"),
    SSC_RENXUAN3_Z6FS("ssc","ssc_renxuan3_z6fs", "任选三组六复式"),
    SSC_RENXUAN3_Z6DS("ssc","ssc_renxuan3_z6ds", "任选三组六单式"),
    SSC_RENXUAN3_HHZX("ssc","ssc_renxuan3_hhzx", "任选三混合组选"),
    SSC_RENXUAN3_ZUXHZ("ssc","ssc_renxuan3_zuxhz", "任选三组选和值"),

    SSC_RENXUAN2_ZXFS("ssc","ssc_renxuan2_zxfs", "任选二直选复式"),
    SSC_RENXUAN2_ZXDS("ssc","ssc_renxuan2_zxds", "任选二直选单式"),
    SSC_RENXUAN2_ZXHZ("ssc","ssc_renxuan2_zxhz", "任选二直选和值"),
    SSC_RENXUAN2_ZUXFS("ssc","ssc_renxuan2_zuxfs", "任选二组选复式"),
    SSC_RENXUAN2_ZUXDS("ssc","ssc_renxuan2_zuxds", "任选二组选单式"),
    SSC_RENXUAN2_ZUXHZ("ssc","ssc_renxuan2_zuxhz", "任选二组选和值"),


    //pk10
    CHAMPION("pk10", "champion", "冠军"),
    RUNNER_UP("pk10", "runner_up", "亚军"),
    THIRD_RUNNER("pk10", "third_runner", "季军"),
    FOURTH_RUNNER("pk10", "fourth_place", "第四名"),
    FIFTH_RUNNER("pk10", "fifth_place", "第五名"),
    SIXTH_RUNNER("pk10", "sixth_place", "第六名"),
    SEVENTH_RUNNER("pk10", "seventh_place", "第七名"),
    EIGHTH_RUNNER("pk10", "eighth_place", "第八名"),
    NINTH_RUNNER("pk10", "ninth_place", "第九名"),
    TENTH_RUNNER("pk10", "tenth_place", "第十名"),
    CHAMPION_UP_SUM("pk10", "champion_up_sum", "冠亚军和"),
    PK10_ZHIXUAN_QYFS("pk10","pk10_zhixuan_qyfs", "前一直选复式"),
    PK10_ZHIXUAN_QEFS("pk10","pk10_zhixuan_qefs", "前二直选复式"),
    PK10_ZHIXUAN_QEDS("pk10","pk10_zhixuan_qeds", "前二直选单式"),
    PK10_ZHIXUAN_QSFS("pk10","pk10_zhixuan_qsfs", "前三直选复式"),
    PK10_ZHIXUAN_QSDS("pk10","pk10_zhixuan_qsds", "前三直选单式"),
    PK10_YIXING_DWD("pk10","pk10_yixing_dwd", "定位胆"),

    //六合彩
    SPECIAL("lhc", "special", "特码"),
    SPECIAL_A("lhc", "special_a", "特码A盘"),
    SPECIAL_B("lhc", "special_b", "特码B盘"),
    SEVEN_SUM("lhc", "seven_sum", "总和"),
    POSITIVE("lhc", "positive", "正码"),
    POSITIVE_FIRST("lhc", "positive_first", "正码一"),
    POSITIVE_SECOND("lhc", "positive_second", "正码二"),
    POSITIVE_THIRD("lhc", "positive_third", "正码三"),
    POSITIVE_FOURTH("lhc", "positive_fourth", "正码四"),
    POSITIVE_FIFTH("lhc", "positive_fifth", "正码五"),
    POSITIVE_SIXTH("lhc", "positive_sixth", "正码六"),
    LHC_HALF_COLOUR("lhc", "lhc_half_colour", "半波"),
    LHC_ONE_ZODIAC("lhc","lhc_one_zodiac", "一肖"),

    LHC_FOUR_ALL_IN("lhc","lhc_four_all_in", "四全中"),
    LHC_THREE_ALL_IN("lhc","lhc_three_all_in", "三全中"),
    LHC_THREE_IN_TWO("lhc","lhc_three_in_two", "三中二"),
    LHC_TWO_ALL_IN("lhc","lhc_two_all_in", "二全中"),
    LHC_TWO_IN_SPECIAL("lhc","lhc_two_in_special", "二中特"),
    LHC_SPECIAL_STRAND("lhc","lhc_special_strand", "特串"),
    LHC_TWO_ZODIAC("lhc","lhc_two_zodiac", "二肖"),
    LHC_THREE_ZODIAC("lhc","lhc_three_zodiac", "三肖"),
    LHC_FOUR_ZODIAC("lhc","lhc_four_zodiac", "四肖"),
    LHC_FIVE_ZODIAC("lhc","lhc_five_zodiac", "五肖"),
    LHC_SIX_ZODIAC("lhc","lhc_six_zodiac", "六肖"),
    LHC_SEVEN_ZODIAC("lhc","lhc_seven_zodiac", "七肖"),
    LHC_EIGHT_ZODIAC("lhc","lhc_eight_zodiac", "八肖"),
    LHC_NINE_ZODIAC("lhc","lhc_nine_zodiac", "九肖"),
    LHC_TEN_ZODIAC("lhc","lhc_ten_zodiac", "十肖"),
    LHC_ELEVEN_ZODIAC("lhc","lhc_eleven_zodiac", "十一肖"),
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
    POINTS("k3","points", "点数"),
    ARMED_FORCES("k3","armed_forces", "三军"),
    DICE_FULL_DICE("k3","dice_full_dice", "围骰/全骰"),
    LONG_CARD("k3","long_card", "长牌"),
    SHORT_CARD("k3","short_card", "短牌"),
    K3_DANXUAN_ERTONG("k3","k3_danxuan_ertong", "二同号单选"),
    K3_FUXUAN_ERTONG("k3","k3_fuxuan_ertong", "二同号复选"),
    K3_DANXUAN_SANTONG("k3","k3_danxuan_santong", "三同号单选"),
    K3_FUXUAN_SANTONG("k3","k3_tongxuan_santong", "三同号通选"),
    K3_TONGXUAN_SANLIAN("k3","k3_tongxuan_sanlian", "三连号通选"),
    K3_ERBUTONG("k3","k3_erbutong", "二不同号"),
    K3_SANBUTONG("k3","k3_sanbutong", "三不同号"),


    //十分彩(重庆幸运农场,广东快乐十分)
    SFC_FIRST("sfc","sfc_first", "第一球"),
    SFC_SECOND("sfc","sfc_second", "第二球"),
    SFC_THIRD("sfc","sfc_third", "第三球"),
    SFC_FOURTH("sfc","sfc_fourth", "第四球"),
    SFC_FIFTH("sfc","sfc_fifth", "第五球"),
    SFC_SIXTH("sfc","sfc_sixth", "第六球"),
    SFC_SEVENTH("sfc","sfc_seventh", "第七球"),
    SFC_EIGHTH("sfc","sfc_eighth", "第八球"),
    SFC_SUM8("sfc","sfc_sum8", "总和"),
    SFC_DRAGON_TIGER_12("sfc","sfc_dragon_tiger_12", "龙1虎2"),
    SFC_DRAGON_TIGER_13("sfc","sfc_dragon_tiger_13", "龙1虎3"),
    SFC_DRAGON_TIGER_14("sfc","sfc_dragon_tiger_14", "龙1虎4"),
    SFC_DRAGON_TIGER_15("sfc","sfc_dragon_tiger_15", "龙1虎5"),
    SFC_DRAGON_TIGER_16("sfc","sfc_dragon_tiger_16", "龙1虎6"),
    SFC_DRAGON_TIGER_17("sfc","sfc_dragon_tiger_17", "龙1虎7"),
    SFC_DRAGON_TIGER_18("sfc","sfc_dragon_tiger_18", "龙1虎8"),
    SFC_DRAGON_TIGER_23("sfc","sfc_dragon_tiger_23", "龙2虎3"),
    SFC_DRAGON_TIGER_24("sfc","sfc_dragon_tiger_24", "龙2虎4"),
    SFC_DRAGON_TIGER_25("sfc","sfc_dragon_tiger_25", "龙2虎5"),
    SFC_DRAGON_TIGER_26("sfc","sfc_dragon_tiger_26", "龙2虎6"),
    SFC_DRAGON_TIGER_27("sfc","sfc_dragon_tiger_27", "龙2虎7"),
    SFC_DRAGON_TIGER_28("sfc","sfc_dragon_tiger_28", "龙2虎8"),
    SFC_DRAGON_TIGER_34("sfc","sfc_dragon_tiger_34", "龙3虎4"),
    SFC_DRAGON_TIGER_35("sfc","sfc_dragon_tiger_35", "龙3虎5"),
    SFC_DRAGON_TIGER_36("sfc","sfc_dragon_tiger_36", "龙3虎6"),
    SFC_DRAGON_TIGER_37("sfc","sfc_dragon_tiger_37", "龙3虎7"),
    SFC_DRAGON_TIGER_38("sfc","sfc_dragon_tiger_38", "龙3虎8"),
    SFC_DRAGON_TIGER_45("sfc","sfc_dragon_tiger_45", "龙4虎5"),
    SFC_DRAGON_TIGER_46("sfc","sfc_dragon_tiger_46", "龙4虎6"),
    SFC_DRAGON_TIGER_47("sfc","sfc_dragon_tiger_47", "龙4虎7"),
    SFC_DRAGON_TIGER_48("sfc","sfc_dragon_tiger_48", "龙4虎8"),
    SFC_DRAGON_TIGER_56("sfc","sfc_dragon_tiger_56", "龙5虎6"),
    SFC_DRAGON_TIGER_57("sfc","sfc_dragon_tiger_57", "龙5虎7"),
    SFC_DRAGON_TIGER_58("sfc","sfc_dragon_tiger_58", "龙5虎8"),
    SFC_DRAGON_TIGER_67("sfc","sfc_dragon_tiger_67", "龙6虎7"),
    SFC_DRAGON_TIGER_68("sfc","sfc_dragon_tiger_68", "龙6虎8"),
    SFC_DRAGON_TIGER_78("sfc","sfc_dragon_tiger_78", "龙7虎8"),
    //快乐彩(幸运28,北京快乐8)
    KENO_SELECTION("keno","keno_selection", "选号"),
    KENO_SUM20("keno","keno_sum20", "和值"),
    KENO_NUMBER("keno","keno_number", "个数"),
    XY28_SUM3("keno","xy28_sum3", "和值"),
    //其他(福彩3D,体彩排列3)
    PL3_HUNDRED("qt", "pl3_hundred", "百定位"),
    PL3_TEN("qt", "pl3_ten", "十定位"),
    PL3_ONE("qt", "pl3_one", "个定位"),
    PL3_HUNDRED_TEN("qt", "pl3_hundred_ten", "百十定位"),
    PL3_HUNDRED_ONE("qt", "pl3_hundred_one", "百个定位"),
    PL3_TEN_ONE("qt", "pl3_ten_one", "十个定位"),
    PL3_HUNDRED_TEN_ONE("qt", "pl3_hundred_ten_one", "百十个定位"),
    PL3_ONE_ALL_THREE("qt", "pl3_all_three", "全三一字"),
    PL3_TWO_SAME("qt", "pl3_two_same", "二同"),
    PL3_TWO_DIFFERENT("qt", "pl3_two_different", "二不同"),
    PL3_THREE_SAME("qt", "pl3_three_same", "三同"),
    PL3_THREE_GROUP3("qt", "pl3_three_group3", "组三"),
    PL3_THREE_GROUP6("qt", "pl3_three_group6", "组六"),
    PL3_HUNDRED_TEN_SUM("qt", "pl3_hundred_ten_sum", "百十和数"),
    PL3_HUNDRED_ONE_SUM("qt", "pl3_hundred_one_sum", "百个和数"),
    PL3_TEN_ONE_SUM("qt", "pl3_ten_one_sum", "十个和数"),
    PL3_HUNDRED_TEN_ONE_SUM("qt", "pl3_hundred_ten_one_sum", "百十个和数"),
    PL3_GROUP3("qt", "pl3_group3", "组选三"),
    PL3_GROUP6("qt", "pl3_group6", "组选六"),
    PL3_SPAN("qt", "pl3_span", "跨度"),

    PL3_SANXING_ZXFS("qt","pl3_sanxing_zhixuan_fs", "三星直选复式"),
    PL3_SANXING_ZXDS("qt","pl3_sanxing_zhixuan_ds", "三星直选单式"),
    PL3_SANXING_ZXHZ("qt","pl3_sanxing_zhixuan_hz", "三星直选和值"),
    PL3_SANXING_Z3FS("qt","pl3_sanxing_zuxuan_z3fs", "三星组三复式"),
    PL3_SANXING_Z3DS("qt","pl3_sanxing_zuxuan_z3ds", "三星组三单式"),
    PL3_SANXING_Z6FS("qt","pl3_sanxing_zuxuan_z6fs", "三星组六复式"),
    PL3_SANXING_Z6DS("qt","pl3_sanxing_zuxuan_z6ds", "三星组六单式"),
    PL3_SANXING_HHZX("qt","pl3_sanxing_zuxuan_hhzx", "三星混合组选"),
    PL3_SANXING_ZUXHZ("qt","pl3_sanxing_zuxuan_zxhz", "三星组选和值"),
    PL3_ERXING_QEZXFS("qt","pl3_erxing_zhixuan_qefs", "前二直选复式"),
    PL3_ERXING_QEZXDS("qt","pl3_erxing_zhixuan_qeds", "前二直选单式"),
    PL3_ERXING_QEZUXFS("qt","pl3_erxing_zuxuan_qefs", "前二组选复式"),
    PL3_ERXING_QEZUXDS("qt","pl3_erxing_zuxuan_qeds", "前二组选单式"),
    PL3_ERXING_HEZXFS("qt","pl3_erxing_zhixuan_hefs", "后二直选复式"),
    PL3_ERXING_HEZXDS("qt","pl3_erxing_zhixuan_heds", "后二直选单式"),
    PL3_ERXING_HEZUXFS("qt","pl3_erxing_zuxuan_hefs", "后二组选复式"),
    PL3_ERXING_HEZUXDS("qt","pl3_erxing_zuxuan_heds", "后二组选单式"),
    PL3_YIXING_DWD("qt","pl3_yixing_dwd", "定位胆"),
    PL3_BUDINGWEI_SXYM("qt","pl3_budingwei_sxym", "三星一码不定位"),
    ;

    private String type;
    private String code;
    private String trans;

    LotteryBettingEnum(String type, String code, String trans) {
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

    public static String getTransByCode(String code){
        String trans = "";
        if(!TextUtils.isEmpty(code)){
            for (LotteryBettingEnum lotteryBet : LotteryBettingEnum.values()) {
                if(code.equals(lotteryBet.getCode())){
                    trans = lotteryBet.getTrans();
                    break;
                }
            }
        }
        return trans;
    }
}
