package com.nhahv.noteremember.ui.create

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.chrisbanes.photoview.PhotoView
import com.nhahv.noteremember.util.GlideApp

/**
 * Created by nhahv0902 on 9/29/17.
 */
class ImageViewPagerAdapter(imagesHash: HashMap<String, String>) : PagerAdapter() {

    private var images: ArrayList<String> = ArrayList()

    init {
        images.addAll(imagesHash.values)
    }

    override fun isViewFromObject(p0: View?, p1: Any?): Boolean {
        return p0 == p1
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val photoView = PhotoView(container?.context)
        GlideApp.with(container?.context)
                .load(images[position])
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

    override fun getCount() = images.size

    fun update(imagesMap: HashMap<String, String>) {
        images.clear()
        images.addAll(imagesMap.values)
        notifyDataSetChanged()
    }
}