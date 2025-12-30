package com.perpushub.bandung.ui.bookdetail

import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookDetail

data class BookDetailUiState(
    val book: BookDetail? = null,
    val bookCopies: List<BookCopy> = listOf(),
    val isLoading: Boolean = false
)
