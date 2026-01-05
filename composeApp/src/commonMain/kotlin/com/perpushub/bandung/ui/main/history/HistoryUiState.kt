package com.perpushub.bandung.ui.main.history

import com.perpushub.bandung.common.model.Loan

data class HistoryUiState(
    val loans: List<Loan> = emptyList(),
    val isLoading: Boolean = false
)
