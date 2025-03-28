package com.kth.mvi.base

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <STATE : MviContract.State, EFFECT : MviContract.Effect> BaseScreen(
    viewModel: BaseViewModel<*, STATE, EFFECT>,
    onEffect: (EFFECT) -> Unit,
    content: @Composable (STATE) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
           when (effect) {
               is BaseEffect.ShowToast -> {
                   Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
               }
               is BaseEffect.ShowDialog -> {
                   dialogMessage = effect.message
               }
               BaseEffect.ShowLoading -> {
                   isLoading = true
               }
               BaseEffect.HideLoading -> {
                   isLoading = false
               }
               else -> onEffect(effect)
           }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(state)

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        dialogMessage?.let { message ->
            AlertDialog(
                onDismissRequest = { dialogMessage = null },
                confirmButton = {
                    TextButton(onClick = { dialogMessage = null }) {
                        Text("확인")
                    }
                },
                title = { Text("알림") },
                text = { Text(message) }
            )
        }

    }
}