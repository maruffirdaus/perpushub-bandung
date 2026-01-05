package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.data.model.Token
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val status: String,
    val message: String,
    val data: Token?
)
