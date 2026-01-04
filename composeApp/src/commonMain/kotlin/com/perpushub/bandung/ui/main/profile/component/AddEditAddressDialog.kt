package com.perpushub.bandung.ui.main.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextField

@Composable
fun AddEditAddressDialog(
    visible: Boolean,
    onSaveClick: (AddressInput) -> Unit,
    onDismissRequest: () -> Unit,
    initialInput: AddressInput? = null
) {
    var input by rememberSaveable(initialInput, visible) { mutableStateOf(initialInput ?: AddressInput()) }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    val width = if (isAtLeastMediumBreakpoint) {
        540.dp
    } else {
        360.dp
    }

    BoxWithConstraints {
        val maxDialogWidth = maxWidth - 16.dp

        ContentDialog(
            title = if (initialInput == null) "Tambah alamat" else "Ubah alamat",
            visible = visible,
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    TextField(
                        value = input.label,
                        onValueChange = {
                            input = input.copy(label = it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        header = {
                            Text("Label")
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        TextField(
                            value = input.recipientName,
                            onValueChange = {
                                input = input.copy(recipientName = it)
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            header = {
                                Text("Nama penerima")
                            }
                        )
                        TextField(
                            value = input.phoneNumber,
                            onValueChange = {
                                input = input.copy(phoneNumber = it)
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            header = {
                                Text("Nomor telepon")
                            }
                        )
                    }
                    TextField(
                        value = input.addressLine,
                        onValueChange = {
                            input = input.copy(addressLine = it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        header = {
                            Text("Alamat")
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        TextField(
                            value = input.city,
                            onValueChange = {
                                input = input.copy(city = it)
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            header = {
                                Text("Kota")
                            }
                        )
                        TextField(
                            value = input.province,
                            onValueChange = {
                                input = input.copy(province = it)
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            header = {
                                Text("Provinsi")
                            }
                        )
                    }
                    TextField(
                        value = input.postalCode,
                        onValueChange = {
                            input = input.copy(postalCode = it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        header = {
                            Text("Kode pos")
                        }
                    )
                }
            },
            primaryButtonText = "Simpan",
            closeButtonText = "Batal",
            onButtonClick = {
                when (it) {
                    ContentDialogButton.Primary -> {
                        onSaveClick(
                            AddressInput(
                                label = input.label,
                                recipientName = input.recipientName,
                                phoneNumber = input.phoneNumber,
                                addressLine = input.addressLine,
                                city = input.city,
                                province = input.province,
                                postalCode = input.postalCode
                            )
                        )
                        onDismissRequest()
                    }

                    ContentDialogButton.Close -> onDismissRequest()
                    else -> {}
                }
            },
            size = if (maxDialogWidth > width) {
                DialogSize(width, width)
            } else {
                DialogSize(maxDialogWidth, maxDialogWidth)
            }
        )
    }
}

@Composable
@Preview
private fun AddEditAddressDialogPreview() {
    AppTheme {
        AddEditAddressDialog(
            visible = true,
            onSaveClick = {},
            onDismissRequest = {}
        )
    }
}