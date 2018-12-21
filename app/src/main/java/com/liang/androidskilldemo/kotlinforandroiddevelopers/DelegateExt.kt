package com.liang.androidskilldemo.kotlinforandroiddevelopers

import android.content.Context

/**
 * created by zhangliang on 2018/12/21
 * profile: zhangliangnbu@163.com
 */
object DelegateExt {
    fun<T : Any> preference(context: Context, name: String, default: T) =
            Preference(context, name, default)
}