package com.dawoo.lotterybox.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.dawoo.lotterybox.ConstantValue;


/**
 * Created by rain on 18-4-3.
 */

public class PermissionUtil {
    public static boolean checkPermission(Activity activity, String permission, int requestCode) {
        if (activity == null) {
            return false;
        }
        try {
            //检测是否有权限
            int permissionCode = ActivityCompat.checkSelfPermission(activity,
                    permission);
            if (permissionCode != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请读写的权限，会弹出对话框
                if (permission.equalsIgnoreCase(ConstantValue.PERMISSIONS_STORAGE_WRITE)) {
                    ActivityCompat.requestPermissions(activity, new String[]{permission, ConstantValue.PERMISSIONS_STORAGE_READ}, requestCode);
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
