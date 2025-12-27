package com.perpushub.bandung.data.repository

import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.BookDetail

class BookRepository {
    fun searchBooks(query: String): List<Book> = Book.dummies.shuffled()

    fun getTopBooks(): List<Book> = Book.dummies.shuffled()

    fun getBookDetail(id: Int): BookDetail? = BookDetail.dummies[id]
}