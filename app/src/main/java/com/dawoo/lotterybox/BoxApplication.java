package com.dawoo.lotterybox;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.util.SoundUtil;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.SmartRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by benson on 17-12-27.
 */

public class BoxApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)  {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }

        context = getApplicationContext();
        Utils.init(context);
        DataCenter.getInstance().setDomain(BuildConfig.HOST_URL).setImgDomain(BuildConfig.HOST_IMG_URL);
        DataCenter.getInstance().getSysInfo().initSysInfo(
                PackageInfoUtil.getVersionName(context),
                PackageInfoUtil.getVersionCode(context),
                PackageInfoUtil.getUniqueId(context));

        initLotteryData();
        SoundUtil.getInstance().load(ConstantValue.VOICE_ON_CLICK, R.raw.anjian);
    }

    private void initLotteryData() {
//        CQSSCGFWanFaUtil gfWanFaUtil = new CQSSCGFWanFaUtil();
//        gfWanFaUtil.initData();  //初始化时时彩玩法源数据
//        PK10ADataUtil pk10ADataUtil = new PK10ADataUtil(); //初始化pk10 a盘数据
//        pk10ADataUtil.initData();
//        JSK3Util jsk3Util = new JSK3Util(); //初始化jsk3 a盘数据
//        jsk3Util.initData();
//        QTDataAUtil qtDataAUtil = new QTDataAUtil();
//        qtDataAUtil.initData();
    }


    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

}
