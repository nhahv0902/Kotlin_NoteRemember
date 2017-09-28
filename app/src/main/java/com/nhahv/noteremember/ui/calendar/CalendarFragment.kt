package com.nhahv.noteremember.ui.calendar

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.noteremember.databinding.FragmentCalendarBinding
import com.nhahv.noteremember.ui.setting.SettingFragment

/**
 * Notebook Screen.
 */
class CalendarFragment : LifecycleFragment() {

    private lateinit var viewModel: CalendarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(CalendarViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}