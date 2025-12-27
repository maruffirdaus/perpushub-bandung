package com.perpushub.bandung.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.composefluent.FluentTheme
import io.github.composefluent.background.Mica
import io.github.composefluent.lightColors

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    FluentTheme(lightColors()) {
        Mica(Modifier.fillMaxSize()) {
            content()
        }
    }
}