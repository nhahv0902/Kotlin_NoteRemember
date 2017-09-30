package com.nhahv.noteremember.util

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nhahv.noteremember.R
import com.nhahv.noteremember.ui.home.HomeViewModel
import com.nhahv.noteremember.ui.notebook.NotebookViewModel

/**
 * Created by nhahv0902 on 9/28/17.
 */


@BindingAdapter("bottomNavigation")
fun navigation(view: BottomNavigationView, position: Int) {
    view.selectedItemId = when (position) {
        0 -> R.id.navigation_notebook
        1 -> R.id.navigation_calendar
        2 -> R.id.navigation_search
        3 -> R.id.navigation_setting
        else -> R.id.navigation_notebook
    }
}

/*
* override Layout height in View
* in NoteCreationActivity
* */
@BindingAdapter("layout_height")
fun setLayoutHeight(view: View, height: Float) {
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter(value = *arrayOf("imageUrl", "imageUri", "bindError"), requireAll = false)
fun imageUrl(view: ImageView, url: String?, uri: Uri?, error: Drawable) {
    GlideApp.with(view.context)
            .load(url ?: uri)
            .error(error)
            .placeholder(error)
            .thumbnail(0.5F)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .centerCrop()
            .into(view)
}

@BindingAdapter("adapter", "currentItem")
fun currentItem(view: ViewPager, adapter: FragmentPagerAdapter?, position: Int) {
    adapter?.let {
        view.adapter = adapter
        view.currentItem = position
    }
}

/*
* Binding checked image in selected picture
*
* */

@BindingAdapter("isPicked")
fun isPicked(view: ImageView, isPicked: Boolean) {
    view.setImageResource(if (isPicked) R.drawable.ic_check_circle else R.drawable.ic_un_check_circle)
}


@BindingAdapter("swipeRefreshLayout", "refresh")
fun swipeRefreshLayout(view: SwipeRefreshLayout, viewModel: NotebookViewModel, isRefresh: Boolean) {
    view.setColorSchemeResources(R.color.colorPrimary)
    view.isRefreshing = isRefresh
    view.setOnRefreshListener {
        view.isRefreshing = true
        viewModel.onLoadNotebookData()
    }
}
