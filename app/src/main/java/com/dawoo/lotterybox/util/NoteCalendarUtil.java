package com.dawoo.lotterybox.util;

import com.dawoo.coretool.util.date.DateTool;

/**
 * Created by archar on 18-2-9.
 */

public class NoteCalendarUtil {

    public static String reolveDate(String start, String end) {
        if (start.equals(end)) {
            long start1 = DateTool.getLongFromTime(DateTool.FMT_DATE, start);
            String starStr = DateTool.getTimeFromLong(DateTool.FMT_DATE_MD, start1);
            return starStr;
        } else {
            long start1 = DateTool.getLongFromTime(DateTool.FMT_DATE, start);
            long end1 = DateTool.getLongFromTime(DateTool.FMT_DATE, end);
            String starStr = DateTool.getTimeFromLong(DateTool.FMT_DATE_MD, start1);
            String endtr = DateTool.getTimeFromLong(DateTool.FMT_DATE_MD, end1);
            return starStr + "/" + endtr;
        }
    }
}
