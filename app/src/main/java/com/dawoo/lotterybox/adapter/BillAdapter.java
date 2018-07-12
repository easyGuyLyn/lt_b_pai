package com.dawoo.lotterybox.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.*;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.bean.record.recordnum.BillDepositStatusEnum;
import com.dawoo.lotterybox.util.BillHistUtl;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;

/**
 * Created by rain on 18-4-19.
 */

public class BillAdapter<T> extends BaseQuickAdapter {
    int type = 0;//0资金记录 1充值记录

    public BillAdapter(int layoutResId) {
        super(layoutResId);
    }

    public BillAdapter(int layoutResId, int type) {
        super(layoutResId);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        BillItemBean itemBean = (BillItemBean) item;
        if (type == 0) {
            helper.setText(R.id.parent_name, BillHistUtl.getParentName(itemBean.getType()));
            helper.setText(R.id.child_name, BillHistUtl.getChildName(itemBean.getItem()));
            helper.setText(R.id.balance_num, NumberFormaterUtils.formaterS2S(itemBean.getBalance()));
            if (itemBean.getMoney().contains("-")) {
                helper.setTextColor(R.id.money_num, Color.parseColor("#B71A2E"));
            } else {
                helper.setTextColor(R.id.money_num, Color.parseColor("#069f11"));
            }
        } else {
            helper.setText(R.id.status_tv, BillHistUtl.getStatus(itemBean.getStatus()));
            helper.setText(R.id.parent_name, BillHistUtl.getParentName(itemBean.getType()) + "\n" + BillHistUtl.getChildName(itemBean.getItem()));
            if (itemBean.getStatus().equalsIgnoreCase(BillDepositStatusEnum.FAIL.getCode())
                    || itemBean.getStatus().equalsIgnoreCase(BillDepositStatusEnum.OVER_TIME.getCode())
                    || itemBean.getStatus().equalsIgnoreCase(BillDepositStatusEnum.REJECT.getCode())) {
                helper.setTextColor(R.id.money_num, Color.parseColor("#B71A2E"));//红
                helper.setTextColor(R.id.status_tv, Color.parseColor("#B71A2E"));//红
            } else if (itemBean.getStatus().equalsIgnoreCase(BillDepositStatusEnum.SUCCESS.getCode())) {
                helper.setTextColor(R.id.money_num, Color.parseColor("#069f11"));//绿
                helper.setTextColor(R.id.status_tv, Color.parseColor("#069f11"));//灰
            } else {
                helper.setTextColor(R.id.money_num, Color.parseColor("#0087d5"));//蓝
                helper.setTextColor(R.id.status_tv, Color.parseColor("#0087d5"));//灰
            }

        }
        String time = DateTool.getTimeFromLong(
                DateTool.FMT_DATE_TIME, itemBean.getCompletionTime() == 0 ? itemBean.getCreateTime() : itemBean.getCompletionTime());
        String[] times = time.split(" ");
        helper.setText(R.id.time_tv, times[0] + "\n" + times[1]);
        helper.setText(R.id.money_num, NumberFormaterUtils.formaterS2S(itemBean.getMoney()));
        int position = mData.indexOf(item);
        if (position % 2 == 0) {
            helper.itemView.setBackgroundResource(R.color.white);
        } else {
            helper.itemView.setBackgroundResource(R.color.item_gray);
        }

    }


}
