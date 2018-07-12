package com.dawoo.lotterybox.view.activity.chart;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.GsonUtil;
import com.dawoo.lotterybox.view.view.PlayTypePopupWindow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图表头部的控件
 * Created by benson on 18-2-19.
 */

public class ChartHeaderView extends RelativeLayout {
    @BindView(R.id.iv_go_back)
    ImageView mIvGoBack;
    @BindView(R.id.tv_play_method)
    TextView mTvPlayMethod;
    @BindView(R.id.iv_right_menu)
    ImageView mIvRightMenu;
    @BindView(R.id.root_view)
    View mRootView;
    private Context mContext;
    private PlayTypePopupWindow mPlayTypePopupWindow;
    private PlayTypeBean.PlayBean mPlayTypeBean;
    private OnClickPlayTypeListener listener;

    public ChartHeaderView(Context context) {
        super(context);
        initUI(context);
    }

    public ChartHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public ChartHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.chart_header_view, this);
        ButterKnife.bind(this, view);

        initPlayTypePopWindow();
    }


    @OnClick({R.id.iv_go_back, R.id.tv_play_method, R.id.iv_right_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                ((Activity) mContext).finish();
                break;
            case R.id.tv_play_method:
                mPlayTypePopupWindow.doTogglePopupWindow(mRootView, mPlayTypeBean);
                break;
            case R.id.iv_right_menu:

                break;
        }
    }

    public void setTitleName(String name) {
        mTvPlayMethod.setText(name);
    }

    private void initPlayTypePopWindow() {
        List<PlayTypeBean> mList = GsonUtil.jsonToList(AssetsReader.getJson(mContext, AssetsReader.SSC_JSON_FILE), PlayTypeBean.class);
        mPlayTypeBean = mList.get(0).getPlayBeans().get(0);
        mPlayTypePopupWindow = new PlayTypePopupWindow(mContext, LotteryEnum.CQSSC.getType(),mList);
        mPlayTypePopupWindow.setOnClickPlayType(new PlayTypePopupWindow.OnClickPlayType() {
            @Override
            public void callBackTypeName(PlayTypeBean.PlayBean playTypeBean) {
                if (listener != null) {
                    listener.onHowToplayLClickListener(playTypeBean);
                }
            }
        });
    }

    public void setOnClickPlayTypeListener(OnClickPlayTypeListener listener) {
        this.listener = listener;
    }

    public interface OnClickPlayTypeListener {
        public void onHowToplayLClickListener(PlayTypeBean.PlayBean playTypeBean);
    }

    /**
     * 获取默认玩法
     *
     * @return
     */
    public PlayTypeBean.PlayBean getDefaultHowToPlay() {
        if (mPlayTypePopupWindow == null) {
            return null;
        }
        return mPlayTypePopupWindow.getDefaultPlayBean();
    }
}
