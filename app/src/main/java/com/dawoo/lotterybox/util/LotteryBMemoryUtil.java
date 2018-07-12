package com.dawoo.lotterybox.util;

import android.widget.TextView;

import com.dawoo.lotterybox.BoxApplication;

/**
 * Created by archar on 18-3-15.
 */

public class LotteryBMemoryUtil {

    public static void setMemoryData(String code, TextView mTvInputNote) {
        if (!SharePreferenceUtil.getMemoryFunction(BoxApplication.getContext(), code).equals("-1")) {
            SharePreferenceUtil.putMemoryFunction(BoxApplication.getContext(), mTvInputNote.getText().toString(), code);
        }

    }
}
