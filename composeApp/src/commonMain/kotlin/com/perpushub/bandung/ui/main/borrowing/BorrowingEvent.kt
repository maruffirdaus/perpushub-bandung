package com.perpushub.bandung.ui.main.borrowing

import com.perpushub.bandung.ui.main.borrowing.model.BorrowTab

sealed class BorrowingEvent {
    class OnSelectedTabChange(val tab: BorrowTab) : BorrowingEvent()
    object OnCartsRefresh : BorrowingEvent()
    object OnRequestsRefresh : BorrowingEvent()
    object OnDeliveriesRefresh : BorrowingEvent()
    object OnLoansRefresh : BorrowingEvent()
    class OnLibraryDialogRefresh(val bookId: Int) : BorrowingEvent()
    class OnCartDelete(val id: Int) : BorrowingEvent()
}