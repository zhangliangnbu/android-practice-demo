package com.liang.androidskilldemo.act


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.d(TAG, "onAttach -> $context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d(TAG, "onCreate -> $activity")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.d(TAG, "onActivityCreated  -> $activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_show_dialog_fragment.setOnClickListener {
            dialog.show(activity!!.supportFragmentManager!!, "dialog_sample")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Logger.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_b, container, false)
    }

    override fun onStart() {
        super.onStart()
        Logger.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Logger.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Logger.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Logger.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Logger.d(TAG, "onDetach")
    }

    companion object {
        private const val TAG = ""
    }


}
