package com.vladislav.shumilov.core_data.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

fun flowInterval(period: Long, initialDelay: Long = 0) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}