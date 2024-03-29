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
import kotlinx.android.synthetic.main.activity_grid_layout.*
import kotlinx.android.synthetic.main.item_text.view.*

class GridLayoutActivity : Activity() {

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
        setContentView(R.layout.activity_grid_layout)

        val texts = listOf("播报", "市场分析", "介绍机会", "介绍机会", "贵州茅台","市场分析", "介绍机会", "原创", "拍摄", "贵州茅台", "逢低买进", "高抛低吸", "看涨", "低吸", "低吸","看涨", "低吸", "低吸", "低吸", "低吸", "看涨", "低吸", "低吸")
        val textAdapter = TextAdapter(texts)

        rv_items.apply {
            val glm = GridLayoutManager(context, 12, RecyclerView.VERTICAL, false)
            glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(p0: Int): Int {



                    return texts[p0].length
                }
            }

            layoutManager = glm

//            addItemDecoration(EvenItemDecoration(EvenGridLayoutActivity.dp2px(context, 10), 4))
            adapter = textAdapter
        }


    }

    class TextAdapter(private val images: List<String> = listOf()) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            // 动态设置图片大小 保证宽高相等
//            val ivSize = (EvenGridLayoutActivity.getScreenContentWidth(parent.context) - EvenGridLayoutActivity.dp2px(
//                parent.context,
//                10
//            ) * 5) / 4
////            view.iv_image.layoutParams.width = 1000
//            view.tv_tag.layoutParams.height = ivSize
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.view.tv_tag.text = images[position]
        }

        override fun getItemCount(): Int {
            return images.size
        }

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    }

    class EvenItemDecoration(private val space: Int, private val column: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            // 每个span分配的间隔大小
            val spanSpace = space * (column + 1) / column
            // 列索引
            val colIndex = position % column
            // 列左、右间隙
            outRect.left = space * (colIndex + 1) - spanSpace * colIndex
            outRect.right = spanSpace * (colIndex + 1) - space * (colIndex + 1)
            // 行间距
            if (position >= column) {
                outRect.top = space
            }
        }
    }
}
