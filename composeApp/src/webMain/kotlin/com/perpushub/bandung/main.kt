package com.perpushub.bandung

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation3.runtime.NavKey
import com.github.terrakok.navigation3.browser.HierarchicalBrowserNavigation
import com.github.terrakok.navigation3.browser.buildBrowserHistoryFragment
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.navigation.auth.AuthNavKey
import com.perpushub.bandung.ui.navigation.main.MainNavKey

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val appBackStack = remember { mutableStateListOf<NavKey>(AppNavKey.Auth) }
        val authBackStack = remember { mutableStateListOf<NavKey>(AuthNavKey.Login) }
        val mainBackStack = remember { mutableStateListOf<NavKey>(MainNavKey.Home) }

        HierarchicalBrowserNavigation {
            when (appBackStack.lastOrNull()) {
                is AppNavKey.Auth -> {
                    when (authBackStack.lastOrNull()) {
                        is AuthNavKey.Login -> buildBrowserHistoryFragment("login")
                        is AuthNavKey.Register -> buildBrowserHistoryFragment("register")
                        else -> null
                    }
                }

                is AppNavKey.Main -> {
                    when (val mainNavKey = mainBackStack.lastOrNull()) {
                        is MainNavKey.Home -> buildBrowserHistoryFragment("home")
                        is MainNavKey.BookDetail -> buildBrowserHistoryFragment(
                            "book-detail",
                            mapOf("id" to mainNavKey.id.toString())
                        )

                        is MainNavKey.Borrowing -> buildBrowserHistoryFragment("borrowing")
                        is MainNavKey.History -> buildBrowserHistoryFragment("history")
                        is MainNavKey.Profile -> buildBrowserHistoryFragment("profile")
                        else -> null
                    }
                }

                else -> null
            }
        }
        App(
            backButtonEnabled = false,
            appBackStack = appBackStack,
            mainBackStack = mainBackStack
        )
    }
}