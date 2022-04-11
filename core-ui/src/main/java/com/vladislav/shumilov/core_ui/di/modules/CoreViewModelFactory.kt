package com.vladislav.shumilov.core_ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import javax.inject.Inject
import javax.inject.Provider

private const val UNKNOWN_MODEL_CLASS = "unknown model class"

@Suppress(UNCHECKED_CAST)
class CoreViewModelFactory
@Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("$UNKNOWN_MODEL_CLASS $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}