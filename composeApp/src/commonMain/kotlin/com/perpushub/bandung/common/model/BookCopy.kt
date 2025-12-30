package com.perpushub.bandung.common.model

data class BookCopy(
    val id: Int,
    val book: Book,
    val library: Library,
    val status: BookCopyStatus
) {
    companion object {
        val dummies = mutableMapOf(
            0 to listOf(
                BookCopy(
                    id = 0,
                    book = Book.dummies[0],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 1,
                    book = Book.dummies[0],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 2,
                    book = Book.dummies[0],
                    library = Library(
                        id = LibraryDetail.dummies[1].id,
                        name = LibraryDetail.dummies[1].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
            ),
            1 to listOf(
                BookCopy(
                    id = 3,
                    book = Book.dummies[1],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 4,
                    book = Book.dummies[1],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 5,
                    book = Book.dummies[1],
                    library = Library(
                        id = LibraryDetail.dummies[1].id,
                        name = LibraryDetail.dummies[1].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
            ),
            2 to listOf(
                BookCopy(
                    id = 6,
                    book = Book.dummies[2],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 7,
                    book = Book.dummies[1],
                    library = Library(
                        id = LibraryDetail.dummies[0].id,
                        name = LibraryDetail.dummies[0].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 8,
                    book = Book.dummies[1],
                    library = Library(
                        id = LibraryDetail.dummies[1].id,
                        name = LibraryDetail.dummies[1].name
                    ),
                    status = BookCopyStatus.AVAILABLE
                ),
            )
        )
    }
}
