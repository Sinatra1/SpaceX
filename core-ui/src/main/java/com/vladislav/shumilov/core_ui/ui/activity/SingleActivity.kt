package com.vladislav.shumilov.core_ui.ui.activity

import androidx.navigation.NavController

interface SingleActivity {
    fun popBackStack()

    fun getNavigationController(): NavController
}