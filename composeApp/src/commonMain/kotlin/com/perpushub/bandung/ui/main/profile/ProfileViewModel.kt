package com.perpushub.bandung.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.UserRepository
import com.perpushub.bandung.service.SessionManager
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import com.perpushub.bandung.ui.main.profile.model.ProfileTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val uiMessageManager: UiMessageManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnSelectedTabChange -> changeSelectedTab(event.tab)
            is ProfileEvent.OnUserRefresh -> refreshUser()
            is ProfileEvent.OnAddressesRefresh -> refreshAddresses()
        }
    }

    private fun changeSelectedTab(tab: ProfileTab) {
        _uiState.update {
            it.copy(selectedTab = tab)
        }
    }

    private fun refreshUser() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(user = userRepository.getUser(userId))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    private fun refreshAddresses() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(addresses = userRepository.getAddresses(userId))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}