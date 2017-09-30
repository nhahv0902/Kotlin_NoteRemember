package com.nhahv.noteremember.ui.loadpicture.preview

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.chrisbanes.photoview.PhotoView
import com.nhahv.noteremember.ui.loadpicture.model.PicturePicker
import com.nhahv.noteremember.util.GlideApp

/**
 * Created by nhahv on 10/1/17.
 */
class PictureViewPager(private val images: ArrayList<String>?) : PagerAdapter() {

    override fun isViewFromObject(p0: View?, p1: Any?): Boolean = p0 == p1


    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val photoView = PhotoView(container?.context)
        GlideApp.with(container?.context)
                .load(images?.get(position))
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(photoView)
        container?.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return photoView
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

    override fun getCount() = images?.size ?: 0
}