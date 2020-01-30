package com.vladislav.shumilov.core_domain.utils

infix fun Int.hasFlag(value: Int)  = this and value == value