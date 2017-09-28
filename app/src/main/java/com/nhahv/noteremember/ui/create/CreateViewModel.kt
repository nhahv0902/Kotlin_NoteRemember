package com.nhahv.noteremember.ui.create

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt


/**
 * Exposes the data to be used in the Create screen.
 */

class CreateViewModel : ViewModel() {

    val imageHashMap: HashMap<String, String> = HashMap()
    val imageSize: ObservableInt = ObservableInt(0)
    val adapter: ObservableField<ImageViewPagerAdapter> = ObservableField(ImageViewPagerAdapter(imageHashMap))


}
