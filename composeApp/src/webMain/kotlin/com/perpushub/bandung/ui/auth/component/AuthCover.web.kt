package com.perpushub.bandung.ui.auth.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import perpushubbandung.composeapp.generated.resources.Res

@Composable
actual fun AuthCover(
    modifier: Modifier
) {
    AsyncImage(
        model = Res.getUri("drawable/img_auth.png"),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}