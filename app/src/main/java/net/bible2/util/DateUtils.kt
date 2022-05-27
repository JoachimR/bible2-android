package net.bible2.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * dateString: yyyy-MM-dd
 */
fun dateStringToDayOfYear(dateString: String): Int? =
    dateFromString(dateString)?.let { date ->
        Calendar.getInstance().apply {
            time = date
        }.get(Calendar.DAY_OF_YEAR)
    }

@SuppressLint("SimpleDateFormat")
private fun simpleDateFormat() = SimpleDateFormat("yyyy-MM-dd")

/**
 * dateString: yyyy-MM-dd
 */
fun dateFromString(dateString: String?): Date? {
    try {
        if (dateString != null) {
            return simpleDateFormat().parse(dateString)
        }
    } catch (e: Exception) {
        logWarn(e) { "Error when trying to parse date from string $dateString" }
    }
    return null
}
