package com.perpushub.bandung.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.perpushub.bandung.ui.auth.AuthScreen
import com.perpushub.bandung.ui.main.MainScreen
import com.perpushub.bandung.ui.navigation.auth.AuthNavDisplay
import com.perpushub.bandung.ui.navigation.auth.AuthNavKey
import com.perpushub.bandung.ui.navigation.main.MainNavDisplay
import com.perpushub.bandung.ui.navigation.main.MainNavKey

@Composable
fun AppNavDisplay(
    appBackStack: MutableList<NavKey>,
    authBackStack: MutableList<NavKey>,
    mainBackStack: MutableList<NavKey>,
    backButtonEnabled: Boolean = true
) {
    NavDisplay(
        backStack = appBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppNavKey.Auth> {
                AuthScreen(
                    navDisplay = {
                        AuthNavDisplay(
                            backStack = authBackStack,
                            onNavigate = { key ->
                                when (key) {
                                    is AppNavKey -> appBackStack[0] = key
                                    is AuthNavKey -> authBackStack.add(key)
                                }
                            }
                        )
                    },
                    onNavigate = { key ->
                        appBackStack[0] = key
                    }
                )
            }
            entry<AppNavKey.Main> {
                MainScreen(
                    navDisplay = {
                        MainNavDisplay(
                            backStack = mainBackStack
                        )
                    },
                    backStack = mainBackStack,
                    onNavigate = { key ->
                        when (key) {
                            is AppNavKey -> appBackStack[0] = key
                            is MainNavKey -> mainBackStack.add(key)
                        }
                    },
                    onNavigateBack = {
                        if (mainBackStack.size > 1) {
                            mainBackStack.removeLast()
                        }
                    },
                    backButtonEnabled = backButtonEnabled
                )
            }
        }
    )
}