package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookDetail
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class BookRepository {
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
        delay(0.25.seconds)
        return Book.dummies.shuffled()
    }

    suspend fun getRecommendedBooks(): List<Book> {
        delay(0.25.seconds)
        return Book.dummies.shuffled()
    }

    suspend fun getBookDetail(id: Int): BookDetail? {
        delay(0.25.seconds)
        return BookDetail.dummies[id]
    }

    suspend fun getBookCopies(id: Int): List<BookCopy> {
        delay(0.25.seconds)
        return BookCopy.dummies[id] ?: listOf()
    }
}