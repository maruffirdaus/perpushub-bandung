package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
data class LoanRequest(
    val id: Int,
    val userId: Int,
    val book: Book,
    val status: LoanRequestStatus
) {
    companion object {
        val dummies = mutableListOf<LoanRequest>()
    }
}
