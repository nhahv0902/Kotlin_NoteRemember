package com.nhahv.noteremember.util

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by nhahv0902 on 9/28/17.
 */

@BindingAdapter("selected")
fun selected(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}
