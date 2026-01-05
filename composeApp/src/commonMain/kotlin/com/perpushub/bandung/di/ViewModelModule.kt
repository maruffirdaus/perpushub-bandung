package com.perpushub.bandung.di

import com.perpushub.bandung.ui.auth.AuthViewModel
import com.perpushub.bandung.ui.auth.login.LoginViewModel
import com.perpushub.bandung.ui.auth.register.RegisterViewModel
import com.perpushub.bandung.ui.main.MainViewModel
import com.perpushub.bandung.ui.main.bookdetail.BookDetailViewModel
import com.perpushub.bandung.ui.main.borrowing.BorrowingViewModel
import com.perpushub.bandung.ui.main.history.HistoryViewModel
import com.perpushub.bandung.ui.main.home.HomeViewModel
import com.perpushub.bandung.ui.main.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { (id: Int) -> BookDetailViewModel(id, get(), get(), get(), get(), get(), get()) }
    viewModel { BorrowingViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { HistoryViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
}