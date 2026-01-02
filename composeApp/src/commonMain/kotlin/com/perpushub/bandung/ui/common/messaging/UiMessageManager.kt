package com.perpushub.bandung.ui.common.messaging

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class UiMessageManager {
    private val _messages = Channel<UiMessage>(Channel.BUFFERED)
    val messages = _messages.receiveAsFlow()

    fun emitMessage(message: UiMessage) {
        _messages.trySend(message)
    }
}