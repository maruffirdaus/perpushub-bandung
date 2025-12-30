package com.perpushub.bandung.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun HorizontalBookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    alternate: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(FluentTheme.shapes.control)
            .let {
                if (alternate) {
                    it.background(
                        color = FluentTheme.colors.background.card.default,
                        shape = FluentTheme.shapes.control
                    )
                } else {
                    it
                }
            }
            .let {
                if (onClick != null) {
                    it.clickable(onClick = onClick)
                } else {
                    it
                }
            }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = book.title,
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(2f / 3f)
                .clip(FluentTheme.shapes.control),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = book.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Text(
                text = if (book.authors.isEmpty()) {
                    "Unknown"
                } else {
                    book.authors.joinToString(", ") { it.name }
                },
                color = FluentTheme.colors.text.text.secondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = FluentTheme.typography.caption
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = book.description,
                color = FluentTheme.colors.text.text.secondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5,
                style = FluentTheme.typography.caption
            )
        }
    }
}

@Composable
@Preview
private fun HorizontalBookItemPreview() {
    AppTheme {
        HorizontalBookItem(
            book = Book.dummies[0]
        )
    }
}