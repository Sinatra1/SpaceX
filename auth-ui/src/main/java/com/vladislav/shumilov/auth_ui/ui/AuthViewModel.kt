package com.vladislav.shumilov.auth_ui.ui

import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.auth_ui.common.AuthInteractor

class AuthViewModel(private val authInteractor: AuthInteractor) : ViewModel() {

    fun login() {
        authInteractor.login()
    }
}