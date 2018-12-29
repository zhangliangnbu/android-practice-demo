package com.liang.androidskilldemo

import android.app.Presentation
import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Display
import kotlinx.android.synthetic.main.activity_presentation_sample.*

class PresentationSampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PresentationSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation_sample)

        btn_show_presentation.setOnClickListener {
            logDisplays()
        }
    }

    private fun logDisplays() {
        tv_displays.text = ""
        val dm = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val pds = dm.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)
        pds.forEach { pd ->
            val text = "name->${pd.name}\n"
                    .plus("id->${pd.displayId}\n")
                    .plus("width->${pd.width}\n")
                    .plus("height->${pd.height}\n\n")
            tv_displays.text = tv_displays.text.toString().plus(text)
        }

        if(pds.isNotEmpty()) {
            val mp = MyPresentation(this, pds.first())
            mp.show()
        }
    }

    class MyPresentation(context: Context, display: Display):Presentation(context, display) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.presentation_sample)

        }

    }
}
