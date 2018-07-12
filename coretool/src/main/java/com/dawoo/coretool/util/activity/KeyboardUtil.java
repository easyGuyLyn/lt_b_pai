package com.dawoo.coretool.util.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by benson on 18-2-8.
 */

public class KeyboardUtil {

    /**
     * 开关键盘
     */
    public static void toggleKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }



    /**
     * 展示输入法软键盘
     *
     * @param activity
     * @param currentFocusedView 当前获得焦点了的view
     */
    public static void showInputKeyboard(Activity activity, View currentFocusedView) {
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(currentFocusedView, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏输入法键盘
     *
     * @param activity
     */
    public static void hideInputKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken()
                , InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
