package com.perpushub.bandung.ui.main.borrowing

import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.common.model.Loan
import com.perpushub.bandung.common.model.LoanRequest
import com.perpushub.bandung.common.model.LoanRequestDetail
import com.perpushub.bandung.ui.main.borrowing.model.BorrowTab

data class BorrowingUiState(
    val selectedTab: BorrowTab = BorrowTab.CART,
    val carts: List<LoanRequest> = listOf(),
    val requests: List<LoanRequestDetail> = listOf(),
    val deliveries: List<Loan> = listOf(),
    val loans: List<Loan> = listOf(),
    val bookCopies: List<BookCopy> = listOf(),
    val libraries: List<LibraryDetail> = listOf(),
    val isLibraryDialogLoading: Boolean = false,
    val addresses: List<Address> = listOf(),
    val isAddressPickerDialogLoading: Boolean = false,
    val isLoading: Boolean = false
)
