package com.liang.androidskilldemo.performance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liang.androidskilldemo.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CPUPerformanceActivity extends AppCompatActivity {

    private Button btnDisplay;
    private Button btnTest;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuperformance);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnTest = findViewById(R.id.btnTestCallChain);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();
                btnDisplay.setText("触发时时间：" + time);
                try {
                    Thread.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyTitleChanged(String.valueOf(time));
            }
        });

        // todo
        Thread.setDefaultUncaughtExceptionHandler(null);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]>[] g = new List[4];
        ArrayList<int[]>[] gg = new ArrayList[4];
        int[] a = new int[]{1, 2};




    }

    private void notifyTitleChanged(String title) {
        try {
            Thread.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 计算title
                String calTitle = calTitle(title);

                // 更新ui
                btnDisplay.post(new Runnable() {
                    @Override
                    public void run() {
                        btnDisplay.setText(calTitle);
                    }
                });
            }
        }, "TestCPUThread").start();
    }

    public static String calTitle(String title) {
        int cal = 0;
        for (int i = 0; i < 100000; i ++) {
            if (i % 37 == 0) {
                cal += i;
            }
        }
        try {
            Thread.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "最终结果：" + title + "-" + cal;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 延迟加载
        btnTest.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("run", "reportFullyDrawn");
                reportFullyDrawn();
            }
        }, 1000L);
    }

    public class Pair implements Comparable<Pair> {
        public int compareTo(Pair other) {
            return -1;
        }
    }
}