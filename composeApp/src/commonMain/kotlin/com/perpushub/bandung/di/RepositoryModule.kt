package com.perpushub.bandung.di

import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRepository
import com.perpushub.bandung.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository() }
    single { UserRepository() }
    single { BookRepository() }
    single { LoanRepository() }
    single { LibraryRepository() }
}