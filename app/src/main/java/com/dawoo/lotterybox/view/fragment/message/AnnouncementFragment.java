package com.dawoo.lotterybox.view.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.message.AnnouncementBean;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IAnnouncementView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.message.AnnouncementDetailActivity;
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
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.message.AnnouncementDetailActivity.MSG_DATA;
import static com.dawoo.lotterybox.view.activity.message.AnnouncementDetailActivity.MSG_ID;

/**
 * Created by name on 18-3-18.
 */

public class AnnouncementFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, IAnnouncementView {

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    Unbinder unbinder;

    private int mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
    private int mPageSize = ConstantValue.RECORD_LIST_PAGE_SIZE;

    private ListAdapter mListAdapter;
    private MessagePresenter mMessagePresenter;
    private List<AnnouncementBean.NoticeListBean> mList = new ArrayList<>();


    public AnnouncementFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message_annount_or_site, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initView();
        initDate();
        return v;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mListAdapter = new ListAdapter(R.layout.item_announcement);
        mListAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mRecyclerView.setAdapter(mListAdapter);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    private void initDate() {
        mMessagePresenter = new MessagePresenter(mContext, this);
    }

    @Override
    protected void loadData() {
        onRefresh();
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
        mMessagePresenter.getBulletins(mPageSize, mPageNumber, true);
    }

    @Override
    public void onRefresh() {
//        if (!DataCenter.getInstance().isLogin()) {
//            ActivityUtil.startLoginActivity();
//            return;
//        }
        mPageNumber = ConstantValue.RECORD_LIST_PAGE_NUMBER;
        mMessagePresenter.getBulletins(mPageSize, mPageNumber, false);
    }

    @Override
    public void onRefreshResult(Object o) {
        AnnouncementBean announcementBean = (AnnouncementBean) o;
        if (announcementBean.getError() > 0) {
            SingleToast.showMsg(announcementBean.getMessage());
        }
        if (announcementBean.getNoticeListBeans() != null) {
            mList = announcementBean.getNoticeListBeans();
            mListAdapter.setNewData(mList);
            mPageNumber++;
        }

        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMoreResult(Object o) {
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
        AnnouncementBean announcementBean = (AnnouncementBean) o;
        if (announcementBean.getError() > 0) {
            SingleToast.showMsg(announcementBean.getMessage());
        }
        if (mListAdapter.getData().size() >= announcementBean.getExtend().getTotalCount()) {
            SingleToast.showMsg(getString(R.string.no_more_data));
            return;
        }

        if (announcementBean.getNoticeListBeans() != null) {
            mList.addAll(announcementBean.getNoticeListBeans());
            mListAdapter.notifyDataSetChanged();
            mPageNumber++;
        }


    }


    private class ListAdapter extends BaseQuickAdapter {


        public ListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            AnnouncementBean.NoticeListBean noticeListBean = (AnnouncementBean.NoticeListBean) item;
            helper.setText(R.id.tv_short_content, Html.fromHtml(noticeListBean.getContent()));
            helper.setText(R.id.tv_message_time, DateTool.getTimeFromLong(DateTool.FMT_DATE, noticeListBean.getCreateTime()));

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AnnouncementDetailActivity.class);
                    intent.putExtra(MSG_ID, noticeListBean.getId());
                    intent.putExtra(MSG_DATA, noticeListBean);
                    startActivity(intent);
                }
            });
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

//    /**
//     * 登录成功后回调
//     */
//    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGINED)})
//    public void onLoginCallBack(String s) {
//        // 登录成功后加载
//        onRefresh();
//    }
}
