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
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onload(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
                            // TODO: 10/24/16 表明补丁加载成功
                            Toast.makeText(getApplicationContext(), "补丁包加载成功", Toast.LENGTH_SHORT).show();
                        } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
                            // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Toast.makeText(getApplicationContext(), "补丁包加载成功,重启生效", Toast.LENGTH_SHORT).show();
                        } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
                            // 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
                            //HotFixManager.getInstance().cleanPatches(false);
                            Toast.makeText(getApplicationContext(), "补丁包加载失败", Toast.LENGTH_SHORT).show();
                        } else {
                            // TODO: 10/25/16 其它错误信息, 查看PatchStatusCode类说明
                            Toast.makeText(getApplicationContext(), "补丁包加载失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).initialize();
    }
}
