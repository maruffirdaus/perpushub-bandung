package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class GetBooksResponse(
    val status: String,
    val message: String,
    val data: List<Book>?
)
