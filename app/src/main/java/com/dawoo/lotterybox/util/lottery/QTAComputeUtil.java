package com.dawoo.lotterybox.util.lottery;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-4-11.
 * 福彩、体彩A盘  冷热遗漏计算
 */

public class QTAComputeUtil {

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
        if ("直选复式".equals(playTypeName) || "定胆位".equals(playTypeName)){
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2])};
                for (int j = positions[0]; j < positions[1] + 1; j++) {
                    int code = codes[j];
                    if (type) {
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = layoutBeans.get(j - positions[0]).getChildLayoutBeans().get(code);  //获取相对应的球实体
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                    } else {
                        List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(j - positions[0]).getChildLayoutBeans();
                        setOmit(childLayoutBeans, code);
                    }
                }
            }

        }else if ("直选和值".equals(playTypeName) || "组选和值".equals(playTypeName)){
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2])};
                int andValue = 0;  //和值
                boolean isLeopard = false;  //组合是否为豹子
                int recordNum = 0;  //记录号码,对比是否为豹子
                for (int j = positions[0]; j < positions[1] + 1; j++) {
                    int code = codes[j];
                    if (j == positions[0]) {
                        recordNum = code;
                    } else {
                        if (recordNum == code) {
                            isLeopard = true;
                        } else {
                            isLeopard = false;
                        }
                    }
                    andValue += code;

                }
                if (type) {
                    if (isLeopard) {
                        if ("组选和值".equals(playTypeName))
                            continue;
                    }
                    PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = layoutBeans.get(0).getChildLayoutBeans().get(andValue);  //获取相对应的球实体
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                } else {
                    if (isLeopard) {
                        if ("组选和值".equals(playTypeName)) {  //不记录豹子，所以全体加1
                            for (int k = 0; k < childLayoutBeans.size(); k++) {
                                childLayoutBeans.get(k).setNumberRelevant(childLayoutBeans.get(k).getNumberRelevant() + 1);
                            }
                            continue;
                        }
                    }
                    setOmit(childLayoutBeans, andValue);
                }

            }
        }else if ("组三复式".equals(playTypeName) || "组六复式".equals(playTypeName) || "组选复式".equals(playTypeName) || "三星一码".equals(playTypeName)){
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean;
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2])};
                List<Integer> singleNums = new ArrayList<>();
                for (int j = positions[0]; j < positions[1] + 1; j++) {
                    int code = codes[j];
                    if (!singleNums.contains(code)) {  //去重
                        singleNums.add(code);
                        if (type) {
                            childLayoutBean = childLayoutBeans.get(code);
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        }
                    }
                }
                if (!type) {
                    for (int h = 0; h < childLayoutBeans.size(); h++) {
                        childLayoutBean = childLayoutBeans.get(h);
                        if (!singleNums.contains(h)) {
                            if (childLayoutBean.getNumberRelevant() < 1000) {
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                            }
                        }else {
                            if (childLayoutBean.getNumberRelevant() < 1000){
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                            }
                        }
                    }
                }
            }
        }

    }

    //设置遗漏
    private void setOmit(List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans, int index) {
        for (int k = 0; k < childLayoutBeans.size(); k++) {
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(k);
            if (k == index) {
                if (childLayoutBean.getNumberRelevant() < 1000)
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000); //1000用作判断该球已出现，无需再叠加计算遗漏
            } else {
                if (childLayoutBean.getNumberRelevant() < 1000) {
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                }
            }
        }
    }

    //定位具体的球计算组合位置
    private int[] getPosition() {
        int[] positions = new int[2];
        String mainType = mPlayBean.getMainType();
        if ("定胆位".equals(mainType) || "三星".equals(mainType) || "不定位".equals(mainType)){
            positions[0] = 0;
            positions[1] = 2;
        }else if ("前二".equals(mainType)){
            positions[0] = 0;
            positions[1] = 1;
        }else if ("后二".equals(mainType)){
            positions[0] = 1;
            positions[1] = 2;
        }
        return positions;
    }
}
