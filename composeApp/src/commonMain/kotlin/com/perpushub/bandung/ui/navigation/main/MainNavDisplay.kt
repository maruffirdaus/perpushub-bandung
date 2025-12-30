package com.perpushub.bandung.ui.navigation.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.perpushub.bandung.ui.bookdetail.BookDetailScreen
import com.perpushub.bandung.ui.borrowing.BorrowingScreen
import com.perpushub.bandung.ui.delivery.DeliveryScreen
import com.perpushub.bandung.ui.history.HistoryScreen
import com.perpushub.bandung.ui.home.HomeScreen
import com.perpushub.bandung.ui.profile.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavDisplay(
    backStack: MutableList<NavKey>
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
                    onNavigate = { key ->
                        backStack.add(key)
                    }
                )
            }
            entry<MainNavKey.BookDetail> { key ->
                BookDetailScreen(
                    onNavigate = { key ->
                        backStack.add(key)
                    },
                    viewModel = koinViewModel { parametersOf(key.id) }
                )
            }
            entry<MainNavKey.Borrowing> {
                BorrowingScreen(
                    onNavigate = { key ->
                        backStack.add(key)
                    }
                )
            }
            entry<MainNavKey.Delivery> {
                DeliveryScreen()
            }
            entry<MainNavKey.History> {
                HistoryScreen()
            }
            entry<MainNavKey.Profile> {
                ProfileScreen()
            }
        }
    )
}