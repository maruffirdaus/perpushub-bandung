package com.perpushub.bandung.ui.navigation.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.perpushub.bandung.ui.auth.login.LoginScreen
import com.perpushub.bandung.ui.auth.register.RegisterScreen

@Composable
fun AuthNavDisplay(
    backStack: MutableList<NavKey>,
    onNavigate: (NavKey) -> Unit = backStack::add
) {
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AuthNavKey.Login> {
                LoginScreen(
                    onNavigate = onNavigate
                )
            }
            entry<AuthNavKey.Register> {
                RegisterScreen(
                    onNavigate = onNavigate,
                    onNavigateBack = {
                        if (backStack.size > 1) {
                            backStack.removeLast()
                        }
                    }
                )
            }
        }
    )
}