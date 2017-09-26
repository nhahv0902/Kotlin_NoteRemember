package com.nhahv.noteremember.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

/**
 * Created by nhahv0902 on 9/28/17.
 */
@SuppressLint("RestrictedApi")
fun BottomNavigationView.removeShiftingMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Log.e("Error", "Unable to get shift mode field", e)
    } catch (e: IllegalAccessException) {
        Log.e("Error", "Unable to change value of shift mode", e)
    }
}