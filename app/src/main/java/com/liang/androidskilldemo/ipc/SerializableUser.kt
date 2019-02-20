package com.liang.androidskilldemo.ipc

import java.io.Serializable

/**
 * Created by zhangliang on 2019/2/18.
 * Profile https://zhangliangnbu.github.io
 */
data class SerializableUser(var userId: Int, var userName: String, var isMale: Boolean) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
