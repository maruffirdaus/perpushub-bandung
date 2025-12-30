package com.perpushub.bandung.ui.borrowing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRepository
import com.perpushub.bandung.service.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.layout.Forced
import ovh.plrapps.mapcompose.ui.state.MapState
import kotlin.math.pow

class BorrowingViewModel(
    private val sessionManager: SessionManager,
    private val loanRepository: LoanRepository,
    private val bookRepository: BookRepository,
    private val libraryRepository: LibraryRepository,
    private val tileStreamProvider: TileStreamProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(BorrowingUiState())
    val uiState = _uiState
        .onStart { loadInitialData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            BorrowingUiState()
        )

    var mapState: MapState

    init {
        val maxLevel = 19
        val minLevel = 12
        val tileSize = 256
        val mapSize = tileSize * 2.0.pow(maxLevel).toInt()

        mapState = MapState(
            levelCount = maxLevel + 1,
            fullWidth = mapSize,
            fullHeight = mapSize,
            tileSize = tileSize,
            workerCount = 16
        ) {
            minimumScaleMode(Forced(1 / 2.0.pow(maxLevel - minLevel)))
        }.apply {
            addLayer(tileStreamProvider)
        }

        viewModelScope.launch {
            sessionManager.session.collect { session ->
                if (session == null) {
                    _uiState.update {
                        it.copy(errorMessage = "Masuk untuk melanjutkan")
                    }
                }
            }
        }
    }

    private fun loadInitialData() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            _uiState.update {
                it.copy(
                    loanRequests = loanRepository.getLoanRequests(userId),
                    libraries = libraryRepository.getLibraries()
                )
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun refreshSelectLibraryDialogData(bookId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isSelectLibraryDialogLoading = true)
            }
            _uiState.update {
                it.copy(bookCopies = bookRepository.getBookCopies(bookId))
            }
            _uiState.update {
                it.copy(isSelectLibraryDialogLoading = false)
            }
        }
    }

    fun deleteLoanRequest(id: Int) {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            loanRepository.deleteLoanRequest(id)
            _uiState.update {
                it.copy(loanRequests = loanRepository.getLoanRequests(userId))
            }
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}