package com.dawoo.lotterybox.view.activity.message;

import android.text.Html;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.message.AnnouncementBean;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IMessageBaseView;
import com.dawoo.lotterybox.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by name on 18-3-18.
 */

public class AnnouncementDetailActivity extends BaseActivity implements IMessageBaseView {
    public static final String MSG_ID = "msg_id";
    public static final String MSG_DATA = "msg_data";


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tv_message_time)
    TextView tvMessageTime;
    @BindView(R.id.tv_short_content)
    TextView tvShortContent;

    private MessagePresenter mMessagePresenter;
    private int mId;
    private AnnouncementBean.NoticeListBean mNoticeListBean;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_announcement_detail);
        ButterKnife.bind(this);

    }

    @Override
    protected void initViews() {
        titleName.setText(R.string.msgAnn_detail);
        leftBtn.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        mId = getIntent().getIntExtra(MSG_ID, -1);
        mNoticeListBean = (AnnouncementBean.NoticeListBean) getIntent().getSerializableExtra(MSG_DATA);
        mMessagePresenter = new MessagePresenter(this, this);
        if (mNoticeListBean != null) {
            tvMessageTime.setText(DateTool.getTimeFromLong(DateTool.FMT_DATE, mNoticeListBean.getCreateTime()));
            tvShortContent.setText(Html.fromHtml(mNoticeListBean.getContent()));
        }
    }


    @Override
    public void onRefreshResult(Object o) {

    }

    @Override
    public void onLoadMoreResult(Object o) {

    }

    @Override
    protected void onDestroy() {
        mMessagePresenter.onDestory();
        super.onDestroy();
    }
}
