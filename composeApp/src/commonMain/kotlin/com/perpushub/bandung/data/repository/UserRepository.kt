package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.common.model.User
import com.perpushub.bandung.data.remote.UserService
import com.perpushub.bandung.data.remote.model.request.AddEditAddressRequest

class UserRepository(
    private val service: UserService
) {
    suspend fun getUser(id: Int): User? {
        val response = service.getUser(id)
        return response.data
    }

    suspend fun addAddress(input: AddressInput) {
        val request = AddEditAddressRequest(
            label = input.label,
            recipientName = input.recipientName,
            phoneNumber = input.phoneNumber,
            addressLine = input.addressLine,
            city = input.city,
            province = input.province,
            postalCode = input.postalCode
        )
        val response = service.addAddress(request)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun getAddresses(): List<Address> {
        val response = service.getAddresses()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun editAddress(addressId: Int, input: AddressInput) {
        val request = AddEditAddressRequest(
            label = input.label,
            recipientName = input.recipientName,
            phoneNumber = input.phoneNumber,
            addressLine = input.addressLine,
            city = input.city,
            province = input.province,
            postalCode = input.postalCode
        )
        val response = service.editAddress(addressId, request)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun deleteAddress(addressId: Int) {
        val response = service.deleteAddress(addressId)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }
}