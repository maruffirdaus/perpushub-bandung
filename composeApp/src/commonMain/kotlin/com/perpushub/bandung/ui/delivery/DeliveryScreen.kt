package com.perpushub.bandung.ui.delivery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
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
fun DeliveryScreen(
    viewModel: DeliveryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeliveryScreenContent(
        uiState = uiState
    )
}

@Composable
fun DeliveryScreenContent(
    uiState: DeliveryUiState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Pengantaran")
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

            }
        }
    }
}

@Composable
@Preview
private fun DeliveryScreenPreview() {
    AppTheme {
        DeliveryScreenContent(
            uiState = DeliveryUiState()
        )
    }
}