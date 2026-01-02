package com.perpushub.bandung.ui.common.extension

fun String.isValidUsername(): Boolean {
    val usernameRegex = """^(?!.*\.\.)(?!^\.)(?!.*\.$)[a-z0-9._]{1,30}$""".toRegex()
    return this.isNotBlank() && usernameRegex.matches(this)
}

fun String.isValidEmail(): Boolean {
    val emailRegex = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
    return this.isNotBlank() && emailRegex.matches(this)
}

fun String.isValidPassword(): Boolean {
    return this.length in 8..32
}