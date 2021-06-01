package com.liang.androidskilldemo.act

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_app_link.*

class AppLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_link)

        val action: String? = intent?.action
        val data: Uri? = intent?.data

        tv_info.text = "action:$action, data:$data"
    }
}
