package com.liang.androidskilldemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.liang.androidskilldemo.R;
import com.orhanobut.logger.Logger;

public class MyService extends Service {

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stopSelf(msg.arg1);
        }
    }

    private ServiceHandler handler;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("");
        HandlerThread thread = new HandlerThread("MyService");
        thread.start();
        handler = new ServiceHandler(thread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("intentAction = " + intent.getAction());

        // 将后台服务变为前台服务
        // Android O 及以上需要创建渠道，按渠道管理通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建渠道
            NotificationChannel channel = new NotificationChannel("Ch1", "渠道1", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
            // 创建通知
            Intent notificationIntent = new Intent(getApplicationContext(), ServiceManageActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            Notification notification = new Notification.Builder(this, channel.getId())
                    .setContentTitle("标题")
                    .setContentText("内容啊内容啊内容啊内容啊内容啊内容啊内容啊内容啊")
                    .setSmallIcon(R.drawable.ic_android1)
                    .setContentIntent(pendingIntent)
                    .setTicker("描述：该服务是测试服务")
                    .build();
            startForeground(10, notification);
        }

        Message message = handler.obtainMessage();

        handler.sendMessage(message);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.d("");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("");
    }
}