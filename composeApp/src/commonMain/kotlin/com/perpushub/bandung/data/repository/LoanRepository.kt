package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Loan
import com.perpushub.bandung.data.remote.LoanService

class LoanRepository(
    private val service: LoanService
) {
    suspend fun receiveBook(id: Int) {
        val response = service.receiveBook(id)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun getInDelivery(): List<Loan> {
        val response = service.getInDelivery()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getBorrowed(): List<Loan> {
        val response = service.getBorrowed()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getHistory(): List<Loan> {
        val response = service.getHistory()
        return response.data ?: throw Exception(response.message)
    }
}