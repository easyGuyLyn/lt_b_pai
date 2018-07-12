package com.dawoo.lotterybox.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dawoo.coretool.util.activity.ActivityStackManager;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.util.ScreenListener;
import com.dawoo.lotterybox.view.activity.setting.LockActivity;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity的基本类
 * Created by benson on 17-12-19.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private Unbinder mBind;
    private ScreenListener mScreenListener;
    static boolean isActive = true;
    private ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().addActivity(this);
        createLayoutView();
        initStatusBar();
        mBind = ButterKnife.bind(this); // 初始化ButterKnife
        mScreenListener = new ScreenListener(this);
        mScreenListener.register(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                if (SPUtils.getInstance().getBoolean(SPConfig.IS_OPEN_CODE)) {
                    Intent intent = new Intent(BaseActivity.this, LockActivity.class);
                    intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.UN_LOCK);
                    startActivity(intent);
                }

            }

            @Override
            public void onScreenOff() {

            }

            @Override
            public void onUserPresent() {

            }
        });
        initViews();
        initData();
    }

    //状态栏
    private void initStatusBar() {
        //兼容４．４
        //   StatusBarUtil.setTranslucent(this,50);
        //     StatusBarUtil.setDarkMode(this);
        mImmersionBar = ImmersionBar
                .with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        addStatusView(this);
    }

    //动态设置填充高度
    public static void addStatusView(Activity activity) {
        View top = activity.findViewById(R.id.v_top_child);
        if (top != null) {
            top.post(new Runnable() {
                @Override
                public void run() {
                    top.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.getStatusBarHeight(activity)));
                }
            });
        }
    }

    //动态设置填充高度
    public static void addStatusView(View view,Context fragment) {
        View top = view.findViewById(R.id.v_top_child);
        if (top != null) {
            top.post(new Runnable() {
                @Override
                public void run() {
                    top.setLayoutParams(new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.getStatusBarHeight(fragment)));
                }
            });
        }
    }

    protected abstract void createLayoutView();


    protected abstract void initViews();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        if (!isActive) {
            isActive = true;
            if (SPUtils.getInstance().getBoolean(SPConfig.IS_OPEN_CODE)) {
                Intent intent = new Intent(BaseActivity.this, LockActivity.class);
                intent.putExtra(LockActivity.LOCK_TYPE, LockActivity.UN_LOCK);
                startActivity(intent);
            }


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!AppUtils.isAppForeground()) {
            isActive = false;
        }
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        mScreenListener.unregister();
        ActivityStackManager.getInstance().removeActivity(this);
    }

}
