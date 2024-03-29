package com.liang.androidskilldemo.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.liang.androidskilldemo.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_fragment_sample.*

class FragmentSampleActivity : AppCompatActivity() {

    private var detachedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_container, SampleBFragment(), "B")
        }.commit()

        btnAddTwoFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_container, SampleCFragment(), "C")
                    .add(R.id.fl_container, SampleDFragment(), "D")
                    .addToBackStack("CD")
                    .commit()
        }

        btnRemoveCurrentFragment.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fl_container)
                    ?: return@setOnClickListener
            supportFragmentManager.beginTransaction()
                    .remove(currentFragment)
                    .commit()
        }

        btnReplaceFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .show(SampleCFragment())
                    .hide(SampleCFragment())
                    .replace(R.id.fl_container, SampleCFragment())
                    .detach(SampleCFragment())
                    .attach(SampleCFragment())
                    .commit()
        }

        btnDetachFragment.setOnClickListener {
            detachedFragment = supportFragmentManager.findFragmentById(R.id.fl_container)
                    ?: return@setOnClickListener
            supportFragmentManager.beginTransaction()
                    .detach(detachedFragment!!)
                    .commit()
        }

        btnAttachFragment.setOnClickListener {
            if (detachedFragment == null) return@setOnClickListener
            supportFragmentManager.beginTransaction()
                    .attach(detachedFragment!!)
                    .commit()
        }

        btnLog.setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount
            Logger.d("backStackEntryCount = $count" )
        }

    }
}
