package com.kth.mvi.feature.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kth.mvi.base.BaseScreen

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    val context = LocalContext.current

    BaseScreen(
        viewModel = viewModel,
        onEffect = { /* 사용자 정의 Effect 없음 */ }
    ) { state ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text("이름: ${state.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.handleEvent(UserEvent.LoadUsers)
            }) {
                Text("Load User")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.handleEvent(UserEvent.ChangeName("Hoonihoon"))
            }) {
                Text("Change Name to Hoonihoon")
            }
        }
    }
}