package com.dawoo.lotterybox.view.fragment.keno;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.dawoo.lotterybox.view.activity.lottery.keno.XY28Activity;
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
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils.setSpanCountByData;
import static com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity.changeType;

/**
 * Created by archar on 18-2-27.
 */

public class XY28_HZTM_Fragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    @BindView(R.id.tv_play_type_name)
    TextView mTvPlayTypeName;
    private SZPDetailAdapter mAdapter;

    private List<PlayTypeBean.PlayBean> mList = new ArrayList<>();
    private LinkedHashMap<String, List<PlayDetailBean>> mListLinkedHashMap = new LinkedHashMap<>();

    private List<PlayDetailBean> selectedBean = new ArrayList<>();

    public XY28_HZTM_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_list_just_single, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        initListener();
        return v;
    }


    private void initViews() {
        mAdapter = new SZPDetailAdapter(R.layout.click_ball_play_type_item_layout);
        mRvSzpPlayType.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvSzpPlayType.setAdapter(mAdapter);
    }


    private void initData() {
        mList = XYBDataUtils.initXy28HZTMData();
        for (PlayTypeBean.PlayBean playBean : mList) {
            mListLinkedHashMap.put(playBean.getPlayTypeName(), XYBDataUtils.init_Xy28_HZTM_Data(playBean.getPlayTypeName()));
        }
        mTvPlayTypeName.setText(mList.get(0).getPlayTypeName());
        mAdapter.setNewData(mListLinkedHashMap.get(mList.get(0).getPlayTypeName()));
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

    @OnClick(R.id.tv_play_type_name)
    public void onViewClicked() {
        ((XY28Activity) getActivity()).showHowPlayDialog(mList.get(0));
    }

    private class SZPDetailAdapter extends BaseQuickAdapter {

        public SZPDetailAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            ((TextView) helper.getView(R.id.tv_playType)).setTextColor(getResources().getColor(R.color.white));
            helper.setText(R.id.tv_playType, playDetailBean.getNum());
            if (changeType == 1) {
                helper.setText(R.id.tv_lr, playDetailBean.getLr());
            } else if (changeType == 2) {
                helper.setText(R.id.tv_lr, playDetailBean.getYl());
            } else {
                helper.setText(R.id.tv_lr, "");
            }
            helper.setBackgroundRes(R.id.tv_playType, XYBDataUtils.getBackShapeColor(playDetailBean.getNum()));
            helper.getView(R.id.tv_odds).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_odds, playDetailBean.getOdd());
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
                        int bottomParent = mRvSzpPlayType.getTop();
                        int bottomChild = helper.itemView.getBottom();
                        int bottomContentHeight = ((XY28Activity) getActivity()).getBottomContentHeight();
                        int bottomTouzhu = DensityUtil.dp2px(mContext, 150);

                        if (bottomContentHeight - (bottomParent + bottomChild) < bottomTouzhu) {
                            int move = bottomTouzhu - (bottomContentHeight - (bottomParent + bottomChild));
                            mRvSzpPlayType.scrollBy(0, move);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    ((XY28Activity) getActivity()).onTouZhuView(playDetailBean);
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
            if (selectedBean.size() > 0) {
                for (PlayTypeBean.PlayBean playBean : mList) {
                    mListLinkedHashMap.put(playBean.getPlayTypeName(), Lottery_B_DataUtils.clearSelected(mListLinkedHashMap.get(playBean.getPlayTypeName())));
                }
                selectedBean.clear();
            }
            int randomparent = RandomUtils.randInt(mList.size());
            int randomChild = RandomUtils.randInt(mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).size());
            mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(randomChild).setSelected(true);
            selectedBean.add(mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(randomChild));
            ((XY28Activity) getActivity()).onTouZhuView(mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(randomChild));
            mAdapter.notifyDataSetChanged();
        }
    }

}
