package com.perpushub.bandung.ui.borrowing

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
import com.perpushub.bandung.ui.borrowing.component.LoanRequestCard
import com.perpushub.bandung.ui.borrowing.component.SelectLibraryDialog
import com.perpushub.bandung.ui.common.component.Header
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
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
        onSelectLibraryDialogDataRefresh = viewModel::refreshSelectLibraryDialogData,
        onLoanRequestDelete = viewModel::deleteLoanRequest,
        onNavigate = onNavigate,
        mapState = viewModel.mapState
    )
}

@Composable
fun BorrowingScreenContent(
    uiState: BorrowingUiState,
    onSelectLibraryDialogDataRefresh: (Int) -> Unit,
    onLoanRequestDelete: (Int) -> Unit,
    onNavigate: (NavKey) -> Unit,
    mapState: MapState? = null
) {
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
                            var selectedLibraryId: Int? by remember { mutableStateOf(null) }
                            var isSelectLibraryDialogOpen by remember { mutableStateOf(false) }

                            SelectLibraryDialog(
                                bookCopies = uiState.bookCopies,
                                libraries = uiState.libraries,
                                visible = isSelectLibraryDialogOpen,
                                loading = uiState.isSelectLibraryDialogLoading,
                                onDismissRequest = {
                                    isSelectLibraryDialogOpen = false
                                },
                                mapState = mapState
                            )
                            LoanRequestCard(
                                loanRequest = loanRequest,
                                selectedLibrary = uiState.libraries.find { it.id == selectedLibraryId }?.name,
                                onCardClick = {
                                    onNavigate(AppNavKey.BookDetail(loanRequest.book.id))
                                },
                                onSelectLibraryClick = {
                                    isSelectLibraryDialogOpen = true
                                    onSelectLibraryDialogDataRefresh(loanRequest.book.id)
                                },
                                onContinueClick = { dueDate -> },
                                onDeleteClick = {
                                    onLoanRequestDelete(loanRequest.id)
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
            onSelectLibraryDialogDataRefresh = {},
            onLoanRequestDelete = {},
            onNavigate = {}
        )
    }
}