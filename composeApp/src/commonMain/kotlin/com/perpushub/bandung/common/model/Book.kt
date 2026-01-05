package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val description: String,
    val coverUrl: String
) {
    companion object {
        val dummies = BookDetail.dummies.map {
            Book(
                id = it.id,
                title = it.title,
                authors = it.authors,
                description = it.description,
                coverUrl = it.coverUrl
            )
        }
    }
}
