package com.perpushub.bandung

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import com.perpushub.bandung.di.dataModule
import com.perpushub.bandung.di.networkModule
import com.perpushub.bandung.di.repositoryModule
import com.perpushub.bandung.di.uiModule
import com.perpushub.bandung.di.viewModelModule
import com.perpushub.bandung.ui.common.messaging.MessageHost
import com.perpushub.bandung.ui.navigation.AppNavDisplay
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.navigation.auth.AuthNavKey
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinConfiguration

@Composable
fun App(
    backButtonEnabled: Boolean = true,
    appBackStack: MutableList<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = AppNavKey.serializersModule
        },
        AppNavKey.Auth
    ),
    authBackStack: MutableList<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = AuthNavKey.serializersModule
        },
        AuthNavKey.Login
    ),
    mainBackStack: MutableList<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = MainNavKey.serializersModule
        },
        MainNavKey.Home
    )
) {
    KoinApplication(
        configuration = KoinConfiguration {
            modules(dataModule, networkModule, repositoryModule, uiModule, viewModelModule)
        }
    ) {
        AppTheme {
            MessageHost()
            AppNavDisplay(
                appBackStack = appBackStack,
                authBackStack = authBackStack,
                mainBackStack = mainBackStack,
                backButtonEnabled = backButtonEnabled
            )
        }
    }
}