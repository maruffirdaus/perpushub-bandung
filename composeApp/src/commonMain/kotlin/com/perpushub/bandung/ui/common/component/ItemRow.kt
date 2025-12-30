package com.perpushub.bandung.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.ExpanderItemSeparator

@Composable
fun ItemRow(
    items: List<@Composable () -> Unit>,
    separatorVisible: Boolean = true,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FluentTheme.colors.background.card.secondary)
                .padding(horizontal = 16.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                Box(Modifier.weight(1f)) {
                    item()
                }
            }
        }
        if (separatorVisible) {
            ExpanderItemSeparator()
        }
    }
}