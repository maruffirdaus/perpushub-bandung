package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
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
                )
            ),
            1 to listOf(
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
                )
            ),
            2 to listOf(
                BookCopy(
                    id = 6,
                    book = Book.dummies[2],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 7,
                    book = Book.dummies[2],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 8,
                    book = Book.dummies[2],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            3 to listOf(
                BookCopy(
                    id = 9,
                    book = Book.dummies[3],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 10,
                    book = Book.dummies[3],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 11,
                    book = Book.dummies[3],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            4 to listOf(
                BookCopy(
                    id = 12,
                    book = Book.dummies[4],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 13,
                    book = Book.dummies[4],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 14,
                    book = Book.dummies[4],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            5 to listOf(
                BookCopy(
                    id = 13,
                    book = Book.dummies[5],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 14,
                    book = Book.dummies[5],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 15,
                    book = Book.dummies[5],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            6 to listOf(
                BookCopy(
                    id = 14,
                    book = Book.dummies[6],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 15,
                    book = Book.dummies[6],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 16,
                    book = Book.dummies[6],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            7 to listOf(
                BookCopy(
                    id = 17,
                    book = Book.dummies[7],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 18,
                    book = Book.dummies[7],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 19,
                    book = Book.dummies[7],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
            ),
            8 to listOf(
                BookCopy(
                    id = 20,
                    book = Book.dummies[8],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 21,
                    book = Book.dummies[8],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 22,
                    book = Book.dummies[8],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            9 to listOf(
                BookCopy(
                    id = 23,
                    book = Book.dummies[9],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 24,
                    book = Book.dummies[9],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 25,
                    book = Book.dummies[9],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            10 to listOf(
                BookCopy(
                    id = 26,
                    book = Book.dummies[10],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 27,
                    book = Book.dummies[10],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 28,
                    book = Book.dummies[10],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            11 to listOf(
                BookCopy(
                    id = 29,
                    book = Book.dummies[11],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 30,
                    book = Book.dummies[11],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 31,
                    book = Book.dummies[11],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            12 to listOf(
                BookCopy(
                    id = 32,
                    book = Book.dummies[12],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 33,
                    book = Book.dummies[12],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 34,
                    book = Book.dummies[12],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            13 to listOf(
                BookCopy(
                    id = 35,
                    book = Book.dummies[13],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 36,
                    book = Book.dummies[13],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 37,
                    book = Book.dummies[13],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            14 to listOf(
                BookCopy(
                    id = 38,
                    book = Book.dummies[14],
                    library = Library.dummies[1],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 39,
                    book = Book.dummies[14],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                ),
                BookCopy(
                    id = 40,
                    book = Book.dummies[14],
                    library = Library.dummies[0],
                    status = BookCopyStatus.AVAILABLE
                )
            ),
            15 to listOf()
        )
    }
}
