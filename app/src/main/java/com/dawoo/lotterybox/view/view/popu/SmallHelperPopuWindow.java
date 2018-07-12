package com.dawoo.lotterybox.view.view.popu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.lottery.LotteryBPlayTypeExplainActivity;
import com.dawoo.lotterybox.view.activity.NoteRcdActivity;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3BActivity;
import com.dawoo.lotterybox.view.activity.lottery.keno.BJKL8Activity;
import com.dawoo.lotterybox.view.activity.lottery.keno.XY28Activity;
import com.dawoo.lotterybox.view.activity.lottery.pk10.PK10BActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTBActivity;
import com.dawoo.lotterybox.view.activity.record.CharseNumRecordFragment;
import com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment;
import com.dawoo.lotterybox.view.activity.record.RecentOpenRecActivity;
import com.dawoo.lotterybox.view.activity.lottery.sfc.CqxyncActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCWayPaperActivity;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by archar on 18-3-12.
 */

public class SmallHelperPopuWindow extends PopupWindow {
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_recent_awards)
    TextView mTvRecentAwards;
    @BindView(R.id.tv_my_bet)
    TextView mTvMyBet;
    @BindView(R.id.tv_my_chase_number)
    TextView mTvMyChaseNumber;
    @BindView(R.id.tv_trend_chart)
    TextView mTvTrendChart;
    @BindView(R.id.tv_lzt_pop)
    LinearLayout mTvLztPop;
    @BindView(R.id.line_lzt)
    View lineLZT;
    @BindView(R.id.tv_wf_pop)
    TextView mTvWfPop;
    private Context mContext;
    private String mCode;//彩种code
    private ArrayList<Handicap> mHandicaps = new ArrayList<>();//120期数据


    public void setHandicaps(List<Handicap> handicaps) {
        mHandicaps.clear();
        mHandicaps.addAll(handicaps);
    }

    public SmallHelperPopuWindow(Context context, String code) {
        super();
        mContext = context;
        mCode = code;
        View popView = LayoutInflater.from(mContext).inflate(R.layout.layout_ssc_head_assistant_popupwindow, null);
        setContentView(popView);
        ButterKnife.bind(this, popView);
        if (mContext instanceof K3BActivity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof PK10BActivity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof BJKL8Activity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof XY28Activity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof QTBActivity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof CqxyncActivity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        } else if (mContext instanceof HKSMActivity) {
            mTvLztPop.setVisibility(View.GONE);
            lineLZT.setVisibility(View.GONE);
        }
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity) mContext).getWindow().setAttributes(lp);
            }
        });
        setBackgroundDrawable(new BitmapDrawable());
    }

    public void doTogglePopupWindow(View view) {
        if (this == null) {
            return;
        }
        if (this.isShowing()) {
            this.dismiss();
        } else {
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
            lp.alpha = 0.7f;
            lp.gravity = Gravity.RIGHT;
            ((Activity) mContext).getWindow().setAttributes(lp);
            changeBalance();
            this.showAsDropDown(view);
        }
    }

    public void dissMissPopWindow() {
        if (this != null && this.isShowing()) {
            this.dismiss();
        }
    }


    @OnClick({R.id.tv_recent_awards, R.id.tv_my_bet, R.id.tv_my_chase_number, R.id.tv_trend_chart, R.id.tv_lzt_pop, R.id.tv_wf_pop})
    public void onViewClicked(View view) {
        if (TextUtils.isEmpty(mCode)) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_recent_awards:
                RecentOpenRecActivity.goRecentOPenRecActivity(mContext, mCode);
                break;
            case R.id.tv_my_bet:
                NoteRcdActivity.goRcordActivity(mContext, HistoryRepotFormFragment.class.getSimpleName());
                break;
            case R.id.tv_my_chase_number:
                NoteRcdActivity.goRcordActivity(mContext, CharseNumRecordFragment.class.getSimpleName());
                break;
            case R.id.tv_trend_chart:
                if (null == mHandicaps || mHandicaps.size() == 0) {
                    SingleToast.showMsg("没有获取到开奖数据");
                    return;}
                dissMissPopWindow();
                ActivityUtil.startChartActivity(mContext, mHandicaps, mCode);
                break;
            case R.id.tv_lzt_pop:
                SSCWayPaperActivity.goLztActivity(mContext, mCode);
                break;
            case R.id.tv_wf_pop:
                LotteryBPlayTypeExplainActivity.goWfActivity(mContext, mCode);
                break;
        }
    }

    /**
     * 在每次的show方法钱调用，保证准确性
     */
    public void changeBalance() {
        if (DataCenter.getInstance().isLogin()) {
            mTvBalance.setText(BalanceUtils.getScalsBalance(DataCenter.getInstance().getUser().getBalance()));
            mTvUserName.setText(DataCenter.getInstance().getUser().getUsername());
        } else {
            mTvBalance.setText("0.00");
        }
    }
}
