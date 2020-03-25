package com.vladislav.shumilov.launch_ui.ui

import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_ui.common.LaunchInteractor

@LaunchScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractor) : ViewModel() {

    fun getList() {
        launchInteractor.getList().subscribe { t1, t2 ->
            
        }
    }
}