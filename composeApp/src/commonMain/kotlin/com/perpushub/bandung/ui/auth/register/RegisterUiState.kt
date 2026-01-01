package com.perpushub.bandung.ui.auth.register

data class RegisterUiState(
    val username: String = "",
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val confirmPassword: String = "",
    val isConfirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)
