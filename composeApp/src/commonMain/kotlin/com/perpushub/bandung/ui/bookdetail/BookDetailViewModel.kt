package com.perpushub.bandung.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshBookDetail(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(bookDetail = bookRepository.getBookDetail(id))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}