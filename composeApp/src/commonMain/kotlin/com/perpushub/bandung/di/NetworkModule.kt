package com.perpushub.bandung.di

import com.perpushub.bandung.BuildConfig
import com.perpushub.bandung.data.local.SessionService
import com.perpushub.bandung.data.remote.AuthService
import com.perpushub.bandung.data.remote.BookService
import com.perpushub.bandung.data.remote.LibraryService
import com.perpushub.bandung.data.remote.LoanRequestService
import com.perpushub.bandung.data.remote.LoanService
import com.perpushub.bandung.data.remote.MapTilerTileStreamProvider
import com.perpushub.bandung.data.remote.UserService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.scope.Scope
import org.koin.dsl.module
import ovh.plrapps.mapcompose.core.TileStreamProvider

val networkModule = module {
    single { provideHttpClient() }
    single { AuthService(get()) }
    single { UserService(get()) }
    single { BookService(get()) }
    single { LibraryService(get()) }
    single { LoanRequestService(get()) }
    single { LoanService(get()) }
    single<TileStreamProvider> { MapTilerTileStreamProvider(get()) }
}

private fun Scope.provideHttpClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    install(Auth) {
        bearer {
            loadTokens {
                val accessToken = get<SessionService>().session.value?.accessToken

                if (accessToken != null) {
                    BearerTokens(accessToken, "")
                } else {
                    null
                }
            }
            sendWithoutRequest {
                it.url.buildString().startsWith(BuildConfig.BACKEND_BASE_URL)
            }
        }
    }
}