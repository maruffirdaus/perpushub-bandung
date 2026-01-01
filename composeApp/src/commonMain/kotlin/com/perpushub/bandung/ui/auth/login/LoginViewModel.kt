package com.perpushub.bandung.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.service.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> changeEmail(event.email)
            is LoginEvent.OnPasswordChange -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityToggle -> togglePasswordVisibility()
            is LoginEvent.OnLogin -> login()
        }
    }

    private fun changeEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    private fun togglePasswordVisibility() {
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            sessionManager.login(uiState.value.email, uiState.value.password)
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}