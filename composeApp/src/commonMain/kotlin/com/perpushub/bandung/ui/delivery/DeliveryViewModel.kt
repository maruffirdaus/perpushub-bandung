package com.perpushub.bandung.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeliveryViewModel(
    private val sessionManager: SessionManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DeliveryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            sessionManager.session.collect { session ->
                if (session == null) {
                    _uiState.update {
                        it.copy(errorMessage = "Masuk untuk melanjutkan")
                    }
                }
            }
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}