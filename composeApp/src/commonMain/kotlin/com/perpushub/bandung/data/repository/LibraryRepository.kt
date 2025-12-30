package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.LibraryDetail
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class LibraryRepository {
    suspend fun getLibraries(): List<LibraryDetail> {
        delay(0.25.seconds)
        return LibraryDetail.dummies.sortedBy { it.name }
    }
}