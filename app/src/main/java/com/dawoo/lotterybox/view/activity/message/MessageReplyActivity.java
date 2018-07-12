package com.dawoo.lotterybox.view.activity.message;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.message.MailDetailBean;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IMessageBaseView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.view.fragment.message.MailBoxFragment.REFRSH_Fragment_DATA;


/**
 * Created by archar on 18-4-27.
 */

public class MessageReplyActivity extends BaseActivity implements IMessageBaseView {
    public static final String MAIL_DETAIL_DATA = "mail_detail_data";
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.et_site_sendMsg)
    EditText mEtSendMsg;
    @BindView(R.id.tv_num_tip)
    TextView mTvNumTip;

    private MessagePresenter mMessagePresenter;
    private MailDetailBean mMailDetailBean;

    public static void startActivity(Context context, MailDetailBean baseMailBean) {
        Intent intent = new Intent(context, MessageReplyActivity.class);
        intent.putExtra(MAIL_DETAIL_DATA, baseMailBean);
        context.startActivity(intent);
    }


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_mail_replay);
    }

    @Override
    protected void initViews() {
        if (getIntent().getSerializableExtra(MAIL_DETAIL_DATA) != null) {
            mMailDetailBean = (MailDetailBean) getIntent().getSerializableExtra(MAIL_DETAIL_DATA);
        }
        mHeadView.setHeader(getString(R.string.reply_mail), true);
        mMessagePresenter = new MessagePresenter(this, this);
    }

    @Override
    protected void initData() {
        if (mMailDetailBean == null) return;
        mTvReceiver.setText(mMailDetailBean.getCreateUsername());
        mTvTitle.setText(mMailDetailBean.getTitle());
        mTvContent.setText(mMailDetailBean.getContent());
        initListener();
    }

    private void initListener() {
        mEtSendMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvNumTip.setText(mEtSendMsg.getText().toString().length() + "/200字");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onRefreshResult(Object o) {
        BaseHttpResult baseHttpResult = (BaseHttpResult) o;
        if (baseHttpResult.getError() == 0) {
            SingleToast.showMsg(getString(R.string.action_success));
            RxBus.get().post(REFRSH_Fragment_DATA, "1");
            finish();
        } else {
            SingleToast.showMsg(getString(R.string.action_faliure));
        }
    }

    @Override
    public void onLoadMoreResult(Object o) {

    }

    @Override
    protected void onDestroy() {
        mMessagePresenter.onDestory();
        super.onDestroy();
    }


    @OnClick(R.id.b_replay_right_now)
    public void onViewClicked() {
        if (checkContent()) {
            String username = mTvReceiver.getText().toString();
            String content = mEtSendMsg.getText().toString();
            String title = "回复: " + mTvTitle.getText().toString();
            mMessagePresenter.replyMsg(username, content, title);
        }
    }

    private boolean checkContent() {
        if (TextUtils.isEmpty(mEtSendMsg.getText().toString())) {
            SingleToast.showMsg(getString(R.string.must_input_content));
            return false;
        }

        if (mEtSendMsg.getText().toString().length() > 200) {
            SingleToast.showMsg(getString(R.string.content_toolong));
            return false;
        }
        return true;
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
