package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.di.LAUNCH_DETAIL_ID
import javax.inject.Inject
import javax.inject.Named

class LaunchDetailViewModelFactory @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val resources: Resources,
    @Named(LAUNCH_DETAIL_ID)
    private val launchId: String
) :
    ViewModelProvider.Factory {

    @Suppress(UNCHECKED_CAST)
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(LaunchDetailViewModel::class.java)) {
            LaunchDetailViewModel(launchInteractor, resources, launchId) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}