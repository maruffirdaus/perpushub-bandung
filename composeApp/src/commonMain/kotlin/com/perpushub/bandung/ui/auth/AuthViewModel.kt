package com.perpushub.bandung.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val sessionManager: SessionManager,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            sessionManager.session.collect { session ->
                _uiState.update {
                    it.copy(isLoggedIn = session != null)
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            sessionManager.login(email, password)
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}