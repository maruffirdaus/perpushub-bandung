package com.perpushub.bandung.ui.borrowing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.ui.common.util.GeoUtil
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.FluentDialog
import io.github.composefluent.component.Text
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.scrollTo
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun SelectLibraryDialog(
    bookCopies: List<BookCopy>,
    libraries: List<LibraryDetail>,
    visible: Boolean,
    loading: Boolean,
    onDismissRequest: () -> Unit,
    mapState: MapState? = null
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastExpandedBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    val width = if (isAtLeastExpandedBreakpoint) {
        720.dp
    } else {
        360.dp
    }

    val scope = rememberCoroutineScope()

    scope.launch {
        mapState?.scrollTo(
            GeoUtil.lonToRelativeX(libraries.getOrNull(0)?.longitude ?: 0.0),
            GeoUtil.latToRelativeY(libraries.getOrNull(0)?.latitude ?: 0.0),
        )
    }

    FluentDialog(
        visible = visible,
        size = DialogSize(
            min = width,
            max = width
        )
    ) {
        if (isAtLeastExpandedBreakpoint) {
            Row(
                modifier = Modifier.aspectRatio(3f / 2f)
            ) {
                ListSection(
                    onDismissRequest = onDismissRequest,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight()
                ) {
                    if (mapState != null) {
                        MapUI(state = mapState)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.aspectRatio(2f / 3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                ) {
                    if (mapState != null) {
                        MapUI(state = mapState)
                    }
                }
                ListSection(
                    onDismissRequest = onDismissRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ListSection(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(FluentTheme.colors.background.layer.alt)
                .padding(24.dp)
        ) {
            Text(
                text = "Pilih perpustakaan",
                style = FluentTheme.typography.subtitle
            )
            Spacer(Modifier.height(12.dp))
        }
        Box(
            modifier = Modifier
                .height(1.dp)
                .background(FluentTheme.colors.stroke.surface.default)
        )
        Box(
            modifier = Modifier
                .height(80.dp)
                .padding(horizontal = 25.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AccentButton(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                ) {
                    Text("Pilih")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onDismissRequest
                ) {
                    Text("Batal")
                }
            }
        }
    }
}

@Composable
@Preview
private fun SelectLibraryDialogPreview() {
    AppTheme {
        SelectLibraryDialog(
            bookCopies = BookCopy.dummies[0]!!,
            libraries = LibraryDetail.dummies,
            visible = true,
            loading = false,
            onDismissRequest = {}
        )
    }
}