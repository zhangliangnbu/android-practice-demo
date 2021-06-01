package com.liang.androidskilldemo.edittext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_edit_text_delete.*

/**
 * 1. EditText长度限制
 * 2. 超过长度则提示
 * 3. 从光标处删除超出的部分
 */
class EditTextDeleteActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "EditTextDelete"
        private const val MAX = 8
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_delete)

        et_delete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                println("$TAG: beforeTextChanged->${s.toString()},$start,$count,$after")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println("$TAG: onTextChanged->${s.toString()},$start,$before,$count")
            }

            override fun afterTextChanged(s: Editable?) {
                println("$TAG: afterTextChanged->${s.toString()}")
                val text = et_delete.text
                val index = et_delete.selectionStart
                if(text.length > MAX) {
                    println("超出上线啦！！！")
                    var start = index - (text.length - MAX)
                    if(start < 0)  {
                        start = 0
                    }
                    val end = start + text.length - MAX
                    text.delete(start, end)
                }
            }
        })
    }
}
