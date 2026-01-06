package com.perpushub.bandung.ui.main.bookdetail

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookDetail
import com.perpushub.bandung.common.model.LibraryDetail

data class BookDetailUiState(
    val book: BookDetail? = null,
    val similarBooks: List<Book> = listOf(),
    val bookCopies: List<BookCopy> = listOf(),
    val libraries: List<LibraryDetail> = listOf(),
    val isLibraryDialogOpen: Boolean = false,
    val isLibraryDialogLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)
