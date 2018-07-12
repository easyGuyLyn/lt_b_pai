package com.dawoo.lotterybox.view.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jack
 * @date 18-2-9
 * 分享礼金
 */

public class ShareGiftsActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.next_day)
    TextView nextDay;
    @BindView(R.id.next_money)
    TextView nextMoney;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private Context context;


    private ShareGigtAdapter shareGiftsAdapter;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_sharegifts);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.sharegifs_title), true);
        context = this;
    }

    @Override
    protected void initData() {
        swipeTarget.setLayoutManager(new LinearLayoutManager(context));
        shareGiftsAdapter = new ShareGigtAdapter(R.layout.item_sharegifts_activity);
        shareGiftsAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        swipeTarget.setAdapter(shareGiftsAdapter);
        MSPropties.setSwipeToLoadLayout(context, swipeToLoadLayout);
    }


    @OnClick({R.id.head_view, R.id.textView3, R.id.textView4, R.id.imageView, R.id.textView5, R.id.textView7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.textView3:
                break;
            case R.id.textView4:
                break;
            case R.id.imageView:
                break;
            case R.id.textView5:
                break;
            case R.id.textView7:
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }


    class ShareGigtAdapter extends BaseQuickAdapter {

        public ShareGigtAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    }
}
