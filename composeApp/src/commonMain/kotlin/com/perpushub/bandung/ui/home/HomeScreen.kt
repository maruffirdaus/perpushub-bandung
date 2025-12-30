package com.perpushub.bandung.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.ui.common.component.Header
import com.perpushub.bandung.ui.home.component.HorizontalBookItem
import com.perpushub.bandung.ui.home.component.HorizontalList
import com.perpushub.bandung.ui.home.component.VerticalBookItem
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextBoxButton
import io.github.composefluent.component.TextField
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Search
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onSearchQueryChange = viewModel::changeSearchQuery,
        onBooksSearch = viewModel::searchBooks,
        onNavigate = onNavigate
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onSearchQueryChange: (String) -> Unit,
    onBooksSearch: () -> Unit,
    onNavigate: (NavKey) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    val isAtLeastExpandedBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val bookDiscoveryListState = rememberLazyListState()
        val bookSearchListState = rememberLazyListState()

        Header(
            text = "Beranda",
            actionsOnly = !isAtLeastMediumBreakpoint,
            actions = {
                TextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.let {
                        if (isAtLeastMediumBreakpoint) {
                            it.width(if (isAtLeastExpandedBreakpoint) 320.dp else 240.dp)
                        } else {
                            it.fillMaxWidth()
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onBooksSearch()
                            scope.launch {
                                bookSearchListState.animateScrollToItem(0)
                            }
                        }
                    ),
                    trailing = {
                        TextBoxButton(
                            onClick = {
                                onBooksSearch()
                                scope.launch {
                                    bookSearchListState.animateScrollToItem(0)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Regular.Search,
                                contentDescription = "Cari"
                            )
                        }
                    },
                    placeholder = {
                        Text("Cari")
                    }
                )
            }
        )
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ProgressRing()
            }
        } else if (uiState.searchedBooks != null) {
            BookSearchSection(
                searchQuery = uiState.searchQuery,
                searchedBooks = uiState.searchedBooks,
                lazyListState = bookSearchListState,
                onNavigate = onNavigate
            )
        } else {
            BookDiscoverySection(
                topBooks = uiState.topBooks,
                recommendedBooks = uiState.recommendedBooks,
                lazyListState = bookDiscoveryListState,
                onNavigate = onNavigate
            )
        }
    }
}

@Composable
private fun ColumnScope.BookSearchSection(
    searchQuery: String,
    searchedBooks: List<Book>,
    lazyListState: LazyListState,
    onNavigate: (NavKey) -> Unit
) {
    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                bottom = 32.dp
            )
        ) {
            if (searchedBooks.isEmpty()) {
                item {
                    Text("Buku dengan kata kunci \"${searchQuery}\" tidak ditemukan.")
                }
            } else {
                itemsIndexed(searchedBooks) { index, book ->
                    HorizontalBookItem(
                        book = book,
                        onClick = {
                            onNavigate(MainNavKey.BookDetail(book.id))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        alternate = index % 2 == 0
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.BookDiscoverySection(
    topBooks: List<Book>,
    recommendedBooks: List<Book>,
    lazyListState: LazyListState,
    onNavigate: (NavKey) -> Unit
) {
    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item {
                HorizontalList(
                    title = "Buku teratas",
                    items = topBooks
                ) { book ->
                    VerticalBookItem(
                        book = book,
                        onClick = {
                            onNavigate(MainNavKey.BookDetail(book.id))
                        }
                    )
                }
                Spacer(Modifier.height(32.dp))
            }
            item {
                Text(
                    text = "Rekomendasi buku",
                    style = FluentTheme.typography.bodyStrong,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Spacer(Modifier.height(16.dp))
            }
            itemsIndexed(recommendedBooks) { index, book ->
                HorizontalBookItem(
                    book = book,
                    onClick = {
                        onNavigate(MainNavKey.BookDetail(book.id))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    alternate = index % 2 == 0
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 1280, heightDp = 720)
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreenContent(
            uiState = HomeUiState(),
            onSearchQueryChange = {},
            onBooksSearch = {},
            onNavigate = {}
        )
    }
}