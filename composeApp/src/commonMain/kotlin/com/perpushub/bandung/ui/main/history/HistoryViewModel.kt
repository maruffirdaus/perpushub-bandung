package com.perpushub.bandung.ui.main.history

import androidx.lifecycle.ViewModel
import com.perpushub.bandung.service.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HistoryViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnErrorMessageClear -> clearErrorMessage()
        }
    }

    private fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}