package com.perpushub.bandung.ui.main.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perpushub.bandung.ui.main.common.component.Header
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HistoryScreenContent(
    uiState: HistoryUiState,
    onEvent: (HistoryEvent) -> Unit
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
                ContentDialogButton.Primary -> onEvent(HistoryEvent.OnErrorMessageClear)
                else -> {}
            }
        },
        size = DialogSize.Min
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Riwayat")
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
                    item {
                        Text("Belum ada riwayat peminjaman.")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun HistoryScreenPreview() {
    AppTheme {
        HistoryScreenContent(
            uiState = HistoryUiState(),
            onEvent = {}
        )
    }
}