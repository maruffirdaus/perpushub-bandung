package com.perpushub.bandung.ui.navigation.auth

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface AuthNavKey : NavKey {
    @Serializable
    object Login : AuthNavKey, NavKey

    @Serializable
    object Register : AuthNavKey, NavKey

    companion object {
        val serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Login::class, Login.serializer())
                subclass(Register::class, Register.serializer())
            }
        }
    }
}