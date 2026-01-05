package com.perpushub.bandung.data.repository

import com.perpushub.bandung.data.local.SessionService
import com.perpushub.bandung.data.remote.AuthService
import com.perpushub.bandung.data.remote.model.request.LoginRequest
import com.perpushub.bandung.data.remote.model.request.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(
    private val authService: AuthService,
    private val sessionService: SessionService
) {
    val currentUserId: Flow<Int?> = sessionService.session.map { it?.userId }

    suspend fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        val response = authService.login(request)
        sessionService.save(response.data ?: throw Exception(response.message))
    }

    fun logout() {
        sessionService.clear()
    }

    suspend fun register(username: String, fullName: String, email: String, password: String) {
        val request = RegisterRequest(username, fullName, email, password)
        val response = authService.register(request)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }
}