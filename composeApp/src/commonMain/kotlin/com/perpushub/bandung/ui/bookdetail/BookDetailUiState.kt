package com.perpushub.bandung.ui.bookdetail

import com.perpushub.bandung.common.model.BookDetail

data class BookDetailUiState(
    val bookDetail: BookDetail? = null,
    val isLoading: Boolean = false
)
