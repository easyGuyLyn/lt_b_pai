package com.dawoo.lotterybox.view.activity.message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.message.MailDetailBean;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IMessageBaseView;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dawoo.lotterybox.view.activity.message.MessageMailActivity.receiveMail_index;


/**
 * Created by archar on 18-4-27.
 */

public class MessageDetailActivity extends BaseActivity implements IMessageBaseView {
    public static final String MAIL_TYPE = "mail_type";
    public static final String MSG_ID = "msg_id";
    public static final String MSG_SEND_TYPE = "msg_send_type";

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_sender)
    TextView mTv_sender;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_createTime)
    TextView mTvCreateTime;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.b_replay)
    Button mBReplay;
    @BindView(R.id.t_man_r_s)
    TextView mTManRS;

    private MessagePresenter mMessagePresenter;
    private int mId;
    private int mType;
    private MailDetailBean mMailDetailBean;
    private String mTypeName;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_mail_msg_detail);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.mail_detail), true);

        mId = getIntent().getIntExtra(MSG_ID, -1);
        mType = getIntent().getIntExtra(MAIL_TYPE, -1);
        mTypeName = getIntent().getStringExtra(MSG_SEND_TYPE);
        if (mType == receiveMail_index) {
            mBReplay.setVisibility(View.VISIBLE);
        } else {
            mTManRS.setText(R.string.receiver_mail);
        }
    }

    @Override
    protected void initData() {
        mMessagePresenter = new MessagePresenter(this, this);
        mMessagePresenter.getMsgDetail(mId, mType);
    }


    @Override
    public void onRefreshResult(Object o) {
        MailDetailBean baseMailBean = (MailDetailBean) o;
        if (baseMailBean != null) {
            setResult(Activity.RESULT_OK);
            mMailDetailBean = baseMailBean;
            mTvTitle.setText(baseMailBean.getTitle());
            mTvType.setText(mTypeName);
            mTvContent.setText(baseMailBean.getContent());
            mTvCreateTime.setText(DateTool.getTimeFromLong(DateTool.FMT_DATE, baseMailBean.getCreateTime()));

            if (mType == receiveMail_index) {
                mTv_sender.setText(baseMailBean.getCreateUsername());
            }else {
                mTv_sender.setText(baseMailBean.getReceiverUsername());
            }
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


    @OnClick(R.id.b_replay)
    public void onViewClicked() {
        if (mMailDetailBean != null) {
            MessageReplyActivity.startActivity(this, mMailDetailBean);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
