package com.perpushub.bandung.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.perpushub.bandung.ui.navigation.AppNavKey
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.AccentButton
import io.github.composefluent.component.Button
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
        onLogin = viewModel::login
    )
}

@Composable
fun AuthScreenContent(
    uiState: AuthUiState,
    onLogin: (String, String) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val isAtLeastMediumBreakpoint =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)

    if (isAtLeastMediumBreakpoint) {
        val scrollState = rememberScrollState()

        Row {
            ImageSection(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            ScrollbarContainer(
                adapter = rememberScrollbarAdapter(scrollState),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                AuthSection(
                    login = uiState.isLogin,
                    onLogin = onLogin,
                    scrollState = scrollState
                )
            }
        }
    } else {
        val scrollState = rememberScrollState()

        ScrollbarContainer(
            adapter = rememberScrollbarAdapter(scrollState),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ImageSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                )
                AuthSection(
                    login = uiState.isLogin,
                    onLogin = onLogin
                )
            }
        }
    }
}

@Composable
private fun ImageSection(
    modifier: Modifier = Modifier
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
    onLogin: (String, String) -> Unit,
    scrollState: ScrollState? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FluentTheme.colors.background.layer.alt)
            .let {
                if (scrollState != null) {
                    it.verticalScroll(scrollState)
                } else {
                    it
                }
            }
            .padding(32.dp)
            .wrapContentWidth()
            .widthIn(max = 360.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Masuk",
            modifier = Modifier.fillMaxWidth(),
            style = FluentTheme.typography.title
        )
        Spacer(Modifier.height(24.dp))
        if (login) {
            LoginSection()
        } else {
            RegisterSection()
        }
        Spacer(Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AccentButton(
                onClick = {
                    onLogin("", "")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Masuk")
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("Daftar")
            }
        }
    }
}

@Composable
private fun LoginSection() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
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
private fun RegisterSection() {
    var username by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
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
            onLogin = { _, _ -> }
        )
    }
}