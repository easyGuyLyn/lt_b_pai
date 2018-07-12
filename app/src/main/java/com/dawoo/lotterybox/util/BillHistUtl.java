package com.dawoo.lotterybox.util;

import com.dawoo.lotterybox.bean.record.BillItemBean;
import com.dawoo.lotterybox.bean.record.recordnum.BaseSelectedBean;
import com.dawoo.lotterybox.bean.record.recordnum.BillDepositStatusEnum;
import com.dawoo.lotterybox.bean.record.recordnum.BillItemEnum;
import com.dawoo.lotterybox.bean.record.recordnum.BillTypeEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rain on 18-4-19.
 */

public class BillHistUtl {

    public static String getStatus(String code) {
        for (BillDepositStatusEnum statusEnum : BillDepositStatusEnum.values()) {
            if (statusEnum.getCode().equalsIgnoreCase(code)) {
                return statusEnum.getTrans();
            }
        }
        return "未知";
    }

    /**
     * getparent
     *
     * @param code
     * @return
     */
    public static List<BaseSelectedBean> getParentByCode(String code) {
        List<BaseSelectedBean> mlist = new ArrayList<>();
        BaseSelectedBean first = new BaseSelectedBean();
        first.setCode("all");
        first.setName("全部");
        mlist.add(first);
        for (BillTypeEnum billTypeEnum : BillTypeEnum.values()) {

            if (code.isEmpty() || code.equalsIgnoreCase("all")) {
                BaseSelectedBean m = new BaseSelectedBean();
                m.setCode(billTypeEnum.getCode());
                m.setName(billTypeEnum.getTrans());
                mlist.add(m);
            } else if (billTypeEnum.getParent().getCode().equalsIgnoreCase(code)) {
                BaseSelectedBean m = new BaseSelectedBean();
                m.setCode(billTypeEnum.getCode());
                m.setName(billTypeEnum.getTrans());
                mlist.add(m);
            }

        }
        return mlist;
    }

    public static String getParentName(String code) {
        for (BillTypeEnum typeEnum : BillTypeEnum.values()) {
            if (code.equalsIgnoreCase(typeEnum.getCode())) {
                return typeEnum.getTrans();
            }
        }
        return "资金记录";
    }

    public static String getChildName(String code) {
        for (BillItemEnum typeEnum : BillItemEnum.values()) {
            if (code.equalsIgnoreCase(typeEnum.getCode())) {
                return typeEnum.getTrans();
            }
        }
        return "资金记录";
    }

    /**
     * getChild
     *
     * @param code
     * @return
     */
    public static List<BaseSelectedBean> getChildByCode(String parentCode, String code) {
        List<BaseSelectedBean> mlist = new ArrayList<>();
        BaseSelectedBean first = new BaseSelectedBean();
        first.setCode("all");
        first.setName("全部");
        mlist.add(first);
        for (BillItemEnum billTypeEnum : BillItemEnum.values()) {
            if (parentCode.isEmpty() || parentCode.equalsIgnoreCase("all")) {
                if (code.isEmpty() || code.equalsIgnoreCase("all")) {
                    BaseSelectedBean m = new BaseSelectedBean();
                    m.setCode(billTypeEnum.getCode());
                    m.setName(billTypeEnum.getTrans());
                    mlist.add(m);
                } else if (billTypeEnum.getParent().getCode().equals(code)) {
                    BaseSelectedBean m = new BaseSelectedBean();
                    m.setCode(billTypeEnum.getCode());
                    m.setName(billTypeEnum.getTrans());
                    mlist.add(m);
                }
            } else if (billTypeEnum.getParent().getParent().getCode().equalsIgnoreCase(parentCode)) {
                if (code.isEmpty() || code.equalsIgnoreCase("all")) {
                    BaseSelectedBean m = new BaseSelectedBean();
                    m.setCode(billTypeEnum.getCode());
                    m.setName(billTypeEnum.getTrans());
                    mlist.add(m);
                } else if (billTypeEnum.getParent().getCode().equals(code)) {
                    BaseSelectedBean m = new BaseSelectedBean();
                    m.setCode(billTypeEnum.getCode());
                    m.setName(billTypeEnum.getTrans());
                    mlist.add(m);
                }
            }

        }
        return mlist;
    }


    public synchronized static void sethowHistory(List<BillItemBean> allDatas,
                                                  boolean balanceSort, boolean timeSort) {
        if (!allDatas.isEmpty()) {
            sortData(allDatas, balanceSort, timeSort);
        }
    }

    static void sortData(List<BillItemBean> showDatas, boolean balanceSort, boolean timeSort) {

        sortFormBalance(balanceSort, showDatas);

        sortFormTime(timeSort, showDatas);

    }

    public static void sortFormBalance(boolean balanceSort, List<BillItemBean> showDatas) {
        Collections.sort(showDatas, new Comparator<BillItemBean>() {
            @Override
            public int compare(BillItemBean o1, BillItemBean o2) {
                double o1Balance;
                double o2Balance;
                if (o1.getBalance() == null) {
                    o1Balance = Double.parseDouble(o1.getMoney().replaceAll(",", ""));
                    o2Balance = Double.parseDouble(o2.getMoney().replaceAll(",", ""));
                } else {
                    o1Balance = Double.parseDouble(o1.getBalance().replaceAll(",", ""));
                    o2Balance = Double.parseDouble(o2.getBalance().replaceAll(",", ""));
                }
                if (balanceSort) {
                    if (o1Balance > o2Balance) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (o1Balance < o2Balance) {
                        return -1;
                    }
                    return 0;
                }
            }
        });
    }

    public static void sortFormTime(boolean timeSort, List<BillItemBean> showDatas) {
        Collections.sort(showDatas, new Comparator<BillItemBean>() {
            @Override
            public int compare(BillItemBean o1, BillItemBean o2) {

                if (timeSort) {
                    if (o1.getCompletionTime() == 0) {
                        if (o1.getCompletionTime() > o2.getCompletionTime()) {
                            return 0;
                        }
                    } else {
                        if (o1.getCreateTime() > o2.getCreateTime()) {
                            return 0;
                        }
                    }
                    return -1;
                } else {
                    if (o1.getCompletionTime() == 0) {
                        if (o1.getCompletionTime() < o2.getCompletionTime()) {
                            return 0;
                        }
                    } else {
                        if (o1.getCreateTime() < o2.getCreateTime()) {
                            return 0;
                        }
                    }
                    return -1;
                }
            }
        });
    }

    public static void sortFromStatus(boolean status, List<BillItemBean> showDatas) {
        Collections.sort(showDatas, new Comparator<BillItemBean>() {
            @Override
            public int compare(BillItemBean o1, BillItemBean o2) {
                String o1Status = o1.getStatus();
                String o2Status = o2.getStatus();
                if (status) {
                    return o1Status.compareTo(o2Status);
                } else {
                    return -o1Status.compareTo(o2Status);
                }
            }
        });
    }

    public synchronized static void sortListData(List<BillItemBean> showDatas, boolean balance, boolean status, boolean time) {

        sortFormBalance(balance, showDatas);
        sortFromStatus(status, showDatas);
        sortFormTime(time, showDatas);
    }
}
