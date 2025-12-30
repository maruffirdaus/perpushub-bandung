package com.perpushub.bandung.ui.borrowing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import coil3.compose.AsyncImage
import com.perpushub.bandung.common.model.Book
import com.perpushub.bandung.common.model.LibraryDetail
import com.perpushub.bandung.common.model.LoanRequest
import com.perpushub.bandung.ui.common.component.ExpanderItem
import com.perpushub.bandung.ui.common.component.HeaderItemRow
import com.perpushub.bandung.ui.common.component.ItemRow
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.ExperimentalFluentApi
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.CalendarDatePicker
import io.github.composefluent.component.Text

@OptIn(ExperimentalFluentApi::class)
@Composable
fun LoanRequestCard(
    loanRequest: LoanRequest,
    selectedLibrary: String?,
    onCardClick: () -> Unit,
    onSelectLibraryClick: () -> Unit,
    onContinueClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    defaultExpanded: Boolean = false,
    alternate: Boolean = false
) {
    var selectedDueDate: String? by rememberSaveable { mutableStateOf(null) }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .let {
                if (alternate) {
                    it.background(
                        color = FluentTheme.colors.background.card.default,
                        shape = RoundedCornerShape(4.dp)
                    )
                } else {
                    it
                }
            }
            .clickable(onClick = onCardClick)
            .padding(16.dp)
    ) {
        Row {
            AsyncImage(
                model = loanRequest.book.coverUrl,
                contentDescription = loanRequest.book.title,
                modifier = Modifier
                    .width(80.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = loanRequest.book.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = if (loanRequest.book.authors.isEmpty()) {
                        "Unknown"
                    } else {
                        loanRequest.book.authors.joinToString(", ") { it.name }
                    },
                    color = FluentTheme.colors.text.text.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = FluentTheme.typography.caption
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = loanRequest.book.description,
                    color = FluentTheme.colors.text.text.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = FluentTheme.typography.caption
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        ExpanderItem(
            title = "Rincian peminjaman",
            defaultExpanded = defaultExpanded
        ) {
            HeaderItemRow(listOf("Perpustakaan"))
            ItemRow(
                items = listOf(
                    {
                        Button(
                            onClick = onSelectLibraryClick
                        ) {
                            Text(selectedLibrary ?: "Pilih perpustakaan")
                        }
                    }
                )
            )
            HeaderItemRow(listOf("Alamat"))
            ItemRow(
                items = listOf(
                    {
                        Button(
                            onClick = {}
                        ) {
                            Text("Pilih alamat")
                        }
                    }
                )
            )
            HeaderItemRow(listOf("Tanggal pengembalian"))
            ItemRow(
                items = listOf(
                    {
                        CalendarDatePicker(
                            onChoose = {
                                selectedDueDate = "${it.year}-${it.monthValue}-${it.day}"
                            }
                        )
                    }
                ),
                separatorVisible = false
            )
        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            AccentButton(
                onClick = {
                    selectedDueDate?.let { dueDate ->
                        onContinueClick(dueDate)
                    }
                },
                modifier = Modifier.let {
                    if (isAtLeastMediumBreakpoint) {
                        it
                    } else {
                        it.weight(1f)
                    }
                },
                disabled = selectedLibrary == null || selectedDueDate == null
            ) {
                Text("Lanjutkan")
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = onDeleteClick,
                modifier = Modifier.let {
                    if (isAtLeastMediumBreakpoint) {
                        it
                    } else {
                        it.weight(1f)
                    }
                }
            ) {
                Text("Hapus")
            }
        }
    }
}

@Composable
@Preview
private fun LoanRequestCardPreview() {
    AppTheme {
        LoanRequestCard(
            loanRequest = LoanRequest(
                id = 0,
                userId = 0,
                book = Book.dummies[0],
                requestAt = "2025-05-25"
            ),
            selectedLibrary = LibraryDetail.dummies[0].name,
            onCardClick = {},
            onSelectLibraryClick = {},
            onContinueClick = {},
            onDeleteClick = {}
        )
    }
}