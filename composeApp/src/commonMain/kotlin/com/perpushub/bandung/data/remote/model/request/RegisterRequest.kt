package com.perpushub.bandung.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val fullName: String,
    val email: String,
    val password: String
)
