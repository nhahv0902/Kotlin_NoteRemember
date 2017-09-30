package com.nhahv.noteremember.ui.loadpicture.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by nhahv on 10/1/17.
 */
data class PicturePicker(val path: String?, var isPicked: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.let {
            it.writeString(path)
            it.writeByte(if (isPicked) 1.toByte() else 0.toByte())
        }

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<PicturePicker> {
        override fun createFromParcel(parcel: Parcel): PicturePicker {
            return PicturePicker(parcel)
        }

        override fun newArray(size: Int): Array<PicturePicker?> {
            return arrayOfNulls(size)
        }
    }
}