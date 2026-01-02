package com.perpushub.bandung.di

import com.perpushub.bandung.ui.common.messaging.UiMessageManager
import org.koin.dsl.module

val uiModule = module {
    single { UiMessageManager() }
}