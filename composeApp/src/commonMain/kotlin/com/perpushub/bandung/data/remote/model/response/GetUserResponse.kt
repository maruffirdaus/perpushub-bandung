package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.User
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    val status: String,
    val message: String,
    val data: User?
)
