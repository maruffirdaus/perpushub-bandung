package com.perpushub.bandung.di

import com.perpushub.bandung.service.session.SessionManager
import org.koin.dsl.module

val serviceModule = module {
    single { SessionManager(get()) }
}