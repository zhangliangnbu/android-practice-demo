package com.liang.androidskilldemo.ipc

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by zhangliang on 2019/2/18.
 * Profile https://zhangliangnbu.github.io
 */
data class ParcelableBook(var number: String?, var name: String?, var price: Float): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(number)
        parcel.writeString(name)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableBook> {
        override fun createFromParcel(parcel: Parcel): ParcelableBook {
            return ParcelableBook(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableBook?> {
            return arrayOfNulls(size)
        }
    }
}