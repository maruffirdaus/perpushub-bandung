package com.perpushub.bandung.ui.common.messaging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.Text
import org.koin.compose.koinInject

@Composable
fun MessageHost(
    manager: UiMessageManager = koinInject()
) {
    var currentMessage: UiMessage? by remember { mutableStateOf(null) }

    LaunchedEffect(manager) {
        manager.messages.collect { message ->
            currentMessage = message
        }
    }

    currentMessage?.let { message ->
        ContentDialog(
            title = message.title,
            visible = true,
            content = {
                Text(message.message)
            },
            primaryButtonText = "Tutup",
            onButtonClick = {
                when (it) {
                    ContentDialogButton.Primary -> currentMessage = null
                    else -> {}
                }
            },
            size = DialogSize.Min
        )
    }
}