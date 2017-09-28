package com.nhahv.noteremember.util

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.nhahv.noteremember.R
import com.nhahv.noteremember.ui.home.HomeViewModel

/**
 * Created by nhahv0902 on 9/28/17.
 */

/*
* Binding bottom navigation view and viewpager in main
* MainActivity
* */
@BindingAdapter("bottomNavigation", "viewPager")
fun bottomNavigation(view: BottomNavigationView, viewModel: HomeViewModel, viewPager: ViewPager) {
    view.setOnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_notebook -> {
                viewModel.onSwitchNotebook()
                viewPager.currentItem = 0
            }
            R.id.navigation_calendar -> {
                viewModel.onSwitchCalendar()
                viewPager.currentItem = 1
            }
            R.id.navigation_note_create -> {
                viewModel.onCreateNote()
            }
            R.id.navigation_search -> {

            }
            R.id.navigation_setting -> {
                viewModel.onSwitchSetting()
                viewPager.currentItem = 4
            }

        }
        true
    }
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {}

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

        override fun onPageSelected(position: Int) {
            view.selectedItemId = when (position) {
                0 -> R.id.navigation_notebook
                1 -> R.id.navigation_calendar
                3 -> R.id.navigation_setting
                else -> R.id.navigation_notebook
            }
        }
    })
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
