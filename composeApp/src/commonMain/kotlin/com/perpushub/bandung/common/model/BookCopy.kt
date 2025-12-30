package com.perpushub.bandung.common.model

data class BookCopy(
    val id: Int,
    val book: Book,
    val library: Library,
    val status: BookCopyStatus
) {
    companion object {
        val dummies = mapOf(
            0 to mutableListOf(
                BookCopy(
                    id = 0,
                    book = Book.dummies[0],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 1,
                    book = Book.dummies[0],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 2,
                    book = Book.dummies[0],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
            ),
            1 to mutableListOf(
                BookCopy(
                    id = 3,
                    book = Book.dummies[1],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 4,
                    book = Book.dummies[1],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 5,
                    book = Book.dummies[1],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
            ),
            2 to mutableListOf(
                BookCopy(
                    id = 6,
                    book = Book.dummies[2],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 7,
                    book = Book.dummies[1],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 8,
                    book = Book.dummies[1],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
            )
        )
    }
}
