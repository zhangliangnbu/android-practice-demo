package com.liang.androidskilldemo

import android.Manifest
import android.app.ActivityManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import kotlinx.android.synthetic.main.activity_back_stack.*

class BackStackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_stack)

        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.PACKAGE_USAGE_STATS)
                != PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

        btn_task.setOnClickListener {

            printTasksInfoOfApi22()
        }
    }

    private fun printTasksInfo() {
        val text = ""

        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        am.runningAppProcesses
        am.appTasks
        am.getRunningTasks(20).forEach { info ->
            println("${info.baseActivity?.packageName}:${info.baseActivity?.shortClassName}")
            text.plus("${info.baseActivity?.packageName}:${info.baseActivity?.shortClassName} \n")
        }

        tv_tasks.text = text
    }

    private fun printTasksInfoApi21() {
        val processStateField = ActivityManager.RunningAppProcessInfo::class.java.getDeclaredField("processState")

    }

    private fun printTasksInfoOfApi22() {

        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val currentTime = System.currentTimeMillis()
        val ues = usm.queryEvents(currentTime - 60 * 1000, currentTime)
        val ue = UsageEvents.Event()
        if (ues.hasNextEvent()) {
            ues.getNextEvent(ue)
            println("ue:${ue.packageName}:${ue.className}")
        }
    }

}
