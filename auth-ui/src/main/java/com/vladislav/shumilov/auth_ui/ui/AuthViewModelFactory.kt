package com.vladislav.shumilov.auth_ui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.auth_ui.common.AuthInteractor
import com.vladislav.shumilov.auth_ui.di.AuthScope
import javax.inject.Inject

@AuthScope
class AuthViewModelFactory @Inject constructor(private val authInteractor: AuthInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = if (modelClass.isAssignableFrom(
            AuthViewModel::class.java
        )
    ) {
        AuthViewModel(authInteractor) as T
    } else {
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}