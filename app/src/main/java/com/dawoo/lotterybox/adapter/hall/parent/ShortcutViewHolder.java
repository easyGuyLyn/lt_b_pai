package com.dawoo.lotterybox.adapter.hall.parent;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.BaseViewHolder;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.view.activity.ActivitiesActivity;
import com.dawoo.lotterybox.view.activity.account.bank.BindCardActivity;
import com.dawoo.lotterybox.view.activity.account.deposit.PayParentActivity;
import com.dawoo.lotterybox.view.activity.account.withdraw.WithdrawalsActivity;

/**
 * Created by b on 18-5-11.
 */

public class ShortcutViewHolder extends BaseViewHolder {

    private Context mContext;

    public ShortcutViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        LinearLayout ll_recharge = itemView.findViewById(R.id.ll_recharge);
        LinearLayout ll_withdraw = itemView.findViewById(R.id.ll_withdraw);
        LinearLayout ll_promo = itemView.findViewById(R.id.ll_promo);
        LinearLayout ll_custom_service = itemView.findViewById(R.id.ll_custom_service);
        ll_recharge.setOnClickListener(mOnClickListener);
        ll_withdraw.setOnClickListener(mOnClickListener);
        ll_promo.setOnClickListener(mOnClickListener);
        ll_custom_service.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ll_promo) {
                mContext.startActivity(new Intent(mContext, ActivitiesActivity.class));
                return;
            } else if (v.getId() == R.id.ll_custom_service) {
                ActivityUtil.gotoCustomerService();
                return;
            }
            if (!DataCenter.getInstance().getUser().isLogin()) {
                ActivityUtil.startLoginActivity();
                return;
            }
            switch (v.getId()) {
                case R.id.ll_recharge:
                    mContext.startActivity(new Intent(mContext, PayParentActivity.class));
                    break;

                case R.id.ll_withdraw:
                    if (DataCenter.getInstance().getUser().isBindBankCard()) {
                        ActivityUtils.startActivity(WithdrawalsActivity.class);
                    } else {
                        ActivityUtils.startActivity(BindCardActivity.class);
                    }
                    break;

            }
        }

    };
}
