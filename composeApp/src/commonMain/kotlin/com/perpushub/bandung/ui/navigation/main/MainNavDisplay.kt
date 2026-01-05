package com.perpushub.bandung.ui.navigation.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.perpushub.bandung.ui.main.bookdetail.BookDetailScreen
import com.perpushub.bandung.ui.main.borrowing.BorrowingScreen
import com.perpushub.bandung.ui.main.history.HistoryScreen
import com.perpushub.bandung.ui.main.home.HomeScreen
import com.perpushub.bandung.ui.main.profile.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavDisplay(
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
            entry<MainNavKey.Home> {
                HomeScreen(
                    onNavigate = onNavigate
                )
            }
            entry<MainNavKey.BookDetail> { key ->
                BookDetailScreen(
                    onNavigate = onNavigate,
                    viewModel = koinViewModel { parametersOf(key.id) }
                )
            }
            entry<MainNavKey.Borrowing> {
                BorrowingScreen(
                    onNavigate = onNavigate
                )
            }
            entry<MainNavKey.History> {
                HistoryScreen(
                    onNavigate = onNavigate
                )
            }
            entry<MainNavKey.Profile> {
                ProfileScreen()
            }
        }
    )
}