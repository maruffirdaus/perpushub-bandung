package com.perpushub.bandung.ui.main.borrowing

import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.common.model.LoanRequest

data class BorrowingUiState(
    val loanRequests: List<LoanRequest> = listOf(),
    val bookCopies: List<BookCopy> = listOf(),
    val libraries: List<LibraryDetail> = listOf(),
    val isLibraryDialogLoading: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
