package com.perpushub.bandung.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LoanRepository
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val id: Int,
    private val sessionManager: SessionManager,
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

    init {
        viewModelScope.launch {
            sessionManager.session.collect { session ->
                _uiState.update {
                    it.copy(isLoggedIn = session != null)
                }
            }
        }
    }

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
        if (sessionManager.session.value == null) {
            _uiState.update {
                it.copy(errorMessage = "Masuk untuk melanjutkan")
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            sessionManager.session.value?.userId?.let { userId ->
                loanRepository.addLoanRequest(userId, id)
                onSuccess()
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}