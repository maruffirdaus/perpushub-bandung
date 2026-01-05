package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.response.GetBookCopiesResponse
import com.perpushub.bandung.data.remote.model.response.GetBookDetailResponse
import com.perpushub.bandung.data.remote.model.response.GetBooksResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BookService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun getTopBooks(): GetBooksResponse {
        val response = client.get("$baseUrl/books/top")
        return response.body()
    }

    suspend fun getRecommendedBooks(): GetBooksResponse {
        val response = client.get("$baseUrl/books/recommended")
        return response.body()
    }

    suspend fun getBookDetail(id: Int): GetBookDetailResponse {
        val response = client.get("$baseUrl/books/$id")
        return response.body()
    }

    suspend fun getBookCopies(id: Int): GetBookCopiesResponse {
        val response = client.get("$baseUrl/books/$id/copies")
        return response.body()
    }
}