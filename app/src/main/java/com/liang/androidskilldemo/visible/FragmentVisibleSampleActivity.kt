package com.liang.androidskilldemo.visible

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_fragment_visible_sample.*

class FragmentVisibleSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_visible_sample)

        vp_fragment.adapter = SampleAdapter(supportFragmentManager)
    }

    class SampleAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return VisibleSampleFragment.newInstance("page - $p0", "--")
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
