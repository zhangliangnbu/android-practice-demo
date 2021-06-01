package com.liang.androidskilldemo.kotlinforandroiddevelopers

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * created by zhangliang on 2018/12/21
 * profile: zhangliangnbu@163.com
 * sharepreferences 封装
 * 使用了属性代理和泛型
 */
class Preference<T>(val context: Context, val name:String, private val default: T)
    :ReadWriteProperty<Any?, T> {

    private val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun<T> findPreference(name: String, default: T):T = with(prefs) {
        val res: Any? = when(default) {
            is Int -> getInt(name, default)
            is Long -> getLong(name, default)
            is Float -> getFloat(name, default)
            is String -> getString(name, default)
            is Boolean -> getBoolean(name, default)
            else -> throw IllegalAccessException("this type can not be gotten from preference")
        }
        return@with res as T
    }

    private fun<U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when(value) {
            is Int -> putInt(name, value)
            is Long -> putLong(name, value)
            is Float -> putFloat(name, value)
            is String -> putString(name, value)
            is Boolean -> putBoolean(name, value)
            else -> throw IllegalAccessException("this type can not be saved into preference")
        }.apply()
    }
}

