package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.Address
import kotlinx.serialization.Serializable

@Serializable
data class GetAddressesResponse(
    val status: String,
    val message: String,
    val data: List<Address>?
)
