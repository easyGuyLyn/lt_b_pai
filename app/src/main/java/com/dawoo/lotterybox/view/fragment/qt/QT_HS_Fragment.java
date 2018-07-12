package com.dawoo.lotterybox.view.fragment.qt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTBActivity;
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

/**
 * qt合数
 * archar
 */

public class QT_HS_Fragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;


    // 选中的bean
    private List<PlayDetailBean> selectedBean = new ArrayList<>();
    private String[] mTitleList;
    private LinkedHashMap<String, List<List<PlayDetailBean>>> mListLinkedHashMap = new LinkedHashMap<>();
    private MainAdapter mMainAdapter;

    private List<PlayTypeBean.PlayBean> yzzhList;
    private List<PlayTypeBean.PlayBean> rzzhList;
    private List<PlayTypeBean.PlayBean> szzhList;
    private List<PlayTypeBean.PlayBean> bsgList;
    private int mCurrentPostion = 0;

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ssc_b_yzdw, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        return v;
    }


    private void initViews() {
        mTitleList = getResources().getStringArray(R.array.QTB_HS);
        initTabView(mTitleList);

        mMainAdapter = new MainAdapter(R.layout.layout_lottery_b_common_item);
        mRvSzpPlayType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvSzpPlayType.setAdapter(mMainAdapter);
    }


    private void initData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                yzzhList = QTBDataUtil.initBSHSData();
                rzzhList = QTBDataUtil.initBGHSData();
                szzhList = QTBDataUtil.initSGHSData();
                bsgList = QTBDataUtil.initBSGHSData();

                List<List<PlayDetailBean>> listList0 = new ArrayList<>();
                for (int i = 0; i < yzzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = yzzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_BSHS_Data(playBean.getPlayTypeName());
                    listList0.add(list);
                }
                mListLinkedHashMap.put(mTitleList[0], listList0);

                List<List<PlayDetailBean>> listList1 = new ArrayList<>();
                for (int i = 0; i < rzzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = rzzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_BGHS_Data(playBean.getPlayTypeName());
                    listList1.add(list);
                }
                mListLinkedHashMap.put(mTitleList[1], listList1);

                List<List<PlayDetailBean>> listList2 = new ArrayList<>();
                for (int i = 0; i < szzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = szzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_SGHS_Data(playBean.getPlayTypeName());
                    listList2.add(list);
                }
                mListLinkedHashMap.put(mTitleList[2], listList2);

                List<List<PlayDetailBean>> listList3 = new ArrayList<>();
                for (int i = 0; i < bsgList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = bsgList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_BSGHS_Data(playBean.getPlayTypeName());
                    listList3.add(list);
                }
                mListLinkedHashMap.put(mTitleList[3], listList3);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMainAdapter == null) return;
                        mMainAdapter.setNewData(mListLinkedHashMap.get(mTitleList[mCurrentPostion]));
                    }
                });
            }
        });
    }


    @Override
    protected void loadData() {

    }

    /**
     * 初始化tab
     */
    void initTabView(String[] titleList) {
        // 初始化tab
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentPostion = tab.getPosition();
                ((QTBActivity) getActivity()).offTouZhuView();
                changeContentByTab(titleList[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < titleList.length; i++) {
            if (i == 0) {
                mTabLayout.addTab(mTabLayout.newTab().setText(titleList[i]), true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(titleList[i]), false);
            }
        }
    }

    private void changeContentByTab(String playName) {
        SingleToast.showMsg(playName);
        initData();
    }


    @Subscribe(tags = {@Tag(LotteryBFragmentManager.TOP_FRGMENT)})
    public void clearSelectedData(String id) {
        if (!id.equals(this.getId()) && selectedBean.size() > 0) {
            List<List<PlayDetailBean>> playDetailBeanList = new ArrayList<>();
            for (List<PlayDetailBean> mItemList : mListLinkedHashMap.get(mTitleList[mCurrentPostion])) {
                playDetailBeanList.add(Lottery_B_DataUtils.clearSelected(mItemList));
            }
            mListLinkedHashMap.put(mTitleList[mCurrentPostion], playDetailBeanList);
            selectedBean.clear();
            mMainAdapter.notifyDataSetChanged();
            ((QTBActivity) getActivity()).offTouZhuView();
        }
    }


    class MainAdapter extends BaseQuickAdapter {

        public MainAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            List<PlayDetailBean> list = (List<PlayDetailBean>) item;
            helper.setText(R.id.tv_play_type_name, list.get(helper.getAdapterPosition()).getKind());
            helper.getView(R.id.tv_play_type_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCurrentPostion == 0) {
                        ((QTBActivity) getActivity()).showHowPlayDialog(yzzhList.get(helper.getAdapterPosition()));
                    } else if (mCurrentPostion == 1) {
                        ((QTBActivity) getActivity()).showHowPlayDialog(rzzhList.get(helper.getAdapterPosition()));
                    } else if (mCurrentPostion == 2) {
                        ((QTBActivity) getActivity()).showHowPlayDialog(szzhList.get(helper.getAdapterPosition()));
                    } else if (mCurrentPostion == 3) {
                        ((QTBActivity) getActivity()).showHowPlayDialog(bsgList.get(helper.getAdapterPosition()));
                    }
                }
            });
            RecyclerView recyclerView = helper.getView(R.id.rv_detail);
            DetailAdapter detailAdapter = new DetailAdapter(R.layout.click_play_type_item_layout, helper.getAdapterPosition());
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(detailAdapter);
            detailAdapter.setNewData(list);
        }
    }


    /**
     * 适配器
     */
    class DetailAdapter extends BaseQuickAdapter {

        private int parentPostion;

        public DetailAdapter(int layoutResId, int postion) {
            super(layoutResId);
            parentPostion = postion;
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            helper.setText(R.id.tv_playType, playDetailBean.getNum());
            helper.setText(R.id.tv_odds, playDetailBean.getOdd());
            helper.setText(R.id.tv_lr, playDetailBean.getLr());
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
                        int bottomParent = mMainAdapter.getViewByPosition(mRvSzpPlayType, parentPostion, R.id.rl_root_common).getTop();
                        int bottomChild = helper.itemView.getBottom();
                        int bottomContentHeight = ((QTBActivity) getActivity()).getBottomContentHeight();
                        int bottomTouzhu = DensityUtil.dp2px(mContext, 150);
                        if (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)) < bottomTouzhu) {
                            int move = mTabLayout.getHeight() + bottomTouzhu - (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)));
                            mRvSzpPlayType.scrollBy(0, move);
                        }
                    }
                    ((QTBActivity) getActivity()).onTouZhuView(playDetailBean);
                    mMainAdapter.notifyDataSetChanged();
                }
            });
            //动态设置 列数
            setSpanCountByData(mData.size(), mContext, helper.getAdapterPosition(), helper.itemView);
        }


    }


    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_LR)})
    public void changeLr(String id) {
        initData();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_SELECTED)})
    public void randomSelected(String id) {
        if (!isHidden()) {
            if (selectedBean.size() > 0) {
                List<List<PlayDetailBean>> playDetailBeanList = new ArrayList<>();
                for (List<PlayDetailBean> mItemList : mListLinkedHashMap.get(mTitleList[mCurrentPostion])) {
                    playDetailBeanList.add(Lottery_B_DataUtils.clearSelected(mItemList));
                }
                ((QTBActivity) getActivity()).clearSelected();
                mListLinkedHashMap.put(mTitleList[mCurrentPostion], playDetailBeanList);
                selectedBean.clear();
            }
            int randomparent = RandomUtils.randInt(mListLinkedHashMap.get(mTitleList[mCurrentPostion]).size());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < mListLinkedHashMap.get(mTitleList[mCurrentPostion]).size(); i++) {
                        if (randomparent == i) {
                            int randomItem = RandomUtils.randInt(mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).size());
                            mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).get(randomItem).setSelected(true);
                            PlayDetailBean playDetailBean = mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).get(randomItem);
                            selectedBean.add(playDetailBean);
                            ((QTBActivity) getActivity()).onTouZhuView(playDetailBean);
                        }
                    }
                    mMainAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}
