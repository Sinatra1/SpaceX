package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import javax.inject.Inject

@FragmentScope
class LaunchDetailViewModelFactory @Inject constructor(
    private val launchInteractor: LaunchInteractorImpl,
    private val resources: Resources
) :
    ViewModelProvider.Factory {

    @Suppress(UNCHECKED_CAST)
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(LaunchDetailViewModel::class.java)) {
            LaunchDetailViewModel(launchInteractor, resources) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}