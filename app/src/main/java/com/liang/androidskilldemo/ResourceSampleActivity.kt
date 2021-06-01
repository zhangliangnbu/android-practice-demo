package com.liang.androidskilldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_resource_sample.*

class ResourceSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ResourceSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_sample)

//        val list = assets.list("")
//        val list = resources.assets.list("")
//        list?.forEach {
//            Log.d(TAG, it)
//        }
        assets.list("images")?.forEach {
            Log.d(TAG, it)
        }

        val count = 2
        val songsFound = resources.getQuantityString(R.plurals.numberOfSongsAvailable, count, count)
        tv_display.text = songsFound

        btn_count.setOnClickListener {
            val c = et_count.text.toString().toInt()
            tv_display.text = resources.getQuantityString(R.plurals.numberOfSongsAvailable, c, c)
        }


    }
}
