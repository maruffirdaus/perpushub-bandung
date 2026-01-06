package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookDetail
import com.perpushub.bandung.data.remote.BookService

class BookRepository(
    private val service: BookService
) {
    suspend fun searchBooks(query: String): List<Book> {
        val response = service.searchBooks(query)
        return response.data ?: throw Exception(response.message)
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

    suspend fun getSimilarBooks(id: Int): List<Book> {
        val response = service.getSimilarBooks(id)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getBookCopies(id: Int): List<BookCopy> {
        val response = service.getBookCopies(id)
        return response.data ?: throw Exception(response.message)
    }
}