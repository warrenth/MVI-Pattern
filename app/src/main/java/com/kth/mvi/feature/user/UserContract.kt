package com.kth.mvi.feature.user

import com.kth.mvi.base.MviContract

sealed interface UserEvent : MviContract.Event {
    data object LoadUsers : UserEvent
    data class ChangeName(val name: String) : UserEvent
}

sealed interface UserEffect : MviContract.Effect

data class UserState(
    val name: String = ""
) : MviContract.State
