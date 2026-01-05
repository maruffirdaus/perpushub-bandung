package com.perpushub.bandung.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
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
            is MainEvent.OnLogout -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}