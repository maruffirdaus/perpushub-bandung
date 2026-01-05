package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.request.LoginRequest
import com.perpushub.bandung.data.remote.model.request.RegisterRequest
import com.perpushub.bandung.data.remote.model.response.LoginResponse
import com.perpushub.bandung.data.remote.model.response.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun login(request: LoginRequest): LoginResponse {
        val response = client.post("$baseUrl/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        println(response.body<LoginResponse>())
        return response.body()
    }

    suspend fun register(request: RegisterRequest): StatusResponse {
        val response = client.post("$baseUrl/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }
}