package com.perpushub.bandung.ui.main.home

import com.perpushub.bandung.common.model.Book

data class HomeUiState(
    val searchQuery: String = "",
    val searchedBooks: List<Book>? = null,
    val topBooks: List<Book> = listOf(),
    val recommendedBooks: List<Book> = listOf(),
    val isLoading: Boolean = false
)
