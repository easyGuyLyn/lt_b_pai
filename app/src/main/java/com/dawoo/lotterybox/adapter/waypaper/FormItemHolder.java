package com.dawoo.lotterybox.adapter.waypaper;

import android.content.Context;
import android.view.View;
import android.widget.SimpleAdapter;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.BaseViewHolder;
import com.dawoo.lotterybox.view.view.MeasureAllGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by b on 18-3-19.
 */

public class FormItemHolder extends BaseViewHolder {

    private MeasureAllGridView mRlv;
    private Context mContext;

    public FormItemHolder(Context context,View itemView) {
        super(itemView);
        this.mContext = context;
        mRlv = itemView.findViewById(R.id.gv_form_item);
    }

    public void onBindView(List<String> strings) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (String lotteriesBean : strings) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("lottery", lotteriesBean);
            listMap.add(map);
        }
        String[] from = {"lottery"};
        int[] to = {R.id.tv_lattice};
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, listMap, R.layout.item_way_paper_form_lattice, from, to);
        mRlv.setAdapter(simpleAdapter);
    }
}
