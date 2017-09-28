package com.nhahv.noteremember.ui.home

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import android.support.v4.app.FragmentManager

/**
 * Created by nhahv0902 on 9/28/17.
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModeFactory private constructor(private val fm: FragmentManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(fm)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: HomeViewModeFactory? = null

        fun getInstance(fm: FragmentManager) =
                INSTANCE ?: synchronized(HomeViewModeFactory::class.java) {
                    INSTANCE ?: HomeViewModeFactory(fm).also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}