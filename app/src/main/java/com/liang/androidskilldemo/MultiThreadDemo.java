package com.liang.androidskilldemo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import org.reactivestreams.Subscription;

import java.util.Arrays;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by zhangliang on 2021/6/30
 */
public class MultiThreadDemo {

    private  Handler handler;
    public void testHandler() {
        // 创建线程A 、Looper、Handler
        Thread threadA = new Thread(() -> {
            Looper.prepare();
            // 创建 Handler。
            handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Log.d("handleMessage", "msg what == " + msg.what);
                }
            };
            Looper.loop();
        });
        threadA.start();

        // 创建线程B，并利用Handler 发送消息
        Thread threadB = new Thread(() -> {
            // 防止ThreadA没有执行完而导致handler为空
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(111);
        });
        threadB.start();
    }

    public void testThreadHandler() {
        // 创建线程A 、Looper
        HandlerThread threadA = new HandlerThread("handler thread a");
        threadA.start();
        // 创建Handler
        handler = new Handler(threadA.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d("handleMessage", "msg what == " + msg.what);
            }
        };

        // 创建线程B，并利用Handler 发送消息
        Thread threadB = new Thread(() -> {
            handler.sendEmptyMessage(111);
        });
        threadB.start();
    }

    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            // on ui thread
            super.onPreExecute();
            Log.d("MyAsyncTask", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... strings) {
            // on work thread
            publishProgress(0);
            Log.d("MyAsyncTask", "doInBackground ==" + Arrays.toString(strings));
            publishProgress(100);
            return Arrays.toString(strings);
        }

        @Override
        protected void onPostExecute(String s) {
            // on ui thread
            super.onPostExecute(s);
            Log.d("MyAsyncTask", "onPostExecute ==" + s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // on ui thread
            super.onProgressUpdate(values);
            Log.d("MyAsyncTask", "onProgressUpdate ==" + Arrays.toString(values));
        }
    }

    public void testAsyncTask() {
        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute("a", "b", "c");
    }

    public void testRxJava() {
        Log.d("RxJava", "before");
        Disposable a = Flowable.just("a")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(subscription -> {
                    Log.d("RxJava", "doOnSubscribe");
                })
                .subscribe(s -> Log.d("RxJava", "s ==" + s));
    }


}
