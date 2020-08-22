package com.vladislav.shumilov.core_ui.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator

fun NavController.navigate(fragmentClassName: Class<*>, bundle: Bundle? = null) {
    graph.forEach {
        if ((it as FragmentNavigator.Destination).className == fragmentClassName.name) {
            navigate(it.id, bundle)
            return
        }
    }
}