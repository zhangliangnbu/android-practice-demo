package com.liang.androidskilldemo.ipc

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by zhangliang on 2019/2/18.
 * Profile https://zhangliangnbu.github.io
 */
class ParcelableUser() : Parcelable {

    var userId: Int = 0
    var userName: String = ""
    var isMale: Boolean = false
    var book: ParcelableBook? = null

    constructor(userId: Int, userName: String, isMale: Boolean, book: ParcelableBook) : this() {
        this.userId = userId
        this.userName = userName
        this.isMale = isMale
        this.book = book
    }

    // 反序列化
    constructor(parcel: Parcel) : this() {
        this.userId = parcel.readInt()
        this.userName = parcel.readString() ?: ""
        this.isMale = parcel.readInt() == 1
        this.book = parcel.readParcelable(Thread.currentThread().contextClassLoader)
    }

    // 序列化
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(userName)
        parcel.writeInt(if (isMale) 1 else 0)
        parcel.writeParcelable(book, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ParcelableUser(userId=$userId, userName='$userName', isMale=$isMale, book=$book)"
    }


    companion object CREATOR : Parcelable.Creator<ParcelableUser> {
        override fun createFromParcel(parcel: Parcel): ParcelableUser {
            return ParcelableUser(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableUser?> {
            return arrayOfNulls(size)
        }
    }


}