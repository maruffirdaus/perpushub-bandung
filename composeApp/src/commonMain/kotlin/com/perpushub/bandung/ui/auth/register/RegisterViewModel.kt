package com.perpushub.bandung.ui.auth.register

import androidx.lifecycle.ViewModel
import com.perpushub.bandung.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnUsernameChange -> changeUsername(event.username)
            is RegisterEvent.OnFullNameChange -> changeFullName(event.fullName)
            is RegisterEvent.OnEmailChange -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChange -> changePassword(event.password)
            is RegisterEvent.OnPasswordVisibilityToggle -> togglePasswordVisibility()
            is RegisterEvent.OnConfirmPasswordChange -> changeConfirmPassword(event.password)
            is RegisterEvent.OnConfirmPasswordVisibilityToggle -> toggleConfirmPasswordVisibility()
            is RegisterEvent.OnRegister -> register()
        }
    }

    private fun changeUsername(username: String) {
        _uiState.update {
            it.copy(username = username)
        }
    }

    private fun changeFullName(fullName: String) {
        _uiState.update {
            it.copy(fullName = fullName)
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

    private fun changeConfirmPassword(password: String) {
        _uiState.update {
            it.copy(confirmPassword = password)
        }
    }

    private fun toggleConfirmPasswordVisibility() {
        _uiState.update {
            it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible)
        }
    }

    private fun register() {

    }
}