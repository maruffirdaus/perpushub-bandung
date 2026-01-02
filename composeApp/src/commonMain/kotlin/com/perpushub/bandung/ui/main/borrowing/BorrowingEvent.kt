package com.perpushub.bandung.ui.main.borrowing

sealed class BorrowingEvent {
    object OnLoanRequestsRefresh : BorrowingEvent()
    class OnLibraryDialogRefresh(val bookId: Int) : BorrowingEvent()
    class OnLoanRequestDelete(val id: Int) : BorrowingEvent()
    object OnErrorMessageClear : BorrowingEvent()
}