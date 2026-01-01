package com.perpushub.bandung.ui.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ProgressRingSize
import io.github.composefluent.component.SubtleButton
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextBoxButton
import io.github.composefluent.component.TextField
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Eye
import io.github.composefluent.icons.regular.EyeOff
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    onNavigate: (NavKey) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onNavigate = onNavigate,
        onNavigateBack = onNavigateBack
    )
}

@Composable
fun RegisterScreenContent(
    uiState: RegisterUiState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigate: (NavKey) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .wrapContentWidth()
            .widthIn(max = 360.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Daftar",
            modifier = Modifier.fillMaxWidth(),
            style = FluentTheme.typography.title
        )
        Spacer(Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = uiState.username,
                onValueChange = {
                    onEvent(RegisterEvent.OnUsernameChange(it))
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                header = {
                    Text("Username")
                },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.fullName,
                onValueChange = {
                    onEvent(RegisterEvent.OnFullNameChange(it))
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                header = {
                    Text("Nama lengkap")
                },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.email,
                onValueChange = {
                    onEvent(RegisterEvent.OnEmailChange(it))
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                header = {
                    Text("Email")
                },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = uiState.password,
                onValueChange = {
                    onEvent(RegisterEvent.OnPasswordChange(it))
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                visualTransformation = if (uiState.isPasswordVisible) {
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
                            onEvent(RegisterEvent.OnPasswordVisibilityToggle)
                        }
                    ) {
                        Icon(
                            imageVector = if (uiState.isPasswordVisible) {
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
            TextField(
                value = uiState.confirmPassword,
                onValueChange = {
                    onEvent(RegisterEvent.OnConfirmPasswordChange(it))
                },
                enabled = !uiState.isLoading,
                singleLine = true,
                visualTransformation = if (uiState.isConfirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                header = {
                    Text("Konfirmasi password")
                },
                trailing = {
                    TextBoxButton(
                        onClick = {
                            onEvent(RegisterEvent.OnConfirmPasswordVisibilityToggle)
                        }
                    ) {
                        Icon(
                            imageVector = if (uiState.isConfirmPasswordVisible) {
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
        Spacer(Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AccentButton(
                onClick = {
                    onEvent(RegisterEvent.OnRegister)
                },
                modifier = Modifier.weight(1f),
                disabled = uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    ProgressRing(
                        size = ProgressRingSize.Small
                    )
                }
                Text("Daftar")
            }
            Button(
                onClick = {
                    onNavigateBack()
                },
                modifier = Modifier.weight(1f),
                disabled = uiState.isLoading
            ) {
                Text("Batal")
            }
        }
        Spacer(Modifier.height(24.dp))
        SubtleButton(
            onClick = {
                onNavigate(AppNavKey.Main)
            },
            disabled = uiState.isLoading
        ) {
            Text("Lanjutkan tanpa akun")
        }
    }
}

@Composable
@Preview
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreenContent(
            uiState = RegisterUiState(),
            onEvent = {},
            onNavigate = {},
            onNavigateBack = {}
        )
    }
}