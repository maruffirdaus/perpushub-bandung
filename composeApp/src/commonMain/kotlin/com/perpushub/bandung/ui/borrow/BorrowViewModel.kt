package com.perpushub.bandung.ui.borrow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BorrowViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BorrowUiState())
    val uiState = _uiState.asStateFlow()
}