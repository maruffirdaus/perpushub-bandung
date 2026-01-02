package com.perpushub.bandung.di

import com.perpushub.bandung.data.remote.MapTilerTileStreamProvider
import io.ktor.client.HttpClient
import org.koin.dsl.module
import ovh.plrapps.mapcompose.core.TileStreamProvider

val networkModule = module {
    single { HttpClient() }
    single<TileStreamProvider> { MapTilerTileStreamProvider(get()) }
}