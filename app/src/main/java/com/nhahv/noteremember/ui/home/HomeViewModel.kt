package com.nhahv.noteremember.ui.home

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.nhahv.noteremember.ui.ViewPagerAdapter
import com.nhahv.noteremember.ui.calendar.CalendarFragment
import com.nhahv.noteremember.ui.notebook.NotebookFragment
import com.nhahv.noteremember.ui.setting.SettingFragment

/**
 * Exposes the data to be used in the Create screen.
 */

class HomeViewModel(fm: FragmentManager) : ViewModel() {

    private val fragments: ArrayList<Fragment> = ArrayList()
    var adapter: ObservableField<ViewPagerAdapter> = ObservableField(ViewPagerAdapter(fragments, fm))

    init {
        fragments.add(NotebookFragment.newInstance())
        fragments.add(SettingFragment.newInstance())
        fragments.add(CalendarFragment.newInstance())
        adapter.get().notifyDataSetChanged()
    }


    fun onSwitchNotebook() {}
    fun onSwitchCalendar() {}
    fun onCreateNote() {}
    fun onSwitchSetting() {}
}
