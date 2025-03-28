package com.kth.mvi.base

sealed interface BaseEffect : MviContract.Effect {
    data class ShowToast(val message: String) : BaseEffect
    data class ShowDialog(val message: String) : BaseEffect
    data object ShowLoading : BaseEffect
    data object HideLoading : BaseEffect
}