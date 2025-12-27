package com.perpushub.bandung.ui.home.component

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun HorizontalList(
    title: String,
    items: List<Book>,
    itemContent: @Composable LazyItemScope.(Book) -> Unit
) {
    Column {
        val lazyListState = rememberLazyListState()

        Text(
            text = title,
            style = FluentTheme.typography.bodyStrong,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(Modifier.height(16.dp))
        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(items = items, itemContent = itemContent)
        }
    }
}

@Composable
@Preview
private fun HorizontalListPreview() {
    AppTheme {
        HorizontalList(
            title = "Rekomendasi buku",
            items = emptyList(),
            itemContent = {}
        )
    }
}