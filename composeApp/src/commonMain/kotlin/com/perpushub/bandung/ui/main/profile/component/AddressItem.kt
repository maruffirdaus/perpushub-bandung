package com.perpushub.bandung.ui.main.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.common.model.Address
import com.perpushub.bandung.ui.main.common.component.ExpanderItem
import com.perpushub.bandung.ui.main.common.component.ItemRow
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.Text

@Composable
fun AddressItem(
    address: Address,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    defaultExpanded: Boolean = false,
    alternate: Boolean = false
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    Column(
        modifier = Modifier
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
            .padding(16.dp)
    ) {
        ExpanderItem(
            title = address.label,
            defaultExpanded = defaultExpanded
        ) {
            ItemRow(
                items = listOf(
                    {
                        Text("Nama penerima")
                    },
                    {
                        Text(address.recipientName)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Nomor telepon")
                    },
                    {
                        Text(address.phoneNumber)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Alamat")
                    },
                    {
                        Text(address.addressLine)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Kota")
                    },
                    {
                        Text(address.city)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Provinsi")
                    },
                    {
                        Text(address.province)
                    }
                )
            )
            ItemRow(
                items = listOf(
                    {
                        Text("Kode pos")
                    },
                    {
                        Text(address.postalCode)
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
                onClick = onEditClick,
                modifier = Modifier.let {
                    if (isAtLeastMediumBreakpoint) {
                        it
                    } else {
                        it.weight(1f)
                    }
                },
            ) {
                Text("Ubah")
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
private fun AddressItemPreview() {
    AppTheme {
        AddressItem(
            address = Address.dummies[0]!![0],
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}