package com.dawoo.lotterybox.view.activity.message;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.fragment.message.MailBoxFragment;
import com.dawoo.lotterybox.view.fragment.message.SendMsgFragment;
import com.dawoo.lotterybox.view.view.CustomViewPager;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.view.fragment.message.MailBoxFragment.EDIT;
import static com.dawoo.lotterybox.view.fragment.message.MailBoxFragment.REFRSH_Fragment;

/**
 * archar
 * 邮箱
 */

public class MessageMailActivity extends BaseActivity {
    @BindView(R.id.hend_back)
    ImageView hendBack;
    @BindView(R.id.message_receive)
    Button mMessage_receive;
    @BindView(R.id.message_send_email)
    Button mMessage_send_email;
    @BindView(R.id.message_send)
    Button mMessage_send;

    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;

    @BindView(R.id.tv_edit)
    TextView mTvEdit;

    public static final int receiveMail_index = 0;//收件箱
    public static final int sendMail_index = 1;//发件箱
    public static final int sendMSG_index = 2;//发消息
    private List<Fragment> mFragments = new ArrayList<>();

    private MessageViewPageAdapter mMessageViewPageAdapter;
    private int mCurrentType = 0;
    private android.os.Handler mHandler = new android.os.Handler();

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_message_mail);
    }

    @Override
    protected void initViews() {
        mFragments.add(MailBoxFragment.newInstance(receiveMail_index));
        mFragments.add(MailBoxFragment.newInstance(sendMail_index));
        mFragments.add(SendMsgFragment.newInstance());
        mMessageViewPageAdapter = new MessageViewPageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mMessageViewPageAdapter);
        mViewPager.setScanScroll(false);
        switchTab(receiveMail_index);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.hend_back, R.id.message_receive, R.id.message_send_email, R.id.message_send, R.id.view_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hend_back:
                finish();
                break;
            case R.id.message_receive:
                switchTab(receiveMail_index);
                break;
            case R.id.message_send_email:
                switchTab(sendMail_index);
                break;
            case R.id.message_send:
                switchTab(sendMSG_index);
                break;
            default:
        }
    }


    @OnClick(R.id.tv_edit)
    public void onViewClicked() {
        RxBus.get().post(EDIT + mCurrentType, mTvEdit.getText().toString());
        if (mTvEdit.getText().toString().equals("编辑")) {
            mTvEdit.setText("取消");
        } else {
            mTvEdit.setText("编辑");
        }
    }


    public void setEditButtonShow(String status) {
        mTvEdit.setVisibility(View.VISIBLE);
        mTvEdit.setText(status);
    }

    public void setEditButtonGone(String status) {
        mTvEdit.setVisibility(View.GONE);
        mTvEdit.setText(status);
    }


    private class MessageViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();

        public MessageViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //   super.destroyItem(container, position, object);
        }
    }

    /**
     * 状态选择
     *
     * @param tabIndex
     */
    void switchTab(int tabIndex) {
        mTvEdit.setText("编辑");
        mTvEdit.setVisibility(View.GONE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.get().post(REFRSH_Fragment, "" + tabIndex);
            }
        }, 1);

        switch (tabIndex) {
            case receiveMail_index:
                mMessage_receive.setTextColor(getResources().getColor(R.color.colorAccent));
                mMessage_send_email.setTextColor(getResources().getColor(R.color.white));
                mMessage_send.setTextColor(getResources().getColor(R.color.white));
                mMessage_receive.setSelected(true);
                mMessage_send_email.setSelected(false);
                mMessage_send.setSelected(false);
                mViewPager.setCurrentItem(receiveMail_index);
                mCurrentType = receiveMail_index;
                break;
            case sendMail_index:
                mMessage_receive.setTextColor(getResources().getColor(R.color.white));
                mMessage_send_email.setTextColor(getResources().getColor(R.color.colorAccent));
                mMessage_send.setTextColor(getResources().getColor(R.color.white));
                mMessage_receive.setSelected(false);
                mMessage_send_email.setSelected(true);
                mMessage_send.setSelected(false);
                mViewPager.setCurrentItem(sendMail_index);
                mCurrentType = sendMail_index;
                break;
            case sendMSG_index:
                mMessage_receive.setTextColor(getResources().getColor(R.color.white));
                mMessage_send_email.setTextColor(getResources().getColor(R.color.white));
                mMessage_send.setTextColor(getResources().getColor(R.color.colorAccent));
                mMessage_receive.setSelected(false);
                mMessage_send_email.setSelected(false);
                mMessage_send.setSelected(true);
                mViewPager.setCurrentItem(sendMSG_index);
                mCurrentType = sendMSG_index;
                break;
            default:
        }
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
