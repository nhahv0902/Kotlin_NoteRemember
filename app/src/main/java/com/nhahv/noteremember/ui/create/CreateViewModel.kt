package com.nhahv.noteremember.ui.create

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.nhahv.noteremember.data.Notebook
import com.nhahv.noteremember.ui.loadpicture.preview.PictureViewPager
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Exposes the data to be used in the Create screen.
 */

class CreateViewModel : ViewModel() {

    val calendar: Calendar = Calendar.getInstance()
    val imageSize: ObservableInt = ObservableInt(0)

    val imageHashMap: HashMap<String, String> = HashMap()
    val pictures: ArrayList<String> = ArrayList()
    val adapter: ObservableField<PictureViewPager> = ObservableField(PictureViewPager(pictures))

    val dayOfMonth: ObservableField<String> = ObservableField()
    val dayOfWeek: ObservableField<String> = ObservableField()
    val monthOfYear: ObservableField<String> = ObservableField()
    val notebook: ObservableField<Notebook> = ObservableField(Notebook())

    init {
        notebook().time = convertTimeToText(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        convertDateTime()
    }

    private fun convertTimeToText(hourOfDay: Int, minute: Int): String {
        val amPm = if (hourOfDay >= 12) "PM" else "AM"
        val hour = if (hourOfDay >= 20) "${hourOfDay - 12}" else if (hourOfDay in 12..19) "0${hourOfDay - 12}" else "0$hourOfDay"
        val minuteString = if (minute < 10) "0$minute" else "$minute"
        return "$hour:$minuteString $amPm"
    }

    private fun convertDateTime() {
        dayOfMonth.set("${calendar.get(Calendar.DAY_OF_MONTH)}")
        dayOfWeek.set(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()))
        monthOfYear.set("${calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())} ${calendar.get(Calendar.YEAR)}")
        notebook().date = calendar.timeInMillis
    }

    fun updateTime(hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MILLISECOND, minute)
        notebook().time = convertTimeToText(hourOfDay, minute)
        notebook.notifyChange()
    }

    fun updateDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        convertDateTime()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_CANCELED) return
        val images: ArrayList<String> = data?.extras!!.getStringArrayList("items")
        when (requestCode) {
            CreateActivity.REQUEST_CODE -> {
                images.filterNot { imageHashMap.containsKey(it) }
                        .forEach { imageHashMap.put(it, it) }
                pictures.clear()
                pictures.addAll(imageHashMap.values)
                imageSize.set(pictures.size)
                adapter.notifyChange()
            }
            CreateActivity.REQUEST_CODE_VIEWER -> {
                imageHashMap.clear()
                for (item in images) {
                    imageHashMap.put(item, item)
                }
                imageSize.set(imageHashMap.size)
                adapter.notifyChange()
            }
        }
    }

    private fun dayOfMonth() = dayOfMonth.get()
    private fun dayOfWeek() = dayOfWeek.get()
    private fun monthOfYear() = monthOfYear.get()
    private fun notebook() = notebook.get()


}
