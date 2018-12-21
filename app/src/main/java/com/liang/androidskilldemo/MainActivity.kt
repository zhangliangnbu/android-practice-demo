package com.liang.androidskilldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG : String = "MainActivity"
        private const val CATEGORY_SAMPLE : String = "com.liang.skilldemo.Sample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resolve_views.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ResolveInfoAdapter(context, getResolves())
        }
    }

    private fun getResolves() : List<ResolveInfo> {
        val intent = Intent()
        intent.action = Intent.ACTION_RUN
        intent.addCategory(CATEGORY_SAMPLE)
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    }

    fun toActivity(resolve: ResolveInfo) {
        val acInfo = resolve.activityInfo
        val componentName = ComponentName(acInfo.applicationInfo.packageName, acInfo.name)
        Log.d(TAG, "packageName/activityName : ${acInfo.applicationInfo.packageName}-/-${acInfo.name}")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.component = componentName
        startActivity(intent)
    }

    internal inner class ResolveInfoAdapter(context : Context, private val resolves : List<ResolveInfo>) : RecyclerView.Adapter<ResolveInfoAdapter.ResolveInfoViewHolder>() {
        private val inflater : LayoutInflater = LayoutInflater.from(context)
        private val pm : PackageManager = context.packageManager

        override fun getItemCount(): Int {
            return resolves.size
        }

        override fun onBindViewHolder(holder: ResolveInfoViewHolder, position: Int) {
            holder.label.text = resolves[position].loadLabel(pm)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResolveInfoViewHolder {
            return ResolveInfoViewHolder(inflater.inflate(R.layout.resolve_item, parent, false))
        }

        internal inner class ResolveInfoViewHolder(view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            val label : TextView = view.findViewById(R.id.tv_label) as TextView
            init {
                view.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                toActivity(resolves[adapterPosition])
            }
        }
    }
}
