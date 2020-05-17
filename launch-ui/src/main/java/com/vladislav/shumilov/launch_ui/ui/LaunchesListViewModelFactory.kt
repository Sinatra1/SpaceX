package com.vladislav.shumilov.launch_ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import javax.inject.Inject

@FragmentScope
class LaunchesListViewModelFactory @Inject constructor(private val launchInteractor: LaunchInteractorImpl) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = if (modelClass.isAssignableFrom(
            LaunchesListViewModel::class.java
        )
    ) {
        LaunchesListViewModel(launchInteractor) as T
    } else {
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}