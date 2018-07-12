package com.dawoo.lotterybox.adapter.lhc;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;

/**
 * Created by rain on 18-3-8.
 */

public class LHCTopTypeAdapter extends BaseQuickAdapter{
    public LHCTopTypeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        PlayChooseBean bean = (PlayChooseBean) item;
        helper.itemView.setSelected(bean.isSelected());
        helper.setText(R.id.tv_top_type,bean.getBetName());
        if(bean.isSelected()){
            helper.setTextColor(R.id.tv_top_type, BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
        }else{
            helper.setTextColor(R.id.tv_top_type,BoxApplication.getContext().getResources().getColor(R.color.text_blue));
        }
    }
}
