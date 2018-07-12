package com.dawoo.lotterybox.util.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;


public class HistoryReportAnimSetUtil {

    //动态设置margin
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    //箭头动画
    public static void rotation(View view, AnimatorSet animSet) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
        // 步骤3：根据需求组合动画
        animSet.play(rotation);
        animSet.setDuration(500);
        // 步骤4：启动动画
        animSet.start();

    }

    //下方整体动画
    public static void doAnimAll(View viewGroup, View icon, int px, AnimatorSet animSet, AnimatorSet animSet1) {
        ObjectAnimator transLateY = null;
        if (px < -10) {
            transLateY = ObjectAnimator.ofFloat(viewGroup, "translationY", 0, px, px);
        } else {
            transLateY = ObjectAnimator.ofFloat(viewGroup, "translationY", px * (-1), 0, 0);
        }
        // 步骤3：根据需求组合动画
        animSet.play(transLateY);
        animSet.setDuration(500);
        // 步骤4：启动动画
        animSet.start();
        rotation(icon, animSet1);//播放箭头动画
    }


}
