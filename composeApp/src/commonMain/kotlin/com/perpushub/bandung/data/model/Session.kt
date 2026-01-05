package com.perpushub.bandung.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String? = null
)