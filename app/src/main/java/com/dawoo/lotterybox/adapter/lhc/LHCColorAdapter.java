package com.dawoo.lotterybox.adapter.lhc;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;

/**
 * Created by rain on 18-3-9.
 */

public class LHCColorAdapter<T> extends BaseQuickAdapter {
    public LHCColorAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        PlayDetailBean bean = (PlayDetailBean) item;
        FlexboxLayout flexboxLayout = helper.getView(R.id.bos_fl);
        TextView nameTV = helper.getView(R.id.play_name);
        TextView oddTV = helper.getView(R.id.play_odd);
        if(bean==null){return;}

        if(LHCDataUtils.hot==0){
            helper.setText(R.id.lv_tv, "");
        }else if(LHCDataUtils.hot==1){
            helper.setText(R.id.lv_tv, bean.getLr());
        }else{
            helper.setText(R.id.lv_tv, bean.getYl());
        }
        helper.itemView.setSelected(bean.isSelected());
        nameTV.setText(bean.getNum());
        oddTV.setText(bean.getOdd());
        if (bean.isSelected()) {
            nameTV.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
            oddTV.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
        } else {
            nameTV.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.black));
            oddTV.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.text_blue));
        }
        // flexboxLayout.setFlexDirection(FlexDirection.ROW_REVERSE);
        flexboxLayout.setFlexWrap(FlexWrap.WRAP);
        flexboxLayout.setJustifyContent(JustifyContent.FLEX_END);
        if (flexboxLayout.getChildCount() > 0) {
            flexboxLayout.removeAllViewsInLayout();
        }
        String[] names = bean.getKind().split(",");
        if (names != null) ;
        for (String name : names) {
            TextView boll = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout_lhc_simple_boss_view, null);
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(DensityUtil.dp2px(mContext, 30), DensityUtil.dp2px(mContext, 30));
            params.setMargins(DensityUtil.dp2px(mContext, 2), 0, 0, 0);
            boll.setLayoutParams(params);
            boll.setText(name);
            boll.setBackgroundResource(LHCUtil.getBallbg(name));
            flexboxLayout.addView(boll);
        }

    }
}
