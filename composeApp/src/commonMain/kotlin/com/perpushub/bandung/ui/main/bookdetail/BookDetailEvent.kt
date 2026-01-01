package com.perpushub.bandung.ui.main.bookdetail

sealed class BookDetailEvent {
    class OnBorrow(val onSuccess: () -> Unit) : BookDetailEvent()
    object OnErrorMessageClear : BookDetailEvent()
}