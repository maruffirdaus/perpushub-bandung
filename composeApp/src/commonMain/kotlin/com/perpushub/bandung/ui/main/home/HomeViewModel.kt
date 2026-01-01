package com.perpushub.bandung.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState
        .onStart {
            loadInitialData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            HomeUiState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(
                    topBooks = bookRepository.getTopBooks(),
                    recommendedBooks = bookRepository.getRecommendedBooks()
                )
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSearchQueryChange -> changeSearchQuery(event.query)
            is HomeEvent.OnBooksSearch -> searchBooks()
        }
    }

    private fun changeSearchQuery(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                searchedBooks = if (query.isBlank()) null else it.searchedBooks
            )
        }
    }

    private fun searchBooks() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            uiState.value.searchQuery.let { query ->
                if (query.isNotBlank()) {
                    _uiState.update {
                        it.copy(searchedBooks = bookRepository.searchBooks(uiState.value.searchQuery.trim()))
                    }
                }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}