package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.data.model.Session
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val status: String,
    val message: String,
    val data: Session?
)