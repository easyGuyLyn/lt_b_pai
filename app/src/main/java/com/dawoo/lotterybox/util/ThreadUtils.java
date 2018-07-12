package com.dawoo.lotterybox.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanzhuqing on 16/7/26.
 */
public class ThreadUtils {
    private static ExecutorService fixedThreadPool = Executors.newScheduledThreadPool(5);
    public static void newThread(Runnable runnable){
        fixedThreadPool.execute(runnable);
    }
}
