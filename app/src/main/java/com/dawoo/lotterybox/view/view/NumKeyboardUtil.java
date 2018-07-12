package com.dawoo.lotterybox.view.view;


import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.R;

/**
 * @author jack
 * @date 18-2-12
 */

public class NumKeyboardUtil {
    private KeyboardView keyboardView;
    // 数字键盘
    private Keyboard k;
    private PasswordInputView ed;
    private String passNum;
    private int mPasswordLength = 6;


    public NumKeyboardUtil(Activity act, Context ctx, PasswordInputView edit) {
//        this.ed = edit;
//        k = new Keyboard(ctx, R.xml.inputkeyboard);
//        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
//        keyboardView.setKeyboard(k);
//        keyboardView.setEnabled(true);
//        keyboardView.setPreviewEnabled(true);
//        keyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        //一些特殊操作按键的codes是固定的比如完成、回退等
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            passNum = ed.getText().toString();
            int start = ed.getSelectionStart();
            // 回退
            if (primaryCode == Keyboard.KEYCODE_DELETE) {
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                // 完成
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {
                hideKeyboard();
            } else { //将要输入的数字现在编辑框中
                editable.insert(start, Character.toString((char) primaryCode));
            }
            /**
             * 当长度为6的时候代表已经输入完成
             * 给主界面一个回调函数告诉他输完了,
             * 下面该干啥干啥
             */
            if (passNum.length() == mPasswordLength) {
                if (onPasswordFinishListener != null) {
                    onPasswordFinishListener.onFinishPassword(passNum.toString().trim());
                }
            }

        }
    };


    public String getPswNum() {

        return passNum;
    }


    public void showKeyboard() {
        keyboardView.setVisibility(View.VISIBLE);
    }

    public void hideKeyboard() {
        keyboardView.setVisibility(View.GONE);
    }

    public int getKeyboardVisible() {
        return keyboardView.getVisibility();
    }

    /**
     *
     */
    private PayPasswodInput.OnPasswordFinishListener onPasswordFinishListener;

    public void setOnPasswordFinishListener(PayPasswodInput.OnPasswordFinishListener listener) {
        onPasswordFinishListener = listener;
    }

    public interface OnPasswordFinishListener {
        void onFinishPassword(String password);
    }

}
