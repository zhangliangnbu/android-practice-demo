package com.liang.androidskilldemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.util.Log;

import com.orhanobut.logger.Logger;

import androidx.annotation.NonNull;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * created by zhangliang on 2021/6/1
 */
public class MyApplication extends Application {

    private boolean isDebug = true;

    @Override
    public void onCreate() {
        if (isDebug) {
            strictModeForDebug();
        }
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(1)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.d("onCreate");

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int mc = am.getMemoryClass();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        Logger.d("memory class = " + mc
                + ", totalMem = " + memoryInfo.totalMem
                + ", availMem = " + memoryInfo.availMem
                + ", lowMemory = " + memoryInfo.lowMemory
                + ", threshold = " + memoryInfo.threshold
        );

    }

    private void strictModeForDebug() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.d(newConfig.orientation);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logger.d("");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Logger.d("");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Logger.d("level=" + level);
    }
}
