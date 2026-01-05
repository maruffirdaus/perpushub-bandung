package com.perpushub.bandung.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String
) {
    companion object {
        val dummies = listOf(
            Category(
                id = 0,
                name = "Comics & Graphic Novels"
            ),
            Category(
                id = 1,
                name = "Fiction"
            )
        )
    }
}
