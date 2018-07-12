package com.dawoo.lotterybox.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.dawoo.lotterybox.BoxApplication;


/**
 * 保存一些基本信息
 * Created by benson on 17-12-27.
 */

public class SharePreferenceUtil {

    /**
     * 登录密码记忆
     */

    public static String getPassWord(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_User", Context.MODE_PRIVATE);
        return sp.getString("password", "");
    }

    public static void savePassWord(Context context, String pwd) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("password", pwd);
        editor.apply();
    }



    /**
     * 获取保存的域名
     *
     * @param context
     * @return
     */
    public static String getDomain(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_NetInfo", Context.MODE_PRIVATE);
        return sp.getString("domain", "");
    }

    /**
     * 保存检测成功后的域名
     *
     * @param context
     * @param domain
     */
    public static void saveDomain(Context context, String domain) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_NetInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("domain", domain);
        editor.apply();
    }


    /**
     * 保存 玩法界面的 json串
     */

    public static void savePlayBeanJson(Context context, String json) {
        SharedPreferences sp = context.getSharedPreferences("PlayBeanJson", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("PlayBeanJson", json);
        editor.apply();
    }

    /**
     * 获取 玩法界面 的json
     *
     * @param context
     * @return
     */
    public static String getPlayBeanListJson(Context context) {
        SharedPreferences sp = context.getSharedPreferences("PlayBeanJson", Context.MODE_PRIVATE);
        return sp.getString("PlayBeanJson", "");
    }

    /**
     * 获取声音状态
     *
     * @param context
     * @return
     */
    public static Boolean getVoiceStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_Info", Context.MODE_PRIVATE);
        return sp.getBoolean("VoiceStatus", true);
    }

    /**
     * 保存声音状态
     *
     * @param context
     */
    public static void saveVoiceStatus(Context context, boolean isOpen) {
        SharedPreferences sp = context.getSharedPreferences("Box_Base_Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("VoiceStatus", isOpen);
        editor.apply();
    }

    /**
     * 保存跳进绑卡界面的数据
     */
    public static void saveBankCardStatues(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Bank_Card_statues", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ank_Card_statues", "mejump");
        editor.apply();
    }

    /**
     * 保存设备号信息
     */
    public static void savaDeviceId(Context context, String deviceId) {
        SharedPreferences sp = context.getSharedPreferences("sava_deveice_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("device_id", deviceId);
        editor.apply();
    }

    /**
     * 获取设备deviceId信息
     */
    public static String getDeviceId() {
        SharedPreferences sharedPreferences = BoxApplication.getContext().getSharedPreferences("sava_deveice_id", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString("device_id", "");
        return deviceId;
    }

    /**
     * 中奖动画　开
     */
    public static void openWinningAnimation() {
        SharedPreferences sharedPreferences = BoxApplication.getContext().getSharedPreferences("winning_animition", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("animation", true);
        editor.apply();
    }

    /**
     * 中奖动画　关
     */
    public static void closeWinningAnimation() {
        SharedPreferences sharedPreferences = BoxApplication.getContext().getSharedPreferences("winning_animition", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("animation", false);
        editor.apply();
    }


    /**
     * 记忆投注设置  彩种独立
     */
    public static void putMemoryFunction(Context context, String value, String code) {
        SharedPreferences sp = context.getSharedPreferences("LotteryBMemory", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Memory" + code, value);
        editor.apply();
    }

    /**
     * 记忆投注获取  -1表示没打开开关    其他值表示打开了开关 和 且记忆的数据
     *  彩种独立
     * @param context
     */
    public static String getMemoryFunction(Context context, String code) {
        SharedPreferences sp = context.getSharedPreferences("LotteryBMemory", Context.MODE_PRIVATE);
        return sp.getString("Memory" + code, "-1");
    }


}
