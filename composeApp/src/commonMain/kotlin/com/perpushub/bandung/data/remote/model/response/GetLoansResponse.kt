package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.Loan
import kotlinx.serialization.Serializable

@Serializable
data class GetLoansResponse(
    val status: String,
    val message: String,
    val data: List<Loan>?
)
