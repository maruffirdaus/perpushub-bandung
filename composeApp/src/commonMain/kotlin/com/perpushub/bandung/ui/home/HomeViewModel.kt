package com.perpushub.bandung.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun changeSearchQuery(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                searchedBooks = if (query.isBlank()) null else it.searchedBooks
            )
        }
    }

    fun searchBooks() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            uiState.value.searchQuery.let { query ->
                if (query.isNotBlank()) {
                    _uiState.update {
                        it.copy(searchedBooks = bookRepository.searchBooks(uiState.value.searchQuery))
                    }
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun refreshTopBooks() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(topBooks = bookRepository.getTopBooks())
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}