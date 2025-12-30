package com.perpushub.bandung.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface AppNavKey : NavKey {
    @Serializable
    object Auth : AppNavKey, NavKey

    @Serializable
    object Main : AppNavKey, NavKey

    companion object {
        val serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Auth::class, Auth.serializer())
                subclass(Main::class, Main.serializer())
            }
        }
    }
}