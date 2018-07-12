package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.play_style.PlayStyeParentAdapter;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.AssetsReader;
import com.dawoo.lotterybox.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archar on 18-
 */

public class PlayTypePopupWindow {

    private List<PlayTypeBean> mList = new ArrayList<>();
    private View mContentView;
    private PopupWindow mPopWindow = null;
    private RecyclerView mRecyclerView = null;
    private Context mContext;
    private PlayStyeParentAdapter mPlayStyeParentAdapter;
    private PlayTypePopupWindow mPlayTypePopupWindow;
    private OnClickPlayType mOnClickPlayType;
    private PlayTypeBean.PlayBean mDefaultPlayBean;

    public PlayTypePopupWindow(Context context, String type , List<PlayTypeBean> mList) {
        mContext = context;
        mPlayTypePopupWindow = this;
        if (mList != null){
            this.mList = mList;
        }else {
            initData(type);
        }
        mDefaultPlayBean = mList.get(0).getPlayBeans().get(0);
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(R.layout.layout_play_style_popwindow, null);
            mRecyclerView = mContentView.findViewById(R.id.rcv_play);
            mPlayStyeParentAdapter = new PlayStyeParentAdapter(mContext, mList, mPlayTypePopupWindow, mRecyclerView);
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mPlayStyeParentAdapter);
            refeshSelectData(mDefaultPlayBean);
        }
        if (mPopWindow == null) {
            mPopWindow = new CurrentShowAsDropPopWindow(mContentView, RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT, true);
            mPopWindow.setTouchable(true);
            mPopWindow.setOutsideTouchable(true);
        }
    }

    /**
     * 初始化数据源
     * @param type
     */
    void initData(String type) {
       if(LotteryEnum.CQSSC.getType().equals(type)) {
           // 时时彩 重庆时时彩 ssc
           mList = GsonUtil.jsonToList(AssetsReader.getJson(mContext, AssetsReader.SSC_JSON_FILE), PlayTypeBean.class);

       } else if(LotteryEnum.HKLHC.getType().equals(type)) {
           // 六合彩 香港六合彩 lhc

       } else if(LotteryEnum.JSK3.getType().equals(type)) {
           // 江苏快3 k3

       }else if(LotteryEnum.BJPK10.getType().equals(type)) {
           // 北京PK10 pk10

       }else if(LotteryEnum.CQXYNC.getType().equals(type)) {
           // 重庆幸运农场 广东快乐十分 sfc

       }else if(LotteryEnum.BJKL8.getType().equals(type)) {
           // 北京快乐8 北京快乐8 keno

       }else if(LotteryEnum.XY28.getType().equals(type)) {
           // 幸运28 幸运28

       }else if(LotteryEnum.FC3D.getType().equals(type)) {
            // 福彩3D pl3

       }
    }


    public void refeshSelectData(PlayTypeBean.PlayBean currentPlayType) {
        mDefaultPlayBean = currentPlayType;
        for (PlayTypeBean playBean : mList) {
            for (PlayTypeBean.PlayBean playTypeBean : playBean.getPlayBeans()) {
                if (playTypeBean.getPlayTypeName().equals(currentPlayType.getPlayTypeName())
                        && playTypeBean.getMainType().equals(currentPlayType.getMainType())
                        && playTypeBean.getScheme().equals(currentPlayType.getScheme())) {
                    playTypeBean.setSelected(true);
                } else {
                    playTypeBean.setSelected(false);
                }
            }
        }
        if (mPlayStyeParentAdapter != null) {
            mPlayStyeParentAdapter.notifyDataSetChanged();
        }
    }

    public void doTogglePopupWindow(View view, PlayTypeBean.PlayBean currentPlayType) {
        if (mPopWindow == null) {
            return;
        }
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            refeshSelectData(currentPlayType);
            mPopWindow.showAsDropDown(view);
        }
    }

    public void dissMissPopWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }

    public void dissMissPopAndCallBack(PlayTypeBean.PlayBean name) {
        if (mOnClickPlayType != null) {
            mOnClickPlayType.callBackTypeName(name);
        }
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }

    public PlayTypeBean.PlayBean getDefaultPlayBean() {
        return mDefaultPlayBean;
    }

    public void setOnClickPlayType(OnClickPlayType onClickPlayType) {
        mOnClickPlayType = onClickPlayType;
    }

    public interface OnClickPlayType {
        void callBackTypeName(PlayTypeBean.PlayBean name);
    }

}
