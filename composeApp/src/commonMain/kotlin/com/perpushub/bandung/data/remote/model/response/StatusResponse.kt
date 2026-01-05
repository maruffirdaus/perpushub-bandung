package com.perpushub.bandung.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val status: String,
    val message: String
)