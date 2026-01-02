package com.perpushub.bandung.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.Icon
import io.github.composefluent.component.LocalContentDialog
import io.github.composefluent.component.MenuItem
import io.github.composefluent.component.NavigationDefaults
import io.github.composefluent.component.NavigationDisplayMode
import io.github.composefluent.component.NavigationView
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberNavigationState
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Book
import io.github.composefluent.icons.regular.History
import io.github.composefluent.icons.regular.Home
import io.github.composefluent.icons.regular.Person
import io.github.composefluent.icons.regular.SignOut
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    navDisplay: @Composable () -> Unit,
    backStack: List<NavKey>,
    onNavigate: (NavKey) -> Unit,
    onNavigateBack: () -> Unit,
    backButtonEnabled: Boolean = true,
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        navDisplay = navDisplay,
        backStack = backStack,
        onNavigate = onNavigate,
        onNavigateBack = onNavigateBack,
        backButtonEnabled = backButtonEnabled
    )
}

@Composable
fun MainScreenContent(
    uiState: MainUiState,
    onEvent: (MainEvent) -> Unit,
    navDisplay: @Composable () -> Unit,
    backStack: List<NavKey>,
    onNavigate: (NavKey) -> Unit,
    onNavigateBack: () -> Unit,
    backButtonEnabled: Boolean = false
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    val isAtLeastExpandedBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    val dialog = LocalContentDialog.current
    val scope = rememberCoroutineScope()

    NavigationView(
        menuItems = {
            item {
                val isSelected = backStack.lastOrNull() is MainNavKey.Home

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(MainNavKey.Home)
                    },
                    text = {
                        Text("Beranda")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Regular.Home,
                            contentDescription = "Beranda"
                        )
                    }
                )
            }
            if (uiState.user != null) {
                item {
                    val isSelected = backStack.lastOrNull() is MainNavKey.Borrowing

                    MenuItem(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) onNavigate(MainNavKey.Borrowing)
                        },
                        text = {
                            Text("Peminjaman")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Regular.Book,
                                contentDescription = "Peminjaman"
                            )
                        }
                    )
                }
                item {
                    val isSelected = backStack.lastOrNull() is MainNavKey.History

                    MenuItem(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) onNavigate(MainNavKey.History)
                        },
                        text = {
                            Text("Riwayat")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Regular.History,
                                contentDescription = "Riwayat"
                            )
                        }
                    )
                }
            }
        },
        modifier = Modifier.safeContentPadding(),
        displayMode = if (isAtLeastExpandedBreakpoint) {
            NavigationDisplayMode.Left
        } else if (isAtLeastMediumBreakpoint) {
            NavigationDisplayMode.LeftCompact
        } else {
            NavigationDisplayMode.LeftCollapsed
        },
        state = rememberNavigationState(
            initialExpanded = false
        ),
        backButton = if (backButtonEnabled) {
            {
                NavigationDefaults.BackButton(
                    onClick = onNavigateBack,
                    disabled = backStack.size == 1
                )
            }
        } else {
            {}
        },
        footerItems = {
            if (uiState.user != null) {
                item {
                    val isSelected = backStack.lastOrNull() is MainNavKey.Profile

                    MenuItem(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) onNavigate(MainNavKey.Profile)
                        },
                        text = {
                            Text(uiState.user.fullName)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Regular.Person,
                                contentDescription = "Profil"
                            )
                        }
                    )
                }
                item {
                    MenuItem(
                        selected = false,
                        onClick = {
                            scope.launch {
                                val result = dialog.show(
                                    title = "Keluar?",
                                    contentText = "Anda akan keluar dari akun.",
                                    primaryButtonText = "Ya",
                                    closeButtonText = "Tidak",
                                    size = DialogSize.Min
                                )
                                if (result == ContentDialogButton.Primary) {
                                    onEvent(MainEvent.OnLogout)
                                    onNavigate(AppNavKey.Auth)
                                }
                            }
                        },
                        text = {
                            Text("Keluar")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Regular.SignOut,
                                contentDescription = "Keluar"
                            )
                        }
                    )
                }
            } else {
                item {
                    MenuItem(
                        selected = false,
                        onClick = {
                            onNavigate(AppNavKey.Auth)
                        },
                        text = {
                            Text("Masuk atau daftar")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Regular.Person,
                                contentDescription = "Masuk atau daftar"
                            )
                        }
                    )
                }
            }
        },
        contentPadding = if (isAtLeastMediumBreakpoint) {
            PaddingValues()
        } else {
            PaddingValues(top = 48.dp)
        },
    ) {
        navDisplay()
    }
}

@Composable
@Preview(widthDp = 1280, heightDp = 720)
private fun MainScreenPreview() {
    AppTheme {
        MainScreenContent(
            uiState = MainUiState(),
            onEvent = {},
            navDisplay = {},
            backStack = emptyList(),
            onNavigate = {},
            onNavigateBack = {}
        )
    }
}