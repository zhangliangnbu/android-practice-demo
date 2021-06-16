package com.liang.androidskilldemo.visible


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.fragment_visible_sample.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VisibleSampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VisibleSampleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visible_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_content.text = param1 ?: "--"
    }

    override fun onResume() {
        super.onResume()
        Logger.d(tag(), "onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d(tag(), "onPause")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Logger.d(tag(), "setUserVisibleHint isVisibleToUser:$isVisibleToUser")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Logger.d(tag(), "onHiddenChanged hidden:$hidden")
    }

    private fun tag(): String = "fragment-$param1->"


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VisibleSampleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                VisibleSampleFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
