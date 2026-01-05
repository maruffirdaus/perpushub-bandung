package com.perpushub.bandung.ui.main.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.LoanRepository
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val loanRepository: LoanRepository,
    private val uiMessageManager: UiMessageManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnLoansRefresh -> refreshLoans()
        }
    }

    private fun refreshLoans() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(loans = loanRepository.getHistory())
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}