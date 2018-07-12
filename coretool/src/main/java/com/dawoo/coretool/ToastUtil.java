package com.dawoo.coretool;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * Created by benson on 17-12-28.
 */

public class ToastUtil {
    public static void showResShort(Context context, @StringRes int stringId) {
        Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
    }

    public static void showResLong(Context context, @StringRes String stringId) {
        Toast.makeText(context, stringId, Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 单列的toast
     */
    private static Toast mToast;

}
