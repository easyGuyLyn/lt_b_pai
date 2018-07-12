package com.dawoo.lotterybox.adapter.hall.parent;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.BaseViewHolder;
import com.dawoo.lotterybox.adapter.hall.child.RecyclerAdapter;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.bean.lottery.Lottery;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;

import java.util.ArrayList;
import java.util.List;

import static com.dawoo.lotterybox.bean.TypeAndLotteryBean.CHILD_ITEM;
import static com.dawoo.lotterybox.bean.TypeAndLotteryBean.PARENT_ITEM;
import static com.dawoo.lotterybox.ConstantValue.OFFICIAL_PLAY;
import static com.dawoo.lotterybox.ConstantValue.TRADITIONAL_PLAY;
import static com.dawoo.lotterybox.ConstantValue.ESPECIAL_LT_NAME;

/**
 * Created by b on 18-4-12.
 */

public class ChildRecyclerViewHolder extends BaseViewHolder {

    private final Context mContext;
    RecyclerView mRecycleView;
    private RecyclerAdapter mAdapter;

    public ChildRecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        mRecycleView = itemView.findViewById(R.id.recycle_view);
        this.mContext = context;
    }

    public void bindView(List<TypeAndLotteryBean> lotteryTypes, RecyclerAdapter.OnScrollListener mOnScrollListener) {
        addTypeData(lotteryTypes);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (lotteryTypes.get(position).getType() == PARENT_ITEM)
                    return 1;

                if (lotteryTypes.get(position).getType() == CHILD_ITEM)
                    return 2;
                return 1;
            }
        });

        mRecycleView.setNestedScrollingEnabled(false);
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
//        mRecycleView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new RecyclerAdapter(mContext, lotteryTypes, mRecycleView);
        mAdapter.setOnScrollListener(mOnScrollListener);
        mRecycleView.setAdapter(mAdapter);
    }

//    RecyclerAdapter.OnScrollListener mOnScrollListener = new RecyclerAdapter.OnScrollListener() {
//        @Override
//        public void scrollTo(int pos) {
//            mRecycleView.scrollToPosition(pos);
//        }
//    };

    private void addTypeData(List<TypeAndLotteryBean> lotteryTypes) {
        for (TypeAndLotteryBean typeAndLotteryBean : lotteryTypes) {
            if (LotteryEnum.HKLHC.getType().equals(typeAndLotteryBean.getTypeCode())) {
                typeAndLotteryBean.setIcon(R.mipmap.icon_6he);
            } else if (LotteryEnum.BJKL8.getType().equals(typeAndLotteryBean.getTypeCode())) {
                typeAndLotteryBean.setIcon(R.mipmap.icon_k8);
            } else if (LotteryEnum.CQXYNC.getType().equals(typeAndLotteryBean.getTypeCode())) {
                // 十分彩 重庆幸运农场， 广东快乐十分
                typeAndLotteryBean.setIcon(R.mipmap.icon_sfc);
            } else if (LotteryEnum.XY28.getType().equals(typeAndLotteryBean.getTypeCode())) {
                typeAndLotteryBean.setIcon(R.mipmap.icon_xy28);
            } else if (LotteryEnum.GXK3.getType().equals(typeAndLotteryBean.getTypeCode())) {
                // k3 包括江苏快三 广西快三 湖北快三 安徽快三
                typeAndLotteryBean.setIcon(R.mipmap.icon_k3);
            } else if (LotteryEnum.BJPK10.getType().equals(typeAndLotteryBean.getTypeCode())) {
                typeAndLotteryBean.setIcon(R.mipmap.img_pk10);
            } else if (LotteryEnum.CQSSC.getType().equals(typeAndLotteryBean.getTypeCode())) {
                typeAndLotteryBean.setIcon(R.mipmap.icon_ssc);
            } else if (LotteryEnum.FC3D.getType().equals(typeAndLotteryBean.getTypeCode())) {
                // 福彩3D 排列3
                typeAndLotteryBean.setIcon(R.mipmap.icon_p3);
            }
            setABDisPatch(typeAndLotteryBean);
        }
    }

    /**
     * 设置ab盘分流
     */
    void setABDisPatch(TypeAndLotteryBean typeAndLotteryBean) {
        String type = typeAndLotteryBean.getTypeCode();
        if (LotteryEnum.HKLHC.getType().equals(type) || LotteryEnum.BJKL8.equals(type)
                || LotteryEnum.XY28.getType().equals(type) || LotteryEnum.CQXYNC.getType().equals(type)) {
            return;
        }

        List<TypeAndLotteryBean.LotteriesBean> lotteriesBeans = typeAndLotteryBean.getLotteries();
        List<TypeAndLotteryBean.LotteriesBean> lotteriesBeans2 = new ArrayList<>();
        lotteriesBeans2.addAll(lotteriesBeans);
        for (TypeAndLotteryBean.LotteriesBean lotteriesBean : lotteriesBeans) {
            String mode = lotteriesBean.getModel();
            String ltName = lotteriesBean.getName();

            if (ConstantValue.MODE_ALL_PLAY.equals(mode)) {
                // 官方和传统玩法
                lotteriesBeans2.remove(lotteriesBean);
                lotteriesBean.setName(ltName + OFFICIAL_PLAY);
                lotteriesBeans2.add(lotteriesBean);

                TypeAndLotteryBean.LotteriesBean lotteriesBean2 = new TypeAndLotteryBean.LotteriesBean();
                lotteriesBean2.setName(ltName + TRADITIONAL_PLAY);
                lotteriesBean2.setCode(lotteriesBean.getCode());
                lotteriesBean2.setModel(lotteriesBean.getModel());
                lotteriesBeans2.add(lotteriesBean2);
            } else if (ConstantValue.MODE_OFFICIAL_PLAY.equals(mode)) {
                // 官方玩法
                lotteriesBeans2.remove(lotteriesBean);
                lotteriesBean.setName(ltName + OFFICIAL_PLAY);
                lotteriesBeans2.add(lotteriesBean);
            } else if (ConstantValue.MODE_TRADITIONAL_PLAY.equals(mode)) {
                // 传统玩法
                lotteriesBeans2.remove(lotteriesBean);
                TypeAndLotteryBean.LotteriesBean lotteriesBean2 = new TypeAndLotteryBean.LotteriesBean();
                lotteriesBean2.setName(ltName + TRADITIONAL_PLAY);
                lotteriesBean2.setCode(lotteriesBean.getCode());
                lotteriesBean2.setModel(lotteriesBean.getModel());
                lotteriesBeans2.add(lotteriesBean2);
            }
        }
        typeAndLotteryBean.setLotteries(lotteriesBeans2);
    }
}
