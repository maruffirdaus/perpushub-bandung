package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Session
import com.perpushub.bandung.common.model.User
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AuthRepository {
    suspend fun login(email: String, password: String): Session {
        delay(0.25.seconds)
        return Session(
            userId = User.dummies.find { it.email == email }?.id ?: User.dummies[0].id,
            accessToken = "access_token",
            refreshToken = "refresh_token"
        )
    }

    suspend fun register(username: String, fullName: String, email: String, password: String) {
        delay(0.25.seconds)
        User.dummies.add(
            User(
                id = User.dummies.size,
                username = username,
                fullName = fullName,
                email = email,
                address = listOf()
            )
        )
    }
}