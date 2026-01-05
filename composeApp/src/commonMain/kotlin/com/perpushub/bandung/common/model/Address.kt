package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val id: Int,
    val userId: Int,
    val label: String,
    val recipientName: String,
    val phoneNumber: String,
    val addressLine: String,
    val city: String,
    val province: String,
    val postalCode: String
) {
    companion object {
        val dummies = mutableMapOf(
            0 to listOf(
                Address(
                    id = 0,
                    userId = 0,
                    label = "Kampus",
                    recipientName = "Maruf Firdaus",
                    phoneNumber = "082133628466",
                    addressLine = "Jl. Telekomunikasi No.1, Sukapura, Kec. Dayeuhkolot",
                    city = "Kabupaten Bandung",
                    province = "Jawa Barat",
                    postalCode = "40257"
                )
            )
        )
    }
}
