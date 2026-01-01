package com.perpushub.bandung.ui.main.borrowing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.ui.main.borrowing.component.LoanRequestItem
import com.perpushub.bandung.ui.main.common.component.Header
import com.perpushub.bandung.ui.main.common.component.LibraryDialog
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun BorrowingScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: BorrowingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BorrowingScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigate = onNavigate,
        mapState = viewModel.mapState
    )
}

@Composable
fun BorrowingScreenContent(
    uiState: BorrowingUiState,
    onEvent: (BorrowingEvent) -> Unit,
    onNavigate: (NavKey) -> Unit,
    mapState: MapState? = null
) {
    ContentDialog(
        title = "Terjadi kesalahan",
        visible = uiState.errorMessage != null,
        content = {
            Text("${uiState.errorMessage}")
        },
        primaryButtonText = "Tutup",
        onButtonClick = {
            when (it) {
                ContentDialogButton.Primary -> onEvent(BorrowingEvent.OnErrorMessageClear)
                else -> {}
            }
        },
        size = DialogSize.Min
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Peminjaman")
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ProgressRing()
            }
        } else {
            val lazyListState = rememberLazyListState()

            ScrollbarContainer(
                adapter = rememberScrollbarAdapter(lazyListState),
                modifier = Modifier.weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                    contentPadding = PaddingValues(
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 32.dp
                    )
                ) {
                    if (uiState.loanRequests.isEmpty()) {
                        item {
                            Text("Daftar permintaan peminjaman masih kosong. Jelajahi buku di halaman Beranda.")
                        }
                    } else {
                        itemsIndexed(uiState.loanRequests) { index, loanRequest ->
                            var selectedLibrary: LibraryDetail? by remember { mutableStateOf(null) }
                            var isLibraryDialogOpen by remember { mutableStateOf(false) }

                            LibraryDialog(
                                bookCopies = uiState.bookCopies,
                                libraries = uiState.libraries,
                                visible = isLibraryDialogOpen,
                                onDismissRequest = {
                                    isLibraryDialogOpen = false
                                },
                                mapState = mapState,
                                onSelectClick = {
                                    selectedLibrary = it
                                },
                                loading = uiState.isLibraryDialogLoading
                            )

                            LoanRequestItem(
                                loanRequest = loanRequest,
                                selectedLibrary = selectedLibrary?.name,
                                onItemClick = {
                                    onNavigate(MainNavKey.BookDetail(loanRequest.book.id))
                                },
                                onSelectLibraryClick = {
                                    isLibraryDialogOpen = true
                                    onEvent(
                                        BorrowingEvent.OnSelectLibraryDialogDataRefresh(
                                            loanRequest.book.id
                                        )
                                    )
                                },
                                onContinueClick = { dueDate -> },
                                onDeleteClick = {
                                    onEvent(
                                        BorrowingEvent.OnLoanRequestDelete(
                                            loanRequest.id
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                defaultExpanded = index == 0,
                                alternate = index % 2 == 0
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 1280, heightDp = 720)
private fun BorrowingScreenPreview() {
    AppTheme {
        BorrowingScreenContent(
            uiState = BorrowingUiState(),
            onEvent = {},
            onNavigate = {}
        )
    }
}