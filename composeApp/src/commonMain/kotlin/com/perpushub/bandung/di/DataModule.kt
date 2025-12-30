package com.perpushub.bandung.di

import com.perpushub.bandung.data.remote.MapTilerTileStreamProvider
import org.koin.dsl.module
import ovh.plrapps.mapcompose.core.TileStreamProvider

val dataModule = module {
    single<TileStreamProvider> { MapTilerTileStreamProvider(get()) }
}