package com.perpushub.bandung.ui.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import com.perpushub.bandung.common.model.BookCopy
import com.perpushub.bandung.common.model.BookCopyStatus
import com.perpushub.bandung.common.model.BookDetail
import com.perpushub.bandung.ui.common.component.ExpanderItem
import com.perpushub.bandung.ui.common.component.Header
import com.perpushub.bandung.ui.common.component.HeaderItemRow
import com.perpushub.bandung.ui.common.component.ItemRow
import com.perpushub.bandung.ui.common.util.DateUtil
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.BookAdd
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookDetailScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: BookDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BookDetailScreenContent(
        uiState = uiState,
        onBorrow = viewModel::borrow,
        onErrorMessageClear = viewModel::clearErrorMessage,
        onNavigate = onNavigate
    )
}

@Composable
fun BookDetailScreenContent(
    uiState: BookDetailUiState,
    onBorrow: (() -> Unit) -> Unit,
    onErrorMessageClear: () -> Unit,
    onNavigate: (NavKey) -> Unit
) {
    ContentDialog(
        title = "Terjadi kesalahan",
        visible = uiState.errorMessage != null,
        content = {
            Text("${uiState.errorMessage}")
        },
        primaryButtonText = "Tutup",
        onButtonClick = {
            when (it) {
                ContentDialogButton.Primary -> onErrorMessageClear()
                else -> {}
            }
        },
        size = DialogSize.Min
    )

    if (uiState.isLoading || uiState.book == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressRing()
        }
    } else {
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
                                maxLines = 2
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
                        AccentButton(
                            onClick = {
                                onBorrow {
                                    onNavigate(MainNavKey.Borrowing)
                                }
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
                            BookDetailSection(
                                book = uiState.book,
                                bookCopies = uiState.bookCopies
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
                            BookDetailSection(
                                book = uiState.book,
                                bookCopies = uiState.bookCopies
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = book.title,
            modifier = Modifier
                .width(240.dp)
                .aspectRatio(2f / 3f)
                .clip(FluentTheme.shapes.control),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun BookDetailSection(
    book: BookDetail,
    bookCopies: List<BookCopy>
) {
    Column {
        BookAboutSection(
            book = book
        )
        Spacer(Modifier.height(4.dp))
        BookCopiesSection(
            bookCopies = bookCopies
        )
    }
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
                    Text(DateUtil.formatDate(book.publishedDate))
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
private fun BookCopiesSection(
    bookCopies: List<BookCopy>
) {
    ExpanderItem(
        title = "Ketersediaan di perpustakaan",
        defaultExpanded = true
    ) {
        val distinctCopies = bookCopies
            .filter { it.status == BookCopyStatus.AVAILABLE }
            .distinctBy { it.library.id }
            .sortedBy { it.library.name }

        if (distinctCopies.isEmpty()) {
            ItemRow(
                items = listOf(
                    {
                        Text("Salinan tidak tersedia")
                    }
                ),
                separatorVisible = false
            )
        } else {
            HeaderItemRow(listOf("Perpustakaan", "Jumlah"))
            distinctCopies.forEachIndexed { index, copy ->
                ItemRow(
                    items = listOf(
                        {
                            Text(copy.library.name)
                        },
                        {
                            Text(
                                text = bookCopies.count {
                                    it.library.id == copy.library.id
                                }.toString()
                            )
                        }
                    ),
                    separatorVisible = index != distinctCopies.lastIndex
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 1280, heightDp = 720)
private fun BookDetailScreenPreview() {
    AppTheme {
        BookDetailScreenContent(
            uiState = BookDetailUiState(),
            onBorrow = {},
            onErrorMessageClear = {},
            onNavigate = {}
        )
    }
}