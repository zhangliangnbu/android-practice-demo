package com.liang.androidskilldemo.progressBar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_progress_bar.*

class ProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        pb_progress.progress = 70
    }
}
