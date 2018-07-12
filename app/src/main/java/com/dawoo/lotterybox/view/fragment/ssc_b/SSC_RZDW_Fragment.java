package com.dawoo.lotterybox.view.fragment.ssc_b;

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
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCBActivity;
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

import static com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity.changeType;

/**
 * Created by archar on 18-2-27.
 */

public class SSC_RZDW_Fragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    private SZPAdapter mAdapter;

    private List<PlayTypeBean.PlayBean> mList = new ArrayList<>();
    private LinkedHashMap<String, LinkedHashMap<String, List<PlayDetailBean>>> mStringLinkedHashMapLinkedHashMap = new LinkedHashMap<>();
    private List<PlayDetailBean> selectedBean = new ArrayList<>();
    String playName = BoxApplication.getContext().getString(R.string.wq);

    public SSC_RZDW_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_ssc_b_yzdw, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        initListener();
        return v;
    }


    private void initViews() {
        mList = SSCBDataUtil.initRZDWData();
        initTab();
        mAdapter = new SZPAdapter(R.layout.layout_lottery_b_common_no_notice_item);
        mRvSzpPlayType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvSzpPlayType.setAdapter(mAdapter);
    }


    private void initData() {
        selectedBean.clear();
        for (PlayTypeBean.PlayBean playBean : mList) {
            String palyTypeName = playBean.getPlayTypeName();
            List<String> childTypes = new ArrayList<>();
            int length = palyTypeName.length();
            for (int i = 0; i < length; i++) {
                childTypes.add(palyTypeName.charAt(i) + "");
            }

            LinkedHashMap<String, List<PlayDetailBean>> listLinkedHashMap = new LinkedHashMap<>();
            for (String childType : childTypes) {
                listLinkedHashMap.put(childType, SSCBDataUtil.init_RZDW_0_9_Data(
                        playBean.getPlayTypeName(), childType));
            }
            mStringLinkedHashMapLinkedHashMap.put(playBean.getPlayTypeName(), listLinkedHashMap);
        }
        List<String> titleList = new ArrayList<>();
        //下面有异步
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (String scheme : mStringLinkedHashMapLinkedHashMap.get(playName).keySet()) {
                    titleList.add(scheme);
                }
                mAdapter.setNewData(titleList);
            }
        });

    }


    /**
     * 初始化 tab ui
     */
    private void initTab() {
        for (int i = 0; i < mList.size(); i++) {
            if (i == 0) {
                mTabLayout.addTab(mTabLayout.newTab().setText(mList.get(i).getPlayTypeName()), true);
            } else {
                mTabLayout.addTab(mTabLayout.newTab().setText(mList.get(i).getPlayTypeName()), false);
            }
        }
    }


    private void initListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mList.get(tab.getPosition()).getPlayTypeName().equalsIgnoreCase(playName)) {
                    return;
                }
                ((SSCBActivity) getActivity()).offTouZhuView();
                playName = mList.get(tab.getPosition()).getPlayTypeName();
                chooseType();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void chooseType() {
        SingleToast.showMsg(playName);
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }


    @Subscribe(tags = {@Tag(LotteryBFragmentManager.TOP_FRGMENT)})
    public void clearSelectedData(String id) {
        if (!id.equals(this.getId()) && selectedBean.size() > 0) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    initData();
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
            String title = (String) item;
            TextView tv_play_type_name = helper.getView(R.id.tv_play_type_name);
            tv_play_type_name.setText(title);

            RecyclerView recyclerView = helper.getView(R.id.rv_detail);
            DetailAdapter detailAdapter = new DetailAdapter(R.layout.click_ball_play_type_item_layout, helper.getAdapterPosition());
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(detailAdapter);
            detailAdapter.setNewData(mStringLinkedHashMapLinkedHashMap.get(playName).get(title));

        }
    }


    private class DetailAdapter extends BaseQuickAdapter {

        private int parentPostion;

        public DetailAdapter(int layoutResId, int postion) {
            super(layoutResId);
            parentPostion = postion;
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            helper.setText(R.id.tv_playType, playDetailBean.getNum());
            helper.itemView.setSelected(playDetailBean.isSelected());
            helper.setText(R.id.tv_odds, playDetailBean.getOdd());
            if (changeType == 1) {
                helper.setText(R.id.tv_lr, playDetailBean.getLr());
            } else if (changeType == 2) {
                helper.setText(R.id.tv_lr, playDetailBean.getYl());
            } else {
                helper.setText(R.id.tv_lr, "");
            }
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
                        int bottomContentHeight = ((SSCBActivity) getActivity()).getBottomContentHeight();
                        int bottomTouzhu = DensityUtil.dp2px(mContext, 150);
                        if (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)) < bottomTouzhu) {
                            int move = mTabLayout.getHeight() + bottomTouzhu - (bottomContentHeight - (bottomParent + bottomChild + DensityUtil.dp2px(mContext, 40)));
                            mRvSzpPlayType.scrollBy(0, move);
                        }
                    }
                    ((SSCBActivity) getActivity()).onTouZhuView(playDetailBean);
                    mAdapter.notifyDataSetChanged();
                }
            });
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
                    if (selectedBean.size() > 0) {
                        for (PlayTypeBean.PlayBean playBean : mList) {
                            String palyTypeName = playBean.getPlayTypeName();
                            List<String> childTypes = new ArrayList<>();
                            int length = palyTypeName.length();
                            for (int i = 0; i < length; i++) {
                                childTypes.add(palyTypeName.charAt(i) + "");
                            }

                            LinkedHashMap<String, List<PlayDetailBean>> listLinkedHashMap = new LinkedHashMap<>();
                            for (String childType : childTypes) {
                                listLinkedHashMap.put(childType, SSCBDataUtil.init_RZDW_0_9_Data(
                                        playBean.getPlayTypeName(), childType));
                            }
                            mStringLinkedHashMapLinkedHashMap.put(playBean.getPlayTypeName(), listLinkedHashMap);
                        }
                        selectedBean.clear();
                    }
                    String type = playName.charAt(0) + "";
                    int randomChild0 = RandomUtils.randInt(mStringLinkedHashMapLinkedHashMap.get(playName).get(type).size() - 1);
                    mStringLinkedHashMapLinkedHashMap.get(playName).get(type).get(randomChild0).setSelected(true);
                    PlayDetailBean bean = mStringLinkedHashMapLinkedHashMap.get(playName).get(type).get(randomChild0);
                    int randomChild1 = RandomUtils.randInt(mStringLinkedHashMapLinkedHashMap.get(playName).get(playName.charAt(1) + "").size() - 1);
                    mStringLinkedHashMapLinkedHashMap.get(playName).get(playName.charAt(1) + "").get(randomChild1).setSelected(true);
                    PlayDetailBean bean1 = mStringLinkedHashMapLinkedHashMap.get(playName).get(playName.charAt(1) + "").get(randomChild1);
                    selectedBean.add(bean);
                    selectedBean.add(bean1);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        //    ((SSCBActivity) getActivity()).offTouZhuView();
                            mAdapter.notifyDataSetChanged();
                            ((SSCBActivity) getActivity()).onTouZhuView(bean);
                            ((SSCBActivity) getActivity()).onTouZhuView(bean1);
                        }
                    });
                }
            });
        }
    }
}
