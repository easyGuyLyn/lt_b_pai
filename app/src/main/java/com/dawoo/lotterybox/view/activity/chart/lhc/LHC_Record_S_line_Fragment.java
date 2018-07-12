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
 * 特码走势
 * Created by rain on 18-3-19.
 */

public class LHC_Record_S_line_Fragment extends LHC_Record_baseFragment {
    public static LHC_Record_S_line_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_S_line_Fragment fragment = new LHC_Record_S_line_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countTopNums = 49;
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

    @Override
    void initData() {

        for (int i = 0; i < mDatas.size(); i++) {
            Handicap handicap = mDatas.get(i);
            List<LHCRecordBean> itemData = new ArrayList<>();
            String[] openCodes= handicap.getOpenCode().split(",");
            if (openCodes.length < 7) {
                continue;
            }
            String code = openCodes[6];
            for (int j = 0; j < typeData.size(); j++) {
                LHCRecordBean bean = new LHCRecordBean();
                if (code.equalsIgnoreCase(typeData.get(j))) {
                    bean.setCode(code);
                    bean.setAnimall(false);
                    bean.setLeaveOut(0);
                    integerList.add(j);
                }
                if (bean.getCode() == null) {
                    if (i == 0) {
                        bean.setLeaveOut(1);
                    } else {
                        bean.setLeaveOut(contentDatas.get(i - 1).get(j).getLeaveOut() + 1);
                    }
                }
                itemData.add(bean);
            }

            contentDatas.add(itemData);
        }
        leftAdapter.notifyDataSetChanged();
        contentRV.setList(integerList);
        mAdapter.notifyDataSetChanged();
    }

}
