package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookDetail
import com.perpushub.bandung.data.remote.BookService
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class BookRepository(
    private val service: BookService
) {
    suspend fun searchBooks(query: String): List<Book> {
        delay(0.25.seconds)
        return Book.dummies.mapNotNull {
            if (it.title.lowercase().contains(query.lowercase()) ||
                it.authors.any { author -> author.name.lowercase().contains(query.lowercase()) }
            ) {
                it
            } else {
                null
            }
        }
    }

    suspend fun getTopBooks(): List<Book> {
        val response = service.getTopBooks()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getRecommendedBooks(): List<Book> {
        val response = service.getRecommendedBooks()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getBookDetail(id: Int): BookDetail? {
        val response = service.getBookDetail(id)
        return response.data
    }

    suspend fun getBookCopies(id: Int): List<BookCopy> {
        val response = service.getBookCopies(id)
        return response.data ?: throw Exception(response.message)
    }
}