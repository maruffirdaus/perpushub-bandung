package com.perpushub.bandung.ui.main.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.perpushub.bandung.common.model.BookDetail
import com.perpushub.bandung.ui.main.common.component.ActionDivider
import com.perpushub.bandung.ui.main.common.component.BookCover
import com.perpushub.bandung.ui.main.common.component.ExpanderItem
import com.perpushub.bandung.ui.main.common.component.Header
import com.perpushub.bandung.ui.main.common.component.ItemRow
import com.perpushub.bandung.ui.main.common.component.LibraryDialog
import com.perpushub.bandung.ui.main.common.util.DateUtil
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ProgressRingSize
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.BookAdd
import org.koin.compose.viewmodel.koinViewModel
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun BookDetailScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: BookDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookDetailScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigate = onNavigate,
        mapState = viewModel.mapState
    )
}

@Composable
fun BookDetailScreenContent(
    uiState: BookDetailUiState,
    onEvent: (BookDetailEvent) -> Unit,
    onNavigate: (NavKey) -> Unit,
    mapState: MapState? = null
) {
    if (uiState.isLoading || uiState.book == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressRing()
        }
    } else {
        LibraryDialog(
            title = "Ketersediaan buku",
            bookCopies = uiState.bookCopies,
            libraries = uiState.libraries,
            visible = uiState.isLibraryDialogOpen,
            onDismissRequest = {
                onEvent(BookDetailEvent.OnLibraryDialogClose)
            },
            mapState = mapState,
            loading = uiState.isLibraryDialogLoading
        )

        BoxWithConstraints {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val scrollState = rememberScrollState()

                Header(
                    text = {
                        Column {
                            Text(
                                text = uiState.book.title,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = if (uiState.book.authors.isEmpty()) {
                                    "Unknown"
                                } else {
                                    uiState.book.authors.joinToString(", ") { it.name }
                                },
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = FluentTheme.typography.body
                            )
                        }
                    },
                    actions = {
                        Button(
                            onClick = {
                                onEvent(BookDetailEvent.OnLibraryDialogOpen)
                            }
                        ) {
                            Text("Cek ketersediaan")
                        }
                        ActionDivider()
                        AccentButton(
                            onClick = {
                                onEvent(
                                    BookDetailEvent.OnBorrow {
                                        onNavigate(MainNavKey.Borrowing)
                                    }
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Regular.BookAdd,
                                contentDescription = "Pinjam"
                            )
                            Text("Pinjam")
                        }
                    }
                )
                ScrollbarContainer(
                    adapter = rememberScrollbarAdapter(scrollState),
                    modifier = Modifier.weight(1f)
                ) {
                    val modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(
                            start = 32.dp,
                            end = 32.dp,
                            bottom = 32.dp
                        )

                    if (this@BoxWithConstraints.maxWidth >= 800.dp) {
                        Row(
                            modifier = modifier
                        ) {
                            BookCoverSection(
                                book = uiState.book
                            )
                            Spacer(Modifier.width(16.dp))
                            BookAboutSection(
                                book = uiState.book
                            )
                        }
                    } else {
                        Column(
                            modifier = modifier
                        ) {
                            BookCoverSection(
                                book = uiState.book,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(16.dp))
                            BookAboutSection(
                                book = uiState.book
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BookCoverSection(
    book: BookDetail,
    modifier: Modifier = Modifier
) {
    BookCover(
        coverUrl = book.coverUrl,
        contentDescription = book.title,
        width = 240.dp,
        modifier = modifier,
        progressRingSize = ProgressRingSize.Medium
    )
}

@Composable
private fun BookAboutSection(
    book: BookDetail
) {
    ExpanderItem(
        title = "Tentang buku ini",
        defaultExpanded = true
    ) {
        ItemRow(
            items = listOf(
                {
                    Text("Penerbit")
                },
                {
                    Text(book.publisher)
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Tanggal publikasi")
                },
                {
                    Text(
                        DateUtil.formatDate(
                            book.publishedDate
                        )
                    )
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("ISBN 10")
                },
                {
                    Text(book.isbn10)
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("ISBN 13")
                },
                {
                    Text(book.isbn13)
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Jumlah halaman")
                },
                {
                    Text(book.pageCount.toString())
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Kategori")
                },
                {
                    Text(book.categories.joinToString(", ") { it.name })
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Bahasa")
                },
                {
                    Text(book.language.uppercase())
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text(book.description)
                }
            ),
            separatorVisible = false
        )
    }
}

@Composable
@Preview(widthDp = 1280, heightDp = 720)
private fun BookDetailScreenPreview() {
    AppTheme {
        BookDetailScreenContent(
            uiState = BookDetailUiState(),
            onEvent = {},
            onNavigate = {}
        )
    }
}