package com.perpushub.bandung.common.model

data class User(
    val id: Int,
    val username: String,
    val fullName: String,
    val email: String,
    val address: List<Address>,
) {
    companion object {
        val dummies = mutableListOf(
            User(
                id = 0,
                username = "maruffirdaus",
                fullName = "Maruf Firdaus",
                email = "maruffirdaus@outlook.com",
                address = Address.dummies[0] ?: listOf()
            )
        )
    }
}
