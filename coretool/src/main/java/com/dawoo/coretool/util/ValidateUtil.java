package com.dawoo.coretool.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by benson on 18-2-7.
 */

public class ValidateUtil {
    public static boolean isStringFormatCorrect(String str) {
        String strPattern = "[a-zA-Z_0-9]{4,16}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
