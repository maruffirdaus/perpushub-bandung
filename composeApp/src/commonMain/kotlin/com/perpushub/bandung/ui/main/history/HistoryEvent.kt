package com.perpushub.bandung.ui.main.history

sealed class HistoryEvent {
    object OnLoansRefresh : HistoryEvent()
}