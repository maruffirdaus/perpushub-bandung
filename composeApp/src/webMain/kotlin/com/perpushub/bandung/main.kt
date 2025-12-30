package com.perpushub.bandung

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation3.runtime.NavKey
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation
import com.github.terrakok.navigation3.browser.buildBrowserHistoryFragment
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentName
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentParameters
import com.perpushub.bandung.ui.navigation.AppNavKey

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val backStack = remember { mutableStateListOf<NavKey>(AppNavKey.Home) }
        ChronologicalBrowserNavigation(
            backStack = backStack,
            saveKey = { key ->
                when (key) {
                    is AppNavKey.Home -> buildBrowserHistoryFragment("home")
                    is AppNavKey.BookDetail -> buildBrowserHistoryFragment("book-detail", mapOf("id" to key.id.toString()))
                    is AppNavKey.Borrowing -> buildBrowserHistoryFragment("borrowing")
                    is AppNavKey.Delivery -> buildBrowserHistoryFragment("delivery")
                    is AppNavKey.History -> buildBrowserHistoryFragment("history")
                    is AppNavKey.Profile -> buildBrowserHistoryFragment("profile")
                    else -> null
                }
            },
            restoreKey = { fragment ->
                when (getBrowserHistoryFragmentName(fragment)) {
                    "root" -> AppNavKey.Home
                    "home" -> AppNavKey.Home
                    "book-detail" -> AppNavKey.BookDetail(
                        getBrowserHistoryFragmentParameters(fragment).getValue("id")?.toInt()
                            ?: error("id is required")
                    )
                    "borrowing" -> AppNavKey.Borrowing
                    "delivery" -> AppNavKey.Delivery
                    "history" -> AppNavKey.History
                    "profile" -> AppNavKey.Profile
                    else -> null
                }
            }
        )
        App(
            backButtonEnabled = false,
            backStack = backStack
        )
    }
}