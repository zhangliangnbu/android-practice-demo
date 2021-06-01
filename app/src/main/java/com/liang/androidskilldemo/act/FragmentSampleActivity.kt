package com.liang.androidskilldemo.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R

class FragmentSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, SampleBFragment())
        }.commit()


    }
}
