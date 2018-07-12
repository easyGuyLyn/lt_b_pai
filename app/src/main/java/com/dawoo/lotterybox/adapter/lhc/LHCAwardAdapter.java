package com.dawoo.lotterybox.adapter.lhc;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.lottery.LHCUtil;

import java.util.List;

/**
 * Created by rain on 18-3-7.
 */

public class LHCAwardAdapter extends BaseQuickAdapter{
    public LHCAwardAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    public LHCAwardAdapter(@Nullable List data) {
        super(data);
    }

    public LHCAwardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        Handicap cqsscAwardResultBean = (Handicap) item;
        String[] openCodes = cqsscAwardResultBean.getOpenCode().split(",");
        String expct = cqsscAwardResultBean.getExpect();
        helper.setText(R.id.lhc_issue, mContext.getString(R.string.which_periods, expct.substring(expct.length() - 3)));
        int position =helper.getLayoutPosition();
        if(position%2==0){
            helper.setBackgroundRes(R.id.lhc_ll,R.color.sm_bg);
        }else{
            helper.setBackgroundRes(R.id.lhc_ll,R.color.sm_result_bg);
        }
        if(openCodes!=null&&openCodes.length==7){
            LHCUtil.initBall(helper.getView(R.id.sm_code_0),helper.getView(R.id.sm_code_name_0),openCodes[0]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_1),helper.getView(R.id.sm_code_name_1),openCodes[1]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_2),helper.getView(R.id.sm_code_name_2),openCodes[2]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_3),helper.getView(R.id.sm_code_name_3),openCodes[3]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_4),helper.getView(R.id.sm_code_name_4),openCodes[4]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_5),helper.getView(R.id.sm_code_name_5),openCodes[5]);
            LHCUtil.initBall(helper.getView(R.id.sm_code_6),helper.getView(R.id.sm_code_name_6),openCodes[6]);
        }
    }

}
