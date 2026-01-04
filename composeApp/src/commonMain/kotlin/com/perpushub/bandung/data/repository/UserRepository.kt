package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.common.model.User
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class UserRepository {
    suspend fun getUser(id: Int): User? {
        delay(0.25.seconds)
        return User.dummies[id]
    }

    suspend fun addAddress(userId: Int, input: AddressInput) {
        delay(0.25.seconds)
        Address.dummies[userId] = Address.dummies[userId]
            ?.plus(
                Address(
                    id = Address.dummies[userId]?.size ?: 0,
                    userId = userId,
                    label = input.label,
                    recipientName = input.recipientName,
                    phoneNumber = input.phoneNumber,
                    addressLine = input.addressLine,
                    city = input.city,
                    province = input.province,
                    postalCode = input.postalCode
                )
            ) ?: listOf()
    }

    suspend fun getAddresses(userId: Int): List<Address> {
        delay(0.25.seconds)
        return Address.dummies[userId] ?: listOf()
    }

    suspend fun editAddress(userId: Int, addressId: Int, input: AddressInput) {
        delay(0.25.seconds)
        Address.dummies[userId] = Address.dummies[userId]?.map { address ->
            if (address.id == addressId) {
                address.copy(
                    label = input.label,
                    recipientName = input.recipientName,
                    phoneNumber = input.phoneNumber,
                    addressLine = input.addressLine,
                    city = input.city,
                    province = input.province,
                    postalCode = input.postalCode
                )
            } else {
                address
            }
        } ?: listOf()
    }

    suspend fun deleteAddress(userId: Int, addressId: Int) {
        delay(0.25.seconds)
        Address.dummies[userId] = Address.dummies[userId]?.filter { it.id != addressId } ?: listOf()
    }
}