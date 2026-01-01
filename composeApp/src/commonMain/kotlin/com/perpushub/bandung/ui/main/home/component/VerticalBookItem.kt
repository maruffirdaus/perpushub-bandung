package com.perpushub.bandung.ui.main.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun VerticalBookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    width: Dp = 160.dp
) {
    Column(
        modifier = modifier
            .width(width)
            .clip(FluentTheme.shapes.control)
            .let {
                if (onClick != null) {
                    it.clickable(onClick = onClick)
                } else {
                    it
                }
            }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = book.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(FluentTheme.shapes.control),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = book.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = if (book.authors.isEmpty()) {
                "Unknown"
            } else {
                book.authors.joinToString(", ") { it.name }
            },
            color = FluentTheme.colors.text.text.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.caption
        )
    }
}

@Composable
@Preview
private fun VerticalBookItemPreview() {
    AppTheme {
        VerticalBookItem(
            book = Book.dummies[0]
        )
    }
}