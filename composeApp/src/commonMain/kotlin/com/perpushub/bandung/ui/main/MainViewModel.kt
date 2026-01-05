package com.perpushub.bandung.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.data.repository.UserRepository
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val uiMessageManager: UiMessageManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.currentUserId.collect { userId ->
                if (userId != null) {
                    _uiState.update {
                        it.copy(user = userRepository.getUser(userId))
                    }
                } else {
                    _uiState.update {
                        it.copy(user = null)
                    }
                }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLogout -> logout(event.onSuccess)
        }
    }

    private fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                authRepository.logout()
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