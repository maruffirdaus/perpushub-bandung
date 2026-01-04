package com.perpushub.bandung.ui.main.profile

import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.User
import com.perpushub.bandung.ui.main.profile.model.ProfileTab

data class ProfileUiState(
    val selectedTab: ProfileTab = ProfileTab.ACCOUNT,
    val user: User? = null,
    val addresses: List<Address> = listOf(),
    val addressToEdit: Address? = null,
    val isAddEditAddressDialogOpen: Boolean = false,
    val isLoading: Boolean = false
)
