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
import android.widget.TextView;

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
import static com.dawoo.lotterybox.view.fragment.qt.QTBDataUtil.getOddMap;
import static com.dawoo.lotterybox.view.fragment.qt.QTBDataUtil.getRec;

/**
 * qt组合
 * Created by benson on 18-2-27.
 */

public class QT_ZH_Fragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tv_odd_explain)
    TextView mTvOddExplain;


    // 选中的bean
    private List<PlayDetailBean> selectedBean = new ArrayList<>();
    private String[] mTitleList;
    private List<PlayTypeBean.PlayBean> mList;
    private LinkedHashMap<String, List<List<PlayDetailBean>>> mListLinkedHashMap = new LinkedHashMap<>();
    private MainAdapter mMainAdapter;
    boolean mIsItemStyle;

    private List<PlayTypeBean.PlayBean> yzzhList;
    private List<PlayTypeBean.PlayBean> rzzhList;
    private List<PlayTypeBean.PlayBean> szzhList;
    private int mCurrentPostion = 0;
    private String mRzzhOddExplain;//二字组合赔率说明
    private String mSzzhOddExplain;//三字组合赔率说明

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ssc_b_qt_rzdw, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        return v;
    }


    private void initViews() {
        mTitleList = getResources().getStringArray(R.array.QTB_ZuiHe);
        initTabView(mTitleList);

        mMainAdapter = new MainAdapter(R.layout.layout_lottery_b_common_no_notice_item);
        mRvSzpPlayType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvSzpPlayType.setAdapter(mMainAdapter);
        initRZSZOddExplain();
    }

    private void initRZSZOddExplain() {
        String oddRzTwoSame = getOddMap().get(getRec().getString(R.string.pl3_two_same_二同) + ":" + "二同").getOdd() + "";
        String oddRzTwoDiff = getOddMap().get(getRec().getString(R.string.pl3_two_different_二不同) + ":" + "二不同").getOdd() + "";
        mRzzhOddExplain = "(二字相同@" + oddRzTwoSame + " 二字不同@" + oddRzTwoDiff + ")";

        String oddSzThreeSame = getOddMap().get(getRec().getString(R.string.pl3_three_same_三同) + ":" + "三同").getOdd() + "";
        String oddSzZuSan = getOddMap().get(getRec().getString(R.string.pl3_three_group3_组三) + ":" + "组三").getOdd() + "";
        String oddSzZuLiu = getOddMap().get(getRec().getString(R.string.pl3_three_group6_组六) + ":" + "组六").getOdd() + "";

        mSzzhOddExplain = "(三同@" + oddSzThreeSame + " 组三@" + oddSzZuSan + " 组六@" + oddSzZuLiu + ")";
        addOddExplainView();
    }

    private void addOddExplainView() {
        if (mCurrentPostion == 0) {
            mTvOddExplain.setVisibility(View.GONE);
        } else if (mCurrentPostion == 1) {
            mTvOddExplain.setVisibility(View.VISIBLE);
            mTvOddExplain.setText(mRzzhOddExplain);
        } else if (mCurrentPostion == 2) {
            mTvOddExplain.setVisibility(View.VISIBLE);
            mTvOddExplain.setText(mSzzhOddExplain);
        }
    }


    private void initData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                yzzhList = QTBDataUtil.initYZZHData();
                rzzhList = QTBDataUtil.initRZZHData();
                szzhList = QTBDataUtil.initSZZHData();

                List<List<PlayDetailBean>> listList0 = new ArrayList<>();
                for (int i = 0; i < yzzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = yzzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_YZZH_0_9_Data(playBean.getPlayTypeName(), "");
                    listList0.add(list);
                }
                mListLinkedHashMap.put(mTitleList[0], listList0);

                List<List<PlayDetailBean>> listList1 = new ArrayList<>();
                for (int i = 0; i < rzzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = rzzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_RZZH_0_9_Data(playBean.getPlayTypeName(), "");
                    listList1.add(list);
                }
                mListLinkedHashMap.put(mTitleList[1], listList1);

                List<List<PlayDetailBean>> listList2 = new ArrayList<>();
                for (int i = 0; i < szzhList.size(); i++) {
                    PlayTypeBean.PlayBean playBean = szzhList.get(i);
                    List<PlayDetailBean> list = QTBDataUtil.init_SZZH_0_9_Data(playBean.getPlayTypeName(), "");
                    listList2.add(list);
                }
                mListLinkedHashMap.put(mTitleList[2], listList2);
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
                if (0 == tab.getPosition()) {
                    mIsItemStyle = true;
                } else {
                    mIsItemStyle = false;
                }
                mCurrentPostion = tab.getPosition();
                ((QTBActivity) getActivity()).offTouZhuView();
                changeContentByTab(titleList[tab.getPosition()]);
                addOddExplainView();
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
                        ((QTBActivity) getActivity()).showHowPlayDialog(yzzhList.get(0));
                    } else if (mCurrentPostion == 1) {
                        ((QTBActivity) getActivity()).showHowPlayDialog(rzzhList.get(helper.getAdapterPosition()));
                    } else {
                        ((QTBActivity) getActivity()).showHowPlayDialog(szzhList.get(helper.getAdapterPosition()));
                    }
                }
            });
            RecyclerView recyclerView = helper.getView(R.id.rv_detail);
            int rId = 0;
            if (mIsItemStyle) {
                rId = R.layout.click_play_type_item_layout;
            } else {
                rId = R.layout.click_ball_play_type_item_layout;
            }
            DetailAdapter detailAdapter = new DetailAdapter(rId, helper.getAdapterPosition());
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(detailAdapter);
            detailAdapter.setNewData(list);
        }
    }


    /**
     * 一字定位
     * 二字定位
     * 三字定位
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
            helper.setText(R.id.tv_lr, playDetailBean.getLr());
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
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < mListLinkedHashMap.get(mTitleList[mCurrentPostion]).size(); i++) {
                        int randomItem = RandomUtils.randInt(mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).size());
                        mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).get(randomItem).setSelected(true);
                        PlayDetailBean playDetailBean = mListLinkedHashMap.get(mTitleList[mCurrentPostion]).get(i).get(randomItem);
                        selectedBean.add(playDetailBean);
                        ((QTBActivity) getActivity()).onTouZhuView(playDetailBean);
                    }
                    mMainAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}
