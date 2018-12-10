package com.liang.androidskilldemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_background_update.*
import java.text.SimpleDateFormat
import java.util.*

class BackgroundUpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_update)

        btn_trigger.setOnClickListener {
            Handler().postDelayed({
                val date = SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.CHINA).format(Date())
                Log.d("background update->", "开始更新啦$date")
                tv_back_update.text = "$date"
                tv_back_update.visibility = View.GONE
                AlertDialog.Builder(this)
                        .setTitle("test")
                        .setMessage("test")
                        .show()
            }, 3000)
        }

    }
}
