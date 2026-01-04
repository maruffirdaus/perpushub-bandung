package com.perpushub.bandung.ui.main.borrowing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRequestRepository
import com.perpushub.bandung.data.repository.UserRepository
import com.perpushub.bandung.service.SessionManager
import com.perpushub.bandung.ui.common.messaging.UiError
import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import com.perpushub.bandung.ui.main.borrowing.model.BorrowTab
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

class BorrowingViewModel(
    private val sessionManager: SessionManager,
    private val loanRequestRepository: LoanRequestRepository,
    private val bookRepository: BookRepository,
    private val libraryRepository: LibraryRepository,
    private val userRepository: UserRepository,
    private val tileStreamProvider: TileStreamProvider,
    private val uiMessageManager: UiMessageManager
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
            scroll(
                GeoUtil.lonToRelativeX(107.61809010534124),
                GeoUtil.latToRelativeY(-6.917757178073011)
            )
        }.apply {
            addLayer(tileStreamProvider)
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(libraries = libraryRepository.getLibraries())
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

    fun onEvent(event: BorrowingEvent) {
        when (event) {
            is BorrowingEvent.OnSelectedTabChange -> changeSelectedTab(event.tab)
            is BorrowingEvent.OnCartsRefresh -> refreshCarts()
            is BorrowingEvent.OnRequestsRefresh -> refreshRequests()
            is BorrowingEvent.OnDeliveriesRefresh -> {}
            is BorrowingEvent.OnLoansRefresh -> {}
            is BorrowingEvent.OnLibraryDialogRefresh -> refreshLibraryDialog(event.bookId)
            is BorrowingEvent.OnAddressPickerDialogRefresh -> refreshAddressPickerDialog()
            is BorrowingEvent.OnSubmitLoanRequest -> submitLoanRequest(
                event.libraryId,
                event.addressId,
                event.dueDate
            )

            is BorrowingEvent.OnCartDelete -> deleteCart(event.id)
        }
    }

    private fun changeSelectedTab(tab: BorrowTab) {
        _uiState.update {
            it.copy(selectedTab = tab)
        }
    }

    private fun refreshCarts() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(carts = loanRequestRepository.getDrafts(userId))
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

    private fun refreshRequests() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(requests = loanRequestRepository.getSubmitted(userId))
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

    private fun refreshLibraryDialog(bookId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLibraryDialogLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(bookCopies = bookRepository.getBookCopies(bookId))
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

    private fun refreshAddressPickerDialog() {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isAddressPickerDialogLoading = true)
            }
            try {
                _uiState.update {
                    it.copy(addresses = userRepository.getAddresses(userId))
                }
            } catch (e: Exception) {
                uiMessageManager.emitMessage(UiError(e.message ?: "Unknown error."))
            } finally {
                _uiState.update {
                    it.copy(isAddressPickerDialogLoading = false)
                }
            }
        }
    }

    private fun submitLoanRequest(libraryId: Int, addressId: Int, dueDate: String) {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                loanRequestRepository.submitDraft(userId, libraryId, addressId, dueDate)
                _uiState.update {
                    it.copy(carts = loanRequestRepository.getDrafts(userId))
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

    private fun deleteCart(id: Int) {
        val userId = sessionManager.session.value?.userId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            try {
                loanRequestRepository.deleteDraft(id)
                _uiState.update {
                    it.copy(carts = loanRequestRepository.getDrafts(userId))
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