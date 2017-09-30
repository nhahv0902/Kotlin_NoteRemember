package com.nhahv.noteremember.ui.create.photoview

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.os.Bundle
import com.nhahv.noteremember.R
import com.nhahv.noteremember.ui.BaseRecyclerAdapter
import com.nhahv.noteremember.ui.loadpicture.preview.PreviewPictureActivity
import com.nhahv.noteremember.util.Navigator

/**
 * Created by nhahv on 10/1/17.
 */
class PhotoViewerViewModel(private val navigator: Navigator) : ViewModel() {

    val pictures: ArrayList<String> = ArrayList()
    var pictureSize: Int = 0
    val adapter: ObservableField<BaseRecyclerAdapter<String>> = ObservableField(BaseRecyclerAdapter(pictures, this, R.layout.item_photo_viewer))

    fun updatePictures(items: ArrayList<String>) {
        pictures.clear()
        pictures.addAll(items)
        adapter.notifyChange()
        pictureSize = pictures.size
    }

    fun onClickViewPicture(position: Int, item: String) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putStringArrayList("items", pictures)
        navigator.startActivity<PreviewPictureActivity>(bundle)
    }

    fun onClickDeletePicture(position: Int) {
        pictures.removeAt(position)
        adapter.get().notifyItemRemoved(position)
    }
}