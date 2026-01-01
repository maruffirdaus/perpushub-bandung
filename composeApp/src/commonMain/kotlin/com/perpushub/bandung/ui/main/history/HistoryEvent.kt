package com.perpushub.bandung.ui.main.history

sealed class HistoryEvent {
    object OnErrorMessageClear : HistoryEvent()
}