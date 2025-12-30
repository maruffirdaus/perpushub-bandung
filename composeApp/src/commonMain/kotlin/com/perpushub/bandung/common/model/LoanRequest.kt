package com.perpushub.bandung.common.model

data class LoanRequest(
    val id: Int,
    val userId: Int,
    val book: Book,
    val requestAt: String
)
