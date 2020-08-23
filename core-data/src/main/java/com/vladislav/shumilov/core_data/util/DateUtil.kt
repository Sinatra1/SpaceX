package com.vladislav.shumilov.core_data.util

import java.text.SimpleDateFormat
import java.util.*

private const val HUMAN_DATE_FORMAT = "yyyy MMMM dd hh:mm"
private const val MILLIS = 1000L

private val humanSimpleDateTimeFormat = SimpleDateFormat(HUMAN_DATE_FORMAT, Locale.US)

fun unixTimeToHumanDateTime(unixTime: Long?) =
    if (unixTime == null) ""
    else humanSimpleDateTimeFormat.format(unixTimeToDate(unixTime))

private fun unixTimeToDate(unixTime: Long) = Date(unixTime * MILLIS)