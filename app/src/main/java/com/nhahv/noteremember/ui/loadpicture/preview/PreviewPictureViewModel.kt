package com.nhahv.noteremember.ui.loadpicture.preview

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.nhahv.noteremember.ui.loadpicture.model.PicturePicker

/**
 * Created by nhahv on 9/30/17.
 */
class PreviewPictureViewModel : ViewModel() {

    val images: ArrayList<String> = ArrayList()
    val position: ObservableInt = ObservableInt(0)
    val adapter: ObservableField<PictureViewPager> = ObservableField(PictureViewPager(images))

    fun update(items: ArrayList<String>, position: Int) {
        images.addAll(items)
        this.position.set(position)
        adapter.notifyChange()
    }
}