package com.liang.androidskilldemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.AutoTransition
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_animation_sample2.*

class AnimationSample2Activity : AppCompatActivity() {

    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private var mCurrentAnimator: Animator? = null

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private var mShortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animation_sample2)

        // cross fade
        sv_content.visibility = View.GONE
        val sat = resources.getInteger(android.R.integer.config_longAnimTime)
        layout_cross_fade.setOnClickListener {
            sv_content.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate().alpha(1f)
                        .setDuration(sat.toLong())
                        .setListener(null)
            }

            pb_loading_spinner.animate()
                    .alpha(0f)
                    .setDuration(sat.toLong())
                    .setListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            pb_loading_spinner.visibility = View.GONE
                        }
                    })

            sv_content.animate().start()
            pb_loading_spinner.animate().start()
        }

        // circular reveal
        iv_circular_anim.visibility = View.INVISIBLE
        tv_circular_anim.setOnClickListener {
            iv_circular_anim.apply {
                val cx = width / 2
                val cy = height / 2
                val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
                val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, radius)
                this.visibility = View.VISIBLE
                anim.start()
            }
        }

        // curve move
        tv_move_anim.setOnClickListener {
            iv_move_anim.apply {
                val path = Path().apply {
                    arcTo(0f, y-1000,  1000f, y, 270f, -180f, false)
                }
                ObjectAnimator.ofFloat(iv_move_anim, View.X, View.Y, path).apply {
                    duration = 5000
                    start()
                }
            }
        }

        // zoom
        // Hook up clicks on the thumbnail views.
        val thumb1View: View = findViewById(R.id.thumb_button_1)
        thumb1View.setOnClickListener {
            zoomImageFromThumb(thumb1View, R.drawable.ic_android5)
        }

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        // layout transition
        tv_layout_transition.setOnClickListener {
            val aScene = Scene.getSceneForLayout(scene_root, R.layout.a_scene, this)
            val bScene = Scene.getSceneForLayout(scene_root, R.layout.another_scene, this)
            val transition = AutoTransition().apply {
                duration = 5000
            }
            TransitionManager.go(bScene, transition)
        }
    }

    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        mCurrentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView: ImageView = findViewById(R.id.expanded_image)
        expandedImageView.setImageResource(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
                .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        mCurrentAnimator = AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = mShortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    mCurrentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    mCurrentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            mCurrentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            mCurrentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = mShortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        mCurrentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        mCurrentAnimator = null
                    }
                })
                start()
            }
        }
    }
}
