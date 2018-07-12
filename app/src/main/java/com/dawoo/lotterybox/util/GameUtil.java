package com.dawoo.lotterybox.util;

import android.util.Log;

import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.bean.record.CancelNumRecordHis;
import com.dawoo.lotterybox.bean.record.CancelRecordHisData;
import com.dawoo.lotterybox.bean.record.ChaseNumRecordHis;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.status;

/**
 * Created by archar on 18-2-18.
 */

public class GameUtil {


    /**
     * 根据投注记录页面 : 时间, 彩种 ,状态
     * 过滤数据该页面数据
     *
     * @param hisListShow
     */
    public synchronized static List<NoteRecordHis> recordNoteFilter(
            List<NoteRecordHis> hisListShow,
            List<NoteRecordHis> allList,
            boolean noteSort,
            boolean profitSort,
            boolean timeSort) {
        hisListShow.clear();
        hisListShow.addAll(allList);
        if (hisListShow.size() > 0) {
            sortData(noteSort, profitSort, timeSort, hisListShow);
        }

        return hisListShow;
    }


    /**
     * 根据追号记录页面 : 时间, 彩种 ,状态
     * 过滤数据该页面数据
     *
     * @param hisListShow
     */
    public synchronized static List<ChaseNumRecordHis> recordChaseNumNoteFilter(
            List<ChaseNumRecordHis> hisListShow,
            List<ChaseNumRecordHis> hisList,
            String showStartDate,
            String showEndDate,
            String code,
            String statu,
            boolean noteSort,
            boolean profitSort,
            boolean timeSort) {

        hisListShow.clear();
        long startTime = DateTool.getLongFromTime(DateTool.FMT_DATE, showStartDate);
        long endTime = DateTool.getLongFromTime(DateTool.FMT_DATE, showEndDate);
        for (ChaseNumRecordHis noteRecordHis : hisList) {
            String noTime = DateTool.getTimeFromLong(DateTool.FMT_DATE, noteRecordHis.getBetTime());
            long time = DateTool.getLongFromTime(DateTool.FMT_DATE, noTime);
            if (time >= startTime && time <= endTime) {
                if (code.equals("")) {
                    if (statu == status[0]) {
                        hisListShow.add(noteRecordHis);
                    } else {
                        if (noteRecordHis.getStatus().equals(statu)) {
                            hisListShow.add(noteRecordHis);
                        }

                    }
                } else {
                    if (noteRecordHis.getCode().equals(code)) {
                        if (statu == status[0]) {
                            hisListShow.add(noteRecordHis);
                        } else {
                            if (noteRecordHis.getStatus().equals(statu)) {
                                hisListShow.add(noteRecordHis);
                            }

                        }
                    }
                }
            }
        }
        if (hisListShow.size() > 0) {
            sortData1(noteSort, profitSort, timeSort, hisListShow);
        }

        return hisListShow;
    }


    /**
     * 投注记录排序
     *
     * @param noteSort
     * @param profitSort
     * @param timeSort
     * @param hisListShow
     */
    public static void sortData(boolean noteSort,
                                boolean profitSort,
                                boolean timeSort,
                                List<NoteRecordHis> hisListShow) {


        sortFormNote(noteSort, hisListShow);

        sortFormProfit(profitSort, hisListShow);

        sortFormBetTime(timeSort, hisListShow);


    }


    public static void sortFormNote(boolean sortAmount, List<NoteRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<NoteRecordHis>() {
            @Override
            public int compare(NoteRecordHis o1, NoteRecordHis o2) {
                if (sortAmount) {
                    if (o1.getBetAmount() > o2.getBetAmount()) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (o1.getBetAmount() < o2.getBetAmount()) {
                        return -1;
                    }
                    return 0;
                }
            }
        });
    }


    public static void sortFormBetTime(boolean sortTime, List<NoteRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<NoteRecordHis>() {
            @Override
            public int compare(NoteRecordHis o1, NoteRecordHis o2) {
                if (sortTime) {
                    if (o1.getBetTime() > o2.getBetTime()) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (o1.getBetTime() < o2.getBetTime()) {
                        return -1;
                    }
                    return 0;
                }
            }
        });

    }

    public static void sortFormProfit(boolean status, List<NoteRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<NoteRecordHis>() {
            @Override
            public int compare(NoteRecordHis o1, NoteRecordHis o2) {
                if (status) {
                    if (o1.getProfit() > o2.getProfit()) {
                        return -1;
                    }
                    return 0;
                } else {
                    if (o1.getProfit() < o2.getProfit()) {
                        return -1;
                    }
                    return 0;
                }
            }
        });
    }


    /**
     * 追号记录排序
     *
     * @param noteSort
     * @param profitSort
     * @param timeSort
     * @param hisListShow
     */
    private static void sortData1(boolean noteSort,
                                  boolean profitSort,
                                  boolean timeSort,
                                  List<ChaseNumRecordHis> hisListShow) {

        if (noteSort) {
            sortFormNote1(hisListShow);
        }
        if (profitSort) {
            sortFormProfit1(hisListShow);
        }
        if (timeSort) {
            sortFormBetTime1(hisListShow);
        }

    }

    private static void sortFormNote1(List<ChaseNumRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<ChaseNumRecordHis>() {
            @Override
            public int compare(ChaseNumRecordHis o1, ChaseNumRecordHis o2) {
                if (o1.getBetAmount() > o2.getBetAmount()) {
                    return -1;
                }
                return 0;
            }
        });
    }


    private static void sortFormBetTime1(List<ChaseNumRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<ChaseNumRecordHis>() {
            @Override
            public int compare(ChaseNumRecordHis o1, ChaseNumRecordHis o2) {
                if (o1.getBetTime() > o2.getBetTime()) {
                    return -1;
                }
                return 0;
            }
        });

    }

    private static void sortFormProfit1(List<ChaseNumRecordHis> hisListShow) {

        Collections.sort(hisListShow, new Comparator<ChaseNumRecordHis>() {
            @Override
            public int compare(ChaseNumRecordHis o1, ChaseNumRecordHis o2) {
                if (o1.getProfit() > o2.getProfit()) {
                    return -1;
                }
                return 0;
            }
        });
    }


}
