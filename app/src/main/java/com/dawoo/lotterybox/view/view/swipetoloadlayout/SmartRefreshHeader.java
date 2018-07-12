package com.dawoo.lotterybox.view.view.swipetoloadlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by alex on 18-5-15.
 */

public class SmartRefreshHeader extends LinearLayout implements RefreshHeader {
    public static final int MODE_DOG_RUN = 1;
    public static final int MODE_DOG_EAT = 2;


    private TextView mTvStatus;
    private ImageView mIv_refresh_headPic;
    private TextView mTv_final_time;
    private String mLastUpdateTime;
    private int mAnimPic = R.drawable.chickenrun1;
    private int mAnim = R.drawable.anim_refresh_head_dog_run;

    public SmartRefreshHeader(Context context) {
        super(context);
        init();
    }

    public SmartRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @NonNull
    @Override
    public View getView() {
        setBackgroundColor(getResources().getColor(R.color.game_record_bootom_color));
        return this;
    }

    private void init() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.refresh_head_layout, null);
        mTvStatus = view.findViewById(R.id.tv_refresh_status);
        mIv_refresh_headPic = view.findViewById(R.id.iv_refresh_headPic);
        mTv_final_time = view.findViewById(R.id.tv_final_time);
        addView(view, lp);
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Scale;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        startHeadAnim();
        setAnimMode(MODE_DOG_RUN);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
//        if (!isDragging) {
//            if (offset >= maxDragHeight) {
//                mTvStatus.setText(getContext().getResources().getString(R.string.RELEASE_TO_REFRESH));
//            } else {
//                mTvStatus.setText(getContext().getResources().getString(R.string.SWIPE_TO_REFRESH));
//            }
//        } else {
//            mTvStatus.setText(getContext().getResources().getString(R.string.REFRESH_RETURNING));
//        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        setAnimMode(MODE_DOG_RUN);
        updateTime();
        startHeadAnim();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        stopHeadAnim();
        mTvStatus.setText(getContext().getResources().getString(R.string.REFRESH_COMPLETE));
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTvStatus.setText("下拉开始刷新");

                break;
            case Refreshing:
                mTvStatus.setText("正在刷新");

                break;
            case ReleaseToRefresh:
                mTvStatus.setText("释放立即刷新");

                break;
        }
    }

    /**
     * 切换动画的模式
     *
     * @param mode
     */
    public void setAnimMode(int mode) {
        if (mode == MODE_DOG_RUN) {
            mAnimPic = R.drawable.chickenrun1;
            mAnim = R.drawable.anim_refresh_head_dog_run;
        } else if (mode == MODE_DOG_EAT) {
            mAnimPic = R.drawable.chicken_peck1;
            mAnim = R.drawable.anim_refresh_head_dog_eat;
        }
        mIv_refresh_headPic.setImageResource(mAnimPic);
    }

    private void updateTime() {
        mLastUpdateTime = DateTool.getTimeFromLong(DateTool.FMT_TIME_4, System.currentTimeMillis());
        mTv_final_time.setText("最后更新:  今天 " + mLastUpdateTime);
    }

    private void startHeadAnim() {
        Log.e("HeadAnim", "startHeadAnim");
        mIv_refresh_headPic.setImageResource(mAnim);
        AnimationDrawable ad = (AnimationDrawable) mIv_refresh_headPic.getDrawable();
        ad.start();
    }

    private void stopHeadAnim() {
        Log.e("HeadAnim", "stopHeadAnim");
        Drawable drawable = mIv_refresh_headPic.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        mIv_refresh_headPic.setImageResource(mAnimPic);
    }
}
