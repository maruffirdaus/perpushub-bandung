package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.response.GetLibrariesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LibraryService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun getLibraries(): GetLibrariesResponse {
        val response = client.get("$baseUrl/libraries")
        return response.body()
    }
}