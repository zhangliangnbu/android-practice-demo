package com.liang.androidskilldemo.visible

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.appcompat.app.AppCompatActivity
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
