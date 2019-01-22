package com.cdx.example.quanxian.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * 检查权限/权限数组
 * request权限
 */
public class PermissionUtils {

    /**
     * 检查多个权限
     *
     * 检查权限
     * @param permissions 权限数组
     * @param context Context
     * @return true 已经拥有所有check的权限 false存在一个或多个未获得的权限
     */
    public static boolean checkPermissionsGroup(Context context, String[] permissions) {

        for (String permission : permissions) {
            if (!checkPermission(context, permission)){
                return false;
            }
        }
        return true;
    }

    /**
     * 检查单个权限
     * @param context Context
     * @param permission 权限
     * @return boolean 是否授权
     */
    private static boolean checkPermission(Context context, String permission) {
        //当版本号 > 23，也就是Android的版本>6.0的时候，才会动态的申请权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 申请权限
     * @param activity Activity
     * @param permissions 权限数组
     * @param requestCode 请求码
     */
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        // 先检查是否已经授权
        if (!checkPermissionsGroup(activity, permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    /**
     * 通过AppOpsManager判断小米手机授权情况
     *
     * @return boolean
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkXiaomi(Context context, String[] opstrArrays) {
        AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);
        String packageName = context.getPackageName();
        for (String opstr : opstrArrays) {
            int locationOp = appOpsManager.checkOp(opstr, Binder.getCallingUid(), packageName);
            if (locationOp == AppOpsManager.MODE_IGNORED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 没有权限的提示
     * @param context Context
     * @param tip 对于的提示
     */
    public static void showNoPermissionTip(Context context, String tip) {
        Toast.makeText(context, tip , Toast.LENGTH_SHORT).show();
    }

}
