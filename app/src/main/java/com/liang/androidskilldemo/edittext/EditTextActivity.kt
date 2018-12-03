package com.liang.androidskilldemo.edittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {

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

    }
}
