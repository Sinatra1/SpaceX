package com.vladislav.shumilov.launch_ui.ui

import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.common.LaunchInteractor

@FragmentScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractor) : ViewModel() {

    fun getList() {
        launchInteractor.getList().subscribe { t1, t2 ->

        }
    }
}