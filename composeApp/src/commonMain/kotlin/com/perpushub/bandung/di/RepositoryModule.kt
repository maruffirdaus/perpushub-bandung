package com.perpushub.bandung.di

import com.perpushub.bandung.data.repository.AuthRepository
import com.perpushub.bandung.data.repository.BookRepository
import com.perpushub.bandung.data.repository.LibraryRepository
import com.perpushub.bandung.data.repository.LoanRepository
import com.perpushub.bandung.data.repository.LoanRequestRepository
import com.perpushub.bandung.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(), get()) }
    single { UserRepository(get()) }
    single { BookRepository(get()) }
    single { LibraryRepository(get()) }
    single { LoanRequestRepository(get()) }
    single { LoanRepository(get()) }
}