package com.perpushub.bandung.ui.main.borrowing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perpushub.bandung.common.model.Address
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
import io.github.composefluent.icons.regular.Add

@Composable
fun AddressPickerDialog(
    addresses: List<Address>,
    visible: Boolean,
    onSelectClick: (Address) -> Unit,
    onAddAddressClick: () -> Unit,
    onDismissRequest: () -> Unit,
    loading: Boolean = false
) {
    var selectedAddress: Address? by remember(visible) { mutableStateOf(null) }

    BoxWithConstraints {
        val width = 360.dp
        val maxDialogWidth = maxWidth - 16.dp

        FluentDialog(
            visible = visible,
            size = if (maxDialogWidth > width) {
                DialogSize(width, width)
            } else {
                DialogSize(maxDialogWidth, maxDialogWidth)
            }
        ) {
            Column(
                modifier = Modifier.aspectRatio(2f / 3f)
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
                        text = "Pilih alamat",
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
                                items(addresses) { address ->
                                    AddressItem(
                                        address = address,
                                        selected = address.id == selectedAddress?.id,
                                        onClick = {
                                            selectedAddress = address
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                item {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(FluentTheme.shapes.control)
                                            .clickable {
                                                onDismissRequest()
                                                onAddAddressClick()
                                            }
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Regular.Add,
                                            contentDescription = "Tambah alamat",
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        Text("Tambah alamat")
                                    }
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
                                selectedAddress?.let {
                                    onSelectClick(it)
                                    onDismissRequest()
                                }
                            },
                            disabled = selectedAddress == null,
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
    }
}

@Composable
private fun AddressItem(
    address: Address,
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
            text = address.label,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = address.recipientName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.caption
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = address.addressLine,
            color = FluentTheme.colors.text.text.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.caption
        )
        Text(
            text = "${address.city}, ${address.province}",
            color = FluentTheme.colors.text.text.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.caption
        )
        Text(
            text = address.postalCode,
            color = FluentTheme.colors.text.text.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.caption
        )
    }
}

@Composable
@Preview
private fun AddressPickerDialogPreview() {
    AppTheme {
        AddressPickerDialog(
            addresses = listOf(),
            visible = true,
            onSelectClick = {},
            onAddAddressClick = {},
            onDismissRequest = {}
        )
    }
}