package com.perpushub.bandung.ui.home

import com.perpushub.bandung.common.model.Book

data class HomeUiState(
    val searchQuery: String = "",
    val searchedBooks: List<Book>? = null,
    val topBooks: List<Book> = listOf(),
    val isLoading: Boolean = false
)
