package com.dawoo.lotterybox.adapter.ssc;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.BaseViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by b on 18-2-11.
 */

public class GamePlayHolder extends BaseViewHolder {

    private Context mContext;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rb_all)
    RadioButton mRbAll;
    @BindView(R.id.rb_big)
    RadioButton mRbBig;
    @BindView(R.id.rb_small)
    RadioButton mRbSmall;
    @BindView(R.id.rb_odd)
    RadioButton mRbOdd;
    @BindView(R.id.rb_even_numbers)
    RadioButton mRbEvenNumbers;
    @BindView(R.id.rb_clear)
    RadioButton mRbClear;
    @BindView(R.id.rg_ssc_game_play)
    RadioGroup mRgSscGamePlay;
    @BindView(R.id.gv_ball_list)
    RecyclerView mGvBallList;

    public GamePlayHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        ButterKnife.bind(this, itemView);
    }

    public void onBindView(PlayTypeBean.PlayBean mPlayTypeBean, int pos, GamePlayAdapter gamePlayAdapter) {
        PlayTypeBean.PlayBean.LayoutBean layoutBean = mPlayTypeBean.getLayoutBeans().get(pos);
        mTvTitle.setText(layoutBean.getLayoutTitle());
        mRgSscGamePlay.clearCheck();
        if (layoutBean.getShowRightMenuType() == 0) {
            mRbAll.setVisibility(View.VISIBLE);
            mRbBig.setVisibility(View.VISIBLE);
            mRbSmall.setVisibility(View.VISIBLE);
            mRbOdd.setVisibility(View.VISIBLE);
            mRbEvenNumbers.setVisibility(View.VISIBLE);
            mRbClear.setVisibility(View.VISIBLE);
        } else if (layoutBean.getShowRightMenuType() == 1) {
            mRbAll.setVisibility(View.VISIBLE);
            mRbBig.setVisibility(View.GONE);
            mRbSmall.setVisibility(View.GONE);
            mRbOdd.setVisibility(View.GONE);
            mRbEvenNumbers.setVisibility(View.GONE);
            mRbClear.setVisibility(View.VISIBLE);
        } else {
            mRbAll.setVisibility(View.GONE);
            mRbBig.setVisibility(View.GONE);
            mRbSmall.setVisibility(View.GONE);
            mRbOdd.setVisibility(View.GONE);
            mRbEvenNumbers.setVisibility(View.GONE);
            mRbClear.setVisibility(View.GONE);
        }

        GamePlayBallQuickAdapter mQuickAdapter = null;
        if (layoutBean.getItemType() == 0) {  //布局类型
            mQuickAdapter = new GamePlayBallQuickAdapter(R.layout.item_grid_game_play_ball);  //球
        } else
            mQuickAdapter = new GamePlayBallQuickAdapter(R.layout.item_grid_game_play_block); //方块

        mGvBallList.setLayoutManager(new GridLayoutManager(mContext, 7));
        mQuickAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.empty_view, null));
        mGvBallList.setAdapter(mQuickAdapter);
        mQuickAdapter.setNewData(layoutBean.getChildLayoutBeans());
        mQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.isSelected()) {
                    view.setSelected(false);
                    layoutBean.getChildLayoutBeans().get(position).setSelected(false);
                } else {
                    view.setSelected(true);
                    layoutBean.getChildLayoutBeans().get(position).setSelected(true);
                    if (!layoutBean.isSelectEqual()) {
                        for (int i = 0; i < mPlayTypeBean.getLayoutBeans().size(); i++) {
                            if (i != pos) {
                                PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = mPlayTypeBean.getLayoutBeans().get(i).getChildLayoutBeans().get(position);
                                if (childLayoutBean.isSelected()) {
                                    childLayoutBean.setSelected(false);
                                    gamePlayAdapter.notifyItemChanged(i + 1);
                                }
                            }
                        }
                    }
                }
                if (layoutBean.getSelectMax() != 0) { //最大可选择控制
                    int max = 1;
                    for (int i = 0; i < layoutBean.getChildLayoutBeans().size(); i++) {
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = layoutBean.getChildLayoutBeans().get(i);
                        if (childLayoutBean.isSelected()) {
                            if (i != position) {
                                if (max < layoutBean.getSelectMax()) {
                                    max++;
                                } else {
                                    childLayoutBean.setSelected(false);
                                }
                            }
                        }
                    }
                    gamePlayAdapter.notifyItemChanged(pos + 1);
                }
                if (mContext instanceof BaseLotteryAActivity)
                    ((BaseLotteryAActivity) mContext).isBetBtEnable();
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = mGvBallList.getChildCount();
                switch (v.getId()) {
                    case R.id.rb_all:
                        for (int i = 0; i < count; i++) {
                            mGvBallList.getChildAt(i).setSelected(true);
                            layoutBean.getChildLayoutBeans().get(i).setSelected(true);
                        }
                        break;
                    case R.id.rb_big:
                        for (int i = 0; i < count; i++) {
                            if (i < (count / 2)) {
                                mGvBallList.getChildAt(i).setSelected(false);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(false);
                            } else {
                                mGvBallList.getChildAt(i).setSelected(true);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(true);
                            }
                        }
                        break;
                    case R.id.rb_small:
                        for (int i = 0; i < count; i++) {
                            if (i < (count / 2)) {
                                mGvBallList.getChildAt(i).setSelected(true);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(true);
                            } else {
                                mGvBallList.getChildAt(i).setSelected(false);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(false);
                            }
                        }
                        break;
                    case R.id.rb_odd:
                        for (int i = 0; i < count; i++) {
                            int i2 = i;
                            if (mPlayTypeBean.getPlayTypeCode().contains("pk10")) //pk10布局数字从1开始
                                i2 += 1;
                            if ((i2 % 2) != 0) {
                                mGvBallList.getChildAt(i).setSelected(true);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(true);
                            } else {
                                mGvBallList.getChildAt(i).setSelected(false);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(false);
                            }
                        }
                        break;
                    case R.id.rb_even_numbers:
                        for (int i = 0; i < count; i++) {
                            int i2 = i;
                            if (mPlayTypeBean.getPlayTypeCode().contains("pk10"))
                                i2 += 1;
                            if ((i2 % 2) == 0) {
                                mGvBallList.getChildAt(i).setSelected(true);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(true);
                            } else {
                                mGvBallList.getChildAt(i).setSelected(false);
                                layoutBean.getChildLayoutBeans().get(i).setSelected(false);
                            }
                        }
                        break;
                    case R.id.rb_clear:
                        for (int i = 0; i < count; i++) {
                            mGvBallList.getChildAt(i).setSelected(false);
                            layoutBean.getChildLayoutBeans().get(i).setSelected(false);
                        }
                        break;
                }

                if (mContext instanceof BaseLotteryAActivity)
                    ((BaseLotteryAActivity) mContext).isBetBtEnable();

            }
        };

        mRbAll.setOnClickListener(onClickListener);
        mRbBig.setOnClickListener(onClickListener);
        mRbSmall.setOnClickListener(onClickListener);
        mRbOdd.setOnClickListener(onClickListener);
        mRbEvenNumbers.setOnClickListener(onClickListener);
        mRbClear.setOnClickListener(onClickListener);

    }

    class GamePlayBallQuickAdapter extends BaseQuickAdapter {
        public GamePlayBallQuickAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(com.chad.library.adapter.base.BaseViewHolder helper, Object item) {
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean) item;
            helper.setText(R.id.tv_ball, childLayoutBean.getNumber());
            if (childLayoutBean.getNumberRelevant() == -1)
                helper.setText(R.id.tv_ball_info, "");
            else if (childLayoutBean.getNumberRelevant() >= 1000)
                helper.setText(R.id.tv_ball_info, childLayoutBean.getNumberRelevant() - 1000 + "");
            else
                helper.setText(R.id.tv_ball_info, childLayoutBean.getNumberRelevant() + "");
            helper.itemView.setSelected(childLayoutBean.isSelected());
        }
    }
}
