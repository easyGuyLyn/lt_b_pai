package com.dawoo.lotterybox.util;

import android.content.Context;

import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by archar on 18-2-12.
 * <p>
 * 玩法说明界面 对玩法数据的分组构造,构造成想要的数据模型
 */

public class JsonToPlayExplainBean {

    public static Map<String, List<PlayTypeBean.PlayBean>> getPlayBeansFromJson(Context mContext,String fileName) {
        List<PlayTypeBean> mList = GsonUtil.jsonToList(AssetsReader.getJson(mContext,fileName),PlayTypeBean.class);
        List<PlayTypeBean.PlayBean> playTypeBeans = new ArrayList<>();
        for (PlayTypeBean playTypeBean:mList){
            playTypeBeans.addAll(playTypeBean.getPlayBeans());
        }

        LinkedHashMap<String, List<PlayTypeBean.PlayBean>> MainTypeMap = new LinkedHashMap<>();
        for (PlayTypeBean.PlayBean playTypeBean : playTypeBeans) {
            List<PlayTypeBean.PlayBean> tempList = MainTypeMap.get(playTypeBean.getMainType());
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(playTypeBean);
                MainTypeMap.put(playTypeBean.getMainType(), tempList);
            } else {
                tempList.add(playTypeBean);
            }
        }
        return MainTypeMap;

    }


}
