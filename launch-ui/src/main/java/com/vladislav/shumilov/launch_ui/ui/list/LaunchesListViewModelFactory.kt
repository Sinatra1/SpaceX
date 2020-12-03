package com.vladislav.shumilov.launch_ui.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import javax.inject.Inject

@FragmentScope
class LaunchesListViewModelFactory @Inject constructor(private val launchInteractor: LaunchInteractor) :
    ViewModelProvider.Factory {

    @Suppress(UNCHECKED_CAST)
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(LaunchesListViewModel::class.java)) {
            LaunchesListViewModel(launchInteractor) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}