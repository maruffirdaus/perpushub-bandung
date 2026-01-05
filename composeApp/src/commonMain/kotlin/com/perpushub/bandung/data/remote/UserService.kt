package com.perpushub.bandung.data.remote

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.remote.model.response.StatusResponse
import com.perpushub.bandung.data.remote.model.request.AddEditAddressRequest
import com.perpushub.bandung.data.remote.model.response.GetAddressesResponse
import com.perpushub.bandung.data.remote.model.response.GetUserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserService(
    private val client: HttpClient
) {
    private val baseUrl = BuildConfig.BACKEND_BASE_URL

    suspend fun getUser(id: Int): GetUserResponse {
        val response = client.get("$baseUrl/users/$id")
        return response.body()
    }

    suspend fun addAddress(request: AddEditAddressRequest): StatusResponse {
        val response = client.post("$baseUrl/users/addresses") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun getAddresses(): GetAddressesResponse {
        val response = client.get("$baseUrl/users/addresses")
        return response.body()
    }

    suspend fun editAddress(addressId: Int, request: AddEditAddressRequest): StatusResponse {
        val response = client.put("$baseUrl/users/addresses/$addressId") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return response.body()
    }

    suspend fun deleteAddress(addressId: Int): StatusResponse {
        val response = client.delete("$baseUrl/users/addresses/$addressId")
        return response.body()
    }
}