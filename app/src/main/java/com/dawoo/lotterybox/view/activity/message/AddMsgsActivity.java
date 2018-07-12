package com.dawoo.lotterybox.view.activity.message;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.fragment.message.SendMsgFragment;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;

/**
 * Created by archar on 18-3-19.
 */

public class AddMsgsActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeaderView mHeadView;

    private FragmentManager mFragmentManager;

    public static void goActivity(Context context) {
        Intent intent = new Intent(context, AddMsgsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_note_rcd);
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mHeadView.setHeader(getString(R.string.mial_add_msg), true);
    }

    @Override
    protected void initData() {
        addFragment(SendMsgFragment.class);
    }

    private void addFragment(Class<? extends Fragment> clazz) {
        Fragment fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 点击 EditText 以外的区域 ，自动收起软键盘
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
