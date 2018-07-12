package com.dawoo.lotterybox.adapter.hall.parent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.BaseViewHolder;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.util.NoticeDialog;
import com.dawoo.lotterybox.util.SoundUtil;
import com.dawoo.lotterybox.view.view.MarqueeTextView;
import com.dawoo.lotterybox.view.view.UserNoticeDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-4-12.
 * 公告
 */

public class MarqueeTextViewHolder extends BaseViewHolder {

    private final Context mContext;
    MarqueeTextView mNoticeTv;

    public MarqueeTextViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mNoticeTv = itemView.findViewById(R.id.notice_tv);
    }

    public void bindView(List<Bulletin> bulletins) {
        ArrayList<String> titleList = new ArrayList<>();
        for (Bulletin bean : bulletins)
            titleList.add(bean.getContent());
        mNoticeTv.setTextArraysAndClickListener(titleList, view -> {
            SoundUtil.getInstance().playVoiceOnclick();
            UserNoticeDialog dialog = new UserNoticeDialog(mContext, titleList);
        });
    }
}
