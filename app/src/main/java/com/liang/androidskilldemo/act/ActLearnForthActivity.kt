package com.liang.androidskilldemo.act

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_act_learn_forth.*

class ActLearnForthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_learn_forth)

        btn_next.setOnClickListener {
            val intent = Intent(this, ActLearnFifthActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }
}
