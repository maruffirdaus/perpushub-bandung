package com.perpushub.bandung.ui.main.profile

import com.perpushub.bandung.ui.main.profile.model.ProfileTab

data class ProfileUiState(
    val selectedTab: ProfileTab = ProfileTab.ACCOUNT,
    val isLoading: Boolean = false
)
