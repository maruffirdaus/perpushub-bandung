package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.LoanRequest
import com.perpushub.bandung.common.model.LoanRequestDetail
import com.perpushub.bandung.data.remote.LoanRequestService
import com.perpushub.bandung.data.remote.model.request.AddDraftRequest
import com.perpushub.bandung.data.remote.model.request.SubmitDraftRequest

class LoanRequestRepository(
    private val service: LoanRequestService
) {
    suspend fun addDraft(bookId: Int) {
        val request = AddDraftRequest(bookId)
        val response = service.addDraft(request)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun getDrafts(): List<LoanRequest> {
        val response = service.getDrafts()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun deleteDraft(id: Int) {
        val response = service.deleteDraft(id)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun submitDraft(id: Int, libraryId: Int, addressId: Int, dueDate: String) {
        val request = SubmitDraftRequest(libraryId, addressId, dueDate)
        val response = service.submitDraft(id, request)
        if (response.status != "success") {
            throw Exception(response.message)
        }
    }

    suspend fun getSubmitted(): List<LoanRequestDetail> {
        val response = service.getSubmitted()
        return response.data ?: throw Exception(response.message)
    }
}