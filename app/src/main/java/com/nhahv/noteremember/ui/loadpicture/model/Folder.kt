package com.nhahv.noteremember.ui.loadpicture.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by nhahv on 9/30/17.
 */

data class Folder(var name: String, var images: ArrayList<String>) : Parcelable {
  companion object {
    @JvmField val CREATOR: Parcelable.Creator<Folder> = object : Parcelable.Creator<Folder> {
      override fun createFromParcel(source: Parcel): Folder = Folder(source)
      override fun newArray(size: Int): Array<Folder?> = arrayOfNulls(size)
    }
  }

  constructor(source: Parcel) : this(
      source.readString(),
      source.createStringArrayList()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(name)
    dest.writeStringList(images)
  }
}