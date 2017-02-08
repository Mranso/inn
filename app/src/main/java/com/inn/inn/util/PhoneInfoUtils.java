
package com.inn.inn.util;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 手机信息获取
 *
 * @author Administrator 常用字段，数据获取
 */
public class PhoneInfoUtils {

    public static PhoneInfoUtils pdd = null;
    public Context context;

    public static PhoneInfoUtils getInstance(Context mContext) {
        if (pdd == null) {
            pdd = new PhoneInfoUtils(mContext.getApplicationContext());
        }
        return pdd;
    }

    public PhoneInfoUtils(Context mContext) {
        context = mContext;
    }

    /**
     * 获取当前应用的版本号
     */
    public String getAppVersionName() {

        String currentVersion = null;
        try {
            currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }// 获取本地版本号
        return currentVersion;
    }

    /**
     * 获取应用的的代号
     */
    public int getVersionCode() {

        int currentVersionCode = 1;
        try {
            currentVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVersionCode;
    }

}
