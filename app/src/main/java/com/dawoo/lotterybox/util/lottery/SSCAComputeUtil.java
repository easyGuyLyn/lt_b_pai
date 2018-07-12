package com.dawoo.lotterybox.util.lottery;

import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by b on 18-4-9.
 * 计算时时彩A盘 冷热，遗漏
 */

public class SSCAComputeUtil {

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

        if ("直选复式".equals(playTypeName) || "定胆位".equals(playTypeName) || playTypeName.endsWith("组合")) {

            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]), Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4])};
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

        } else if ("直选和值".equals(playTypeName) || "组选和值".equals(playTypeName) || "和值尾数".equals(playTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]), Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4])};
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
                    if ("和值尾数".equals(playTypeName)) {
                        int endNum = andValue % 10;
                        PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(endNum);
                        childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
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
                    if ("和值尾数".equals(playTypeName)) {
                        int endNum = andValue % 10;
                        setOmit(childLayoutBeans, endNum);
                        continue;
                    }
                    setOmit(childLayoutBeans, andValue);
                }

            }
        } else if ("直选跨度".equals(playTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]), Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4])};
                int minNum = 0;
                int maxNum = 0;
                for (int j = positions[0]; j < positions[1] + 1; j++) {
                    int code = codes[j];
                    if (j == positions[0]) {
                        minNum = code;
                        maxNum = code;
                    } else {
                        if (minNum > code)
                            minNum = code;
                        if (maxNum < code)
                            maxNum = code;
                    }
                }
                int span = maxNum - minNum;
                if (type) {
                    PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean = childLayoutBeans.get(span);  //获取相对应的球实体
                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                } else {
                    setOmit(childLayoutBeans, span);
                }

            }

        } else if ("组三复式".equals(playTypeName) || "组六复式".equals(playTypeName) || "组选包胆".equals(playTypeName) || "不定位".equals(mainTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();
            PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean;
            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]), Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4])};
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
                        } else {
                            if (childLayoutBean.getNumberRelevant() < 1000) {
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                            }
                        }
                    }
                }
            }

        } else if ("特殊号".equals(playTypeName)) {
            List<PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean> childLayoutBeans = layoutBeans.get(0).getChildLayoutBeans();

            for (int i = 0; i < mHandicapList.size(); i++) {
                String openCode = mHandicapList.get(i).getOpenCode();
                String[] strCodes = openCode.split(",");
                int[] codes = {Integer.valueOf(strCodes[0]), Integer.valueOf(strCodes[1]), Integer.valueOf(strCodes[2]), Integer.valueOf(strCodes[3]), Integer.valueOf(strCodes[4])};
                boolean isLeopard = true;   //组合是否为豹子
                boolean isDou = false;       //组合是否为对子
                boolean isStraight = true;  //组合是否为顺子
                int recordNum = 0;           //记录上一个号码
                String straight = "";        //拼接结果
                List<Integer> sortNums = new ArrayList<>();
                for (int j = positions[0]; j < positions[1] + 1; j++) {
                    int code = codes[j];
                    sortNums.add(code);
                }
                Collections.sort(sortNums);  //排序
                for (int j = 0; j < sortNums.size(); j++) {
                    int code = sortNums.get(j);
                    straight = straight + code;
                    if (j != 0) {
                        if (recordNum == code) {
                            if (isLeopard)
                                isLeopard = true;
                            else isLeopard = false;
                            isDou = true;
                        } else {
                            isLeopard = false;
                        }
                        if ((recordNum + 1) != code)
                            isStraight = false;
                    }
                    if (j == (sortNums.size() - 1)) {
                        if (isLeopard)
                            isDou = false;
                    }
                    recordNum = code;
                }
                if ("019".equals(straight) || "089".equals(straight))
                    isStraight = true;

                for (PlayTypeBean.PlayBean.LayoutBean.ChildLayoutBean childLayoutBean : childLayoutBeans) {
                    if (type) {
                        if ("豹子".equals(childLayoutBean.getNumber())) {
                            if (isLeopard) {
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                            }

                        } else if ("顺子".equals(childLayoutBean.getNumber())) {
                            if (isStraight)
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        } else if ("对子".equals(childLayoutBean.getNumber())) {
                            if (isDou)
                                childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                        }
                    } else {
                        if ("豹子".equals(childLayoutBean.getNumber())) {
                            if (isLeopard) {
                                if (childLayoutBean.getNumberRelevant() < 1000) {
                                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                                }
                            } else {
                                if (childLayoutBean.getNumberRelevant() < 1000) {
                                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                                }
                            }
                        } else if ("顺子".equals(childLayoutBean.getNumber())) {
                            if (isStraight) {
                                if (childLayoutBean.getNumberRelevant() < 1000) {
                                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                                }
                            } else {
                                if (childLayoutBean.getNumberRelevant() < 1000) {
                                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1);
                                }
                            }
                        } else if ("对子".equals(childLayoutBean.getNumber())) {
                            if (isDou) {
                                if (childLayoutBean.getNumberRelevant() < 1000) {
                                    childLayoutBean.setNumberRelevant(childLayoutBean.getNumberRelevant() + 1000);
                                }
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
        if ("后三".equals(mainType)) {
            positions[0] = 2;
            positions[1] = 4;
        } else if ("前三".equals(mainType)) {
            positions[0] = 0;
            positions[1] = 2;
        } else if ("五星".equals(mainType) || "定胆位".equals(mainType)) {
            positions[0] = 0;
            positions[1] = 4;
        } else if ("四星".equals(mainType)) {
            positions[0] = 1;
            positions[1] = 4;
        } else if ("前二".equals(mainType)) {
            positions[0] = 0;
            positions[1] = 1;
        } else if ("不定位".equals(mainType)) {
            String playTypeName = mPlayBean.getPlayTypeName();
            if (playTypeName.startsWith("前三")) {
                positions[0] = 0;
                positions[1] = 2;
            } else if (playTypeName.startsWith("后三")) {
                positions[0] = 2;
                positions[1] = 4;
            } else if (playTypeName.startsWith("前四")) {
                positions[0] = 0;
                positions[1] = 3;
            } else if (playTypeName.startsWith("后四")) {
                positions[0] = 1;
                positions[1] = 4;
            } else {
                positions[0] = 0;
                positions[1] = 4;
            }
        }
        return positions;
    }

}
