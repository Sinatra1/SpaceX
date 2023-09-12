package com.vladislav.shumilov.core_ui.models

import kotlinx.coroutines.flow.StateFlow

interface StateDelegate<State> {

    val stateFlow: StateFlow<State>

    val state: State

    fun updateState(update: State.() -> State)
}