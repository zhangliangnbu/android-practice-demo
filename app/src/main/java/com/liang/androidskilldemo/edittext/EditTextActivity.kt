package com.liang.androidskilldemo.edittext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import android.view.View
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_edit_text.*

/**
 * 光标显示与否逻辑
 */
class EditTextActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "EditTextSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        // 需求：首次进入不显示光标，之后在点击编辑之后才显示
        et_selection.isCursorVisible = false
        et_selection.setOnClickListener {
            et_selection.isCursorVisible = true
        }

        btn_edit_by_code.setOnClickListener {
            et_selection.setText("通过代码设置的哦，不显示光标")
        }

        var editEnabled = true

        btn_enable.setOnClickListener {
            editEnabled = editEnabled.not()
            if(editEnabled) {
                view_shadow.visibility = View.GONE

                et_focus.requestFocus()
                et_focus.isEnabled = true
                et_focus.isCursorVisible = true

            } else {
                view_shadow.visibility = View.VISIBLE
                view_shadow.requestFocus()

                et_focus.isEnabled = false
                et_focus.isCursorVisible = false
            }
        }

        val count = group_test.childCount
        for (i in 0 until count) {
            val child = group_test.getChildAt(i)
            Logger.d(TAG, child.javaClass.typeName)
        }

    }
}
