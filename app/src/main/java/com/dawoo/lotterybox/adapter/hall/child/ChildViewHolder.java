package com.dawoo.lotterybox.adapter.hall.child;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3AActivity;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3BActivity;
import com.dawoo.lotterybox.view.activity.lottery.keno.BJKL8Activity;
import com.dawoo.lotterybox.view.activity.lottery.keno.XY28Activity;
import com.dawoo.lotterybox.view.activity.lottery.pk10.PK10AActivity;
import com.dawoo.lotterybox.view.activity.lottery.pk10.PK10BActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTAActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTBActivity;
import com.dawoo.lotterybox.view.activity.lottery.sfc.CqxyncActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCAActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCBActivity;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dawoo.lotterybox.ConstantValue.ESPECIAL_LT_NAME;
import static com.dawoo.lotterybox.ConstantValue.OFFICIAL_PLAY;
import static com.dawoo.lotterybox.ConstantValue.TRADITIONAL_PLAY;
/**
 * 首页彩种类型列表二级布局
 * 子布局ViewHolder
 */

public class ChildViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private GridView mGv_lottery;

    public ChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final TypeAndLotteryBean dataBean, final int pos) {
        List<TypeAndLotteryBean.LotteriesBean> lotteries = dataBean.getLotteries();
        mGv_lottery = (GridView) view.findViewById(R.id.gv_lottery);

        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (TypeAndLotteryBean.LotteriesBean lotteriesBean : lotteries) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("lottery", lotteriesBean.getName());
            listMap.add(map);
        }
        String[] from = {"lottery"};
        int[] to = {R.id.tv_lottery_name};
        SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, listMap, R.layout.item_grad_lottery_type, from, to);
        mGv_lottery.setAdapter(simpleAdapter);
        mGv_lottery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoLotteryActivity(dataBean, position);
            }

        });
    }

    /**
     * 根据点击彩种进行分发
     * 含有A盘的彩种进入A盘投注界面
     * 含有B盘的彩种进入B盘投注界面
     *
     * @param dataBean
     * @param position
     */

    void gotoLotteryActivity(TypeAndLotteryBean dataBean, int position) {
        if (dataBean == null) {
            return;
        }
        String type = dataBean.getTypeCode();
        List<TypeAndLotteryBean.LotteriesBean> lotteries = dataBean.getLotteries();
        if (lotteries == null) {
            return;
        }
        if (lotteries.size() == 0) {
            return;
        }

        // 根据彩种类型进入彩票的彩种投注界面
        TypeAndLotteryBean.LotteriesBean lb = lotteries.get(position);
        if (LotteryEnum.JSK3.getType().equals(type)) {
            // k3
            if (lb.getName().contains(OFFICIAL_PLAY)) {
                startActivity(K3AActivity.class, lb);
            } else if (lb.getName().contains(TRADITIONAL_PLAY)) {
                startActivity(K3BActivity.class, lb);
            }
        } else if (LotteryEnum.HKLHC.getType().equals(type)) {
            // lhc
            startActivity(HKSMActivity.class, lb);
        } else if (LotteryEnum.CQSSC.getType().equals(type)) {
            // ssc
            if (lb.getName().contains(OFFICIAL_PLAY)) {
                startActivity(SSCAActivity.class, lb);
            } else if (lb.getName().contains(TRADITIONAL_PLAY) || lb.getName().contains(ESPECIAL_LT_NAME)) {
                startActivity(SSCBActivity.class, lb);
            }
        } else if (LotteryEnum.BJPK10.getType().equals(type)) {
            // pk10
            if (lb.getName().contains(OFFICIAL_PLAY)) {
                startActivity(PK10AActivity.class, lb);
            } else if (lb.getName().contains(TRADITIONAL_PLAY) || LotteryEnum.JSPK10.getCode().equals(lb.getCode()) || LotteryEnum.XYFT.getCode().equals(lb.getCode())) {
                startActivity(PK10BActivity.class, lb);
            }
        } else if (LotteryEnum.CQXYNC.getType().equals(type)) {
            // sfc 重庆幸运农场 广东快乐十分
            startActivity(CqxyncActivity.class, lb);
        } else if (LotteryEnum.BJKL8.getType().equals(type) && LotteryEnum.BJKL8.getCode().equals(lb.getCode())) {
            // keno 北京快乐8
            startActivity(BJKL8Activity.class, lb);
        } else if (LotteryEnum.XY28.getType().equals(type) && LotteryEnum.XY28.getCode().equals(lb.getCode())) {
            // xy28 幸运28
            startActivity(XY28Activity.class, lb);

        } else if (LotteryEnum.FC3D.getType().equals(type)) {
            // pl3
            if (lb.getName().contains(OFFICIAL_PLAY)) {
                startActivity(QTAActivity.class, lb);
            } else if (lb.getName().contains(TRADITIONAL_PLAY)) {
                startActivity(QTBActivity.class, lb);
            }
        }
    }

    /**
     * 进入投注activity
     *
     * @param clazz
     * @param lb
     */
    public static void startActivity(Class clazz, TypeAndLotteryBean.LotteriesBean lb) {
        String name = lb.getName();
        String code = lb.getCode();
        ActivityUtil.startNoteActivity(clazz, name, code);
    }
}
