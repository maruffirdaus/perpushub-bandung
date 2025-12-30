package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.LoanRequest
import kotlinx.coroutines.delay
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import ovh.plrapps.mapcompose.utils.removeFirst
import kotlin.time.Duration.Companion.seconds

class LoanRepository {
    private val loanRequest: MutableList<LoanRequest> = mutableListOf()

    suspend fun addLoanRequest(userId: Int, bookId: Int): Int {
        delay(0.5.seconds)
        loanRequest += LoanRequest(
            id = loanRequest.size,
            userId = userId,
            book = Book.dummies[bookId],
            requestAt = kotlin.time.Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()
        )
        return loanRequest.lastIndex
    }

    suspend fun getLoanRequests(userId: Int): List<LoanRequest> {
        delay(0.5.seconds)
        return loanRequest.filter { it.userId == userId }.reversed()
    }

    suspend fun getLoanRequest(id: Int): LoanRequest? {
        delay(0.5.seconds)
        return loanRequest.find { it.id == id }
    }

    suspend fun deleteLoanRequest(id: Int) {
        delay(0.5.seconds)
        loanRequest.removeFirst { it.id == id }
    }
}