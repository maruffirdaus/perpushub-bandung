package com.perpushub.bandung.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SubmitDraftRequest(
    val libraryId: Int,
    val addressId: Int,
    val dueDate: String
)
