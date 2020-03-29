package com.vladislav.shumilov.core_data

import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope