package com.liang.androidskilldemo.act

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_act_learn.*

/**
 * 状态变更
 */
class ActLearnActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ActLearnSample"
    }

    private var testInt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_learn)
        btn_to_next.setOnClickListener { startActivity(Intent(this, ActLearnNextActivity::class.java)) }
        // new task
        btn_to_first_new_stack.setOnClickListener {
            val intent = Intent(this, ActLearnNextActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
            }
            startActivity(intent)
        }

        btn_changeTest.setOnClickListener {
            testInt = testInt.inc()
            btn_showTest.text = testInt.toString()
        }

        btn_finish.setOnClickListener { finish() }

        Logger.d("testInt = $testInt" + ", " +
                "savedInstanceState testInt = " + savedInstanceState?.get("testInt")?.toString())

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Logger.d("orientation = ${newConfig.orientation}")
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
        super.onRestoreInstanceState(savedInstanceState)
        Logger.d("testInt = " + savedInstanceState.get("testInt")?.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Logger.d(TAG)
        super.onSaveInstanceState(outState)
        outState.putInt("testInt", testInt)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Logger.d(TAG)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onNewIntent(intent: Intent?) {
        Logger.d(TAG)
        super.onNewIntent(intent)
    }


}
