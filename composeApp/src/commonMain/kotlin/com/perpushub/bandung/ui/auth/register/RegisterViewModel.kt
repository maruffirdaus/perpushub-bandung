package com.perpushub.bandung.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.ui.common.extension.isValidEmail
import com.perpushub.bandung.ui.common.extension.isValidPassword
import com.perpushub.bandung.ui.common.extension.isValidUsername
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val uiMessageManager: UiMessageManager
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
            is RegisterEvent.OnRegister -> register(event.onSuccess)
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

    private fun register(onSuccess: () -> Unit) {
        if (!uiState.value.username.isValidUsername()) {
            uiMessageManager.emitMessage(UiError("Username harus terdiri dari 1-30 karakter (huruf, angka, titik, atau garis bawah) dan tidak boleh dimulai, diakhiri, atau mengandung titik berurutan."))
            return
        }
        if (uiState.value.fullName.isBlank()) {
            uiMessageManager.emitMessage(UiError("Nama lengkap tidak boleh kosong."))
            return
        }
        if (!uiState.value.email.isValidEmail()) {
            uiMessageManager.emitMessage(UiError("Email tidak valid."))
            return
        }
        if (!uiState.value.password.isValidPassword()) {
            uiMessageManager.emitMessage(UiError("Password harus terdiri dari 8-32 karakter."))
            return
        }
        if (uiState.value.password != uiState.value.confirmPassword) {
            uiMessageManager.emitMessage(UiError("Konfirmasi password tidak cocok."))
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                authRepository.register(
                    username = uiState.value.username,
                    fullName = uiState.value.fullName,
                    email = uiState.value.email,
                    password = uiState.value.password
                )
                onSuccess()
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}