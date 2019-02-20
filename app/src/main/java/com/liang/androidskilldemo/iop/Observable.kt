package com.liang.androidskilldemo.iop

/**
 * created by zhangliang on 2019/1/27
 * profile https://zhangliangnbu.github.io
 *
 * observable object or subject
 */
interface Observable<T> {
    fun addObserver(observer:T)
    fun removeObserver(observer: T)
    fun removeAllObservers()
}