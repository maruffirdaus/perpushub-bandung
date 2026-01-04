package com.perpushub.bandung.ui.main.profile

import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.ui.main.profile.model.ProfileTab

sealed class ProfileEvent {
    class OnSelectedTabChange(val tab: ProfileTab) : ProfileEvent()
    object OnUserRefresh : ProfileEvent()
    object OnAddressesRefresh : ProfileEvent()
    class OnAddEditAddressDialogOpen(val address: Address? = null) : ProfileEvent()
    object OnAddEditAddressDialogClose : ProfileEvent()
    class OnAddressSave(val input: AddressInput) : ProfileEvent()
    class OnAddressDelete(val addressId: Int) : ProfileEvent()
}