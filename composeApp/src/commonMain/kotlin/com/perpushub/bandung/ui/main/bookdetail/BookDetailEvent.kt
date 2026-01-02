package com.perpushub.bandung.ui.main.bookdetail

sealed class BookDetailEvent {
    object OnLibraryDialogOpen : BookDetailEvent()
    object OnLibraryDialogClose : BookDetailEvent()
    class OnBorrow(val onSuccess: () -> Unit) : BookDetailEvent()
}