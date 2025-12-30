package com.perpushub.bandung.ui.common.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.composefluent.component.Expander
import io.github.composefluent.component.Text

@Composable
fun ExpanderItem(
    title: String,
    modifier: Modifier = Modifier,
    defaultExpanded: Boolean = false,
    icon: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    var isExpanded by remember { mutableStateOf(defaultExpanded) }

    Expander(
        expanded = isExpanded,
        onExpandedChanged = { isExpanded = it },
        heading = {
            Text(title)
        },
        modifier = modifier,
        icon = icon,
        expandContent = content
    )
}