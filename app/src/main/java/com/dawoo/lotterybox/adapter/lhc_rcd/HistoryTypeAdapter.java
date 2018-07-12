package com.dawoo.lotterybox.adapter.lhc_rcd;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;

/**
 * Created by rain on 18-4-25.
 */

public class HistoryTypeAdapter extends BaseQuickAdapter {
    public HistoryTypeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item instanceof String) {
            helper.setText(R.id.item_left_tv, item.toString());
        } else {
            Handicap handicap = (Handicap) item;
            helper.setText(R.id.item_left_tv, BoxApplication.getContext().getString(R.string.which_periods, handicap.getExpect().substring(handicap.getExpect().length() - 3)));
            int position = helper.getAdapterPosition();
            if (position % 2 == 0) {
                helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            } else {
                helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_chart_item_bg));
            }
        }

    }

}
