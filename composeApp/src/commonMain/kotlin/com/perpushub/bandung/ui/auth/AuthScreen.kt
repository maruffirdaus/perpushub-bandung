package com.perpushub.bandung.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass
import com.perpushub.bandung.ui.common.component.Header
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.Text
import io.github.composefluent.component.TextBoxButton
import io.github.composefluent.component.TextField
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Eye
import io.github.composefluent.icons.regular.EyeOff
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import perpushubbandung.composeapp.generated.resources.Res
import perpushubbandung.composeapp.generated.resources.img_auth

@Composable
fun AuthScreen(
    onNavigate: (NavKey) -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            onNavigate(AppNavKey.Main)
        }
    }

    AuthScreenContent(
        uiState = uiState,
        onNavigate = onNavigate
    )
}

@Composable
fun AuthScreenContent(
    uiState: AuthUiState,
    onNavigate: (NavKey) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    if (isAtLeastMediumBreakpoint) {
        Row {
            ImageSection(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            AuthSection(
                login = uiState.isLogin,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    } else {
        Column {
            ImageSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            AuthSection(
                login = uiState.isLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun ImageSection(
    modifier: Modifier
) {
    Image(
        painter = painterResource(Res.drawable.img_auth),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun AuthSection(
    login: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(FluentTheme.colors.background.layer.alt),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val width = 480.dp
        val scrollState = rememberScrollState()

        Header(
            text = if (login) "Masuk" else "Daftar",
            modifier = Modifier.width(width)
        )
        ScrollbarContainer(
            adapter = rememberScrollbarAdapter(scrollState),
            modifier = Modifier.weight(1f)
        ) {
            val childModifier = Modifier
                .width(width)
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    bottom = 32.dp
                )

            if (login) {
                LoginSection(
                    modifier = childModifier
                )
            } else {
                RegisterSection(
                    modifier = childModifier
                )
            }
        }
    }
}

@Composable
private fun LoginSection(
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
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

@Composable
private fun RegisterSection(
    modifier: Modifier = Modifier
) {
    var username by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            singleLine = true,
            header = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = fullName,
            onValueChange = {
                fullName = it
            },
            singleLine = true,
            header = {
                Text("Nama lengkap")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
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

@Composable
@Preview
private fun AuthScreenPreview() {
    AppTheme {
        AuthScreenContent(
            uiState = AuthUiState(),
            onNavigate = {}
        )
    }
}