package com.dawoo.lotterybox.view.activity.chart.keno2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by archar on 18-5-4.
 */

public class Xy28RewardListFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    public static final String REFRSHLIST = "refrsh_list";
    @BindView(R.id.rlv_lottery_record)
    RecyclerView mRlvLotteryRecord;
    private Unbinder mUnbinder;


    private ArrayList<Handicap> mHandicaps = new ArrayList<>();
    private AwardResultsQuickAdapter mAwardResultsQuickAdapter;


    public Xy28RewardListFragment() {
    }


    public static Xy28RewardListFragment newInstance(ArrayList<Handicap> handicaps) {
        Xy28RewardListFragment fragment = new Xy28RewardListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, handicaps);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHandicaps = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xy28_reward_record, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        RxBus.get().register(this);
        initView();
        initData();
        return view;
    }

    private void initView() {
        mAwardResultsQuickAdapter = new AwardResultsQuickAdapter(R.layout.item_xy28_lottery_result_item);
        mRlvLotteryRecord.setLayoutManager(new LinearLayoutManager(mContext));
        mRlvLotteryRecord.setAdapter(mAwardResultsQuickAdapter);
    }

    private void initData() {
        mAwardResultsQuickAdapter.setNewData(mHandicaps);
    }

    @Subscribe(tags = {@Tag(REFRSHLIST)})
    public void clearSelectedData(Xy28RewardBean xy28EventBean) {
        if (xy28EventBean != null) {
            mHandicaps = xy28EventBean.getHandicaps();
            initData();
        }
    }

    class AwardResultsQuickAdapter extends BaseQuickAdapter {
        public AwardResultsQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            Handicap cqsscAwardResultBean = (Handicap) item;
            String openCode = cqsscAwardResultBean.getOpenCode().replace(",", " ");
            String[] nums = cqsscAwardResultBean.getOpenCode().split(",");
            helper.setText(R.id.tv_no, getString(R.string.which_periods, cqsscAwardResultBean.getExpect()));
            helper.setText(R.id.tv_num, openCode);
            int addNums = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]) + Integer.valueOf(nums[2]);
            helper.setText(R.id.tv_hz, addNums + "");
            helper.setText(R.id.tv_dx, getString(addNums < 14 ? R.string.small : R.string.big));
            helper.setText(R.id.tv_ds, getString((addNums % 2) == 0 ? R.string.double_ : R.string.single));

            if (helper.getAdapterPosition() % 2 == 0) {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_s));
            } else {
                helper.itemView.setBackgroundColor(getResources().getColor(R.color.custom_blue_light1));
            }
        }
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    protected void loadData() {

    }
}
