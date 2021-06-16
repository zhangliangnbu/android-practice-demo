package com.liang.androidskilldemo.act

import android.content.ClipData.Item
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liang.androidskilldemo.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_transaction_backstack.*


class FragmentTransactionActivity : AppCompatActivity() {

    private var count = 0
    private var detachedFragment: Fragment? = null
    private var hiddenFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_backstack)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_container, createFragment())
        }.commit()

        btnAddTwoFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_container, createFragment())
                    .add(R.id.fl_container, createFragment())
                    .addToBackStack("CD$count")
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
                    .replace(R.id.fl_container, createFragment())
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

        btnHideFragment.setOnClickListener {
            hiddenFragment = supportFragmentManager.findFragmentById(R.id.fl_container)
                    ?: return@setOnClickListener
            supportFragmentManager.beginTransaction()
                    .hide(hiddenFragment!!)
                    .commit()
        }

        btnShowFragment.setOnClickListener {
            if (hiddenFragment == null) return@setOnClickListener
            supportFragmentManager.beginTransaction()
                    .show(hiddenFragment!!)
                    .commit()
        }

        btnLog.setOnClickListener {
            val count = supportFragmentManager.backStackEntryCount
            Logger.d("backStackEntryCount = $count" )
        }
    }

    private fun createName(): String {
        count = count.inc()
        return count.toString()
    }

    private fun createFragment(): Fragment {
        val bundle = Bundle().apply {
            putString(SampleCFragment.FRAGMENT_NAME, createName())
        }
        val fragment = SampleCFragment().apply {
            arguments = bundle
        }
        return fragment
    }
}