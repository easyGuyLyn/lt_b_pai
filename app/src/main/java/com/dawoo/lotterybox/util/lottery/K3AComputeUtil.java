package com.dawoo.lotterybox.util.lottery;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by b on 18-4-10.
 * 计算k3A盘 冷热遗漏
 */

public class K3AComputeUtil {

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
        String mainTypeName = mPlayBean.getMainType();
        List<PlayTypeBean.PlayBean.LayoutBean> layoutBeans = mPlayBean.getLayoutBeans();
        for (PlayTypeBean.PlayBean.LayoutBean layoutBean : layoutBeans) {
            for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : layoutBean.getChildLayoutBeans()) {
                childLayoutBean.setNumberRelevant(0); //清理之前的数据
            }
        }
        if ("三同号通选".equals(mainTypeName) || "三同号单选".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(0);
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2])};
                boolean isSlh = true;  //是否三同号
                int nowNum = 0;
                for (int j = 0; j < codes.length; j++) {
                    if (j != 0) {
                        if (nowNum != codes[j]) {
                            isSlh = false;
                        }
                    }
                    nowNum = codes[j];
                }

                if ("三同号单选".equals(mainTypeName))
                    childLayoutBean = childLayoutBeans.get(nowNum - 1);
                if (type) {
                    if (isSlh) {
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                    }
                } else {
                    if (isSlh) {
                        if (childLayoutBean.getNumberRelevant() < 1000)
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);

                    } else {
                        if (childLayoutBean.getNumberRelevant() < 1000) {
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        }
                    }
                    if ("三同号单选".equals(mainTypeName)) {
                        for (int j = 0; j < childLayoutBeans.size(); j++) {
                            if (j != (nowNum - 1)) {
                                if (childLayoutBeans.get(j).getNumberRelevant() < 1000) {
                                    childLayoutBeans.get(j).setNumberRelevant(childLayoutBeans.get(j).getNumberRelevant() + 1);
                                }
                            }
                        }
                    }
                }
            }

        } else if ("二同号复选".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean;
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                List<Integer> sortNums = new ArrayList<>();
                sortNums.add(Integer.valueOf(strCodes[0]));
                sortNums.add(Integer.valueOf(strCodes[1]));
                sortNums.add(Integer.valueOf(strCodes[2]));
                Collections.sort(sortNums);
                boolean isDuo = false;
                int duoNumber = -1;
                int nowNum = 0;
                for (int j = 0; j < sortNums.size(); j++) {
                    if (j != 0) {
                        if (nowNum == sortNums.get(j)) {
                            if (isDuo)
                                isDuo = false;
                            else {
                                isDuo = true;
                                duoNumber = sortNums.get(j);
                            }
                        }
                    }
                    nowNum = sortNums.get(j);
                }

                if (type) {
                    if (isDuo) {
                        childLayoutBean = childLayoutBeans.get(duoNumber - 1);
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                    }
                } else {
                    for (int j = 0; j < childLayoutBeans.size() ; j ++ ) {
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean1 = childLayoutBeans.get(j);
                        if (isDuo){
                            if (j == duoNumber-1){
                                if (childLayoutBean1.getNumberRelevant() < 1000) {
                                    childLayoutBean1.setNumberRelevant(childLayoutBean1.getNumberRelevant() + 1000);
                                    continue;
                                }
                            }
                        }
                        if (childLayoutBean1.getNumberRelevant() < 1000)
                            childLayoutBean1.setNumberRelevant(childLayoutBean1.getNumberRelevant() + 1);
                    }
                }
            }

        } else if ("二同号单选".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans2 = layoutBeans.get(1).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean;  //同号
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean2; //不同号
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                List<Integer> sortNums = new ArrayList<>();
                sortNums.add(Integer.valueOf(strCodes[0]));
                sortNums.add(Integer.valueOf(strCodes[1]));
                sortNums.add(Integer.valueOf(strCodes[2]));
                Collections.sort(sortNums);
                boolean isDuo = false;
                int duoNumber = -1;
                int singleNumber = -1;
                int nowNum = 0;
                for (int j = 0; j < sortNums.size(); j++) {
                    if (j != 0) {
                        if (nowNum == sortNums.get(j)) {
                            if (isDuo) {
                                isDuo = false;
                            } else {
                                isDuo = true;
                                duoNumber = sortNums.get(j);
                                if (j == 1)
                                    singleNumber = sortNums.get(2);
                                else singleNumber = sortNums.get(0);
                            }
                        }
                    }
                    nowNum = sortNums.get(j);
                }
                if (type) {
                    if (isDuo) {
                        childLayoutBean = childLayoutBeans.get(duoNumber - 1);
                        childLayoutBean2 = childLayoutBeans2.get(singleNumber - 1);
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        childLayoutBean2.setNumberRelevant(childLayoutBean2.getNumberRelevant() + 1);
                    }
                } else {
                    for (int j = 0; j < childLayoutBeans.size() ; j ++ ) {
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean3 = childLayoutBeans.get(j);
                        if (isDuo){
                            if (j == duoNumber-1){
                                if (childLayoutBean3.getNumberRelevant() < 1000) {
                                    childLayoutBean3.setNumberRelevant(childLayoutBean3.getNumberRelevant() + 1000);
                                    continue;
                                }
                            }
                        }
                        if (childLayoutBean3.getNumberRelevant() < 1000)
                            childLayoutBean3.setNumberRelevant(childLayoutBean3.getNumberRelevant() + 1);
                    }
                    for (int j = 0; j < childLayoutBeans2.size() ; j ++ ) {
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean4 = childLayoutBeans2.get(j);
                        if (isDuo){
                            if (j == singleNumber-1){
                                if (childLayoutBean4.getNumberRelevant() < 1000) {
                                    childLayoutBean4.setNumberRelevant(childLayoutBean4.getNumberRelevant() + 1000);
                                    continue;
                                }
                            }
                        }
                        if (childLayoutBean4.getNumberRelevant() < 1000)
                            childLayoutBean4.setNumberRelevant(childLayoutBean4.getNumberRelevant() + 1);
                    }
                }
            }
        } else if ("三不同号".equals(mainTypeName) || "二不同号".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean;
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2])};
                List<Integer> singleNums = new ArrayList<>();
                for (int j = 0; j < codes.length; j++) {
                    int code = codes[j];
                    if (!singleNums.contains(code)) {  //去重
                        singleNums.add(code);
                        if (type) {
                            childLayoutBean = childLayoutBeans.get(code - 1);
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        }
                    }
                }
                if (!type) {
                    for (int h = 0; h < childLayoutBeans.size(); h++) {
                        childLayoutBean = childLayoutBeans.get(h);
                        if (!singleNums.contains(h + 1)) {
                            if (childLayoutBean.getNumberRelevant() < 1000) {
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                            }
                        } else {
                            if (childLayoutBean.getNumberRelevant() < 1000) {
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                            }
                        }
                    }
                }

            }

        } else if ("三连号通选".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(0);
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                List<Integer> sortNums = new ArrayList<>();
                sortNums.add(Integer.valueOf(strCodes[0]));
                sortNums.add(Integer.valueOf(strCodes[1]));
                sortNums.add(Integer.valueOf(strCodes[2]));
                Collections.sort(sortNums);
                boolean isStraight = true;
                int nowNum = 0;
                for (int j = 0; j < sortNums.size(); j++) {
                    if (j != 0) {
                        if ((nowNum + 1) != sortNums.get(j))
                            isStraight = false;
                    }
                    nowNum = sortNums.get(j);
                }
                if (type) {
                    if (isStraight) {
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                    }
                } else {
                    if (isStraight) {
                        if (childLayoutBean.getNumberRelevant() < 1000)
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                    } else {
                        if (childLayoutBean.getNumberRelevant() < 1000) {
                            childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        }
                    }
                }
            }
        }
    }
}
