package com.liang.androidskilldemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_looper_sample.*

class LooperSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LooperSample->"
    }

    private val demo = MultiThreadDemo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper_sample)

        tv_hw.setOnClickListener {
//            demo.testHandler()
//            demo.testThreadHandler()
//            demo.testAsyncTask()
            demo.testRxJava()
        }
    }
}
