package com.liang.androidskilldemo.act

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_act_learn_next.*

class ActLearnNextActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ActLearnNextSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_learn_next)
        btn_next.setOnClickListener {
            val intent = Intent(this, ActLearnThirdActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        Logger.d(TAG, "onRestart")
        super.onRestart()
    }

    override fun onStart() {
        Logger.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Logger.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Logger.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Logger.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logger.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Logger.d(TAG, "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Logger.d(TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState, outPersistentState)
    }
}
