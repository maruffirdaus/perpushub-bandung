package com.perpushub.bandung

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowFrameStyle
import com.mayakapps.compose.windowstyler.WindowStyle
import io.github.composefluent.FluentTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PerpusHub Bandung",
    ) {
        val windowColor = FluentTheme.colors.background.mica.base

        WindowStyle(
            isDarkTheme = false,
            backdropType = WindowBackdrop.Solid(windowColor),
            frameStyle = WindowFrameStyle(
                titleBarColor = windowColor
            )
        )
        App()
    }
}