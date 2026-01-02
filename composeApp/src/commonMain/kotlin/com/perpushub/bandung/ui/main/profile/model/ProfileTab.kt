package com.perpushub.bandung.ui.main.profile.model

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.composefluent.icons.Icons
import io.github.composefluent.icons.regular.Location
import io.github.composefluent.icons.regular.Person

enum class ProfileTab(val icon: ImageVector, val label: String) {
    ACCOUNT(Icons.Regular.Person, "Akun"),
    ADDRESS(Icons.Regular.Location, "Alamat")
}