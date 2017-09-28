package com.nhahv.noteremember.ui.create

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityCreateBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity

/**
 * Create UI.
 */
class CreateActivity : LifecycleAppcompatActivity() {

    private lateinit var mViewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(CreateViewModel::class.java)
        val binding: ActivityCreateBinding = DataBindingUtil.setContentView(this, R.layout.activity_create)
        binding.viewModel = mViewModel
    }

    class MyObserver : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {

        }
    }
}
