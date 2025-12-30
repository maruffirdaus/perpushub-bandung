package com.perpushub.bandung.ui.common.component

import androidx.compose.runtime.Composable
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun HeaderItemRow(
    titles: List<String>
) {
    ItemRow(
        items = titles.map { titles ->
            {
                Text(
                    text = titles,
                    style = FluentTheme.typography.caption.copy(color = FluentTheme.colors.text.text.secondary)
                )
            }
        }
    )
}