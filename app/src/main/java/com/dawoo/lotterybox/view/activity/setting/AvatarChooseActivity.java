package com.dawoo.lotterybox.view.activity.setting;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.activity.UserInforMationActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author alex
 */
public class AvatarChooseActivity extends BaseActivity {

    @BindView(R.id.hv_top)
    HeaderView hdTop;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private AvatarChooseAdapter avatarChooseAdapter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_avatar_choose);
    }

    @Override
    protected void initViews() {
        hdTop.setHeader("选择头像", true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(gridLayoutManager);
        rvContent.addItemDecoration(new RecyclerSpace(1));
        //  rvContent.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        avatarChooseAdapter = new AvatarChooseAdapter();
        rvContent.setAdapter(avatarChooseAdapter);
    }

    @Override
    protected void initData() {
        List<String> avaterUrl = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            avaterUrl.add("a" + i);

        }
        avatarChooseAdapter.setNewData(avaterUrl);

        avatarChooseAdapter.setOnItemClickListener((adapter, view, position) -> {
            String url = avaterUrl.get(position);
            String name = SPUtils.getInstance().getString(SPConfig.USERNAME);
            SPUtils.getInstance().put(name, url);
            DataCenter.getInstance().getUser().setAvatarUrl(url);
            RxBus.get().post(ConstantValue.EVENT_UPDATE_AVATER, url);
            finish();
        });
    }

    class AvatarChooseAdapter extends BaseQuickAdapter<String, AvatarChooseAdapter.AvatarChooseHolder> {

        public AvatarChooseAdapter() {
            super(R.layout.item_avatar_choose);
        }

        @Override
        protected void convert(AvatarChooseHolder helper, String item) {
            Glide.with(mContext).load(mContext.getResources().getDrawable(PackageInfoUtil.getResource(BoxApplication.getContext(), item)))
                    .into(helper.ivAvater);
        }

        class AvatarChooseHolder extends BaseViewHolder {
            @BindView(R.id.iv_avatar)
            ImageView ivAvater;

            public AvatarChooseHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    public class RecyclerSpace extends RecyclerView.ItemDecoration {
        private int space;
        private int color = -1;
        private Drawable mDivider;
        private Paint mPaint;
        private int type;

        public int getColor() {
            return color;
        }

        public void setColor(@ColorRes int color) {
            this.color = color;
        }

        public RecyclerSpace(int space) {
            this.space = space;
        }

        public RecyclerSpace(int space, int color) {
            this.space = space;
            this.color = color;
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(color);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(space * 2);
        }

        public RecyclerSpace(int space, int color, int type) {
            this.space = space;
            this.color = color;
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(color);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(space * 2);
            this.type = type;
        }

        public RecyclerSpace(int space, Drawable mDivider) {
            this.space = space;
            this.mDivider = mDivider;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (parent.getLayoutManager() != null) {
                if (parent.getLayoutManager() instanceof LinearLayoutManager && !(parent.getLayoutManager() instanceof GridLayoutManager)) {
                    if (((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                        outRect.set(space, 0, space, 0);
                    } else {
                        outRect.set(0, space, 0, space);
                    }
                } else {
                    outRect.set(space, space, space, space);
                }
            }

        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            if (parent.getLayoutManager() != null) {
                if (parent.getLayoutManager() instanceof LinearLayoutManager && !(parent.getLayoutManager() instanceof GridLayoutManager)) {
                    if (((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                        drawHorizontal(c, parent);
                    } else {
                        drawVertical(c, parent);
                    }
                } else {
                    if (type == 0) {
                        drawGrideview(c, parent);
                    } else {
                        drawGrideview1(c, parent);
                    }
                }
            }
        }

        //绘制纵向 item 分割线

        private void drawVertical(Canvas canvas, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + layoutParams.rightMargin;
                final int right = left + space;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }

        //绘制横向 item 分割线
        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            int left = parent.getPaddingLeft();
            int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + layoutParams.bottomMargin;
                int bottom = top + space;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }

            }
        }

        //绘制grideview item 分割线 不是填充满的
        private void drawGrideview(Canvas canvas, RecyclerView parent) {
            GridLayoutManager linearLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            int childSize = parent.getChildCount();
            int other = parent.getChildCount() / linearLayoutManager.getSpanCount() - 1;
            if (other < 1) {
                other = 1;
            }
            other = other * linearLayoutManager.getSpanCount();
            if (parent.getChildCount() < linearLayoutManager.getSpanCount()) {
                other = parent.getChildCount();
            }
            int top, bottom, left, right, spancount;
            spancount = linearLayoutManager.getSpanCount() - 1;
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                if (i < other) {
                    top = child.getBottom() + layoutParams.bottomMargin;
                    bottom = top + space;
                    left = (layoutParams.leftMargin + space) * (i + 1);
                    right = child.getMeasuredWidth() * (i + 1) + left + space * i;
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                }
                if (i != spancount) {
                    top = (layoutParams.topMargin + space) * (i / linearLayoutManager.getSpanCount() + 1);
                    bottom = (child.getMeasuredHeight() + space) * (i / linearLayoutManager.getSpanCount() + 1) + space;
                    left = child.getRight() + layoutParams.rightMargin;
                    right = left + space;
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                } else {
                    spancount += 4;
                }
            }
        }

        /***/
        private void drawGrideview1(Canvas canvas, RecyclerView parent) {
            GridLayoutManager linearLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            int childSize = parent.getChildCount();
            int top, bottom, left, right, spancount;
            spancount = linearLayoutManager.getSpanCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                //画横线
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                top = child.getBottom() + layoutParams.bottomMargin;
                bottom = top + space;
                left = layoutParams.leftMargin + child.getPaddingLeft() + space;
                right = child.getMeasuredWidth() * (i + 1) + left + space * i;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
                //画竖线
                top = (layoutParams.topMargin + space) * (i / linearLayoutManager.getSpanCount() + 1);
                bottom = (child.getMeasuredHeight() + space) * (i / linearLayoutManager.getSpanCount() + 1) + space;
                left = child.getRight() + layoutParams.rightMargin;
                right = left + space;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }

                //画上缺失的线框
                if (i < spancount) {
                    top = child.getTop() + layoutParams.topMargin;
                    bottom = top + space;
                    left = (layoutParams.leftMargin + space) * (i + 1);
                    right = child.getMeasuredWidth() * (i + 1) + left + space * i;
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                }
                if (i % spancount == 0) {
                    top = (layoutParams.topMargin + space) * (i / linearLayoutManager.getSpanCount() + 1);
                    bottom = (child.getMeasuredHeight() + space) * (i / linearLayoutManager.getSpanCount() + 1) + space;
                    left = child.getLeft() + layoutParams.leftMargin;
                    right = left + space;
                    if (mDivider != null) {
                        mDivider.setBounds(left, top, right, bottom);
                        mDivider.draw(canvas);
                    }
                    if (mPaint != null) {
                        canvas.drawRect(left, top, right, bottom, mPaint);
                    }
                }
            }
        }
    }
}
