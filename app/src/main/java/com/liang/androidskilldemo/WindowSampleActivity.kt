package com.liang.androidskilldemo

import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_window_sample.*

class WindowSampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "WindowSample"
        private const val CODE_REQUEST_ALERT_PERMISSION = 1225
    }

    private var isAppWindowShow = false
    private var isInnerWindowShow = false
    private var isSystemWindowShow = false
    private var isSystem2WindowShow = false

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

    private val systemButton2 by lazy {
        val button = Button(this)
        button.text = "这是系统window2222啊啊啊啊啊啊"
        button.setOnClickListener {
            wm.removeView(it)
            isSystem2WindowShow = isSystem2WindowShow.not()
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

        // 同一级别的window,最后创建的显示在最外层
        btn_system_window.setOnClickListener {
            if(Settings.canDrawOverlays(this)) {
                // 和SDK版本有关 todo 待调研
                val type = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                }
                showOrHideWindow(systemButton, type, isSystemWindowShow)
                isSystemWindowShow = isSystemWindowShow.not()
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent,
                    CODE_REQUEST_ALERT_PERMISSION
                )
            }
        }

        btn_system_window2.setOnClickListener {
            if(Settings.canDrawOverlays(this)) {
                // 和SDK版本有关 todo 待调研
                val type = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                }
                showOrHideWindow(systemButton2, type, isSystem2WindowShow)
                isSystem2WindowShow = isSystem2WindowShow.not()
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent,
                    CODE_REQUEST_ALERT_PERMISSION
                )
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
            Logger.d(TAG, permissions.contentToString())
            Logger.d(TAG, grantResults.contentToString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_REQUEST_ALERT_PERMISSION) {
            Logger.d(TAG, resultCode.toString())
        }
    }
}
