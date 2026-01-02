package com.perpushub.bandung.ui.auth.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import perpushubbandung.composeapp.generated.resources.Res
import perpushubbandung.composeapp.generated.resources.img_auth

@Composable
actual fun AuthCover(
    modifier: Modifier
) {
    Image(
        painter = painterResource(Res.drawable.img_auth),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}