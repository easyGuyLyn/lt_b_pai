package com.dawoo.lotterybox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.LoginActivity;
import com.dawoo.lotterybox.view.activity.account.deposit.PostDepositActivity;
import com.dawoo.lotterybox.view.activity.chart.BaseChartActivity;
import com.dawoo.lotterybox.view.activity.chart.lhc.SMRecordActivity;
import com.dawoo.lotterybox.view.activity.chart.sfc.NCTrendChatActivity;
import com.dawoo.lotterybox.view.activity.chart.k3.K3TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.keno2.Bjkl8TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.keno2.Xy28TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.pk10.PK10TrendChartActivity;
import com.dawoo.lotterybox.view.activity.chart.ssc.ChartSSCActivity;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3AActivity;
import com.dawoo.lotterybox.view.activity.lottery.k3.K3BActivity;
import com.dawoo.lotterybox.view.activity.lottery.keno.BJKL8Activity;
import com.dawoo.lotterybox.view.activity.lottery.keno.XY28Activity;
import com.dawoo.lotterybox.view.activity.lottery.pk10.PK10AActivity;
import com.dawoo.lotterybox.view.activity.lottery.pk10.PK10BActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTAActivity;
import com.dawoo.lotterybox.view.activity.lottery.qt.QTBActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCBActivity;
import com.dawoo.lotterybox.view.activity.record.RecentOpenRecActivity;
import com.dawoo.lotterybox.view.activity.lottery.sfc.CqxyncActivity;
import com.dawoo.lotterybox.view.activity.lottery.ssc.SSCAActivity;
import com.dawoo.lotterybox.view.activity.lottery.lhc.HKSMActivity;
import com.dawoo.lotterybox.view.activity.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import static com.dawoo.lotterybox.ConstantValue.LT_CODE;
import static com.dawoo.lotterybox.ConstantValue.LT_NAME;

/**
 * 一些页面的跳转
 * Created by benson on 18-1-14.
 */

public class ActivityUtil {
    private static Context mContext = BoxApplication.getContext();

    public static void setContext(Context context) {
        mContext = context;
    }

