package com.dawoo.coretool.util;
/**
 * Created by benson on 17-12-24.
 */

import android.text.TextUtils;
import android.util.Log;

import com.dawoo.coretool.BuildConfig;

/**
 * 作者： quietUncle on 2016/2/26
 */
public class LogUtils {
    public static String tagPrefix = "LogUtls";//log前缀
    public static boolean debug = BuildConfig.LOG_DEBUG;

    public static void d(Object o) {
        logger("d", o);
    }
    public static void e(Object o) {
        logger("e", o);
    }
    public static void i(Object o) {
        logger("i", o);
    }
    public static void w(Object o) {
        logger("w", o);
    }

    /**
     *
     * @param type logger级别
     * @param o   logger内容
     */
    private static void logger(String type, Object o) {
        if (!debug) {
            return;
        }
        String msg=o+"";
        String tag = getTag(getCallerStackTraceElement());
        switch (type){
            case  "i":
                Log.i(tag,msg);
                break;
            case  "d":
                Log.d(tag,msg);
                break;
            case  "e":
                Log.e(tag,msg);
                break;
            case  "w":
                Log.w(tag,msg);
                break;
        }
    }


    private static String getTag(StackTraceElement element) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = element.getClassName(); // 获取到类名

        callerClazzName = callerClazzName.substring(callerClazzName
                .lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, element.getMethodName(),
                element.getLineNumber()); // 替换
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":"
                + tag;
        return tag;
    }

    /**
     * 获取线程状态
     * @return
     */
    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[5];
    }


    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;

    public static void e(String TAG, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.e(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.e(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }
}
