package com.perpushub.bandung.ui.main

sealed class MainEvent {
    class OnLogout(val onSuccess: () -> Unit) : MainEvent()
}