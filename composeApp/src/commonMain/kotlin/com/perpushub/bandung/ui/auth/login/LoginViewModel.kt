package com.perpushub.bandung.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.service.SessionManager
import com.perpushub.bandung.ui.common.extension.isValidEmail
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sessionManager: SessionManager,
    private val uiMessageManager: UiMessageManager
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
        if (!uiState.value.email.isValidEmail()) {
            uiMessageManager.emitMessage(UiError("Email tidak valid."))
            return
        }
        if (uiState.value.password.isBlank()) {
            uiMessageManager.emitMessage(UiError("Password tidak boleh kosong."))
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                sessionManager.login(uiState.value.email, uiState.value.password)
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}