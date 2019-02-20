package com.liang.androidskilldemo.iop

import java.util.concurrent.Executor

/**
 * created by zhangliang on 2019/1/27
 * profile https://zhangliangnbu.github.io
 */
abstract class ObservableTask:Observable<ObservableTask.Callback>, Executor {


    override fun addObserver(observer: Callback) {
    }

    override fun removeObserver(observer: Callback) {
    }

    override fun removeAllObservers() {
    }

    interface Callback {
        fun onSuccess(data:Any?)
        fun onFail(errorType:String, data: Any?)
        fun onProgress(current:Int, total:Int)
    }
}