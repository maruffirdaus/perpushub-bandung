package com.perpushub.bandung.common.model

data class Author(
    val id: Int,
    val name: String
) {
    companion object {
        val dummies = listOf(
            Author(
                id = 0,
                name = "Makoto Shinkai"
            )
        )
    }
}
