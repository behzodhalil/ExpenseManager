package com.example.expensemanager.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class TimerHelper {

    var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("American/New_York"))

    init {
        sdf.timeZone = TimeZone.getTimeZone("American/New_York")
    }

    fun getCurrentDateTimeStr(): String {
        val currentDate = cal.time
        return sdf.format(currentDate)
    }

    fun getNotificationUniqueId(): Int {
        return cal.timeInMillis.toInt()
    }
}