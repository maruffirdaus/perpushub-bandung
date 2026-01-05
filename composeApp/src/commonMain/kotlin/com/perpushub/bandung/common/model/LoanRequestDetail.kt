package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
data class LoanRequestDetail(
    val id: Int,
    val userId: Int,
    val book: Book,
    val library: Library,
    val recipientName: String,
    val phoneNumber: String,
    val addressLine: String,
    val city: String,
    val province: String,
    val postalCode: String,
    val dueDate: String,
    val status: LoanRequestStatus
)
