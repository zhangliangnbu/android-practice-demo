package com.liang.androidskilldemo.ipc

import android.os.Environment
import android.os.Parcel
import android.os.Parcelable
import com.liang.androidskilldemo.BuildConfig
import java.io.*

/**
 * Created by zhangliang on 2019/2/18.
 * Profile https://zhangliangnbu.github.io
 */
class Serializer {

    /**
     * 持久化Serializable对象
     */
    fun saveSerializable(so: Serializable) {
        val out = ObjectOutputStream(FileOutputStream(getFilePath(USER_CACHE_FILE)))
        out.writeObject(so)
        out.close()
    }

    fun getSerializable(): Serializable {
        val `in` = ObjectInputStream(FileInputStream(getFilePath(USER_CACHE_FILE)))
        val so = `in`.readObject() as Serializable
        `in`.close()
        return so
    }

    /**
     * 直接持久化非serializable对象，抛出异常
     * throw java.io.NotSerializableException
     */
    private fun persistAny(o: Any) {
        val out = ObjectOutputStream(FileOutputStream(getFilePath(USER_ANY_CACHE_FILE)))
        out.writeObject(o)
        out.close()
    }

    private fun getAny(): Any {
        val `in` = ObjectInputStream(FileInputStream(getFilePath(USER_ANY_CACHE_FILE)))
        val so = `in`.readObject()
        `in`.close()
        return so
    }

    /**
     * 持久化Parcelable对象到disk, 但官方不建议这样做，理由如下：
     * changes in the underlying implementation of any of the data in the Parcel can render older data unreadable.
     */
    fun saveParcelable(parcelable: Parcelable) {
        val parcel = Parcel.obtain()
        parcel.writeParcelable(parcelable, 0)
        val out = DataOutputStream(FileOutputStream(getFilePath(USER_CACHE_FILE2)))
        out.write(parcel.marshall())
        parcel.recycle()
    }

    fun getParcelable(): Parcelable? {
        val ino = DataInputStream(FileInputStream(getFilePath(USER_CACHE_FILE2)))
        val data = ino.readBytes()
        val parcel = Parcel.obtain()
        parcel.unmarshall(data, 0, data.size)
        parcel.setDataPosition(0)
        val parcelable =  parcel.readParcelable<Parcelable>(Thread.currentThread().contextClassLoader)
        ino.close()
        parcel.recycle()
        return parcelable
    }

    private fun getFilePath(filePath: String): String {
        val dir = "${Environment.getExternalStorageDirectory()}/${BuildConfig.APPLICATION_ID}"
        val dirFile = File(dir)
        if(!dirFile.exists()) {
            dirFile.mkdirs()
        }
        return "$dir/$filePath"
    }

    companion object {
        private const val USER_CACHE_FILE = "serial_cache.txt"
        private const val USER_ANY_CACHE_FILE = "any_cache.txt"
        private const val USER_CACHE_FILE2 = "parcelable_cache.txt"
    }
}



