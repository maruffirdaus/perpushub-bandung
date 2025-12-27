package com.perpushub.bandung.ui.delivery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeliveryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DeliveryUiState())
    val uiState = _uiState.asStateFlow()
}