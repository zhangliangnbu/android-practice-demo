package com.liang.androidskilldemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_looper_sample.*

class LooperSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LooperSample->"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper_sample)

        tv_hw.setOnClickListener {
            testLooper()
        }
    }

    private fun testLooper() {
        Thread(Runnable {
            if(Looper.myLooper() == null) {
                Looper.prepare()
            }

            val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Log.d(TAG, "msg from handle $msg")
                }
            }
            handler.sendEmptyMessage(0)
        }).start()
    }
}
