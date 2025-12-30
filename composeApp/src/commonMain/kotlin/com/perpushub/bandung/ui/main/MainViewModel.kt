package com.perpushub.bandung.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.data.repository.UserRepository
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionManager: SessionManager,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            sessionManager.session.collect { session ->
                if (session?.userId != null) {
                    _uiState.update {
                        it.copy(user = userRepository.getUser(session.userId))
                    }
                } else {
                    _uiState.update {
                        it.copy(user = null)
                    }
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoginDialogLoading = true)
            }
            sessionManager.login(email, password)
            _uiState.update {
                it.copy(
                    isLoginDialogOpen = false,
                    isLoginDialogLoading = false
                )
            }
        }
    }

    fun register(username: String, fullName: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isRegisterDialogLoading = true)
            }
            authRepository.register(username, fullName, email, password)
            _uiState.update {
                it.copy(
                    isLoginDialogOpen = true,
                    isRegisterDialogOpen = false,
                    isRegisterDialogLoading = false
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.logout()
        }
    }

    fun openLoginDialog() {
        _uiState.update {
            it.copy(isLoginDialogOpen = true)
        }
    }

    fun closeLoginDialog() {
        _uiState.update {
            it.copy(isLoginDialogOpen = false)
        }
    }

    fun openRegisterDialog() {
        _uiState.update {
            it.copy(isRegisterDialogOpen = true)
        }
    }

    fun closeRegisterDialog() {
        _uiState.update {
            it.copy(isRegisterDialogOpen = false)
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}