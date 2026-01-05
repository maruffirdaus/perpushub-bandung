package com.perpushub.bandung.di

import com.perpushub.bandung.data.local.SessionService
import org.koin.dsl.module

val dataModule = module {
    single { SessionService() }
}