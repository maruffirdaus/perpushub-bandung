package com.perpushub.bandung.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LoanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val id: Int,
    private val bookRepository: BookRepository,
    private val loanRepository: LoanRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState = _uiState
        .onStart { loadInitialData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            BookDetailUiState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(
                    book = bookRepository.getBookDetail(id),
                    bookCopies = bookRepository.getBookCopies(id)
                )
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun borrow(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            loanRepository.addLoanRequest(0, id)
            onSuccess()
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}