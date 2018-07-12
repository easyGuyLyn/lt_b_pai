package com.dawoo.lotterybox.view.fragment.k3_b;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrognito.patternlockview.utils.RandomUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3BActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.LotteryBFragmentManager;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils.setSpanCountByData;
import static com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity.changeType;

/**
 * Created by archar on 18-2-27.
 */

public class K3_WS_QS_Fragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    private SZPAdapter mAdapter;

    private List<PlayTypeBean.PlayBean> mList = new ArrayList<>();
    private LinkedHashMap<String, List<PlayDetailBean>> mListLinkedHashMap = new LinkedHashMap<>();

    private List<PlayDetailBean> selectedBean = new ArrayList<>();

    public K3_WS_QS_Fragment() {
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    protected void loadData() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_just, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        initListener();
        return v;
    }


    private void initViews() {
        mAdapter = new SZPAdapter(R.layout.layout_lottery_b_common_item);
        mRvSzpPlayType.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRvSzpPlayType.setAdapter(mAdapter);
    }


    private void initData() {
        if (!selectedBean.isEmpty()) {
            selectedBean.clear();
            ((K3BActivity) getActivity()).offTouZhuView();
        }
        mList = K3DataUtil.initWSQSData();
        for (PlayTypeBean.PlayBean playBean : mList) {
            mListLinkedHashMap.put(playBean.getPlayTypeName(), K3DataUtil.init_WS_QS_Data(playBean.getPlayTypeName()));
        }
        mAdapter.setNewData(mList);
    }

    private void initListener() {

    }


    @Subscribe(tags = {@Tag(LotteryBFragmentManager.TOP_FRGMENT)})
    public void clearSelectedData(String id) {
        if (!id.equals(this.getId()) && selectedBean.size() > 0) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    for (PlayTypeBean.PlayBean playBean : mList) {
                        mListLinkedHashMap.put(playBean.getPlayTypeName(), Lottery_B_DataUtils.clearSelected(mListLinkedHashMap.get(playBean.getPlayTypeName())));
                    }
                    selectedBean.clear();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                }
            });
        }
    }

    private class SZPAdapter extends BaseQuickAdapter {

        public SZPAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayTypeBean.PlayBean playBean = (PlayTypeBean.PlayBean) item;
            helper.setText(R.id.tv_play_type_name, playBean.getPlayTypeName());
            helper.getView(R.id.tv_play_type_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((K3BActivity) getActivity()).showHowPlayDialog(playBean);
                }
            });

            RecyclerView recyclerView = helper.getView(R.id.rv_detail);
            SZPDetailAdapter szpDetailAdapter = new SZPDetailAdapter(R.layout.click_play_type_item1_layout, helper.getAdapterPosition());
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(szpDetailAdapter);
            szpDetailAdapter.setNewData(mListLinkedHashMap.get(playBean.getPlayTypeName()));
        }
    }


    private class SZPDetailAdapter extends BaseQuickAdapter {

        private int parentPostion;

        public SZPDetailAdapter(int layoutResId, int postion) {
            super(layoutResId);
            parentPostion = postion;
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            if (playDetailBean.getNum().equals("111")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("1"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("1"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("1"));
            } else if (playDetailBean.getNum().equals("222")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("2"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("2"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("2"));
            } else if (playDetailBean.getNum().equals("333")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("3"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("3"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("3"));
            } else if (playDetailBean.getNum().equals("444")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("4"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("4"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("4"));
            } else if (playDetailBean.getNum().equals("555")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("5"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("5"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("5"));
            } else if (playDetailBean.getNum().equals("666")) {
                helper.setBackgroundRes(R.id.tv_playType, K3DataUtil.getFastIcon("6"));
                helper.setBackgroundRes(R.id.tv_playType1, K3DataUtil.getFastIcon("6"));
                helper.setBackgroundRes(R.id.tv_playType2, K3DataUtil.getFastIcon("6"));
            } else if (playDetailBean.getNum().equals("全骰")) {
                helper.setText(R.id.tv_playType, playDetailBean.getNum());
                ((ViewGroup) helper.getView(R.id.tv_playType1).getParent()).setVisibility(View.GONE);
                ((ViewGroup) helper.getView(R.id.tv_playType2).getParent()).setVisibility(View.GONE);
            }

            helper.setText(R.id.tv_odds, playDetailBean.getOdd());
            if (changeType == 1) {
                helper.setText(R.id.tv_lr, playDetailBean.getLr());
            } else if (changeType == 2) {
                helper.setText(R.id.tv_lr, playDetailBean.getYl());
            } else {
                helper.setText(R.id.tv_lr, "");
            }
            helper.itemView.setSelected(playDetailBean.isSelected());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playDetailBean.isSelected()) {
                        playDetailBean.setSelected(false);
                        selectedBean.remove(playDetailBean);
                    } else {
                        playDetailBean.setSelected(true);
                        selectedBean.add(playDetailBean);
                    }
                    if (selectedBean.size() > 0) {
                        int bottomParent = mAdapter.getViewByPosition(mRvSzpPlayType, parentPostion, R.id.rl_root_common).getTop();
                        int bottomChild = helper.itemView.getBottom();
                        int bottomContentHeight = ((K3BActivity) getActivity()).getBottomContentHeight();
                        int bottomTouzhu = DensityUtil.dp2px(mContext, 150);
                        if (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)) < bottomTouzhu) {
                            int move = bottomTouzhu - (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)));
                            mRvSzpPlayType.scrollBy(0, move);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    ((K3BActivity) getActivity()).onTouZhuView(playDetailBean);
                }
            });
            //动态设置 列数
            setSpanCountByData(mData.size(), mContext, helper.getAdapterPosition(), helper.itemView);
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_LR)})
    public void changeLr(String id) {
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_SELECTED)})
    public void randomSelected(String id) {
        if (!isHidden()) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    if (!selectedBean.isEmpty()) {
                        for (PlayTypeBean.PlayBean playBean : mList) {
                            mListLinkedHashMap.put(playBean.getPlayTypeName(), Lottery_B_DataUtils.clearSelected(mListLinkedHashMap.get(playBean.getPlayTypeName())));
                        }
                    }
                    int randomType = RandomUtils.randInt(mList.size() - 1);
                    int randomChild = RandomUtils.randInt(mListLinkedHashMap.get(mList.get(randomType).getPlayTypeName()).size() - 1);
                    mListLinkedHashMap.get(mList.get(randomType).getPlayTypeName()).get(randomChild).setSelected(true);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!selectedBean.isEmpty()) {
                                selectedBean.clear();
                                //           ((K3BActivity) getActivity()).offTouZhuView();
                            }
                            selectedBean.add(mListLinkedHashMap.get(mList.get(randomType).getPlayTypeName()).get(randomChild));
                            mAdapter.notifyDataSetChanged();
                            ((K3BActivity) getActivity()).onTouZhuView(mListLinkedHashMap.get(mList.get(randomType).getPlayTypeName()).get(randomChild));
                        }
                    });
                }
            });
        }
    }

}
