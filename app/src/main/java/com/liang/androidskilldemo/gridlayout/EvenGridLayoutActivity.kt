package com.liang.androidskilldemo.gridlayout

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_even_grid_layout.*
import kotlinx.android.synthetic.main.item_image.view.*


class EvenGridLayoutActivity : Activity() {

    companion object {
        fun dp2px(context: Context, dps: Int): Int {
            return Math.round(dps.toFloat() * getDensityDpiScale(context))
        }

        private fun getDensityDpiScale(context: Context): Float {
            return context.resources.displayMetrics.xdpi / 160.0f
        }

        fun getScreenContentWidth(context: Context): Int {
            val displayMetrics = context.resources.displayMetrics
            return displayMetrics.widthPixels
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_even_grid_layout)


        val images = listOf(
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher_round
        )

        val imageAdapter = ImageAdapter(images)

        rv_items.apply {
            layoutManager = GridLayoutManager(context, 4, RecyclerView.VERTICAL, false)
            addItemDecoration(EvenItemDecoration(dp2px(context, 20), 4))
            adapter = imageAdapter
        }
    }

    class ImageAdapter(private val images: List<Int> = listOf()) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            // 动态设置图片大小 保证宽高相等
            val ivSize = (getScreenContentWidth(parent.context) - dp2px(parent.context, 10) * 5) / 4
//            view.iv_image.layoutParams.width = 1000
            view.iv_image.layoutParams.height = ivSize
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.view.iv_image.setImageResource(images[position])
        }

        override fun getItemCount(): Int {
            return images.size
        }

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    }

    class EvenItemDecoration(private val space: Int, private val column: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//            val position = parent.getChildAdapterPosition(view)
//            // 每列分配的padding大小，包括左padding和右padding，如果图片足够宽则padding为0
//            val colPadding = space * (column + 1) / column
//            // 列索引
//            val colIndex = position % column
//            // 列左、右padding。先计算每列的左padding，然后找出规律，右padding=space-左padding
//            outRect.left = space * (colIndex + 1) - colPadding * colIndex
//            outRect.right = colPadding * (colIndex + 1) - space * (colIndex + 1)
//            // 行间距
//            if (position >= column) {
//                outRect.top = space
//            }
            outRect.left = space
            outRect.right = space
        }
    }
}
