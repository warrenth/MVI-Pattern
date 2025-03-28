package com.kth.mvi.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<EVENT : MviContract.Event, STATE : MviContract.State, EFFECT : MviContract.Effect> (
    initialState: STATE
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EFFECT>()
    val effect: SharedFlow<EFFECT> = _effect.asSharedFlow()

    protected fun setState(reducer: STATE.() -> STATE) {
        _state.update { it.reducer() }
    }

    protected suspend fun postEffect(effect: EFFECT) {
        _effect.emit(effect)
    }

    abstract fun handleEvent(event: EVENT)
}