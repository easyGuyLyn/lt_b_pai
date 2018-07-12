package com.dawoo.lotterybox.view.activity.lottery.qt;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;

/**
 * 120期历史开奖数据适配器
 * Created by benson on 18-3-23.
 */

public class QTB_History_QuickAdapter extends BaseQuickAdapter {
    public QTB_History_QuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        Handicap handicap = (Handicap) item;
        String openCode = handicap.getOpenCode().replace(",", " ");
        helper.setText(R.id.tv_no, mContext.getString(R.string.which_periods_, handicap.getExpect()));
        helper.setText(R.id.tv_award_results, openCode);
        helper.setText(R.id.tv_status, Lottery_B_DataUtils.getSanStarStatus(handicap.getOpenCode()));
        if (helper.getAdapterPosition() % 2 == 0) {
            helper.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.custom_blue_s));
        } else {
            helper.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.custom_blue_light1));
        }
    }
}
