package com.dawoo.lotterybox.adapter.lhc;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.ParentPlayTypeBean;

import java.util.List;

/**
 * Created by rain on 18-3-7.
 */

public class LHCParentPlayAdapter extends BaseQuickAdapter{
    public LHCParentPlayAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        ParentPlayTypeBean typeBean= (ParentPlayTypeBean) item;
        helper.setText(R.id.tv_sscb_palyType,typeBean.getName());
        if(typeBean.isChoose()){
            helper.itemView.setSelected(true);
            helper.setTextColor(R.id.tv_sscb_palyType, Color.parseColor("#fe9e2e"));
        }else{
            helper.itemView.setSelected(false);
            helper.setTextColor(R.id.tv_sscb_palyType, Color.parseColor("#ff81cfe1"));

        }
    }
}
