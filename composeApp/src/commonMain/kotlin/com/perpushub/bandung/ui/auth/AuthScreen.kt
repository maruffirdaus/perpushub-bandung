package com.perpushub.bandung.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass
import coil3.compose.AsyncImage
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel
import perpushubbandung.composeapp.generated.resources.Res

@Composable
fun AuthScreen(
    navDisplay: @Composable () -> Unit,
    onNavigate: (NavKey) -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AuthScreenContent(
        uiState = uiState,
        navDisplay = navDisplay,
        onNavigate = onNavigate
    )
}

@Composable
fun AuthScreenContent(
    uiState: AuthUiState,
    navDisplay: @Composable () -> Unit,
    onNavigate: (NavKey) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) onNavigate(AppNavKey.Main)
    }

    if (isAtLeastMediumBreakpoint) {
        val scrollState = rememberScrollState()

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(FluentTheme.colors.background.layer.alt)
        ) {
            ImageSection(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            ScrollbarContainer(
                adapter = rememberScrollbarAdapter(scrollState),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    navDisplay()
                }
            }
        }
    } else {
        val scrollState = rememberScrollState()

        ScrollbarContainer(
            adapter = rememberScrollbarAdapter(scrollState),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FluentTheme.colors.background.layer.alt)
                    .verticalScroll(scrollState)
            ) {
                ImageSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                )
                navDisplay()
            }
        }
    }
}

@Composable
private fun ImageSection(
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = Res.getUri("drawable/img_auth.png"),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
private fun AuthScreenPreview() {
    AppTheme {
        AuthScreenContent(
            uiState = AuthUiState(),
            navDisplay = {},
            onNavigate = {}
        )
    }
}