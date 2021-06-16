package com.liang.androidskilldemo.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.liang.androidskilldemo.R;
import com.orhanobut.logger.Logger;

public class BroadcastActivity extends AppCompatActivity {

    private static final String ACTION1 = "com.liang.androidskilldemo.ACTION1";
    private static final String ACTION_IPC = "com.liang.androidskilldemo.ACTION_IPC";

    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        // 注册广播
        br = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION1);
        intentFilter.addAction(ACTION_IPC);
        registerReceiver(br, intentFilter);

        // 发送普通广播
        View btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(ACTION1);
            sendBroadcast(intent);
            Logger.d(intent.getAction());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (br != null) {
            unregisterReceiver(br);
            br = null;
        }
    }
}