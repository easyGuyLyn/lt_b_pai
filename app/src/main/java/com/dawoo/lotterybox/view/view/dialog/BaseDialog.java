package com.dawoo.lotterybox.view.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by benson on 18-2-11.
 */

public class BaseDialog extends Dialog{
    public BaseDialog(@NonNull Context context) {
        super(context);
        setParams();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setParams();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setParams();
    }

    void setParams() {
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    //    win.setGravity(Gravity.BOTTOM);

        win.setAttributes(lp);
    }
}
