package com.perpushub.bandung.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val networkModule = module {
    single { HttpClient(CIO) }
}