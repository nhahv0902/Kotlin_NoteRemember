package com.nhahv.noteremember.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import com.nhahv.noteremember.ui.create.photoview.PhotoViewerViewModel
import com.nhahv.noteremember.ui.home.HomeViewModel
import com.nhahv.noteremember.ui.loadpicture.imagescreen.ImageScreenViewModel
import com.nhahv.noteremember.ui.loadpicture.model.PictureLoader
import com.nhahv.noteremember.util.Navigator

/**
 * Created by nhahv0902 on 9/28/17.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val activity: AppCompatActivity
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(activity.supportFragmentManager)
                isAssignableFrom(ImageScreenViewModel::class.java) ->
                    ImageScreenViewModel(PictureLoader(activity.applicationContext), Navigator(activity))
                isAssignableFrom(PhotoViewerViewModel::class.java) ->
                    PhotoViewerViewModel(Navigator(activity))
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(activity: AppCompatActivity) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(activity).also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}