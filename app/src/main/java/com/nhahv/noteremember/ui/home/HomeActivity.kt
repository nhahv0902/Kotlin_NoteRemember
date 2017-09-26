package com.nhahv.noteremember.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityHomeBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity
import com.nhahv.noteremember.util.removeShiftingMode
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : LifecycleAppcompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        navigation.removeShiftingMode()
    }
}
