package com.vladislav.shumilov.core_data.util

import java.text.SimpleDateFormat
import java.util.*

private const val HUMAN_DATE_FORMAT = "yyyy MMMM dd"
private const val DATABASE_DATE_FORMAT = "yyyy-MM-dd"

private val humanSimpleDateFormat = SimpleDateFormat(HUMAN_DATE_FORMAT, Locale.US)
private val databaseSimpleDateFormat = SimpleDateFormat(DATABASE_DATE_FORMAT, Locale.US)

fun databaseDateToHumanDate(databaseDate: String?) =
    humanSimpleDateFormat.format(databaseDateStrToDate(databaseDate))

fun databaseDateStrToDate(databaseDate: String?) = databaseSimpleDateFormat.parse(databaseDate ?: "")