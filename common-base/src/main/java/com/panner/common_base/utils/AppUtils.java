package com.panner.common_base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.panner.common_base.exception.SystemException;

import java.util.List;
import java.util.UUID;

/**
 * @author Panner
 * @version 2019-04-07-22:42
 */
public class AppUtils {
    /**
     * 获取应用程序名称
     *
     * @throws SystemException
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-5-07
     */
    public static String getAppName(Context context) throws SystemException {
        PackageInfo packageInfo = getVersionInfo(context);
        int labelRes = packageInfo.applicationInfo.labelRes;
        return context.getResources().getString(labelRes);
    }

    /**
     * 获取应用版本号
     *
     * @throws SystemException
     */
    public static int getAppVersion(Context context) throws SystemException {
        PackageInfo packageInfo = getVersionInfo(context);
        return packageInfo.versionCode;
    }

    /**
     * 获得系统版本信息
     *
     * @param application {@link Context}
     * @return {@link PackageInfo}
     */
    public static PackageInfo getVersionInfo(Context application) throws SystemException {
        PackageManager mg = application.getPackageManager();
        try {
            return mg.getPackageInfo(application.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new SystemException("Version information acquisition failed", e);
        }
    }

    /**
     * 去除电话号码中的 "-" 和" "
     */
    public static String phoneStringToNumberString(String phoneString) {
        if (TextUtils.isEmpty(phoneString)) {
            return phoneString;
        }
        return phoneString.replaceAll("-", "").replaceAll(" ", "");
    }

    /**
     * 重启应用:需要启动界面与后面界面在一个task中，如果使用单例需用singleTask。
     */
    public static void restartApplication(Activity activity) {
        Intent intent = activity.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * 创建一个启动图标
     *
     * @param title 快捷键的标题
     * @param logo  快捷键的图标
     */
    public static void createShortCut(Context context, String title, int logo,
                                      Class<? extends Activity> activity) {
        //创建一个添加快捷方式的Intent
        Intent addSC = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //快捷键的图标
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context, logo);
        //创建单击快捷键启动本程序的Intent
        Intent launcherIntent = new Intent(context, activity);
        //设置快捷键的标题
        addSC.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        //设置快捷键的图标
        addSC.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //设置单击此快捷键启动的程序
        addSC.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
        //向系统发送添加快捷键的广播
        context.sendBroadcast(addSC);
    }

    /**
     * 得到唯一设备ID,需要权限:uses-permission android:name="android.permission.READ_PHONE_STATE"
     *
     * @see <a href='http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id/2853253#2853253'>stackoverflow</a>
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    /**
     * 之后使用:https://github.com/jaredrummler/AndroidProcesses
     * app是否有Activity运行
     */
    @Deprecated
    public static boolean isForeground(Context context) {

        String PackageName = context.getPackageName();
        // Get the Activity Manager
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        // Get a list of running tasks, we are only interested in the last one,
        // the top most so we give a 1 as parameter so we only get the topmost.
        List<ActivityManager.RunningTaskInfo> task = manager.getRunningTasks(1);
        if (task == null || task.isEmpty()) {
            return false;
        }
        // Get the info we need for comparison.
        ComponentName componentInfo = task.get(0).topActivity;

        // Check if it matches our package name.
        if (componentInfo.getPackageName().equals(PackageName)) {
            return true;
        }

        // If not then our app is not on the foreground.
        return false;
    }

    /**
     * 启动到应用商店app详情界面
     */
    public static void openPlayStore(Context context) {
        openPlayStore(context, context.getPackageName());
    }

    /**
     * 打开对应app应用商店
     *
     * @param appPkg 目标App的包名
     */
    public static void openPlayStore(Context context, String appPkg) {
        if (TextUtils.isEmpty(appPkg)) {
            return;
        }

        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + appPkg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
