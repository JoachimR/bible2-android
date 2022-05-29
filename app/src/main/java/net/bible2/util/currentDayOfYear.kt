package net.bible2.util

import java.util.Calendar
import net.bible2.DayOfYear

fun currentDayOfYear(): DayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
