package com.perpushub.bandung.ui.main.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ProgressRingSize

@Composable
fun BookCover(
    coverUrl: String,
    contentDescription: String?,
    width: Dp,
    modifier: Modifier = Modifier,
    progressRingSize: Dp = ProgressRingSize.Small
) {
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = coverUrl,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(width)
                .aspectRatio(2f / 3f)
                .clip(FluentTheme.shapes.control),
            onLoading = {
                isLoading = true
            },
            onSuccess = {
                isLoading = false
            },
            contentScale = ContentScale.Crop
        )
        if (isLoading) {
            ProgressRing(
                size = progressRingSize
            )
        }
    }
}

@Composable
@Preview
private fun BookCoverPreview() {
    AppTheme {
        BookCover(
            coverUrl = "",
            contentDescription = "",
            width = 240.dp
        )
    }
}