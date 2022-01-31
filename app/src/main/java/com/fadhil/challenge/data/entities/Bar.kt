package com.fadhil.challenge.data.entities

import android.os.Parcel
import android.os.Parcelable

data class Bar (
    var length: Double?,
    var width: Double?,
    var depth: Double?,
    var volume: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(length)
        parcel.writeValue(width)
        parcel.writeValue(depth)
        parcel.writeValue(volume)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bar> {
        override fun createFromParcel(parcel: Parcel): Bar {
            return Bar(parcel)
        }

        override fun newArray(size: Int): Array<Bar?> {
            return arrayOfNulls(size)
        }
    }
}