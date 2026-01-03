package com.perpushub.bandung.ui.main.profile

import com.perpushub.bandung.ui.main.profile.model.ProfileTab

sealed class ProfileEvent {
    class OnSelectedTabChange(val tab: ProfileTab) : ProfileEvent()
    object OnUserRefresh : ProfileEvent()
    object OnAddressesRefresh : ProfileEvent()
}