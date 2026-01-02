package com.perpushub.bandung.ui.main.profile

import com.perpushub.bandung.ui.main.profile.model.ProfileTab

sealed class ProfileEvent {
    data class OnSelectedTabChange(val tab: ProfileTab) : ProfileEvent()
}