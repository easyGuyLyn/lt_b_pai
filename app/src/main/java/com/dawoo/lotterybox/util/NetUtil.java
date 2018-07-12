package com.dawoo.lotterybox.util;


/**
 * Created by benson on 18-1-3.
 */

public class NetUtil {

    public static String handleUrl(String domain, String url) {
        // 是不是http开头
        // 第一个字符是不是包含反斜杠
        if (url == null) {
            return "";
        }
        if (!url.contains("http") && !url.contains("www.")) {
            if (url.indexOf("/") == 0) {
                return (domain + url);
            } else {
                return (domain + "/" + url);
            }
        }
        return url;
    }
}
