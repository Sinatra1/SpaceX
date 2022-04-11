package com.vladislav.shumilov.core_data

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.reflect.KClass

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CoreScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)