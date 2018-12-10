package com.liang.androidskilldemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_switch.*

class SwitchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)

        cb_demo.setOnCheckedChangeListener { buttonView, isChecked ->
            tv_check_desc.text = "选中->$isChecked"
        }
    }
}
