package com.dawoo.lotterybox.view.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.dawoo.lotterybox.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by benson on 18-1-1.
 */

public class UserNoticeDialog {
    private final Context mContext;
    private final List<String> mNoticeList;
    @BindView(R.id.close_iv)
    ImageView mClose;
    @BindView(R.id.notice_dialog_recyclerview)
    RecyclerView noticeRecyclerView;
    private Dialog mDialog;
    @BindView(R.id.notice_title)
    TextView noticeTitle;


    public UserNoticeDialog(@NonNull Context context, List<String> noticeList) {
        mContext = context;
        mNoticeList = noticeList;
        mDialog = new Dialog(context, R.style.CustomDialogStyle);
        mDialog.setContentView(R.layout.notice_diaolog);
        mDialog.setCancelable(false);
        ButterKnife.bind(this, mDialog);
        setLayoutParams();
        initViews();
        mDialog.show();
    }

    private void setLayoutParams() {
        Window win = mDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }

    public void showDialog() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @OnClick({R.id.close_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_iv:
                dismissDialog();
                break;
        }
    }

    void initViews() {
        NoticeRecyclerAdapter mAdapter = new NoticeRecyclerAdapter();
        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        noticeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        noticeRecyclerView.setAdapter(mAdapter);
        //设置分割线
        noticeRecyclerView.addItemDecoration(new DashlineItemDivider());
    }


    class NoticeRecyclerAdapter extends RecyclerView.Adapter {


        public NoticeRecyclerAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            NoticeViewHolder noticeViewHolder = new NoticeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_notice_dialog_recycler, parent, false));
            return noticeViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((NoticeViewHolder) holder).tvNoticeItem.setText(mNoticeList.get(position));
        }

        @Override
        public int getItemCount() {
            return mNoticeList.size();
        }
    }


    class NoticeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_notice_item)
        TextView tvNoticeItem;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class DashlineItemDivider extends RecyclerView.ItemDecoration {


        public void onDrawOver(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < (childCount - 1); i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                //以下计算主要用来确定绘制的位置
                final int top = child.getBottom() + params.bottomMargin;

                //绘制虚线
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(ContextCompat.getColor(mContext, R.color.setting_divide_line));
                Path path = new Path();
                path.moveTo(left, top);
                path.lineTo(right, top);
                PathEffect effects = new DashPathEffect(new float[]{15, 15, 15, 15}, 5);//此处单位是像素不是dp  注意 请自行转化为dp
                paint.setPathEffect(effects);
                c.drawPath(path, paint);


            }
        }

    }

    /**
     * 设置titleName
     *
     * @param titleName
     */
    public void setTitleName(String titleName) {
        noticeTitle.setText(titleName);
    }

    /**
     * 设置颜色
     */
    public void setTextColor(int color) {
        noticeTitle.setTextColor(color);
    }

}
