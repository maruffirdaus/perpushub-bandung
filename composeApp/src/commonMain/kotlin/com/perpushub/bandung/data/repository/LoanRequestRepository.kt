package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.LoanRequest
import com.perpushub.bandung.common.model.LoanRequestStatus
import kotlinx.coroutines.delay
import ovh.plrapps.mapcompose.utils.removeFirst
import kotlin.time.Duration.Companion.seconds

class LoanRequestRepository {
    suspend fun addDraft(userId: Int, bookId: Int): Int {
        delay(0.25.seconds)
        LoanRequest.dummies += LoanRequest(
            id = LoanRequest.dummies.size,
            userId = userId,
            book = Book.dummies[bookId],
            status = LoanRequestStatus.DRAFT
        )
        return LoanRequest.dummies.lastIndex
    }

    suspend fun getDrafts(userId: Int): List<LoanRequest> {
        delay(0.25.seconds)
        return LoanRequest.dummies
            .filter { it.userId == userId && it.status == LoanRequestStatus.DRAFT }
            .reversed()
    }

    suspend fun deleteDraft(id: Int) {
        delay(0.25.seconds)
        LoanRequest.dummies.removeFirst { it.id == id }
    }

    suspend fun submitDraft(id: Int, libraryId: Int, addressId: Int, dueDate: String) {
        delay(0.25.seconds)
        val request = LoanRequest.dummies.first { it.id == id }
        val index = LoanRequest.dummies.indexOf(request)
        if (index != -1) {
            LoanRequest.dummies[index] = request.copy(status = LoanRequestStatus.PENDING)
        }
    }

    suspend fun getSubmitted(userId: Int): List<LoanRequest> {
        delay(0.25.seconds)
        return LoanRequest.dummies
            .filter { it.userId == userId }
            .filterNot { it.status == LoanRequestStatus.DRAFT }
            .reversed()
    }
}