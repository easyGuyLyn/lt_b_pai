package com.dawoo.lotterybox.view.activity.chart.lhc;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;

import java.util.ArrayList;
import java.util.List;

/**
 * 号码走势
 * Created by rain on 18-3-18.
 */

public class LHC_Record_code_Fragment extends LHC_Record_baseFragment {

    public static LHC_Record_code_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_code_Fragment fragment = new LHC_Record_code_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       countTopNums=49;
        if (typeData.size() > 0) {
            return;
        }
        for (int i = 1; i < 50; i++) {
            if (i < 10) {
                typeData.add("0" + String.valueOf(i));
            } else {
                typeData.add(String.valueOf(i));
            }
        }
    }



    void initData() {

        for (int i = 0; i < mDatas.size(); i++) {
            Handicap handicap = mDatas.get(i);
            List<LHCRecordBean> itemData = new ArrayList<>();
            String[] codes = handicap.getOpenCode().split(",");
            if (codes.length < 7) {
                continue;
            }
            for (int j = 0; j < typeData.size(); j++) {
                LHCRecordBean bean = new LHCRecordBean();
                for (String code : codes) {
                    if (code.equalsIgnoreCase(typeData.get(j))) {
                        bean.setCode(code);
                        bean.setLeaveOut(0);
                        break;
                    }
                }
                if (bean.getCode() == null) {
                    if (i == 0) {
                        bean.setLeaveOut(1);
                    } else if(i - 1 < contentDatas.size()) {
                        bean.setLeaveOut(contentDatas.get(i - 1).get(j).getLeaveOut() + 1);
                    }
                }
                itemData.add(bean);
            }

            contentDatas.add(itemData);
        }
        leftAdapter.notifyDataSetChanged();
        contentRV.isDrawLine(false);
        mAdapter.notifyDataSetChanged();
    }

}
