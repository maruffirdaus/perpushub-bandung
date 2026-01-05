package com.perpushub.bandung.ui.main.borrowing

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.common.model.Loan
import com.perpushub.bandung.common.model.LoanRequestDetail
import com.perpushub.bandung.ui.main.borrowing.component.AddressPickerDialog
import com.perpushub.bandung.ui.main.borrowing.component.BorrowedItem
import com.perpushub.bandung.ui.main.borrowing.component.CartItem
import com.perpushub.bandung.ui.main.borrowing.component.DeliveryItem
import com.perpushub.bandung.ui.main.borrowing.component.RequestsItem
import com.perpushub.bandung.ui.main.borrowing.model.BorrowTab
import com.perpushub.bandung.ui.main.common.component.LibraryDialog
import com.perpushub.bandung.ui.main.common.extension.alignHorizontalSpace
import com.perpushub.bandung.ui.navigation.main.MainNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.SelectorBarItem
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import org.koin.compose.viewmodel.koinViewModel
import ovh.plrapps.mapcompose.ui.state.MapState

@Composable
fun BorrowingScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: BorrowingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BorrowingScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigate = onNavigate,
        mapState = viewModel.mapState
    )
}

@Composable
fun BorrowingScreenContent(
    uiState: BorrowingUiState,
    onEvent: (BorrowingEvent) -> Unit,
    onNavigate: (NavKey) -> Unit,
    mapState: MapState? = null
) {
    LaunchedEffect(uiState.selectedTab) {
        when (uiState.selectedTab) {
            BorrowTab.CART -> onEvent(BorrowingEvent.OnCartsRefresh)
            BorrowTab.REQUESTS -> onEvent(BorrowingEvent.OnRequestsRefresh)
            BorrowTab.DELIVERY -> onEvent(BorrowingEvent.OnDeliveriesRefresh)
            BorrowTab.BORROWED -> onEvent(BorrowingEvent.OnLoansRefresh)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val lazyListState = rememberLazyListState()

        Spacer(Modifier.height(32.dp))
        Text(
            text = "Peminjaman",
            modifier = Modifier
                .alignHorizontalSpace()
                .padding(horizontal = 32.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.title
        )
        Spacer(Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.alignHorizontalSpace(),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState)
        ) {
            items(BorrowTab.entries) { tab ->
                SelectorBarItem(
                    selected = uiState.selectedTab == tab,
                    onSelectedChange = {
                        onEvent(BorrowingEvent.OnSelectedTabChange(tab))
                    },
                    text = {
                        Text(tab.label)
                    },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )
                    }
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ProgressRing()
            }
        } else {
            when (uiState.selectedTab) {
                BorrowTab.CART -> CartSection(
                    uiState = uiState,
                    onEvent = onEvent,
                    onNavigate = onNavigate,
                    mapState = mapState
                )

                BorrowTab.REQUESTS -> RequestsSection(
                    requests = uiState.requests,
                    onNavigate = onNavigate
                )

                BorrowTab.DELIVERY -> DeliverySection(
                    deliveries = uiState.deliveries,
                    onEvent = onEvent,
                    onNavigate = onNavigate
                )

                BorrowTab.BORROWED -> BorrowedSection(
                    loans = uiState.loans,
                    onNavigate = onNavigate
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.CartSection(
    uiState: BorrowingUiState,
    onEvent: (BorrowingEvent) -> Unit,
    onNavigate: (NavKey) -> Unit,
    mapState: MapState? = null
) {
    val lazyListState = rememberLazyListState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier
                .alignHorizontalSpace()
                .fillMaxHeight(),
            state = lazyListState,
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                bottom = 32.dp
            )
        ) {
            if (uiState.carts.isEmpty()) {
                item {
                    Text("Keranjang masih kosong. Jelajahi buku di halaman Beranda.")
                }
            } else {
                itemsIndexed(uiState.carts) { index, loanRequest ->
                    var selectedLibrary: LibraryDetail? by remember { mutableStateOf(null) }
                    var isLibraryDialogOpen by remember { mutableStateOf(false) }

                    var selectedAddress: Address? by remember { mutableStateOf(null) }
                    var isAddressDialogOpen by remember { mutableStateOf(false) }

                    LibraryDialog(
                        title = "Pilih perpustakaan",
                        bookCopies = uiState.bookCopies,
                        libraries = uiState.libraries,
                        visible = isLibraryDialogOpen,
                        onDismissRequest = {
                            isLibraryDialogOpen = false
                        },
                        mapState = mapState,
                        onSelectClick = {
                            selectedLibrary = it
                        },
                        loading = uiState.isLibraryDialogLoading
                    )

                    AddressPickerDialog(
                        addresses = uiState.addresses,
                        visible = isAddressDialogOpen,
                        onSelectClick = {
                            selectedAddress = it
                        },
                        onAddAddressClick = {
                            onNavigate(MainNavKey.Profile)
                        },
                        onDismissRequest = {
                            isAddressDialogOpen = false
                        },
                        loading = uiState.isAddressPickerDialogLoading
                    )

                    CartItem(
                        loanRequest = loanRequest,
                        selectedLibrary = selectedLibrary?.name,
                        selectedAddress = selectedAddress?.label,
                        onItemClick = {
                            onNavigate(MainNavKey.BookDetail(loanRequest.book.id))
                        },
                        onSelectLibraryClick = {
                            isLibraryDialogOpen = true
                            onEvent(BorrowingEvent.OnLibraryDialogRefresh(loanRequest.book.id))
                        },
                        onSelectAddressClick = {
                            isAddressDialogOpen = true
                            onEvent(BorrowingEvent.OnAddressPickerDialogRefresh)
                        },
                        onContinueClick = { dueDate ->
                            val library = selectedLibrary
                            val address = selectedAddress

                            if (library == null || address == null) return@CartItem

                            onEvent(
                                BorrowingEvent.OnSubmitLoanRequest(
                                    loanRequest.id,
                                    library.id,
                                    address.id,
                                    dueDate
                                )
                            )
                        },
                        onDeleteClick = {
                            onEvent(BorrowingEvent.OnCartDelete(loanRequest.id))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        defaultExpanded = index == 0,
                        alternate = index % 2 == 0
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.RequestsSection(
    requests: List<LoanRequestDetail>,
    onNavigate: (NavKey) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier
                .alignHorizontalSpace()
                .fillMaxHeight(),
            state = lazyListState,
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                bottom = 32.dp
            )
        ) {
            if (requests.isEmpty()) {
                item {
                    Text("Belum ada pengajuan buku.")
                }
            } else {
                itemsIndexed(requests) { index, loanRequestDetail ->
                    RequestsItem(
                        loanRequestDetail = loanRequestDetail,
                        onClick = {
                            onNavigate(MainNavKey.BookDetail(loanRequestDetail.book.id))
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
private fun ColumnScope.DeliverySection(
    deliveries: List<Loan>,
    onEvent: (BorrowingEvent) -> Unit,
    onNavigate: (NavKey) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier
                .alignHorizontalSpace()
                .fillMaxHeight(),
            state = lazyListState,
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                bottom = 32.dp
            )
        ) {
            if (deliveries.isEmpty()) {
                item {
                    Text("Belum ada buku yang dikirim.")
                }
            } else {
                itemsIndexed(deliveries) { index, loan ->
                    DeliveryItem(
                        loan = loan,
                        onItemClick = {
                            onNavigate(MainNavKey.BookDetail(loan.book.id))
                        },
                        onReceiveBookClick = {
                            onEvent(BorrowingEvent.OnBookReceive(loan.id))
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
private fun ColumnScope.BorrowedSection(
    loans: List<Loan>,
    onNavigate: (NavKey) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(lazyListState),
        modifier = Modifier.weight(1f)
    ) {
        LazyColumn(
            modifier = Modifier
                .alignHorizontalSpace()
                .fillMaxHeight(),
            state = lazyListState,
            contentPadding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                bottom = 32.dp
            )
        ) {
            if (loans.isEmpty()) {
                item {
                    Text("Belum ada buku yang dipinjam.")
                }
            } else {
                itemsIndexed(loans) { index, loan ->
                    BorrowedItem(
                        loan = loan,
                        onClick = {
                            onNavigate(MainNavKey.BookDetail(loan.book.id))
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
@Preview(widthDp = 1280, heightDp = 720)
private fun BorrowingScreenPreview() {
    AppTheme {
        BorrowingScreenContent(
            uiState = BorrowingUiState(),
            onEvent = {},
            onNavigate = {}
        )
    }
}