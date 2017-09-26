package com.nhahv.noteremember.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

/**
 * Created by nhahv0902 on 9/25/17.
 */
@SuppressLint("Registered")
open class LifecycleAppcompatActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val registry: LifecycleRegistry
        get() = LifecycleRegistry(this)

    override fun getLifecycle() = registry
}