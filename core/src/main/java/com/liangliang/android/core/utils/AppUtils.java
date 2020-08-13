package com.liangliang.android.core.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * App帮助类
 */
public class AppUtils {
    /**
     * 获取应用程序名称
     *
     * @param context 上下文
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取客户端版本名称
     *
     * @param context 上下文
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取客户端版本号
     *
     * @param context 上下文
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 当前是否在主线程（UI线程）
     */
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 判断传入的context是不是在主进程
     *
     * @param context 上下文
     */
    public static boolean isMainProcess(Context context) {
        String processName = getProcessName(context);
        return !TextUtils.isEmpty(processName) && !processName.contains(":");
    }

    /**
     * 获取进程名称
     *
     * @param context 上下文
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return "";
    }

    /**
     * 安装akp文件
     *
     * @param context 上下文
     * @param apkPath apk路径
     */
    public static void installApk(Context context, @NonNull String apkPath, String authority) throws Exception {
        File file = FileUtils.createFile(apkPath);
        if (!FileUtils.isFileExists(file)) {
            throw new IllegalArgumentException("file no exists");
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(context, authority, file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    /**
     * 卸载应用
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApp(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }

    /**
     * 获取随机的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 权限是否被授予
     *
     * @param context    上下文
     * @param permission 权限
     */
    public static boolean isPermissionGranted(Context context, String permission) {
        try {
            return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据包名打开对应的设置界面
     *
     * @param context 上下文
     */
    public static void jumpAppDetailSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    /**
     * 获取MetaData
     *
     * @param context 上下文
     * @param key     标签名
     */
    public static Object getMetaData(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到定位设置页
     * @param context 上下文
     */
    public static void jumpLocationSetting(Context context){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * GPS是否打开
     * @param context 上下文
     */
    public static boolean isGpsOpen(Context context) {
        String str = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        return !TextUtils.isEmpty(str) && (str.contains("gps") || str.contains("GPS"));
    }

    /**
     * wifi是否可用
     * @param context 上下文
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean isWifiEnabled(Context context) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return manager != null && manager.isWifiEnabled();
    }

    /**
     * 设置wifi是否可用
     * @param context 上下文
     * @param enabled 是否可用
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    public static void setWifiEnabled(Context context, boolean enabled) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (manager != null && manager.isWifiEnabled()){
            manager.setWifiEnabled(enabled);
        }
    }

    /**
     * 跳转到WIFI设置页
     * @param context 上下文
     */
    public static void jumpWifiSetting(Context context){
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到数据流量设置页
     * @param context 上下文
     */
    public static void jumpDataRoamingSetting(Context context){
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到密码设置页
     * @param context 上下文
     */
    public static void jumpSetPswdSetting(Context context){
        Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
        context.startActivity(intent);
    }
}
