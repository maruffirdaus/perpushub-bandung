package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.response.StatusResponse
import com.perpushub.bandung.data.remote.model.request.AddDraftRequest
import com.perpushub.bandung.data.remote.model.request.SubmitDraftRequest
import com.perpushub.bandung.data.remote.model.response.GetDraftsResponse
import com.perpushub.bandung.data.remote.model.response.GetSubmittedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoanRequestService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun addDraft(request: AddDraftRequest): StatusResponse {
        val response = client.post("$baseUrl/loan-requests") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun getDrafts(): GetDraftsResponse {
        val response = client.get("$baseUrl/loan-requests/drafts")
        return response.body()
    }

    suspend fun deleteDraft(id: Int): StatusResponse {
        val response = client.delete("$baseUrl/loan-requests/$id")
        return response.body()
    }

    suspend fun submitDraft(id: Int, request: SubmitDraftRequest): StatusResponse {
        val response = client.post("$baseUrl/loan-requests/$id/submit") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun getSubmitted(): GetSubmittedResponse {
        val response = client.get("$baseUrl/loan-requests/submitted")
        return response.body()
    }
}