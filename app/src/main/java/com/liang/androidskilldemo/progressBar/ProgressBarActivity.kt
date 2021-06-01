package com.liang.androidskilldemo.progressBar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_progress_bar.*
import utils.ActivityCounter

class ProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        pb_progress.progress = 70

        ActivityCounter.count ++
        Log.d("activity counter->", "${ActivityCounter.count}")
    }
}
