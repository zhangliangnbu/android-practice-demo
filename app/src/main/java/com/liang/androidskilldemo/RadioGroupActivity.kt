package com.liang.androidskilldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_radio_group.*

class RadioGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio_group)

//        val rbs = listOf(rb_1, rb_2)
//        rbs.forEach { rb ->
//            rb.setOnClickListener {
//                if(rb.isChecked) {
//                    rg_group.clearCheck()
//                }
//        } }
        listOf(rb_1, rb_2).forEach { rb->
            rb.setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_DOWN) {
                    println("touch->down ${rb.isChecked}")
                    if(rb.isChecked) {
                        rg_group.clearCheck()
                        return@setOnTouchListener true
                    }
                }
                return@setOnTouchListener false
            }
        }

        rb_1.setOnCheckedChangeListener { buttonView, isChecked ->
            println("1checked->$isChecked")
        }

        rb_2.setOnCheckedChangeListener { buttonView, isChecked ->
            println("2checked->$isChecked")
        }

    }
}
