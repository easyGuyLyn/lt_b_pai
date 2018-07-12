package com.dawoo.lotterybox.util.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class LotteryBAnimSetUtil {

    public static long DURATION = 2200;//盘口UI动画时间
    private static boolean isOpenAnim = true;//盘口动画是否打开

    public static void doPeriodTextViewAnim(AnimatorSet animSet, View view, int px) {
        if (!isOpenAnim) return;
        ObjectAnimator transLateY = ObjectAnimator.ofFloat(view, "translationY", px * (-1), 0, 0);
        // 步骤3：根据需求组合动画
        animSet.play(transLateY);
        animSet.setDuration(DURATION);
        // 步骤4：启动动画
        animSet.start();
    }

    public static void doXhAnim(AnimatorSet animSet, View view, int px) {
        if (!isOpenAnim) return;
        ObjectAnimator transLateY = ObjectAnimator.ofFloat(view, "translationY", px, 0, 0);
        // 步骤3：根据需求组合动画
        animSet.play(transLateY);
        animSet.setDuration(DURATION);
        // 步骤4：启动动画
        animSet.start();
    }

    public static void doNumsAnim(AnimatorSet animSet, View view, int px) {
        if (!isOpenAnim) return;
        ObjectAnimator transLateX1 = ObjectAnimator.ofFloat(view, "translationX", px * (-1), 50, 0);
        animSet.setDuration(DURATION + 800);
        // 步骤3：根据需求组合动画
        animSet.play(transLateX1);
        // 步骤4：启动动画
        animSet.start();
    }

    public static void doStatusAnim(AnimatorSet animSet, TextView view, int px) {
        if (!isOpenAnim) return;
        if (TextUtils.isEmpty(view.getText().toString())) {
            ObjectAnimator transLateX1 = ObjectAnimator.ofFloat(view, "translationY", px * (-1), 0, 0);

            animSet.setDuration(DURATION);
            // 步骤3：根据需求组合动画
            animSet.play(transLateX1);
            // 步骤4：启动动画
            animSet.start();
        }
    }

    public static void doStatusAnimBjkl8(AnimatorSet animSet, TextView view, int px) {
        if (!isOpenAnim) return;
        if (TextUtils.isEmpty(view.getText().toString())) {
            ObjectAnimator transLateY = ObjectAnimator.ofFloat(view, "translationY", px * (-1), 0, 0);
            // 步骤3：根据需求组合动画
            animSet.play(transLateY);
            animSet.setDuration(DURATION);
            // 步骤4：启动动画
            animSet.start();
        }
    }

    public static void doTimeAnim(AnimatorSet animSet, View view, int px) {
        if (!isOpenAnim) return;
        ObjectAnimator transLateY = ObjectAnimator.ofFloat(view, "translationY", px, 0, 0);
        // 步骤3：根据需求组合动画
        animSet.play(transLateY);
        animSet.setDuration(DURATION);
        // 步骤4：启动动画
        animSet.start();
    }
}
