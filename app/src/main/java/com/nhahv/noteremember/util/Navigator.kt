package com.nhahv.noteremember.util

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by nhahv on 10/1/17.
 */
class Navigator(val context: AppCompatActivity) {

    inline fun <reified T : AppCompatActivity> startActivity(bundle: Bundle) {
        val intent = Intent(context.applicationContext, T::class.java)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    inline fun <reified T : AppCompatActivity> startActivity() {
        context.startActivity(Intent(context.applicationContext, T::class.java))
    }
}