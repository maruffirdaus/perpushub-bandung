package com.perpushub.bandung.ui.main.borrowing

sealed class BorrowingEvent {
    class OnSelectLibraryDialogDataRefresh(val bookId: Int) : BorrowingEvent()
    class OnLoanRequestDelete(val id: Int) : BorrowingEvent()
    object OnErrorMessageClear : BorrowingEvent()
}