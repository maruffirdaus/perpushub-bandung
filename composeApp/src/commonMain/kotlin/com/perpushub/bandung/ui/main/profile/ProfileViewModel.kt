package com.perpushub.bandung.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.data.repository.UserRepository
import com.perpushub.bandung.service.SessionManager
import com.perpushub.bandung.ui.common.messaging.UiError
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
            is ProfileEvent.OnAddEditAddressDialogOpen -> openAddEditAddressDialog(event.address)
            is ProfileEvent.OnAddEditAddressDialogClose -> closeAddEditAddressDialog()
            is ProfileEvent.OnAddressSave -> saveAddress(event.input)
            is ProfileEvent.OnAddressDelete -> deleteAddress(event.addressId)
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
            try {
                _uiState.update {
                    it.copy(user = userRepository.getUser(userId))
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun refreshAddresses() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(addresses = userRepository.getAddresses(userId))
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun openAddEditAddressDialog(address: Address?) {
        _uiState.update {
            it.copy(
                addressToEdit = address,
                isAddEditAddressDialogOpen = true
            )
        }
    }

    private fun closeAddEditAddressDialog() {
        _uiState.update {
            it.copy(
                addressToEdit = null,
                isAddEditAddressDialogOpen = false
            )
        }
    }

    private fun saveAddress(input: AddressInput) {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                val addressToEdit = uiState.value.addressToEdit

                if (addressToEdit == null) {
                    userRepository.addAddress(userId, input)
                } else {
                    userRepository.editAddress(userId, addressToEdit.id, input)
                }

                _uiState.update {
                    it.copy(addresses = userRepository.getAddresses(userId))
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun deleteAddress(addressId: Int) {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                userRepository.deleteAddress(userId, addressId)
                _uiState.update {
                    it.copy(addresses = userRepository.getAddresses(userId))
                }
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