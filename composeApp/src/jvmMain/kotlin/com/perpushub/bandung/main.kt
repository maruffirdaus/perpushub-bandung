package com.perpushub.bandung

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowStyle
import com.perpushub.bandung.ui.theme.GlobalThemeState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PerpusHub Bandung",
    ) {
        WindowStyle(
            isDarkTheme = GlobalThemeState.isDarkTheme,
            backdropType = WindowBackdrop.Mica
        )
        App()
    }
}