package com.liang.androidskilldemo

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.appcompat.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_text_view.*

class TextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)

        // TextSwitcher
        ts_turn_comments.setInAnimation(this, R.anim.ts_fade_in)
        ts_turn_comments.setOutAnimation(this, R.anim.ts_fade_out)
        ts_turn_comments.setFactory {
            val t = TextView(this)
            t.setHorizontallyScrolling(true)
            t.gravity = Gravity.CENTER or Gravity.START
            return@setFactory t
        }
        ts_turn_comments.setCurrentText("还没有评论哦")

        var count = 1
        ts_turn_comments.setOnClickListener {
            ts_turn_comments.setCurrentText("")
            count++
            val text = "第$count 个用户: 这是第$count 条评论666啊，未来是否可期啊啊啊，是mmmmmm？"
            ts_turn_comments.setText(text)
        }

        // library TextBannerView
        val list = mutableListOf<String>()
        list.add("学好Java、Android、C#、C、ios、html+css+js")
        list.add("走遍天下都不怕！！！！！")
        list.add("不是我吹，就怕你做不到，哈哈不是我吹，就怕你做不到，哈哈不是我吹，就怕你做不到，哈哈")
        list.add("superluo")
        list.add("你是最棒的，奔跑吧孩子！")
        tv_banner.setDatas(list)

        // view anim
        val tvs = listOf(tv_anim, tv_anim2)
        val oas = listOf(
            ObjectAnimator.ofFloat(tvs[0], "translationX", 0f, -1440f).setDuration(5000),
            ObjectAnimator.ofFloat(tvs[1], "translationX", 0f, -1440f).setDuration(5000)
        )

        tvs[0].setText("不是我吹，就怕你做不到，你是最棒的，奔跑吧孩子0000111112222333！")
        oas[0].start()

        var ac = 0
        cl_anim.setOnClickListener {
            ac++
            val index = ac % 2
            val tv = tvs[index]
            val oa = oas[index]
            if(oa.isRunning) {
                oa.cancel()
//                oa.end()
            }

            val text = "第$ac 个用户: 这是第$ac 条评论666啊，未来是否可期啊啊啊，是么？"
            tv.text = text
            tv.setBackgroundColor(getColor(R.color.gray))

            tv.post {
                oa.setFloatValues(0f, -1440f - tv.width)
                oa.start()
            }
        }

        // dynamic
        animText(cl_anim2, 3)
    }

    private fun animText(cl:ConstraintLayout, textCount:Int) {
        val tvs = mutableListOf<TextView>()
        val oas = mutableListOf<ObjectAnimator>()
        for (i in 1..textCount) {
            // tv
            val tv = TextView(cl.context)
            tv.id = View.generateViewId()
            tv.setSingleLine()
            tv.gravity = Gravity.CENTER_VERTICAL or Gravity.START
            cl.addView(tv)
            val set = ConstraintSet()
            set.constrainWidth(tv.id, ConstraintSet.WRAP_CONTENT)
            set.connect(tv.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            set.connect(tv.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            set.connect(tv.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0)
            set.applyTo(cl)
            // oa
            val oa = ObjectAnimator.ofFloat(tv, "translationX", 0f, 0f).setDuration(5000)
            tvs.add(tv)
            oas.add(oa)
        }
//        cl.invalidate()

        var ac = 0
        cl.setOnClickListener {
            ac++
            val index = ac % textCount
            val tv = tvs[index]
            val oa = oas[index]
            if(oa.isRunning) {
                oa.cancel()
//                oa.end()
            }

            tv.text = "第$ac 个用户: 这是第$ac 条评论666啊，未来是否可期啊啊啊，是么？"
            tv.translationX = getWindowWidth()
            tv.setBackgroundColor(getColor(R.color.gray))

            tv.post {
                oa.setFloatValues(getWindowWidth(), -1f * tv.width)
                oa.start()
            }
        }
    }

    private fun getWindowWidth(): Float {
        val wm = this.windowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels * 1f
    }


}
