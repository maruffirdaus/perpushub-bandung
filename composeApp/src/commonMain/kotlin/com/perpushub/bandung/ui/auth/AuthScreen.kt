package com.perpushub.bandung.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoggedIn) {
        onNavigate(MainNavKey.Home)
    }

    AuthScreenContent(
        uiState = uiState,
        onNavigate = onNavigate
    )
}

@Composable
fun AuthScreenContent(
    uiState: AuthUiState,
    onNavigate: (NavKey) -> Unit
) {
}

@Composable
@Preview
private fun AuthScreenPreview() {
    AppTheme {
        AuthScreenContent(
            uiState = AuthUiState(),
            onNavigate = {}
        )
    }
}