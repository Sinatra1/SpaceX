package com.vladislav.shumilov.launch_ui.ui

import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.launch_ui.common.LaunchInteractor

class LaunchesListViewModel(private val launchInteractor: LaunchInteractor) : ViewModel() {

    fun getList() {
        launchInteractor.getList()
    }
}