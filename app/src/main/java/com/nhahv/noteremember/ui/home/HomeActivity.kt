package com.nhahv.noteremember.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityHomeBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity
import com.nhahv.noteremember.ui.ViewModelFactory
import com.nhahv.noteremember.ui.create.CreateActivity
import com.nhahv.noteremember.util.removeShiftingMode
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : LifecycleAppcompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, ViewModelFactory.getInstance(this))
                .get(HomeViewModel::class.java)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        navigation.removeShiftingMode()
        event()
    }

    override fun onResume() {
        super.onResume()
        viewModel.position.notifyChange()
    }

    private fun event() {
        createNote.setOnClickListener {
            startActivity<CreateActivity>()
        }

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_notebook -> {
                    viewModel.onSwitchNotebook()
                    viewPager.currentItem = 0
                    viewModel.setPosition(0)
                }
                R.id.navigation_calendar -> {
                    viewModel.onSwitchCalendar()
                    viewPager.currentItem = 1
                    viewModel.setPosition(1)
                }
                R.id.navigation_note_create -> startActivity<CreateActivity>()
                R.id.navigation_search -> {
                    viewPager.currentItem = 0
                    viewModel.setPosition(2)
                }
                R.id.navigation_setting -> {
                    viewModel.onSwitchSetting()
                    viewPager.currentItem = 2
                    viewModel.setPosition(3)
                }
            }
            true
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                viewModel.setPosition(position)

            }
        })
    }
}
