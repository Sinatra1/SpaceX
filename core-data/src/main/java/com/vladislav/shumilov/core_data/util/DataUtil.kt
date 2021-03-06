package com.vladislav.shumilov.core_data.util

import java.util.*

const val UNCHECKED_CAST = "UNCHECKED_CAST"

fun generateRandomId() = UUID.randomUUID().toString()

fun strFirstKeyToUpper(str: String) =
    str.first().toUpperCase() + str.substring(1)