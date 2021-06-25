package com.liang.androidskilldemo.act


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.fragment_sample_c.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SampleCFragment : Fragment() {

    private var TAG = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.d(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = arguments?.getString(FRAGMENT_NAME, "Default") ?: return
        this.TAG = text
        Logger.d(TAG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Logger.d(TAG)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvFragmentTag.text = TAG
        Logger.d(TAG)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Logger.d(TAG)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d(TAG)
    }

    override fun onStart() {
        super.onStart()
        Logger.d(TAG)
    }

    override fun onResume() {
        super.onResume()
        Logger.d("$TAG: isHidden = $isHidden")
    }

    override fun onPause() {
        super.onPause()
        Logger.d(TAG)
    }

    override fun onStop() {
        super.onStop()
        Logger.d(TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.d(TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG)
    }

    override fun onDetach() {
        super.onDetach()
        Logger.d(TAG)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Logger.d("$TAG: isHidden = $isHidden")
    }

    companion object {
        public const val FRAGMENT_NAME = "fragment_name"
    }


}
