package com.perpushub.bandung.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.composefluent.FluentTheme
import io.github.composefluent.ProvideTextStyle
import io.github.composefluent.component.Text

@Composable
fun Header(
    text: String,
    actionsOnly: Boolean = false,
    actions: @Composable (RowScope.() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Header(
        text = {
            Text(
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        modifier = modifier,
        actionsOnly = actionsOnly,
        actions = actions
    )
}

@Composable
fun Header(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    actionsOnly: Boolean = false,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 32.dp, end = 32.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!actionsOnly) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                ProvideTextStyle(
                    FluentTheme.typography.title
                ) {
                    text()
                }
            }
        }
        if (actions != null) {
            if (!actionsOnly) {
                Spacer(Modifier.width(24.dp))
            }
            actions()
        }
    }
}