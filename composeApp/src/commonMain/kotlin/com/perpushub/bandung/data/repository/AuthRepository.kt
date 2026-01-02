package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Session
import com.perpushub.bandung.common.model.User
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AuthRepository {
    suspend fun login(email: String, password: String): Session {
        delay(0.25.seconds)
        val user = User.dummies.find { it.email == email }
        if (user == null) {
            throw Exception("Email atau kata sandi salah. Silakan coba lagi.")
        }
        return Session(
            userId = user.id,
            accessToken = "access_token",
            refreshToken = "refresh_token"
        )
    }

    suspend fun register(username: String, fullName: String, email: String, password: String) {
        delay(0.25.seconds)
        if (User.dummies.any { it.username == username }) {
            throw Exception("Username sudah digunakan. Silakan coba lagi.")
        }
        if (User.dummies.any { it.email == email }) {
            throw Exception("Email sudah digunakan. Silakan coba lagi.")
        }
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