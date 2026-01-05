package com.perpushub.bandung.data.remote.model.response

import com.perpushub.bandung.common.model.LibraryDetail
import kotlinx.serialization.Serializable

@Serializable
data class GetLibrariesResponse(
    val status: String,
    val message: String,
    val data: List<LibraryDetail>?
)