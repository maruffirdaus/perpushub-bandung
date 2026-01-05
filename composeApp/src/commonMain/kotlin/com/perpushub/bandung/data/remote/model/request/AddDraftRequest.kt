package com.perpushub.bandung.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddDraftRequest(
    val bookId: Int
)
