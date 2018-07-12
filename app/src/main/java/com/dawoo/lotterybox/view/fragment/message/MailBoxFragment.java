package com.dawoo.lotterybox.view.fragment.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.message.BaseMailBean;
import com.dawoo.lotterybox.bean.message.ReceiveMsgBean;
import com.dawoo.lotterybox.bean.message.SendMsgBean;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IMessageView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.message.MessageDetailActivity;
import com.dawoo.lotterybox.view.activity.message.MessageMailActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.message.MessageDetailActivity.MSG_ID;
import static com.dawoo.lotterybox.view.activity.message.MessageDetailActivity.MSG_SEND_TYPE;
import static com.dawoo.lotterybox.view.activity.message.MessageMailActivity.receiveMail_index;
import static com.dawoo.lotterybox.view.activity.message.MessageMailActivity.sendMail_index;


/**
 * Created by name on 18-3-18.
 */

public class MailBoxFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, IMessageView {
    public static final String EDIT = "编辑";
    public static final String REFRSH_Fragment = "刷新fragment 右上角的UI";
    public static final String MAIL_TYPE = "mail_type";
    public static final String REFRSH_Fragment_DATA = "刷新fragment数据";

    Unbinder unbinder;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.ll_b)
    LinearLayout mLlB;
    @BindView(R.id.fftop)
    FrameLayout mFftop;


    private int mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
    private int mPageSize = ConstantValue.RECORD_LIST_PAGE_SIZE;

    private ListAdapter mListAdapter;
    private MessagePresenter mMessagePresenter;
    private String[] mTitiles;
    private List<ReceiveMsgBean.ReceiveMsgListBean> mReceiveMsgListBeans = new ArrayList<>();//收件箱數據源
    private List<SendMsgBean.SendMsgListBean> mSendMsgListBeans = new ArrayList<>();//发件箱数据源
    private List<BaseMailBean> mSelectData = new ArrayList<>();//选中的数据

    private int mType = 0;//0 收件箱 1 发件箱
    private String mTabType;//当前的tab类型
    private boolean mIsNeedShowCheckBox;//是否需要展示选择按钮
    private int mCurrentReadMsgID = -1;//当前阅读的收件箱消息id
    private String mCurrentUserType = "--";

    public static MailBoxFragment newInstance(int type) {
        MailBoxFragment fragment = new MailBoxFragment();
        Bundle args = new Bundle();
        args.putInt(MAIL_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt(MAIL_TYPE);
        }
    }

    public MailBoxFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message_mailbox, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initView();
        initDate();
        initListener();
        return v;
    }

    private void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabType = mTitiles[tab.getPosition()];
                mSelectData.clear();
                if (mType == receiveMail_index) {
                    for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                        receiveMsgListBean.setSelect(false);
                    }
                } else if (mType == sendMail_index) {
                    for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
                        sendMsgListBean.setSelect(false);
                    }
                }
                mListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initView() {
        mTitiles = new String[]{getString(R.string.mail_all), getString(R.string.mail_readed), getString(R.string.mail_unread)};
        mTabType = mTitiles[0];
        initTab();
        if (mType == receiveMail_index) {
            mFftop.setVisibility(View.VISIBLE);
        } else if (mType == sendMail_index) {
            mFftop.setVisibility(View.GONE);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mListAdapter = new ListAdapter(R.layout.item_mail);
        mListAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mRecyclerView.setAdapter(mListAdapter);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    /**
     * 初始化 tab ui
     */
    private void initTab() {
        for (int i = 0; i < mTitiles.length; i++) {
            if (i == 0) {
                mTabLayout.addTab(mTabLayout.newTab().setText(mTitiles[i]), true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(mTitiles[i]), false);
            }
        }
    }

    private void initDate() {
        if (ConstantValue.PLAYER_AGENT.equals(DataCenter.getInstance().getUser().getPlayerType())) {
            //代理
            mCurrentUserType = "代理";
        } else if (ConstantValue.PLAYER_MEMBER.equals(DataCenter.getInstance().getUser().getPlayerType())) {
            //一般会员
            mCurrentUserType = "会员";
        } else {
            mCurrentUserType = "--";
        }

        mMessagePresenter = new MessagePresenter(mContext, this);
    }

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Subscribe(tags = {@Tag(EDIT + receiveMail_index)})
    public void editAndCancel0(String s) {
        if (mType == receiveMail_index)
            changeEditStatus(s);
    }

//    @Subscribe(tags = {@Tag(EDIT + sendMail_index)})
//    public void editAndCancel1(String s) {
//        if (mType == sendMail_index)
//            changeEditStatus(s);
//    }

    @Subscribe(tags = {@Tag(REFRSH_Fragment)})
    public void resresh(String s) {
        if (mType == Integer.parseInt(s) && mType == receiveMail_index) {
            mIsNeedShowCheckBox = false;
            mListAdapter.notifyDataSetChanged();
            mSelectData.clear();
            refreshEditTextUI();
        }
    }

    @Subscribe(tags = {@Tag(REFRSH_Fragment_DATA)})
    public void resreshData(String s) {
        if (mType == sendMail_index) {
            onRefresh();
        }
    }

    //点击编辑按钮触发的逻辑
    private void changeEditStatus(String status) {
        mSelectData.clear();
        if (status.equals("编辑")) {
            mIsNeedShowCheckBox = true;
            if (mListAdapter.getData() != null && mListAdapter.getData().size() > 0) {
                mLlB.setVisibility(View.VISIBLE);
            }

        } else {
            mIsNeedShowCheckBox = false;
            if (mListAdapter.getData() != null && mListAdapter.getData().size() > 0) {
                mLlB.setVisibility(View.GONE);
            }
        }
        mListAdapter.notifyDataSetChanged();
    }

    //全选
    private void chooseAll() {
        Log.e("choose", "chooseAll");
        if (mType == receiveMail_index) {
            if (mTabType.equals(mTitiles[0])) {
                if (mReceiveMsgListBeans.size() == mSelectData.size()) {
                    mSelectData.clear();
                    for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                        receiveMsgListBean.setSelect(false);
                    }
                    mListAdapter.notifyDataSetChanged();
                    return;
                }
            } else if (mTabType.equals(mTitiles[1]) || mTabType.equals(mTitiles[2])) {
                List<BaseMailBean> beans = new ArrayList<>();
                for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                    if (mTabType.equals(mTitiles[1]) && receiveMsgListBean.getReadStatus().equals("1")) {
                        beans.add(receiveMsgListBean);
                    } else if (mTabType.equals(mTitiles[2]) && receiveMsgListBean.getReadStatus().equals("0")) {
                        beans.add(receiveMsgListBean);
                    }
                }
                if (beans.size() == mSelectData.size()) {
                    mSelectData.clear();
                    for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                        receiveMsgListBean.setSelect(false);
                    }
                    mListAdapter.notifyDataSetChanged();
                    return;
                }
            }
            Log.e("chooses start for    ", "");
            for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                if (mTabType.equals(mTitiles[0])) {
                    receiveMsgListBean.setSelect(true);
                    Log.e("chooseset  Select ", receiveMsgListBean.getTitle());
                    if (!mSelectData.contains(receiveMsgListBean))
                        mSelectData.add(receiveMsgListBean);
                } else if (mTabType.equals(mTitiles[1])) {
                    if (receiveMsgListBean.getReadStatus().equals("1")) {
                        receiveMsgListBean.setSelect(true);
                        if (!mSelectData.contains(receiveMsgListBean))
                            mSelectData.add(receiveMsgListBean);
                    }
                } else if (mTabType.equals(mTitiles[2])) {
                    if (receiveMsgListBean.getReadStatus().equals("0")) {
                        receiveMsgListBean.setSelect(true);
                        if (!mSelectData.contains(receiveMsgListBean))
                            mSelectData.add(receiveMsgListBean);
                    }
                }
            }

        } else if (mType == sendMail_index) {
            if (mTabType.equals(mTitiles[0])) {
                if (mSendMsgListBeans.size() == mSelectData.size()) {
                    mSelectData.clear();
                    for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
                        sendMsgListBean.setSelect(false);
                    }
                    mListAdapter.notifyDataSetChanged();
                    return;
                }
            } else if (mTabType.equals(mTitiles[1]) || mTabType.equals(mTitiles[2])) {
                List<BaseMailBean> beans = new ArrayList<>();
                for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
                    if (mTabType.equals(mTitiles[1]) && sendMsgListBean.getReadStatus().equals("1")) {
                        beans.add(sendMsgListBean);
                    } else if (mTabType.equals(mTitiles[2]) && sendMsgListBean.getReadStatus().equals("0")) {
                        beans.add(sendMsgListBean);
                    }
                }
                if (beans.size() == mSelectData.size()) {
                    mSelectData.clear();
                    for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
                        sendMsgListBean.setSelect(false);
                    }
                    mListAdapter.notifyDataSetChanged();
                    return;
                }
            }

            for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
                if (mTabType.equals(mTitiles[0])) {
                    sendMsgListBean.setSelect(true);
                    if (!mSelectData.contains(sendMsgListBean))
                        mSelectData.add(sendMsgListBean);
                } else if (mTabType.equals(mTitiles[1])) {
                    if (sendMsgListBean.getReadStatus().equals("1")) {
                        sendMsgListBean.setSelect(true);
                        if (!mSelectData.contains(sendMsgListBean))
                            mSelectData.add(sendMsgListBean);
                    }
                } else if (mTabType.equals(mTitiles[2])) {
                    if (sendMsgListBean.getReadStatus().equals("0")) {
                        sendMsgListBean.setSelect(true);
                        if (!mSelectData.contains(sendMsgListBean))
                            mSelectData.add(sendMsgListBean);
                    }
                }
            }
        }
        mListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        mMessagePresenter.onDestory();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadMore() {
        mMessagePresenter.getMsgList(mType, mPageSize, mPageNumber, true);
    }

    @Override
    public void onRefresh() {
        if (!DataCenter.getInstance().isLogin()) {
            ActivityUtil.startLoginActivity();
            return;
        }
        mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        mMessagePresenter.getMsgList(mType, mPageSize, mPageNumber, false);
    }

    @Override
    public void onRefreshResult(Object o) {
        if (mType == receiveMail_index) {
            ReceiveMsgBean receiveMsgBean = (ReceiveMsgBean) o;
            if (receiveMsgBean.getError() > 0) {
                SingleToast.showMsg(receiveMsgBean.getMessage());
            }
            if (receiveMsgBean.getReceiveMsgListBeans() != null) {
                mReceiveMsgListBeans = receiveMsgBean.getReceiveMsgListBeans();
                mListAdapter.setNewData(mReceiveMsgListBeans);
                mPageNumber++;
            }
            refreshEditTextUI();
        } else if (mType == sendMail_index) {
            SendMsgBean sendMsgBean = (SendMsgBean) o;
            if (sendMsgBean.getError() > 0) {
                SingleToast.showMsg(sendMsgBean.getMessage());
            }
            if (sendMsgBean.getSendMsgListBeans() != null) {
                mSendMsgListBeans = sendMsgBean.getSendMsgListBeans();
                mListAdapter.setNewData(mSendMsgListBeans);
                mPageNumber++;
            }
        }
        //    refreshEditTextUI();
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
    }


    //刷新右上角编辑的ui
    private void refreshEditTextUI() {
        mIsNeedShowCheckBox = false;
        mLlB.setVisibility(View.GONE);
        if (null != mListAdapter.getData() && mListAdapter.getData().size() > 0) {
            ((MessageMailActivity) getActivity()).setEditButtonShow("编辑");
        } else {
            ((MessageMailActivity) getActivity()).setEditButtonGone("编辑");
        }
    }

    @Override
    public void onLoadMoreResult(Object o) {
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
        if (mType == receiveMail_index) {
            ReceiveMsgBean receiveMsgBean = (ReceiveMsgBean) o;
            if (receiveMsgBean.getError() > 0) {
                SingleToast.showMsg(receiveMsgBean.getMessage());
            }
            if (mListAdapter.getData().size() >= receiveMsgBean.getExtend().getTotalCount()) {
                mSwipeToLoadLayout.setLoadingMore(false);
                SingleToast.showMsg(getString(R.string.no_more_data));
                return;
            }
            if (receiveMsgBean.getReceiveMsgListBeans() != null) {
                mReceiveMsgListBeans.addAll(receiveMsgBean.getReceiveMsgListBeans());
                mPageNumber++;
            }

        } else if (mType == sendMail_index) {
            SendMsgBean sendMsgBean = (SendMsgBean) o;
            if (sendMsgBean.getError() > 0) {
                SingleToast.showMsg(sendMsgBean.getMessage());
            }
            if (mListAdapter.getData().size() >= sendMsgBean.getExtend().getTotalCount()) {
                mSwipeToLoadLayout.setLoadingMore(false);
                SingleToast.showMsg(getString(R.string.no_more_data));
                return;
            }
            if (sendMsgBean.getSendMsgListBeans() != null) {
                mSendMsgListBeans.addAll(sendMsgBean.getSendMsgListBeans());
                mPageNumber++;
            }

        }
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteMsg(Object o) {
        BaseHttpResult baseHttpResult = (BaseHttpResult) o;
        if (baseHttpResult.getError() == 0) {
            deleteCurrentData();
            SingleToast.showMsg(getString(R.string.action_success));
        } else {
            SingleToast.showMsg(getString(R.string.action_faliure));
        }
    }

    private void deleteCurrentData() {
        refreshEditTextUI();
        if (mType == receiveMail_index) {
            mReceiveMsgListBeans.removeAll(mSelectData);
        } else if (mType == sendMail_index) {
            mSendMsgListBeans.removeAll(mSelectData);
        }
        mListAdapter.notifyDataSetChanged();
        mSelectData.clear();
    }

    @OnClick({R.id.b_delete, R.id.b_choose_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b_delete:
                List<Integer> ids = new ArrayList<>();
                for (BaseMailBean listData : mSelectData) {
                    ids.add(listData.getId());
                }
                if (ids.size() == 0) {
                    SingleToast.showMsg(getString(R.string.must_choose_datas));
                    return;
                }
                mMessagePresenter.deleteMsg(ids.toArray());
                break;
            case R.id.b_choose_all:
                chooseAll();
                break;
        }
    }

    private class ListAdapter extends BaseQuickAdapter {

        public ListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            BaseMailBean mailListData = (BaseMailBean) item;

            if (mTabType.equals(mTitiles[0])) {
                setItemHeight(helper.itemView, 160);
            } else if (mTabType.equals(mTitiles[1]) && mailListData.getReadStatus().equals("0")) {
                setItemHeight(helper.itemView, 0);
            } else if (mTabType.equals(mTitiles[1]) && mailListData.getReadStatus().equals("1")) {
                setItemHeight(helper.itemView, 160);
            } else if (mTabType.equals(mTitiles[2]) && mailListData.getReadStatus().equals("1")) {
                setItemHeight(helper.itemView, 0);
            } else if (mTabType.equals(mTitiles[2]) && mailListData.getReadStatus().equals("0")) {
                setItemHeight(helper.itemView, 160);
            }


            if (mType == receiveMail_index && mailListData.getReadStatus().equals("0")) {
                helper.getView(R.id.rl_item).setBackgroundResource(R.drawable.shape_wite_radius);
            } else if (mType == receiveMail_index && mailListData.getReadStatus().equals("1")) {
                helper.getView(R.id.rl_item).setBackgroundResource(R.drawable.shape_wite_radius_read);
            }

            String sendType = "";
            if (mType == receiveMail_index) {
                ReceiveMsgBean.ReceiveMsgListBean bean = (ReceiveMsgBean.ReceiveMsgListBean) mailListData;
                if (null != bean.getSendType() && bean.getSendType().equals("0")) {
                    helper.setText(R.id.tv_message_left, "类型: 会员");
                    sendType = "会员";
                } else if (null != bean.getSendType() && bean.getSendType().equals("1")) {
                    helper.setText(R.id.tv_message_left, "类型: 站长");
                    sendType = "站长";
                }
                if (!TextUtils.isEmpty(bean.getCreateUsername())) {
                    helper.setText(R.id.tv_message_mid, "发件人: " + bean.getCreateUsername());
                }
            } else if (mType == sendMail_index) {
                SendMsgBean.SendMsgListBean bean = (SendMsgBean.SendMsgListBean) mailListData;
                //    helper.setText(R.id.tv_message_left, "类型: " + mCurrentUserType);
                if (!TextUtils.isEmpty(bean.getReceiverusername())) {
                    helper.setText(R.id.tv_message_mid, "收件人: " + bean.getReceiverusername());
                }
                sendType = mCurrentUserType;
            }
            helper.setText(R.id.tv_message_time, DateTool.getTimeFromLong(DateTool.FMT_DATE, mailListData.getCreateTime()));
            helper.setText(R.id.tv_message_title, mailListData.getTitle());
            helper.setText(R.id.tv_short_content, mailListData.getContent());
            if (!mailListData.isSelect()) {
                helper.getView(R.id.iv_select).setBackground(getResources().getDrawable(R.drawable.checkbox_off));
            } else {
                helper.getView(R.id.iv_select).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
            }
            String finalSendType = sendType;
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentReadMsgID = mailListData.getId();
                    Intent intent = new Intent(mContext, MessageDetailActivity.class);
                    intent.putExtra(MAIL_TYPE, mType);
                    intent.putExtra(MSG_ID, mCurrentReadMsgID);
                    intent.putExtra(MSG_SEND_TYPE, finalSendType);
                    startActivityForResult(intent, mType);
                }
            });

            helper.getView(R.id.iv_select).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSwipeToLoadLayout.isLoadingMore() || mSwipeToLoadLayout.isRefreshing())
                        return;
                    mailListData.setSelect(!mailListData.isSelect());
                    if (!mailListData.isSelect()) {
                        helper.getView(R.id.iv_select).setBackground(getResources().getDrawable(R.drawable.checkbox_off));
                    } else {
                        helper.getView(R.id.iv_select).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
                    }
                    if (mSelectData.contains(mailListData)) {
                        mSelectData.remove(mailListData);
                    } else {
                        mSelectData.add(mailListData);
                    }
                }
            });

            if (mIsNeedShowCheckBox) {
                helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.iv_select).setVisibility(View.INVISIBLE);
            }

        }
    }

    private void setItemHeight(View itemView, int dp) {
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        DensityUtil.dp2px(mContext, dp));
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mType) {
            if (resultCode == Activity.RESULT_OK) {
                if (mType == receiveMail_index) {
                    for (ReceiveMsgBean.ReceiveMsgListBean receiveMsgListBean : mReceiveMsgListBeans) {
                        if (receiveMsgListBean.getId() == mCurrentReadMsgID) {
                            receiveMsgListBean.setReadStatus("1");
                        }
                    }
                }
//                else {
//                    for (SendMsgBean.SendMsgListBean sendMsgListBean : mSendMsgListBeans) {
//                        if (sendMsgListBean.getId() == mCurrentReadMsgID) {
//                            sendMsgListBean.setReadStatus("1");
//                        }
//                    }
//                }
                mListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        LogUtils.d(s);
        //  收起刷新
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    /**
     * 登录成功后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGINED)})
    public void onLoginCallBack(String s) {
        // 登录成功后加载
        onRefresh();
    }

}
