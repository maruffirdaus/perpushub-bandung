package com.perpushub.bandung.ui.borrowing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
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
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.CheckmarkCircle
import io.github.composefluent.icons.regular.Warning
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
    onSelectClick: (LibraryDetail) -> Unit,
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
                    bookCopies = bookCopies,
                    libraries = libraries,
                    loading = loading,
                    onSelectClick = onSelectClick,
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
                    bookCopies = bookCopies,
                    libraries = libraries,
                    loading = loading,
                    onSelectClick = onSelectClick,
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
    bookCopies: List<BookCopy>,
    libraries: List<LibraryDetail>,
    loading: Boolean,
    onSelectClick: (LibraryDetail) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLibrary: LibraryDetail? by remember { mutableStateOf(null) }

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(FluentTheme.colors.background.layer.alt)
                .padding(top = 24.dp)
        ) {
            val lazyListState = rememberLazyListState()

            Text(
                text = "Pilih perpustakaan",
                modifier = Modifier.padding(horizontal = 24.dp),
                style = FluentTheme.typography.subtitle
            )
            Spacer(Modifier.height(12.dp))
            if (loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ProgressRing()
                }
            } else {
                ScrollbarContainer(
                    adapter = rememberScrollbarAdapter(lazyListState),
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = lazyListState,
                        contentPadding = PaddingValues(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 24.dp
                        )
                    ) {
                        items(libraries) { library ->
                            val availableCopies = bookCopies.count { it.library.id == library.id }

                            LibraryItem(
                                library = library,
                                availableCopies = availableCopies,
                                selected = library.id == selectedLibrary?.id,
                                onClick = {
                                    if (availableCopies > 0) {
                                        selectedLibrary = library
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
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
                    onClick = {
                        selectedLibrary?.let {
                            onSelectClick(it)
                            onDismissRequest()
                        }
                    },
                    disabled = selectedLibrary == null,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pilih")
                }
                Button(
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal")
                }
            }
        }
    }
}

@Composable
private fun LibraryItem(
    library: LibraryDetail,
    availableCopies: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(FluentTheme.shapes.control)
            .background(
                if (selected) {
                    FluentTheme.colors.subtleFill.secondary
                } else {
                    FluentTheme.colors.subtleFill.transparent
                }
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = library.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = library.address,
            color = FluentTheme.colors.text.text.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            style = FluentTheme.typography.caption
        )
        Spacer(Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isAvailable = availableCopies > 0
            val color = if (isAvailable) {
                FluentTheme.colors.system.success
            } else {
                FluentTheme.colors.system.critical
            }


            Icon(
                imageVector = if (isAvailable) Icons.Regular.CheckmarkCircle else Icons.Regular.Warning,
                contentDescription = null,
                tint = color
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = if (isAvailable) {
                    "Tersedia ($availableCopies)"
                } else {
                    "Tidak tersedia"
                },
                modifier = Modifier.weight(1f),
                color = color,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
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
            onSelectClick = {},
            onDismissRequest = {}
        )
    }
}