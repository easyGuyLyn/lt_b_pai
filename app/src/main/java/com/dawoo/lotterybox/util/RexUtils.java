package com.dawoo.lotterybox.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 18-5-3.
 * @author alex
 */

public class RexUtils {
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.NUMBER_PHONE); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isQQ(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.QQ); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isRealName(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.REALNAME); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isEmail(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.EMAIL); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isLoginPWD(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.LOGIN_PWD); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isSSCode(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.SECURITY_PWD); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static boolean isRecCode(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.REC_CODE); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    public static boolean isAccount(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.ACCOUNT); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    public static boolean isWeChat(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.WeChat); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    public static boolean isPassWord(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.PASSWORD_LEVEL_4); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    public static boolean isNickName(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(RexConstantValue.NICKNAME); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
