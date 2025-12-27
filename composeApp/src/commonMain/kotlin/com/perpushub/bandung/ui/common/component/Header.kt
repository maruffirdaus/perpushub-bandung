package com.perpushub.bandung.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun Header(
    text: String,
    actionsOnly: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 32.dp, end = 32.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!actionsOnly) {
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = FluentTheme.typography.title
            )
        }
        actions()
    }
}