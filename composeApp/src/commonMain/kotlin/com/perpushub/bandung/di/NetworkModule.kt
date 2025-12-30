package com.perpushub.bandung.di

import com.perpushub.bandung.data.remote.MapTilerTileStreamProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import ovh.plrapps.mapcompose.core.TileStreamProvider

val networkModule = module {
    single { HttpClient(CIO) }
    single<TileStreamProvider> { MapTilerTileStreamProvider(get()) }
}