package com.kth.mvi.feature.user

import androidx.lifecycle.viewModelScope
import com.kth.mvi.base.BaseEffect
import com.kth.mvi.base.BaseViewModel
import com.kth.mvi.base.MviContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : BaseViewModel<UserEvent, UserState, MviContract.Effect>(
    initialState = UserState()
) {
    override fun handleEvent(event: UserEvent) {
        when (event) {
            is UserEvent.ChangeName -> setState { copy(name = event.name) }
            is UserEvent.LoadUsers -> loadUser()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            postEffect(BaseEffect.ShowLoading)

            runCatching {
                delay(1000)
                "Kim Tae hoon"
            }.onSuccess { name ->
                setState { copy(name = name) }
            }.onFailure {
                postEffect(BaseEffect.ShowDialog("불러오기 실패"))
                postEffect(BaseEffect.ShowToast("불러오기 실패"))
            }

            postEffect(BaseEffect.HideLoading)
        }
    }
}