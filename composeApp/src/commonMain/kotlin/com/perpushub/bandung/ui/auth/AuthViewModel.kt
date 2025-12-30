package com.perpushub.bandung.ui.auth

import androidx.lifecycle.ViewModel
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(
    private val sessionManager: SessionManager,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()
}