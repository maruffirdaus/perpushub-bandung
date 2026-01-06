package com.perpushub.bandung.ui.main.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.common.model.AddressInput
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.FluentDialog
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextField

@Composable
fun AddEditAddressDialog(
    visible: Boolean,
    onSaveClick: (AddressInput) -> Unit,
    onDismissRequest: () -> Unit,
    initialInput: AddressInput? = null
) {
    var input by rememberSaveable(initialInput, visible) {
        mutableStateOf(
            initialInput ?: AddressInput()
        )
    }

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

        FluentDialog(
            visible = visible,
            size = if (maxDialogWidth > width) {
                DialogSize(width, width)
            } else {
                DialogSize(maxDialogWidth, maxDialogWidth)
            }
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(FluentTheme.colors.background.layer.alt)
                        .padding(24.dp)
                ) {
                    Text(
                        text = if (initialInput == null) "Tambah alamat" else "Ubah alamat",
                        style = FluentTheme.typography.subtitle
                    )
                    Spacer(Modifier.height(12.dp))
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
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
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
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
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
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
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
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
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
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
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
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
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
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (input.label.isNotBlank() &&
                                        input.recipientName.isNotBlank() &&
                                        input.phoneNumber.isNotBlank() &&
                                        input.addressLine.isNotBlank() &&
                                        input.city.isNotBlank() &&
                                        input.province.isNotBlank() &&
                                        input.postalCode.isNotBlank()
                                    ) {
                                        onSaveClick(input)
                                        onDismissRequest()
                                    }
                                }
                            )
                        )
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
                                onSaveClick(input)
                                onDismissRequest()
                            },
                            disabled = input.label.isBlank() ||
                                    input.recipientName.isBlank() ||
                                    input.phoneNumber.isBlank() ||
                                    input.addressLine.isBlank() ||
                                    input.city.isBlank() ||
                                    input.province.isBlank() ||
                                    input.postalCode.isBlank(),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Simpan")
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