package com.perpushub.bandung.ui.navigation.main

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface MainNavKey : NavKey {
    @Serializable
    object Home : MainNavKey, NavKey

    @Serializable
    data class BookDetail(
        val id: Int
    ) : MainNavKey, NavKey

    @Serializable
    object Borrowing : MainNavKey, NavKey

    @Serializable
    object History : MainNavKey, NavKey

    @Serializable
    object Profile : MainNavKey, NavKey

    companion object {
        val serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(Home::class, Home.serializer())
                subclass(BookDetail::class, BookDetail.serializer())
                subclass(Borrowing::class, Borrowing.serializer())
                subclass(History::class, History.serializer())
                subclass(Profile::class, Profile.serializer())
            }
        }
    }
}