    /**
     * 进入webview
     */
    public static void startWebView(String url, String type) {
        if (TextUtils.isEmpty(url)) {
            SingleToast.showMsg("链接地址为空");
            return;
        }

        if (!url.contains("http")) {
            if (!url.startsWith("/")) {
                url = DataCenter.getInstance().getDomain() + "/" + url;
            } else {
                url = DataCenter.getInstance().getDomain() + url;
            }

        }

        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(ConstantValue.WEBVIEW_URL, url);
        intent.putExtra(ConstantValue.WEBVIEW_TYPE, type);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 去客服
     */
    public static void gotoCustomerService() {
        String csLink = SPUtils.getInstance().getString("cslink", "");
        startWebView(csLink, ConstantValue.WEBVIEW_TYPE_THIRD_ORDINARY);
    }

    public static boolean isLogin() {
        if (DataCenter.getInstance().getUser().isLogin()) {
            return true;
        }
        ActivityUtils.startActivity(LoginActivity.class);
        return false;
    }


    public static void startLoginActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 进入最近的开奖记录activity
     *
     * @param lotteryCode
     */
    public static void startRecentOpenRecActivity(String lotteryCode) {
        Intent intent = new Intent(mContext, RecentOpenRecActivity.class);
        intent.putExtra(RecentOpenRecActivity.LOTTERY_CODE, lotteryCode);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 进入投注activity
     *
     * @param clazz
     * @param name
     * @param code
     */
    public static void startNoteActivity(Class clazz, String name, String code) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtra(LT_NAME, name);
        intent.putExtra(LT_CODE, code);
        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    /**
     * 进入趋势图activity
     *
     * @param clazz
     */
    private static void startChartActivity(Class clazz, ArrayList<Handicap> list, String name, String code) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtra(LT_NAME, name);
        intent.putExtra(LT_CODE, code);
        intent.putParcelableArrayListExtra(ConstantValue.RENCT_DATA, list);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 根据code进入投注界面
     *
     * @param code
     */
    public static void startNoteActivity(String code) {
        String mode = DataCenter.getInstance().getmLotteryType().get(code);
        if (LotteryEnum.CQSSC.getCode().equals(code)) {
            startActivityByModel(SSCAActivity.class,SSCBActivity.class,mode,code);

        } else if (LotteryEnum.XJSSC.getCode().equals(code)) {
            startActivityByModel(SSCAActivity.class,SSCBActivity.class,mode,code);
        } else if (LotteryEnum.FFSSC.getCode().equals(code)) {
            startActivityByModel(SSCAActivity.class,SSCBActivity.class,mode,code);

        } else if (LotteryEnum.BJPK10.getCode().equals(code)) {
            startActivityByModel(PK10AActivity.class,PK10BActivity.class,mode,code);

        } else if (LotteryEnum.XYFT.getCode().equals(code)) {
            startActivityByModel(PK10AActivity.class,PK10BActivity.class,mode,code);

        } else if (LotteryEnum.JSPK10.getCode().equals(code)) {
            startActivityByModel(PK10AActivity.class,PK10BActivity.class,mode,code);

        } else if (LotteryEnum.HKLHC.getCode().equals(code)) {
            startNoteActivity(HKSMActivity.class, LotteryUtil.getLotteryNameByCode(code), code);
        } else if (LotteryEnum.HBK3.getCode().equals(code)) {
            startActivityByModel(K3AActivity.class,K3BActivity.class,mode,code);

        } else if (LotteryEnum.GXK3.getCode().equals(code)) {
            startActivityByModel(K3AActivity.class,K3BActivity.class,mode,code);

        } else if (LotteryEnum.JSK3.getCode().equals(code)) {
            startActivityByModel(K3AActivity.class,K3BActivity.class,mode,code);

        } else if (LotteryEnum.AHK3.getCode().equals(code)) {
            startActivityByModel(K3AActivity.class,K3BActivity.class,mode,code);

        } else if (LotteryEnum.CQXYNC.getCode().equals(code)) {
            startNoteActivity(CqxyncActivity.class, LotteryUtil.getLotteryNameByCode(code), code);

        } else if (LotteryEnum.GDKL10.getCode().equals(code)) {
            startNoteActivity(CqxyncActivity.class, LotteryUtil.getLotteryNameByCode(code), code);

        } else if (LotteryEnum.BJKL8.getCode().equals(code)) {
            startNoteActivity(BJKL8Activity.class, LotteryUtil.getLotteryNameByCode(code), code);

        } else if (LotteryEnum.XY28.getCode().equals(code)) {
            startNoteActivity(XY28Activity.class, LotteryUtil.getLotteryNameByCode(code), code);

        } else if (LotteryEnum.FC3D.getCode().equals(code)) {
            startActivityByModel(QTAActivity.class,QTBActivity.class,mode,code);

        } else if (LotteryEnum.TCPL3.getCode().equals(code)) {
            startActivityByModel(QTAActivity.class,QTBActivity.class,mode,code);
        }
    }

    public static void startActivityByModel(Class A,Class B,String mode , String code){
        if (TextUtils.isEmpty(mode))
            startNoteActivity(A, LotteryUtil.getLotteryNameByCode(code), code);
        else{
            if ("all".equals(mode) || "official".equals(mode))
                startNoteActivity(A, LotteryUtil.getLotteryNameByCode(code), code);
            else  startNoteActivity(B, LotteryUtil.getLotteryNameByCode(code), code);
        }
    }

    public static void dispatchChartActivity(Context context, Class clazz, List<Handicap> list, String code) {
        if (list == null) {
            ToastUtil.showToastShort(mContext, "获取彩票开奖数据异常");
            return;
        }

        BaseChartActivity.startChartActivity(context, clazz, list, code);
    }


    /**
     * 根据code进入趋势图
     *
     * @param context
     * @param list
     * @param code
     */
    public static void startChartActivity(Context context, List<Handicap> list, String code) {
        if (LotteryEnum.CQSSC.getCode().equals(code)) {
            dispatchChartActivity(context, ChartSSCActivity.class, list, code);

        } else if (LotteryEnum.XJSSC.getCode().equals(code)) {
            dispatchChartActivity(context, ChartSSCActivity.class, list, code);

        } else if (LotteryEnum.FFSSC.getCode().equals(code)) {
            dispatchChartActivity(context, ChartSSCActivity.class, list, code);

        } else if (LotteryEnum.BJPK10.getCode().equals(code)) {
            dispatchChartActivity(context, PK10TrendChartActivity.class, list, code);

        } else if (LotteryEnum.XYFT.getCode().equals(code)) {
            dispatchChartActivity(context, PK10TrendChartActivity.class, list, code);

        } else if (LotteryEnum.JSPK10.getCode().equals(code)) {
            dispatchChartActivity(context, PK10TrendChartActivity.class, list, code);

        } else if (LotteryEnum.HKLHC.getCode().equals(code)) {

            dispatchChartActivity(context, SMRecordActivity.class, list, code);

        } else if (LotteryEnum.HBK3.getCode().equals(code)) {
            dispatchChartActivity(context, K3TrendChartActivity.class, list, code);

        } else if (LotteryEnum.GXK3.getCode().equals(code)) {
            dispatchChartActivity(context, K3TrendChartActivity.class, list, code);

        } else if (LotteryEnum.JSK3.getCode().equals(code)) {
            dispatchChartActivity(context, K3TrendChartActivity.class, list, code);

        } else if (LotteryEnum.AHK3.getCode().equals(code)) {
            dispatchChartActivity(context, K3TrendChartActivity.class, list, code);

        } else if (LotteryEnum.CQXYNC.getCode().equals(code)) {
            dispatchChartActivity(context, NCTrendChatActivity.class, list, code);

        } else if (LotteryEnum.GDKL10.getCode().equals(code)) {
            dispatchChartActivity(context, NCTrendChatActivity.class, list, code);

        } else if (LotteryEnum.BJKL8.getCode().equals(code)) {
            dispatchChartActivity(context, Bjkl8TrendChartActivity.class, list, code);

        } else if (LotteryEnum.XY28.getCode().equals(code)) {
            dispatchChartActivity(context, Xy28TrendChartActivity.class, list, code);

        } else if (LotteryEnum.FC3D.getCode().equals(code)) {
            dispatchChartActivity(context, Xy28TrendChartActivity.class, list, code);

        } else if (LotteryEnum.TCPL3.getCode().equals(code)) {
            dispatchChartActivity(context, Xy28TrendChartActivity.class, list, code);
        }
    }

    public static void startOtherapp(String packageName) {
        try {
            PackageManager packageManager
                    = BoxApplication.getContext().getPackageManager();
            Intent intent = packageManager.
                    getLaunchIntentForPackage(packageName);
            mContext.startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showToastShort(mContext, "您的手机未安装该应用");
        }
    }

    /**
     * 进入提交申请页面
     */
    public static void startWithdrawActivity(Activity activity, PayDetailBean payDetailBean, double depositAmount, int saleId) {
        Intent intent = new Intent(activity, PostDepositActivity.class);
        intent.putExtra("item", payDetailBean);
        intent.putExtra("depositAmount", depositAmount);
        intent.putExtra("saleId", saleId);
        activity.startActivityForResult(intent, ConstantValue.REQUEST_DEPOSIT);
    }
}
