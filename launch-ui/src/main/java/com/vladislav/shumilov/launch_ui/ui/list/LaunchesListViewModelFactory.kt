package com.vladislav.shumilov.launch_ui.ui.list

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import javax.inject.Inject

@FragmentScope
internal class LaunchesListViewModelFactory @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val resources: Resources
) : ViewModelProvider.Factory {

    @Suppress(UNCHECKED_CAST)
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(LaunchesListViewModel::class.java)) {
            LaunchesListViewModel(launchInteractor, resources) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}