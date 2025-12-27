package com.perpushub.bandung.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.perpushub.bandung.ui.bookdetail.BookDetailScreen
import com.perpushub.bandung.ui.borrow.BorrowScreen
import com.perpushub.bandung.ui.delivery.DeliveryScreen
import com.perpushub.bandung.ui.history.HistoryScreen
import com.perpushub.bandung.ui.home.HomeScreen
import com.perpushub.bandung.ui.profile.ProfileScreen

@Composable
fun AppNavDisplay(
    backStack: List<NavKey>,
    onNavigate: (NavKey) -> Unit
) {
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppNavKey.Home> {
                HomeScreen(
                    onNavigate = onNavigate
                )
            }
            entry<AppNavKey.BookDetail> { key ->
                BookDetailScreen(
                    id = key.id
                )
            }
            entry<AppNavKey.Borrow> {
                BorrowScreen()
            }
            entry<AppNavKey.Delivery> {
                DeliveryScreen()
            }
            entry<AppNavKey.History> {
                HistoryScreen()
            }
            entry<AppNavKey.Profile> {
                ProfileScreen()
            }
        }
    )
}