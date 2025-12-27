package com.perpushub.bandung

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PerpusHub Bandung",
    ) {
        App()
    }
}