package com.liang.androidskilldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_check_box.*

class WidgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box)

        play_seekBar.setPadding(10, 0, 10, 0)

    }
}
