package com.perpushub.bandung.ui.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perpushub.bandung.ui.common.component.Header
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookDetailScreen(
    id: Int,
    viewModel: BookDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.refreshBookDetail(id)
    }

    BookDetailScreenContent(
        uiState = uiState
    )
}

@Composable
fun BookDetailScreenContent(
    uiState: BookDetailUiState
) {
    if (uiState.isLoading || uiState.bookDetail == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressRing()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val scrollState = rememberScrollState()

            Header(uiState.bookDetail.title)
            ScrollbarContainer(
                adapter = rememberScrollbarAdapter(scrollState),
                modifier = Modifier.weight(1f)
            ) {

            }
        }
    }
}

@Composable
@Preview
private fun BookDetailScreenPreview() {
    AppTheme {
        BookDetailScreenContent(
            uiState = BookDetailUiState()
        )
    }
}