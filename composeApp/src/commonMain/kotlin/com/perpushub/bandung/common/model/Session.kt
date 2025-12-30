package com.perpushub.bandung.common.model

data class Session(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String? = null
)
