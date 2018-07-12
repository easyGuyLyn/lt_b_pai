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
 * Created by rain on 18-3-21.
 */

public class LHC_Record_LCode_line_Fragment extends LHC_Record_baseFragment {

    public static LHC_Record_LCode_line_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_LCode_line_Fragment fragment = new LHC_Record_LCode_line_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countTopNums = 11;
        if (typeData.size() > 0) {
            return;
        }
        typeData.add("特码");
        typeData.add("0尾");
        typeData.add("1尾");
        typeData.add("2尾");
        typeData.add("3尾");
        typeData.add("4尾");
        typeData.add("5尾");
        typeData.add("6尾");
        typeData.add("7尾");
        typeData.add("8尾");
        typeData.add("9尾");
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
                int lastCode = Integer.parseInt(code.substring(code.length() - 1));
                if (j == 0) {
                    bean.setCode(code);
                } else {
                    if (lastCode == j - 1) {
                        bean.setCode(code);
                        bean.setLeaveOut(0);
                        integerList.add(j);
                    }
                }
                if (bean.getCode() == null) {
                    if (i == 0) {
                        bean.setLeaveOut(1);
                    } else if(i - 1 < contentDatas.size()){
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
