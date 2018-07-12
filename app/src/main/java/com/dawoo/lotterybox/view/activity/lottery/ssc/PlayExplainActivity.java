package com.dawoo.lotterybox.view.activity.lottery.ssc;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.CommonViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.JsonToPlayExplainBean;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by archar on 18-2-12.
 */

public class PlayExplainActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.rv_play_explain)
    RecyclerView mRvPlayExplain;

    public static final String ASSETS_FILE_NAME = "file_name";
    private PlalExplainAdapter mPlalExplainAdapter;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_playexplain);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.play_explain_title), true);
        initMainTypeRv();
    }

    private void initMainTypeRv() {
        mPlalExplainAdapter = new PlalExplainAdapter(R.layout.activity_play_explain_item);
        mRvPlayExplain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvPlayExplain.setAdapter(mPlalExplainAdapter);
    }


    @Override
    protected void initData() {
        List<PlayTypeBean> list = new ArrayList<>();
        Map<String, List<PlayTypeBean.PlayBean>> map = JsonToPlayExplainBean.getPlayBeansFromJson(this,getIntent().getStringExtra(ASSETS_FILE_NAME));
        for (Map.Entry<String, List<PlayTypeBean.PlayBean>> entry : map.entrySet()) {
            PlayTypeBean playBean = new PlayTypeBean();
            playBean.setParentTitle(entry.getKey());
            playBean.setPlayBeans(entry.getValue());
            list.add(playBean);
        }
        mPlalExplainAdapter.setNewData(list);
    }


    private class PlalExplainAdapter extends BaseQuickAdapter {


        public PlalExplainAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayTypeBean playBean = (PlayTypeBean) item;
            helper.setText(R.id.tv_mainType, playBean.getParentTitle());

            RecyclerView recyclerView = helper.getView(R.id.rv_play_explain_item);
            recyclerView.requestDisallowInterceptTouchEvent(false);
            PlayExplainItemListAdapter playExplainItemListAdapter = new PlayExplainItemListAdapter(playBean.getPlayBeans());
            recyclerView.setLayoutManager(new LinearLayoutManager(PlayExplainActivity.this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(playExplainItemListAdapter);


            helper.getView(R.id.tv_mainType).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerView.getVisibility() == View.GONE) {
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private class PlayExplainItemListAdapter extends RecyclerView.Adapter {
        private List<PlayTypeBean.PlayBean> mPlayTypeBeans;

        public PlayExplainItemListAdapter(List<PlayTypeBean.PlayBean> playTypeBeans) {
            mPlayTypeBeans = playTypeBeans;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommonViewHolder(View.inflate(PlayExplainActivity.this, R.layout.layout_rv_item_explain, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            viewHolder.getTv(R.id.tv_paly_explain).setText(mPlayTypeBeans.get(position).getPlayTypeName() + "\n" +
                    mPlayTypeBeans.get(position).getPlayTypeExplain());
        }

        @Override
        public int getItemCount() {
            return mPlayTypeBeans == null ? 0 : mPlayTypeBeans.size();
        }
    }

}
