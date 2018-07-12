package com.dawoo.lotterybox.util;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.view.PasswordInputView;
import com.dawoo.lotterybox.view.view.VirtualKeyboardView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by alex on 18-3-21.
 *
 * @author alex
 *         防微信支付安全键盘结合输入的工具类
 */

public class PWKeyboardManager {

    private Context mContext;
    private VirtualKeyboardView virtualKeyboardView;
    private PasswordInputView ed;
    private InputProgressCallback inputProgressCallback;
    private Animation inAnim;
    private Animation outAnim;
    private ArrayList<Map<String, String>> valueList;
    private boolean isKeyboardShow = false;

    public PWKeyboardManager(Context mContext, VirtualKeyboardView virtualKeyboardView, PasswordInputView ed, InputProgressCallback inputProgressCallback) {
        this.mContext = mContext;
        this.virtualKeyboardView = virtualKeyboardView;
        this.ed = ed;
        this.inputProgressCallback = inputProgressCallback;
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        initAnim();
        ed.setInputType(InputType.TYPE_NULL);
    }

    private void initData() {
        valueList = this.virtualKeyboardView.getValueList();
    }

    private void initEvent() {
        this.ed.setOnClickListener(v -> showKeyborad());
        this.virtualKeyboardView.getLayoutBack().setOnClickListener(v -> hideKeyborad());
        this.virtualKeyboardView.getGridView().setOnItemClickListener(onItemClickListener);
        ed.setFinishCallback(number -> inputProgressCallback.onFinish(number));
    }

    public void hideKeyborad() {
        virtualKeyboardView.startAnimation(outAnim);
        virtualKeyboardView.setVisibility(View.GONE);
        isKeyboardShow = false;
    }

    public void showKeyborad() {
        if (!isKeyboardShow) {
            virtualKeyboardView.setFocusable(true);
            virtualKeyboardView.setFocusableInTouchMode(true);
            virtualKeyboardView.startAnimation(inAnim);
            virtualKeyboardView.setVisibility(View.VISIBLE);
            isKeyboardShow = true;
        }

    }

    public void clearData() {
        ed.setText("");
    }

    /**
     * 数字键盘显示动画
     */
    private void initAnim() {
        inAnim = AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in);
        outAnim = AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_out);
    }


    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = ed.getText().toString().trim();
                amount = amount + valueList.get(position).get("name");
                ed.setText(amount);
                Editable ea = ed.getText();
                ed.setSelection(ea.length());
            } else {
                if (position == 9) {      //点击退格键
                    String amount = ed.getText().toString().trim();
                    if (!amount.contains(".")) {
                        //  amount = amount + valueList.get(position).get("name");
                        ed.setText(amount);
                        Editable ea = ed.getText();
                        ed.setSelection(ea.length());
                    }
                }
                if (position == 11) {      //点击退格键
                    String amount = ed.getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        ed.setText(amount);

                        Editable ea = ed.getText();
                        ed.setSelection(ea.length());
                    }
                }
            }
        }
    };


    public interface InputProgressCallback {
        void onFinish(String number);
    }


}
