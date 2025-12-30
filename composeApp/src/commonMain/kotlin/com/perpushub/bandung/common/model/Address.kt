package com.perpushub.bandung.common.model

data class Address(
    val id: Int,
    val title: String,
    val recipientName: String,
    val phoneNumber: String,
    val addressLine: String,
    val city: String,
    val province: String,
    val postalCode: String
) {
    companion object {
        val dummies = mutableMapOf(
            0 to mutableListOf(
                Address(
                    id = 0,
                    title = "Kampus",
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
