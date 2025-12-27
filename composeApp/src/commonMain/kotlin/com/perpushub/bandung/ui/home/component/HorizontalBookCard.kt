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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kotlinx.datetime.LocalDate

@Composable
fun HorizontalBookCard(
    book: Book,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    alternate: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .let {
                if (alternate) {
                    it.background(
                        color = FluentTheme.colors.background.card.default,
                        shape = RoundedCornerShape(4.dp)
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
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(book.title)
            Text(
                text = buildString {
                    append(
                        if (book.authors.isEmpty()) {
                            "Unknown"
                        } else {
                            book.authors.joinToString(", ") { it.name }
                        }
                    )
                    append(" (")
                    append(LocalDate.parse(book.publishedDate).year.toString())
                    append(")")
                },
                color = FluentTheme.colors.text.text.secondary,
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
private fun HorizontalBookCardPreview() {
    AppTheme {
        HorizontalBookCard(
            book = Book.dummies[0]
        )
    }
}