package com.nhahv.noteremember.ui.loadpicture.imagescreen

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import com.nhahv.noteremember.R
import com.nhahv.noteremember.ui.BaseRecyclerAdapter
import com.nhahv.noteremember.ui.loadpicture.model.Folder
import com.nhahv.noteremember.ui.loadpicture.model.PictureLoader
import com.nhahv.noteremember.ui.loadpicture.model.PicturePicker
import com.nhahv.noteremember.ui.loadpicture.preview.PreviewPictureActivity
import com.nhahv.noteremember.util.Navigator

/**
 * Created by nhahv0902 on 9/29/17.
 */
class ImageScreenViewModel(private val pictureLoader: PictureLoader, val navigator: Navigator) : ViewModel() {

    val isPicking: ObservableBoolean = ObservableBoolean(false)

    val picturePicked: ObservableArrayList<String> = ObservableArrayList()

    val folders: ArrayList<Folder> = ArrayList()
    val pictures: ArrayList<PicturePicker> = ArrayList()
    val adapterAlbum: ObservableField<BaseRecyclerAdapter<Folder>> = ObservableField(BaseRecyclerAdapter(folders, this, R.layout.item_album))
    val adapterPhotos: ObservableField<BaseRecyclerAdapter<PicturePicker>> = ObservableField(BaseRecyclerAdapter(pictures, this, R.layout.item_photos))


    fun onLoadImageFromSDCard() {
        folders.addAll(pictureLoader.loadPictures())
        adapterAlbum.get().notifyDataSetChanged()
        folders
                .flatMap { it.images }
                .mapTo(pictures) { PicturePicker(it) }
        adapterPhotos.get().notifyDataSetChanged()
    }


    fun onClickPicture(item: PicturePicker, position: Int) {
        if (isPicking.get()) {
            if (item.isPicked) {
                picturePicked.remove(item.path)
            } else {
                picturePicked.add(item.path)
            }
            item.isPicked = !item.isPicked
            adapterPhotos.get().notifyItemChanged(position)
        } else {
            val bundle = Bundle()
            bundle.putInt("position", position)
            val items: ArrayList<String> = ArrayList()
            for ((path) in pictures) {
                path?.let { items.add(it) }
            }
            bundle.putStringArrayList("items", items)
            navigator.startActivity<PreviewPictureActivity>(bundle)
        }
    }

    fun onLongClickPicture(item: PicturePicker, position: Int): Boolean {
        if (isPicking.get()) {
            if (item.isPicked) {
                picturePicked.remove(item.path)
            } else {
                picturePicked.add(item.path)
            }
            item.isPicked = !item.isPicked
            adapterPhotos.get().notifyItemChanged(position)
        } else {
            isPicking.set(true)
            picturePicked.add(item.path)
            item.isPicked = true
            adapterPhotos.get().notifyItemChanged(position)
        }
        return true
    }

    fun clickCancel() {
        isPicking.set(false)
        for (item in pictures) {
            item.isPicked = false
        }
        adapterPhotos.notifyChange()
        picturePicked.clear()
    }

}