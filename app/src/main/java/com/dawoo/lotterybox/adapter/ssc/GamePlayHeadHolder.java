package com.dawoo.lotterybox.adapter.ssc;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.BaseViewHolder;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by b on 18-2-11.
 */

public class GamePlayHeadHolder extends BaseViewHolder{
    private Context mContext;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.hot_and_cold)
    CheckBox mHotAndCold;
    @BindView(R.id.cb_omit)
    CheckBox mCbOmit;
    @BindView(R.id.iv_random)
    ImageView mIvRandom;
    @BindView(R.id.tv_how_play)
    TextView mTvHowPlay;

    public GamePlayHeadHolder(Context context,View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext = context;
    }


    public void onBindView(PlayTypeBean.PlayBean mPlayTypeBean ,GamePlayAdapter gamePlayAdapter) {
        if(DataCenter.getInstance().isLogin()){
            mTvBalance.setText(DataCenter.getInstance().getUser().getBalance());
        }
        mTvHowPlay.setText(mPlayTypeBean.getSingleExplain());
        if (gamePlayAdapter.type == 0){
            mHotAndCold.setChecked(true);
        }else if (gamePlayAdapter.type == 1){
            mCbOmit.setChecked(true);
        }
        mIvRandom.setOnClickListener(v -> {
            ((BaseLotteryAActivity)mContext).doComputerSelect();
        });
        mTvHowPlay.setOnClickListener(v -> {
           if (mContext instanceof BaseLotteryAActivity){
               ((BaseLotteryAActivity)mContext).showHowPlayDialog();
           }
        });
        mHotAndCold.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                gamePlayAdapter.type = 0;
                ((BaseLotteryAActivity)mContext).openHotColdOrOmit(0);
                mCbOmit.setChecked(false);
            }else {
                if (!mCbOmit.isChecked()){
                    gamePlayAdapter.type = -1;
                    ((BaseLotteryAActivity)mContext).openHotColdOrOmit(-1);
                }
            }
        });
        mCbOmit.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                gamePlayAdapter.type = 1;
                ((BaseLotteryAActivity)mContext).openHotColdOrOmit(1);
                mHotAndCold.setChecked(false);
            }else {
                if (!mHotAndCold.isChecked()){
                    gamePlayAdapter.type = -1;
                    ((BaseLotteryAActivity)mContext).openHotColdOrOmit(-1);
                }
            }
        });
    }
}
