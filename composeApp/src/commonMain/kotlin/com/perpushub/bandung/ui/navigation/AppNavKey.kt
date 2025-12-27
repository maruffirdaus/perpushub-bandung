package com.perpushub.bandung.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

object AppNavKey {
    @Serializable
    object Home : NavKey

    @Serializable
    data class BookDetail(
        val id: Int
    ) : NavKey

    @Serializable
    object Borrow : NavKey

    @Serializable
    object Delivery : NavKey

    @Serializable
    object History : NavKey

    @Serializable
    object Profile : NavKey

    val serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Home::class, Home.serializer())
            subclass(BookDetail::class, BookDetail.serializer())
            subclass(Borrow::class, Borrow.serializer())
            subclass(Delivery::class, Delivery.serializer())
            subclass(History::class, History.serializer())
            subclass(Profile::class, Profile.serializer())
        }
    }
}