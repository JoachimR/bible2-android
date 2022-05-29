package net.bible2.util

import android.content.Context
import android.text.format.DateUtils
import java.util.Calendar
import net.bible2.DayOfYear

fun textForDayOfYear(context: Context, dayOfYear: DayOfYear): String =
    DateUtils.formatDateTime(
        context,
        Calendar.getInstance().apply {
            set(Calendar.DAY_OF_YEAR, dayOfYear)
        }.timeInMillis,
        DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_WEEKDAY
    )
