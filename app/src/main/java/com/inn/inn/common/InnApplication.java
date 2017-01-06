package com.inn.inn.common;

import android.app.Application;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;

public class InnApplication extends Application {
    public static String appVersion;
    public static String appId;

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
        initHotFix();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }

    private void initApp() {
        appId = "83545-1";
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
    }

    private void initHotFix() {
        HotFixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAppId(appId)
                .setAesKey(null)
                .setSupportHotpatch(true)
                .setEnableDebug(true)
                .initialize();
    }
}
