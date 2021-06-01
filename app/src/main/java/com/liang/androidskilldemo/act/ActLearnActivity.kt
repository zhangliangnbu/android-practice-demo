package com.liang.androidskilldemo.act

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_act_learn.*

class ActLearnActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ActLearnSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCrate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_learn)
        btn_to_next.setOnClickListener { startActivity(Intent(this, ActLearnNextActivity::class.java)) }
        // new task
        btn_to_first_new_stack.setOnClickListener {
            val intent = Intent(this, ActLearnNextActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d(TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState, outPersistentState)
    }


}
