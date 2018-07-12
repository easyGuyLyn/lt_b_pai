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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.CommonViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.util.ThreadUtils;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;
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
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.lottery.BaseLotteryBActivity.changeType;

/**
 * Created by archar on 18-2-27.
 */

public class SSC_ZX6_Fragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_szp_play_type)
    RecyclerView mRvSzpPlayType;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tv_play_type_name)
    TextView mTvPlayTypeName;

    private MainAdapter mMainAdapter;

    private List<PlayTypeBean.PlayBean> mList = new ArrayList<>();
    private LinkedHashMap<String, LinkedHashMap<String, List<PlayDetailBean>>> mStringLinkedHashMapLinkedHashMap = new LinkedHashMap<>();
    private List<PlayDetailBean> selectedBean = new ArrayList<>();
    private String playName = BoxApplication.getContext().getString(R.string.q3zx6);
    private int mCurrentPostion = 0;

    public SSC_ZX6_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_ssc_b_yzdw_title, container, false);
        unbinder = ButterKnife.bind(this, v);
        RxBus.get().register(this);
        initViews();
        initData();
        initListener();
        return v;
    }


    private void initViews() {
        mList = SSCBDataUtil.initZX6Data();
        initTab();
    }


    private void initData() {
        selectedBean.clear();
        ((SSCBActivity) getActivity()).offTouZhuView();
        for (PlayTypeBean.PlayBean playBean : mList) {
            LinkedHashMap<String, List<PlayDetailBean>> listLinkedHashMap = new LinkedHashMap<>();
            listLinkedHashMap.put("1", SSCBDataUtil.init_ZX6_0_9_Data(
                    playBean.getPlayTypeName(), "1"));
            mStringLinkedHashMapLinkedHashMap.put(playBean.getPlayTypeName(), listLinkedHashMap);
        }

        //下面有异步
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMainAdapter = new MainAdapter(mStringLinkedHashMapLinkedHashMap.get(playName));
                mRvSzpPlayType.setLayoutManager(new LinearLayoutManager(mContext));
                mRvSzpPlayType.setAdapter(mMainAdapter);
                mTvPlayTypeName.setText(playName);
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
                if (playName.equalsIgnoreCase(mList.get(tab.getPosition()).getPlayTypeName())) {
                    return;
                }
                mCurrentPostion = tab.getPosition();
                playName = mList.get(tab.getPosition()).getPlayTypeName();
                ((SSCBActivity) getActivity()).offTouZhuView();
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
        if (selectedBean.size() > 0) {
            selectedBean.clear();
            ((SSCBActivity) getActivity()).offTouZhuView();
            for (PlayTypeBean.PlayBean playBean : mList) {
                LinkedHashMap<String, List<PlayDetailBean>> listLinkedHashMap = new LinkedHashMap<>();
                listLinkedHashMap.put("1", SSCBDataUtil.init_ZX6_0_9_Data(
                        playBean.getPlayTypeName(), "1"));
                mStringLinkedHashMapLinkedHashMap.put(playBean.getPlayTypeName(), listLinkedHashMap);
            }
        }
        mMainAdapter.setData(mStringLinkedHashMapLinkedHashMap.get(playName));
        mMainAdapter.notifyDataSetChanged();
        mTvPlayTypeName.setText(playName);
    }


    @Subscribe(tags = {@Tag(LotteryBFragmentManager.TOP_FRGMENT)})
    public void clearSelectedData(String id) {
        if (!id.equals(this.getId()) && selectedBean.size() > 0) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    selectedBean.clear();
                    for (String key : mStringLinkedHashMapLinkedHashMap.keySet()) {
                        for (String secKEY : mStringLinkedHashMapLinkedHashMap.get(key).keySet()) {
                            Lottery_B_DataUtils.clearSelected(mStringLinkedHashMapLinkedHashMap.get(key).get(secKEY));
                        }
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMainAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    @OnClick(R.id.tv_play_type_name)
    public void onViewClicked() {
        ((SSCBActivity) getActivity()).showHowPlayDialog(mList.get(mCurrentPostion));
    }

    private class MainAdapter extends RecyclerView.Adapter {
        private LinkedHashMap<String, List<PlayDetailBean>> mData = new LinkedHashMap<>();
        private List<String> titleList = new ArrayList<>();

        public MainAdapter(LinkedHashMap<String, List<PlayDetailBean>> data) {
            mData = data;
            for (String scheme : mData.keySet()) {
                titleList.add(scheme);
            }
        }

        public LinkedHashMap<String, List<PlayDetailBean>> getData() {
            return mData;
        }

        public void setData(LinkedHashMap<String, List<PlayDetailBean>> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommonViewHolder(View.inflate(mContext, R.layout.layout_ssc_b_main_item, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CommonViewHolder viewHolder = (CommonViewHolder) holder;
            TextView tv_play_type_name = viewHolder.getTv(R.id.tv_play_type_name);
            tv_play_type_name.setVisibility(View.GONE);

            RecyclerView recyclerView = viewHolder.getView(R.id.rv_detail);
            DetailAdapter detailAdapter = new DetailAdapter(R.layout.click_ball_play_type_item_layout);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(detailAdapter);
            detailAdapter.setNewData(mData.get(titleList.get(position)));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
    }


    private class DetailAdapter extends BaseQuickAdapter {

        public DetailAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            PlayDetailBean playDetailBean = (PlayDetailBean) item;
            helper.setText(R.id.tv_playType, playDetailBean.getNum());
            helper.itemView.setSelected(playDetailBean.isSelected());
            if (changeType == 1) {
                helper.setText(R.id.tv_lr, playDetailBean.getLr());
            } else if (changeType == 2) {
                helper.setText(R.id.tv_lr, playDetailBean.getYl());
            } else {
                helper.setText(R.id.tv_lr, "");
            }
            helper.setText(R.id.tv_odds, playDetailBean.getOdd());
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
                    ((SSCBActivity) getActivity()).onTouZhuView(playDetailBean);
                    mMainAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_LR)})
    public void changeLr(String id) {
        mMainAdapter.notifyDataSetChanged();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_CHANGE_SELECTED)})
    public void randomSelected(String id) {
        if (!isHidden()) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    if (selectedBean.size() > 0) {
                        for (PlayTypeBean.PlayBean playBean : mList) {
                            LinkedHashMap<String, List<PlayDetailBean>> listLinkedHashMap = new LinkedHashMap<>();
                            listLinkedHashMap.put("1", SSCBDataUtil.init_ZX6_0_9_Data(
                                    playBean.getPlayTypeName(), "1"));
                            mStringLinkedHashMapLinkedHashMap.put(playBean.getPlayTypeName(), listLinkedHashMap);
                        }
                    }
                    int[] selecteds = SSCBDataUtil.getRandomList(6, 0, mStringLinkedHashMapLinkedHashMap.get(playName).get("1").size() - 1);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (selectedBean.size() > 0) {
                                selectedBean.clear();
                                //           ((SSCBActivity) getActivity()).offTouZhuView();
                            }
                            for (int i : selecteds) {
                                mStringLinkedHashMapLinkedHashMap.get(playName).get("1").get(i).setSelected(true);
                                selectedBean.add(mStringLinkedHashMapLinkedHashMap.get(playName).get("1").get(i));
                                ((SSCBActivity) getActivity()).onTouZhuView(mStringLinkedHashMapLinkedHashMap.get(playName).get("1").get(i));

                            }
                            mMainAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }
}
