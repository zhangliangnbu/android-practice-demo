package com.liang.androidskilldemo.window

import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_window_sample.*

class WindowSampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "WindowSample"
        private const val CODE_REQUEST_ALERT_PERMISSION = 1225
    }

    private var isAppWindowShow = false
    private var isInnerWindowShow = false
    private var isSystemWindowShow = false

    private val appButton by lazy {
        val button = Button(this)
        button.text = "这是应用window啊"
        button.setOnClickListener {
            wm.removeView(it)
            isAppWindowShow = isAppWindowShow.not()
        }
        return@lazy button
    }

    private val innerButton by lazy {
        val button = Button(this)
        button.text = "这是内部window啊啊啊"
        button.setOnClickListener {
            wm.removeView(it)
            isInnerWindowShow = isInnerWindowShow.not()
        }
        return@lazy button
    }

    private val systemButton by lazy {
        val button = Button(this)
        button.text = "这是系统window啊啊啊啊啊啊"
        button.setOnClickListener {
            wm.removeView(it)
            isSystemWindowShow = isSystemWindowShow.not()
        }
        return@lazy button
    }

    private val wm by lazy {
        getSystemService(WindowManager::class.java) as WindowManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_sample)

        btn_app_window.setOnClickListener {
            showOrHideWindow(appButton, WindowManager.LayoutParams.TYPE_APPLICATION, isAppWindowShow)
            isAppWindowShow = isAppWindowShow.not()
        }

        btn_inner_window.setOnClickListener {
            showOrHideWindow(innerButton, WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL, isInnerWindowShow)
            isInnerWindowShow = isInnerWindowShow.not()
        }

        btn_system_window.setOnClickListener {
            if(Settings.canDrawOverlays(this)) {
                // 和SDK版本有关 todo 待调研
                // 崩溃。Permission denied 26 28
                // 通过。23 24 25
                showOrHideWindow(systemButton, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, isSystemWindowShow)
                isSystemWindowShow = isSystemWindowShow.not()
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent, CODE_REQUEST_ALERT_PERMISSION)
            }
        }
    }

    private fun showOrHideWindow(view: View, type: Int, isShowing: Boolean = false) {
        if (isShowing) {
            wm.removeView(view)
        } else {
            val wp = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    type,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    PixelFormat.TRANSLUCENT)
            wm.addView(view, wp)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODE_REQUEST_ALERT_PERMISSION) {
            Log.d(TAG, permissions.contentToString())
            Log.d(TAG, grantResults.contentToString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_REQUEST_ALERT_PERMISSION) {
            Log.d(TAG, resultCode.toString())
        }
    }
}
