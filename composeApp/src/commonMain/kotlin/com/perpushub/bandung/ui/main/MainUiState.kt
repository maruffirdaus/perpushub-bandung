package com.perpushub.bandung.ui.main

import com.perpushub.bandung.common.model.User

data class MainUiState(
    val user: User? = null,
    val isLoginDialogOpen: Boolean = false,
    val isRegisterDialogOpen: Boolean = false,
    val isLoginDialogLoading: Boolean = false,
    val isRegisterDialogLoading: Boolean = false
)
