package com.dawoo.lotterybox.view.activity.chart.lhc;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LHCUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 18-3-19.
 */

public class LHC_Record_Animal_Fragment extends LHC_Record_baseFragment {

    public static LHC_Record_Animal_Fragment newInstace(List<Handicap> mDatas) {
        LHC_Record_Animal_Fragment fragment = new LHC_Record_Animal_Fragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ConstantValue.RENCT_DATA, (ArrayList<? extends Parcelable>) mDatas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countTopNums = 19;
        if (typeData.size() > 0) {
            return;
        }
        typeData.add("正一");
        typeData.add("正二");
        typeData.add("正三");
        typeData.add("正四");
        typeData.add("正五");
        typeData.add("正六");
        typeData.add("特码");
        typeData.add("鼠");
        typeData.add("牛");
        typeData.add("虎");
        typeData.add("兔");
        typeData.add("龙");
        typeData.add("蛇");
        typeData.add("马");
        typeData.add("羊");
        typeData.add("猴");
        typeData.add("鸡");
        typeData.add("狗");
        typeData.add("猪");
    }

    @Override
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
                if (j < 7) {
                    bean.setCode(LHCUtil.getShengXiao(codes[j]));
                    bean.setLeaveOut(0);
                    bean.setAnimall(true);
                } else {
                    if (LHCUtil.getShengXiao(codes[6]).equalsIgnoreCase(typeData.get(j))) {
                        bean.setCode(codes[6]);
                        bean.setLeaveOut(0);
                        integerList.add(j);
                    }
                    if (bean.getCode() == null) {
                        if (i == 0) {
                            bean.setLeaveOut(1);
                        } else {
                            if (i - 1 < contentDatas.size() && j < countTopNums) {
                                bean.setLeaveOut(contentDatas.get(i - 1).get(j).getLeaveOut() + 1);
                            }

                        }
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
