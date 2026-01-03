package com.perpushub.bandung.ui.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.perpushub.bandung.common.model.User
import com.perpushub.bandung.ui.main.common.component.ExpanderItem
import com.perpushub.bandung.ui.main.common.component.ItemRow
import com.perpushub.bandung.ui.main.profile.model.ProfileTab
import com.perpushub.bandung.ui.theme.AppTheme
import io.github.composefluent.FluentTheme
import io.github.composefluent.component.Icon
import io.github.composefluent.component.ProgressRing
import io.github.composefluent.component.ScrollbarContainer
import io.github.composefluent.component.SelectorBarItem
import io.github.composefluent.component.Text
import io.github.composefluent.component.rememberScrollbarAdapter
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.filled.Person
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileUiState,
    onEvent: (ProfileEvent) -> Unit
) {
    LaunchedEffect(uiState.selectedTab) {
        when (uiState.selectedTab) {
            ProfileTab.ACCOUNT -> onEvent(ProfileEvent.OnUserRefresh)
            ProfileTab.ADDRESS -> onEvent(ProfileEvent.OnAddressesRefresh)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val lazyRowState = rememberLazyListState()

        Spacer(Modifier.height(32.dp))
        Text(
            text = "Profil",
            modifier = Modifier.padding(horizontal = 32.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = FluentTheme.typography.title
        )
        Spacer(Modifier.height(12.dp))
        LazyRow(
            state = lazyRowState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyRowState)
        ) {
            items(ProfileTab.entries) { tab ->
                SelectorBarItem(
                    selected = uiState.selectedTab == tab,
                    onSelectedChange = {
                        onEvent(ProfileEvent.OnSelectedTabChange(tab))
                    },
                    text = {
                        Text(tab.label)
                    },
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )
                    }
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                ProgressRing()
            }
        } else {
            when (uiState.selectedTab) {
                ProfileTab.ACCOUNT -> {
                    if (uiState.user != null) {
                        AccountSection(
                            user = uiState.user
                        )
                    }
                }

                ProfileTab.ADDRESS -> AddressSection()
            }
        }
    }
}

@Composable
private fun ColumnScope.AccountSection(
    user: User
) {
    val scrollState = rememberScrollState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(scrollState),
        modifier = Modifier.weight(1f)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    bottom = 32.dp
                )
        ) {
            if (maxWidth >= 656.dp) {
                Row {
                    AccountPictureSection(
                    )
                    Spacer(Modifier.width(16.dp))
                    AccountInfoSection(
                        user = user
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AccountPictureSection(
                    )
                    Spacer(Modifier.height(16.dp))
                    AccountInfoSection(
                        user = user
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountPictureSection() {
    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape)
            .background(FluentTheme.colors.background.card.default)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = FluentTheme.colors.system.neutral
        )
    }
}

@Composable
private fun AccountInfoSection(
    user: User
) {
    ExpanderItem(
        title = "Info akun",
        defaultExpanded = true
    ) {
        ItemRow(
            items = listOf(
                {
                    Text("Username")
                },
                {
                    Text(user.username)
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Nama lengkap")
                },
                {
                    Text(user.fullName)
                }
            )
        )
        ItemRow(
            items = listOf(
                {
                    Text("Email")
                },
                {
                    Text(user.email)
                }
            ),
            separatorVisible = false
        )
    }
}

@Composable
private fun ColumnScope.AddressSection() {
    val scrollState = rememberScrollState()

    ScrollbarContainer(
        adapter = rememberScrollbarAdapter(scrollState),
        modifier = Modifier.weight(1f)
    ) {

    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreenContent(
            uiState = ProfileUiState(),
            onEvent = {}
        )
    }
}