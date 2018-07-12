package com.dawoo.lotterybox;

/**
 * 常量
 */

public interface ConstantValue {
//    /**
//     * 获取域名请求地址
//     */
//    String BASE_URL = "https://apiplay.info:1344/boss/";
//    //String BASE_URL = "http://192.168.0.92/boss/"; //测试
    /**
     * 线路域名URL
     */
    String LINE_URL = "/app/line.html";
    /**
     * 优惠详情URL
     */
    String PROMO_DETAIL_URL = "/#/promo/";


    String WEBVIEW_URL = "WEBVIEW_URL";
    String WEBVIEW_TYPE = "WEBVIEW_TYPE";
    String WEBVIEW_TYPE_GAME = "WEBVIEW_TYPE_GAME";
    String WEBVIEW_TYPE_GAME_WITH_HEAD_VIEW = "WEBVIEW_TYPE_GAME_WITH_HEAD_VIEW";
    // 平台一般网页
    String WEBVIEW_TYPE_ORDINARY = "WEBVIEW_TYPE_ORDINARY";
    String WEBVIEW_TYPE_PAY = "WEBVIEW_TYPE_PAY";
    // 第三方一般网页
    String WEBVIEW_TYPE_THIRD_ORDINARY = "WEBVIEW_TYPE_THIRD_ORDINARY";

    /**
     * 登录后
     */
    String EVENT_TYPE_LOGINED = "EVENT_TYPE_LOGINED";
    /**
     * 通知刷新用户相关信息
     */
    String EVENT_TYPE_USER_INFO = "EVENT_TYPE_USER_INFO";
    /**
     * 登出帐户
     */
    String EVENT_TYPE_LOGOUT = "EVENT_TYPE_LOGOUT";
    /**
     * 接口失败，抛出异常
     * 页面有刷新或者加载更多，或者页面要在抛出异常处理一些事物
     */
    String EVENT_TYPE_NETWORK_EXCEPTION = "EVENT_TYPE_NETWORK_EXCEPTION";
    /**
     * 接口返回成功但是服务器校验失败处理
     */
    String EVENT_TYPE_NETWORK_RETURN = "String EVENT_TYPE_NETWORK_EXCEPTION";
    /**
     * 通知图表玩法改变
     */
    String EVENT_TYPE_CHART_HOW_TO_PLAY = "EVENT_TYPE_CHART_HOW_TO_PLAY";

    /**
     * 绑卡成功
     */
    String EVENT_BIND_CARD_SUCCEED = "event_bind_card_succeed";
    /**
     * 修改昵称成功
     */
    String EVENT_UPDATE_NICKNAME = "update_nick_name";
    /**
     * 修改真实姓名成功
     */
    String EVENT_UPDATE_REAL_NAMW = "update_real_name";
    /**
     * 修改头像成功
     */
    String EVENT_UPDATE_AVATER = "event_update_avater";
    /**
     * 设置安全密码
     */
    String EVENT_SET_SS_CODE = "event_set_ss_code";
    /**
     * 遗漏或冷热数据更新
     */
    String EVENT_CHANGE_LR = "event_change_ls";
    /**
     * 随机或者重置
     */
    String EVENT_CHANGE_SELECTED = "event_change_selected";
    /**
     * 通知首页开奖列表重新拉取数据
     */
    String EVENT_LOTTERY_RCD_DATA_REFRESH = "EVENT_LOTTERY_RCD_DATA_REFRESH";

    /**
     * A盘购物车操作数据
     */
    String EVENT_LOTTERY_CART_DATA_CHANGE = "event_lottery_cart_data_change";

    /**
     * 通知投注页面重新拉取数据
     */
    String EVENT_LOTTERY_History_DATA_REFRESH = "EVENT_LOTTERY_HISTORY_DATA_REFRESH";
    /**
     *
     */
    String EVENT_TEAM_STATE_SEARCH = "event_team_state_search";
    /**
     * 获取到了对应参数
     */
    String EVENT_GET_NET_TYPE = "event_get_net_type";
    /**
     * 跳转到主页
     */
    String EVENT_TYPE_GOTOTAB_HOME = "EVENT_TYPE_GOTOTAB_HOME";
    /**
     * 正在开奖中
     */
    String EVENT_TYPE_OPENING_LOTTERY = "EVENT_TYPE_OPENING_LOTTERY";
    /**
     * 开户成功
     */
    String EVENT_SIMPLE_OA_SUCCEED="event_simple_open_account_succeed";
    /**
     * 接口单页请求数据条数
     */
    int RECORD_LIST_PAGE_SIZE = 12;
    int RECORD_LIST_PAGE_NUMBER = 1;
    int LOTTERY_LIST_PAGE_SIZE = 120;
    int LOTTERY_LIST_PAGE_NUMBER = 1;


    String LT_CODE = "lt_code";
    String LT_NAME = "lt_name";
    String LT_MODLE = "lt_modle";
    String RENCT_DATA = "RENCT_DATA"; //走势图跳转携带数据key
    String MODE_ALL_PLAY = "all";
    String MODE_OFFICIAL_PLAY = "official";
    String MODE_TRADITIONAL_PLAY = "tradition";
    String OFFICIAL_PLAY = "官方";
    String TRADITIONAL_PLAY = "传统";
    String ESPECIAL_LT_NAME = "分时时彩";

    String THEME_COLOR_BLUE = "white";
    String THEME_COLOR_RED = "red";
    String THEME_COLOR_GREEN = "green";
    String THEME_COLOR_BALCK = "black";

    String APP_KEY = "Ap4P4G1QF7lHgg==";
    String APP_SECRET = "e5a6dbf1b5205f1ac1f5e668f8d4c9a7";

    String VOICE_ON_CLICK = "VOICE_ON_CLICK";

    int CalendarRequestCode = 1;
    int uploadimage = 1;

    String PLAYER_AGENT = "agent";
    String PLAYER_MEMBER = "member";


    /**
     * 线上支付
     */
    String ONLINE = "online";
    /**
     * 比特币
     */
    String BITCOIN = "bitcoin";
    /**
     * 微信支付
     */
    String WECHAT = "wechat";
    /**
     * 网银存款
     */
    String COMPANY = "company";

    /**
     * 支付宝支付
     */
    String ALIPAY = "alipay";

    /**
     * QQ钱包
     */
    String QQ = "qq";
    /**
     * 京东钱包
     */
    String JD = "jd";
    /**
     * "百度钱包"
     */
    String BD = "bd";
    /**
     * 一码付
     */
    String ONECODEPAY = "onecodepay";
    /**
     * "银联扫码"
     */
    String UNIONPAY = "unionpay";
    /**
     * "柜员机"
     */
    String COUNTER = "counter";

    /**
     * "易收付"
     */
    String EASYPAY = "easy";
    /**
     * "其他"
     */
    String OTHER = "other";
    /**
     * "快充中心"
     */
    String IS_FASTRECHARGE = "isfastrecharge";

    String packageQQ = "com.tencent.mobileqq";
    String packageali = "com.eg.android.AlipayGphone";
    String packagewechat = "com.tencent.mm";
    String PERMISSIONS_STORAGE_WRITE = "android.permission.WRITE_EXTERNAL_STORAGE";
    String PERMISSIONS_STORAGE_READ = "android.permission.READ_EXTERNAL_STORAGE";

    String COPY_RIGHT = "COPYRIGHT © 2004-2018";

    int RESULE_SUCCESS=1;
    int REQUEST_DEPOSIT=101;
}
