package com.perpushub.bandung.di

import com.perpushub.bandung.data.repository.BookRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { BookRepository() }
}