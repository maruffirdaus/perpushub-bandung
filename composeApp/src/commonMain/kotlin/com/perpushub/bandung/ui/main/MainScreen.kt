package com.perpushub.bandung.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.Icon
import io.github.composefluent.component.MenuItem
import io.github.composefluent.component.NavigationDefaults
import io.github.composefluent.component.NavigationDisplayMode
import io.github.composefluent.component.NavigationView
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberNavigationState
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.BookAdd
import io.github.composefluent.icons.regular.Box
import io.github.composefluent.icons.regular.History
import io.github.composefluent.icons.regular.Home
import io.github.composefluent.icons.regular.PersonCircle

@Composable
fun MainScreen(
    navDisplay: @Composable () -> Unit,
    backStack: List<NavKey>,
    onNavigate: (NavKey) -> Unit,
    onBack: () -> Unit,
    backButtonEnabled: Boolean = false
) {
    MainScreenContent(
        navDisplay = navDisplay,
        backStack = backStack,
        onNavigate = onNavigate,
        onBack = onBack,
        backButtonEnabled = backButtonEnabled
    )
}

@Composable
fun MainScreenContent(
    navDisplay: @Composable () -> Unit,
    backStack: List<NavKey>,
    onNavigate: (NavKey) -> Unit,
    onBack: () -> Unit,
    backButtonEnabled: Boolean = false
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    val isAtLeastExpandedBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    NavigationView(
        menuItems = {
            item {
                val isSelected = backStack.lastOrNull() == AppNavKey.Home

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(AppNavKey.Home)
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
            item {
                val isSelected = backStack.lastOrNull() == AppNavKey.Borrow

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(AppNavKey.Borrow)
                    },
                    text = {
                        Text("Pinjam")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Regular.BookAdd,
                            contentDescription = "Pinjam"
                        )
                    }
                )
            }
            item {
                val isSelected = backStack.lastOrNull() == AppNavKey.Delivery

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(AppNavKey.Delivery)
                    },
                    text = {
                        Text("Pengantaran")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Regular.Box,
                            contentDescription = "Pengantaran"
                        )
                    }
                )
            }
            item {
                val isSelected = backStack.lastOrNull() == AppNavKey.History

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(AppNavKey.History)
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
        },
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
                    onClick = onBack,
                    disabled = backStack.size == 1
                )
            }
        } else {
            {}
        },
        footerItems = {
            item {
                val isSelected = backStack.lastOrNull() == AppNavKey.Profile

                MenuItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) onNavigate(AppNavKey.Profile)
                    },
                    text = {
                        Text("Profil")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Regular.PersonCircle,
                            contentDescription = "Profil"
                        )
                    }
                )
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
            navDisplay = {},
            backStack = emptyList(),
            onNavigate = {},
            onBack = {}
        )
    }
}