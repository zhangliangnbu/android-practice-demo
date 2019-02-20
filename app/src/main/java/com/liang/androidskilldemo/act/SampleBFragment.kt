package com.liang.androidskilldemo.act


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.fragment_sample_b.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SampleBFragment : Fragment() {
    private val dialog = SampleDialogFragment()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG, "onAttach -> $context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate -> $activity")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated  -> $activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_show_dialog_fragment.setOnClickListener {
            dialog.show(activity!!.supportFragmentManager!!, "dialog_sample")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_b, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

    companion object {
        private const val TAG = "SampleFragmentB"
    }


}
