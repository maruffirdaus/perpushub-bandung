package com.perpushub.bandung.data.remote

import PerpusHubBandung.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.readRemaining
import kotlinx.io.RawSource
import ovh.plrapps.mapcompose.core.TileStreamProvider

class MapTilerTileStreamProvider(
    private val client: HttpClient
) : TileStreamProvider {
    override suspend fun getTileStream(
        row: Int,
        col: Int,
        zoomLvl: Int
    ): RawSource? {
        val key = BuildConfig.API_KEY
        val url = "https://api.maptiler.com/maps/streets-v4/256/${zoomLvl}/${col}/${row}.png?key=${key}"
        return try {
            client.get(url).bodyAsChannel().readRemaining()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}