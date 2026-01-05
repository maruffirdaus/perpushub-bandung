package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.data.remote.LibraryService

class LibraryRepository(
    private val service: LibraryService
) {
    suspend fun getLibraries(): List<LibraryDetail> {
        val response = service.getLibraries()
        return response.data ?: throw Exception(response.message)
    }
}