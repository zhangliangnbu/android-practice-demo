package com.liang.androidskilldemo.ipc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_serializable_sample2.*

class SerializableSample2Activity : AppCompatActivity() {

    companion object {
        private const val EXTRA_USER = "EXTRA_USER"

        fun open(context: Context, user: ParcelableUser) {
            context.startActivity(Intent(context, SerializableSample2Activity::class.java)
                    .putExtra(EXTRA_USER, user))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serializable_sample2)
        val user = intent.getParcelableExtra<ParcelableUser>(EXTRA_USER)
        tv_deparcelable_result.text = user.toString()
    }
}
