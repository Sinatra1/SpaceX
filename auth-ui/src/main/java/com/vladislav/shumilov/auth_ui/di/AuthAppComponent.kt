package com.vladislav.shumilov.auth_ui.di

interface AuthAppComponent {
    fun plusAuthComponent(authModule: AuthModule): AuthComponent
}