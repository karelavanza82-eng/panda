package com.aurora.music.di

import com.aurora.music.viewmodels.AccountViewModel
import com.aurora.music.viewmodels.AccountSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountPresentationModule = module {
    viewModelOf(::AccountViewModel)
    viewModelOf(::AccountSettingsViewModel)
}
