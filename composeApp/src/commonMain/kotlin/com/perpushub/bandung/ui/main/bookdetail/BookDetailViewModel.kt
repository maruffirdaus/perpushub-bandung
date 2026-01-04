package com.perpushub.bandung.ui.main.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRequestRepository
import com.perpushub.bandung.service.SessionManager
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import com.perpushub.bandung.ui.main.common.util.GeoUtil
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

class BookDetailViewModel(
    private val id: Int,
    private val sessionManager: SessionManager,
    private val bookRepository: BookRepository,
    private val libraryRepository: LibraryRepository,
    private val loanRequestRepository: LoanRequestRepository,
    private val tileStreamProvider: TileStreamProvider,
    private val uiMessageManager: UiMessageManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState = _uiState
        .onStart { loadInitialData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            BookDetailUiState()
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
            scroll(
                GeoUtil.lonToRelativeX(107.61809010534124),
                GeoUtil.latToRelativeY(-6.917757178073011)
            )
        }.apply {
            addLayer(tileStreamProvider)
        }

        viewModelScope.launch {
            sessionManager.session.collect { session ->
                _uiState.update {
                    it.copy(isLoggedIn = session != null)
                }
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(
                        book = bookRepository.getBookDetail(id),
                        libraries = libraryRepository.getLibraries()
                    )
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun onEvent(event: BookDetailEvent) {
        when (event) {
            is BookDetailEvent.OnLibraryDialogOpen -> openLibraryDialog()
            is BookDetailEvent.OnLibraryDialogClose -> closeLibraryDialog()
            is BookDetailEvent.OnBorrow -> borrow(event.onSuccess)
        }
    }

    fun openLibraryDialog() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLibraryDialogOpen = true)
            }
            _uiState.update {
                it.copy(isLibraryDialogLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(bookCopies = bookRepository.getBookCopies(id))
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLibraryDialogLoading = false)
                }
            }
        }
    }

    fun closeLibraryDialog() {
        _uiState.update {
            it.copy(isLibraryDialogOpen = false)
        }
    }

    private fun borrow(onSuccess: () -> Unit) {
        if (sessionManager.session.value == null) {
            uiMessageManager.emitMessage(UiError("Masuk untuk melanjutkan."))
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                sessionManager.session.value?.userId?.let { userId ->
                    loanRequestRepository.addDraft(userId, id)
                    onSuccess()
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}