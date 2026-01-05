package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.LoanRequest
import kotlinx.serialization.Serializable

@Serializable
data class GetDraftsResponse(
    val status: String,
    val message: String,
    val data: List<LoanRequest>?
)