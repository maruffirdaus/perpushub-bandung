package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.BookDetail
import kotlinx.serialization.Serializable

@Serializable
data class GetBookDetailResponse(
    val status: String,
    val message: String,
    val data: BookDetail?
)
