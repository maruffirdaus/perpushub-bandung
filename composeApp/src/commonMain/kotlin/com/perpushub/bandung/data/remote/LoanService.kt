package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.response.GetLoansResponse
import com.perpushub.bandung.data.remote.model.response.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class LoanService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun receiveBook(id: Int): StatusResponse {
        val response = client.post("$baseUrl/loans/$id/receive")
        return response.body()
    }

    suspend fun getInDelivery(): GetLoansResponse {
        val response = client.get("$baseUrl/loans/in-delivery")
        return response.body()
    }

    suspend fun getBorrowed(): GetLoansResponse {
        val response = client.get("$baseUrl/loans/borrowed")
        return response.body()
    }

    suspend fun getHistory(): GetLoansResponse {
        val response = client.get("$baseUrl/loans/history")
        return response.body()
    }
}