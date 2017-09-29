package com.nhahv.noteremember.ui.create

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityCreateBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity
import com.nhahv.noteremember.ui.loadpicture.imagescreen.ImageScreenActivity
import com.nhahv.noteremember.util.setupToolbar
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.layout_create_note.*
import java.util.*

/**
 * Create UI.
 */
class CreateActivity : LifecycleAppcompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private lateinit var viewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this)
                .get(CreateViewModel::class.java)
        val binding: ActivityCreateBinding = DataBindingUtil.setContentView(this, R.layout.activity_create)
        binding.viewModel = viewModel
        setupToolbar(toolbar)
        event()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_pick_image -> startActivity<ImageScreenActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun event() {
        doneCreateNote.setOnClickListener {}
        previewImage.setOnClickListener {}
        dayOfMonth.setOnClickListener { onDatePicker() }
        dayOfWeek.setOnClickListener { onDatePicker() }
        monthOfYear.setOnClickListener { onDatePicker() }
        timeOfDay.setOnClickListener { onTimePicker() }
        searchPlace.setOnClickListener {} // onSearchPlaceAddress
    }

    // event setOnClick
    private fun onDatePicker() {
        val datePicker = DatePickerDialog.newInstance(
                this,
                viewModel.calendar.get(Calendar.YEAR),
                viewModel.calendar.get(Calendar.MONTH),
                viewModel.calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.accentColor = Color.parseColor("#F06292")
        datePicker.show(fragmentManager, "")
    }

    private fun onTimePicker() {
        val timePicker = TimePickerDialog.newInstance(
                this,
                viewModel.calendar.get(Calendar.HOUR_OF_DAY),
                viewModel.calendar.get(Calendar.MINUTE),
                false
        )
        timePicker.accentColor = Color.parseColor("#F06292")
        timePicker.version = TimePickerDialog.Version.VERSION_2
        timePicker.show(fragmentManager, "")
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        viewModel.updateTime(hourOfDay, minute)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewModel.updateDate(year, monthOfYear, dayOfMonth)
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
