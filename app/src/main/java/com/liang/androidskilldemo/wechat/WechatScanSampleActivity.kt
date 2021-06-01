package com.liang.androidskilldemo.wechat

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_wechat_scan_sample.*

class WechatScanSampleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wechat_scan_sample)

        btn_to_scan.setOnClickListener { toScan2() }

    }

    private fun toScan() {
        val intent = this.packageManager.getLaunchIntentForPackage("com.tencent.mm")
        intent?.putExtra("LauncherUI.From.Scaner.Shortcut", true)
        this.startActivity(intent)
    }

    @SuppressLint("WrongConstant")
    private fun toScan2() {
        val intent = Intent()
        intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
        intent.putExtra("LauncherUI.From.Scaner.Shortcut", true)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "android.intent.action.VIEW"
        this.startActivity(intent)
    }


}
