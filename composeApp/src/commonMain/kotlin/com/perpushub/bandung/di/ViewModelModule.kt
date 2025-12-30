package com.perpushub.bandung.di

import com.perpushub.bandung.ui.bookdetail.BookDetailViewModel
import com.perpushub.bandung.ui.borrowing.BorrowingViewModel
import com.perpushub.bandung.ui.delivery.DeliveryViewModel
import com.perpushub.bandung.ui.history.HistoryViewModel
import com.perpushub.bandung.ui.home.HomeViewModel
import com.perpushub.bandung.ui.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { (id: Int) -> BookDetailViewModel(id, get(), get()) }
    viewModel { BorrowingViewModel(get(), get(), get(), get()) }
    viewModel { DeliveryViewModel() }
    viewModel { HistoryViewModel() }
    viewModel { ProfileViewModel() }
}