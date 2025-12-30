package com.perpushub.bandung.di

import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { BookRepository() }
    single { LoanRepository() }
    single { LibraryRepository() }
}