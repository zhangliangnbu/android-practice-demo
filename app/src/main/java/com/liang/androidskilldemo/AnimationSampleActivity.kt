package com.liang.androidskilldemo

import android.animation.AnimatorInflater
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_animation_sample.*

class AnimationSampleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AnimationSample"
    }

    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var animatedVectorDrawable: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_sample)

        // property animation
        // layout change transition
//        cl_transition.layoutTransition
        tv_first.visibility = View.VISIBLE
        tv_second.visibility = View.GONE
        btn_transition.setOnClickListener {
            showOrHide(tv_first)
            showOrHide(tv_second)
        }

        // state list animator
        // button 默认不能点击 todo???
        btn_state_list_animator.setOnClickListener {
            Toast.makeText(this, "点击啦", Toast.LENGTH_SHORT).show()
        }

        // key frame animation
        btn_key_frame.setOnClickListener {
            val kf0 = Keyframe.ofFloat(0f, 0f)
            val kf1 = Keyframe.ofFloat(0.5f, 360f)
            val kf2 = Keyframe.ofFloat(1f, 0f)
            val pvh = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)
            ObjectAnimator.ofPropertyValuesHolder(it, pvh).apply {
                duration = 500
            }.start()
        }

        // view property animator for multi animation
        btn_view_property_animator.setOnClickListener {
            it.animate()
                    .translationXBy(50f)
                    .translationYBy(100f)
                    .translationZBy(3f)
        }

        // by xml
        btn_animation_by_xml.setOnClickListener {
            val oa = AnimatorInflater.loadAnimator(this, R.animator.property_left_to_right) as ObjectAnimator
            oa.apply {
                target = it
                start()
            }
        }

        // -----drawable animation----
        // animation drawable
        iv_animation_drawable.apply {
            setBackgroundResource(R.drawable.al_animation_drawable_smaple)
            animationDrawable = background as AnimationDrawable
        }
        iv_animation_drawable.setOnClickListener {
            if (animationDrawable.isRunning) {
                animationDrawable.stop()
            }
            animationDrawable.start()
        }

        iv_anim_vector_drawable.apply {
            animatedVectorDrawable = background as AnimatedVectorDrawable
        }
        iv_anim_vector_drawable.setOnClickListener {
            if(animatedVectorDrawable.isRunning) {
                animatedVectorDrawable.stop()
            }
            animatedVectorDrawable.start()
        }

    }


    private fun showOrHide(view: View) {
        view.apply {
            visibility = if (visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }
}
