package com.perpushub.bandung.common.model

data class AddressInput(
    val label: String = "",
    val recipientName: String = "",
    val phoneNumber: String = "",
    val addressLine: String = "",
    val city: String = "",
    val province: String = "",
    val postalCode: String = ""
)