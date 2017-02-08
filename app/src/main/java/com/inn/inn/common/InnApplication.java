package com.inn.inn.common;

import android.app.Application;

import com.inn.inn.BuildConfig;
import com.inn.inn.util.PhoneInfoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.taobao.hotfix.HotFixManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

public class InnApplication extends Application {
    public static String appVersion;
    public static String appId;

    private static InnApplication innApplication;

    public static synchronized InnApplication getInstance() {
        if (innApplication == null) {
            innApplication = new InnApplication();
        }
        return innApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
        initHotFix();
        initImageLoader();
        initBugly();
    }

    private void initBugly() {
        if (BuildConfig.DEBUG) {
            initBuglyConfigForDebug();
        } else {
            initBuglyConfig();
        }
    }

    //初始化Bugly
    private void initBuglyConfig() {
        try {
            CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
            userStrategy.setAppChannel("Release");
            userStrategy.setAppReportDelay(3 * 1000);
            userStrategy.setAppVersion(PhoneInfoUtils.getInstance(this).getAppVersionName());
            Bugly.init(this, "92ab17b6e7", BuildConfig.DEBUG, userStrategy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化Debug下Bugly
    private void initBuglyConfigForDebug() {
        try {
            CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
            userStrategy.setAppChannel("Debug");
            userStrategy.setAppReportDelay(1000);
            userStrategy.setAppVersion(PhoneInfoUtils.getInstance(this).getAppVersionName());
            Bugly.init(this, "92ab17b6e7", BuildConfig.DEBUG, userStrategy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCacheSizePercentage(10)
                .build();
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
