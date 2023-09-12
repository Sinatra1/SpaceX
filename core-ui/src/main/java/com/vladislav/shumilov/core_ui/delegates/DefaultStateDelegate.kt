package com.vladislav.shumilov.core_ui.delegates

import com.vladislav.shumilov.core_ui.models.StateDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultStateDelegate<State>(initialState: State) : StateDelegate<State> {

    private val _stateFlow = MutableStateFlow(initialState)
    override val stateFlow = _stateFlow.asStateFlow()

    override val state
        get() = stateFlow.value

    override fun updateState(update: State.() -> State) {
        val prevValue = _stateFlow.value
        val nextValue = update(prevValue)
        _stateFlow.compareAndSet(prevValue, nextValue)
    }
}