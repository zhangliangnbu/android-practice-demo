package com.liang.androidskilldemo;

import android.app.Application;
import android.content.res.Configuration;
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

    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(1)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.d("onCreate");

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
