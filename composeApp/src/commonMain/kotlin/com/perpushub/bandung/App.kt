package com.perpushub.bandung

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import com.perpushub.bandung.di.dataModule
import com.perpushub.bandung.di.networkModule
import com.perpushub.bandung.di.repositoryModule
import com.perpushub.bandung.di.viewModelModule
import com.perpushub.bandung.ui.main.MainScreen
import com.perpushub.bandung.ui.navigation.AppNavDisplay
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinConfiguration

@Composable
fun App(
    backButtonEnabled: Boolean = true,
    backStack: MutableList<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = AppNavKey.serializersModule
        },
        AppNavKey.Home
    )
) {
    KoinApplication(
        configuration = KoinConfiguration {
            modules(networkModule, dataModule, repositoryModule, viewModelModule)
        }
    ) {
        AppTheme {
            MainScreen(
                navDisplay = {
                    AppNavDisplay(
                        backStack = backStack,
                        onNavigate = { key ->
                            backStack.add(key)
                        }
                    )
                },
                backStack = backStack,
                onNavigate = { key ->
                    backStack.add(key)
                },
                onBack = {
                    backStack.removeLastOrNull()
                },
                backButtonEnabled = backButtonEnabled
            )
        }
    }
}