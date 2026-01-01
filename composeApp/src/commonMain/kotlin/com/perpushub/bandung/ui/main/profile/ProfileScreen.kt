package com.perpushub.bandung.ui.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perpushub.bandung.ui.main.common.component.Header
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        uiState = uiState
    )
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileUiState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Profil")
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
            val scrollState = rememberScrollState()

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
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreenContent(
            uiState = ProfileUiState()
        )
    }
}