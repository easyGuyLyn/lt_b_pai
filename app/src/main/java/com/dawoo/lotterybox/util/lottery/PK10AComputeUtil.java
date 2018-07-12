package com.dawoo.lotterybox.util.lottery;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.util.List;

/**
 * Created by b on 18-4-10.
 * 计算pk10A盘 冷热遗漏
 */

public class PK10AComputeUtil {

    private List<Handicap> mHandicapList;
    private PlayTypeBean.PlayBean mPlayBean;

    public void setData(List<Handicap> mHandicapList) {
        this.mHandicapList = mHandicapList;
    }

    public void setPlayTypeBean(PlayTypeBean.PlayBean mPlayBean) {
        this.mPlayBean = mPlayBean;
    }


    /**
     * @param type true:冷热  false: 遗漏
     */
    public void compute(boolean type) {
        if (mHandicapList == null || mPlayBean == null)
            return;
        int[] positions = getPosition();
        String playTypeName = mPlayBean.getPlayTypeName();
        String mainTypeName = mPlayBean.getMainType();
        List<PlayTypeBean.PlayBean.LayoutBean> layoutBeans = mPlayBean.getLayoutBeans();
        for (PlayTypeBean.PlayBean.LayoutBean layoutBean : layoutBeans) {
            for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : layoutBean.getChildLayoutBeans()) {
                childLayoutBean.setNumberRelevant(0); //清理之前的数据
            }
        }

        for (int i = 0; i < mHandicapList.size(); i++) {
            String openCode = mHandicapList.get(i).getOpenCode();
            String[] strCodes = openCode.split(",");
            int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]),
                    Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4]),Integer.valueOf(strCodes[5]),
                    Integer.valueOf(strCodes[6]), Integer.valueOf(strCodes[7]),
                    Integer.valueOf(strCodes[8]), Integer.valueOf(strCodes[9])};
            for (int j = positions[0]; j < positions[1] + 1; j++) {
                int code = codes[j];
                if (type) {
                    PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = layoutBeans.get(j - positions[0]).getChildLayoutBeans().get(code-1);  //获取相对应的球实体
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                } else {
                    List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(j - positions[0]).getChildLayoutBeans();
                    setOmit(childLayoutBeans, code);
                }
            }
        }

    }

    //设置遗漏

    private void setOmit(List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans, int index) {
        for (int k = 0; k < childLayoutBeans.size(); k++) {
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(k);
            if (k == (index-1)) {
                if (childLayoutBean.getNumberRelevant() < 1000)
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000); //1000用作判断该球已出现，无需再叠加计算遗漏
            } else {
                if (childLayoutBean.getNumberRelevant() < 1000) {
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                }
            }
        }
    }

    private int[] getPosition(){
        int[] positions = new int[2];
        String mainType = mPlayBean.getMainType();
        if ("定胆位".equals(mainType)){
            positions[0] = 0;
            positions[1] = 9;
        }else if ("前一".equals(mainType)){
            positions[0] = 0;
            positions[1] = 0;
        }else if ("前二".equals(mainType)){
            positions[0] = 0;
            positions[1] = 1;
        }else if ("前三".equals(mainType)){
            positions[0] = 0;
            positions[1] = 2;
        }
        return positions;
    }
}
