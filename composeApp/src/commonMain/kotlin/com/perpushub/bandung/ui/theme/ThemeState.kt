package com.perpushub.bandung.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThemeState(
    isDarkTheme: Boolean = false
) {
    var isDarkTheme by mutableStateOf(isDarkTheme)
        private set
}