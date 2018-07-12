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
import com.dawoo.lotterybox.view.activity.lottery.keno.BJKL8Activity;
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

public class BJKL8_X1_Fragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvDetail;
    @BindView(R.id.tv_play_type_name)
    TextView mTvPlayTypeName;


    private SZPDetailAdapter szpDetailAdapter;
    private List<PlayTypeBean.PlayBean> mList = new ArrayList<>();
    private LinkedHashMap<String, List<PlayDetailBean>> mListLinkedHashMap = new LinkedHashMap<>();

    private List<PlayDetailBean> selectedBean = new ArrayList<>();

    public BJKL8_X1_Fragment() {
        // Required empty public constructor
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
        szpDetailAdapter = new SZPDetailAdapter(R.layout.click_ball_play_type_item_layout);
        mRvDetail.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvDetail.setAdapter(szpDetailAdapter);
    }


    private void initData() {
        mList = XYBDataUtils.initBJKL8ChooseData(1);
        for (PlayTypeBean.PlayBean playBean : mList) {
            mListLinkedHashMap.put(playBean.getPlayTypeName(), XYBDataUtils.init_BJKl8_Choose_Data(playBean.getPlayTypeName()));
        }
        mTvPlayTypeName.setText(mList.get(0).getPlayTypeName() + "(中1@" + mList.get(0).getScheme() + "）");
        szpDetailAdapter.setNewData(mListLinkedHashMap.get(mList.get(0).getPlayTypeName()));
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
                            szpDetailAdapter.notifyDataSetChanged();
                        }
                    });

                }
            });
        }
    }

    @OnClick(R.id.tv_play_type_name)
    public void onViewClicked() {
        ((BJKL8Activity) getActivity()).showHowPlayDialog(mList.get(0));
    }


    private class SZPDetailAdapter extends BaseQuickAdapter {

        public SZPDetailAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            helper.setText(R.id.tv_playType, playDetailBean.getNum());
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
                        int bottomParent = mRvDetail.getTop();
                        int bottomChild = helper.itemView.getBottom();
                        int bottomContentHeight = ((BJKL8Activity) getActivity()).getBottomContentHeight();
                        int bottomTouzhu = DensityUtil.dp2px(mContext, 150);

                        if (bottomContentHeight - (bottomParent + bottomChild) < bottomTouzhu) {
                            int move = bottomTouzhu - (bottomContentHeight - (bottomParent + bottomChild));
                            mRvDetail.scrollBy(0, move);
                        }
                    }
                    szpDetailAdapter.notifyDataSetChanged();
                    ((BJKL8Activity) getActivity()).onTouZhuView(playDetailBean);
                }
            });
            //动态设置 列数
            setSpanCountByData(mData.size(), mContext, helper.getAdapterPosition(), helper.itemView);
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_LR)})
    public void changeLr(String id) {
        szpDetailAdapter.notifyDataSetChanged();
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
            int[] randomChild = XYBDataUtils.getRandomList(1, 0, mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).size());
            for (int i : randomChild) {
                mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(i).setSelected(true);
                selectedBean.add(mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(i));
                ((BJKL8Activity) getActivity()).onTouZhuView(mListLinkedHashMap.get(mList.get(randomparent).getPlayTypeName()).get(i));
            }
            szpDetailAdapter.notifyDataSetChanged();
        }
    }

}
