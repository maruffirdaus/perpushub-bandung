package com.perpushub.bandung.ui.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.visible
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.component.ContentDialog
import io.github.composefluent.component.ContentDialogButton
import io.github.composefluent.component.DialogSize
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextBoxButton
import io.github.composefluent.component.TextField
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Eye
import io.github.composefluent.icons.regular.EyeOff

@Composable
fun LoginDialog(
    visible: Boolean,
    loading: Boolean,
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    ContentDialog(
        title = "Masuk",
        visible = visible,
        content = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (loading) {
                    ProgressRing()
                }
                Column(
                    modifier = Modifier.visible(!loading)
                ) {
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        singleLine = true,
                        header = {
                            Text("Email")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        singleLine = true,
                        visualTransformation = if (isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        header = {
                            Text("Password")
                        },
                        trailing = {
                            TextBoxButton(
                                onClick = {
                                    isPasswordVisible = !isPasswordVisible
                                }
                            ) {
                                Icon(
                                    imageVector = if (isPasswordVisible) {
                                        Icons.Regular.EyeOff
                                    } else {
                                        Icons.Regular.Eye
                                    },
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        primaryButtonText = "Masuk",
        secondaryButtonText = "Daftar",
        closeButtonText = "Batal",
        onButtonClick = {
            when (it) {
                ContentDialogButton.Primary -> onLoginClick(email, password)
                ContentDialogButton.Secondary -> onRegisterClick()
                ContentDialogButton.Close -> onDismissRequest()
            }

            email = ""
            password = ""
        },
        size = DialogSize.Min
    )
}

@Composable
@Preview
private fun LoginDialogPreview() {
    AppTheme {
        LoginDialog(
            visible = true,
            loading = false,
            onLoginClick = { _, _ -> },
            onRegisterClick = {},
            onDismissRequest = {}
        )
    }
}