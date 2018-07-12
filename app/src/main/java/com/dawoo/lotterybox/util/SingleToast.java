package com.dawoo.lotterybox.util;

import android.widget.Toast;

import com.dawoo.lotterybox.BoxApplication;

/**
 * Created by archar on 18-2-8.
 */

public class SingleToast {

    private static final int MIN_DELAY_TIME = 2000;  // 两次间隔
    private static long lastClickTime;
    private static String lastStr = "";

    private static Toast mToast;

    public static void showMsg(String msg) {
        if (isNeedCheck() && lastStr.equals(msg)) {
            return;
        }
        lastStr = msg;
        if (mToast != null) {
            mToast.setText(msg);
        } else {
            mToast = Toast.makeText(BoxApplication.getContext(), msg, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    private static boolean isNeedCheck() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
