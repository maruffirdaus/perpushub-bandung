package com.perpushub.bandung.ui.main

import com.perpushub.bandung.common.model.User

data class MainUiState(
    val user: User? = null,
    val isLoading: Boolean = false
)
