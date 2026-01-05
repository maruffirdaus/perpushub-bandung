package com.perpushub.bandung.ui.main.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.perpushub.bandung.common.model.Loan
import com.perpushub.bandung.ui.main.common.component.BookCover
import com.perpushub.bandung.ui.main.common.component.ExpanderItem
import com.perpushub.bandung.ui.main.common.component.ItemRow
import com.perpushub.bandung.ui.main.common.util.DateUtil
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Text

@Composable
fun LoanItem(
    loan: Loan,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    alternate: Boolean = false
) {
    Column(
        modifier = modifier
            .clip(FluentTheme.shapes.control)
            .let {
                if (alternate) {
                    it.background(
                        color = FluentTheme.colors.background.card.default,
                        shape = FluentTheme.shapes.control
                    )
                } else {
                    it
                }
            }
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row {
            BookCover(
                coverUrl = loan.book.coverUrl,
                contentDescription = loan.book.title,
                width = 80.dp
            )
            Spacer(Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = loan.book.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Text(
                    text = if (loan.book.authors.isEmpty()) {
                        "Unknown"
                    } else {
                        loan.book.authors.joinToString(", ") { it.name }
                    },
                    color = FluentTheme.colors.text.text.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = FluentTheme.typography.caption
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = loan.book.description,
                    color = FluentTheme.colors.text.text.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = FluentTheme.typography.caption
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        ExpanderItem(
            title = "Rincian peminjaman"
        ) {
            ItemRow(
                items = listOf(
                    {
                        Text("Perpustakaan")
                    },
                    {
                        Text(loan.library.name)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Nama penerima")
                    },
                    {
                        Text(
                            text = loan.recipientName,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Nomor telepon")
                    },
                    {
                        Text(
                            text = loan.phoneNumber,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Alamat")
                    },
                    {
                        Text(
                            text = buildString {
                                append(loan.addressLine)
                                append(", ")
                                append(loan.city)
                                append(", ")
                                append(loan.province)
                                append(" ")
                                append(loan.postalCode)
                            },
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3
                        )
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Tanggal pengembalian")
                    },
                    {
                        Text(
                            text = DateUtil.formatDate(loan.dueDate),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                ),
                separatorVisible = false
            )
        }
    }
}