package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.request.LoginRequest
import com.perpushub.bandung.data.remote.model.request.RegisterRequest
import com.perpushub.bandung.data.remote.model.response.LoginResponse
import com.perpushub.bandung.data.remote.model.response.RefreshTokenResponse
import com.perpushub.bandung.data.remote.model.response.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.authProviders
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class AuthService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun login(request: LoginRequest): LoginResponse {
        val response = client.post("$baseUrl/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun logout(): StatusResponse {
        val response = client.post("$baseUrl/auth/logout")
        if (response.status.isSuccess()) {
            client.authProviders.filterIsInstance<BearerAuthProvider>().forEach { it.clearToken() }
        }
        return response.body()
    }

    suspend fun register(request: RegisterRequest): StatusResponse {
        val response = client.post("$baseUrl/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun refreshToken(refreshToken: String): RefreshTokenResponse {
        val response = client.post("$baseUrl/auth/refresh") {
            header("Authorization", "Bearer $refreshToken")
        }
        return response.body()
    }
}