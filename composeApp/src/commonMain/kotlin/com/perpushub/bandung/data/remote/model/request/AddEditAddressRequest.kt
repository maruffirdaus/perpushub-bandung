package com.perpushub.bandung.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddEditAddressRequest(
    val label: String,
    val recipientName: String,
    val phoneNumber: String,
    val addressLine: String,
    val city: String,
    val province: String,
    val postalCode: String
)