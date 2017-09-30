package com.nhahv.noteremember.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by nhahv0902 on 9/25/17.
 */
@SuppressLint("Registered")
open class LifecycleAppcompatActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val registry: LifecycleRegistry
        get() = LifecycleRegistry(this)


    inline fun <reified T : AppCompatActivity> startActivity() {
        startActivity(Intent(applicationContext, T::class.java))
    }

    inline fun <reified T : AppCompatActivity> startActivity(bundle: Bundle) {
        val intent = Intent(applicationContext, T::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    inline fun <reified T : AppCompatActivity> startActivityForResult(requestCode: Int) {
        startActivityForResult(Intent(applicationContext, T::class.java), requestCode)
    }

    inline fun <reified T : AppCompatActivity> startActivityForResult(bundle: Bundle, requestCode: Int) {
        val intent = Intent(applicationContext, T::class.java)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    override fun getLifecycle() = registry
}